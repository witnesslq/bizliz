package com.bear.web.controller.sca

import java.util

import com.bear.common.consts.Url
import com.bear.web.dto.ResultDto
import com.bear.common.utils.JsonUtils._
import com.bear.web.model.{Car, HabitTypes, Person}
import com.bear.worm.service.InvestorPageProcessor
import com.bear.worm.storage.InvestPipeline
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate
import org.springframework.data.elasticsearch.core.query.{IndexQuery, NativeSearchQueryBuilder}
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Repository
import org.springframework.web.bind.annotation.{CrossOrigin, RequestMapping, RequestMethod, RestController}
import org.springframework.web.context.request.async.DeferredResult
import us.codecraft.webmagic.Spider
import us.codecraft.webmagic.downloader.selenium.SeleniumDownloader

import scala.beans.BeanProperty
import scala.collection.JavaConversions._

/**
  * Created by Apple on 26/08/2016.
  */
@RestController
@CrossOrigin(origins = Array("*"), maxAge = 3600, allowedHeaders = Array("Content-Type"),
  allowCredentials = "false")
class HabitsController {

  /*@Autowired
  @BeanProperty var elasticsearchTemplate: ElasticsearchTemplate = _*/

  @Autowired
  @BeanProperty var kafkaTemplate: KafkaTemplate[Int, String] = _

  @RequestMapping(value = Array(Url.Habits.ADD_HABIT_TYPE_INFO), method = Array(RequestMethod.GET))
  def add = {
    kafkaTemplate.setDefaultTopic("testTopic")
    kafkaTemplate.sendDefault(2, "hello world")
   /* val sd = new SeleniumDownloader("/Users/Apple/tool/driver/chromedriver")
    sd.setSleepTime(5000)
    (580 to 618).foreach{ f =>
      Spider.create(new InvestorPageProcessor)
        .thread(1).addUrl(s"https://rong.36kr.com/n/api/search/user?p=$f")
        //.setDownloader(sd)
        .setPipelines(java.util.Arrays.asList(new InvestPipeline()))
        .run()
    }*/
    ""
  }

  @RequestMapping(value = Array(Url.Habits.SHOW_HABIT_INFO), method = Array(RequestMethod.GET))
  def show: ResultDto = {
    //val d = new DeferredResult[String]()
    HabitTypes.find
  }

  /*@RequestMapping(value = Array(Url.Habits.SHOW_INFO), method = Array(RequestMethod.GET))
  def find: String = {
    val foo = new Person();
    foo.setName("Foo");
    foo.setId("1");

    //val cars = new util.ArrayList[Car]();
    //val subaru = new Car();

    /*subaru.setName("Subaru");
    subaru.setModel("Imprezza");

    cars.add(subaru)
    foo.setCar(cars);*/

    val indexQuery = new IndexQuery();
    indexQuery.setId(foo.getId());
    indexQuery.setObject(foo);

    //creating mapping
    //elasticsearchTemplate.putMapping(classOf[Person]);
    //indexing document
    elasticsearchTemplate.index(indexQuery);
    //refresh
    //elasticsearchTemplate.refresh(classOf[Person], true);

    val persons = elasticsearchTemplate.queryForList(new NativeSearchQueryBuilder().build(), classOf[Person]);
    persons.foreach{ p =>
      println(p.getName + "----" + p.getId)
    }
    ""

  }*/



}
