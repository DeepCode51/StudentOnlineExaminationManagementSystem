<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@include file="/include/taglibs.jsp"%>
<html>
  <head>
    <title>修改资源</title>
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
	  		var resName = $('#resName').val();
	  		var serial = $('#serial').val();
	  		
	  		if(resName==null || resName ==''){
	  			alert("资源名称不能为空！");
	  			return false;
	  		}
	  		if(serial==null || serial ==''){
	  			alert("序号不能为空！");
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
        <td height="31"><div class="titlebt">新增资源</div></td>
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
				<form action="${ctx}/res!update.action" method="post" id="myform">
				<input type="hidden" name="resId" value="${res.resId}">
				<table border="0" cellspacing="10" cellpadding="0">
					<tr>
						<td colspan="2"><s:actionmessage/></td>
					</tr>
				  <tr>
					<td>资源名称：</td>
					<td>						
						<input name="resName" id="resName" type="text" size="20" value="${res.resName}">
					</td>
				  </tr>
				  <tr>
					<td>父资源名称：</td>
					<td>
						<select name="parent.resId">
						<option value="">请选择</option>
						<c:forEach var="r" items="${resList}" begin="0">
							<option value="${r.resId }" <c:if test="${r.resId==r.parent.resId}"></c:if>>${r.resName}</option>
						</c:forEach>
						</select>
					</td>
				  </tr>
				   <tr>
					<td>资源url</td>
					<td><input type="text" name="resUrl" id="resUrl"  size="20" value="${res.resUrl}"></td>
				  </tr>
				   <tr>
					<td>资源级别</td>
					<td>
						<select name="resLvl">
							<option value="一级" <c:if test="${res.resLvl=='一级'}">selected="selected"</c:if> >一级</option>
							<option value="二级" <c:if test="${res.resLvl=='二级'}">selected="selected"</c:if> >二级</option>
						</select>
					</td>
				  </tr>
				   <tr>
					<td>资源类型:</td>
					<td><input name="resType" type="radio" value="1" <c:if test="${res.resType=='1'}">checked="checked"</c:if>>菜单
						<input name="resType" type="radio" value="2" <c:if test="${res.resType=='2'}">checked="checked"</c:if> >按钮	</td>
				  </tr>
				   <tr>
					<td>序号:</td>
					<td><input type="text" id="serial" name="serial" size="20" value="${res.serial}"></td>
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