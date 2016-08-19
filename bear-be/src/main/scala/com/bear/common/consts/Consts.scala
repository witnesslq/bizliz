package com.bear.common.consts

/**
  * Created by tanghong on 16/6/14.
  */
object Consts {

  object PromotionTypeCons extends  Enumeration{
    val nonPromotion = Value(1, "非活动类型")
    val bargainId = Value(2, "砍价类型")
  }

  object ProductTypeCons extends Enumeration{
    val  project = Value(1, "项目")
    val  goods = Value(2, "商品")
    val combo = Value(3, "疗程")
    //val projectSeries = Value(4, "项目系列")
    //val goodsSeriesId =  Value(5, "商品系列")
  }

  object VoucherTypeCons extends Enumeration{
    val common = Value(0, "通用")
    val project = Value(1, "项目")
    val goods = Value(2, "商品")
    val combo = Value(3, "疗程")
    val projectSeries = Value(4, "项目系列")
    val goodsSeriesId =  Value(5, "商品系列")
  }



  object StatusCons{
   final val enable: Int = 0
   final val stop: Int = 1
   final val remove: Int = 2
  }

  object BargainStatusCons{
  }

  object TeletextActionCons{
    final val download: Int = 1
    final val praise: Int = 3
  }

  object TeletextCategoryCons{
    final val zefun = 1
    final val promotion = 2
  }

  object PaymentTypeCons{
    final val card: Int = 1
    final val weixin: Int = 2
    final val cardOrWeixin = 3
  }

  object PromotionJoinRoleCons{
    final val sponsor = 1
    final val assists = 2
  }

  object PromotionDescriptionCons{
    final val shareDes = ""
    final val participatorDes =
      """{"description": ["一言不合就砍价！${nickname}帮你砍了${amount}元。",
                                    |" ${nickname}花费了九牛二虎之力帮你砍掉${amount}元!",
                                    |" ${nickname}砍价砍得好，颜值没烦恼！xx帮你砍了${amount}元。",
                                    |" ${nickname}用屠龙宝刀帮你一下砍去了${amount}元！",
                                    |" ${nickname}帮你砍了${amount}元。友谊小船秒变巨轮！",
                                    |"只要套路深，天价砍成针！${nickname}帮你砍了${amount}元。",
                                    |"爱美之心感动了上苍，${nickname}帮你砍掉了${amount}元。",
                                    |"${nickname}帮你砍了${amount}元。这一刀砍得老板没了脾气。",
                                    |"${nickname}不想和你说话并向你扔了把刀，帮你砍掉${amount}元。",
                                    |"${nickname}动了动小指头就帮你砍掉了${amount}元！"
                                    | ]}""".stripMargin
  }

  object BargainEventStatusCons{
    val noStart = 0
    val start = 1
    val complete = 2
    val invalid = 3
    val stop = 4
    val remove = 5
  }

}
