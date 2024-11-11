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
import com.sanqing.po.Res;
import com.sanqing.po.Role;
import com.sanqing.po.User;
import com.sanqing.service.ResService;
import com.sanqing.util.Page;
import com.sanqing.util.PageResult;
import com.sanqing.util.PageUtil;
import com.sanqing.util.TimeHelper;

@Service("resService")
@Transactional(readOnly=true)
public class ResServiceImpl implements ResService {

	@Resource(name = "resDao")
	private BaseDao<Res, String> resDao;
	@Override
	public PageResult queryResByPage(Page page, Res res) {
		Map<String, Object> map = new HashMap<String, Object>();
		String hql = "from Res a where 1=1";

		if (StringUtils.isNotBlank(res.getResName())) {
			hql += " and a.resName like:resName";
			map.put("resName", "%" + res.getResName() + "%");
		}
		page = PageUtil.createPage(page.getEveryPage(), (int) resDao.countHqlResult(hql, map), page.getCurrentPage());// 根据总记录数创建分页信息
		List<Res> list = resDao.findByPage(page, hql, map);// 通过分页信息取得试题
		PageResult result = new PageResult(page, list);// 封装分页信息和记录信息，返回给调用处
		return result;
	}

	@Override
	@Transactional(readOnly=false)
	public void add(Res res) {
		res.setResState("00A");
		res.setCreateTime(TimeHelper.getCurrentTime());
		resDao.save(res);
	}

	@Override
	public Res getById(String id) {		
		return resDao.getByKey(id);
	}

	@Override
	@Transactional(readOnly=false)
	public void edit(Res res) {
		Res r = resDao.getByKey(res.getResId());
		BeanUtils.copyProperties(res, r,new String[]{"createTime","resState"});
		if(r.getParent()==null || StringUtils.isBlank(r.getParent().getResId())){
			r.setParent(null);
		}
	}

	@Override
	@Transactional(readOnly=false)
	public void delete(String id) {
		resDao.delete(id);
	}

	@Override
	public List<Res> getAll() {		
		return resDao.find("from Res a where a.resType='1'");
	}

}
