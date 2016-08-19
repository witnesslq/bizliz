package com.bear.web.mapper

import com.bear.web.entity.WxClaimedVouchers
import org.springframework.stereotype.Repository

/**
  * Created by tanghong on 16/6/26.
  */
@Repository
trait WxClaimedVouchersMapper {

  def dynamicInsert(v: WxClaimedVouchers): Int


  def selectByOpenId(openid: String): java.util.List[WxClaimedVouchers]

  def batchUpdateState(list: java.util.List[Int]): Int
}
