<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@include file="/include/taglibs.jsp"%>
<html>
  <head>
    <title>成绩管理</title>
	<style type="text/css">
	<!--
	body {
		margin-left: 0px;
		margin-top: 0px;
		margin-right: 0px;
		margin-bottom: 0px;
		background-color: #EEF2FB;
	}
	#manageSubject table  td{
		font-size:12px;
	}
	-->
	</style>
	<link href="${ctx}/teacher/images/skin.css" rel="stylesheet" type="text/css">
  </head>
<body>
<form method="post" action="${ctx}/score.action" name="queryForm">
<table width="100%" border="0" cellpadding="0" cellspacing="0">
  <tr>
    <td width="17" valign="top" background="${ctx}/teacher/images/mail_leftbg.gif"><img src="${ctx}/teacher/images/left-top-right.gif" width="17" height="29" /></td>
    <td valign="top" background="${ctx}/teacher/images/content-bg.gif"><table width="100%" height="31" border="0" cellpadding="0" cellspacing="0" class="left_topbg" id="table2">
      <tr>
        <td height="31"><div class="titlebt">成绩管理</div></td>
      </tr>
    </table></td>
    <td width="16" valign="top" background="${ctx}/teacher/images/mail_rightbg.gif"><img src="${ctx}/teacher/images/nav-right-bg.gif" width="16" height="29" /></td>
  </tr>
  <tr>
    <td valign="middle" background="${ctx}/teacher/images/mail_leftbg.gif">&nbsp;</td>
    <td valign="top" bgcolor="#F7F8F9"><table width="98%" border="0" align="center" cellpadding="0" cellspacing="0">
      <tr>
        <td width="53%" valign="top" style="font-size: 12px;padding-bottom: 5px;">
        	&nbsp;&nbsp;&nbsp;&nbsp;学生名称:<input type="text" name="user.name" value="${name}"/>
        	&nbsp;&nbsp;<input type="button"  value="查询" onclick="javaScript:document.queryForm.submit()">
        </td>
        </tr>
      <tr>
        <td valign="middle">

        <span class="left_txt">
			<div id="manageSubject" align="center" height="400px"><!--管理试题-->
			<table width="95%"  border="0"  cellspacing="0" cellpadding="0"  style="border-collpase:collpase;border:1px solid #AEDEF2;border-collapse: collapse ">
				  <tr align="center">
					<th>序号</th>				
					<th>学生姓名</th>
					<th>课程名称</th>
					<th>成绩</th>
					<th>考试时间</th>
				  </tr>
				  <c:forEach items="${pageResult.list }" begin="0" var="score" varStatus="status">
				   <tr align="center">
				        	<td class="std">${ status.index + 1}</td>
							<td class="std">${score.user.name}</td>							
							<td class="std">${score.course.courseName}</td>
							<td class="std">${score.score}</td>	
							<td class="std">${score.course.createTime}</td>
					  </tr>
				  </c:forEach>
				  <tr>
				  	<td colspan="7" align="right">
				  		共${pageResult.page.totalCount}条纪录，当前第${pageResult.page.currentPage}/${pageResult.page.totalPage}页，每页${pageResult.page.everyPage}条纪录
				  	<c:if test="${pageResult.page.hasPrePage }">				  	
                		<a href="subjectQuery.action?currentPage=1">首页</a> | 
                		<a href="subjectQuery.action?currentPage=${pageResult.page.currentPage - 1}">上一页</a> | 
               		</c:if>
               		<c:if test="${!pageResult.page.hasPrePage }">
               		首页 | 上一页 | 
               		</c:if>
               		<c:if test="${pageResult.page.hasNextPage }">               		
                		<a href="subjectQuery.action?currentPage=${pageResult.page.currentPage + 1}">下一页</a> | 
                		<a href="subjectQuery.action?currentPage=${pageResult.page.totalPage}">尾页</a>
               		</c:if>
               		<c:if test="${!pageResult.page.hasNextPage }">
               		下一页 | 尾页
               		</c:if>
				  	</td>
				  </tr>	  
			</table>	
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
</form>
</body>
</html>
<style>
th {
    background-color: #eef6fe;
    border: 1px solid #aedef2;
    color: #05b;
    font-size: 14px;
    width: 671px;
}
.std{
    background: none repeat scroll 0 0 ghostwhite;
    border: 1px solid #aedef2;
    color: #333;
    font-family: 新宋体;
    font-size: 12px;
}
</style>
