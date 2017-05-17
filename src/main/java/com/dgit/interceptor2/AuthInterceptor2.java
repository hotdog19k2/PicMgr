package com.dgit.interceptor2;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class AuthInterceptor2 extends HandlerInterceptorAdapter {
	private static final Logger logger = LoggerFactory.getLogger(LoginInterceptor2.class);

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// TODO Auto-generated method stub
		logger.info("preHandler....");
		//session에 들어있는 login 정보를 받아서, 잇으면 그대로 진행, 없으면 로그인화며으로 이동
		HttpSession session = request.getSession();
		
		Object login = session.getAttribute(LoginInterceptor2.LOGIN);
		
		if(login == null){ //로그인이 안된다면
			logger.info("go login");
				
			response.sendRedirect(request.getContextPath()+"/user/login");
			return false;
		}
		return true;
	}
	// 로그인화면 이동전에 원래 이동하고자 하는 곳을  저장해 둔후 , 그로그인 성공하면 그곳으로 이동하도록 한다. 
	private void saveDest(HttpServletRequest req){
		String uri = req.getRequestURI();
		String query = req.getQueryString();
		
		if(query == null || query.equals("null")){
			query = "";
		}else {
			query = "?" + query;
		}
		if(req.getMethod().equals("GET")){
			logger.info("dest:" + (uri+query));
			// 매개변수가 있따면 매개변수ㅜ 값까지 모두 기억해둔다.
			req.getSession().setAttribute("dest",uri+query);
			
		}
		
	}
	
	

}
