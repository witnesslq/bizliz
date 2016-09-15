package com.bear.common
package utils
package http

import java.io.{File, InputStream}

import com.bear.common.exception.WeixinException
import com.bear.common.utils.cla.ConvertUitls
import org.apache.http.client.methods.{HttpPost, HttpUriRequest}
import org.apache.http.entity.ContentType
import org.apache.http.entity.mime.MultipartEntityBuilder
import org.apache.http.impl.client.CloseableHttpClient
import org.apache.http.util.EntityUtils

import scala.util.{Failure, Success, Try}
import org.json4s._
import org.json4s.native.JsonMethods._
import org.slf4j.LoggerFactory

/**
  * Created by Apple on 16/6/6.
  */
object LocalHttpClient {


  private[this] implicit val formats = DefaultFormats

  private[this] lazy val client: CloseableHttpClient = HttpClientFactory.createClient(10, 100)

  val logger = LoggerFactory.getLogger(LocalHttpClient.getClass)

  def handle(request: HttpUriRequest): Try[String] = {
    try {
      val response = client.execute(request)
      val status = response.getStatusLine.getStatusCode
      if (status >= 200 && status < 300) {
        val content = EntityUtils.toString(response.getEntity, "UTF-8")
        Success(content)
      }
      else {
        //client.close()
        Failure(new Exception("http handle failure"))
      }
    } catch {
      case e: Exception => Failure(e)
    }
  }

  /**
    * http请求处理接口
 *
    * @param request
    * @param des 接口调用描述
    * @tparam T
    * @return
    */
  def handleJson[T: Manifest](request: HttpUriRequest, des: String = ""): Try[T] = {
    //val clazz = (implicitly[Manifest[T]]).runtimeClass
    try {
      val response = client.execute(request)
      val status = response.getStatusLine.getStatusCode
      if (status >= 200 && status < 300) {
        val content = EntityUtils.toString(response.getEntity, "UTF-8")
        logger.info(s"weixin return content $content")
        val jValue = parse(content)
        jValue.\\("errcode") match {
          case JInt(code) if code == 0 =>
            val e = WeixinException(content)
            e.log
            Failure(e)
          case _ =>
            val json = jValue.extract[T]
            Success(json)
        }
      }
      else {
        //client.close()
        logger.info(s"$des http send failure")
        Failure(new Exception("http send failure"))
      }
    } catch {
      case e: Exception => logger.error(e.getMessage)
        Failure(e)
    }
  }

  // 上传的一些设置需要优化:header
  def handleWxUpload[T: Manifest](url: String, file: File): Try[T] = {
    //val clazz = (implicitly[Manifest[T]]).runtimeClass
    try {
      val post = new HttpPost(url)
      post.setHeader("Connection", "Keep-Alive")
      post.setHeader("Cache-Control", "no-cache")
      val entity = MultipartEntityBuilder.create().addBinaryBody("media", file,
        ContentType.APPLICATION_OCTET_STREAM, file.getName).build()
      post.setEntity(entity)
      val response = client.execute(post)
      val status = response.getStatusLine.getStatusCode
      if (status >= 200 && status < 300) {
        val content = EntityUtils.toString(response.getEntity)
        logger.info(s"weixin upload return content $content")
        val jValue = parse(content)
        jValue.\\("errcode") match {
          case JInt(code) if code == 0 =>
            val e = WeixinException(content)
            e.log
            Failure(e)
          case _ =>
            val json = jValue.extract[T]
            Success(json)
        }
      }
      else {
        //client.close()
        Failure(WeixinException("http handle failure"))
      }
    } catch {
      case e: Exception => Failure(e)
    }
  }

  def handleWxUpload[T: Manifest](url: String, inputStream: Array[Byte], fileName: String): Try[T] = {
    //val clazz = (implicitly[Manifest[T]]).runtimeClass
    try {
      val post = new HttpPost(url)
      post.setHeader("Connection", "Keep-Alive")
      post.setHeader("Cache-Control", "no-cache")
      val bytes = ConvertUitls.array2JArr(inputStream)
      val contentType: ContentType = ContentType.MULTIPART_FORM_DATA
      val entity = MultipartEntityBuilder.create().addBinaryBody("media", bytes, contentType, fileName).build()
      post.setEntity(entity)
      val response = client.execute(post)
      val status = response.getStatusLine.getStatusCode
      if (status >= 200 && status < 300) {
        val content = EntityUtils.toString(response.getEntity, "UTF-8")
        logger.info(s"weixin upload return content $content")
        val jValue = parse(content)
        jValue.\\("errcode") match {
          case JInt(code) if code == 0 =>
            val e = WeixinException(content)
            e.log
            Failure(e)
          case _ =>
            val json = jValue.extract[T]
            Success(json)
        }
      }
      else {
        //client.close()
        logger.error("http send failure")
        Failure(new Exception("http send failure"))
      }
    } catch {
      case e: Exception =>
        logger.error(e.getMessage)
        Failure(e)
    }
  }

}


