<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@include file="/include/taglibs.jsp"%>
<html>
  <head>
    <title>学习资料修改</title>
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
	  		var name = $('#name').val();
	  		
	  		if(name==null || name ==''){
	  			alert("资料名称不能为空！");
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
        <td height="31"><div class="titlebt">学习资料修改</div></td>
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
				<form action="${ctx}/study!update.action" method="post" id="myform">
				<input type="hidden" name="id" value="${study.id}">
				<table border="0" cellspacing="10" cellpadding="0">
					<tr>
						<td colspan="2"><s:actionmessage/></td>
					</tr>
					<tr>
				  	<td>科目名称:</td>
					<td>
						<select name="course.courseId">
							<c:forEach var="course" items="${courses}" begin="0">
								<option value="${course.courseId }" <c:if test="${study.course.courseId==course.courseId}">selected="selected"</c:if>>${course.courseName }</option>
							</c:forEach>
						</select>
					</td>
				  </tr>
				  <tr>
					<td>资料名称：</td>
					<td>						
						<input name="name" id="name" type="text" size="20" value="${study.name }">
					</td>
				  </tr>	
				    <tr>
					<td>章节：</td>
					<td>						
						<input name="chapters" id="chapters" type="text" size="20"  value="${study.chapters }">
					</td>
				  </tr>	
				   <tr>
					<td valign="top">资料内容:</td>
					<td>
						<textarea name="content" cols="76" rows="10">${study.content }</textarea>
					</td>
				  </tr>
				    <tr>
					<td>序号：</td>
					<td>						
						<input name="serial" id="serial" type="text" size="20" value="${study.serial }">
					</td>
				  </tr>				  
				  <tr>
				  	<td colspan="2"><div align="center">
				  	  <input type="button" value="录入" onclick="sub(this)">				  	  
				  	  <input type="reset" value="重置">
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