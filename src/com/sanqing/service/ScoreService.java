package com.sanqing.service;

import java.util.List;
import java.util.Map;

import com.sanqing.po.Score;
import com.sanqing.po.Subject;
import com.sanqing.po.User;
import com.sanqing.util.Page;
import com.sanqing.util.PageResult;

public interface ScoreService {

	public PageResult queryScoreByPage(Page page, Score score,User sysUser);

	public List<Map<String, Object>> getMyExamList(User sysUser);

	public List<Subject> getSubject(String string, String id);

	public int accountResult(String[] subjectId, List<String> studentAnswers, String courseId, User sysUser);
}
