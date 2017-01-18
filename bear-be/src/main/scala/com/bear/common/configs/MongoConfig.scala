package com.bear.common.configs

import com.mongodb.{Mongo, MongoClient, MongoClientOptions, ServerAddress}
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.{Bean, Configuration, PropertySource}
import org.springframework.data.authentication.UserCredentials
import org.springframework.data.mongodb.MongoDbFactory
import org.springframework.data.mongodb.config.AbstractMongoConfiguration
import org.springframework.data.mongodb.core.{MongoClientFactoryBean, MongoFactoryBean, MongoTemplate, SimpleMongoDbFactory}

import scala.beans.BeanProperty
import scalaz.std.effect.sql.connection

/**
  * Created by Apple on 18/01/2017.
  */
@Configuration
@PropertySource(Array("classpath:application.yml"))
class MongoConfig{

  @Value("${spring.data.mongo.host}")
  @BeanProperty var host: String =_

  @Value("${spring.data.mongo.port}")
  @BeanProperty var port: Int = _

  @Bean
  def mongo: MongoClient = {
    /*val build = MongoClientOptions.builder()
    .connectionsPerHost(8)
    .threadsAllowedToBlockForConnectionMultiplier(4)
    .connectTimeout(1000)
    .maxWaitTime(1500)
    .socketKeepAlive(true)
    .socketTimeout(1500)
    val mg = new MongoClientOptions()*/
    new MongoClient(new ServerAddress(host, port))
  }

  @Bean
  def bearDbFactory: MongoDbFactory = {
    //val user = new UserCredentials("user", "pwd")
    new SimpleMongoDbFactory(mongo, "bear")
  }

  @Bean
  def bearAiDbFactory: MongoDbFactory = {
    //val user = new UserCredentials("user", "pwd")
    new SimpleMongoDbFactory(mongo, "bear_ai")
  }

  @Bean
  def bearTemplate: MongoTemplate = {
    new MongoTemplate(bearDbFactory)
  }

  @Bean
  def bearAiTemplate: MongoTemplate = {
    new MongoTemplate(bearAiDbFactory)
  }

}
