package com.bear.web.entity

import scala.beans.BeanProperty
/**
  * Created by tanghong on 01/08/2016.
  */
class WxAppInfo {
  @BeanProperty var id: Int = _
  @BeanProperty var authId: Int = _
  @BeanProperty var storeId: Int = _
  @BeanProperty var appId: String = _
  @BeanProperty var nickName: String = _
  @BeanProperty var headImg: String = _
  @BeanProperty var serviceType: Int = _
  @BeanProperty var verifyType: Int = _
  @BeanProperty var userName: String = _ // 原始id
  @BeanProperty var alias: String = _ //授权方公众号所设置的微信号,可能为null
  @BeanProperty var qrCodeUrl: String = _
  @BeanProperty var businessInfo: String = _
  @BeanProperty var funcInfo: String = _
  @BeanProperty var createon: String = _
  @BeanProperty var state: Int = _
}
