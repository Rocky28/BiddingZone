<%@page import="java.sql.*"%>
<%@page import="p1.MnplBean"%>
<%@page contentType="text/html" pageEncoding="UTF-8" session="true"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Grade A Players</title>
        <link rel="stylesheet" type="text/css" href="Grade.css"><% HttpSession s=request.getSession(true); %>
    </head>
    <body>
        <div id="head">
            <img src="TUCC-Logo_static.gif" width="10%"><p id="tname">Mnpl 2.0</p> 
        </div>
        <div id="playerstats">
            <% MnplBean mb=new MnplBean(); int str=(Integer)s.getAttribute("id"); try{ResultSet rs =mb.srchIdD(str); if(rs==null){response.sendRedirect("final.jsp");}%>
            <img src="images/gradeD/<% String str1=rs.getString(2); out.print(str1);%>.jpg" width="30%" height="50%">
                       <table><tr><th>Player Name : </th><td><% out.print(str1); %></td></tr>
                       <tr><th>Player Year : </th><td><% out.print(rs.getString(3));%> year</td></tr>
                       <tr><th>Player Batting Hand : </th><td><% String hand=rs.getString(4);if(hand.equals("R")){out.print("Right Handed");}else{out.print("Left Handed");} %></td></tr>
                       <tr><th>Player Bowling Hand : </th><td><% String hand1=rs.getString(5);if(hand1.equals("R")){out.print("Right Handed");}else{out.print("Left Handed");} }catch(Exception e){System.out.println(e);}%></td></tr></table>
                       <form name="frm" action="prev3" method="post"><input type="submit" value="Previous"></form><form name="frm" action="next3" method="post"><input type="submit" value="Next"></form>
             
        </div>
    </body>
</html>
