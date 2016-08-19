package com.bear
package common
package api

import utils._
import consts.WxConsts.WxUrlCons._
import consts.WxConsts.MaterialCons._
import utils.JsonUtils._
import java.io.{File, InputStream}

import com.bear.common.api.dto._
import com.bear.common.utils.http.LocalHttpClient
import org.apache.http.client.methods.{HttpGet, HttpPost}
import org.apache.http.entity.{ContentType, StringEntity}
import org.json4s.JValue

import scala.util.{Failure, Try}

/**
  * Created by Apple on 16/6/6.
  */
object MaterialApi {

  def addNewsContentImgUrl(accessToken: String, imgUrl: String): Try[NewsContentUrl] = {
    val (inStream, fileName) = IOUtils.getInputStream(imgUrl)
    val location = s"${BASE_API_URI}/cgi-bin/media/uploadimg?access_token=${accessToken}"
    LocalHttpClient.handleWxUpload[NewsContentUrl](location, inStream, fileName)
  }

  // 添加图片素材
  def addTemporaryMaterial(accessToken: String, inputStream: Array[Byte], fileName: String, category: String = IMAGE): Try[MediaInfo] = {
    val location = s"${BASE_API_URI}/cgi-bin/media/upload?access_token=$accessToken&type=$category"
    LocalHttpClient.handleWxUpload[MediaInfo](location, inputStream, fileName)
  }

  // 添加图文素材
  def addImageTextMaterial(accessToken: String, articles: List[WxTeletextDetail]): Try[MediaInfo] = {
    val location = s"${BASE_API_URI}/cgi-bin/media/uploadnews?access_token=${accessToken}"
    val hp = new HttpPost(location)
    val content = writeAny(Map("articles" -> articles))
    val entity = new StringEntity(content, ContentType.APPLICATION_JSON)
    hp.setEntity(entity)
    LocalHttpClient.handleJson[MediaInfo](hp)
  }

  /**
    * 更新图文素材, true为更新失败, false为更新成功
 *
    * @param accessToken
    * @param media_id
    * @param articles
    * @return
    */
  def updateTeletext(accessToken: String, media_id: String, articles: List[WxTeletextDetail]): Boolean = {
    val location = s"${BASE_API_URI}/cgi-bin/material/update_news?access_token=${accessToken}"
    var hp: HttpPost = null
    var index = 0
    articles.map{ art =>
      val res = writeAny(Map(
        "media_id" -> media_id,
        "index" -> index,
        "articles" -> art
      ))
      index = index + 1
      res
    }.flatMap{ json =>
      hp = new HttpPost(location)
      hp.setEntity(new StringEntity(json, ContentType.APPLICATION_JSON))
      LocalHttpClient.handleJson[WechatResult](hp).toOption
    }.map(_.errcode).exists(_ != 0)
  }


  // 获取图文素材
  def getImageTextMaterial(accessToken: String, media_id: String): Try[NewsItems] = {
    val location = s"${BASE_API_URI}/cgi-bin/material/get_material?access_token=${accessToken}"
    val hp = new HttpPost(location)
    val content = writeAny(Map("media_id" -> media_id))
    val entity = new StringEntity(content, ContentType.APPLICATION_JSON)
    hp.setEntity(entity)
    LocalHttpClient.handleJson[NewsItems](hp)
  }

  // 删除图文素材
  def removeImageTextMaterial(accessToken: String, media_id: String): Try[JValue] = {
    val location = s"${BASE_API_URI}/cgi-bin/material/del_material?access_token=${accessToken}"
    val hp = new HttpPost(location)
    val content = writeAny(Map("media_id" -> media_id))
    val entity = new StringEntity(content, ContentType.APPLICATION_JSON)
    hp.setEntity(entity)
    LocalHttpClient.handleJson[JValue](hp)
  }

  // 获取素材总数
  def getMaterialCount(accessToken: String): Try[MediaQuantity] = {
    val location = s"${BASE_API_URI}/cgi-bin/material/get_materialcount?access_token=${accessToken}"
    val hg = new HttpGet(location)
    LocalHttpClient.handleJson[MediaQuantity](hg)
  }

  // 批量获取图片素材
  def getBatchMaterial(accessToken: String, count: Int): Try[JValue] = {
    val location = s"${BASE_API_URI}/cgi-bin/material/batchget_material?access_token=${accessToken}"
    val content = writeAny(Map(
      "type" -> "news",
      "offset" -> "0",
      "count" -> count
     ))
    val hp = new HttpPost(location)
    val entity = new StringEntity(content, ContentType.APPLICATION_JSON)
    hp.setEntity(entity)
    LocalHttpClient.handleJson[JValue](hp)
  }

}
