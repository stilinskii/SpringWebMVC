package hello.servlet.web.frontController.v2;

import hello.servlet.web.frontController.MyView;
import hello.servlet.web.frontController.v2.controller.MemberFormControllerV2;
import hello.servlet.web.frontController.v2.controller.MemberSaveControllerV2;
import hello.servlet.web.frontController.v2.controller.MemberListControllerV2;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name="FrontControllerServletV2", urlPatterns = "/front-controller/v2/*")
public class FrontControllerServletV2 extends HttpServlet {

    // <request uri,  매핑하는 컨트롤러>
    private Map<String, ControllerV2> controllerMap = new HashMap<>();


    public FrontControllerServletV2() {
        //매핑정보들
        controllerMap.put("/front-controller/v2/members/new-form", new MemberFormControllerV2());
        controllerMap.put("/front-controller/v2/members/save", new MemberSaveControllerV2());
        controllerMap.put("/front-controller/v2/members", new MemberListControllerV2());
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("FrontControllerServletV2.service");

        String requestURI = request.getRequestURI();
        ControllerV2 controller = controllerMap.get(requestURI);
        if(controller==null){
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }
        // 각 컨트롤러에서 요청 처리
        MyView process = controller.process(request, response);
        //처리 끝나고 프론트 컨트롤러로 다시 돌아와 jsp로 권한 forward
        process.render(request,response);

        //결과적으로 컨트롤러들에서는 view만 반환해주면 나머지는 프론트 컨트롤러에서 공통적으로 해주기때문에
        //중복되는 코드가 훨씬 줄어든다다

    }}
