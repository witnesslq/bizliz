package com.bear.common.configs

import javax.servlet.{DispatcherType, ServletContext}

import net.bull.javamelody.{MonitoringFilter, SessionListener}
import org.springframework.boot.web.servlet.{FilterRegistrationBean, ServletContextInitializer}
import net.bull.javamelody.Parameter
import org.springframework.context.annotation.{Bean, Configuration, ImportResource}
import org.springframework.stereotype.Component;

/**
  * Created by tanghong on 16/7/19.
  */
@Component
//@ImportResource(Array("classpath:net/bull/javamelody/monitoring-spring.xml"))
@SuppressWarnings(Array("javadoc"))
class MelodyConfiguration extends ServletContextInitializer{
  override def onStartup(servletContext: ServletContext) = {
    servletContext.addListener(new SessionListener())
  }

  @Bean
  def registrationBean : FilterRegistrationBean = {
    //ip:port/marketing/monitoring
    val javaMelody = new FilterRegistrationBean()
    javaMelody.setFilter(new MonitoringFilter())
    javaMelody.setAsyncSupported(true)
    javaMelody.setName("melody")
    javaMelody.setDispatcherTypes(DispatcherType.REQUEST, DispatcherType.ASYNC)
    javaMelody.addInitParameter(Parameter.LOG.getCode, true.toString)
    javaMelody.addInitParameter("monitoring-path", "/bear/monitoring")
    //javaMelody.addUrlPatterns("/*")
    javaMelody
  }

}
