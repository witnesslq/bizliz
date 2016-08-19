package com.bear.web.dto

import scala.beans.BeanProperty

/**
  * Created by tanghong on 16/6/28.
  */
class JoinBargainUserInfoDto {
  @BeanProperty var productId: Int = _
  @BeanProperty var headImgUrl: String = _
  @BeanProperty var role: Int = _
  @BeanProperty var bargainAmount: Double = _
  @BeanProperty var openid: String = _
  @BeanProperty var nickName: String = _
  @BeanProperty var bewrite: String = _
  @BeanProperty var createon: String = _
}
