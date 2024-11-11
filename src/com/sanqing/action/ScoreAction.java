package com.sanqing.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;

import com.sanqing.base.action.BaseAction;
import com.sanqing.po.Score;
import com.sanqing.po.Subject;
import com.sanqing.po.User;
import com.sanqing.service.ScoreService;
import com.sanqing.util.Page;
import com.sanqing.util.PageResult;

@Results({
	@Result(name="list",location="/teacher/score/score-list.jsp"),	
	@Result(name="examList",location="/teacher/exam/examList.jsp"),
	@Result(name="examSubject",location="/teacher/exam/index.jsp"),
	@Result(name="submitExam",location="/teacher/exam/examResult.jsp")
})
public class ScoreAction extends BaseAction<Score> {

	private static final long serialVersionUID = -6961985576821652833L;
	private Score score = new Score();
	private int currentPage;
	@Autowired
	private ScoreService scoreService;

	@Override
	public String list() throws Exception {
		User sysUser = (User) getSession().getAttribute("session_user_key");
		Page page = new Page();
		page.setEveryPage(10);// 每页显示10条记录
		page.setCurrentPage(currentPage);// 设置当前页
		PageResult pageResult = scoreService.queryScoreByPage(page, score, sysUser);
		getRequest().setAttribute("pageResult", pageResult);
		if(score.getUser()!=null){
			getRequest().setAttribute("name", score.getUser().getName());
		}	
		return "list";
	}
	public String myExamList(){
		User sysUser = (User) getSession().getAttribute("session_user_key");
		List<Map<String, Object>> list = scoreService.getMyExamList(sysUser);
		getRequest().setAttribute("list", list);
		return "examList";
	}
	
	public String getExamSubject(){
		List<Subject> chooseSubject = scoreService.getSubject("1",score.getId());
		List<Subject> decideList = scoreService.getSubject("2",score.getId());
		getRequest().setAttribute("chooseSubject", chooseSubject);
		getRequest().setAttribute("decideList", decideList);
		getRequest().setAttribute("courseId", score.getId());
		return "examSubject";
	}
	
	public String submitExam(){
		User sysUser = (User) getSession().getAttribute("session_user_key");
		String courseId = getRequest().getParameter("courseId");
		String[] subjectId = getRequest().getParameterValues("subjectID");
		List<String> studentAnswers = new ArrayList<String>();
		for(int i = 0; i < 20; i++) {
			String answer = getRequest().getParameter("subjectAnswer"+i);
			studentAnswers.add(answer);
		}
	   int result = scoreService.accountResult(subjectId,studentAnswers,courseId,sysUser);
	    getRequest().setAttribute("result", result);
	    return "submitExam";
	}
	@Override
	public Score getModel() {
		return score;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

}
