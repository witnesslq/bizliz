package com.bear.web.mapper

import com.bear.web.entity.WxUserInfo
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.{Repository, Service}

import scala.beans.BeanProperty

/**
  * Created by tanghong on 16/6/24.
  */
@Repository
trait WxUserInfoMapper {

  def dynamicInsert(user: WxUserInfo): Int

  def selectByOpenId(openid: String): WxUserInfo

}
@Service
object WxUserInfoMapper{
  import com.bear.common.utils.JsonUtils._
  import scala.collection.JavaConversions._

  @Autowired
  @BeanProperty var wechatUserMapper: WxUserInfoMapper = _

  def provideWxUserInfo(openid: String) = {
    Option(wechatUserMapper.selectByOpenId(openid)).map{ u =>
      (u, bean2Map(u))
    }
  }
}