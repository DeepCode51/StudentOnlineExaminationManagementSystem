package com.sanqing.service;

import com.sanqing.po.User;
import com.sanqing.util.Page;
import com.sanqing.util.PageResult;

public interface UserService {

	public User login(String account,String passWord,String userType);


	public PageResult queryUserByPage(Page page, User user, User sysUser);


	public void add(User user);


	public User getById(String id);


	public void edit(User user);


	public void delete(String id);
}
