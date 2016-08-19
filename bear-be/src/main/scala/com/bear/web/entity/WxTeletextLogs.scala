package com.bear.web.entity

import com.bear.common.consts.Consts._
import com.bear.common.utils.JodaUtils
import org.joda.time.DateTime

import scala.beans.BeanProperty
/**
  * 图文消息操作日志
  */
class WxTeletextLogs {
  @BeanProperty var id: Int = _
  @BeanProperty var promotionTypeId: Int = _
  @BeanProperty var teletextId: Int = _
  /**操作行为: 1为下载, 2为点赞*/
  @BeanProperty var category: Int = _
  @BeanProperty var storeId: Int = _
  @BeanProperty var createon: String = _
  @BeanProperty var updateon: String = _
  @BeanProperty var state: Int = _

  def this(teletextId: Int, storeId: Int, category: Int){
    this
    this.teletextId = teletextId
    this.category = category
    this.storeId = storeId
    this.createon = JodaUtils.getDateTime
    this.updateon = JodaUtils.getDateTime
    this.state = StatusCons.enable
  }
}
