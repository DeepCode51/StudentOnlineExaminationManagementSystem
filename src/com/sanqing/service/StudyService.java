package com.sanqing.service;

import java.util.List;
import java.util.Map;

import com.sanqing.po.Role;
import com.sanqing.po.Study;
import com.sanqing.po.User;
import com.sanqing.util.Page;
import com.sanqing.util.PageResult;

public interface StudyService {



	public PageResult queryStudyByPage(Page page, Study study);


	public void add(Study study);


	public Study getById(String id);


	public void edit(Study study);


	public void delete(String id);


	public List<Map<String, Object>> getMyStudy();


	public List<Map<String, Object>> study(String name, String courseId, User sysUser);

	public Study onLineStudy(String id, User sysUser);
}
