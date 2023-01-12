package hello.login.web.login;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import hello.login.domain.login.LoginService;
import hello.login.domain.member.Member;
import hello.login.web.SessionConst;
import hello.login.web.session.SessionManager;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class LoginController {


	private final LoginService loginService;
	private final SessionManager sessionManager;

	//로그인 폼 보여주기
	@GetMapping("/login")
	public String loginForm(@ModelAttribute("loginForm") LoginForm form){
		return "login/loginForm";
	}

	/**
	 * 쿠키 사용
	 */
	//실제 로그인 처리 부분
	// @PostMapping("/login")
	// public String login(@Valid @ModelAttribute LoginForm form, BindingResult bindingResult, HttpServletResponse response){
	// 	if (bindingResult.hasErrors()){
	// 		return "login/loginForm";
	// 	}
	// 	Member loginMember = loginService.login(form.getLoginId(), form.getPassword());
	//
	// 	if (loginMember == null){
	// 		bindingResult.reject("loginFail", "아이디 또는 비밀번호가 맞지 않습니다");
	// 		return "login/loginForm";
	// 	}
	//
	// 	//로그인 성공 처리
	// 	Cookie idCookie = new Cookie("memberId", String.valueOf(loginMember.getId()));
	// 	//생성한 쿠키를 response 객체에 담아서 전달
	// 	response.addCookie(idCookie);
	// 	// 쿠키에 시간 정보를 주지 않으면 세션쿠키가 된다(브라우저 종료시 모두 종료)
	//
	// 	return "redirect:/";
	// }

	/**
	 *세션 사용
	 */
	// @PostMapping("/login")
	// public String loginV2(@Valid @ModelAttribute LoginForm form, BindingResult bindingResult, HttpServletResponse response){
	// 	if (bindingResult.hasErrors()){
	// 		return "login/loginForm";
	// 	}
	// 	Member loginMember = loginService.login(form.getLoginId(), form.getPassword());
	//
	// 	if (loginMember == null){
	// 		bindingResult.reject("loginFail", "아이디 또는 비밀번호가 맞지 않습니다");
	// 		return "login/loginForm";
	// 	}
	//
	// 	//로그인 성공 처리
	// 	//세션 관리자를 통해 세션 생성, 회원 데이터 보관
	// 	sessionManager.createSession(loginMember, response);
	// 	return "redirect:/";
	// }

	/**
	 * Servlet Http Session 사용
	 */
	// @PostMapping("/login")
	// public String loginV3(@Valid @ModelAttribute LoginForm form, BindingResult bindingResult, HttpServletRequest request){
	// 	if (bindingResult.hasErrors()){
	// 		return "login/loginForm";
	// 	}
	// 	Member loginMember = loginService.login(form.getLoginId(), form.getPassword());
	//
	// 	if (loginMember == null){
	// 		bindingResult.reject("loginFail", "아이디 또는 비밀번호가 맞지 않습니다");
	// 		return "login/loginForm";
	// 	}
	//
	// 	//로그인 성공 처리
	// 	// 세션이 있으면 있는 세션 반환, 없으면 신규 세션을 생성
	// 	HttpSession session = request.getSession();
	// 	session.setAttribute(SessionConst.LOGIN_MEMBER, loginMember);
	//
	// 	return "redirect:/";
	// }

	/**
	 * redirect 처리
	 */
	@PostMapping("/login")
	public String loginV3(@Valid @ModelAttribute LoginForm form, BindingResult bindingResult,
				@RequestParam(defaultValue = "/") String redirectURL,
				HttpServletRequest request){

		if (bindingResult.hasErrors()){
			return "login/loginForm";
		}
		Member loginMember = loginService.login(form.getLoginId(), form.getPassword());

		if (loginMember == null){
			bindingResult.reject("loginFail", "아이디 또는 비밀번호가 맞지 않습니다");
			return "login/loginForm";
		}

		//로그인 성공 처리
		// 세션이 있으면 있는 세션 반환, 없으면 신규 세션을 생성
		HttpSession session = request.getSession();
		session.setAttribute(SessionConst.LOGIN_MEMBER, loginMember);

		return "redirect:"+redirectURL;
	}

	/**
	 * 쿠키 사용
	 */
	//로그아웃 기능
	// @PostMapping("/logout")
	// public String logout(HttpServletResponse response){
	// 	Cookie cookie = new Cookie("memberId", null);
	// 	cookie.setMaxAge(0);
	// 	response.addCookie(cookie);
	// 	return "redirect:/";
	// }

	/**
	 * 세션 사용
	 */
	// @PostMapping("/logout")
	// public String logoutV2(HttpServletRequest request){
	// 	sessionManager.expire(request);
	// 	return "redirect:/";
	// }

	/**
	 * Servlet Http Session 사용
	 */
	@PostMapping("/logout")
	public String logoutV3(HttpServletRequest request){
		HttpSession session = request.getSession(false);
		if (session != null){
			session.invalidate();
		}
		return "redirect:/";
	}
}
