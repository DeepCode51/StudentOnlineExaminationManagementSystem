package com.sanqing.service;

import java.util.List;

import com.sanqing.po.Course;
import com.sanqing.po.Res;
import com.sanqing.util.Page;
import com.sanqing.util.PageResult;

public interface CourseService {
	public PageResult queryCourseByPage(Page page, Course course);

	public void add(Course course);

	public Course getById(String id);

	public void edit(Course course);

	public void delete(String id);
	
	public List<Course> getAll();
}
