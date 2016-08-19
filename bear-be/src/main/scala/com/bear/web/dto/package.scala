package com.bear.web

import com.bear.common.utils.JsonUtils._
import com.bear.common.exception.ResultCode._
/**
  * Created by tanghong on 16/6/16.
  */
package object dto {

  implicit def resultCode2Dto(rc: BaseCode): ResultDto = {
    ResultDto(rc.id, rc.msg)
  }

  trait JsonDto

  case class ResultDto(code: Int, msg: Any) extends JsonDto

  case class RequestDto[T: Manifest](json: String){
    def toObj: T = readerObj[T](json)
  }
}
