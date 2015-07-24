<%@ page contentType="text/html; charset=utf-8" language="java" import="java.sql.*" errorPage="" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@page import="manager.resource.CDResource"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>CD infomation - Lib manager Sys</title>
    
    
<%
	String logedIn = (String) session.getAttribute("login.done") ;
	String roles = (String) session.getAttribute("patron.roles");
	//logedIn = "nttuyen";
	//Nếu không fải là nhân viên thư viện thì không được truy cập trang này
	if(logedIn == null || !"PATRON_LIB_MANAGER".equals(roles)){
		response.sendRedirect("login.jsp");
	}
	
	String action = null ;
	CDResource cd = (CDResource)request.getAttribute("cd_edit");
	if(cd == null){
		action = "ADD_CD";	
		cd = new CDResource();
	}else {
		action = "UPDATE_CD";
	}
	
	
	
	
%>
    
	<link rel="stylesheet" type="text/css" href="style.css">
    <style type="text/css">
<!--
.style1 {
	color: #999999;
	font-size: 14px;
	font-weight: bold;
}
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
<form action="" method="post">
</form></table>
<div align="center"></div>
<div align="center"></div>

<div align="left" >
<p></p>
<p class="left"><img src="images/e01.gif" width="14" height="16" alt="" border="0" align="absmiddle">&nbsp;&nbsp;BẠN ĐỌC</p>
<p class="left"><img src="images/dot_o.gif" width="5" height="5" alt="" border="0">&nbsp;&nbsp;<a href="index.jsp">Tìm kiếm </a></p>
<p class="left"><img src="images/dot_o.gif" width="5" height="5" alt="" border="0">&nbsp;&nbsp;<a href="request.jsp">Đặt sách </a></p>
<p class="left">&nbsp;</p>
</div>

<hr>


<div align="left" >
<p class="left"><img src="images/e01.gif" width="14" height="16" alt="" border="0" align="absmiddle">&nbsp;ACTION</p>
<p class="left"><img src="images/dot_o.gif" width="5" height="5" alt="" border="0">&nbsp;&nbsp;<a href="checkOut.jsp">Cho mượn tài nguyên </a></p>
<p class="left"><img src="images/dot_o.gif" width="5" height="5" alt="" border="0">&nbsp;&nbsp;<a href="checkIn.jsp">Trả tài nguyên </a></p>
</div>

<hr />


<div align="left" >
<p class="left"><img src="images/e01.gif" width="14" height="16" alt="" border="0" align="absmiddle">&nbsp;MANAGER</p>
<p class="left"><img src="images/dot_o.gif" width="5" height="5" alt="" border="0">&nbsp;&nbsp;<a href="">Thêm sách </a></p>
<p class="left"><img src="images/dot_o.gif" width="5" height="5" alt="" border="0">&nbsp;&nbsp;<a href="">Thêm CD  </a></p>

<p class="left"><img src="images/dot_o.gif" width="5" height="5" alt="" border="0">&nbsp;&nbsp;<a href="deleteResource.jsp">Xoá tài liệu </a></p>
<p></p>
<p class="left"><img src="images/dot_o.gif" width="5" height="5" alt="" border="0">&nbsp;&nbsp;<a href="editPatron.jsp">Thêm bạn đọc </a></p>
<p class="left"><img src="images/dot_o.gif" width="5" height="5" alt="" border="0">&nbsp;&nbsp;<a href="updatePatron.jsp">Cập nhật bạn đọc </a></p>
<p class="left"><img src="images/dot_o.gif" width="5" height="5" alt="" border="0">&nbsp;&nbsp;<a href="deletePatron.jsp">Xoá bạn đọc </a></p>
</div>


<br><br>	</td>
	
	<td rowspan="2">
<div align="center"><img src="images/top01.gif" width="597" height="24" alt="" border="0"></div>

<div align="center">
  <table width="100%" border="0">
  <tr>
    <td width="6%" height="43">&nbsp;</td>
    <td width="66%">
        <p align="center">
        	..:: Home &gt; Thêm CD vào thư viện::..        </p>
     </td>
    <td width="28%">
    	<p align="right"><a href="login.jsp">  <%= logedIn %>  </a>|<a href="login.jsp">  Logout  </a></p>
    </td>
  </tr>
</table>
</div>

<table border="0" cellpadding="0" cellspacing="0" width="100%">
<tr valign="top">
	<td width="45%">
    <div>
      <p class="style1">Thông tin CD : </p>
    </div>
    <form action="controler" method="get">
      <table width="95%" border="0" align="center">
        <tr>
          <td width="39%"><p><strong>Mã CD : </strong></p></td>
          <td width="45%"><input name="cd.id" type="text" id="cd.id" size="35"></td>
          <td width="16%">&nbsp;</td>
        </tr>
        <tr>
          <td><p><strong>Tên CD : </strong></p></td>
          <td><input name="cd.name" type="text" id="cd.name" size="35"></td>
          <td>&nbsp;</td>
        </tr>
        <tr>
          <td><p><strong>Thuộc lĩnh vực : </strong></p></td>
          <td><input name="cd.domain" type="text" id="cd.domain" size="35"></td>
          <td>&nbsp;</td>
        </tr>
        <tr>
          <td><p><strong>Tác giả : </strong></p></td>
          <td><input name="cd.authors" type="text" id="cd.authors" size="35"></td>
          <td>&nbsp;</td>
        </tr>
        <tr>
          <td><p><strong>Dung lượng : </strong></p></td>
          <td><input name="cd.size" type="text" id="cd.size" size="35"></td>
          <td>&nbsp;</td>
        </tr>
        <tr>
          <td><p><strong>Thời gian : </strong></p></td>
          <td><input name="cd.time" type="text" id="cd.time" size="35"></td>
          <td>&nbsp;</td>
        </tr>
        <tr>
          <td><p><strong>Định dạng : </strong></p></td>
          <td><input name="cd.kind" type="text" id="cd.kind" size="35"></td>
          <td>&nbsp;</td>
        </tr>
        <tr>
          <td><p><strong>Số lượng trong thư viện : </strong></p></td>
          <td><input name="cd.amount" type="text" id="cd.amount" size="35"></td>
          <td>&nbsp;</td>
        </tr>
        <tr>
          <td valign="top"><p><strong>Miêu tả về CD : </strong></p></td>
          <td><textarea name="cd.description" id="cd.description" cols="35" rows="5"></textarea></td>
          <td>&nbsp;</td>
        </tr>
        <tr>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
        </tr>
        <tr>
          <td>&nbsp;</td>
          <td><div align="center">
          	<input name="action" type="hidden" value="<%= action %>" />
            <input name="cd.submit" type="submit" id="cd.submit" value="Save">
            <input name="cd.reset" type="reset" id="cd.reset">
          </div></td>
          <td>&nbsp;</td>
        </tr>
      </table>
    </form>
</td>
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
