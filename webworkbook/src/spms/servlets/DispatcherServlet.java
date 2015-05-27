package spms.servlets;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import spms.controls.Controller;
import spms.vo.Member;

@WebServlet("*.do")
public class DispatcherServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		response.setContentType("text/html; charset=UTF-8");
		String servletPath = request.getServletPath();
		
		try {
			ServletContext sc = this.getServletContext();
			
			// 페이지 컨트롤러에게 전달할 Map 객체를 준비한다. 
			HashMap<String, Object> model = new HashMap<String, Object>();
			model.put("session", request.getSession());
			model.put("contextPath", request.getContextPath());
			
			Controller pageController = (Controller) sc.getAttribute(servletPath);
			
			switch (servletPath) {
			case "/member/list.do":
				break;
			case "/member/add.do":
				if (request.getParameter("email") != null) {
					model.put("member", new Member()
					.setEmail(request.getParameter("email"))
					.setPassword(request.getParameter("password"))
					.setName(request.getParameter("name")));
				}
				break;
			case "/member/update.do":
				if (request.getParameter("email") == null) {	// 회원 정보 상세 조회 경우
					model.put("no", new Integer(request.getParameter("no")));
				} else {										// 회원 정보 변경 요청인 경우
					model.put("member", new Member()
					.setNo(Integer.parseInt(request.getParameter("no")))
					.setEmail(request.getParameter("email"))
					.setName(request.getParameter("name")));
				}
				break;
			case "/member/delete.do":
				model.put("no", new Integer(request.getParameter("no")));
				break;
			case "/auth/login.do":
				if (request.getParameter("email") != null) {
					model.put("loginInfo", new Member()
							.setEmail(request.getParameter("email"))
							.setPassword(request.getParameter("password")));
				}
				break;
			case "/auth/logout.do":
				break;
			}
			
			// send to PageController
			String viewUrl = pageController.execute(model);
			
			for (String key : model.keySet()) {
				request.setAttribute(key, model.get(key));
			}
					
			// send to JSP View
			if (viewUrl.startsWith("redirect:")) {
				response.sendRedirect(viewUrl.substring(9));
				return;
			} else {
				RequestDispatcher rd = request.getRequestDispatcher(viewUrl);
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
