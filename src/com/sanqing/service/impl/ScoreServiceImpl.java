package com.sanqing.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sanqing.dao.base.BaseDao;
import com.sanqing.po.Course;
import com.sanqing.po.Role;
import com.sanqing.po.Score;
import com.sanqing.po.Subject;
import com.sanqing.po.User;
import com.sanqing.service.ScoreService;
import com.sanqing.util.Page;
import com.sanqing.util.PageResult;
import com.sanqing.util.PageUtil;
import com.sanqing.util.TimeHelper;

@Service("scoreService")
@Transactional(readOnly = true)
public class ScoreServiceImpl implements ScoreService {

	@Resource(name = "scoreDao")
	private BaseDao<Score, String> scoreDao;
	
	@Resource(name = "subjectDao")
	private BaseDao<Subject, String> subjectDao;
	
	@Resource(name = "courseDao")
	private BaseDao<Course, String> courseDao;
	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public PageResult queryScoreByPage(Page page, Score score,User sysUser) {
		Map<String, Object> map = new HashMap<String, Object>();
		String hql = "from Score a where 1=1";
		if (score.getUser()!=null &&StringUtils.isNotBlank(score.getUser().getName())) {
			hql += " and a.user.name like:name";
			map.put("name", "%" + score.getUser().getName() + "%");
		}
		if("1".equals(sysUser.getUserType())){
			hql += " and a.user.userId=:userId";
			map.put("userId", sysUser.getUserId());
		}

		page = PageUtil.createPage(page.getEveryPage(), (int) scoreDao.countHqlResult(hql, map), page.getCurrentPage());// 根据总记录数创建分页信息
		List<Score> list = scoreDao.findByPage(page, hql, map);// 通过分页信息取得试题
		PageResult result = new PageResult(page, list);// 封装分页信息和记录信息，返回给调用处
		return result;
	}

	@Override
	public List<Map<String, Object>> getMyExamList(User sysUser) {
		String sql = "SELECT a.*,c.score,c.createTime AS examTime, 	CASE WHEN a.courseId IN(SELECT b.courseId  	FROM tb_score b 	WHERE b.userId =?) THEN '已考试' 	ELSE '未考试' END AS state FROM tb_course a LEFT JOIN tb_score c ON a.courseId=c.courseId AND c.userId=? WHERE a.courseId IN( SELECT DISTINCT b.courseId FROM tb_subject b )  ";
		return jdbcTemplate.queryForList(sql,sysUser.getUserId(),sysUser.getUserId());
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Subject> getSubject(String subjectType, String id) {
		Map<String, Object> values = new HashMap<String, Object>();
		values.put("subjectType", subjectType);
		values.put("courseId", id);
		String hql = "from Subject a where a.subjectType=:subjectType and a.course.courseId=:courseId order by rand()";
		Query query = subjectDao.createQuery(hql, values);
		query.setMaxResults(10);//设置查询记录数
		return  query.list();
	}

	@Override
	@Transactional(readOnly = false)
	public int accountResult(String[] subjectId, List<String> studentAnswers, String courseId, User sysUser) {
		int result = 0;
		for(int i = 0; i < subjectId.length; i++) {
			Subject subject = subjectDao.getByKey(subjectId[i]);
			if(subject.getSubjectAnswer().equals(studentAnswers.get(i))){
				result += 5;//加5分
			}
		}
		Course course = courseDao.getByKey(courseId);
		Score score = new Score();
		score.setCourse(course);
		score.setCreateTime(TimeHelper.getCurrentTime());
		score.setUser(sysUser);
		score.setScore(result+"");
		scoreDao.save(score);
		return result;
	}

}
