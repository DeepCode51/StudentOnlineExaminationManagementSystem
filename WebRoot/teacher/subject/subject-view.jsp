<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@include file="/include/taglibs.jsp"%>
<html>
  <head>
    <title>查看试题</title>
	<style type="text/css">
	<!--
	body {
		margin-left: 0px;
		margin-top: 0px;
		margin-right: 0px;
		margin-bottom: 0px;
		background-color: #EEF2FB;
	}
	#addSubjectForm table  td{
		font-size:12px;
	}
	-->
	</style>
	<link href="${ctx}/teacher/images/skin.css" rel="stylesheet" type="text/css">
	<script type="text/javascript" src="${ctx}/js/jquery-1.7.2.min.js"></script>
	<script type="text/javascript">
		function selectMy(value){
			if(value=='2'){
				$('#select').css("display","none");
				$('#answer').css("display","");
			}else{
				$('#select').css("display","");
				$('#answer').css("display","none");
			}
		}
	</script>
  </head>
<body> 
 <table width="100%" border="0" cellpadding="0" cellspacing="0">
  <tr>
    <td width="17" valign="top" background="${ctx}/teacher/images/mail_leftbg.gif"><img src="${ctx}/teacher/images/left-top-right.gif" width="17" height="29" /></td>
    <td valign="top" background="${ctx}/teacher/images/content-bg.gif"><table width="100%" height="31" border="0" cellpadding="0" cellspacing="0" class="left_topbg" id="table2">
      <tr>
        <td height="31"><div class="titlebt">查看试题</div></td>
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
			<div id="addSubjectForm" align="center"><!--录入试题表单-->
				<form action="subject!add.action" method="post">
				<table border="0" cellspacing="10" cellpadding="0">
				  <tr>
					<td colspan="2"><FONT color="red"><s:actionerror/></FONT></td>
				  </tr>
				   <tr>
				  	<td>科目名称:</td>
					<td>
						${subject.course.courseName}
					</td>
				  </tr>
				  <tr>
				  	<td>试题类型:</td>
					<td>
						<c:if test="${subject.subjectType=='1' }">选择题</c:if>
						<c:if test="${subject.subjectType=='2' }">判断题</c:if>
					</td>
				  </tr>
				  <tr>
					<td>试题题目:</td>
					<td>${subject.subjectTitle}	</td>
				  </tr>
				 <c:if test="${subject.subjectType=='1' }">
				  <tr>
					<td>选项A:</td>
					<td>${subject.subjectOptionA }</td>
				  </tr>
				   <tr>
					<td>选项B:</td>
					<td>${subject.subjectOptionB }</td>
				  </tr>
				   <tr>
					<td>选项C:</td>
					<td>${subject.subjectOptionC }</td>
				  </tr>
				   <tr>
					<td>选项D:</td>
					<td>${subject.subjectOptionD }</td>
				  </tr>
				  <tr>
					<td>答案:</td>
					<td>${subject.subjectAnswer }</td>
				  </tr>
				  </c:if>
				  	<c:if test="${subject.subjectType=='2' }">		 
				  	<tr id="answer">
				  	<td>答案:</td>
					<td>
						<c:if test="${subject.subjectAnswer=='1' }">正确</c:if>
						<c:if test="${subject.subjectAnswer=='0' }">错误</c:if>
					</td>
					</tr>
				   </c:if>
				  <tr>
					<td valign="top">试题解析:</td>
					<td>
						<textarea name="subjectParse" cols="76" rows="10">${subject.subjectParse }</textarea>
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
</html>
