package com.vpiaotong.elephant.core.controller.resolver;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;

public class OverallExceptionResolver extends SimpleMappingExceptionResolver {

	@Override
	public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler,
			Exception ex) {

		/*String xRequestedWith = request.getHeader("X-Requested-With");
		if (!StringUtils.isEmpty(xRequestedWith)) {
			try {
				response.setContentType("text/plain;charset=utf-8");
				PrintWriter writer = response.getWriter();
				writer.write("djfajldfj.txt (系统找不到指定的文件。)");
				System.out.println(ex.getMessage());
				writer.flush();
			} catch (IOException e) {
				e.printStackTrace();
			}
			return null;
		} else {*/
			String message = ex.getMessage();
			ModelAndView mav = new ModelAndView();
			mav.addObject("errorMess", message);
			mav.setViewName("errorPage/errorPage");
			return mav;
		//}

	}

}
