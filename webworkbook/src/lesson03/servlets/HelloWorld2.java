package lesson03.servlets;

import java.io.IOException;

import javax.servlet.GenericServlet;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * 서블릿 인터페이스 규칙을 통해서 만든 클래스만이 서블릿 클래스가 된다.
 * @author kjlee
 *
 */
public class HelloWorld2 extends GenericServlet {

    private static final long serialVersionUID = 1L;

    @Override
    public void service(ServletRequest arg0, ServletResponse arg1)
            throws ServletException, IOException {
        System.out.println("Hello2 service!!");
    }

}
