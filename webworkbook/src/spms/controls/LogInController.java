package spms.controls;

import java.util.Map;

import javax.servlet.http.HttpSession;

import spms.bind.DataBinding;
import spms.dao.MemberDao;
import spms.vo.Member;

public class LogInController implements Controller, DataBinding {
	MemberDao memberDao;
	
	public LogInController setMemberDao(MemberDao memberDao) {
		this.memberDao = memberDao;
		return this;
	}
	
	@Override
	public Object[] getDataBinders() {
		return new Object[] {
			"loginInfo", spms.vo.Member.class	
		};
	}

	@Override
	public String execute(Map<String, Object> model) throws Exception {
		Member loginInfo = (Member) model.get("loginInfo");
		
		if (loginInfo.getEmail() == null) {			// 로그인 폼 요청인 경우,
			return "/auth/LoginForm.jsp";
		} else {						// 로그인 처리 요청인 경우,
			
			Member member = memberDao.exist(loginInfo.getEmail(), loginInfo.getPassword());
			
			if (member != null) {
				HttpSession session = (HttpSession) model.get("session");
				session.setAttribute("member", member);
				return "redirect:../member/list.do";
			} else {
				return "/auth/LoginFail.jsp";
			}
		}
	}

}
