package com.sanqing.action;

import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;

import com.sanqing.base.action.BaseAction;
import com.sanqing.po.User;
import com.sanqing.service.UserService;
import com.sanqing.util.Page;
import com.sanqing.util.PageResult;

@Results({
	@Result(name="list",location="/teacher/user/user-list.jsp"),
	@Result(name="input",location="/teacher/user/user-add.jsp"),
	@Result(name="edit",location="/teacher/user/user-edit.jsp"),
	@Result(name="studentList",location="/teacher/user/user-studentList.jsp"),
	@Result(name="success",location="user.action",type="redirect")
	
})
public class UserAction extends BaseAction<User>{

	private static final long serialVersionUID = -8396360507495385802L;
	private User user = new User();
	private int currentPage;
	@Autowired
	private UserService userService;

	@Override
	public String list() throws Exception {
		User sysUser = (User) getSession().getAttribute("session_user_key");
		Page page = new Page();
		page.setEveryPage(10);//每页显示10条记录
		page.setCurrentPage(currentPage);//设置当前页
		PageResult pageResult = userService.queryUserByPage(page,user,sysUser);
		getRequest().setAttribute("pageResult", pageResult);
		getRequest().setAttribute("name", user.getName());
		getRequest().setAttribute("account", user.getAccount());
		return "list";
	}
	public String studentList(){
		User sysUser = (User) getSession().getAttribute("session_user_key");
		Page page = new Page();
		page.setEveryPage(10);//每页显示10条记录
		page.setCurrentPage(currentPage);//设置当前页
		user.setUserType("1");
		PageResult pageResult = userService.queryUserByPage(page,user,sysUser);
		getRequest().setAttribute("pageResult", pageResult);
		getRequest().setAttribute("name", user.getName());
		getRequest().setAttribute("account", user.getAccount());
		return "studentList";
	}
	
	public String input(){
		return "input";
	}
	public String add(){
		try {
			userService.add(user);
			this.addActionMessage("新增用户成功!");
		} catch (Exception e) {
			e.printStackTrace();
			this.addActionMessage("新增用户失败："+e.getMessage());
		}
		return "input";
	}
	
	public String edit(){
		user = userService.getById(id);
		getRequest().setAttribute("user", user);
		return "edit";
	}
	@Override
	public User getModel() {
		return user;
	}
	public String update(){
		try {
			userService.edit(user);
			this.addActionMessage("修改用户成功!");
		} catch (Exception e) {
			e.printStackTrace();
			this.addActionMessage("修改用户失败："+e.getMessage());
		}
		return "edit";
	}
	
	public String delete(){
		try {
			userService.delete(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS; 
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	
}
