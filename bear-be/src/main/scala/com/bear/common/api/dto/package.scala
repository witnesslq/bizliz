package com.bear.common.api

/**
  * Created by Apple on 16/6/6.
  */
package object dto{
//微信开发平台推送票据
case class VerifyTicket(msg_signature:String, timeStamp: String, none: String, postData: String)
case class AccessToken(access_token: String, expires_in: Int)
case class ComAccessToken(component_access_token: String, expires_in: Int)

// 票据
case class PreAuthCode(pre_auth_code: String, expires_in: Int)
// 公众号授权信息
case class AuthorizationData(authorization_info: Authorization)
case class Authorization(authorizer_appid: String, authorizer_access_token: String,
                         expires_in: Int, authorizer_refresh_token: String, func_info: Seq[FunCcscopeategory])

case class FunCcscopeategory(funcscope_category: Category)
case class Category(id: Int)
// 刷新授权公众号令牌
case class RefreshAuthorizerAccessToken(authorizer_access_token: String, expires_in: Int, authorizer_refresh_token: String)

//授权公众号账户信息
case class AuthorizerAccountInfo(authorizer_info: AuthorizerInfo, authorization_info: AuthorizationInfo)
case class AuthorizerInfo(nick_name: String, head_img: String,
                          service_type_info: ServiceTypeInfo, verify_type_info: VerifyTypeInfo, user_name: String, qrcode_url: String, business_info: BusinessInfo, alias: String)
case class BusinessInfo(open_store: String, open_scan: String, open_pay: String, open_card: String, open_shake: String)
case class ServiceTypeInfo(id: Int)
case class VerifyTypeInfo(id: Int)
case class AuthorizationInfo(authorizer_appid: String, func_info: Seq[FunCcscopeategory])

//授权方选项设置信息
case class AuthorizerSettingInfo(authorizer_appid: String, option_name: String, option_value: String)
// 响应结果信息

case class WechatResult(errcode: Int, errmsg: String)

  //网页授权access_token
case class WebstieAccessToken(access_token: String, expires_in: Int, refresh_token: String, openid: String, scope: String)
//网页授权用户信息
case class WebsiteUserInfo(openid: String, nickname: String, sex: String, province: String, country: String, headimgurl: String, privilege: List[String], unionid: String)
// 长连接压缩成短链接
case class TarUrlInfo(errcode: Int, errmsg: String, short_url: String)


// js sdk
case class JSSDKTicket(errcode: Int, errmsg: String, ticket: String, expires_in: Int)
case class JSSDkDomain(domain: String)


// 素材管理
case class ThumbMediaInfo(thumb_media_id: String)
case class MediaInfo(media_id: String)
case class NewsContentUrl(url: String)

case class MediaQuantity(
  voice_count: Int,
  video_count: Int,
  image_count: Int,
  news_count: Int)

case class MediaImageText(
  title: String,
  thumb_media_id: String,
  show_cover_pic: String,
  author: String,
  digest: String,
  content: String,
  url: String,
  content_source_url: String)

case class NewsItems(news_item: List[MediaImageText])

case class WebAuthToken(access_token: String, expires_in: Int, refresh_token: String, openid: String, scope: String)

case class WebAuthUserInfo(
  openid: String,
  nickname: String,
  sex: String,
  province: String,
  city: String,
  country: String,
  headimgurl: String,
  privilege: List[String],
  unionid: Option[String]
)


// 素材写入
case class WxTeletextDetail(
  thumb_media_id: String, // 缩略图media_id
  author: String,
  title: String,
  content_source_url: String,
  content: String,
  digest: String = "",
  show_cover_pic: Int = 0
)

case class WxNewsSendResult(errcode: Int, errmsg: String, msg_id: Int, msg_data_id: Option[Int])

case class CreateTeletextDetailMaterial(url: String, articles: List[WxTeletextDetail])
case class UpdateTeletextDetailMaterial(media_id: String, url: String, articles: List[WxTeletextDetail])
}
