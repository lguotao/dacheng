package com.dacheng.mes.sfdcs.server.web.servlet;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dacheng.mes.sfdcs.pojo.WholeConstants;
import com.dacheng.mes.sfdcs.service.DataInfoService;



@SuppressWarnings("serial")
public class InitializeServlet extends HttpServlet {
	
	private String MESSAGE_SERVER = "dataInfoService";
	 
	public void init() throws ServletException {
		ServletContext objServletContext = null;
		String servletPath = "";
		try {
			super.init();
			objServletContext = getServletContext();
			if (null == objServletContext) {
				return;
			}
			servletPath = getServletContext().getRealPath("/WEB-INF/");
			objServletContext.setAttribute("servletPath", servletPath);
			
			ServerLoader.getInstance(objServletContext);
			
			/* set servlet context */
			WholeConstants.servletContext = objServletContext;

			getDefineResource(servletPath);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	protected void service(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		super.service(request, response);
	}

	public void destroy() {
		super.destroy();
	}

	/**
	 * setting initial parameter from setting file
	 */
	private void getDefineResource(String servletPath) {
		
		DataInfoService objDataInfoService = (DataInfoService) ServerLoader.getInstance()
                       .lookupService(MESSAGE_SERVER);
		if (objDataInfoService == null) {
			return;
		}
		String[] arryResult = objDataInfoService.getDefineResource();
		if (arryResult != null && arryResult.length >= 1) {
			if (arryResult[0] != null && arryResult[0].length() > 0) {
				WholeConstants.test = arryResult[0];
			}

		}
	}
}