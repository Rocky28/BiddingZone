<%@page contentType="text/html" pageEncoding="UTF-8" session="true"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Grade A</title>
        <link rel="stylesheet" type="text/css" href="Grade.css"><% HttpSession s=request.getSession(true); %>
    </head>
    <body>
        <div id="head"><%int id=1;s.setAttribute("id",id);s.setAttribute("Sold","Y");%>
            <img src="TUCC-Logo_static.gif" width="10%"><p id="tname">Mnpl 2.0</p> 
        </div>
        
            <a  id="grA" href="GradeApl.jsp">Lets Start The Bidding of Grade A players</a>
        
    </body>
</html>
