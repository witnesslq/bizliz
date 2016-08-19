package com.bear.common.utils

import org.joda.time._
import org.joda.time.format._

/**
  * Created by tanghong on 16/7/1.
  */
object JodaUtils {
  val PATTERN_DATETIME = "yyyy-MM-dd HH:mm:ss"
  val PATTERN_LOCALDATE = "yyyy-MM-dd"

  private[this] def getFormatterBuilder(format: String) = {
    new DateTimeFormatterBuilder().appendPattern(format)
  }

  def getDateTime = DateTime.now.toString(PATTERN_DATETIME)

  def getDate = LocalDate.now.toString(PATTERN_LOCALDATE)

  def dateTimeParse(time: String): DateTime = {
    DateTime.parse(
      time,
      new DateTimeFormatter(null, getFormatterBuilder(PATTERN_DATETIME).toParser)
    )
  }

  def LocalDateParse(time: String): LocalDate = {
    LocalDate.parse(
      time,
      new DateTimeFormatter(null, getFormatterBuilder(PATTERN_LOCALDATE).toParser)
    )
  }

}

