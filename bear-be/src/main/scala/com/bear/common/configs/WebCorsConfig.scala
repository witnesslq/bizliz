package com.bear.common.configs


import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.{CorsRegistry, EnableWebMvc, WebMvcConfigurerAdapter}

/**
  * Created by tanghong on 01/09/2016.
  */
@Configuration
@EnableWebMvc
class WebCorsConfig extends WebMvcConfigurerAdapter{

  override def addCorsMappings(registry: CorsRegistry) = {
    registry.addMapping("/**")
      .allowedOrigins("*")
      .allowedHeaders("*")
      .allowedMethods("*")
      .allowCredentials(true).maxAge(3600)
  }

}
