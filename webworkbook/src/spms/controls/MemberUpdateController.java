package spms.controls;

import java.util.Map;

import spms.annotation.Component;
import spms.bind.DataBinding;
import spms.dao.MemberDao;
import spms.vo.Member;

@Component("/member/update.do")
public class MemberUpdateController implements Controller, DataBinding {
	MemberDao memberDao;
	
	public MemberUpdateController setMemberDao(MemberDao memberDao) {
		this.memberDao = memberDao;
		return this;
	}

	@Override
	public Object[] getDataBinders() {
		return new Object[] {
			"no", Integer.class,
			"member", spms.vo.Member.class
		};
	}
	
	@Override
	public String execute(Map<String, Object> model) throws Exception {
		Member member = (Member) model.get("member");
		
		if (member.getEmail() == null) {		// 회원 상세 정보 요청이 들어온 경우 
			Integer no = (Integer) model.get("no");
			member = memberDao.selectOne(no);
			model.put("member", member);
			
			return "/member/MemberUpdateForm.jsp";
		} else {								// 회원 정보 변경 요청이 들어온 경우
			memberDao.update(member);
			return "redirect:list.do";
		}
	}

}
