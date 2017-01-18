package com.bear.common.configs

import java.net.InetAddress

import org.elasticsearch.client.transport.TransportClient
import org.elasticsearch.common.settings.Settings
import org.elasticsearch.common.transport.InetSocketTransportAddress
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.{Bean, Configuration, PropertySource}
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate

import scala.beans.BeanProperty

/**
  * Created by Apple on 18/01/2017.
  */
@Configuration
@PropertySource(Array("classpath:application.yml"))
class ElasticConfig {
  @Value("${spring.data.mongo.cluster-name}")
  @BeanProperty var clusterName: String =_

  @Value("${spring.data.mongo.cluster-nodes}")
  @BeanProperty var clusterNodes: String = _

  @Bean
  def elasticsearchTemplate: ElasticsearchTemplate = {
    val nodes = clusterNodes.split(":")
    val server = nodes.apply(0)
    val port = nodes.apply(1).toInt
    val settings = Settings.settingsBuilder().put("cluster.name", clusterName).build
    val client = TransportClient.builder().settings(settings).build()
      .addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName(server), port))
    new ElasticsearchTemplate(client)
  }

}
