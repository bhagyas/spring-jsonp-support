package com.intera.util.web.servlet.filter

import java.io.{ByteArrayOutputStream, OutputStream}
import java.util.Map
import javax.servlet.http.{HttpServletRequest, HttpServletResponse}
import javax.servlet.{Filter, FilterChain, FilterConfig, ServletRequest, ServletResponse}

import com.typesafe.scalalogging.slf4j.{StrictLogging, LazyLogging}

import scala.collection.JavaConverters._


class JsonpCallbackFilter extends Filter with StrictLogging {
  def init(fConfig: FilterConfig) {
  }

  def doFilter(request: ServletRequest, response: ServletResponse, chain: FilterChain) {
    val httpRequest: HttpServletRequest = request.asInstanceOf[HttpServletRequest]
    val httpResponse: HttpServletResponse = response.asInstanceOf[HttpServletResponse]

    val params: Map[String, Array[String]] = httpRequest.getParameterMap.asScala.toMap.asInstanceOf[Map[String, Array[String]]];

    if (params.containsKey("callback")) {
      logger.debug("Wrapping response with JSONP callback '" + params.get("callback")(0) + "'")
      val out: OutputStream = httpResponse.getOutputStream
      val wrapper: GenericResponseWrapper = new GenericResponseWrapper(httpResponse)
      chain.doFilter(request, wrapper)
      val outputStream: ByteArrayOutputStream = new ByteArrayOutputStream
      outputStream.write(new String(params.get("callback")(0) + "(").getBytes)
      outputStream.write(wrapper.getData)
      outputStream.write(new String(");").getBytes)
      val jsonpResponse: Array[Byte] = outputStream.toByteArray
      wrapper.setContentType("text/javascript;charset=UTF-8")
      wrapper.setContentLength(jsonpResponse.length)
      out.write(jsonpResponse)
      out.close
    }
    else {
      chain.doFilter(request, response)
    }
  }

  def destroy {
  }

}