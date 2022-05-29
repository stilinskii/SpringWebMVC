package hello.servlet.web.servletMVC;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//멤버 폼을 보여주기
@WebServlet(name="mvcMemberFormServlet", urlPatterns = "/servlet-mvc/members/new-form")
public class MvcMemberFormServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //WEB-INF - 외부에서 직접적으로 불려지지 않았으면 좋겠을때
        String viewPath = "/WEB-INF/views/new-form.jsp";
        //지정된 파일(view)로 이동
        RequestDispatcher dispatcher = request.getRequestDispatcher(viewPath);
        //servlet에서 jsp를 호출할수있게 해줌  ,
        //This method allows one servlet to do preliminary processing of a request and another resource to generate the response.
       //서블릿(컨트롤러)이 리퀘스트를 받으면 프로세스를 JSP(뷰)로이동 후(호출) 뷰에서 리스폰트 만들어서 웹브라우저로 보냄
        dispatcher.forward(request,response);
    }
}
