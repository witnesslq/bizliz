package com.bear.common.utils

/**
  * Created by Apple on 16/6/7.
  */
import java.io.File
import java.nio.file._
import java.security._
import javax.crypto._
import javax.crypto.spec._
import java.security.spec._
import org.joda.time._
import scala.util.Try

/**
*安全有关函数
  */
trait SafeUtils {
  import Magic._

  // 生成公钥私钥, 指定密匙长度（取值范围：512～2048）, 默认 1024 位
  def genKeyPair(in: Int = 1024): (PublicKey, PrivateKey) = {
    //生成证书, 创建‘密匙对’生成器
    val kpg = KeyPairGenerator.getInstance("RSA")
    //指定密匙长度（取值范围：512～2048）
    kpg.initialize(in)
    //生成‘密匙对’，其中包含着一个公匙和一个私匙的信息
    val kp = kpg.genKeyPair()
    //获得公匙和私钥
    (kp.getPublic(), kp.getPrivate())
  }

  // 使用私钥对 plain 进行签名
  def signPrivate(priKey: PrivateKey, plain: Array[Byte]): Array[Byte] = {
    // 用私钥对信息生成数字签名
    val signature = Signature.getInstance("MD5withRSA")
    signature.initSign(priKey)
    signature.update(plain)
    signature.sign()
  }

  // 使用 SecretKey 对 plain 进行签名
  def sign(signKey: SecretKey, plain: Array[Byte]): Array[Byte] = {
    val mac = Mac.getInstance("HmacSHA1")
    mac.init(signKey)
    mac.doFinal(plain)
  }

  /*// 对当前日期进行签名, 使用 | 分割
  def signDate(signKey: SecretKey): String = {
    val date = LocalDate.now.simple
    sign(signKey, date.bytes).hex + "|" + date
  }*/

  // 从签名中分离出信息
  def extractSignedDate(signKey: SecretKey, signed: String): Option[String] = {
    signed.split("\\|", 2) match {
      case Array(signature, raw) if sign(signKey, raw.bytes).hex == signature => Some(raw)
      case _ => None
    }
  }

  // 使用公钥校验签名
  def verifyPublic(pubKey: PublicKey, plain: Array[Byte], signed: Array[Byte]): Boolean = {
    // 解密由 base64 编码的数字签名
    val checker = Signature.getInstance("MD5withRSA")
    checker.initVerify(pubKey)
    checker.update(plain)
    // 验证签名是否正常
    checker.verify(signed)
  }

  // 用公钥加密
  def encryptPublic(pubKey: PublicKey, plain: Array[Byte]): Array[Byte] = {
    //获得一个RSA的Cipher类，使用公鈅加密
    val cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding")
    cipher.init(Cipher.ENCRYPT_MODE, pubKey)
    cipher.doFinal(plain)
  }

  // 用私钥解密
  def decryptPrivate(priKey: PrivateKey, encrypt: Array[Byte]): Array[Byte] = {
    //获得一个私鈅加密类 Cipher，ECB 是加密方式，PKCS5Padding 是填充方法
    val cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding")
    cipher.init(Cipher.DECRYPT_MODE, priKey)
    cipher.doFinal(encrypt)
  }

  // 使用随机 AES Key 密码加密，返回随机 AES 证书密钥、加密后的内容
  def encryptAES(plain: Array[Byte], lenght: Int = 128): (SecretKey, Array[Byte]) = {
    //通过 KeyGenerator 形成一个 key
    val keyGen = KeyGenerator.getInstance("AES")
    keyGen.init(lenght)
    val key = keyGen.generateKey()
    //获得一个密码加密类 Cipher，ECB 是加密方式，PKCS5Padding 是填充方法
    val cipher = Cipher.getInstance("AES/ECB/PKCS5Padding")
    //使用密钥加密
    cipher.init(Cipher.ENCRYPT_MODE, key)
    val cipherText = cipher.doFinal(plain)
    (key, cipherText)
  }

  // 使用指定 AES 密钥加密
  def encryptAES(aesKey: SecretKey, value: Array[Byte]): Array[Byte] = {
    val cipher = Cipher.getInstance("AES/ECB/PKCS5Padding")
    cipher.init(Cipher.ENCRYPT_MODE, aesKey)
    cipher.doFinal(value)
  }

  // 使用 AES Key 解密
  def decryptAES(aesKey: SecretKey, encrypt: Array[Byte]): Array[Byte] = {
    val cipher = Cipher.getInstance("AES/ECB/PKCS5Padding")
    //使用密钥解密
    cipher.init(Cipher.DECRYPT_MODE, aesKey)
    cipher.doFinal(encrypt)
  }

  // AES 通用加解密，加密成 HEX 编码。需要成对调用，生成 hex，解密时也是用 hex， 返回加密后的 hex
  def encrypt(password: String, value: String): String = encryptAES(password.bytes.aesKey, value.bytes).hex
  // 解密， value 是 hex 编码的
  def decrypt(password: String, valueHex: String): String = decryptAES(password.bytes.aesKey, valueHex.hex).string

  // 用 sha1 编码成 hex 格式
  def sha1Hex(text: String): String = text.bytes.sha1.hex
  // 禁止修改下面的函数
  def sha1Hex(code: String, salt: String): String = sha1Hex(s"code=$code;salt=$salt")
  // 获取文件 sha1 编码
  def sha1Hex(file: File): Try[String] = Try {
    val md = MessageDigest.getInstance("SHA-1");
    val is = Files.newInputStream(file.toPath)
    val dis = new DigestInputStream(is, md)
    val buffer = new Array[Byte](8*1024)
    try {
      while(dis.read(buffer) >= 0) {}
    } finally {
      is.close()
    }
    md.digest().hex
  }
}
