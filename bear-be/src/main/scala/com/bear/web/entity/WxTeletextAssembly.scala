package com.bear.web.entity

import com.bear.common.consts.Consts.StatusCons
import com.bear.common.utils.JodaUtils
import org.joda.time.DateTime

import scala.beans.BeanProperty

/**
  * 多图文消息装配
  */
class WxTeletextAssembly {
  @BeanProperty var id: Int = _
  @BeanProperty var mediaId: String = _
  @BeanProperty var teletextId: Int = _
  @BeanProperty var storeId: Int = _
  @BeanProperty var createon: String = _
  @BeanProperty var updateon: String = _
  @BeanProperty var state: Int = _

  def this(teletextId: Int, mediaId: String, storeId: Int){
    this
    this.teletextId = teletextId
    this.mediaId = mediaId
    this.storeId = storeId
    this.createon =  JodaUtils.getDateTime
    this.updateon = JodaUtils.getDateTime
    this.state = StatusCons.enable
  }
}
