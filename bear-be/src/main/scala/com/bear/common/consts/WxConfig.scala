package com.bear.common.consts

/**
 * Created by Apple on 15/10/20.
 */

case class WeiXinConfig(appId:String, secret: String, token: String, aseKey: String, baseUrl: String)

trait WXConfig {
  val platFormConfig: WeiXinConfig

  def ComAppId = platFormConfig.appId
  def secret = platFormConfig.secret
  def token = platFormConfig.token
  def asekey = platFormConfig.aseKey
  def baseUrl = platFormConfig.baseUrl

}
