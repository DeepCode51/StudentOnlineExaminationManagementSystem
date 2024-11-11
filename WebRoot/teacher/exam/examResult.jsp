<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@include file="/include/taglibs.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
<title>考试结果</title>
<link href="images/css2.css" rel="stylesheet" type="text/css"/>
<style type="text/css">
<!--
.STYLE3 {font-size: 18px; }
.STYLE4 {font-size: 18px; font-weight: bold; }
.STYLE5 {color: #FF0000}
-->
</style>

<body>
<table width="1003" height="485" border="0" cellpadding="0" cellspacing="0" class="centerbg">
  <tr>
    <td width="149" height="485">&nbsp;</td>
    <td width="741" valign="top" class="rightbian">
	<table width="60%" align="center" cellpadding="10" cellspacing="0" border="1">
      <tr>
        <td height="35" colspan="2"></td>
      </tr>
      <tr>
        <td height="49" colspan="2"><div align="center"><span class="STYLE4"><strong></strong>考试情况表</span></div></td>
      </tr>
      <tr>
        <td colspan="2">&nbsp;</td>
      </tr>
      <tr>
        <td><div align="center">考试姓名:${session_user_key.name}</div></td>
        <td><div align="center">考生得分:${result}分</div></td>
      </tr>
      <tr>
<!--         <td colspan="2"><div align="center"><a href="showSubjectAnswer.action">查看答案</a></div></td> -->
      </tr>
    </table></td>
    <td width="113">&nbsp;</td>
  </tr>
</table>
</body>
</html>
