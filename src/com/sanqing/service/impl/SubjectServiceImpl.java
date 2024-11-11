package com.sanqing.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sanqing.dao.base.BaseDao;
import com.sanqing.po.Subject;
import com.sanqing.service.SubjectService;
import com.sanqing.util.Page;
import com.sanqing.util.PageResult;
import com.sanqing.util.PageUtil;

@Service("subjectService")
@Transactional(readOnly=true)
public class SubjectServiceImpl implements SubjectService {

	@Resource(name = "subjectDao")
	private BaseDao<Subject, String> subjectDao;
	
	

	@Override
	public PageResult querySubjectByPage(Page page, Subject subject) {
		Map<String, Object> map = new HashMap<String, Object>();
		String hql = "from Subject a where 1=1";

		if (StringUtils.isNotBlank(subject.getSubjectTitle())) {
			hql += " and a.subjectTitle like:subjectTitle";
			map.put("subjectTitle", "%" + subject.getSubjectTitle() + "%");
		}
		page = PageUtil.createPage(page.getEveryPage(), (int) subjectDao.countHqlResult(hql, map), page.getCurrentPage());// 根据总记录数创建分页信息
		List<Subject> list = subjectDao.findByPage(page, hql, map);// 通过分页信息取得试题
		PageResult result = new PageResult(page, list);// 封装分页信息和记录信息，返回给调用处
		return result;
	}
	@Override
	@Transactional
	public void add(Subject subject) {
		subjectDao.save(subject);
	}
	@Override
	public Subject getById(String id) {
		return subjectDao.getByKey(id);
	}
	
	@Override
	@Transactional(readOnly=false)
	public void edit(Subject subject) {
		subjectDao.saveOrUpdate(subject);
	}

	@Override
	@Transactional
	public void delete(String id) {
		subjectDao.delete(id);
	}

}
