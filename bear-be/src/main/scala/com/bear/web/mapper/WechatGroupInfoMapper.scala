package com.bear.web
package mapper

import com.bear.web.entity.WechatGroupInfo
import org.springframework.stereotype.Repository

/**
  * Created by Apple on 16/6/8.
  */
@Repository
trait WechatGroupInfoMapper {

  /**
    * 查询某个类型下的分组信息
    *
    * @param groupType 分组类型(1:会员，2:员工，3:老板，4:无身份)
    * @return 分组信息
    */
  def selectAllByGroupType(groupType: Int): java.util.List[WechatGroupInfo]

  /**
    * 根据门店标识、分组类型查询微信分组ID
    *
    * @param map 门店标识、分组类型
    * @return 微信分组ID
    */
  def selectGroupIdByStoreIdAndGroupType(map: Map[String, Integer]): Int

  /**
    * 根据分组标识删除分组信息
    *
    * @param id 分组标识
    * @return 0:失败，1:成功
    */
  def deleteByPrimaryKey(id: Int): Int

  /**
    * 新增分组信息
    *
    * @param record 分组信息
    * @return 0:失败，1:成功
    */
  def insert(record: WechatGroupInfo): Int

  /**
    * 根据分组标识查询分组信息
    *
    * @param id 分组标识
    * @return 分组信息
    */
  def selectByPrimaryKey(id: Int): WechatGroupInfo

  /**
    * 修改分组信息
    *
    * @param record 分组信息
    * @return 0:失败，1:成功
    */
  def updateByPrimaryKey(record: WechatGroupInfo): Int

}
