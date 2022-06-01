package hello.servlet.web.frontController.v1;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface ControllerV1 {

    //servlet과 똑같은 형태
    void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;
}
