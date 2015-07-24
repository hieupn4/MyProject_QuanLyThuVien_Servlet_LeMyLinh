<%@ page contentType="text/html; charset=utf-8" language="java" import="java.sql.*" errorPage="" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>Login - Lib manager System</title>
    
    
<%
	String logedIn = (String) session.getAttribute("login.done") ;
	String roles = (String) session.getAttribute("patron.roles");
	//logedIn = "nttuyen";
	if(logedIn != null){
		session.removeAttribute("login.done");
		session.removeAttribute("patron.roles");
	}
	String messageErr = (String) request.getAttribute("messageErr");
	if(messageErr == null){
		messageErr = "";
	}
%>
    
    
    
	<link rel="stylesheet" type="text/css" href="style.css">
    <style type="text/css">
<!--
.style1 {color: #FF0000}
-->
    </style>
</head>

<body leftmargin=0 topmargin=0 marginheight="0" marginwidth="0" bgcolor="#ffffff">

<table border="0" cellspacing="0" cellpadding="0" width="100%" height="100%">
<tr valign="top">
	<td width="50%" background="images/bg.gif"><img src="images/px1.gif" width="1" height="1" alt="" border="0"></td>
	<td valign="bottom" background="images/bg_left.gif"><img src="images/bg_left.gif" alt="" width="17" height="16" border="0"></td>
	<td>
<table border="0" cellpadding="0" cellspacing="0" width="780" height="107">
<tr valign="bottom">
	<td><img src="images/logo.gif" width="183" height="107" alt="" border="0"></td>
	<td width="673" background="images/fon_top.gif">
<table border="0" cellpadding="0" cellspacing="0" background="">
<tr valign="bottom">
	<td>
<!-- but act -->
<table border="0" cellpadding="0" cellspacing="0">
<tr valign="bottom">
	<td><img src="images/b_left_a.gif" width="9" height="37" alt="" border="0"></td>
	<td background="images/b_fon_a.gif"><p class="menu01"><a href="">HOME</a></p></td>
	<td><img src="images/b_right_a.gif" width="9" height="37" alt="" border="0"></td>
</tr>
</table>
<!-- /but act -->
	</td>
	<td>
<!-- but -->
<table border="0" cellpadding="0" cellspacing="0">
<tr valign="bottom">
	<td><img src="images/b_left.gif" alt="" width="10" height="30" border="0"></td>
	<td background="images/b_fon.gif"><p class="menu01"><a href="">&nbsp;&nbsp;&nbsp;&nbsp;BOOK&nbsp;&nbsp;&nbsp;&nbsp;</a></p></td>
	<td><img src="images/b_right.gif" alt="" width="10" height="30" border="0"></td>
</tr>
</table>
<!-- /but -->
	</td>
	<td>
<!-- but -->
<table border="0" cellpadding="0" cellspacing="0">
<tr valign="bottom">
	<td><img src="images/b_left.gif" alt="" width="10" height="30" border="0"></td>
	<td background="images/b_fon.gif"><p class="menu01"><a href="">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;CD&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</a></p></td>
	<td><img src="images/b_right.gif" alt="" width="10" height="30" border="0"></td>
</tr>
</table>
<!-- /but -->
	</td>
	<td>
<!-- but -->
<table border="0" cellpadding="0" cellspacing="0">
<tr valign="bottom">
	<td><img src="images/b_left.gif" alt="" width="10" height="30" border="0"></td>
	<td background="images/b_fon.gif"><p class="menu01"><a href="">&nbsp;REQUEST&nbsp;</a></p></td>
	<td><img src="images/b_right.gif" alt="" width="10" height="30" border="0"></td>
</tr>
</table>
<!-- /but -->
	</td>
	<td>
<!-- but -->
<table border="0" cellpadding="0" cellspacing="0">
<tr valign="bottom">
	<td><img src="images/b_left.gif" alt="" width="10" height="30" border="0"></td>
	<td background="images/b_fon.gif"><p class="menu01"><a href="">SUPPORT</a></p></td>
	<td><img src="images/b_right.gif" alt="" width="10" height="30" border="0"></td>
</tr>
</table>
<!-- /but -->
	</td>
	<td>
<!-- but -->
<table border="0" cellpadding="0" cellspacing="0">
<tr valign="bottom">
	<td><img src="images/b_left.gif" alt="" width="10" height="30" border="0"></td>
	<td background="images/b_fon.gif"><p class="menu01"><a href="">CONTACT</a></p></td>
	<td><img src="images/b_right.gif" alt="" width="10" height="30" border="0"></td>
</tr>
</table>
<!-- /but -->
	</td>
</tr>
</table>
	</td>
</tr>
</table>
<table border="0" cellpadding="0" cellspacing="0" width="780" height="107">
<tr valign="top">
	<td bgcolor="#D0E0ED">
<table border="0" cellpadding="0" cellspacing="0" width="100%">
<tr>
	            <td bgcolor="#076BA7"> 
                  <table width="100%" border="0" cellspacing="0" cellpadding="0">
                    <tr>
                      <td><img src="images/text_1.gif" width="183" height="67"></td>
                    </tr>
                  </table>                </td>
</tr>
</table>
<div align="center"></div>
<div align="center"></div>

<div align="left" >
<p></p>
<p class="left"><img src="images/e01.gif" width="14" height="16" alt="" border="0" align="absmiddle">&nbsp;&nbsp;BẠN ĐỌC</p>
<p class="left"><img src="images/dot_o.gif" width="5" height="5" alt="" border="0">&nbsp;&nbsp;<a href="index.jsp">Tìm kiếm </a></p>
<!--
<p class="left"><img src="images/dot_o.gif" width="5" height="5" alt="" border="0">&nbsp;&nbsp;<a href="">Đặt sách </a></p>
-->
<p class="left">&nbsp;</p>
</div>

<hr>
<!-- Menu của người quản lý
	trong trang đăng nhập không thể hiện lên được

<div align="left" >
<p class="left"><img src="images/e01.gif" width="14" height="16" alt="" border="0" align="absmiddle">&nbsp;MANAGER</p>
<p class="left"><img src="images/dot_o.gif" width="5" height="5" alt="" border="0">&nbsp;&nbsp;<a href="editBook.jsp">Thêm sách </a></p>
<p class="left"><img src="images/dot_o.gif" width="5" height="5" alt="" border="0">&nbsp;&nbsp;<a href="editCD.jsp">Thêm CD  </a></p>
<p class="left"><img src="images/dot_o.gif" width="5" height="5" alt="" border="0">&nbsp;&nbsp;<a href="">Cập nhật tài liệu </a></p>
<p class="left"><img src="images/dot_o.gif" width="5" height="5" alt="" border="0">&nbsp;&nbsp;<a href="">Xoá tài liệu </a></p>
<p></p>
<p class="left"><img src="images/dot_o.gif" width="5" height="5" alt="" border="0">&nbsp;&nbsp;<a href="editPatron.jsp">Thêm bạn đọc </a></p>
<p class="left"><img src="images/dot_o.gif" width="5" height="5" alt="" border="0">&nbsp;&nbsp;<a href="">Cập nhật bạn đọc </a></p>
<p class="left"><img src="images/dot_o.gif" width="5" height="5" alt="" border="0">&nbsp;&nbsp;<a href="">Xoá bạn đọc </a></p>
</div>
-->

<br><br>	</td>
	
	<td rowspan="2">
<div align="center"><img src="images/top01.gif" width="597" height="24" alt="" border="0"></div>

<div align="center">
  <table width="100%" border="0">
  <tr>
    <td width="6%" height="30">&nbsp;</td>
    <td width="70%">
        <p align="center">
        	..:: Home &gt; Đăng nhập ::..  </p>
     </td>
    <td width="24%">
    	<p align="right">&nbsp;</p>
    </td>
  </tr>
</table>
</div>

<table border="0" cellpadding="0" cellspacing="0" width="100%">
<tr valign="top">
	<td width="45%">
<p>&nbsp;</p>
<form name="formLogin" method="get" action="controler">
  <table width="95%" border="0" align="center">
    <tr>
      <td width="23%">&nbsp;</td>
      <td width="45%"><p class="style1"> <%= messageErr %> </p></td>
      <td width="32%">&nbsp;</td>
    </tr>
    <tr>
      <td><p> Tên đăng nhập : </p></td>
      <td><input name="login.userName" type="text" id="login.userName" value="Tên đăng nhập " size="35"></td>
      <td>&nbsp;</td>
    </tr>
    <tr>
      <td><p> Mật khẩu : </p></td>
      <td><input name="login.password" type="password" id="login.password" value="mật khẩu" size="35"></td>
      <td>&nbsp;</td>
    </tr>
    <tr>
      <td>&nbsp;</td>
      <td><div align="center">
      	<input name="action" type="hidden" value="LOGIN">
        <input name="login.submit" type="submit" id="login.submit" value="Đăng nhập">
      </div></td>
      <td><p class="right"><a href="">Quên mật khẩu</a></td>
    </tr>
    <tr>
      <td>&nbsp;</td>
      <td>&nbsp;</td>
      <td></td>
    </tr>
<tr></tr>
</table>
</form>
<p>&nbsp;</p>
<p>&nbsp;</p>
<p>&nbsp;</p>
<p>&nbsp;</p>
<p>&nbsp;</p>
<p>&nbsp;</p>
<p>&nbsp;</p>
<p class="right">&nbsp;</p>
<p class="right">&nbsp;</p>	</td>
	</tr>
</table>

<div align="center"><img src="images/hr01.gif" width="556" height="11" alt="" border="0"></div>	</td>
    <td rowspan="2">&nbsp;</td>
</tr>
<tr valign="bottom" bgcolor="#D0E0ED">
	<td><img src="images/bot_left.gif" width="183" height="21" alt="" border="0"></td>
</tr>
</table>
<table border="0" cellpadding="0" cellspacing="0" width="780" height="64" background="images/fon_bot.gif">
<tr valign="top">
	<td>
<table border="0" cellpadding="0" cellspacing="0" width="780" background="">
<tr>
	<td width="300"><p class="menu02">Copyright &copy;2008 nttuyen</p></td>
	<td>
<p class="menu02">
<a href="">Home</a>&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;
<a href="">About Us</a>&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;
<a href="">Support</a>&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;
<a href="">Services</a>&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;
<a href="">Contacts</a>&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;
<a href="">Help</a>&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;
<a href="">FAQ</a>
</p>
	</td>
</tr>
</table>
	</td>
</tr>
</table>
	</td>
	<td valign="bottom" background="images/bg_right.gif"><img src="images/bg_right.gif" alt="" width="17" height="16" border="0"></td>
	<td width="50%" background="images/bg.gif"><img src="images/px1.gif" width="1" height="1" alt="" border="0"></td>
</tr>
</table>

</body>
</html>
