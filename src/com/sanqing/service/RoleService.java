package com.sanqing.service;

import com.sanqing.po.Role;
import com.sanqing.po.User;
import com.sanqing.util.Page;
import com.sanqing.util.PageResult;

public interface RoleService {



	public PageResult queryRoleByPage(Page page, Role role);


	public void add(Role role);


	public Role getById(String id);


	public void edit(Role role);


	public void delete(String id);
}
