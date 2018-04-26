package org.great.ctrl;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class EncoderFilter implements Filter{

	private String encodeType;
	@Override
	public void destroy() {
		
	}

	@Override
	public void doFilter(ServletRequest arg0, ServletResponse arg1,FilterChain arg2) throws IOException, ServletException {
		HttpServletRequest httpServletRequest = (HttpServletRequest) arg0;
		HttpServletResponse httpServletResponse = (HttpServletResponse) arg1;
		httpServletRequest.setCharacterEncoding(encodeType);
		httpServletResponse.setContentType("text/html;charset="+encodeType);
		arg2.doFilter(httpServletRequest, httpServletResponse);
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		encodeType = arg0.getInitParameter("encode");
	}

}
