package spms.servlets;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import spms.dao.MemberDao;

@SuppressWarnings("serial")
@WebServlet("/member/delete")
public class MemberDeleteServlet extends HttpServlet {

//    private Logger logger = Logger.getLogger(getClass());
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
    	int result = 0;
    	
        try {
            ServletContext sc = this.getServletContext();
            MemberDao memberDao = (MemberDao) sc.getAttribute("memberDao");
            
            result = memberDao.delete(Integer.parseInt(req.getParameter("no")));
            if (result != 1) {
            	throw new ServletException("Failed in delete - result:" + result);
            }
            
            req.setAttribute("viewUrl", "redirect:list.do");
        } catch (Exception e) {
        	throw new ServletException(e);
        }
    }
    
}
