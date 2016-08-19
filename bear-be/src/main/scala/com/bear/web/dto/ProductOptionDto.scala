package com.bear.web.dto

import scala.beans.BeanProperty

/**
  * Created by tanghong on 16/6/22.
  */
class ProductOptionDto {
  @BeanProperty var id: Int = _
  @BeanProperty var category: Int = _
  @BeanProperty var storeId: Int = _
  @BeanProperty var name: String = _
  @BeanProperty var imgUrl: String = _
  @BeanProperty var description: String = _
}
