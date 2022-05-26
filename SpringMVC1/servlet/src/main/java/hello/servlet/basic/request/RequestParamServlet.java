package hello.servlet.basic.request;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
//1. 파라미터 전송기능
// http://localhost:8081/request-param?username=hello&age=20

//get과 post방식 test
@WebServlet(name="requestParamServlet", urlPatterns = "/request-param")
public class RequestParamServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("[전체 파라미터 조회] - start");
        //모든 요청파라미터 다 꺼낼 수 있다.
        request.getParameterNames().asIterator()                 //키(uesrname,age)        밸류(hello,20) 키에 해당하는 밸류 가져옴
                .forEachRemaining(paramName -> System.out.println(paramName +"= " + request.getParameter(paramName)));
        System.out.println("request.getParameterNames() = " + request.getParameterNames());
        request.getQueryString();
        System.out.println("request.getQueryString() = " + request.getQueryString());

        System.out.println("[전체 파라미터 조회] - end");
        System.out.println();
        System.out.println("[단일 파라미터 조회] - start");
        String username = request.getParameter("username");
        System.out.println("username = " + username);
        String age = request.getParameter("age");
        System.out.println("age = " + age);
        System.out.println("[단일 파라미터 조회] - end");

        System.out.println("[이름이 같은 복수 파라미터 조회]");
        String[] usernames = request.getParameterValues("username");
        for (String name : usernames) {
            System.out.println("username = " + name);
        }

        response.getWriter().write("ok");

    }
}
