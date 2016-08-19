package com.bear.web.dto

import scala.beans.BeanProperty

/**
  * Created by tanghong on 16/6/24.
  */
class ProductBargainDetailDto {
  @BeanProperty var id: Int = _
  @BeanProperty var storeId: Int = _
  @BeanProperty var teletextId: Int = _ // 一个图文对应多个项目
  @BeanProperty var productTypeId: Int = _
  @BeanProperty var productId: Int = _
  @BeanProperty var productName: String = _
  @BeanProperty var productAmount: Double = _
  @BeanProperty var bargainPromotionId: Int = _
  /**
    * 项目图片
    */
  @BeanProperty var imgUrl: String = _
  @BeanProperty var award: Int = _
  @BeanProperty var state: Int = _

}
