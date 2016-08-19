package com.bear.common
package utils

/**
  * Created by Apple on 16/6/7.
  */
import java.security.SecureRandom
import java.nio.ByteBuffer
import java.util.{StringTokenizer, UUID}

import com.bear.common.utils.Magic._

import scala.util.Try

/**
  * 数据处理
  */
trait DataUtils {
  import Magics._

  private[this] val srandom = SecureRandom.getInstance("SHA1PRNG");//new SecureRandom()

  // 禁止修改下面函数
  def genToken: String = genString
  def genSalt: String = genString

  // 随机生成字符串(长12位)
  def genString: String = genString(12)
  // 随机生成指定长度的随机字符串
  def genString(size: Int): String = (0 until size).map { _ => num2char(srandom.nextInt(base)) }.mkString

  // 随机生成 Long
  def genLong: Long = {
    val bytes = new Array[Byte](16)
    srandom.nextBytes(bytes)
    ByteBuffer.wrap(bytes).getLong
  }

  // 随机生成数字字符串
  def genNumString(size: Int): String = (0 until size).map { _ => nums(srandom.nextInt(10)) }.mkString

  // 随机生成无符号的 Long
  def genULong: Long = math.abs(genLong)

  def genInt: Int = {
    val bytes = new Array[Byte](8)
    srandom.nextBytes(bytes)
    ByteBuffer.wrap(bytes).getInt
  }

  def genUInt: Int = math.abs(genInt)

  // 将 BigInt 编码成62进制(目前只处理正数, 内部使用暂不开放，若 Long 不能满足唯一性，可以使用 UUID)
  // TODO: 增加负数处理机制
  private[this] def encode62Big(num: BigInt): String = {
    if (num.toInt == 0) return "0"
    val str = new StringBuilder(24)
    var i = num
    while (i > 0) {
      str.append(num2char.getOrElse((i % base).toInt, '0'))
      i = i / base
    }
    str.toString.reverse
  }

  // 将 UUID 转换成62进制
  def encode62(uuid: UUID): String = {
    val uu = uuid.toString.split("-").mkString("")
    val num = uu.foldLeft(BigInt(0L))((total, i) => total * 16 + i - '0')
    encode62Big(num)
  }

  /**
    * 默认重试 5 次操作，若失败，则返回 Failure, 若成功返回 Success, 包含成功的值
    */
  def retry[F, T](gen: => F, count: Int = 5)(f: F => T): Try[T] = {
    val result = Try(f(gen))
    if (result.isSuccess || count <= 1) result else retry(gen, count - 1)(f)
  }

  /** 字符串星化 */
  def withStar(str: String) = {
    def star(pre: Int, post: Int) = str.take(pre) + "*" * (str.length - pre - post) + str.takeRight(post)
    str.length match {
      case i if i > 10 => star(3, 4)
      case i if i > 7 => star(2, 2)
      case i if i > 4 => star(1, 1)
      case i => star(0, 0)
    }
  }

  /** 对字符串编码，主要是在 mysql 导出时能够正常导入 */
  private[this] val CharMap = Map(
    '\\' -> """\\""",
    '\u0000' -> """\0""",
    '\n' -> """\n""",
    '\r' -> """\r""",
    '\u001A' -> """\Z""",
    '\'' -> """\'""",
    ';' -> """\;""",
    '"' -> "\\\""
  )
  def escapeSql(str: String) = {
    if (str.exists(CharMap.contains)) {
      val sb = new StringBuilder(str.length)
      str.foreach { c =>
        sb.append(CharMap.getOrElse(c, c))
      }
      sb.toString
    } else {
      str
    }
  }

  // 简单计时
  def timing[T](f: => T)(h: Long => Unit): T = {
    val begin = System.currentTimeMillis
    val ret = f
    h(System.currentTimeMillis - begin)
    ret
  }

  def unicode2String(unicode: String): String = {
    new String(unicode.getBytes("ISO8859-1"), "GBK");
  }


}
