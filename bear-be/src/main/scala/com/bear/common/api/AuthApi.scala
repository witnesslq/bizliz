package com.bear
package common
package api

import com.bear.common.utils.http.LocalHttpClient
import common.consts.WxConsts.MenuButtonCons._
import common.consts.WxConsts.WxUrlCons._
import common.api.dto._
import org.apache.http.client.methods.{HttpGet, HttpPost}
import org.apache.http.entity.{ContentType, StringEntity}

import scala.util.Try
/**
  * Created by tanghong on 16/6/14.
  */
object AuthApi {

  def getWebAuthCode(appId: String, url: String, scope: String = OAUTH2_SCOPE_USER_INFO) = {
    OPEN_URI + s"/connect/oauth2/authorize?appid=$appId&redirect_uri=$url&response_type=code&scope=$scope&state=123#wechat_redirect"
  }

  /**
    * 获取网页授权accesstoken
 *
    * @param appId
    * @param secret
    * @param code
    * @return
    */
  def getWebAccessToken(appId: String, secret: String, code: String): Try[WebAuthToken] = {
    val location = BASE_API_URI  + s"/sns/oauth2/access_token?appid=$appId&secret=$secret&code=$code&grant_type=authorization_code"
    val hg = new HttpGet(location)
    LocalHttpClient.handleJson[WebAuthToken](hg)
  }

  /**
    * 获取网页授权用户信息
 *
    * @param access_token
    * @param openid
    * @return
    */
  def getWebUserInfo(access_token: String, openid: String): Try[WebAuthUserInfo] = {
    val location = s"${BASE_API_URI}/sns/userinfo?access_token=${access_token}&openid=${openid}&lang=zh_CN"
    val hg = new HttpGet(location)
    LocalHttpClient.handleJson[WebAuthUserInfo](hg)
  }


  ////////////////////////
  ////第三平台网页授权API////
  ///////////////////////

  def getTerraceWebAuthCode(appID: String, comAPPID: String, redirectUri: String,
                            actionScope: String = OAUTH2_SCOPE_BASE, state: String = "123"
                           ): String = {
    s"https://open.weixin.qq.com/connect/oauth2/authorize?appid=$appID&redirect_uri=$redirectUri&" +
      s"response_type=code&scope=$actionScope&state=$state&component_appid=$comAPPID#wechat_redirect"
  }

  /**
    * 获取网页授权access_token
    *
    * @param appID
    * @param code
    * @param comAppID
    * @param comAccessToken
    * @return
    */
  def getTerraceWebAccessToken(appID: String, code: String, comAppID: String, comAccessToken: String)
  : Try[WebstieAccessToken] = {
    val location = s"https://api.weixin.qq.com/sns/oauth2/component/access_token?" +
      s"appid=$appID&code=$code&grant_type=authorization_code&component_appid" +
      s"=$comAppID&component_access_token=$comAccessToken"
    // 这里可以拿到unid需要验证
    val hg = new HttpGet(location)
    LocalHttpClient.handleJson[WebstieAccessToken](hg)
  }

  /**
    * 刷新网页授权access_token
    */
  def refreshTerraceWebAccessToken(appID: String, refreshToken: String, comAppID: String, comAccessToken: String)
  : Try[WebstieAccessToken] = {
    val location = s"https://api.weixin.qq.com/sns/oauth2/component/refresh_token?appid=$appID&" +
      s"grant_type=$refreshToken&component_appid" +
      s"=$comAppID&component_access_token=$comAccessToken&refresh_token=$refreshToken"
    val hg = new HttpGet(location)
    LocalHttpClient.handleJson[WebstieAccessToken](hg)
  }

  /**
    * 获取网页授权用户信息 要求scope必须为snsapi_userinfo
    *
    * @param webAccessToken
    * @param openId
    * @return
    */

  def getTerraceWebUserInfo(webAccessToken: String, openId: String): Try[WebsiteUserInfo] = {
    val location = s"https://api.weixin.qq.com/sns/userinfo?access_token=$webAccessToken&openid=$openId&lang=zh_CN"
    val hg = new HttpGet(location)
    LocalHttpClient.handleJson[WebsiteUserInfo](hg)
  }

  /**
    * 长连接转成短连接
 *
    * @param url
    * @param accessToken
    * @return
    */
  def getLong2ShortUrl(url: String, accessToken: String): Try[TarUrlInfo] = {
    val location = BASE_API_URI + s"/cgi-bin/shorturl?access_token=$accessToken"
    val content = s"""{\"action\":\"long2short\",\"long_url\":\"$accessToken\"}"""
    val hp = new HttpPost(location)
    val entity = new StringEntity(content)
    hp.setEntity(entity)
    LocalHttpClient.handleJson[TarUrlInfo](hp)
  }


}
