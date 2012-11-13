<%@ page import="com.ees.synapse.oldconfig.Sympa,java.util.List,com.ees.synapse.oldconfig.Device" %>


<html>
<head>
<script type="text/javascript">
function updateState(newState, theElement, theUnit)
{
    var oForm=document.getElementById("formula2");
    var el=document.getElementById("state1");
    el.value = newState;

    var txt = oForm.toSend;
    txt.value=newState+":"+theElement+":"+theUnit;
    txt.name="deviceState";

    oForm.submit();    

}
</script>
</head>

<body>
<jsp:include page="header.jsp" />

<%@ Sympa sympa; %>

<%
   int n=1;
   String nmz = "state1"; 
   sympa = (Sympa)request.getAttribute("sympa");
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


<textarea readonly id="element1" rows="1" cols="4"><%=element%></textarea>
<textarea readonly id="unit1" rows="1" cols="6"><%=unit%></textarea>
<textarea readonly id="state1" rows="1" cols="6"><%=stan%></textarea>
<button id="stateButtON" type="button" value="on" onclick="updateState(this.value, element1.value, unit1.value)">ON</button>
<button id="stateButtOFF" type="button" value="off" onclick="updateState(this.value, element1.value, unit1.value)">OFF</button>



<form id="formula2" method="POST" action="ChoseItem.do">
    <input id="toSend" type="hidden">
</form>

<jsp:include page="footer.jsp" />
</body>
</html>

