package hello.springmvc.basic.request;

import hello.springmvc.basic.HelloData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@Slf4j
@Controller
public class RequestParamController {
    @RequestMapping("/request-param-v1")
    public void requestParamV1(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String username = request.getParameter("username");
        int age = Integer.parseInt(request.getParameter("age"));
        log.info("username={}, age={}",username,age);

        response.getWriter().write("ok");
    }

    @ResponseBody
    @RequestMapping("/request-param-v2")
    public String requestParamV2(
            @RequestParam("uesrname") String memberName,
            @RequestParam("age") int memberAge){
        log.info("username={}, age={}",memberName,memberAge);
        return "ok";
    }

    @ResponseBody
    @RequestMapping("/request-param-v3")
    public String requestParamV3(
            @RequestParam String uesrname,
            @RequestParam int age){
        log.info("username={}, age={}",uesrname,age);
        return "ok";
    }

    @ResponseBody
    @RequestMapping("/request-param-v4")
    public String requestParamV4(String uesrname, int age){
        //단순타입이면 requestParam도 생략 가능
        log.info("username={}, age={}",uesrname,age);
        return "ok";
    }

    @ResponseBody
    @RequestMapping("/request-param-required")
    public String requestParamRequired(
            @RequestParam(required = true) String uesrname,
            @RequestParam(required = false) Integer age){
        //단순타입이면 requestParam도 생략 가능
        log.info("username={}, age={}",uesrname,age);
        return "ok";
    }

    @ResponseBody
    @RequestMapping("/request-param-default")
    public String requestParamDefault(
            @RequestParam(required = true, defaultValue = "guest") String uesrname,
            @RequestParam(required = false, defaultValue = "-1") int age){
        //단순타입이면 requestParam도 생략 가능
        log.info("username={}, age={}",uesrname,age);
        return "ok";
    }

    @ResponseBody
    @RequestMapping("/request-param-map")
    public String requestParamMap(@RequestParam Map<String, Object> paramMap){
        //단순타입이면 requestParam도 생략 가능
        log.info("username={}, age={}",paramMap.get("username"),paramMap.get("age"));
        return "ok";
    }
    @ResponseBody
    @RequestMapping("/request-param-multimap")
    public String requestParamMultiMap(@RequestParam MultiValueMap<String, Object> paramMap){
        //단순타입이면 requestParam도 생략 가능
        log.info("username={}, age={}",paramMap.get("username"),paramMap.get("age"));
        return "ok";
    }

    @ResponseBody
    @RequestMapping("/model-attribute-v1")
    public String modelAttributeV1(@ModelAttribute HelloData helloData){

        //modelAttribute는 파라미터만 해당되는건가?
        log.info("helloData={}",helloData);
        return "ok";
    }

    @ResponseBody
    @RequestMapping("/model-attribute-v2")
    public String modelAttributeV2(HelloData helloData){
        //modelAttribute 생략가능
        //String int Integer같은 단순타입은 @RequestParam이 받음
        log.info("helloData={}",helloData);
        return "ok";
    }

}
