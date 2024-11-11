package com.sanqing.action;

import java.util.List;

import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;

import com.sanqing.base.action.BaseAction;
import com.sanqing.po.Res;
import com.sanqing.service.ResService;
import com.sanqing.util.Page;
import com.sanqing.util.PageResult;

@Results({
	@Result(name="list",location="/teacher/res/res-list.jsp"),
	@Result(name="input",location="/teacher/res/res-add.jsp"),
	@Result(name="edit",location="/teacher/res/res-edit.jsp"),
	@Result(name="success",location="res.action",type="redirect")
	
})
public class ResAction extends BaseAction<Res>{

	private static final long serialVersionUID = 7795872940645683836L;
	private Res res = new Res();
	private int currentPage;
	@Autowired
	private ResService resService;
	@Override
	public String list() throws Exception {
		Page page = new Page();
		page.setEveryPage(10);//每页显示10条记录
		page.setCurrentPage(currentPage);//设置当前页
		PageResult pageResult = resService.queryResByPage(page, res);
		getRequest().setAttribute("pageResult", pageResult);
		getRequest().setAttribute("resName", res.getResName());		
		return "list";
	}
	
	
	public String input(){
		List<Res> resList = resService.getAll();
		getRequest().setAttribute("resList", resList);	
		return "input";
	}
	public String add(){
		try {
			resService.add(res);
			this.addActionMessage("新增资源成功!");
		} catch (Exception e) {
			e.printStackTrace();
			this.addActionMessage("新增资源失败："+e.getMessage());
		}
		return "input";
	}
	
	public String edit(){
		List<Res> resList = resService.getAll();
		getRequest().setAttribute("resList", resList);	
		res = resService.getById(id);
		getRequest().setAttribute("res", res);
		return "edit";
	}
	
	public String update(){
		try {
			resService.edit(res);
			this.addActionMessage("修改资源成功!");
		} catch (Exception e) {
			e.printStackTrace();
			this.addActionMessage("修改资源失败："+e.getMessage());
		}
		return "edit";
	}
	
	public String delete(){
		try {
			resService.delete(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS; 
	}
	@Override
	public Res getModel() {
		return res;
	}
	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	
}
