<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Grade D</title>
        <link rel="stylesheet" type="text/css" href="Grade.css"><% HttpSession s=request.getSession(true); %>
    </head>
    <body>
        <div id="head"><%int id=21;s.setAttribute("id",id);%>
            <img src="TUCC-Logo_static.gif" width="10%"><p id="tname">Mnpl 2.0</p> 
        </div>
        
            <a  id="grA" href="GradeDpl.jsp">Lets Start The Bidding of Grade D players</a>
        
    </body>
</html>
