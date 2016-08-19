package com.bear.web.mapper

import com.bear.web.entity.WxAuthStore

/**
  * Created by tanghong on 01/08/2016.
  */
trait WxAuthStoreMapper {

  def insertDynamic(was: WxAuthStore): Int

  def updateDynamic(was: WxAuthStore): Int

  def deleteById(map: java.util.Map[String, Int]): Int

  def selectById(authId: Int): WxAuthStore

  def selectByStoreId(storeId: Int): WxAuthStore

  def selectByAppId(appId: String): WxAuthStore

}
