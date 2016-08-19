package com.bear.web.dto

import scala.beans.BeanProperty

/**
  * 数据传输基础格式对象
  */
case class BaseDto(@BeanProperty var code: Int, @BeanProperty var msg: Object) {

  override def toString = {
    "BaseDto [code=" + code + ", msg=" + msg + "]"
  }

}