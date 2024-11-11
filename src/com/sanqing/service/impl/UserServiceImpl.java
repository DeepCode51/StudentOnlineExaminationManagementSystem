package com.sanqing.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sanqing.dao.base.BaseDao;
import com.sanqing.po.User;
import com.sanqing.service.UserService;
import com.sanqing.util.Page;
import com.sanqing.util.PageResult;
import com.sanqing.util.PageUtil;
import com.sanqing.util.TimeHelper;

@Service("userService")
@Transactional(readOnly=true)
public class UserServiceImpl implements UserService{

	@Resource(name = "userDao")
	private BaseDao<User, String> userDao;
	
	@Override
	public User login(String account, String passWord, String userType) {
		Map<String, Object> values = new HashMap<String, Object>();
		values.put("account", account);
		values.put("password", passWord);
		values.put("userType", userType);
		values.put("state", "00A");
		return userDao.findUnique("from User a where a.account=:account and a.password=:password and a.userType=:userType and a.state=:state", values);
	}

	@Override
	public PageResult queryUserByPage(Page page, User user, User sysUser) {
		Map<String,Object> map = new HashMap<String, Object>();
		String hql = "from User a where 1=1";
		if("1".equals(user.getUserType())){
			hql+=" and a.userType=:userType";
			map.put("userType", "1");
		}
		if("1".equals(sysUser.getUserType())){
			hql+=" and a.userId=:userId";
			map.put("userId", sysUser.getUserId());
		}
		if(StringUtils.isNotBlank(user.getName())){
			hql+=" and a.name like:name";
			map.put("name", "%"+user.getName()+"%");
		}
		if(StringUtils.isNotBlank(user.getAccount())){
			hql+=" and a.account=:account";
			map.put("account", user.getAccount());
		}
		
		
		page = PageUtil.createPage(page.getEveryPage(),(int)userDao.countHqlResult(hql,map),page.getCurrentPage());//根据总记录数创建分页信息
		List<User> list = userDao.findByPage(page, hql,map);//通过分页信息取得试题				
		PageResult result = new PageResult(page,list);//封装分页信息和记录信息，返回给调用处
		return result;
	}

	@Override
	@Transactional(readOnly=false)
	public void add(User user) {
		user.setState("00A");
		user.setCreateTime(TimeHelper.getCurrentTime());
		userDao.save(user);
	}

	@Override
	public User getById(String id) {
		return userDao.getByKey(id);
	}

	@Override
	@Transactional(readOnly=false)
	public void edit(User user) {
		User u = userDao.getByKey(user.getUserId());
		BeanUtils.copyProperties(user, u,new String[]{"password","createTime","state"});
		u.setModify_Time(TimeHelper.getCurrentTime());
	}

	@Override
	@Transactional(readOnly=false)
	public void delete(String id) {
		userDao.delete(id);
	}
	
	

}
