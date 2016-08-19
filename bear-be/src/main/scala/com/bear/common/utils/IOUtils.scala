package com.bear.common
package utils

import http.HttpClientFactory._
import java.io._
import java.net.{HttpURLConnection, URL, URLConnection}

import cla.ConvertUitls
import javax.servlet.http.{HttpServletRequest, HttpServletResponse}

import org.apache.commons.fileupload.{FileItem, FileItemIterator}
import org.apache.commons.fileupload.disk.DiskFileItemFactory
import org.apache.commons.fileupload.servlet.{FileCleanerCleanup, ServletFileUpload}
import org.apache.commons.io.FileUtils
import org.apache.http.client.methods.{CloseableHttpResponse, HttpGet}

import scala.util.{Failure, Try}
import collection.JavaConverters._
import scala.collection.mutable.ListBuffer

/**
  * Created by Apple on 16/6/7.
  */
object IOUtils {

  val sizeThreshold = DiskFileItemFactory.DEFAULT_SIZE_THRESHOLD
  val sizeMax = -1
  val encoding = "UTF-8"

  object FileCategorys {
    val pictures = Map(
      "FFD8FF" -> "jpg",
      "89504E47" -> "png",
      "47494638" -> "gif",
      "424D" -> "bmp", // Windows Bitmap (bmp)
      "89504E470D0a1a0a0000" -> "png",
      "424d228c010000000000" -> "bmp",  // 16色位图(bmp)
      "424d8240090000000000" -> "bmp",  // 24位位图(bmp)
      "424d8e1b030000000000" -> "bmp"   // 256色位图(bmp)
    )

    val files = Map(
      "504b0304140000000800" -> "zip",
      "526172211a0700cf9073" -> "rar",
      "235468697320636f6e66" -> "ini",
      "504b03040a0000000000" -> "jar",
      "4d5a9000030000000400" -> "exe",//可执行文件
      "3c25402070616765206c" -> "jsp",//jsp文件
      "4d616e69666573742d56" -> "mf",//MF文件
      "3c3f786d6c2076657273" -> "xml",//xml文件
      "494e5345525420494e54" -> "sql",//xml文件
      "7061636b616765207765" -> "java",//java文件
      "406563686f206f66660d" -> "bat",//bat文件
      "1f8b0800000000000000" -> "gz",//gz文件
      "6c6f67346a2e726f6f74" -> "properties",//bat文件
      "cafebabe0000002e0041" -> "class",//bat文件
      "49545346030000006000" -> "chm",//bat文件
      "04000000010000001300" -> "mxp",//bat文件
      "504b0304140006000800" -> "docx",//docx文件
      "d0cf11e0a1b11ae10000" -> "wps",//WPS文字wps、表格et、演示dps都是一样的
      "6431303a637265617465" -> "torrent",
      "3c21444f435459504520" -> "html", //HTML (html)
      "3c21646f637479706520" -> "htm", //HTM (htm)
      "48544d4c207b0d0a0942" -> "css", //css
      "696b2e71623d696b2e71" -> "js", //js
      "7b5c727466315c616e73" -> "rtf", //Rich Text Format (rtf)
      "38425053000100000000" -> "psd", //Photoshop (psd)
      "46726f6d3a203d3f6762" -> "eml", //Email [Outlook Express 6] (eml)
      "d0cf11e0a1b11ae10000" -> "doc", //MS Excel 注意：word、msi 和 excel的文件头一样
      "d0cf11e0a1b11ae10000" -> "vsd", //Visio 绘图
      "5374616E64617264204A" -> "mdb", //MS Access (mdb)
      "252150532D41646F6265" -> "ps",
      "255044462d312e350d0a" -> "pdf" //AdobeAcrobat (pdf)
    )

    val video = Map(
      "2e524d46000000120001" -> "rmvb", //rmvb/rm相同
      "464c5601050000000900" -> "flv", //flv与f4v相同
      "00000020667479706d70" -> "mp4",
      "49443303000000002176" -> "mp3",
      "000001ba210001000180" -> "mpg", //
      "3026b2758e66cf11a6d9" -> "wmv", //wmv与asf相同
      "52494646e27807005741" -> "wav", //Wave (wav)
      "52494646d07d60074156" -> "avi",
      "4d546864000000060001" -> "mid" //MIDI (mid)
    )

  }


  //  var fileFilter: UploadFileFilter = _

  /**
    * 获取文件后缀类型
    * @return
    */
  def getFileCategory(array: Array[Byte]): String = {
    val stringBuilder = new StringBuilder()
    array.foreach { by =>
      val v = by & 0xFF
      val hv = Integer.toHexString(v)
      if (hv.length() < 2) {
        stringBuilder.append(0)
      }
      stringBuilder.append(hv)
    }
    val hex = String.valueOf(stringBuilder.toString)
    FileCategorys.pictures.getOrElse(hex, "jpg")
  }


  /**
    * 创建文件
    *
    * @param name
    * @return
    */
  def getFile(name: String) = new File(name)

  /**
    * 获取上传文件流
    *
    * @param request
    * @return
    */

  def getFileItemIterator(request: HttpServletRequest): FileItemIterator = {
    val factory: DiskFileItemFactory = new DiskFileItemFactory()
    val tempDir: File = new File("classpath:/temp")
    if (!tempDir.exists()) tempDir.mkdir()
    factory.setSizeThreshold(sizeThreshold)
    factory.setRepository(tempDir)
    // 临时文件清除追踪 -- 最好上传完成后能删除
    factory.setFileCleaningTracker(FileCleanerCleanup.getFileCleaningTracker(request.getServletContext))
    // 文件上传进度监听 可以存在session中
    //upload.setProgressListener(new FileProgressListener)
    val upload: ServletFileUpload = new ServletFileUpload(factory)
    upload.setFileSizeMax(30000000L) // 30m
    upload.setHeaderEncoding(encoding)
    upload.getItemIterator(request)
  }

