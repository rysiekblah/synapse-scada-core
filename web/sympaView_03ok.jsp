
<%@ page import="java.util.List" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="java.util.Map" %>
<%@ page import="com.ees.synapse.oldconfig.Sympa,java.util.List,java.util.ArrayList,com.ees.synapse.oldconfig.Device, java.util.Calendar" %>



<script type="text/javascript">
<jsp:include page="header.jsp" />

<%
   	Map<String, String> stan = (Map)request.getAttribute("fromForm");
	Sympa cfg = (Sympa)request.getAttribute("config");
	Long rtstamp = (Long)request.getAttribute("tstamp");
	String pathLightG;
	String pathLightK;	
	List<Device> devs = new ArrayList<Device>(); 
%>


<%if(cfg != null && stan != null) {%>

<table border width="600">
<tr>
	<td>Element</td> <td>State</td> <td>Debug info</td>
</tr>
	<%
	devs = cfg.getDevice();
	for(int i=0; i<devs.size(); i++) {
		String name = devs.get(i).getName();
		String state = stan.get(name);
		String path;
		if(state.equals("OFF")){
			path = "<img src=\"img/red_light.png\" height=\"50\" width=\"50\">";
		} else  {
			path = "<img src=\"img/green_light.png\" height=\"50\" width=\"50\">";
		}
	%>
		<tr><td><%=name%></td>	<td> <%=path%> </td> <td> <%=state%> </td></tr> 
	<%
	}
	%>
	
</tr>
</table>
	
	
	<%
	String garaz = stan.get("Garage"); 
	if(garaz.equals("OFF")){
		pathLightG = "<img src=\"img/red_light.png\" height=\"50\" width=\"50\">";
	} else {
		pathLightG = "<img src=\"img/green_light.png\" height=\"50\" width=\"50\">";
	}

	String kuchnia = stan.get("Kitchen"); 
	if(kuchnia.equals("OFF")){
		pathLightK = "<img src=\"img/red_light.png\" height=\"50\" width=\"50\">";
	} else {
		pathLightK = "<img src=\"img/green_light.png\" height=\"50\" width=\"50\">";
	}

	%>
	

<table border width="600">
<tr>
	<td>Element</td> <td>State</td> <td>Debug info</td>
</tr>
<tr>
	<td>Garage</td>	<td> <%=pathLightG.toString()%> </td> <td> <%=stan.get("Garage")%> </td> 
</tr>
	<td>Kitchen</td> <td> <%=pathLightK.toString()%> </td> <td> <%=stan.get("Kitchen")%> </td> 
<tr>
</tr>
</table>

<%} else {%>
	server is down
<%}%>

<BR>
<%
	long timestamp = Calendar.getInstance().getTimeInMillis();
%>
Loading time: <%=timestamp-rtstamp%> ms
<jsp:include page="footer.jsp" />














