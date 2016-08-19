package com.bear.web.dto

import scala.beans.BeanProperty

/**
  * Created by tanghong on 16/6/15.
  */
class WxTeletextActionDesDto {
  @BeanProperty var promotionTypeId: Int = _
  @BeanProperty var teletextId: Int = _
  @BeanProperty var category: Int = _
  @BeanProperty var count: Int = _
}

