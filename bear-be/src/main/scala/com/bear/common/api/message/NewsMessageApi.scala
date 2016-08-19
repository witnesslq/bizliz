package com.bear.common
package api
package message

import com.bear.common.api.dto.WxNewsSendResult
import com.bear.common.consts.WxConsts.WxUrlCons._
import com.bear.common.utils.JsonUtils._
import com.bear.common.utils.http.LocalHttpClient
import org.apache.http.client.methods.HttpPost
import org.apache.http.entity.{ContentType, StringEntity}
import org.json4s._
import org.json4s.JsonDSL._

import scala.util.Try

/**
  * Created by tanghong on 16/6/22.
  */
object NewsMessageApi {

  def sendNewsByOpenIds(access_token: String, media_id: String, touser: Seq[String]): Try[WxNewsSendResult] = {
    val location = s"${BASE_API_URI}/cgi-bin/message/mass/send?access_token=${access_token}"
    val hp = new HttpPost(location)
    val content = writeJValue(
      ("touser" -> touser) ~
        ("mpnews" -> ("media_id" -> media_id)) ~
        ("msgtype" -> "mpnews")
    )
    println(content)
    val entity = new StringEntity(content, ContentType.APPLICATION_JSON)
    hp.setEntity(entity)
    LocalHttpClient.handleJson[WxNewsSendResult](hp)
  }
}
