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
import com.sanqing.po.Course;
import com.sanqing.po.Res;
import com.sanqing.service.CourseService;
import com.sanqing.util.Page;
import com.sanqing.util.PageResult;
import com.sanqing.util.PageUtil;
import com.sanqing.util.TimeHelper;

@Service("courseService")
@Transactional(readOnly = true)
public class CourseServiceImpl implements CourseService {

	@Resource(name = "courseDao")
	private BaseDao<Course, String> courseDao;

	@Override
	public PageResult queryCourseByPage(Page page, Course course) {
		Map<String, Object> map = new HashMap<String, Object>();
		String hql = "from Course a where 1=1";

		if (StringUtils.isNotBlank(course.getCourseName())) {
			hql += " and a.courseName like:courseName";
			map.put("courseName", "%" + course.getCourseName() + "%");
		}
		page = PageUtil.createPage(page.getEveryPage(), (int) courseDao.countHqlResult(hql, map), page.getCurrentPage());// 根据总记录数创建分页信息
		List<Course> list = courseDao.findByPage(page, hql, map);// 通过分页信息取得试题
		PageResult result = new PageResult(page, list);// 封装分页信息和记录信息，返回给调用处
		return result;
	}

	@Override
	@Transactional(readOnly=false)
	public void add(Course course) {
		course.setState("00A");
		course.setCreateTime(TimeHelper.getCurrentTime());
		courseDao.save(course);
	}

	@Override
	public Course getById(String id) {
		return courseDao.getByKey(id);
	}

	@Override
	@Transactional(readOnly=false)
	public void edit(Course course) {
		Course c = courseDao.getByKey(course.getCourseId());
		BeanUtils.copyProperties(course, c, new String[] { "createTime", "state" });
		c.setModify_Time(TimeHelper.getCurrentTime());
	}

	@Override
	@Transactional(readOnly=false)
	public void delete(String id) {
		courseDao.delete(id);
	}

	@Override
	public List<Course> getAll() {
		return courseDao.find("from Course a ");
	}

}
