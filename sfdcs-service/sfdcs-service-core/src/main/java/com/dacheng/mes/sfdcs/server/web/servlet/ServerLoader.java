package com.dacheng.mes.sfdcs.server.web.servlet;

import javax.servlet.ServletContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

public class ServerLoader {
	public ServerLoader(ServletContext servletContext) {
		objServletContext = servletContext;
		objApplicationContext = WebApplicationContextUtils
				.getRequiredWebApplicationContext(getContext());
		logger.info("Spring Server loader bean initialize ...");
	}

	public Object lookupService(String serviceBeanName) {
		return objApplicationContext.getBean(serviceBeanName);
	}

	public static synchronized ServerLoader getInstance(
			ServletContext servletContext) {
		if (null == instance) {
			instance = new ServerLoader(servletContext);
		}

		return instance;
	}

	public static synchronized ServerLoader getInstance() {
		return instance;
	}

	public ServletContext getContext() {
		return objServletContext;
	}

	private Log logger = LogFactory.getLog(this.getClass());
	private ServletContext objServletContext = null;
	private ApplicationContext objApplicationContext = null;
	private static ServerLoader instance = null;
}
