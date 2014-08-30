package com.intera.util.web.servlet.filter

import java.io.ByteArrayOutputStream
import java.io.PrintWriter
import javax.servlet.ServletOutputStream
import javax.servlet.http.HttpServletResponse
import javax.servlet.http.HttpServletResponseWrapper

class GenericResponseWrapper(response: HttpServletResponse) extends HttpServletResponseWrapper(response){

  def getData: Array[Byte] = {
    return output.toByteArray
  }

  override def getOutputStream: ServletOutputStream = {
    return new FilterServletOutputStream(output)
  }

  override def getWriter: PrintWriter = {
    return new PrintWriter(getOutputStream, true)
  }

  override def setContentLength(length: Int) {
    this.contentLength = length
    super.setContentLength(length)
  }

  def getContentLength: Int = {
    return contentLength
  }

  override def setContentType(`type`: String) {
    this.contentType = `type`
    super.setContentType(`type`)
  }

  override def getContentType: String = {
    return contentType
  }

  private var output: ByteArrayOutputStream = new ByteArrayOutputStream
  private var contentLength: Int = 0
  private var contentType: String = null
}