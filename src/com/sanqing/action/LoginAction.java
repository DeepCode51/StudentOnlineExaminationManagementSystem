package com.sanqing.action;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;

import com.sanqing.base.action.BaseAction;
import com.sanqing.po.User;
import com.sanqing.service.UserService;

@Results({
		@Result(name="teachLogin",location="/teacher/index.jsp"),
		@Result(name="studentLogin",location="/student/index.jsp"),
		@Result(name="noLogin",location="/login.jsp")
})
public class LoginAction extends BaseAction<User> {

	private static final long serialVersionUID = 4021216916917177796L;

	private User user = new User();
	@Autowired
	private UserService userService;

	@Override
	public String list() throws Exception {
		User u = userService.login(user.getAccount(), user.getPassword(), user.getUserType());
		if (u != null && StringUtils.isNotBlank(u.getUserId())) {
			getSession().setAttribute("session_user_key", u);
			if("1".equals(user.getUserType())){
				return "studentLogin";
			}else {
				return "teachLogin";
			}
			
		} else {
			if ("1".equals(user.getUserType())) {
				addActionError("该学生编号不存在，或者密码不正确!");
			} else {
				addActionError("该教师编号不存在，或者密码不正确!");
			}
			return "noLogin";

		}
	}

	@Override
	public User getModel() {
		return user;
	}

}
