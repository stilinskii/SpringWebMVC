package hello.login.web.login;

import hello.login.domain.login.LoginService;
import hello.login.domain.member.Member;
import hello.login.web.session.SessionManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.UUID;

import static hello.login.web.SessionConst.LOGIN_MEMBER;

@Slf4j
@Controller
@RequiredArgsConstructor
public class loginController {
    private final LoginService loginService;

//    @GetMapping("/login")
//    public String loginForm(@ModelAttribute("loginForm") LoginForm form){
//        return "login/loginForm";
//    }
//
//    @PostMapping("/login")
//    public String login(@Valid @ModelAttribute LoginForm form, BindingResult bindingResult, HttpServletResponse response){
//        if(bindingResult.hasErrors()){
//            return "login/loginForm";
//        }
//        Member loginMember = loginService.login(form.getLoginId(), form.getPassword());
//        if(loginMember == null){
//            bindingResult.reject("loginFail","아이디 또는 비밀번호가 다릅니다");
//            return "login/loginForm";
//        }
//        //로그인 성공 처리
//
//        //쿠키에 시간정보를 주지 않으면 세션 쿠키(브라우저 종료시 모두 종료)
//        Cookie idCookie = new Cookie("memberId", String.valueOf(loginMember.getId()));
//        response.addCookie(idCookie);
//
//        return "redirect:/";
//    }
//
//    @PostMapping("/logout")
//    public String logout(HttpServletResponse response){
//        expireCookie(response,"memberId");
//        return "redirect:/";
//
//    }
//
//    private void expireCookie(HttpServletResponse response, String cookieName) {
//        Cookie cookie = new Cookie(cookieName, null);
//        cookie.setMaxAge(0);
//        response.addCookie(cookie);
//    }


    /*
    * 내가 만든 세션 이용해서 컨트롤러 만들기 V2
    * */
//
//    private final SessionManager sessionManager;
//
//    @GetMapping("/login")
//    public String loginFormV2(@ModelAttribute("loginForm") LoginForm form){
//
//        return "login/loginForm";
//    }
//
//    @PostMapping("/login")
//    public String loginV2(@Valid @ModelAttribute LoginForm form, BindingResult bindingResult, HttpServletResponse response){
//        if(bindingResult.hasErrors()){
//            return "login/loginForm";
//        }
//        Member loginMember = loginService.login(form.getLoginId(), form.getPassword());
//
//        if(loginMember == null){
//            bindingResult.reject("loginFail","아이디 또는 비밀번호가 다릅니다");
//            return "login/loginForm";
//        }
//        //로그인 성공 처리
//        sessionManager.createSession(loginMember,response);
//
//        return "redirect:/";
//    }
//
//    @PostMapping("/logout")
//    public String logoutV2(HttpServletRequest request){
//        sessionManager.expire(request);
//        return "redirect:/";
//
//    }
//

    /*
    * HttpServlet session 이용하기 V3
    * */

    @GetMapping("/login")
    public String loginFormV3(@ModelAttribute("loginForm") LoginForm form){

        return "login/loginForm";
    }

    @PostMapping("/login")
    public String loginV3(@Valid @ModelAttribute LoginForm form, BindingResult bindingResult, HttpServletRequest request){
        if(bindingResult.hasErrors()){
            return "login/loginForm";
        }
        Member loginMember = loginService.login(form.getLoginId(), form.getPassword());

        if(loginMember == null){
            bindingResult.reject("loginFail","아이디 또는 비밀번호가 다릅니다");
            return "login/loginForm";
        }
        //로그인 성공 처리
        //세션이 있으면 있는세션 반환, 없으면 신규세션 반환
        HttpSession session = request.getSession(true);//default가 true (생략가능)
        //세션에 로그인 회원정보 보관
        session.setAttribute(LOGIN_MEMBER,loginMember);


        return "redirect:/";
    }

    @PostMapping("/logout")
    public String logoutV3(HttpServletRequest request){
        HttpSession session = request.getSession(false);
        if(session!=null){
            session.invalidate();
        }
        return "redirect:/";

    }


}
