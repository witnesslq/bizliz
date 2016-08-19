package com.bear.common
package api
package message

import com.bear.common.consts.WxConsts.WxUrlCons._
import com.bear.common.utils.JsonUtils._
import com.bear.common.utils.http.LocalHttpClient
import org.apache.http.client.methods.HttpPost
import org.apache.http.entity.{ContentType, StringEntity}
import org.json4s._
import org.json4s.JsonDSL._

import scala.util.Try
/**
  * Created by tanghong on 16/7/11.
  */
object ServiceMessageApi {

  /**
    * 客服消息发送
 *
    * @param toUser
    * @param content
    * @param accessToken
    * @return
    */
  def sendServiceMsg(toUser: String, content: String, accessToken: String): Try[String] = {
    val location = BASE_API_URI + s"/cgi-bin/message/custom/send?access_token=$accessToken"
    val hp = new HttpPost(location)
    val msg = writeAny(Map(
      "touser" -> toUser,
      "msgtype" -> "text",
      "text" -> Map("content" -> content)
    ))
    val entity = new StringEntity(msg, ContentType.APPLICATION_JSON)
    hp.setEntity(entity)
    LocalHttpClient.handle(hp)
  }
}
