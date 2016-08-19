package com.bear.web.entity

import scala.beans.BeanProperty
/**
  * Created by tanghong on 01/08/2016.
  */
class WxCustomMenus {
  @BeanProperty var id: Int = _ 
  @BeanProperty var authId: Int = _
  @BeanProperty var storeId: Int = _
  @BeanProperty var fId: Int = _
  @BeanProperty var menuName: String = _
  @BeanProperty var menuEvent: Int = _
  @BeanProperty var content: String = _
  @BeanProperty var createon: String = _
  @BeanProperty var state: Int = _
}
