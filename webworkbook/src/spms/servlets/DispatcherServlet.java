package spms.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import spms.vo.Member;

@WebServlet("*.do")
public class DispatcherServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		response.setContentType("text/html; charset=UTF-8");
		String servletPath = request.getServletPath();
		System.out.println("servletPath:" + servletPath);
		
		try {
			String pageControllerPath = null;
			
			switch (servletPath) {
			case "/member/list.do":
				pageControllerPath = "/member/list";
				break;
			case "/member/add.do":
				pageControllerPath = "/member/add";
				if (request.getParameter("email") != null) {
					request.setAttribute("member", new Member()
					.setEmail(request.getParameter("email"))
					.setPassword(request.getParameter("password"))
					.setName(request.getParameter("name")));
				}
				break;
			case "/member/update.do":
				pageControllerPath = "/member/update";
				if (request.getParameter("email") != null) {
					request.setAttribute("member", new Member()
					.setNo(Integer.parseInt(request.getParameter("no")))
					.setEmail(request.getParameter("email"))
					.setName(request.getParameter("name")));
				}
				break;
			case "/member/delete.do":
				pageControllerPath = "/member/delete";
				break;
			case "/auth/login.do":
				pageControllerPath = "/auth/login";
				break;
			case "/auth/logout.do":
				pageControllerPath = "/auth/logout";
				break;
			}
			
			// send to PageController
			RequestDispatcher rd = request.getRequestDispatcher(pageControllerPath);
			rd.include(request, response);
			
			// send to JSP View
			String viewUrl = (String) request.getAttribute("viewUrl");
			if (viewUrl.startsWith("redirect:")) {
				response.sendRedirect(viewUrl.substring(9));
				return;
			} else {
				rd = request.getRequestDispatcher(viewUrl);
				rd.include(request, response);
			}
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("error", e);
			RequestDispatcher rd = request.getRequestDispatcher("/error/Error.jsp");
			rd.forward(request, response);
		}
	}
	

}