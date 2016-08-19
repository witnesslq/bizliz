package com.bear.common.utils

/**
  * Created by Apple on 16/6/7.
  */
import java.io._
import java.security._
import java.security.spec._
import java.util.zip.{GZIPOutputStream, GZIPInputStream}
import javax.crypto._
import javax.crypto.spec._

import org.apache.commons.codec.binary.{Base64, Hex}
import org.joda.time._
import org.joda.time.format._

import scala.annotation.tailrec
import scala.language.implicitConversions

/**
*魔幻类
  */
object Magic {
  import Magics._

  implicit class RichString2(string: String) {
    /** i18n */
   // def i(args: Any*)(implicit lang: Lang): String = Messages(string, args: _*)(lang)
    /** i18n */
    //def i: String = i()
    /** Symbol */
    def s = Symbol(string)
    /** html */
    //def h = if (string.isEmpty) EmptyHtml else Html(string)
    /** 星化 */
    //def star = withStar(string)
    /** 是否为 email */
    def isEmail = string.contains("@")
    /** UTF-8 Bytes*/
    def bytes: Array[Byte] = string.getBytes("UTF-8")
    /** hex */
    def hex: Array[Byte] = Hex.decodeHex(string.toCharArray)
    /** Base64 */
    def base64: Array[Byte] = Base64.decodeBase64(string)
    // 解码62进制成 Long (目前只处理正数) TODO: 增加负数处理机制
    def base62: Long = string.foldLeft(0L)((total, i) => total * base + char2num.getOrElse(i, 0))
    // 转成 DateTime
    def datetime = if (string.exists(_ == ':')) simpleDateTimeFormat.parseDateTime(string) else simpleDateFormat.parseDateTime(string)
    // 转成 LocalTime
    def time = if (string.count(_ == ':') == 2) simpleTimeFormat.parseLocalTime(string) else simpleTimeFormat2.parseLocalTime(string)
    // 转成 LocalDate
    def date = if (string.exists(_ == ':')) simpleDateTimeFormat.parseLocalDate(string) else simpleDateFormat.parseLocalDate(string)
  }



  implicit class RichArray(bytes: Array[Byte]) {
    /** String */
    def string: String = new String(bytes, "UTF-8")
    /** 转成 Hex */
    def hex: String = new String(Hex.encodeHex(bytes))
    /** 转成 Base64 */
    def base64: String = new String(Base64.encodeBase64(bytes), "UTF-8")
    /** 转成 MD5 */
    def md5: Array[Byte] = {
      val digest = MessageDigest.getInstance("MD5")
      digest.update(bytes)
      digest.digest()
    }
    /** 转成 sh1 */
    def sha1: Array[Byte] = {
      val digest = MessageDigest.getInstance("SHA-1")
      digest.update(bytes)
      digest.digest()
    }
    // 转成公钥
    def publicKey: PublicKey = {
      // 构造X  509EncodedKeySpec 对象
      val spec = new X509EncodedKeySpec(bytes)
      // RSA对称加密算法
      val kf = KeyFactory.getInstance("RSA")
      // 取公钥匙对象
      kf.generatePublic(spec)
    }
    // 转成私钥
    def privateKey: PrivateKey = {
      val spec = new PKCS8EncodedKeySpec(bytes)
      val kf = KeyFactory.getInstance("RSA")
      kf.generatePrivate(spec)
    }
    // 转成 AES 私钥
    def aesKey: SecretKey = new SecretKeySpec(padding(bytes), "AES")
    // 转成 Sign 用的
    def signKey: SecretKey = new SecretKeySpec(bytes, "HmacSHA1")
    // gzip 压缩
   /* def gzip: Array[Byte] = {
      val in = new ByteArrayInputStream(bytes)
      val out = new ByteArrayOutputStream(1024)
      val gout = new GZIPOutputStream(out)
      copy(in, gout)
      gout.finish()
      gout.flush()
      gout.close()
      out.toByteArray
    }
    // gunzip 解压
    def gunzip: Array[Byte] = {
      val in = new ByteArrayInputStream(bytes)
      val gin = new GZIPInputStream(in)
      val out = new ByteArrayOutputStream(1024)
      copy(gin, out)
      gin.close()
      out.toByteArray
    }
  }*/

  implicit class RichDateTime(date: DateTime) extends Ordered[DateTime] {
    def compare(x: DateTime) = date.compareTo(x)
    def simple = date.toString(simpleDateTimeFormat)
    // 紧凑型
    def compact = date.toString(compactDateTimeFormat)
  }

  implicit class RichLocalTime(time: LocalTime) extends Ordered[LocalTime] {
    def compare(x: LocalTime) = time.compareTo(x)
    def simple = time.toString(simpleTimeFormat)
  }

  implicit class RichLocalDate(day: LocalDate) extends Ordered[LocalDate] {
    def compare(x: LocalDate) = day.compareTo(x)
    def simple = day.toString(simpleDateFormat)
  }


  implicit class RichLong(value: Long) {
    // 将 Long 编码成62进制(目前只处理正数) TODO: 增加负数处理机制
    def base62: String = {
      if (value == 0) return num2char(0).toString
      val str = new StringBuilder(12)
      var i = value
      while (i > 0) {
        str.append(num2char.getOrElse((i % base).toInt, 'E'))
        i = i / base
      }
      str.toString.reverse
    }
  }

  implicit class RichFile(file: File) {
    @tailrec
    final def subOf(parent: File): Boolean = {
      parent match {
        case null => false
        case _ if file == parent => true
        case _ => file.getParentFile.subOf(parent)
      }
    }
  }

}

private[utils] object Magics {
  // 62进制对应关系，禁止修改该值 (TODO: 从配置文件获取, 但要保证第一个数字为 E)
  val all = "EeXsx84n9NjyTYtJuUfFAa5mMIivG61VqQLbgBlPp02CHcWh7rKwR3kODZozSd"
  val nums = "0123456789"
  val base = all.length
  val char2num = all.map(i => (i, all.indexOf(i))).toMap
  val num2char = char2num.map(_.swap)

  // AES 密码需要16位，因此需要填充
  def padding(bytes: Array[Byte]): Array[Byte] = {
    if (bytes.length >= 16)
      bytes.slice(0, 16)
    else
      bytes ++ Array.fill(16 - bytes.length)(0.toByte)
  }
  //时间相关
  val simpleDateTimeFormat = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss")
  // 作为文件名保存的
  val compactDateTimeFormat = DateTimeFormat.forPattern("yyyyMMdd_HHmmss")
  val simpleDateFormat = DateTimeFormat.forPattern("yyyy-MM-dd")
  val simpleTimeFormat = DateTimeFormat.forPattern("HH:mm:ss")
  val simpleTimeFormat2 = DateTimeFormat.forPattern("HH:mm")
  }


}