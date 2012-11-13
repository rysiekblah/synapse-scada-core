<%@ page import="javax.management.*,java.util.*" %>
<%
	try{
		ArrayList servers = MBeanServerFactory.findMBeanServer(null);
		if(null == servers)
			throw new Exception("No MBeanServer found.");
			
		MBeanServer server = (MBeanServer)servers.get(0);
		
		ObjectName objName = new ObjectName("DefaultDomain:service=Logging,type=File");
		
		String newValue = (String)request.getParameter("LogName");
		
		if(newValue != null && newValue.length() > 0) {
			Attribute attr = new Attribute("LogName", newValue);
			server.setAttribute(objName, attr);
		}
		
		String value = (String)server.getAttribute(objName, "LogName");
%>

MBean <%= objName.getCanonicalName() %>

<FORM METHOD="post" ACTION="mbeaninfo.jsp">
</FORM>
			