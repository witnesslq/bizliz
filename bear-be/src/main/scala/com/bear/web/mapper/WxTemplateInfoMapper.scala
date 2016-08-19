package com.bear.web.mapper

import com.bear.web.entity.WxTemplateInfo

/**
  * Created by tanghong on 01/08/2016.
  */
trait WxTemplateInfoMapper {

  def insertDynamic(was: WxTemplateInfo): Int

  def updateDynamic(was: WxTemplateInfo): Int

  def deleteById(map: java.util.Map[String, Int]): Int

  def selectById(authId: Int): WxTemplateInfo

  def selectByStoreId(storeId: Int): WxTemplateInfo

  def selectByAppId(appId: String): WxTemplateInfo

}
