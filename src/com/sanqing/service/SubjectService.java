package com.sanqing.service;

import com.sanqing.po.Course;
import com.sanqing.po.Subject;
import com.sanqing.util.Page;
import com.sanqing.util.PageResult;

public interface SubjectService {

	public PageResult querySubjectByPage(Page page, Subject subject);

	public void add(Subject subject);

	public Subject getById(String id);

	public void edit(Subject subject);

	public void delete(String id);
}
