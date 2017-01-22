package com.bear.worm

import com.bear.worm.service.InvestorPageProcessor
import com.bear.worm.storage.InvestPipeline
import us.codecraft.webmagic.Spider
import us.codecraft.webmagic.downloader.selenium.SeleniumDownloader
import us.codecraft.webmagic.pipeline.FilePipeline
import scala.collection.JavaConverters._
/**
  * Created by tanghong on 19/01/2017.
  */
object WormMain {

  def main(args: Array[String]) {
    val sd = new SeleniumDownloader("/Users/Apple/tool/driver/chromedriver")
    sd.setSleepTime(5000)
    Spider.create(new InvestorPageProcessor)
    .thread(5).addUrl("https://rong.36kr.com/n/api/search/user?p=0")
    //.setDownloader(sd)
    .setPipelines(java.util.Arrays.asList(new InvestPipeline()))
    .run()
  }

}
