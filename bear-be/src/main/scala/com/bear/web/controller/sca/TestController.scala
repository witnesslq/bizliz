package com.bear.web.controller.sca

import com.bear.common.consts.Url
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation._

/**
  * Created by tanghong on 05/08/2016.
  */
@Controller
@CrossOrigin(origins = Array("*"), maxAge = 3600, allowedHeaders = Array("Content-Type"),
  allowCredentials = "false")
class TestController {

  @RequestMapping(value = Array("/show/index"), method = Array(RequestMethod.GET))
  def index = {
    "index"
  }


}
