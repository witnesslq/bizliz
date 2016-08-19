package com.bear.web.mapper

import com.bear.web.entity.WxAppInfo

/**
  * Created by tanghong on 01/08/2016.
  */
trait WxAppInfoMapper {

  def insertDynamic(was: WxAppInfo): Int

  def updateDynamic(was:WxAppInfo): Int

  def deleteById(map: java.util.Map[String, Int]): Int

  def selectById(authId: Int): WxAppInfo

  def selectByStoreId(storeId: Int):WxAppInfo

  def selectByAppId(appId: String):WxAppInfo

}
