package hello.login.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import hello.login.domain.member.Member;
import hello.login.domain.member.MemberRepository;
import hello.login.web.session.SessionManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

@Slf4j
@Controller
@RequiredArgsConstructor
public class HomeController {

    private final MemberRepository memberRepository;
    private final SessionManager sessionManager;

    /**
     * Cookie 사용
     */
    // @GetMapping("/")
    // public String homeLogin(@CookieValue(name = "memberId", required = false) Long memberId, Model model) {
    //     if (memberId == null){
    //         return "home";
    //     }
    //
    //     Member loginMember = memberRepository.findById(memberId);
    //     if (loginMember == null){
    //         return "home";
    //     }
    //     model.addAttribute("member", loginMember);
    //     return "loginHome";
    //
    // }

    /**
     * Session 사용
     */
    // @GetMapping("/")
    // public String homeLoginV2(HttpServletRequest request, Model model) {
    //     Member session = (Member)sessionManager.getSession(request);
    //
    //     if (session==null){
    //         return "home";
    //     }
    //     model.addAttribute("member", session);
    //     return "loginHome";
    // }

    /**
     * Servlet Http Session 사용
     */
    // @GetMapping("/")
    // public String homeLoginV3(HttpServletRequest request, Model model) {
    //
    //     // 세션이 없으면 홈으로
    //     HttpSession session = request.getSession(false);
    //     if (session == null){
    //         return "home";
    //     }
    //     Member loginMember = (Member)session.getAttribute(SessionConst.LOGIN_MEMBER);
    //
    //     // 세션에 회원 데이터가 없으면 홈으로
    //     if (loginMember == null){
    //         return "home";
    //     }
    //
    //     // 세션이 유지되면 로그인으로 이동
    //     model.addAttribute("member", loginMember);
    //     return "loginMember";
    //
    // }

    /**
     * Servlet Http Session2 사용
     */
    @GetMapping("/")
    public String homeLoginV4(@SessionAttribute(name = SessionConst.LOGIN_MEMBER, required = false) Member loginMember, Model model) {

        // 세션에 회원 데이터가 없으면 홈으로
        if (loginMember == null){
            return "home";
        }

        // 세션이 유지되면 로그인으로 이동
        model.addAttribute("member", loginMember);
        return "loginMember";

    }
}