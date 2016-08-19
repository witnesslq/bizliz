package com.bear.web.dto

import scala.beans.BeanProperty

/**
  * Created by tanghong on 16/6/27.
  */
class GoodsAppointDto {
  @BeanProperty var goodsId: Int = _
  @BeanProperty var goodsName: String = _
  @BeanProperty var goodsPrice: Double = _
  @BeanProperty var costPrice: Double = _
  @BeanProperty var goodsImage: String = _
  @BeanProperty var goodsDesc: String = _
  @BeanProperty var affiliatedImage: String = _

}
