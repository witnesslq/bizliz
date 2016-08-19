package com.bear.web.entity

import com.bear.common.consts.Consts.StatusCons
import com.bear.common.utils.JodaUtils
import org.joda.time.DateTime

import scala.beans.BeanProperty

/**
  * 微信用户信息
  */
class WxUserInfo {
  @BeanProperty var id: Int = _
  @BeanProperty var storeId: Int = _
  @BeanProperty var openid: String = _
  @BeanProperty var nickName: String = _
  @BeanProperty var sex: String = _
  @BeanProperty var headImgUrl: String = _
  @BeanProperty var city: String = _
  @BeanProperty var province: String = _
  @BeanProperty var country: String = _
  @BeanProperty var createon: String = _
  @BeanProperty var updateon: String = _
  @BeanProperty var state: Int = _ // 启用, 停用, 删除
  @BeanProperty var isSubscribe: Int = _ // 0是已订阅, 1是未订阅

  def this(storeId: Int, openId: String, nickName: String,
           sex: String, city: String, province: String,
           country: String, headImgUrl: String, subscribe: Int = 1
          ){
    this
    this.storeId = storeId
    this.openid = openId
    this.nickName = nickName
    this.sex = sex
    this.city = city
    this.province = province
    this.country = country
    this.createon = JodaUtils.getDateTime
    this.updateon = JodaUtils.getDateTime
    this.state = StatusCons.enable
    this.headImgUrl = headImgUrl
    this.isSubscribe = subscribe
  }
}
