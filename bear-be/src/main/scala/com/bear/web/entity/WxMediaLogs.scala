package com.bear.web.entity

import com.bear.common.consts.Consts.StatusCons
import com.bear.common.utils.JodaUtils
import org.joda.time.DateTime

import scala.beans.BeanProperty

/**
  * 图文消息发送统计
  */
class WxMediaLogs {
  /** 统计ID */
  @BeanProperty var id: Int = _
  /** 图文消息ID */
  @BeanProperty var mediaId: String = _

  @BeanProperty var storeId: Int = _

  /** 图文发送微信返回ID */
  @BeanProperty var msgId: Int = _
  /** 图文发送状态 */
  @BeanProperty var msgStatus: String = _
  /** 发送成功人数 */
  @BeanProperty var sentCount: Int = _
  /** 发送失败人数 */
  @BeanProperty var errorCount: Int = _
  /** 图文被阅读次数 */
  @BeanProperty var hasRead: Int = _
  @BeanProperty var createon: String = _
  @BeanProperty var updateon: String = _
  @BeanProperty var state: Int = _
  @BeanProperty var teletextId: Int = _

  def this(mediaId: String, storeId: Int,
           msgId: Int, msgStatus: String, sendCount: Int,
           errorCount: Int, teletextId: Int){
    this
    this.storeId = storeId
    this.msgId = msgId
    this.msgStatus = msgStatus
    this.sentCount = sendCount
    this.errorCount = errorCount
    this.createon = JodaUtils.getDateTime
    this.updateon = JodaUtils.getDateTime
    this.state = StatusCons.enable
    this.teletextId = teletextId
  }

  def this(msgId: Int, mediaId: String, storeId: Int, teletextId: Int){
    this
    this.msgId = msgId
    this.mediaId = mediaId
    this.storeId = storeId
    this.createon = JodaUtils.getDateTime
    this.updateon = JodaUtils.getDateTime
    this.state = StatusCons.enable
    this.teletextId = teletextId
  }
}
