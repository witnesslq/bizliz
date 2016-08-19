package com.bear.web.mapper

import com.bear.web.entity.WxKeywordReply

/**
  * Created by tanghong on 01/08/2016.
  */
trait WxKeywordReplyMapper {

  def insertDynamic(was: WxKeywordReply): Int

  def updateDynamic(was: WxKeywordReply): Int

  def deleteById(map: java.util.Map[String, Int]): Int

  def selectById(authId: Int): WxKeywordReply

  def selectByStoreId(storeId: Int): WxKeywordReply

  def selectByAppId(appId: String): WxKeywordReply

  def selectByKeyword(map: java.util.Map[String, Int]): WxKeywordReply

}
