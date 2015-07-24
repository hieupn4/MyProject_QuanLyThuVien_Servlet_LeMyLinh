<%@ page contentType="text/html; charset=utf-8" language="java" import="java.sql.*" errorPage="" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>request - lib manager sys</title>
    
    
<%
	String logedIn = (String) session.getAttribute("login.done") ;
	String roles = (String) session.getAttribute("patron.roles");
	//logedIn = "nttuyen";
	if(logedIn == null){
		response.sendRedirect("login.jsp");
	}
	
	String messageErr = (String) request.getAttribute("messageErr");
	if(messageErr == null){
		messageErr = "";
	}
	
%>
    
    
    
	<link rel="stylesheet" type="text/css" href="style.css">
    <style type="text/css">
<!--
.style1 {
	font-size: 14px;
	font-weight: bold;
	color: #999999;
}
.style2 {color: #FF0000}
-->
    </style>
    
    <!--<style type="text/css">@import url(jscalendar/calendar-win2k-1.css);</style> -->
 
    <link rel="stylesheet" type="text/css" media="all" href="jscalendar/skins/aqua/theme.css" title="Aqua" />
    <link rel="alternate stylesheet" type="text/css" media="all" href="jscalendar/calendar-blue.css" title="winter" />
    <link rel="alternate stylesheet" type="text/css" media="all" href="jscalendar/calendar-blue2.css" title="blue" />
    <link rel="alternate stylesheet" type="text/css" media="all" href="jscalendar/calendar-brown.css" title="summer" />
    <link rel="alternate stylesheet" type="text/css" media="all" href="jscalendar/calendar-green.css" title="green" />
    <link rel="alternate stylesheet" type="text/css" media="all" href="jscalendar/calendar-win2k-1.css" title="win2k-1" />
    <link rel="alternate stylesheet" type="text/css" media="all" href="jscalendar/calendar-win2k-2.css" title="win2k-2" />
    <link rel="alternate stylesheet" type="text/css" media="all" href="jscalendar/calendar-win2k-cold-1.css" title="win2k-cold-1" />
    
    <link rel="alternate stylesheet" type="text/css" media="all" href="jscalendar/calendar-win2k-cold-2.css" title="win2k-cold-2" />
    <link rel="alternate stylesheet" type="text/css" media="all" href="jscalendar/calendar-system.css" title="system" />
 
    
	<script type="text/javascript" src="jscalendar/calendar.js"></script>
    <script type="text/javascript" src="jscalendar/lang/calendar-en.js"></script>
    <script type="text/javascript" src="jscalendar/calendar-setup.js"></script>
    
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
	<td width="673" background="images/fon_top.gif"><table border="0" cellpadding="0" cellspacing="0" background="">
      <tr valign="bottom">
        <td><!-- but act -->
            <table border="0" cellpadding="0" cellspacing="0">
              <tr valign="bottom">
                <td><img src="images/b_left_a.gif" width="9" height="37" alt="" border="0"></td>
                <td background="images/b_fon_a.gif"><p class="menu01"><a href="">HOME</a></p></td>
                <td><img src="images/b_right_a.gif" width="9" height="37" alt="" border="0"></td>
              </tr>
            </table>
          <!-- /but act -->        </td>
        <td><!-- but -->
            <table border="0" cellpadding="0" cellspacing="0">
              <tr valign="bottom">
                <td><img src="images/b_left.gif" alt="" width="10" height="30" border="0"></td>
                <td background="images/b_fon.gif"><p class="menu01"><a href="">&nbsp;&nbsp;&nbsp;&nbsp;BOOK&nbsp;&nbsp;&nbsp;&nbsp;</a></p></td>
                <td><img src="images/b_right.gif" alt="" width="10" height="30" border="0"></td>
              </tr>
            </table>
          <!-- /but -->        </td>
        <td><!-- but -->
            <table border="0" cellpadding="0" cellspacing="0">
              <tr valign="bottom">
                <td><img src="images/b_left.gif" alt="" width="10" height="30" border="0"></td>
                <td background="images/b_fon.gif"><p class="menu01"><a href="">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;CD&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</a></p></td>
                <td><img src="images/b_right.gif" alt="" width="10" height="30" border="0"></td>
              </tr>
            </table>
          <!-- /but -->        </td>
        <td><!-- but -->
            <table border="0" cellpadding="0" cellspacing="0">
              <tr valign="bottom">
                <td><img src="images/b_left.gif" alt="" width="10" height="30" border="0"></td>
                <td background="images/b_fon.gif"><p class="menu01"><a href="">&nbsp;REQUEST&nbsp;</a></p></td>
                <td><img src="images/b_right.gif" alt="" width="10" height="30" border="0"></td>
              </tr>
            </table>
          <!-- /but -->        </td>
        <td><!-- but -->
            <table border="0" cellpadding="0" cellspacing="0">
              <tr valign="bottom">
                <td><img src="images/b_left.gif" alt="" width="10" height="30" border="0"></td>
                <td background="images/b_fon.gif"><p class="menu01"><a href="">SUPPORT</a></p></td>
                <td><img src="images/b_right.gif" alt="" width="10" height="30" border="0"></td>
              </tr>
            </table>
          <!-- /but -->        </td>
        <td><!-- but -->
            <table border="0" cellpadding="0" cellspacing="0">
              <tr valign="bottom">
                <td><img src="images/b_left.gif" alt="" width="10" height="30" border="0"></td>
                <td background="images/b_fon.gif"><p class="menu01"><a href="">CONTACT</a></p></td>
                <td><img src="images/b_right.gif" alt="" width="10" height="30" border="0"></td>
              </tr>
            </table>
          <!-- /but -->        </td>
      </tr>
    </table></td>
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
<p class="left"><img src="images/dot_o.gif" width="5" height="5" alt="" border="0">&nbsp;&nbsp;<a href="request.jsp">Đặt sách </a></p>
<p class="left">&nbsp;</p>
</div>

<hr>


<!--Khu vực menu của nhân viên thư viện-->
<% if("PATRON_LIB_MANAGER".equals(roles)){ %>


<div align="left" >
<p class="left"><img src="images/e01.gif" width="14" height="16" alt="" border="0" align="absmiddle">&nbsp;ACTION</p>
<p class="left"><img src="images/dot_o.gif" width="5" height="5" alt="" border="0">&nbsp;&nbsp;<a href="checkOut.jsp">Cho mượn tài nguyên </a></p>
<p class="left"><img src="images/dot_o.gif" width="5" height="5" alt="" border="0">&nbsp;&nbsp;<a href="checkIn.jsp">Trả tài nguyên </a></p>
</div>

<hr />


<div align="left" >
<p class="left"><img src="images/e01.gif" width="14" height="16" alt="" border="0" align="absmiddle">&nbsp;MANAGER</p>
<p class="left"><img src="images/dot_o.gif" width="5" height="5" alt="" border="0">&nbsp;&nbsp;<a href="editBook.jsp">Thêm sách </a></p>
<p class="left"><img src="images/dot_o.gif" width="5" height="5" alt="" border="0">&nbsp;&nbsp;<a href="editCD.jsp">Thêm CD  </a></p>

<p class="left"><img src="images/dot_o.gif" width="5" height="5" alt="" border="0">&nbsp;&nbsp;<a href="deleteResource.jsp">Xoá tài liệu </a></p>
<p></p>
<p class="left"><img src="images/dot_o.gif" width="5" height="5" alt="" border="0">&nbsp;&nbsp;<a href="editPatron.jsp">Thêm bạn đọc </a></p>
<p class="left"><img src="images/dot_o.gif" width="5" height="5" alt="" border="0">&nbsp;&nbsp;<a href="updatePatron.jsp">Cập nhật bạn đọc </a></p>
<p class="left"><img src="images/dot_o.gif" width="5" height="5" alt="" border="0">&nbsp;&nbsp;<a href="deletePatron.jsp">Xoá bạn đọc </a></p>
</div>
<% } %>

<br><br>	</td>
	
	<td rowspan="2">
<div align="center"><img src="images/top01.gif" width="597" height="24" alt="" border="0"></div>

<div align="center">
  <table width="100%" border="0">
  <tr>
    <td width="6%" height="30">&nbsp;</td>
    <td width="66%">
        <p align="center">
        	..:: Home &gt; Đặt sách::..        </p>
     </td>
    <td width="28%">
    	<p align="right"><a href="login.jsp">  <%= logedIn %>  </a> |<a href="login.jsp">  Logout  </a></p>
    </td>
  </tr>
</table>
</div>

<table border="0" cellpadding="0" cellspacing="0" width="100%">
<tr valign="top">
	<td width="45%">
<div><p class="style1">Thông tin đặt sách : </p>
</div>

<form name="formRequest" method="post" action="controler">
  <table width="95%" border="0" align="center">
    <tr>
      <td width="37%">&nbsp;</td>
      <td width="63%"><p class="style2"><%= messageErr %></p></td>
    </tr>
    <tr>
      <td><p>Tên đăng nhập của người đặt : </p></td>
      <td><input name="request.userName" type="text" id="request.userName" size="35" value="<%=logedIn %>"></td>
    </tr>
    <tr>
      <td><p>Mã của cuốn sách đặt : </p></td>
      <td><input name="request.resourceID" type="text" id="request.resourceID" size="35"></td>
    </tr>
    <tr>
      <td><p>Ngày mượn : </p></td>
      <td><input name="request.date" type="text" id="request.date" size="35"></td>
    </tr>
    <tr>
      <td>&nbsp;</td>
      <td><div align="center">
      	<input name="action" type="hidden" value="REQUEST"  />
        <input name="request.submit" type="submit" id="request.submit">
        <input name="request.reset" type="reset" id="request.reset" >
      </div></td>
    </tr>
  </table>
</form>

<script type="text/javascript">
	Calendar.setup(
		{
			inputField : "request.date", // ID of the input field
			ifFormat : "%d/%m/%Y", // the date format
			button : "request.date" // ID of the button
		}
	);
</script>



<p>&nbsp;</p>
<p>&nbsp;</p>
<p class="right"></p>	</td>
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
