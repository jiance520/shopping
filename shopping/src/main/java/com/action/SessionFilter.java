package com.action;

import java.io.IOException;
import java.util.*;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// Ȩ�޿��� 
public class SessionFilter implements Filter{

	public void destroy() {
		
	}

	public void doFilter(ServletRequest arg0, ServletResponse arg1,
			FilterChain arg2) throws IOException, ServletException {
		
		HttpServletRequest request = (HttpServletRequest)arg0;
		HttpServletResponse response = (HttpServletResponse)arg1;
		
		String uri = request.getRequestURI();
		//System.out.println(" uri = "+uri);
		String qs = request.getQueryString();
		//System.out.println(" qs = "+qs);
		/*
		 * /EasyBuy/shopping.jsp
		 * /EasyBuy/ShopAction
		 */
		String path = uri.substring(uri.lastIndexOf("/")+1);
		//System.out.println(" path = "+path);
		ArrayList<String> cklist = new ArrayList<String>();
		                  cklist.add("bill.html");
		System.out.println("path:"+path);
		if(cklist.contains(path)){
			 Object uobj = request.getSession().getAttribute("users");
		     if(uobj == null){
		         response.sendRedirect("/index.html");
		     }else{
		    	 arg2.doFilter(arg0, arg1);
		     }
		}else{
			arg2.doFilter(arg0, arg1);
		}
		
	}

	public void init(FilterConfig arg0) throws ServletException {
		
	}

}
