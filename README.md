Adding jsonp support for Spring MVC Controllers
===================================================

This project contains artifacts you can use for adding support for your Spring MVC Controllers.

For more information, please read the following article.

http://jpgmr.wordpress.com/2010/07/28/tutorial-implementing-a-servlet-filter-for-jsonp-callback-with-springs-delegatingfilterproxy/


Getting Started
================
1. Checkout the project from github or download the tar/zip file and extract it.

2. Run `mvn clean install`

3. Add the following dependency to your project.

    	<dependency>
			<groupId>com.intera.util</groupId>
			<artifactId>spring-jsonp-support</artifactId>
			<version>0.0.1</version>
		</dependency>

4. Modify and add the following lines to each of the following files.

`applicationContext.xml`


		<bean class="com.intera.util.web.servlet.filter.JsonpCallbackFilter" id="jsonpCallbackFilter" />


`web.xml`

		<filter>
		    <filter-name>jsonpCallbackFilter</filter-name>
		    <filter-class>org.springframework.web.filter.DelegatingFilterProxy</filter-class>
		</filter>
		 
		<filter-mapping>
		    <filter-name>jsonpCallbackFilter</filter-name>
		    <url-pattern>*.json</url-pattern>
		</filter-mapping>


5. When you are making the request, add .json at the end of the jsonp request and use a callback parameter.

Example : http://localhost:8080/mylistingsapp/listings.json?callback=myFunction



Disclaimer
==========
This code contains no warranty. Use it at your own risk.

If you find any issues, please fork the project, fix it and do a pull request.

With love.