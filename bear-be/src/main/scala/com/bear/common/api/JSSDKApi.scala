package com.bear
package common
package api

import common.utils.Helpers._
import com.bear.common.utils.http.LocalHttpClient
import common.consts.WxConsts.WxUrlCons._
import common.api.dto._
import org.apache.http.client.methods.HttpGet

import scala.util.Try
/**
  * Created by tanghong on 16/6/30.
  */
object JSSDKApi {

  def getSDKTicket(accessToken: String): Try[JSSDKTicket] = {
    val location = BASE_API_URI + s"/cgi-bin/ticket/getticket?access_token=$accessToken&type=jsapi"
    val hg = new HttpGet(location)
    LocalHttpClient.handleJson[JSSDKTicket](hg)
  }

  /**
    * 生成JSSDK签名
 *
    * @param noncestr
    * @param jsapi_ticket
    * @param timestamp
    * @param url
    * @return
    */
  def spawnSignature(noncestr: String, jsapi_ticket: String, timestamp: Long, url: String): Option[String] = {
    if (jsapi_ticket.nonEmpty) {
      /*val str = sha1Hex(Array(s"jsapi_ticket=$jsapi_ticket", s"noncestr=$noncestr", s"timestamp=$timestamp", s"url=$url")
        .sorted.mkString("&"))*/
      Some(sha1Hex(s"jsapi_ticket=$jsapi_ticket&noncestr=$noncestr&timestamp=$timestamp&url=$url"))
    }
    else None
  }

}
