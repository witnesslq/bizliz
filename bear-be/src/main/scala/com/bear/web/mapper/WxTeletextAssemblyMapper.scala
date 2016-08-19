package com.bear.web.mapper

import com.bear.web.entity.WxTeletextAssembly
import org.springframework.stereotype.Repository

/**
  * 多图文消息装配
  */
@Repository
trait WxTeletextAssemblyMapper {

  def insert(tele: WxTeletextAssembly): Int

  def insertByList(teles: java.util.List[WxTeletextAssembly]): Int

}
