package com.bear.web.dto

import scala.beans.BeanProperty

/**
  * Created by tanghong on 16/6/24.
  */
class ProductBargainStatusDto {
  @BeanProperty var bargainEventId: Int = _
  @BeanProperty var productId: Int = _
  @BeanProperty var surplusTimes: Int = _
  @BeanProperty var surplusAmount: Double = _
  @BeanProperty var productAmount: Double = _
  @BeanProperty var award: Int = _
  @BeanProperty var couponId: Int = _
  @BeanProperty var yetAmount: Int = _
  @BeanProperty var sponsor: String = _
  @BeanProperty var nickName: String = _
  @BeanProperty var headImgUrl: String = _
  @BeanProperty var createon: String = _
  @BeanProperty var imgUrl: String = _
}

class JoinBargainUserStat {
  @BeanProperty var productId: Int = _
  @BeanProperty var num: Int = _
}

class UserBargainDetail {
  @BeanProperty var bargainEventId: Int = _
  @BeanProperty var productId: Int = _
  @BeanProperty var surplusTimes: Int = _
  @BeanProperty var surplusAmount: Double = _
  @BeanProperty var couponId: Int = _
  @BeanProperty var yetAmount: Double = _
  @BeanProperty var category: Int = _
  @BeanProperty var bargainAmount: Double = _
  @BeanProperty var createon: String = _
  @BeanProperty var productAmount: Double = _
  @BeanProperty var award: Int = _
  @BeanProperty var bargainPromotionId: Int = _
  @BeanProperty var productName: String = _
  @BeanProperty var bargainProductSettingId: Int = _
  @BeanProperty var sponsor: String = _
  @BeanProperty var nickName: String = _
  @BeanProperty var headImgUrl: String = _
  @BeanProperty var productTypeId: Int = _
  @BeanProperty var status: Int = _
  @BeanProperty var imgUrl: String = _
}