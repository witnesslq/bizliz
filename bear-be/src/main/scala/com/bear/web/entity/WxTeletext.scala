package com.bear.web.entity

import com.bear.common.consts.Consts.StatusCons
import com.bear.common.utils.JodaUtils
import org.joda.time.LocalDate

import scala.beans.BeanProperty

/**
  * 图文消息
  * Created by Apple on 16/6/8.
  */
class WxTeletext {
  /** 消息ID */
  @BeanProperty var id: Int = _

  /**所属活动类型**/
  @BeanProperty var promotionTypeId: Int = _

  /** 门店ID */
  @BeanProperty var storeId: Int = _

  /** 图文消息的标题 */
  @BeanProperty var title: String = _

  /** 作者 */
  @BeanProperty var author: String = _

  /** 图文消息的描述 */
  @BeanProperty var digest: String = _

  /**封面图片*/
  @BeanProperty var imgUrl: String = _

  /** 图文消息textarea */
  @BeanProperty var content: String = _

  @BeanProperty var showCoverPic: Int = _

  /** 原文地址 */
  @BeanProperty var contentSourceUrl: String = _

  /** 复制的图文媒体ID */
  @BeanProperty var fId: Int = _

  /** 创建时间 */
  @BeanProperty var createon: String = _
  @BeanProperty var updateon: String = _
  @BeanProperty var state: Int = _

  def this(ptyId: Int, storeId: Int, title: String,
           author: String, digest: String, imgUrl: String, content: String,
           showCoverPic: Int, contentSourceUrl: String, fId: Int){
    this
    this.promotionTypeId = ptyId
    this.storeId = storeId
    this.title = title
    this.author = author
    this.digest = digest
    this.imgUrl = imgUrl
    this.content = content
    this.showCoverPic = showCoverPic
    this.contentSourceUrl = contentSourceUrl
    this.createon = JodaUtils.getDate
    this.updateon = JodaUtils.getDate
    this.state = StatusCons.enable
    this.fId = fId
  }


}
