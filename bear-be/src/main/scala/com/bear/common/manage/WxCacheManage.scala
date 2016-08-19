package com.bear.common
package manage

import javax.servlet.http.HttpServletRequest

import api.TokenApi
import api.PlatformApi._
import com.bear.common.api.AuthApi._
import com.bear.common.utils.Helpers
import com.bear.web.entity.WxUserInfo
import consts.WxConsts._
import consts.WxApp._
import utils.Magic._
import com.bear.web.mapper.{StoreInfoMapper, StoreWechatMapper, WechatEmployeeMapper, WxUserInfoMapper}
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.{Component, Service}

import scala.beans.BeanProperty
import scala.util.Success

/**
  * Created by Apple on 16/6/9.
  */
@Component
class WxCacheManage {
  @Autowired
  @BeanProperty var storeWcMapper: StoreWechatMapper = _

  @Autowired
  @BeanProperty var storeInfoMapper: StoreInfoMapper = _

  @Autowired
  @BeanProperty var redisService: RedisService = _

  @Autowired
  @BeanProperty var wechatEmployee: WechatEmployeeMapper = _

  @Autowired
  @BeanProperty var wechatUserMapper: WxUserInfoMapper = _

  val logger = LoggerFactory.getLogger(classOf[WxCacheManage])


  object CacheKey{
    final val PLATFORM_TICKET = "_platform_ticket"
    final val COMPONENT_ACCESS_TOKEN = "_component_access_token"
    final val AUTHORIZER_ACCESS_TOKEN = "_authorizer_access_token"
  }

  /**
    * 公众号token凭证管理, key最好拼成hash值
 *
    * @param storeId
    * @return
    */
  def getWxAccessToken(storeId: Int): Option[String] = {
    val key = Redis.STORE_WECHAT_ACCESS_TOKEN_KEY_HASH
    Option(redisService.hget(key, storeId))
    /*try {
      Option(redisService.get(key)).orElse{
        val (appId, secret) = {
          val store = storeWcMapper.selectByPrimaryKey(storeId)
          (store.wechatAppid, store.wechatAppsecret)
        }
        TokenApi.getCommonAccessToken(appId, secret) match {
          case Success(v) =>
            redisService.hsetnx(key, v.access_token, 7200)
            Some(v.access_token)
          case _ => None
        }
      }
    } catch {
      case e: Exception =>
      logger.error(s"weixin token exception: ${e.getMessage}")
      None
    }*/
  }

  def getWxJssdkTicket(storeId: Int): Option[String] = {
    val key = "store_wechat_jsapi_ticket_key_hash"
    Option(storeInfoMapper.selectMainIdByStoreId(storeId)).flatMap{ id =>
      Option(redisService.hget(key, id))
    }
  }

  /**
    * 获取员工对应的openid
 *
    * @param employeeId
    * @return
    */
  def getEmployeeOpenId(employeeId: Int): Option[String] = {
    val key = Redis.WECHAT_EMPLOYEEID_TO_OPENID_KEY_HASH
    Option(redisService.hget(key, employeeId)).orElse{
      Option(wechatEmployee.selectByEmployeeId(employeeId).openId)
        .map{ openid =>
          redisService.hset(key, employeeId, openid)
          openid
      }
    }
  }

  def setWxOpenId(openid: String, req: HttpServletRequest): String = {
    val session = req.getSession(false)
    if (session != null) {
      logger.info("session not null")
      session.removeAttribute("openid")
      session.setAttribute("openid", openid)
      session.getAttribute("openid").asInstanceOf[String]
    }
    else {
      val ss = req.getSession(true)
      logger.info("create new session")
      ss.removeAttribute("openid")
      ss.setAttribute("openid", openid)
      ss.getAttribute("openid").asInstanceOf[String]
    }
  }

  def getWxOpenId(request: HttpServletRequest): Option[String] = {
    Option(request.getSession(false)).flatMap{ m =>
      val openid = m.getAttribute("openid").asInstanceOf[String]
      logger.info(s"当前openid: $openid")
      Option(openid)
    }/*.orElse{
        val openid = setWxOpenId(Helpers.genString(6), request)
        if (Option(wechatUserMapper.selectByOpenId(openid)).isEmpty){
          val wu = new WxUserInfo(
            1005,
            openid,
            "小胖",
            "1",
            "深圳",
            "广东",
            "广东",
            "https://ss2.baidu.com/6ONYsjip0QIZ8tyhnq/it/u=3706555796,1550133346&fm=58"
          )
          wechatUserMapper.dynamicInsert(wu)
        }
        Some(openid)
    }*/
  }

  def setStoreId(storeId: Int, req: HttpServletRequest): Int = {
    val session = req.getSession(false)
    //session.setMaxInactiveInterval(3600 * 12)
    if (session != null) {
      logger.info("store session not null")
      session.removeAttribute("WxStoreId")
      session.setAttribute("WxStoreId", storeId)
      session.getAttribute("WxStoreId").asInstanceOf[Int]
    }
    else {
      val ss = req.getSession(true)
      logger.info("store create new session")
      ss.removeAttribute("WxStoreId")
      session.setAttribute("WxStoreId", storeId)
      ss.getAttribute("WxStoreId").asInstanceOf[Int]
    }
  }

  def getStoreId(req: HttpServletRequest): Option[Int] = {
    Option(req.getSession.getAttribute("WxStoreId")).map(_.asInstanceOf[Int])
  }

  def setPlatformTicket(comAppId: String, ticket: String): Unit = {
    val key = comAppId + CacheKey.PLATFORM_TICKET
    redisService.del(key)
    redisService.set(key, ticket)
  }

  def getPlatformTicket(comAppId: String): Option[String] = {
    val key = comAppId + CacheKey.PLATFORM_TICKET
    Option(redisService.get(key))
  }

  def getCacheComponentAccessToken(comAppId: String, ticket: String = ""): Option[String] = {
    logger.info(s"获取授权票据: $ticket")
    val key = s"${comAppId}${CacheKey.COMPONENT_ACCESS_TOKEN}"
    Option(redisService.get(key)).orElse{
      TokenApi.getPlatFormAccessToken(zefunService.appId, zefunService.secret, ticket.trim)
        .map{ acc =>
          logger.info(s"第三平台token是: ${acc.component_access_token}")
          redisService.del(key)
          redisService.setex(key, acc.component_access_token, 7200)
          acc.component_access_token
      }.toOption
    }
  }

  def refreshCacheAuthorizerAccessToken(id: Long, componentAccessToken: String, appId: String, refrestoken: String)
  : Option[String] = {
    val key = s"${id}${CacheKey.AUTHORIZER_ACCESS_TOKEN}"
    val authToken = Option(redisService.get(key))
    def getRefreshToken(token: String): Option[String] = {
      if (token.nonEmpty) Some(token) else {
        refreshAuthorizerAccessToken(componentAccessToken, zefunService.appId, appId, refrestoken).map{ m =>
          logger.info(s"获取公众号授权令牌:${m.authorizer_access_token}")
          redisService.del(key)
          redisService.setex(key, m.authorizer_access_token, 7200)
          m.authorizer_access_token
        }.toOption
      }
    }
    val result = for {
      token <- authToken
      reshToken <- getRefreshToken(token)
    } yield reshToken
    result
  }


}
