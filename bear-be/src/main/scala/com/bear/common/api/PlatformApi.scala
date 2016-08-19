package com.bear
package common
package api

import com.bear.common.api.dto._
import com.bear.common.utils.http.LocalHttpClient
import consts.WxConsts.WxUrlCons._
import org.apache.http.client.methods.HttpPost
import org.apache.http.entity.{ContentType, StringEntity}
import utils.JsonUtils._

import scala.util.Try
/**
  * Created by tanghong on 16/7/8.
  */
object PlatformApi {
  /**
    * 获取预授权码
    */
  def getPreauthcode(component_access_token: String, component_appid: String): Try[PreAuthCode] = {
    val location = Plat_BASE_API_URL + s"api_create_preauthcode?component_access_token=$component_access_token"
    val content = writeAny(Map(
      "component_appid" -> component_appid
    ))
    val hp = new HttpPost(location)
    val entity = new StringEntity(content, ContentType.APPLICATION_JSON)
    hp.setEntity(entity)
    LocalHttpClient.handleJson[PreAuthCode](hp)
  }

  /**
    * 使用授权码换取公众号信息
    */
  def getAuthorizerInfo(component_access_token: String, component_appid: String, authorization_code: String)
  : Try[AuthorizationData] = {
    val location = Plat_BASE_API_URL + s"api_query_auth?component_access_token=$component_access_token"
    val content =  writeAny(Map(
      "component_appid" -> component_appid,
      "authorization_code" -> authorization_code
    ))
    val hp = new HttpPost(location)
    val entity = new StringEntity(content, ContentType.APPLICATION_JSON)
    hp.setEntity(entity)
    LocalHttpClient.handleJson[AuthorizationData](hp)
  }

  /**
    * 刷新授权公众号令牌
    */
  def refreshAuthorizerAccessToken(component_access_token: String, component_appid: String,
                                   authorizer_appid: String,
                                   authorizer_refresh_token: String): Try[RefreshAuthorizerAccessToken] = {
    val location = Plat_BASE_API_URL + s"api_authorizer_token?component_access_token=$component_access_token"
    val content = writeAny(Map(
      "component_appid" -> component_appid,
      "authorizer_appid" -> authorizer_appid,
      "authorizer_refresh_token" -> authorizer_refresh_token
    ))
    val hp = new HttpPost(location)
    val entity = new StringEntity(content, ContentType.APPLICATION_JSON)
    hp.setEntity(entity)
    LocalHttpClient.handleJson[RefreshAuthorizerAccessToken](hp)
  }

  /**
    * 获取授权方账户信息
    */
  def getAuthorizerAccountInfo(component_access_token: String, component_appid: String, authorizer_appid: String)
  : Try[AuthorizerAccountInfo] = {
    val location = Plat_BASE_API_URL + s"api_get_authorizer_info?component_access_token=$component_access_token"
    val content = writeAny(Map(
      "component_appid" -> component_appid,
      "authorizer_appid" -> authorizer_appid
    ))
    val hp = new HttpPost(location)
    val entity = new StringEntity(content, ContentType.APPLICATION_JSON)
    hp.setEntity(entity)
    LocalHttpClient.handleJson[AuthorizerAccountInfo](hp)
  }

  /**
    * 获取授权方设置信息
    */
  def getAuthorizerOptionInfo(component_access_token: String,
                              component_appid: String,
                              authorizer_appid: String,
                              option_name: String)
  : Try[AuthorizerSettingInfo] = {
    val location = Plat_BASE_API_URL + s"api_get_authorizer_option?component_access_token=$component_access_token"
    val content = writeAny(Map(
      "component_appid" -> component_appid,
      "authorizer_appid" -> authorizer_appid,
      "option_name" -> option_name
    ))
    val hp = new HttpPost(location)
    val entity = new StringEntity(content, ContentType.APPLICATION_JSON)
    hp.setEntity(entity)
    LocalHttpClient.handleJson[AuthorizerSettingInfo](hp)
  }

  /**
    * 设置授权方选项信息
    */
  def setAuthorizerOptionInfo(component_access_token: String,
                              component_appid: String,
                              authorizer_appid: String,
                              option_name: String,
                              option_value: String)
  : Try[WechatResult] = {
    val location = Plat_BASE_API_URL + s"api_set_authorizer_option?component_access_token=$component_access_token"
    val content = writeAny(Map(
      "component_appid" -> component_appid,
      "authorizer_appid" -> authorizer_appid,
      "option_name" -> option_name,
      "option_value" -> option_value
    ))
    val hp = new HttpPost(location)
    val entity = new StringEntity(content, ContentType.APPLICATION_JSON)
    hp.setEntity(entity)
    LocalHttpClient.handleJson[WechatResult](hp)
  }
}
