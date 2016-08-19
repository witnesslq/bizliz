package com.bear
package common
package api

import com.bear.common.api.dto._
import com.bear.common.utils.http.LocalHttpClient
import consts.WxConsts.WxUrlCons._
import org.apache.http.client.methods.{HttpGet, HttpPost}
import org.apache.http.entity.{ContentType, StringEntity}
import org.json4s.JValue
import utils.JsonUtils._

import scala.util.Try


/**
  * Created by Apple on 16/6/6.
  */
object MenuApi {
  def createMenu(menus: JValue, accessToken: String): Try[WechatResult] = {
    val location = BASE_API_URI + s"/cgi-bin/menu/create?access_token=$accessToken"
    val hp = new HttpPost(location)
    val entity = new StringEntity(writeJValue(menus))
    hp.setEntity(entity)
    LocalHttpClient.handleJson[WechatResult](hp)
  }

  def deleteMenu(accessToken: String) = {
    val location = BASE_API_URI + s"/cgi-bin/menu/delete?access_token=$accessToken"
    val hg = new HttpGet(location)
    LocalHttpClient.handleJson[WebstieAccessToken](hg)
  }
}
