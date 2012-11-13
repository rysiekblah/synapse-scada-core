<%@ page import="com.ees.synapse.oldconfig.Sympa" %>

<!--method="POST"	action="ChoseItem.do"-->

<html>
<head>
<script type="text/javascript">
function loadXMLDoc()
{
    var xmlhttp;
    
    if (window.XMLHttpRequest)
    {// code for IE7+, Firefox, Chrome, Opera, Safari
        xmlhttp=new XMLHttpRequest();
    }
    else
    {// code for IE6, IE5
        xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
    }
    
    xmlhttp.onreadystatechange=function()
    {
        if (xmlhttp.readyState==4 && xmlhttp.status==200)
        {
            document.getElementById("myDiv").innerHTML=xmlhttp.responseText;
        }
    }
    
    xmlhttp.open("POST","ChoseItem.do",true);
    xmlhttp.send("deviceState");
}

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

function updateResponse()
{
    //var st=document.getElementById("toSend");
    //var el=document.getElementById("element1");
    //st="off:345:777";
}
</script>
</head>

<body>
<jsp:include page="header.jsp" />

<% String choose = (String)request.getAttribute("fromForm");
   String stan = (String)request.getAttribute("state");
   String element = (String)request.getAttribute("element");
   String unit = (String) request.getAttribute("unit");
%>

<p>Configuration:</p><br>
<%out.print("state: "+ stan+"\n");%>
<%out.print("element: "+element+"\n");%>
<%out.print("unit: "+unit+"\n");%>

<form id="formula1" method="POST" action="ChoseItem.do">
    <p>Area1_Unit1 is <%out.print(choose + " :: " + stan);%></p><br>
    <P> 
    <LABEL for="firstname">First name:</LABEL>
    <INPUT type="text" id="firstname"><BR>
    
    <LABEL for="lastname">Last name:</LABEL>
    <INPUT type="text" id="lastname"><BR>
    
    <LABEL for="email">email:</LABEL>
    <INPUT type="text" id="email"><BR>
    
    <INPUT type="radio" name="deviceState" value="Male"> Male<BR>
    <INPUT type="radio" name="deviceState" value="Female"> Female<BR>
    <INPUT type="submit" value="Send"> <INPUT type="reset">
    </P>
    Area_1::Unit_1: state[<%out.print(choose);%>] ::  
    <input type="submit" name="deviceState" value="on:124:90">
    <!--input type="submit" name="deviceState" value="offthego"-->
</form>

<textarea readonly id="element1" rows="1" cols="4"><%=element%></textarea>
<textarea readonly id="unit1" rows="1" cols="6"><%=unit%></textarea>
<textarea readonly id="state1" rows="1" cols="6"><%=stan%></textarea>
<button id="stateButtON" type="button" value="on" onclick="updateState(this.value, element1.value, unit1.value)">ON</button>
<button id="stateButtOFF" type="button" value="off" onclick="updateState(this.value, element1.value, unit1.value)">OFF</button>



<form id="formula2" method="POST" action="ChoseItem.do">
    <input id="toSend" type="hidden">
    
    <!--input type="submit" onclick="updateResponse()"-->

</form>

<div id="myDiv"><h2>Let AJAX change this text</h2></div>
<button type="button" onclick="loadXMLDoc()">Change Content</button>

<jsp:include page="footer.jsp" />
</body>
</html>
