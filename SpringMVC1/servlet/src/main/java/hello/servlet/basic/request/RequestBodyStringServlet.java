package hello.servlet.basic.request;

import org.springframework.util.StreamUtils;

import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

@WebServlet(name="requestBodyStringServlet", urlPatterns = "/request-body-string")
public class RequestBodyStringServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ServletInputStream inputStream = req.getInputStream();//메세지바디의 내용을 바이트코드로 바로 얻을 수 있음
        String messagebody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);// 스프링제공 : 스트림을 문자열로 변환
        System.out.println("messagebody = " + messagebody);

        resp.getWriter().write("alright");
    }
}
