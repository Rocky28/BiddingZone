<%@page import="java.sql.*"%>
<%@page import="p1.MnplBean"%>
<%@page contentType="text/html" pageEncoding="UTF-8" session="true"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Grade A Players</title>
        <link rel="stylesheet" type="text/css" href="Grade.css"><% HttpSession s=request.getSession(true); %>
        <script type="text/javascript">
var max_time = 5;
var cinterval;
console.log(max_time);
function countdown_timer(){
    var eventSource=new EventSource('check');
    eventSource.addEventListener('refresh', function(event) {
        if(event.data=='yes'){
            window.location.reload();
        }
         
    }, false);
  max_time--;
  console.log(max_time);
  
  document.getElementById('timer').innerHTML = max_time;
  if(max_time == 0){
    window.location="/BiddingZoneNew/addplayer";
    //var xhr = new XMLHttpRequest();
    //xhr.open('GET', 'addplayer', true);
    //xhr.send(null);
                }
            }
            cinterval = setInterval(countdown_timer, 1000);
            console.log(cinterval);
        </script>
    </head>
    <body>
        <div id="head">
            <img src="TUCC-Logo_static.gif" width="10%"><p id="tname">Mnpl 2.0</p> <p id="user">Welcome : <% out.println((String)s.getAttribute("email")); %> <span id="money">Money left : <% MnplBean mb=new MnplBean();out.println(mb.getMyMoney((String)s.getAttribute("email"))); %></span></p>
        </div>
        <div id="playerstats">
            <%  int str=(Integer)s.getAttribute("id"); try{ResultSet rs =mb.srchIdAUnsold(str); if(rs==null){int id=1;s.setAttribute("id",id);response.sendRedirect("Unsold_GradeBpl.jsp");}%>
            <img src="images/gradeA/<%str=rs.getInt("id");s.setAttribute("id",str);String str1=rs.getString(1); out.print(str1);s.setAttribute("info", str1);s.setAttribute("Grade", "A"); %>.jpg">
                       <table><tr><th>Player Name : </th><td><% out.print(str1); %></td></tr>
                       <tr><th>Player Year : </th><td><% out.print(rs.getString(2));%> year</td></tr>
                       <tr><th>Player Batting Hand : </th><td><% String hand=rs.getString(3);if(hand.equals("R")){out.print("Right Handed");}else{out.print("Left Handed");} %></td></tr>
                       <tr><th>Player Bowling Hand : </th><td><% String hand1=rs.getString(4);if(hand1.equals("R")){out.print("Right Handed");}else{out.print("Left Handed");} }catch(Exception e){System.out.println(e);System.out.println("here");}%></td></tr></table>
        </div>
        <div id="arena">
            <table>
                <tr><th>Bidding starts :</th><td>Rupees 1000</td></tr>
                <tr><th>Default bidding : </th><td>Rupees 200</td></tr><% ResultSet res=mb.getCurrentBid((String)s.getAttribute("info"),(String)s.getAttribute("Grade"));while(res.next()){ %>
                <tr><th>Current Highest Bid : <% int h_bid=res.getInt(1);s.setAttribute("c_bid", h_bid);out.print(h_bid); %></th><td><b>Bidder : <% out.print(res.getString(2)); %></b></td></tr><% } %>
                <tr><th>&nbsp;</th><td><b>&nbsp;</b></td></tr>
                <tr><th><form action="bid" method="post"><input type="submit" value="Bid"></form></th><td id="timer">5</td></tr>
            </table>
        </div>
        <div id="owned">
            <p><b>Your Players</b></p>
            <table>
                <tr><td>Player Name</td><td>Price</td></tr><% ResultSet rs=mb.searchMyPlayers((String)s.getAttribute("email")); if(rs.next()){String names=rs.getString(1),tempname="";if(names.equals("Null")){}else{for(int i=0;i<names.length();i++){if(names.charAt(i)==','){int price=mb.getPrice(tempname);%>
                <tr><td><% out.println(tempname); %></td><td><% out.println(price); %></td></tr><%tempname="";continue;}tempname+=names.charAt(i);}}}%>
            </table>
        </div>
        <div id="othersowned">
            <p><b>Bidder's Information</b></p>
            <table>
                <tr><td>Bidders Name</td><td>Players Bought</td><td>Remaining amount</td></tr><% ResultSet rs1=mb.getBidderInfo(); while(rs1.next()){String name=rs1.getString(3);int money=rs1.getInt(1);int total=rs1.getInt(4);%>
                <tr><td><% out.println(name); %></td><td><% out.println(total); %></td><td><% out.println(money); %></td></tr><% } %>
            </table>
        </div>
        
    </body>
</html>
