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
import javax.servlet.http.HttpSession;

public class LoginFilter implements Filter {

	@Override
	public void destroy() {
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
		 FilterChain chain) throws IOException, ServletException {
		 HttpServletRequest req = (HttpServletRequest)request;  
         HttpServletResponse res = (HttpServletResponse)response;  		
       String requestURI = req.getRequestURI().substring(req.getRequestURI().indexOf("/",1),req.getRequestURI().length());  
    //  System.out.println("requestURI:"+requestURI);

         if(!"/login.jsp".equals(requestURI)&&!"/Registraion.jsp".equals(requestURI)&&!"/css/style.css".equals(requestURI)
        		 &&!"/js/jquery.min.js".equals(requestURI)&&!"/webLogin.do".equals(requestURI))  
         {   //System.out.println("验证");
             //取得session. 如果没有session则自动会创建一个, 我们用false表示没有取得到session则设置为session为空.  
             HttpSession session = req.getSession();
             //如果session中没有任何东西.  
             if(session.getAttribute("userName")==null)
             {  
                 res.sendRedirect(req.getContextPath() + "/login.jsp");  
                 //返回  
                 return;
             }  
         }  
         //session中的内容等于登录页面, 则可以继续访问其他区资源.  
         chain.doFilter(req, res); 
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
	}

}
