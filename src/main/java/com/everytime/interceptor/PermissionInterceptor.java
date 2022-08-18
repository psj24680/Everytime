package com.everytime.interceptor;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.everytime.board.bo.BoardBO;

@Component
public class PermissionInterceptor implements HandlerInterceptor {

	@Autowired
	private BoardBO boardBO;

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws IOException {
		// 세션에 userLoginId가 있으면 로그인 상태
		HttpSession session = request.getSession();
		String userLoginId = (String) session.getAttribute("userLoginId");

		// URL path 확인
		String uri = request.getRequestURI();
		logger.info("########## uri: {}", uri);

		// 비로그인 && /post => 로그인 화면으로 redirect
		if ((userLoginId == null && uri.startsWith("/main")) || (userLoginId == null && uri.startsWith("/post"))) {
			response.sendRedirect("/user/sign_in_view");
			return false;
		}

		return true; // 요청된 Path로 Controller수행
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) {
		// URI 확인
		String uri = request.getRequestURI();
		logger.info("########## postHandler: {}", uri);

		// URI에서 게시판 id 구하기
		if (uri.startsWith("/board")) {
			String[] arr = uri.split("/");
			int boardId = Integer.parseInt(arr[2]);

			modelAndView.addObject("board", boardBO.getBoardByBoardId(boardId));
		}
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler,
			Exception ex) {
		// URI 확인
		String uri = request.getRequestURI();
		logger.info("########## afterCompletion: {}", uri);
	}

}
