package com.bear.web.entity

import com.bear.common.consts.Consts.StatusCons
import com.bear.common.utils.JodaUtils
import org.joda.time.DateTime

import scala.beans.BeanProperty

/**
  * Created by tanghong on 16/6/24.
  */
class WxClaimedVouchers {
  @BeanProperty var id: Int = _
  @BeanProperty var storeId: Int = _
  @BeanProperty var promotionTypeId: Int = _
  @BeanProperty var productId: Int = _
  @BeanProperty var productName: String = _
  @BeanProperty var openid: String = _
  @BeanProperty var nickName: String = _
  @BeanProperty var headImgUrl: String = _
  @BeanProperty var couponId: Int = _
  @BeanProperty var award: Int = _
  @BeanProperty var status: Int = _ //领取状态
  @BeanProperty var createon: String = _
  @BeanProperty var updateon: String = _
  @BeanProperty var state: Int = _ // 启用, 停用, 删除
  @BeanProperty var bargainEventId: Int = _

  def this(storeId: Int, promotionTypeId: Int,
           productId: Int, productName: String,
           openid: String, nickName: String,
           headImgUrl: String, bargainEventId: Int){
    this
    this.storeId = storeId
    this.promotionTypeId = promotionTypeId
    this.productId = productId
    this.productName = productName
    this.openid = openid
    this.nickName = nickName
    this.headImgUrl = headImgUrl
    this.status = 0
    this.createon = JodaUtils.getDateTime
    this.updateon = JodaUtils.getDateTime
    this.state = StatusCons.enable
    this.bargainEventId = bargainEventId
  }

  def this(storeId: Int, promotionTypeId: Int,
           productId: Int, productName: String,
           openid: String, nickName: String, headImgUrl: String,
           couponId: Int, award: Int, bargainEventId: Int){
    this
    this.storeId = storeId
    this.promotionTypeId = promotionTypeId
    this.productId = productId
    this.productName = productName
    this.openid = openid
    this.nickName = nickName
    this.headImgUrl = headImgUrl
    this.couponId = couponId
    this.award = award
    this.status = 0
    this.createon = JodaUtils.getDateTime
    this.updateon = JodaUtils.getDateTime
    this.state = StatusCons.enable
    this.bargainEventId = bargainEventId
  }
}
