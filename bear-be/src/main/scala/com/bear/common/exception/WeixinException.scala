package com.bear
package common
package exception

import org.json4s._
import org.json4s.JsonDSL._
import common.utils.JsonUtils._
import org.json4s.native.JsonMethods._
import org.slf4j.LoggerFactory
import scala.util.{Failure, Success, Try}

/**
  * Created by tanghong on 16/7/6.
  */
case class WeixinException(msg: String, des: String = "") extends Exception{
  val logger = LoggerFactory.getLogger(classOf[WeixinException])

  def log = {
    parse(msg).\\("errcode") match {
      case JInt(code) if code == 0 => logger.info(s"weixin handle $des success: $msg")
      case _ => logger.info(s"weixin handle $des failure: $msg")
    }
  }

  def toObj[T: Manifest]: Try[T] = {
    val jvalue = parse(msg)
    jvalue.\\("errcode") match {
      case JInt(code) if code == 0 => Success(jvalue.extract[T])
      case _ => Failure(WeixinException("weixin exception"))
    }
  }
}