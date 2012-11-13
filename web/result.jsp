<%@ page import="java.util.*" %>

<html>
<body>
<jsp:include page="header.jsp"%>

<h1 align="center">Hello world, here is JSP ;-)</h1>


<%
  String choose = (String)request.getAttribute("fromForm");
  String ctrl = (String)request.getAttribute("fromCtrl");
  request.setAttribute("tomek", "TOmek");
  String rqstHtml = (String)request.getParameter("name");
  out.print(rqstHtml + "<br>Your choose for " + choose + " is " + ctrl);
  out.print("<br>" + "Name: " + (String)request.getAttribute("nazwa"));
%>

<% out.print("Malf: " + request.getAttribute("malf")); %>
<% out.print("Null: " + request.getAttribute("nullPtr")); %>

       <form method="POST"	action="ChoseItem.do">
       <p>Device</p><br>
       <select name="deviceName" size="1">
			<option value="main_lamp">Main lamp</option>
			<option value="fridge">Fridge</option>
		</select>
		<br><br>
		<p>State</p><br>
       <select name="deviceState" size="1">
			<option value="on">Turn on</option>
			<option value="off">Turn off</option>
		</select>
		<br><br>
		<center>
			<input type="SUBMIT">
		</center>
		</form>

</body>
</html>
