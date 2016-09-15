package com.bear.common.configs

import java.nio.charset.Charset
import javax.xml.transform.Source
import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.{ApplicationContext, ApplicationContextAware}
import org.springframework.context.annotation.Configuration
import org.springframework.http.converter.json.{GsonHttpMessageConverter, Jackson2ObjectMapperBuilder, MappingJackson2HttpMessageConverter}
import org.springframework.http.converter.support.AllEncompassingFormHttpMessageConverter
import org.springframework.http.converter.xml.{Jaxb2RootElementHttpMessageConverter, MappingJackson2XmlHttpMessageConverter, SourceHttpMessageConverter}
import org.springframework.http.converter.{ByteArrayHttpMessageConverter, HttpMessageConverter, ResourceHttpMessageConverter, StringHttpMessageConverter}
import org.springframework.util.ClassUtils
import org.springframework.web.servlet.config.annotation.{EnableWebMvc, WebMvcConfigurationSupport, WebMvcConfigurerAdapter}

import scala.beans.BeanProperty

/**
  * Created by tanghong on 01/09/2016.
  */
@Configuration
@EnableWebMvc
class WebMsgConverterConfig extends WebMvcConfigurerAdapter with ApplicationContextAware {

  @Autowired
  @BeanProperty var json4sConverter: Json4s2HttpMessageConverter[_] = _

  var application: ApplicationContext = _

  val jaxb2Present: Boolean =
    ClassUtils.isPresent("javax.xml.bind.Binder", classOf[WebMsgConverterConfig].getClassLoader)

  val jackson2Present: Boolean =
    ClassUtils.isPresent("com.fasterxml.jackson.databind.ObjectMapper", classOf[WebMsgConverterConfig].getClassLoader) &&
      ClassUtils.isPresent("com.fasterxml.jackson.core.JsonGenerator", classOf[WebMsgConverterConfig].getClassLoader)

  val jackson2XmlPresent: Boolean =
    ClassUtils.isPresent("com.fasterxml.jackson.dataformat.xml.XmlMapper", classOf[WebMsgConverterConfig].getClassLoader)

  val gsonPresent: Boolean =
    ClassUtils.isPresent("com.google.gson.Gson", classOf[WebMsgConverterConfig].getClassLoader)


  override def setApplicationContext(applicationContext: ApplicationContext): Unit = {
    this.application = application
  }

  override def configureMessageConverters(messageConverters: java.util.List[HttpMessageConverter[_]]): Unit = {
    messageConverters.add(json4sConverter)

    val stringConverter = new StringHttpMessageConverter(Charset.forName("UTF-8"))
    stringConverter.setWriteAcceptCharset(false)
    messageConverters.add(new ByteArrayHttpMessageConverter())
    messageConverters.add(stringConverter)
    messageConverters.add(new ResourceHttpMessageConverter())
    messageConverters.add(new SourceHttpMessageConverter[Source]())
    messageConverters.add(new AllEncompassingFormHttpMessageConverter())

    if (jackson2XmlPresent) {
      val objectMapper = Jackson2ObjectMapperBuilder.xml().applicationContext(application).build()
      messageConverters.add(new MappingJackson2XmlHttpMessageConverter(objectMapper))
    } else if (jaxb2Present) {
      messageConverters.add(new Jaxb2RootElementHttpMessageConverter())
    }

    if (jackson2Present) {
      val objectMapper: ObjectMapper = Jackson2ObjectMapperBuilder.json().applicationContext(application).build()
      messageConverters.add(new MappingJackson2HttpMessageConverter(objectMapper))
    }
    else if (gsonPresent) {
      messageConverters.add(new GsonHttpMessageConverter())
    }
  }

}