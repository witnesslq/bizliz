package com.bear.worm.storage

import com.bear.worm.model.Investor
import us.codecraft.webmagic.{ResultItems, Task}
import us.codecraft.webmagic.pipeline.{PageModelPipeline, Pipeline}
import scala.collection.JavaConverters._
/**
  * Created by Apple on 20/01/2017.
  */
class InvestPipeline extends Pipeline{

  override def process(resultItems: ResultItems, task: Task): Unit = {
    val list = resultItems.get("investorList").asInstanceOf[java.util.Collection[Investor]]
    Investor.add(collectionAsScalaIterable(list).toSeq)
  }
}
