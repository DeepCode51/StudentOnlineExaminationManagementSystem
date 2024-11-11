package com.sanqing.action;

import java.util.List;

import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;

import com.sanqing.base.action.BaseAction;
import com.sanqing.po.Course;
import com.sanqing.po.Subject;
import com.sanqing.service.CourseService;
import com.sanqing.service.SubjectService;
import com.sanqing.util.Page;
import com.sanqing.util.PageResult;

@Results({
	@Result(name="list",location="/teacher/subject/subject-list.jsp"),
	@Result(name="input",location="/teacher/subject/subject-add.jsp"),
	@Result(name="edit",location="/teacher/subject/subject-edit.jsp"),
	@Result(name="view",location="/teacher/subject/subject-view.jsp"),
	@Result(name="success",location="subject.action",type="redirect")
	
})
public class SubjectAction extends BaseAction<Subject> {

	private static final long serialVersionUID = -8060701771704877996L;
	private int currentPage;
	private Subject subject = new Subject();
	@Autowired
	private SubjectService subjectService;
	@Autowired
	private CourseService courseService;

	@Override
	public String list() throws Exception {
		Page page = new Page();
		page.setEveryPage(10);// 每页显示10条记录
		page.setCurrentPage(currentPage);// 设置当前页
		PageResult pageResult = subjectService.querySubjectByPage(page, subject);
		getRequest().setAttribute("pageResult", pageResult);
		getRequest().setAttribute("subjectTitle", subject.getSubjectTitle());
		return "list";
	}

	public String input() {
		List<Course> courses = courseService.getAll();
		getRequest().setAttribute("courses", courses);
		return "input";
	}

	public String add() {
		try {
			subjectService.add(subject);
			this.addActionMessage("新增试题成功!");
		} catch (Exception e) {
			e.printStackTrace();
			this.addActionMessage("新增试题失败:" + e.getMessage());
		}

		return "input";
	}

	public String edit() {
		subject = subjectService.getById(id);
		List<Course> courses = courseService.getAll();
		getRequest().setAttribute("courses", courses);
		getRequest().setAttribute("subject", subject);
		return "edit";
	}
	public String view() {
		subject = subjectService.getById(id);
		getRequest().setAttribute("subject", subject);
		return "view";
	}
	public String update() {
		try {
			subjectService.edit(subject);
			this.addActionMessage("修改试题成功!");
		} catch (Exception e) {
			e.printStackTrace();
			this.addActionMessage("修改试题失败：" + e.getMessage());
		}
		return "edit";
	}

	public String delete() {
		try {
			subjectService.delete(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}

	@Override
	public Subject getModel() {
		return subject;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

}
