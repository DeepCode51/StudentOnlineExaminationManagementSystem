package com.sanqing.action;

import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;

import com.sanqing.base.action.BaseAction;
import com.sanqing.po.Role;
import com.sanqing.service.RoleService;
import com.sanqing.util.Page;
import com.sanqing.util.PageResult;

@Results({
	@Result(name="list",location="/teacher/role/role-list.jsp"),
	@Result(name="input",location="/teacher/role/role-add.jsp"),
	@Result(name="edit",location="/teacher/role/role-edit.jsp"),
	@Result(name="success",location="role.action",type="redirect")
	
})
public class RoleAction extends BaseAction<Role>{

	private static final long serialVersionUID = 7795872940645683836L;
	private Role role = new Role();
	private int currentPage;
	@Autowired
	private RoleService roleService;
	@Override
	public String list() throws Exception {
		Page page = new Page();
		page.setEveryPage(10);//每页显示10条记录
		page.setCurrentPage(currentPage);//设置当前页
		PageResult pageResult = roleService.queryRoleByPage(page, role);
		getRequest().setAttribute("pageResult", pageResult);
		getRequest().setAttribute("roleName", role.getRoleName());		
		return "list";
	}
	
	
	public String input(){
		return "input";
	}
	public String add(){
		try {
			roleService.add(role);
			this.addActionMessage("新增角色成功!");
		} catch (Exception e) {
			e.printStackTrace();
			this.addActionMessage("新增角色失败："+e.getMessage());
		}
		return "input";
	}
	
	public String edit(){
		role = roleService.getById(id);
		getRequest().setAttribute("role", role);
		return "edit";
	}
	
	public String update(){
		try {
			roleService.edit(role);
			this.addActionMessage("修改角色成功!");
		} catch (Exception e) {
			e.printStackTrace();
			this.addActionMessage("修改角色失败："+e.getMessage());
		}
		return "edit";
	}
	
	public String delete(){
		try {
			roleService.delete(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS; 
	}
	@Override
	public Role getModel() {
		return role;
	}
	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	
}
