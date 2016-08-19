package com.bear.web.dto

import scala.beans.BeanProperty
/**
  * Created by tanghong on 16/6/27.
  */
class ProjectAppointDto {
  @BeanProperty var projectId: Int = _
  @BeanProperty var projectName: String = _
  @BeanProperty var projectImage: String = _
  @BeanProperty var projectDesc: String = _
  @BeanProperty var projectPrice: Double = _
  @BeanProperty var appointmentPrice: Double = _
  @BeanProperty var salesCount: Int = _
  @BeanProperty var salesPeople: Int = _
  @BeanProperty var affiliatedImage: String = _
}
