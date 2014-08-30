package com.intera.util.web.servlet.filter

import java.io.{DataOutputStream, OutputStream}
import javax.servlet.ServletOutputStream

class FilterServletOutputStream(output: OutputStream) extends ServletOutputStream {
  def write(b: Int) {
    stream.write(b)
  }

  override def write(b: Array[Byte]) {
    stream.write(b)
  }

  override def write(b: Array[Byte], off: Int, len: Int) {
    stream.write(b, off, len)
  }

  private val stream: DataOutputStream = new DataOutputStream(output)
}