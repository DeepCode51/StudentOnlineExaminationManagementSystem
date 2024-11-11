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
import com.sanqing.po.Role;
import com.sanqing.service.RoleService;
import com.sanqing.util.Page;
import com.sanqing.util.PageResult;
import com.sanqing.util.PageUtil;
import com.sanqing.util.TimeHelper;

@Service("roleService")
@Transactional(readOnly = true)
public class RoleServiceImpl implements RoleService {

	@Resource(name = "roleDao")
	private BaseDao<Role, String> roleDao;

	@Override
	public PageResult queryRoleByPage(Page page, Role role) {
		Map<String, Object> map = new HashMap<String, Object>();
		String hql = "from Role a where 1=1";

		if (StringUtils.isNotBlank(role.getRoleName())) {
			hql += " and a.roleName like:roleName";
			map.put("roleName", "%" + role.getRoleName() + "%");
		}

		page = PageUtil.createPage(page.getEveryPage(), (int) roleDao.countHqlResult(hql, map), page.getCurrentPage());// 根据总记录数创建分页信息
		List<Role> list = roleDao.findByPage(page, hql, map);// 通过分页信息取得试题
		PageResult result = new PageResult(page, list);// 封装分页信息和记录信息，返回给调用处
		return result;
	}

	@Override
	@Transactional(readOnly = false)
	public void add(Role role) {
		role.setState("00A");
		role.setCreateTime(TimeHelper.getCurrentTime());
		roleDao.save(role);
	}

	@Override
	public Role getById(String id) {
		return roleDao.getByKey(id);
	}

	@Override
	@Transactional(readOnly = false)
	public void edit(Role role) {
		Role r = roleDao.getByKey(role.getRoleId());
		BeanUtils.copyProperties(role, r, new String[] { "createTime", "state" });
	}

	@Override
	@Transactional(readOnly = false)
	public void delete(String id) {
		roleDao.delete(id);
	}

}
