package com.dgit.interceptor2;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.dgit.domain.UserVO;

public class LoginInterceptor2 extends HandlerInterceptorAdapter {
	// 본 클래스는 로그인 정보를 세션에 저장만 하는 기능
	
	public static final String LOGIN = "login";
	private static final Logger logger = LoggerFactory.getLogger(LoginInterceptor2.class);

	@Override // 컨트롤러 가기전에 호출되는 함수
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// TODO Auto-generated method stub

		logger.info("preHandler.....................");

		// session 안의 login 정보 삭제
		HttpSession session = request.getSession();

		if (session.getAttribute(LOGIN) != null) {
			logger.info("clear login data preHandler");
			session.removeAttribute(LOGIN);
		}

		return true;
	}
	
	@Override // 컨트롤러 갔다가 온후에 호출되는 함수  ( jsp 가기전에 호출됨)
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub
		logger.info("poserHandler");

		HttpSession session = request.getSession();

		UserVO userVO = (UserVO) modelAndView.getModel().get("userVO");
			
		if (userVO != null) {
			logger.info("new Login Success>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
			session.setAttribute(LOGIN, userVO);
			
			
		/*	Object dest = session.getAttribute("dest");
			String  path = dest!=null ? (String) dest:request.getContextPath();
			
			response.sendRedirect(path);// home
			// 가입되지 않은 회원이면, loginPOst 에서는 list로 간다.
*/		}else{
			logger.info("NULLNULLNULLNULL<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
		}
	}

}
