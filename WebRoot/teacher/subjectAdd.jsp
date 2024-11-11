<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@include file="/include/taglibs.jsp"%>
<html>
  <head>
    <title>试题添加页面</title>
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
	<link href="images/skin.css" rel="stylesheet" type="text/css">
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
    <td width="17" valign="top" background="images/mail_leftbg.gif"><img src="images/left-top-right.gif" width="17" height="29" /></td>
    <td valign="top" background="images/content-bg.gif"><table width="100%" height="31" border="0" cellpadding="0" cellspacing="0" class="left_topbg" id="table2">
      <tr>
        <td height="31"><div class="titlebt">录入试题</div></td>
      </tr>
    </table></td>
    <td width="16" valign="top" background="images/mail_rightbg.gif"><img src="images/nav-right-bg.gif" width="16" height="29" /></td>
  </tr>
  <tr>
    <td valign="middle" background="images/mail_leftbg.gif">&nbsp;</td>
    <td valign="top" bgcolor="#F7F8F9"><table width="98%" border="0" align="center" cellpadding="0" cellspacing="0">
      <tr>
        <td width="53%" valign="top">&nbsp;</td>
        </tr>
      <tr>
        <td valign="middle"><span class="left_txt">
			<div id="addSubjectForm" align="center"><!--录入试题表单-->
				<form action="subjectAdd.action" method="post">
				<table border="0" cellspacing="10" cellpadding="0">
				  <tr>
					<td colspan="2"><FONT color="red"><s:actionerror/></FONT></td>
				  </tr>
				  <tr>
				  	<td>试题类型:</td>
					<td>
						<input type="radio" name="subjectType" value="1" checked="checked" onclick="selectMy(this.value)"/>选择题
						<input type="radio" name="subjectType" value="2" onclick="selectMy(this.value)"/>判断题
					</td>
				  </tr>
				  <tr>
					<td>试题题目:</td>
					<td><input type="text" name="subjectTitle" size="80" ></td>
				  </tr>
				  <tbody id="select" >
				  <tr>
					<td>选项A:</td>
					<td><input type="text" name="subjectOptionA" size="20" ></td>
				  </tr>
				   <tr>
					<td>选项B:</td>
					<td><input type="text" name="subjectOptionB" size="20" ></td>
				  </tr>
				   <tr>
					<td>选项C:</td>
					<td><input type="text" name="subjectOptionC" size="20" ></td>
				  </tr>
				   <tr>
					<td>选项D:</td>
					<td><input type="text" name="subjectOptionD" size="20" ></td>
				  </tr>
				  <tr>
					<td>答案:</td>
					<td>
						<input name="subjectAnswer" type="radio" value="A" checked>A
						<input name="subjectAnswer" type="radio" value="B">B
						<input name="subjectAnswer" type="radio" value="C">C
						<input name="subjectAnswer" type="radio" value="D">D
					</td>
				  </tr>
				  </tbody>				 
				  	<tr style="display: none" id="answer">
				  	<td>答案:</td>
					<td>
						<input name="subjectAnswer" type="radio" value="1" checked>正确
						<input name="subjectAnswer" type="radio" value="0">错误
					</td>
					</tr>
				   
				  <tr>
					<td valign="top">试题解析:</td>
					<td>
						<textarea name="subjectParse" cols="76" rows="10"></textarea>
					</td>
				  </tr>
				  <tr>
				  	<td colspan="2"><div align="center">
				  	  <input type="submit" value="录入">				  	  
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
    <td background="images/mail_rightbg.gif">&nbsp;</td>
  </tr>
  <tr>
    <td valign="bottom" background="images/mail_leftbg.gif"><img src="images/buttom_left2.gif" width="17" height="17" /></td>
    <td background="images/buttom_bgs.gif"><img src="images/buttom_bgs.gif" width="17" height="17"></td>
    <td valign="bottom" background="images/mail_rightbg.gif"><img src="images/buttom_right2.gif" width="16" height="17" /></td>
  </tr>
</table>
</body>
</html>
