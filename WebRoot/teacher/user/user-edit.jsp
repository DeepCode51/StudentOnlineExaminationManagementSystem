<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@include file="/include/taglibs.jsp"%>
<html>
  <head>
    <title>修改用户</title>
	<style type="text/css">
	<!--
	body {
		margin-left: 0px;
		margin-top: 0px;
		margin-right: 0px;
		margin-bottom: 0px;
		background-color: #EEF2FB;
	}
	#updateSubjectForm table  td{
		font-size:12px;
	}
	-->
	</style>
	<link href="${ctx}/teacher/images/skin.css" rel="stylesheet" type="text/css">
	<script type="text/javascript" src="${ctx}/js/jquery-1.7.2.min.js"></script>
	  <script type="text/javascript">
	  	function sub(myform){
	  		var account = $('#account').val();
	  		var name = $('#name').val();
	  		if(account==null || account ==''){
	  			alert("账号不能为空！");
	  			return false;
	  		}
	  		if(name==null || name ==''){
	  			alert("名称不能为空！");
	  			return false;
	  		}
	  		$('#myform').submit();
	  	}
	 </script>
  </head>
<body>
<table width="100%" border="0" cellpadding="0" cellspacing="0">
  <tr>
    <td width="17" valign="top" background="${ctx}/teacher/images/mail_leftbg.gif"><img src="${ctx}/teacher/images/left-top-right.gif" width="17" height="29" /></td>
    <td valign="top" background="${ctx}/teacher/images/content-bg.gif"><table width="100%" height="31" border="0" cellpadding="0" cellspacing="0" class="left_topbg" id="table2">
      <tr>
        <td height="31"><div class="titlebt">修改用户</div></td>
      </tr>
    </table></td>
    <td width="16" valign="top" background="${ctx}/teacher/images/mail_rightbg.gif"><img src="${ctx}/teacher/images/nav-right-bg.gif" width="16" height="29" /></td>
  </tr>
  <tr>
    <td valign="middle" background="${ctx}/teacher/images/mail_leftbg.gif">&nbsp;</td>
    <td valign="top" bgcolor="#F7F8F9"><table width="98%" border="0" align="center" cellpadding="0" cellspacing="0">
      <tr>
        <td width="53%" valign="top">&nbsp;</td>
        </tr>
      <tr>
        <td valign="middle"><span class="left_txt">
			<div id="updateSubjectForm" align="center"><!--更新试题表单-->
				<form action="${ctx}/user!update.action" method="post" id="myform">
				<table border="0" cellspacing="10" cellpadding="0">
				<input type="hidden" name="userId" value="${user.userId}">
					<tr>
						<td colspan="2"><s:actionmessage/></td>
					</tr>
				  <tr>
					<td>账号：</td>
					<td>						
						<input name="account" id="account" type="text" size="20" value="${user.account}" readonly="readonly">
					</td>
				  </tr>
				  <tr>
					<td>用户名称：</td>
					<td><input type="text" name="name" id="name" size="20" value="${user.name}"></td>
				  </tr>
				   <tr>
					<td>手机号码:</td>
					<td><input type="text" name="mobile" size="20" value="${user.mobile}"></td>
				  </tr>
				   <tr>
					<td>年级:</td>
					<td><input type="text" name="grade" size="20" value="${user.grade}"></td>
				  </tr>
				   <tr>
					<td>用户类型:${user.userType }</td>
					<td>
						<input name="userType" type="radio" value="1" <c:if test="${user.userType=='1' }"> checked="checked"</c:if> >学生
						<c:if test="${session_user_key.userType=='2' }">
						<input name="userType" type="radio" value="2" <c:if test="${user.userType=='2' }"> checked="checked"</c:if>>老师						
						</c:if>
					</td>
				  </tr>
				  <tr>
				  	<td colspan="2"><div align="center">
				  	  <input type="button" value="录入" onclick="sub(this)">				  	  
			  	  </div>
				</td>
				  </tr>
			</table>
			</form>	
			</div>
		</td>
        </tr>
      
    </table></td>
    <td background="${ctx}/teacher/images/mail_rightbg.gif">&nbsp;</td>
  </tr>
  <tr>
    <td valign="bottom" background="${ctx}/teacher/images/mail_leftbg.gif"><img src="${ctx}/teacher/images/buttom_left2.gif" width="17" height="17" /></td>
    <td background="${ctx}/teacher/images/buttom_bgs.gif"><img src="${ctx}/teacher/images/buttom_bgs.gif" width="17" height="17"></td>
    <td valign="bottom" background="${ctx}/teacher/images/mail_rightbg.gif"><img src="${ctx}/teacher/images/buttom_right2.gif" width="16" height="17" /></td>
  </tr>
</table>
</body>