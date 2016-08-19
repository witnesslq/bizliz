package com.bear.web
package mapper

import com.bear.web.entity.WxTeletext
import org.springframework.stereotype.Repository
import org.apache.ibatis.annotations.Param

/**
  * Created by Apple on 16/6/8.
  */
@Repository
trait WxTeletextMapper {
  /**
    * 删除
    *
    * @param id 主键
    * @return 状态
    */
  def deleteByPrimaryKey(id: Int): Int


  /**
    * 新增
    *
    * @param record 实体
    * @return 状态
    */
  def insertSelective(record: WxTeletext): Int

  /**
    * 查询
    *
    * @param id 主键
    * @return 实体
    */
  def selectByPrimaryKey(id: Int): WxTeletext

  /**
    * 更新
    *
    * @param record 实体
    * @return 状态
    */
  def updateByPrimaryKeySelective(record: WxTeletext): Int

  /**
    * 批量更新
 *
    * @param records
    * @return
    */
  def batchUpdate(records: java.util.List[WxTeletext]): Int
  /**
    * 批量新增
    *
    * @param automaticReplies 集合
    * @return 状态
    */
  def insertByList(automaticReplies: java.util.List[WxTeletext]): Int


  /**
    * 查询回复消息
    *
    * @param storeId 门店ID
    * @return 回复列表
    */
  def selectByStoreId(storeId: Int): java.util.List[WxTeletext]


  def selectByPromotion(@Param("storeId") storeId: Int, @Param("promotionTypeId") promotionTypeId: Int): java.util.List[WxTeletext]

  def selectByList(list: java.util.List[Int]): java.util.List[WxTeletext]


  def removeById(id: Int): Int


  def isExistsDownloads(map: java.util.Map[String, Int]): Int
}
