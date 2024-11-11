package com.sanqing.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sanqing.dao.base.BaseDao;
import com.sanqing.po.Study;
import com.sanqing.po.User;
import com.sanqing.po.UserStudy;
import com.sanqing.service.StudyService;
import com.sanqing.util.Page;
import com.sanqing.util.PageResult;
import com.sanqing.util.PageUtil;
import com.sanqing.util.TimeHelper;

@Service("studyService")
@Transactional(readOnly = true)
public class StudyServiceImpl implements StudyService {

	@Resource(name = "studyDao")
	private BaseDao<Study, String> studyDao;

	@Resource(name = "userStudyDao")
	private BaseDao<UserStudy, String> userStudyDao;
	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public PageResult queryStudyByPage(Page page, Study study) {
		Map<String, Object> map = new HashMap<String, Object>();
		String hql = "from Study a where 1=1";
		if (study.getCourse() != null && StringUtils.isNotBlank(study.getCourse().getCourseName())) {
			hql += " and a.course.name like:name";
			map.put("name", "%" + study.getCourse().getCourseName() + "%");
		}
		page = PageUtil.createPage(page.getEveryPage(), (int) studyDao.countHqlResult(hql, map), page.getCurrentPage());// 根据总记录数创建分页信息
		List<Study> list = studyDao.findByPage(page, hql, map);// 通过分页信息取得试题
		PageResult result = new PageResult(page, list);// 封装分页信息和记录信息，返回给调用处
		return result;
	}

	@Override
	@Transactional(readOnly = false)
	public void add(Study study) {
		study.setCreateTime(TimeHelper.getCurrentTime());
		studyDao.save(study);
	}

	@Override
	public Study getById(String id) {
		return studyDao.getByKey(id);
	}

	@Override
	@Transactional(readOnly = false)
	public void edit(Study study) {
		Study s = studyDao.getByKey(study.getId());
		BeanUtils.copyProperties(study, s, new String[] { "createTime" });
	}

	@Override
	@Transactional(readOnly = false)
	public void delete(String id) {
		studyDao.delete(id);
	}

	@Override
	public List<Map<String, Object>> getMyStudy() {
		String sql = "SELECT  DISTINCT a. courseId ,a. name  ,b.* FROM tb_study a INNER JOIN tb_course b ON a. courseId =b. courseId ";
		return jdbcTemplate.queryForList(sql);
	}

	@Override
	public List<Map<String, Object>> study(String name, String courseId, User sysUser) {
		String sql = "SELECT a.*,b.createTime AS studyTime, CASE WHEN a.id IN(SELECT b.studyId  FROM tb_user_study b WHERE b.user_id =?) THEN '已学习' ELSE '未学习' END AS state FROM tb_study a LEFT JOIN tb_user_study b ON a.id = b.studyId AND b.user_id=? WHERE a.courseId =? AND a.name =?  ORDER BY a.serial";
		return jdbcTemplate.queryForList(sql, sysUser.getUserId(), sysUser.getUserId(), courseId, name);
	}

	@Override
	@Transactional(readOnly = false)
	public Study onLineStudy(String id, User sysUser) {
		Study study = studyDao.getByKey(id);
		// 判断是否学习过
		Map<String, Object> values = new HashMap<String, Object>();
		values.put("id", id);
		values.put("userId", sysUser.getUserId());
		List<UserStudy> list = userStudyDao.find("from UserStudy a where a.study.id=:id and a.user.userId=:userId", values);
		if (list != null && list.size() > 0) {// 已经学过。更新学习日期
			UserStudy userStudy = list.get(0);
			userStudy.setCreateTime(TimeHelper.getCurrentTime());
		} else {
			UserStudy userStudy = new UserStudy();
			userStudy.setStudy(study);
			userStudy.setUser(sysUser);
			userStudy.setChapters(study.getChapters());
			userStudy.setCreateTime(TimeHelper.getCurrentTime());
			userStudyDao.save(userStudy);
		}
		return study;
	}

}
