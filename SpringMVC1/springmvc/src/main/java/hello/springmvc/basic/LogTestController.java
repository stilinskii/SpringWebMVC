package hello.springmvc.basic;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class LogTestController {
    //@Slf4j 넣어서 없어도 됨
//    private final Logger log = LoggerFactory.getLogger(getClass());

    @RequestMapping("/log-test")
    public String logTest(){
        String name = "Spring";
        System.out.println("name = " + name);
        log.trace("trace log={}",name);
        log.debug("trace log={}",name);
        log.info(" info log={}",name);
        log.warn(" info log={}",name);
        log.error(" info log={}",name);


        //RestController는 스트링이 그대로 반환
        //Controller는 뷰리졸버 통해서 반영영
       return "ok";

    }
}
