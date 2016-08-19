package com.bear.web.entity

import scala.beans.BeanProperty

/**
  * Created by Apple on 16/6/8.
  */
class WechatGroupInfo {
  /** 分组标识 */
  @BeanProperty var id: Int = _

  /** 门店标识 */
  @BeanProperty var storeId: Int = _

  /** 分组类型(1:会员，2:员工，3:老板，4:无身份) */
  @BeanProperty var groupType: Int = _

  /** 微信分组ID */
  @BeanProperty var groupId: Int = _

  /** 微信分组名称 */
  @BeanProperty var groupName: String = _
}
