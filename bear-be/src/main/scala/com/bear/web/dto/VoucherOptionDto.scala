package com.bear.web.dto

import scala.beans.BeanProperty

/**
  * Created by tanghong on 16/6/23.
  */
class VoucherOptionDto {
  @BeanProperty var id: Int = _
  @BeanProperty var storeId: Int = _
  @BeanProperty var name: String = _
  @BeanProperty var price: Int = _
  @BeanProperty var couponTypeId: Int = _
  @BeanProperty var startTime: String = _
  @BeanProperty var releaseTime: String = _
}
