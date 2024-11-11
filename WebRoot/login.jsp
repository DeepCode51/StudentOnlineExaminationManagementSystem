<%@ page language="java" import="java.util.*" pageEncoding="gbk"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@include file="/include/taglibs.jsp"%>
<html>
  <head>
    <title>教师（学生）登录页面</title>
    <style type="text/css">
	<!--
	body {
		margin-left: 0px;
		margin-top: 0px;
		margin-right: 0px;
		margin-bottom: 0px;
		background-color: #1D3647;
	}
	.STYLE1 {color: #E6EBF1}
	-->
	</style>
	<link href="teacher/images/skin.css" rel="stylesheet" type="text/css">
  </head>
  
  <body>
	  <table width="100%" height="166" border="0" cellpadding="0" cellspacing="0">
	  <tr>
	    <td height="42" valign="top"><table width="100%" height="42" border="0" cellpadding="0" cellspacing="0" class="login_top_bg">
	      <tr>
	        <td width="1%" height="21">&nbsp;</td>
	        <td height="42">&nbsp;</td>
	        <td width="17%">&nbsp;</td>
	      </tr>
	    </table></td>
	  </tr>
	  <tr>
	    <td valign="top"><table width="100%" height="532" border="0" cellpadding="0" cellspacing="0" class="login_bg">
	      <tr>
	        <td width="49%" align="right"><table width="91%" height="532" border="0" cellpadding="0" cellspacing="0" class="login_bg2">
	            <tr>
	              <td height="138" valign="top"><table width="89%" height="427" border="0" cellpadding="0" cellspacing="0">
	                <tr>
	                  <td height="149">&nbsp;</td>
	                </tr>
	                <tr>
	                  <td height="80" align="right" valign="top">&nbsp;</td>
	                </tr>
	                <tr>
	                  <td height="198" align="right" valign="top"><table width="100%" border="0" cellpadding="0" cellspacing="0">
	                    <tr>
	                      <td width="35%">&nbsp;</td>
	                      <td width="65%" height="25" class="left_txt"><p>1- 教师录入考试题目以及答案信息</p></td>
	                    </tr>
	                    <tr>
	                      <td>&nbsp;</td>
	                      <td height="25" class="left_txt"><p>2- 学生参加考试并自动评分和查看正确答案</p></td>
	                    </tr>
	                    <tr>
	                      <td>&nbsp;</td>
	                      <td height="25" class="left_txt"><p>3- 教师查看学生考试成绩</p></td>
	                    </tr>
	                  </table></td>
	                </tr>
	              </table></td>
	            </tr>
	            
	        </table></td>
	        <td width="1%" >&nbsp;</td>
	        <td width="50%" valign="bottom"><table width="100%" height="59" border="0" align="center" cellpadding="0" cellspacing="0">
	            <tr>
	              <td width="4%">&nbsp;</td>
	              <td width="96%" height="38"><span class="login_txt_bt">教师（学生）登录</span></td>
	            </tr>
	            <tr>
	              <td>&nbsp;</td>
	              <td height="21"><table cellSpacing="0" cellPadding="0" width="100%" border="0" id="table211" height="328">
	                  <tr>
	                    <td height="164" colspan="2" align="middle">
	                    	<form name="myform" action="${ctx}/login.action" method="post">
	                        <table cellSpacing="0" cellPadding="0" width="100%" border="0" height="143" id="table212">
	                          <tr>
	                          	<td colspan="2"><FONT color="red"><s:actionerror/></FONT></td><!--显示错误信息 -->
	                          </tr>
	                          <tr>
	                            <td width="14%" height="38" class="top_hui_text"><span class="login_txt">编号：&nbsp;&nbsp; </span></td>
	                            <td height="38" colspan="2" class="top_hui_text"><input name="account" class="editbox4" value="" size="20">                            </td>
	                          </tr>
	                          <tr>
	                            <td width="14%" height="35" class="top_hui_text"><span class="login_txt"> 密 码： &nbsp;&nbsp; </span></td>
	                            <td height="35" colspan="2" class="top_hui_text"><input class="editbox4" type="password" size="20" name="password">
	                              <img src="teacher/images/luck.gif" width="19" height="18"> </td>
	                          </tr>
	                          <tr>
	                            <td width="14%" height="35" ><span class="login_txt">登录角色：</span></td>
	                            <td height="35" colspan="2" class="login_txt"><input type="radio" name="userType" value="2" checked="checked">教师
	                                <input type="radio" name="userType" value="1">学生</td>
	                          </tr>
	                          <tr>
	                            <td height="35" >&nbsp;</td>
	                            <td width="19%" height="35" ><input name="Submit" type="submit" class="button" id="Submit" value="登 录"> </td>
	                            <td width="67%" class="top_hui_text"><input name="cs" type="button" class="button" id="cs" value="取 消" onClick="showConfirmMsg1()"></td>
	                          </tr>
	                        </table>
	                        <br>
	                    </form></td>
	                  </tr>
	                  <tr>
	                    <td width="433" height="164" align="right" valign="bottom"><img src="teacher/images/login-wel.gif" width="242" height="138"></td>
	                    <td width="57" align="right" valign="bottom">&nbsp;</td>
	                  </tr>
	              </table></td>
	            </tr>
	          </table>
	          </td>
	      </tr>
	    </table></td>
	  </tr>
	  <tr>
	    <td height="20"><table width="100%" border="0" cellspacing="0" cellpadding="0" class="login-buttom-bg">
	      <tr>
	        <td align="center"><span class="login-buttom-txt STYLE1">在线考试系统Copyright &copy; 2009-2010 </span></td>
	      </tr>
	    </table></td>
	  </tr>
	</table>
  </body>
</html>
