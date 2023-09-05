package com.study.springboot.auth;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

@Configuration
public class CustomAuthenticationFailureHandler implements AuthenticationFailureHandler{
	
	@Override
	public void onAuthenticationFailure(HttpServletRequest request,
										HttpServletResponse response,
			 							AuthenticationException exception)
	// 리퀘스트를 후킹하여 필요한 체크를 한다. ? 웹 애플리케이션에서 요청과 응답사이의 처리과정에 개입하여 특정 작업을
	// 수행하는 것을 의미합니다. 이를 "후킹(hooking)" 또는 "인터셉팅(intercepting)"이라고 합니다.
	throws IOException, ServletException
	{
		String loginid = request.getParameter("j_username");	// 폼 데이터의 값을 구해와 변수에 저장
		String errormsg = "";
		
		// 에러 내용을 비교하여 해당 에러에 대한 한글 메세지를 작성한다. instanceof : 속하는지 여부 검사
		if(exception instanceof BadCredentialsException) {
		// 사용자의 로그인 정보가 잘못되었을 때 발생하는 예외 : BadCredentialsException
		// 만약 예외가 BadCredentialsException 인 경우, loginid에 해당하는 로그인 실패 횟수를 기록한다.
			loginFailureCount(loginid);
			errormsg = "아이디 혹은 비밀번호가 일치하지 않습니다. 다시 시도해주세요.";
		} else if (exception instanceof InternalAuthenticationServiceException) {
			loginFailureCount(loginid);
			errormsg = "아이디 혹은 비밀번호가 일치하지 않습니다. 다시 시도해주세요.";
		} else if (exception instanceof DisabledException) {
			errormsg = "계정이 비활성화되었습니다. 관리자에게 문의해주세요.";
		} else if (exception instanceof CredentialsExpiredException) {
			errormsg = "비밀번호의 유효기간이 만료 되었습니다. 관리자에게 문의해주세요.";
		}

		request.setAttribute("username", loginid);
		request.setAttribute("error_message", errormsg);
		// 리퀘스트 디스패처를 통해 포워딩합니다.
		request.getRequestDispatcher("loginForm?error=true").forward(request, response);
	}
	
	// 비밀번호를 3번 이상 틀릴 시 계정 잠금 처리
	protected void loginFailureCount(String username) {
		/*
		// 틀린 횟수 업데이트
		userDao.countFailure(username);
		// 틀린 횟수 조회
		int cnt = userDao.checkFailureCount(username);
		if(cnt==5) {
			// 계정 잠금 처리
			userDao.disabledUsername(username);
		*/
	}
					
}
