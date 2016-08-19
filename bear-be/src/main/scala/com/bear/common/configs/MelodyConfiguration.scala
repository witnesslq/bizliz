package com.bear.common.configs

import javax.servlet.{DispatcherType, ServletContext}

import net.bull.javamelody.{MonitoringFilter, SessionListener}
import org.springframework.boot.context.embedded.{FilterRegistrationBean, ServletContextInitializer}
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
    val javaMelody = new FilterRegistrationBean()
    javaMelody.setFilter(new MonitoringFilter())
    javaMelody.setAsyncSupported(true)
    javaMelody.setName("javamelody")
    javaMelody.setDispatcherTypes(DispatcherType.REQUEST, DispatcherType.ASYNC);

    // see the list of parameters:
    // https://github.com/javamelody/javamelody/wiki/UserGuide#6-optional-parameters
    javaMelody.addInitParameter(Parameter.LOG.getCode(), true.toString);
    // to add basic auth:
    // javaMelody.addInitParameter(Parameter.AUTHORIZED_USERS.getCode(), "admin:pwd");
    // to change the default storage directory:
    // javaMelody.addInitParameter(Parameter.STORAGE_DIRECTORY.getCode(), "/tmp/javamelody");

    javaMelody.addUrlPatterns("/*");
    return javaMelody;
  }

}
