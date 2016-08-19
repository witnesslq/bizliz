package com.bear.common
package api

import utils.JsonUtils._
import consts.WxConsts.WxUrlCons._
import utils.http.LocalHttpClient
import com.bear.common.api.dto._
import org.apache.http.client.methods.{HttpGet, HttpPost}
import org.apache.http.entity.{ContentType, StringEntity}
import collection.JavaConverters._
import scala.util.Try
/**
  * Created by Apple on 16/6/6.
  */
object TokenApi {

  /**
    * 获取第三方平台的 access_token
 *
    * @param appid
    * @param secret
    * @param ticket
    */
  def getPlatFormAccessToken(appid: String, secret: String, ticket: String = ""): Try[ComAccessToken] = {
    val location = Plat_BASE_API_URL + "api_component_token"
    val hp: HttpPost = new HttpPost(location)
    val content = writeAny(Map(
      "component_appid" -> appid,
      "component_appsecret" -> secret,
      "component_verify_ticket" -> ticket
    ))
    val entity: StringEntity  = new StringEntity(content, ContentType.APPLICATION_JSON)
    hp.setEntity(entity)
    LocalHttpClient.handleJson[ComAccessToken](hp)
  }

  /**
    * 获取普通公众号的 access_token
 *
    * @param appid
    * @param secret
    * @return
    */
  def getCommonAccessToken(appid: String, secret: String): Try[AccessToken] = {
    val location = BASE_API_URI + s"/cgi-bin/token?grant_type=client_credential&appid=$appid&secret=$secret"
    val hp: HttpGet = new HttpGet(location)
    LocalHttpClient.handleJson[AccessToken](hp)
  }

}
