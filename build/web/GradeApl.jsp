<%@page import="java.sql.*"%>
<%@page import="p1.MnplBean"%>
<%@page contentType="text/html" pageEncoding="UTF-8" session="true"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Grade A Players</title>
        <link rel="stylesheet" type="text/css" href="Grade.css"><% HttpSession s=request.getSession(true); %>
        <script language="javascript">
var max_time = 20;
var cinterval;
 
function countdown_timer(){
  max_time--;
  document.getElementById('timer').innerHTML = max_time;
  if(max_time == 0){
    window.location="/Mnpl2.0/addplayer";
  }
}
cinterval = setInterval('countdown_timer()', 1000);
</script>
    </head>
    <body>
        <div id="head">
            <img src="TUCC-Logo_static.gif" width="10%"><p id="tname">Mnpl 2.0</p> <p id="user">Welcome :  <span id="money">Money left : </span></p>
        </div>
        <div id="playerstats">
            <% MnplBean mb=new MnplBean(); int str=(Integer)s.getAttribute("id"); try{ResultSet rs =mb.srchIdA(str); if(rs==null){response.sendRedirect("GradeB.jsp");}%>
            <img src="images/gradeA/<% String str1=rs.getString(2); out.print(str1);s.setAttribute("info", str1);s.setAttribute("Grade", "A"); %>.jpg">
                       <table><tr><th>Player Name : </th><td><% out.print(str1); %></td></tr>
                       <tr><th>Player Year : </th><td><% out.print(rs.getString(3));%> year</td></tr>
                       <tr><th>Player Batting Hand : </th><td><% String hand=rs.getString(4);if(hand.equals("R")){out.print("Right Handed");}else{out.print("Left Handed");} %></td></tr>
                       <tr><th>Player Bowling Hand : </th><td><% String hand1=rs.getString(5);if(hand1.equals("R")){out.print("Right Handed");}else{out.print("Left Handed");} }catch(Exception e){System.out.println(e);}%></td></tr></table>
        </div>
        <div id="arena">
            <table>
                <tr><th>Bidding starts :</th><td>Rupees 1000</td></tr>
                <tr><th>Default bidding : </th><td>Rupees 200</td></tr>
                <tr><th>Current Highest Bid : </th><td><b>Bidder :</b></td></tr>
                <tr><th>&nbsp;</th><td><b>&nbsp;</b></td></tr>
                <tr><th><form action="bid" method="post"><input type="submit" value="Bid"></form></th><td id="timer">20</td></tr>
            </table>
        </div>
        <div id="owned">
            <p><b>Your Players</b></p>
            <table>
                <tr><td>Player Name</td><td>Price</td></tr><% ResultSet rs=mb.serchMyPlayers((String)s.getAttribute("email")); if(rs.next()){String names=rs.getString(1),tempname="";for(int i=0;i<names.length();i++){if(names.charAt(i)==','){int price=mb.getPrice(tempname);%>
                <tr><td><% out.println(tempname); %></td><td><% out.println(price); %></td></tr><%tempname="";continue;}tempname+=names.charAt(i);}}%>
            </table>
        </div>
        <div id="othersowned">
            <p><b>Other Bidder's Information</b></p>
            <table>
                <tr><td>Bidders Name</td><td>Players Bought</td><td>Remaining amount</td></tr><% rs=mb.getBidderInfo(); while(rs.next()){String name=rs.getString(1);int money=rs.getInt(2);int total=rs.getInt(4);%>
                <tr><td><% out.println(name); %></td><td><% out.println(total); %></td><td><% out.println(money); %></td></tr><% } %>
            </table>
        </div>
        
    </body>
</html>
