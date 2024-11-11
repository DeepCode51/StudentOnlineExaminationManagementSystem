package com.sanqing.service;

import java.util.List;

import com.sanqing.po.Res;
import com.sanqing.po.User;
import com.sanqing.util.Page;
import com.sanqing.util.PageResult;

public interface ResService {



	public PageResult queryResByPage(Page page, Res res);


	public void add(Res res);


	public Res getById(String id);


	public void edit(Res res);


	public void delete(String id);


	public List<Res> getAll();
}
