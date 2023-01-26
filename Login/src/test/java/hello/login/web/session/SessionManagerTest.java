package hello.login.web.session;

import static org.junit.jupiter.api.Assertions.*;

import javax.servlet.http.HttpServletResponse;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import hello.login.domain.member.Member;

class SessionManagerTest {

	SessionManager sessionManager = new SessionManager();
	MockHttpServletResponse response = new MockHttpServletResponse();
	MockHttpServletRequest request = new MockHttpServletRequest();

	@Test
	void createSession() {

		//세션 생성
		Member member = new Member();
		sessionManager.createSession(member, response);

		//요청에 응답 쿠키 저장
		request.setCookies(response.getCookies());

		//서버에서 확인
		Object session = sessionManager.getSession(request);
		Assertions.assertThat(session).isEqualTo(member);

		//세션 만료
		sessionManager.expire(request);
		Object expired = sessionManager.getSession(request);
		Assertions.assertThat(expired).isNull();

	}

}