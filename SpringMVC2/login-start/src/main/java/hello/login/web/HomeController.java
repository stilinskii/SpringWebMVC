package hello.login.web;

import hello.login.domain.member.Member;
import hello.login.domain.member.MemberRepository;
import hello.login.web.argumentResolver.Login;
import hello.login.web.session.SessionManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static hello.login.web.SessionConst.LOGIN_MEMBER;

@Slf4j
@Controller
@RequiredArgsConstructor
public class HomeController {
    private final MemberRepository memberRepository;
    private final SessionManager sessionManager;

//    @GetMapping("/")
    public String home() {
        return "home";
    }

//    @GetMapping("/")
    public String homeLogin(@CookieValue(name="memberId", required = false) Long memberId, Model model,HttpServletRequest request){
        if(memberId==null){
            return "home";
        }

        //로그인
//        Member loginMember = memberRepository.findById(memberId);
        Member loginMember = (Member) sessionManager.getSession(request);
        if(loginMember == null){
            return "home";
        }
        model.addAttribute("member",loginMember);
        return "loginHome";
    }
//    @GetMapping("/")
//    public String homeLoginV3(Model model,HttpServletRequest request){
//
//        //세션은 메모리를 쓰는 거기 떄문에 꼭 필요할때만 생성해야한다.
//        HttpSession session = request.getSession(false);
//        if(session==null){
//            return "home";
//        }
//        //로그인
//        Member loginMember = (Member) session.getAttribute(LOGIN_MEMBER);
//
//        //세션에 회원 데이터가 없으면 home
//        if(loginMember == null){
//            return "home";
//        }
//        model.addAttribute("member",loginMember);
//        return "loginHome";
//    }

//    @GetMapping("/")
    public String homeLoginV3(@SessionAttribute(name = LOGIN_MEMBER,required = false) Member loginMember, Model model){

        //세션에 회원 데이터가 없으면 home
        if(loginMember == null){
            return "home";
        }
        model.addAttribute("member",loginMember);
        return "loginHome";
    }

    @GetMapping("/")
    public String homeLoginV3ArgumentResolver(@Login Member loginMember, Model model){

        //세션에 회원 데이터가 없으면 home
        if(loginMember == null){
            return "home";
        }
        model.addAttribute("member",loginMember);
        return "loginHome";
    }
}