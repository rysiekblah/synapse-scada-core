<%@ page import="com.ees.synapse.oldconfig.Sympa,java.util.List,com.ees.synapse.oldconfig.Device,com.ees.synapse.oldconfig.Unit" %>


<html>
<head>
<script type="text/javascript">
function updateState(newState, theElement, theUnit, txtNum)
{
    var oForm=document.getElementById("formula2");
    var el=document.getElementById(txtNum);
    el.value = newState;
    alert("What: " + txtNum);

    var txt = oForm.toSend;
    txt.value=newState+":"+theElement+":"+theUnit;
    txt.name="deviceState";

    oForm.submit();    

}
</script>
</head>

<body>
<jsp:include page="header.jsp" />

<%
   int n=1;
   String nmz = "state1"; 
   Sympa sympa = (Sympa)request.getAttribute("sympa");
   String choose = (String)request.getAttribute("fromForm");
   String stan = (String)request.getAttribute("state");
   String element = (String)request.getAttribute("element");
   String unit = (String) request.getAttribute("unit");
   String tim = (String) request.getAttribute("tStamp");
   String date = (String)request.getAttribute("clientDate");

   if(sympa!=null){
       List<Device> devices = sympa.getDevice();
       int rozmiar = devices.size();

       for(int i=0; i<rozmiar; i++){
%>
    	  <p><%=" name :" + devices.get(i).getName()%></p>
       	  <p><%="   ip :" + devices.get(i).getIp()%></p>
       	  <p><%=" mask : " + devices.get(i).getMask()%></p>
<%
       }
   } else {
      out.print("There is null sympa node\n");
   }
%>

<p><%="Timestamp= "+tim%></p>
<p><%="ClientTimestamp= "+date%></p>


<textarea readonly id="element1" rows="1" cols="4">"dupa"</textarea>
<textarea readonly id="unit1" rows="1" cols="6"><%=unit%></textarea>
<textarea readonly id="state1" rows="1" cols="6"><%=stan%></textarea>
<button id="stateButtON" type="button" value="on" onclick="updateState(this.value, element1.value, unit1.value, 1)">ON</button>
<button id="stateButtOFF" type="button" value="off" onclick="updateState(this.value, element1.value, unit1.value, 1)">OFF</button>

<%!
private String vomitSystemStructure(Sympa theConfiguration, String stan){
        StringBuffer buff = new StringBuffer();
	List<Device> devices = theConfiguration.getDevice();
	List<Unit> units;
        String theUnitState;
	for(int i=0; i<devices.size(); i++){
		units = devices.get(i).getUnit();
                buff.append(devices.get(i).getName() + "<br/>");
                
                buff.append("<ul>");
                for(int j=0; j<units.size(); j++){
                        theUnitState=units.get(j).getName() + "state" + String.valueOf(j);
                        buff.append("<li>");
                        
                        buff.append("" + units.get(j).getName());
                        buff.append("<textarea readonly id=\"" + theUnitState + "\" rows=\"1\" cols=\"6\">");
                        buff.append(stan + "</textarea>");
                        buff.append("<button id=\"stateButtON\" type=\"button\" value=\"on\" ");
                        buff.append("onclick=\"updateState(this.value, element1.value, unit1.value,\""+ theUnitState +"\")\">ON</button>");
                        buff.append("<button id=\"stateButtOFF\" type=\"button\" value=\"off\" ");
                        buff.append("onclick=\"updateState(this.value, element1.value, unit1.value,\""+ theUnitState +"\")\">OFF</button>");

                        buff.append("</li>");
                }                
                buff.append("</ul>");
	}

        return buff.toString();
}
%>



<form id="formula2" method="POST" action="ChoseItem.do">
    <input id="toSend" type="hidden">
</form>

<%
	Sympa confSympa = (Sympa)request.getAttribute("sympa");
%>

<%=vomitSystemStructure(confSympa, stan)%>


<jsp:include page="footer.jsp" />
</body>
</html>

