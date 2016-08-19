package com.bear.common
package utils

import utils.Json.WrapJsonWriter
import org.json4s._
import org.json4s.JsonDSL._
import org.json4s.native.JsonMethods._
import org.json4s.native.Serialization._
import com.bear.web.dto.{JsonDto, ResultDto}
import scala.collection.JavaConversions._

/**
  * json4s act on read json of data and non support java bean
  * jackson scala act on write json of data and support java bean
  * json4s and jackson mixing operating will produce bug
  */
trait BaseJsonUtils {

  // not support case class
  def map2Bean[T: Manifest](map: Map[String, Any]): T = {
    val clazz = (implicitly[Manifest[T]]).runtimeClass
    //clazz.getConstructors()(0).newInstance(args: _*)
    val obj = clazz.newInstance.asInstanceOf[T]
    try {
      clazz.getDeclaredFields.foreach { field =>
        field.setAccessible(true)
        val fileName = field.getName
        if (!fileName.equals("class")) {
          map.get(fileName).map{ v =>
            field.set(obj, v)
          }
        }
      }
      obj
    } catch {
      case e: Exception => e.printStackTrace()
      obj
    }
  }


  def bean2Map[T: Manifest](any: T): Map[String, Any] = {
    val clazz = (implicitly[Manifest[T]]).runtimeClass
    val map = scala.collection.mutable.Map[String, Any]()
    try {
      clazz.getDeclaredFields.foreach { field =>
        field.setAccessible(true)
        val fileName = field.getName
        if (!fileName.equals("class")) {
          map.put(fileName, field.get(any))
        }
      }
      map.toMap
    } catch {
      case e: Exception => e.printStackTrace()
      map.toMap
    }
  }

}

object JsonUtils extends BaseJsonUtils {

  implicit val NO_TYPE_FORMAT = formats(NoTypeHints)

  implicit def jsonDto2String(rd: Option[JsonDto]): String = {
    rd match {
      case Some(r) if r.isInstanceOf[ResultDto] => writeAny(r)
      case _ => write(ResultDto(0, "failure"))
    }
  }

  implicit def resultDto2String(rd: ResultDto): String = {
    writeAny(rd)
  }

  implicit def json2String(json: JValue): String = {
    writeAny(json)
  }

  def writeJValue(json: JValue): String = {
    writeAny(json)
  }

  def toJValue[T <: AnyRef](o: T)(implicit formats: Formats): JValue = {
    parse(write(o))
  }

  def readerObj[T: Manifest](str: String): T = {
    parse(str).extract[T]
  }

  def writeAny[T <: AnyRef](obj: T)(implicit formats: Formats): String = {
    WrapJsonWriter.write(obj)
  }

}