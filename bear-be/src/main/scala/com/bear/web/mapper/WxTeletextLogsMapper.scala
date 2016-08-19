package com.bear.web.mapper

import com.bear.web.dto.WxTeletextActionDesDto
import com.bear.web.entity.WxTeletextLogs
import org.springframework.stereotype.Repository

/**
  * 图文消息操作日志
  */
@Repository
trait WxTeletextLogsMapper {

  def insert(teletext: WxTeletextLogs): Int

  def updateState(map: java.util.Map[String, Int]): Int

  def selectByStoreId(storeId: Int): java.util.List[WxTeletextActionDesDto]

  def isExistsDownloads(map: java.util.Map[String, Int]): Int

  def selectByTeletextId(teletextId: Int): java.util.List[WxTeletextActionDesDto]
}
