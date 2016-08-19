package com.bear.web.entity

import scala.beans.BeanProperty
/**
  * Created by tanghong on 01/08/2016.
  */
class WxTemplateInfo {
  @BeanProperty var id: Int = _
  @BeanProperty var authId: Int = _
  @BeanProperty var storeId: Int = _
  @BeanProperty var tmCode: String = _
  @BeanProperty var tmNo: String = _
  @BeanProperty var createon: String = _
  @BeanProperty var updateon: String = _
  @BeanProperty var state: Int = _
}
