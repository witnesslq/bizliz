package com.bear.web.mapper

import com.bear.web.entity.WxTextReply

/**
  * Created by tanghong on 01/08/2016.
  */
trait WxTextReplyMapper {
  def insertDynamic(was: WxTextReply): Int

  def updateDynamic(was: WxTextReply): Int

  def deleteById(map: java.util.Map[String, Int]): Int

  def selectById(authId: Int): WxTextReply

  def selectByStoreId(storeId: Int): WxTextReply

  def selectByAppId(appId: String): WxTextReply
}
