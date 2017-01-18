package com.bear
package common
package configs

import java.nio.charset.Charset

import utils.JsonUtils._
import org.json4s._
import org.json4s.Extraction
import org.json4s.native.JsonMethods._
import org.json4s.reflect.ScalaType
import org.springframework.http.{HttpInputMessage, HttpOutputMessage, MediaType}
import org.springframework.http.converter.AbstractHttpMessageConverter
import org.springframework.stereotype.Component
import org.springframework.util.StreamUtils

import scala.collection.JavaConversions._

@Component
class Json4s2HttpMessageConverter[T <: AnyRef] extends AbstractHttpMessageConverter[T]{

  def isScalaType(mf: java.lang.Class[_]): Boolean = {
    def isScalaTrait: Boolean  = {
      mf.getInterfaces.exists{ e =>
        e == classOf[Serializable] || e == classOf[Product]
      }
    }
    if (mf == classOf[JObject]) true
    else if (mf == classOf[JArray]) true
    else if (mf == classOf[JValue]) true
    else if (isScalaTrait) true
    else false
  }

  override def canRead(clazz: java.lang.Class[_], mediaType: MediaType): Boolean = {
    isScalaType(clazz) && mediaType.isCompatibleWith(MediaType.APPLICATION_JSON)
  }

  override def canWrite(clazz: java.lang.Class[_], mediaType: MediaType): Boolean = {
    isScalaType(clazz)
  }

  override def readInternal(clazz: java.lang.Class[_ <: T], inputMessage: HttpInputMessage): T = {
    val in = inputMessage.getBody
    try {
      Extraction.extract(parse(in), ScalaType(clazz)).asInstanceOf[T]
    } catch {
      case e: MappingException => throw e
      case e: Exception =>
        throw new MappingException("unknown error", e)
    }
  }

  override def writeInternal(t: T, outputMessage: HttpOutputMessage): Unit = {
    val out = outputMessage.getBody
    StreamUtils.copy(writeAny(t), Charset.forName("UTF-8"), out)
    out.flush()
  }

  override def supports(clazz: java.lang.Class[_]): Boolean = {
    isScalaType(clazz)
  }

  override def getSupportedMediaTypes: java.util.List[MediaType] = {
    List(MediaType.APPLICATION_JSON_UTF8)
  }

}

