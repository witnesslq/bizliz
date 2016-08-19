package com.bear.web
package mapper

import com.bear.web.entity.{WechatEmployee, WechatMember}
import org.springframework.stereotype.Repository

/**
  * Created by Apple on 16/6/8.
  */
@Repository
trait WechatMemberMapper {
  /**
    * 根据微信id删除关联记录
    *
    * @param openId 微信id
    * @return 0:失败，1:成功
    */
  def deleteByPrimaryKey(openId: String): Int

  /**
    * 插入微信id与会员关联记录
    *
    * @param record 关联记录
    * @return 0:失败，1:成功
    */
  def insert(record: WechatMember): Int

  /**
    * 根据微信id查询关联记录
    *
    * @param openId 微信id
    * @return 关联记录
    */
  def selectByPrimaryKey(openId: String): WechatEmployee

  /**
    * 根据微信id修改关联记录
    *
    * @param record 关联记录
    * @return 0:失败，1:成功
    */
  def updateByPrimaryKey(record: WechatMember): Int

  /**
    * 给一个会员id的list,查询出所有openid
    *
    * @param memberIds 会员id集合
    * @return openId集合
    */
  def selectOpenIdsByMemberIdList(memberIds: java.util.List[Int]): java.util.List[String]

  /**
    * 如果取消了关注,将is_subscribe值改为0
    *
    * @param member 关联实体
    */
  def updateByOpenId(member: WechatMember): Unit

  /**
    * 通过openId查询memberId
 *
    * @param fromUserName 微信openId
    * @return memberId
    */
  def selectMemberIdByOpenId(fromUserName: String): WechatMember

  /**
    * 根据会员标识集合查询对应的openid集合
    *
    * @param meberIdList 会员标识集合
    * @return openid集合
    */
  def selectOpenIdByMemberIdList(meberIdList: java.util.List[Int]): java.util.List[String]


  /**
    * 根据openid列表删除会员微信关联记录
    *
    * @param openIdList openid集合
    * @return 删除数量
    */
  def deleteByOpenIdList(openIdList: java.util.List[String]): Int

  /**
    * 根据会员标示查询微信openId
    *
    * @param memberId 会员id
    * @return openId
    */
  def selectOpenIdsByMemberId(memberId: Int): String

}
