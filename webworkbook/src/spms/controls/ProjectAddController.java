package spms.controls;

import java.util.Map;

import spms.annotation.Component;
import spms.bind.DataBinding;
import spms.dao.ProjectDao;
import spms.vo.Project;

@Component("/project/add.do")
public class ProjectAddController implements Controller, DataBinding {
	ProjectDao projectDao;
	
	public void setProjectDao(ProjectDao projectDao) {
		this.projectDao = projectDao;
	}
	
	@Override
	public Object[] getDataBinders() {
		return new Object[] {
			"project", spms.vo.Project.class
		};
	}


	@Override
	public String execute(Map<String, Object> model) throws Exception {
		int result = 0;
		Project project = (Project) model.get("project");
		if (project.getTitle() == null) {		// 프로젝트 등록폼 요청 시,
			return "/project/ProjectForm.jsp";
		} else {								// 프로젝트 등록 요청 시,
			result = projectDao.insert(project);
			return "redirect:list.do";
		}
	}

}
