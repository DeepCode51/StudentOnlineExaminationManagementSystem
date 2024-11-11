package com.sanqing.action;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;

import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;

import com.sanqing.base.action.BaseAction;
import com.sanqing.po.Course;
import com.sanqing.po.Study;
import com.sanqing.po.User;
import com.sanqing.service.CourseService;
import com.sanqing.service.StudyService;
import com.sanqing.util.Page;
import com.sanqing.util.PageResult;

@Results({ @Result(name = "list", location = "/teacher/study/study-list.jsp"), 
	@Result(name = "input", location = "/teacher/study/study-add.jsp"), 
	@Result(name = "edit", location = "/teacher/study/study-edit.jsp"), 
	@Result(name = "success", location = "study.action", type = "redirect"),
	@Result(name = "myStudy", location = "/teacher/study/study-myStudy.jsp"),
	@Result(name = "studyList", location = "/teacher/study/study-studyList.jsp"),
	@Result(name = "onLine", location = "/teacher/study/study-onLine.jsp")
})
public class StudyAction extends BaseAction<Study> {

	private static final long serialVersionUID = 7795872940645683836L;
	private Study study = new Study();
	private int currentPage;
	@Autowired
	private StudyService studyService;
	@Autowired
	private CourseService courseService;

	@Override
	public String list() throws Exception {
		Page page = new Page();
		page.setEveryPage(10);// 每页显示10条记录
		page.setCurrentPage(currentPage);// 设置当前页
		PageResult pageResult = studyService.queryStudyByPage(page, study);
		getRequest().setAttribute("pageResult", pageResult);
		if (study.getCourse() != null) {
			getRequest().setAttribute("courseName", study.getCourse().getCourseName());
		}

		return "list";
	}

	public String input() {
		List<Course> courses = courseService.getAll();
		getRequest().setAttribute("courses", courses);
		return "input";
	}

	public String add() {
		try {
			studyService.add(study);
			this.addActionMessage("新增学习资料成功!");
		} catch (Exception e) {
			e.printStackTrace();
			this.addActionMessage("新增学习资料失败：" + e.getMessage());
		}
		return "input";
	}

	public String edit() {
		List<Course> courses = courseService.getAll();
		getRequest().setAttribute("courses", courses);
		study = studyService.getById(study.getId());
		getRequest().setAttribute("study", study);
		return "edit";
	}

	public String update() {
		try {
			studyService.edit(study);
			this.addActionMessage("修改学习资料成功!");
		} catch (Exception e) {
			e.printStackTrace();
			this.addActionMessage("修改学习资料失败：" + e.getMessage());
		}
		return "edit";
	}

	public String delete() {
		try {
			studyService.delete(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}

	public String getMyStudy(){
		List<Map<String, Object>> studies = studyService.getMyStudy();
		getRequest().setAttribute("list", studies);
		return "myStudy";
	}
	public String study() throws UnsupportedEncodingException{
		String name =new String(getRequest().getParameter("name").getBytes("iso-8859-1"),"utf-8");
		User sysUser = (User) getSession().getAttribute("session_user_key");
		List<Map<String, Object>> list = studyService.study(name,study.getCourse().getCourseId(),sysUser);
		getRequest().setAttribute("list", list);
		return "studyList";
	}
	
	public String onLineStudy(){
		User sysUser = (User) getSession().getAttribute("session_user_key");
		study  =  studyService.onLineStudy(study.getId(),sysUser);
		getRequest().setAttribute("study", study);
		return "onLine";
	}
	@Override
	public Study getModel() {
		return study;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

}
