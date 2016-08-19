package com.bear.web.entity

import scala.beans.BeanProperty

/**
  * Created by Apple on 16/6/10.
  */
class StoreInfo {

  /** 门店标识 */
  @BeanProperty var storeId: Int = _

  /** 总店标识(总店为本身id) */
  @BeanProperty var hqStoreId: Int = _

  /** 门店类型(1：单店，2：连锁总店，3：连锁分店,4:管理员) */
  @BeanProperty var storeType: Int = _

  /** 门店名称 */
  @BeanProperty var storeName: String = _

  /** 门店联系人 */
  @BeanProperty var storeLinkname: String = _

  /** 门店联系电话 */
  @BeanProperty var storeLinkphone: String = _

  /** 门店所属城市 */
  @BeanProperty var storeCity: String = _

  /** 门店所属省份 */
  @BeanProperty var storeProvince: String = _

  /** 店铺地址 */
  @BeanProperty var storeAddress: String = _

  /** 店铺电话 */
  @BeanProperty var storeTel: String = _

  /** 店铺LOGO */
  @BeanProperty var storeLogo: String = _

  /** 店铺轮播图片 */
  @BeanProperty var carouselPicture: String = _

  /** 门店介绍 */
  @BeanProperty var storeDesc: String = _

  /** 技术展示 */
  @BeanProperty var technical: String = _

  /** 特色服务 */
  @BeanProperty var characteristic: String = _

  /** 名师介绍 */
  @BeanProperty var teacherIntroduction: String = _

  /** 门店状态(1:申请中，2:试运营，3:正常营业，4:已过期，5:已删除) */
  @BeanProperty var storeStatus: Int = _

  /** 创建时间 */
  @BeanProperty var createTime: String = _

  /** 修改时间 */
  @BeanProperty var updateTime: String = _

  /**
    * 推荐人名字(店铺查询推荐人业务员创建) by DavidLiang 2016-01-21 17:55:00
    */
  @BeanProperty var recommendName: String = _

  /** 纬度 */
  @BeanProperty var latitude: String = _

  /** 经度 */
  @BeanProperty var longitude: String = _

}
