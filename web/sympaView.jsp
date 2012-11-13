
<%@ page import="java.util.Calendar"%>
<%@ page import="com.synapse.scada.config.SystemConfig"%>
<%@ page import="com.synapse.scada.config.Area"%>
<%@ page import="com.synapse.scada.config.SubArea"%>
<%@ page import="com.synapse.scada.config.Unit"%>

<jsp:include page="header.jsp" />

<%
	SystemConfig cfg = (SystemConfig) request.getAttribute("config");
	Long rtstamp = (Long) request.getAttribute("tstamp");
	String err = (String) request.getAttribute("error");
%>

<%if(err!=null) { %>
	Server status: <%=err %>
<%} %>
<BR>
<%
	if (cfg != null) {
		for (Area area : cfg.getArea()) {
%>
<b>Area name:
<%=area.getName()%>
</b>
<BR>
<%
	for (SubArea subArea : area.getSubArea()) {
%>
<table border width="600">
	<tr>
		<td bgcolor="navy" colspan="3"><font color="white"><b>Sub-area
					name: <%=subArea.getName()%></b></font></td>
	</tr>
	<tr>
		<td width="250">Unit name</td>
		<td>State</td>
		<td>Debug info</td>
	</tr>
	<%
		for (Unit unit : subArea.getUnit()) {
						String name = unit.getName();
						String state = unit.getState() == 0 ? "OFF" : "ON";
						String path;
						if (state.equals("OFF")) {
							path = "<img src=\"img/dark.png\" height=\"50\" width=\"50\">";
						} else {
							path = "<img src=\"img/bright.png\" height=\"50\" width=\"50\">";
						}
	%>
	<tr>
		<td><%=name%></td>
		<td><%=path%></td>
		<td><%=unit.toString()%></td>
	</tr>
	<%
		}
				}
			}
	%>

	</tr>
</table>

<%
	} else {
%>
Server is down.
<%
	}
%>

<BR>
<%
	long timestamp = Calendar.getInstance().getTimeInMillis();
%>
Loading time:
<%=timestamp - rtstamp%>
ms
<jsp:include page="footer.jsp" />














