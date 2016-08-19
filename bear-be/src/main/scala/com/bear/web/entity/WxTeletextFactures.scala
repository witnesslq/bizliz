package com.bear.web.entity

import com.bear.common.consts.Consts.StatusCons
import com.bear.common.utils.JodaUtils
import org.joda.time.DateTime

import scala.beans.BeanProperty

/**
  * 图文消息制作中心
  */
class WxTeletextFactures {
  @BeanProperty var id: Int = _
  @BeanProperty var teletextId: Int = _
  @BeanProperty var promotionTypeId: Int = _
  @BeanProperty var storeId: Int = _
  @BeanProperty var createon: String = _
  @BeanProperty var updateon: String = _
  @BeanProperty var state: Int = _

  def this(teletextId: Int, promotionTypeId: Int, storeId: Int){
    this
    this.teletextId = teletextId
    this.promotionTypeId = promotionTypeId
    this.storeId = storeId
    this.createon = JodaUtils.getDateTime
    this.updateon = JodaUtils.getDateTime
    this.state = StatusCons.enable
  }
}
