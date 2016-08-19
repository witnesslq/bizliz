package com.bear.web.mapper

import com.bear.web.entity.WxMediaLogs
import org.springframework.stereotype.Repository

/**
  * Created by Apple on 16/6/8.
  */
@Repository
trait WxMediaLogsMapper {
  /**
    * 根据主键删除
    *
    * @return 音响行数
    */
  def deleteByPrimaryKey(id: Int): Int


  /**
    * 根据主键删除
    *
    * @param record 类
    * @return 影响行数
    */
  def insertSelective(record: WxMediaLogs): Int

  def insertByList(list: java.util.List[WxMediaLogs]): Int

  /**
    * 根据主键删除
    *
    * @return 状态
    */
  def selectByPrimaryKey(id: Int): WxMediaLogs

  /**
    * 根据主键删除
    *
    * @param record 类
    * @return 影响行数
    */
  def updateByPrimaryKeySelective(record: WxMediaLogs): Int

  def selectByStore(storeId: Int): java.util.List[WxMediaLogs]


}