  /**
    * 下载流文件并获取文件名称
    *
    * @param link
    * @return
    */
  def getInputStream(link: String): (Array[Byte], String) = {
    var res: CloseableHttpResponse = null
    try {
      val hg = new HttpGet(link.trim)
      res = createDefaultClient.execute(hg)
      val in = res.getEntity.getContent
      val buf = ListBuffer[Byte]()
      var b = in.read()
      while (b != -1) {
        buf.append(b.byteValue)
        b = in.read()
      }
      val array = buf.toArray
      val fileName = s"${link.substring(link.lastIndexOf("/") + 1)}.${getFileCategory(array)}"
      in.close()
      (buf.toArray, fileName)
    }
    catch {
      case e: Exception => throw e
    }
    finally {
      res.close()
    }
  }


  /**
    * 上传单个文件
    *
    * @param request
    * @return
    */
  def uploadItemFile(request: HttpServletRequest): Try[File] = {
    val iter: FileItemIterator = this.getFileItemIterator(request)
    var file: File = null
    try {
      while (iter.hasNext) {
        val item = iter.next()
        // val name = item.getFieldName
        // val stream = item.openStream()
        if (!item.isFormField) {
          file = getFile(item.getName)
          FileUtils.copyInputStreamToFile(item.openStream(), file)
        }
      }
    } catch {
      case e: Exception => throw e
    }
    if (file.length() > 0) Try(file)
    else Failure(new Exception("upload file failure"))
  }

  /**
    * 上传多个文件
    *
    * @param request
    * @return
    */
  def uploadMultiFile(request: HttpServletRequest): java.util.Map[String, File] = {
    val iter: FileItemIterator = this.getFileItemIterator(request)
    val map = new java.util.HashMap[String, File]()
    try {
      while (iter.hasNext) {
        val item = iter.next()
        if (!item.isFormField) {
          val fileName = item.getFieldName
          val file = getFile(fileName)
          FileUtils.copyInputStreamToFile(item.openStream(), file)
          if (file.length() > 0) map.put(fileName, file)
        }
      }
    } catch {
      case e: Exception => throw e
    }
    map
  }

  /**
    * 文件下载, 不支持国际化
    *
    * @param fileName
    * @param filePath
    * @param response
    */
  def download(fileName: String, filePath: String, response: HttpServletResponse): Unit = {
    var bis: BufferedInputStream = null
    var bos: BufferedOutputStream = null
    val fileLength = new File(filePath).length()
    try {
      response.setContentType("application/x-msdownload;")
      response.setHeader("Content-disposition", "attachment; filename=" +
        new String(fileName.getBytes("GBK"), "ISO8859-1"))
      response.setHeader("Content-Length", String.valueOf(fileLength));

      bis = new BufferedInputStream(new FileInputStream(filePath))
      bos = new BufferedOutputStream(response.getOutputStream())

      val buff = ConvertUitls.array2JArr(new Array[java.lang.Byte](2014))
      var bytesRead = 0
      bytesRead = bis.read(buff, 0, buff.length)
      while (-1 != bytesRead) {
        bos.write(buff, 0, bytesRead)
      }
    }
    catch {
      case e: Exception =>
    }
    finally {
      if (bis != null) bis.close()
      if (bos != null) bos.close()
    }
  }

  /**
    * 网络文件下载
    *
    * @param link
    * @param response
    */
  def downloadNet(link: String, response: HttpServletResponse): Unit = {
    var bos: BufferedOutputStream = null
    try {
      val conn: URLConnection = new URL(link).openConnection()
      val inStream = conn.getInputStream
      bos = new BufferedOutputStream(response.getOutputStream())

      val buff = ConvertUitls.array2JArr(new Array[java.lang.Byte](2014))
      var bytesRead = 0
      bytesRead = inStream.read(buff, 0, buff.length)
      while (-1 != bytesRead) {
        bos.write(buff, 0, bytesRead)
      }
    }
    catch {
      case e: Exception =>
    }
    finally {
      if (bos != null) bos.close()
    }
  }

  /**
    * 文件下载, 支持在线打开
    *
    * @param filePath
    * @param response
    * @param isOnline
    */
  def download(filePath: String, response: HttpServletResponse, isOnline: Boolean): Unit = {
    val file: File = new File(filePath)
    if (!file.exists()) response.sendError(404, "file not found!")
    else {
      var bis: BufferedInputStream = null
      var bos: BufferedOutputStream = null
      try {
        bis = new BufferedInputStream(new FileInputStream(file))
        bos = new BufferedOutputStream(response.getOutputStream())
        // clean
        response.reset()
        if (isOnline) {
          // 文件最好转换成UTF-8
          val url = new URL(s"file:///${filePath}")
          response.setContentType(url.openConnection.getContentType)
          response.setHeader("Content-Disposition", "inline; filename=" + file.getName())
        }
        else {
          response.setContentType("application/x-msdownload");
          response.setHeader("Content-Disposition", "attachment; filename=" + file.getName())
        }

        val buff = ConvertUitls.array2JArr(new Array[java.lang.Byte](2014))
        var bytesRead = 0
        bytesRead = bis.read(buff, 0, buff.length)
        while (-1 != bytesRead) {
          bos.write(buff, 0, bytesRead)
        }
      } catch {
        case e: Exception =>
      }
      finally {
        if (bis != null) bis.close()
        if (bos != null) bos.close()
      }
    }
  }

}
