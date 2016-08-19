package com.bear
package common
package api

import com.bear.common.consts.WxConsts.WxUrlCons._
import common.utils.JsonUtils._
import com.bear.common.utils.http.LocalHttpClient
import org.apache.http.client.methods.HttpPost
import org.apache.http.entity.{ContentType, StringEntity}
import org.json4s._
import org.json4s.JsonDSL._
import org.json4s.native.JsonMethods._

import scala.util.Try
/**
  * Created by Apple on 16/6/10.
  */
object PreviewApi {

  def sendPervNews(access_token: String, media_id: String, touser: String): Try[JValue] = {
    val location = s"${BASE_API_URI}/cgi-bin/message/mass/preview?access_token=${access_token}"
    val hp = new HttpPost(location)
    val content = writeJValue(
      ("touser" -> touser) ~
      ("mpnews" -> ("media_id" -> media_id)) ~
      ("msgtype" -> "mpnews")
    )
    val entity = new StringEntity(content, ContentType.APPLICATION_JSON)
    hp.setEntity(entity)
    LocalHttpClient.handleJson[JValue](hp)
  }

}
