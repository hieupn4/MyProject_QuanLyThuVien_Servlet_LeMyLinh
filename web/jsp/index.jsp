<%@ page contentType="text/html; charset=utf-8" language="java" import="java.sql.*" errorPage="" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@page import="java.util.List"%>
<%@page import="manager.resource.Resource"%>
<%@page import="java.util.ArrayList"%>
<%@page import="manager.resource.BookResource"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>Home - Lib manager Sys</title>

<%
	String logedIn = (String) session.getAttribute("login.done") ;
	String roles = (String) session.getAttribute("patron.roles");
	//logedIn = "nttuyen";
	
	List<Resource> list = (List<Resource>)session.getAttribute("search_result");
	if(list == null){
		list = new ArrayList<Resource>();
	}
	int size = list.size();
	int pageNumber = size / 10;
	int currentPage = 0;
	try{
		currentPage = Integer.parseInt(request.getParameter("page"));
	}catch(Exception e){
		currentPage = 0;
	}
	Resource resource = new BookResource();
%>

        
	<link rel="stylesheet" type="text/css" href="style.css" />
    <style type="text/css">
<!--
.style1 {
	font-size: 12px;
	font-weight: bold;
}
.style2 {
	color: #999999
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
<!-- /but act -->	</td>
	<td>
<!-- but -->
<table border="0" cellpadding="0" cellspacing="0">
<tr valign="bottom">
	<td><img src="images/b_left.gif" alt="" width="10" height="30" border="0"></td>
	<td background="images/b_fon.gif"><p class="menu01"><a href="">&nbsp;&nbsp;&nbsp;&nbsp;BOOK&nbsp;&nbsp;&nbsp;&nbsp;</a></p></td>
	<td><img src="images/b_right.gif" alt="" width="10" height="30" border="0"></td>
</tr>
</table>
<!-- /but -->	</td>
	<td>
<!-- but -->
<table border="0" cellpadding="0" cellspacing="0">
<tr valign="bottom">
	<td><img src="images/b_left.gif" alt="" width="10" height="30" border="0"></td>
	<td background="images/b_fon.gif"><p class="menu01"><a href="">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;CD&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</a></p></td>
	<td><img src="images/b_right.gif" alt="" width="10" height="30" border="0"></td>
</tr>
</table>
<!-- /but -->	</td>
	<td>
<!-- but -->
<table border="0" cellpadding="0" cellspacing="0">
<tr valign="bottom">
	<td><img src="images/b_left.gif" alt="" width="10" height="30" border="0"></td>
	<td background="images/b_fon.gif"><p class="menu01"><a href="">&nbsp;REQUEST&nbsp;</a></p></td>
	<td><img src="images/b_right.gif" alt="" width="10" height="30" border="0"></td>
</tr>
</table>
<!-- /but -->	</td>
	<td>
<!-- but -->
<table border="0" cellpadding="0" cellspacing="0">
<tr valign="bottom">
	<td><img src="images/b_left.gif" alt="" width="10" height="30" border="0"></td>
	<td background="images/b_fon.gif"><p class="menu01"><a href="">SUPPORT</a></p></td>
	<td><img src="images/b_right.gif" alt="" width="10" height="30" border="0"></td>
</tr>
</table>
<!-- /but -->	</td>
	<td>
<!-- but -->
<table border="0" cellpadding="0" cellspacing="0">
<tr valign="bottom">
	<td><img src="images/b_left.gif" alt="" width="10" height="30" border="0"></td>
	<td background="images/b_fon.gif"><p class="menu01"><a href="">CONTACT</a></p></td>
	<td><img src="images/b_right.gif" alt="" width="10" height="30" border="0"></td>
</tr>
</table>
<!-- /but -->	</td>
</tr>
</table>	</td>
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


<!--Neu chua dang nhap thi khong duoc lua chon dat sach-->
<% if(logedIn != null){ %>
	<p class="left"><img src="images/dot_o.gif" width="5" height="5" alt="" border="0">&nbsp;&nbsp;<a href="request.jsp">Đặt sách </a></p>
<%}%>


<p class="left">&nbsp;</p>
</div>

<hr>

<!---Khu vuc manager
	Neu khong fai la nhan vien thu vien
    thi muc menu nay bi loai bo
--->
<%if("PATRON_LIB_MANAGER".equals(roles)){
%>



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
<%	
} %>

<br><br>	</td>
	
	<td rowspan="2">
<div align="center"><img src="images/top01.gif" width="597" height="24" alt="" border="0"></div>

<div align="center">
  <table width="100%" border="0">
  <tr>
    <td width="6%">&nbsp;</td>
    <td width="67%">
        <p align="center">
        	..:: Home &gt; Tìm kiếm ::..        </p>
     </td>
    <td width="27%">
    <%
	if( logedIn == null ){
	%>
    	<p align="right">&nbsp;&nbsp; Guest &nbsp;&nbsp; |&nbsp;&nbsp;  <a href="login.jsp">Login</a>  &nbsp;&nbsp;</p>
    <%
	}else {
	%>
		<p align="right"> <a href=""><%= logedIn %></a>  |  <a href="controler?action=LOGOUT">Logout</a>  &nbsp;</p>
	<%
	}
	%>
    </td>
  </tr>
</table>
</div>

<table border="0" cellpadding="0" cellspacing="0" width="100%">
<tr valign="top">
	<td width="45%">
<form name="formSearch" method="get" action="controler">
  <table width="90%" border="0" align="center">
    <tr>
      <td width="9%">&nbsp;</td>
      <td width="26%"><div align="right">
        <input type="radio" name="search.type" id="radio" value="RESOURCE_BOOK" checked="true">
        Sách</div></td>
      <td width="7%">&nbsp;</td>
      <td width="38%"><input type="radio" name="search.type" id="radio" value="RESOURCE_CD">
        CD</td>
      <td width="20%">&nbsp;</td>
    </tr>
    <tr>
      <td>&nbsp;</td>
      <td colspan="3"><div align="right">
        <input name="searchKey" type="text" id="searchKey" value="Thông tin tìm kiếm" size="50">
      </div></td>
      <td><div align="left">
        <input name="action" type="hidden" value="SEARCH_RESOURCE" />
        <input name="search.Submit" type="submit" id="search.submit" value="Tìm kiếm">
      </div></td>
    </tr>
    <tr>
      <td>&nbsp;</td>
      <td colspan="3">&nbsp;</td>
      <td>&nbsp;</td>
    </tr>
  </table>
</form>

<div><p class="style1 style2">Kết quả tìm kiếm : </p>
</div>



<%
if(size == 0 ){
	out.println("<p>Không có kết quả tìm kiếm</p>");
	for(int k = 0 ; k < 10; k++){
		out.println("<p></p>");
	}
}else{
	int i = 0;
	while(i < 10 && i + currentPage*10 < size ){
		resource = list.get(i + currentPage*10);
		i++;
%>
		

<table width="95%" border="0">
  <tr>
    <td width="36%"><p><strong> Mã tài liệu : </strong></p></td>
    <td width="64%"><p><%=resource.getID() %></p></td>
  </tr>
  <tr>
    <td><p><strong> Tên tài liệu : </strong></p></td>
    <td><p> <%=resource.getName() %> </p></td>
  </tr>
  <tr>
    <td><p><strong> Thể loại : </strong></p></td>
    <td><p> <%=resource.getDomain() %></p></td>
  </tr>
  <tr>
    <td><p><strong> Số lượng có trong thư viện : </strong></p> </td>
    <td><p> <%=resource.getAmount() %> </p></td>
  </tr>
  <tr>
    <td><p><strong> Tác giả : </strong></p></td>
    <td><p><%=resource.getAuthors() %></p></td>
  </tr>
  <tr valign="top">
    <td><p><strong> Miêu tả : </strong></p></td>
    <td><p>
    	<%=resource.getDescription() %>
    </p></td>
  </tr>
  <tr>
    <td>&nbsp;</td>
    <td><p align="right" class="right"><a href="controler?action=VIEW_RESOURCE&id=<%=resource.getID() %>">Read More</a></p></td>
  </tr>
</table>

<%
	
	}
} 
%>
<p align="right" class="right">
<%
for(int j = 0; j <= pageNumber ; j++){
%>
<a href="index.jsp?page=<%=j %>"> <%=(j+1) %>&nbsp; </a>
<%
}
%>
</p>

<!-- 
<table width="95%" border="0">
  <tr>
    <td width="36%"><p><strong> Mã tài liệu : </strong></p></td>
    <td width="64%"><p>S101</p></td>
  </tr>
  <tr>
    <td><p><strong> Tên tài liệu : </strong></p></td>
    <td><p> Core java </p></td>
  </tr>
  <tr>
    <td><p><strong> Thể loại : </strong></p></td>
    <td><p> Công nghệ thông tin</p></td>
  </tr>
  <tr>
    <td><p><strong> Số lượng có trong thư viện : </strong></p> </td>
    <td><p> 5 </p></td>
  </tr>
  <tr>
    <td><p><strong> Tác giả : </strong></p></td>
    <td><p>Nguyễn Thế Tuyến</p></td>
  </tr>
  <tr valign="top">
    <td><p><strong> Miêu tả : </strong></p></td>
    <td><p>
    	Cuốn sách cơ bản về java
        <br>
        Cuốn sách là những API cơ bản của java. Cuốn sách rất hữu ích cho những ai bắt đầu học java
    </p></td>
  </tr>
  <tr>
    <td>&nbsp;</td>
    <td><p align="right" class="right"><a href="">Read More</a></p></td>
  </tr>
</table>
<p class="right">&nbsp;</p>
<p class="right">&nbsp;</p>	

 -->



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
