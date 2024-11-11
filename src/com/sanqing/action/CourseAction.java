package com.sanqing.action;

import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;

import com.sanqing.base.action.BaseAction;
import com.sanqing.po.Course;
import com.sanqing.service.CourseService;
import com.sanqing.util.Page;
import com.sanqing.util.PageResult;

@Results({
	@Result(name="list",location="/teacher/course/course-list.jsp"),
	@Result(name="input",location="/teacher/course/course-add.jsp"),
	@Result(name="edit",location="/teacher/course/course-edit.jsp"),
	@Result(name="success",location="course.action",type="redirect")
	
})
public class CourseAction extends BaseAction<Course>{

	private static final long serialVersionUID = 7795872940645683836L;
	private Course course = new Course();
	private int currentPage;
	@Autowired
	private CourseService courseService;
	@Override
	public String list() throws Exception {
		Page page = new Page();
		page.setEveryPage(10);//每页显示10条记录
		page.setCurrentPage(currentPage);//设置当前页
		PageResult pageResult = courseService.queryCourseByPage(page, course);
		getRequest().setAttribute("pageResult", pageResult);
		getRequest().setAttribute("courseName", course.getCourseName());		
		return "list";
	}
	
	
	public String input(){
		return "input";
	}
	public String add(){
		try {
			courseService.add(course);
			this.addActionMessage("新增课程成功!");
		} catch (Exception e) {
			e.printStackTrace();
			this.addActionMessage("新增课程失败："+e.getMessage());
		}
		return "input";
	}
	
	public String edit(){
		course = courseService.getById(id);
		getRequest().setAttribute("course", course);
		return "edit";
	}
	
	public String update(){
		try {
			courseService.edit(course);
			this.addActionMessage("修改课程成功!");
		} catch (Exception e) {
			e.printStackTrace();
			this.addActionMessage("修改课程失败："+e.getMessage());
		}
		return "edit";
	}
	
	public String delete(){
		try {
			courseService.delete(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS; 
	}
	@Override
	public Course getModel() {
		return course;
	}
	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	
}
