package p1;
import java.sql.*;
public class MnplBean {
Connection db=null;
    PreparedStatement ps=null,ps1=null;
    ResultSet rs=null,rs1=null;
    public MnplBean()
    {
        try
        {
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
            db=DriverManager.getConnection("jdbc:ucanaccess://C:/Users/rocky/Documents/NetBeansProjects/BiddingZoneNew/MOu.accdb");
            
        }
        catch(Exception e1)
        {
            System.out.println(e1.toString());
        }
    } 
    public ResultSet srchIdA(int id)
    {
        try{
           // System.out.print("here");
            String sql="select * from agrade where id="+id;
            ps=db.prepareStatement(sql);
            rs=ps.executeQuery();
            while(rs.next())
            {
                  
                      return rs;
                  
            }
        }catch(Exception e){ System.out.println(e);}
        return null;
        
    }
    public ResultSet srchIdB(int id)
    {
        try{
            
            String sql="select * from bgrade where id="+id;
            ps=db.prepareStatement(sql);
            rs=ps.executeQuery();
           while(rs.next())
            {
                  
                      return rs;
                  
            }
        }catch(Exception e){ System.out.println(e);}
        return null;
        
    }
    public ResultSet srchIdC(int id)
    {
        try{
            
            String sql="select * from cgrade where id="+id;
            ps=db.prepareStatement(sql);
            rs=ps.executeQuery();
            while(rs.next())
            {
                  
                      return rs;
                  
            }
        }catch(Exception e){ System.out.println(e);}
        return null;
        
    }
    public ResultSet srchIdD(int id)
    {
        try{
            
            String sql="select * from dgrade where id="+id;
            ps=db.prepareStatement(sql);
            rs=ps.executeQuery();
            while(rs.next())
            {
                  
                      return rs;
                  
            }
        }catch(Exception e){ System.out.println(e);}
        return null;
        
    }
    public void insert(String em)
    {
        String em1 = em;
        try{
            ps=db.prepareStatement("insert into users(emailaddress,rem_amt,players_owned,total_players) values(?,?,?,?)");
            ps.setString(1, em);
            ps.setInt(2, 20000);
            ps.setString(3, "Null");
            ps.setInt(4, 0);
            ps.executeUpdate();
        }catch(Exception e){System.out.println(e);}
    }
    public void increaseBid(String em,String name,String gd,int raise)
    {
        int prevbid=0,newbid=raise;
        try{
            String sql="select h_bid from "+gd+"grade where nm=?";
            ps=db.prepareStatement(sql);
            ps.setString(1, name);
            rs=ps.executeQuery();
            if(rs.next())
            {
                prevbid=rs.getInt(1);
            }
            newbid=newbid+prevbid;
            String sql1="update "+gd+"grade set h_bid=?,h_bidder=? where nm=?";
            ps=db.prepareStatement(sql1);
            ps.setInt(1, newbid);
            ps.setString(2, em);
            ps.setString(3, name);
            ps.executeUpdate();
        }catch(Exception e){}
    }
    public void addPlayer(String name,String gd)
    {
        String h_bidder=null;
        int h_bid=0;
        try{
            String sql="select h_bid,h_bidder from "+gd+"grade where nm=?";
            ps=db.prepareStatement(sql);
            ps.setString(1, name);
            rs=ps.executeQuery();
            if(rs.next())
            {
                h_bid=rs.getInt(1);
                h_bidder=rs.getString(2);
            }
            System.out.println(h_bid+h_bidder);
            if(h_bid==0)
            {
                sql="update "+gd+"grade set status=? where nm=?";
                ps=db.prepareStatement(sql);
                ps.setString(1, "NotSold");
                ps.setString(2, name);
                ps.executeUpdate();
            }
            else
            {
                sql="select players_owned,rem_amt,total_players from users where emailaddress=?";
                ps=db.prepareStatement(sql);
                ps.setString(1, h_bidder.trim());
                rs=ps.executeQuery();
                String players="";
                int amt=0;
                int ts=0;
                if(rs.next())
                {
                    players=rs.getString(1);
                    amt=rs.getInt(2);
                    ts=rs.getInt(3);
                    System.out.println(players+amt+ts+name);
                }
                if(players.equals("Null"))
                {
                    players=name+",";
                }
                else
                {
                    players+=name+",";
                }
                amt=amt-h_bid;
                ts=ts+1;
                String sql1="select status from "+gd+"grade where nm=?";
                ps=db.prepareStatement(sql1);
                ps.setString(1,name);
                ResultSet rs1=ps.executeQuery();
                String status=new String("NotSold");
                if(rs1.next())
                {
                    status=rs1.getString(1);
                    System.out.println(status);                    
                }
                if(status==null)
                {
                    sql = "update users set players_owned=?,rem_amt=?,total_players=? where emailaddress=?";
                    ps = db.prepareStatement(sql);
                    ps.setString(1, players);
                    ps.setInt(2, amt);
                    ps.setInt(3, ts);
                    ps.setString(4, h_bidder.trim());
                    ps.executeUpdate();
                }
                else if(status.equals("Sold"))
                {
                    System.out.println("Already Sold");
                }
                else
                {
                    sql = "update users set players_owned=?,rem_amt=?,total_players=? where emailaddress=?";
                    ps = db.prepareStatement(sql);
                    ps.setString(1, players);
                    ps.setInt(2, amt);
                    ps.setInt(3, ts);
                    ps.setString(4, h_bidder.trim());
                    ps.executeUpdate();
                    sql="update "+gd+"grade set status=? where nm=?";
                    ps=db.prepareStatement(sql);
                    ps.setString(1, "Sold");
                    ps.setString(2, name);
                    ps.executeUpdate();
                }
            }
        }catch(Exception e){System.out.println(e);}
    }
    public ResultSet getCurrentBid(String pn,String gd)
    {
        
        try{
            String sql="select h_bid,h_bidder from "+gd+"grade where nm=?";
            ps=db.prepareStatement(sql);
            ps.setString(1, pn);
            rs=ps.executeQuery();
        }catch(Exception e){}
      
        return rs;
    }
    public ResultSet searchMyPlayers(String em)
    {
       
        try{
            ps=db.prepareStatement("select players_owned from users where emailaddress=?");
            ps.setString(1, em);
            rs=ps.executeQuery();
        }catch(Exception e){}
     
        return rs;
    }
    public int getPrice(String pn)
    {
        int price =0;
        try{
            ps=db.prepareStatement("select h_bid from agrade where nm=?");
            ps.setString(1, pn);
            rs=ps.executeQuery();
            if(rs.next())
            {
                price=rs.getInt(1);
            }
            else
            {
                 ps=db.prepareStatement("select h_bid from bgrade where nm=?");
                 ps.setString(1, pn);
                 rs=ps.executeQuery();
                 if(rs.next())
                 {
                    price=rs.getInt(1);
                 }
                 else
                 {
                     ps=db.prepareStatement("select h_bid from cgrade where nm=?");
                     ps.setString(1, pn);
                     rs=ps.executeQuery();
                     if(rs.next())
                     {
                         price=rs.getInt(1);
                     }
                     else
                     {
                         ps=db.prepareStatement("select h_bid from dgrade where nm=?");
                         ps.setString(1, pn);
                         rs=ps.executeQuery();
                         if(rs.next())
                         {
                             price=rs.getInt(1);
                         }
                     }
                 }
            }
        }catch(Exception e){}
        return price;
    }
    public ResultSet getBidderInfo()
    {
        try{
            ps=db.prepareStatement("select * from users");
            rs=ps.executeQuery();
        }catch(Exception e){}
        return rs;
    }
    public boolean checkForNewBid(int h_bid,String pn,String gd)
    {
        int newbid=0;
        try{
            String sql="select h_bid from "+gd+"grade where nm=?";
            ps=db.prepareStatement(sql);
            ps.setString(1, pn);
            rs=ps.executeQuery();
            if(rs.next())
            {
                newbid=rs.getInt(1);
            }
            if(newbid>h_bid)
            {
                return true;
            }
        }catch(Exception e){}
        return false;
    }
    public int getMyMoney(String em)
    {
        try{
            ps=db.prepareStatement("select rem_amt from users where emailaddress=?");
            ps.setString(1, em);
            rs=ps.executeQuery();
            if(rs.next())
            {
                int money=rs.getInt(1);
                return money;
            }
        }catch(Exception e){}
        return 0;
    }
    public ResultSet srchIdAUnsold(int id)
    {
        int countid = 0;
        try {
            //System.out.print("here");
            String sql1 = "select count(*) from agrade";
            ps = db.prepareStatement(sql1);
            rs = ps.executeQuery();
            while (rs.next()) 
            {
                countid = rs.getInt(1);
            }
            String sql = "select * from agrade where status='NotSold' and id=" + id;
            ps = db.prepareStatement(sql);
            ResultSet rs1 = ps.executeQuery();
            boolean b = rs1.next();
            if (b == false) 
            {
                id++;
                for (int i = id; i <= countid; i++) 
                {
                    String sql2 = "select * from agrade where status='NotSold' and id=" + id;
                    ps = db.prepareStatement(sql2);
                    rs = ps.executeQuery();
                    boolean b1 = rs.next();
                    if (b == false) 
                    {
                        continue;
                    } 
                    else 
                    {
                        return rs1;
                    }
                }
            } 
            else 
            {
                return rs1;
            }
           
        }catch(Exception e){ System.out.println(e);}
        return null;
    }
    public ResultSet srchIdBUnsold(int id)
    {
         int countid=0;
        try{
           // System.out.print("here");
            String sql1="select count(*) from bgrade";
            ps=db.prepareStatement(sql1);
            rs=ps.executeQuery();
            while(rs.next())
            {
                countid=rs.getInt(1);
            }
            String sql="select * from bgrade where status='NotSold' and id="+id;
            ps=db.prepareStatement(sql);
            rs=ps.executeQuery();
            while(rs.next())
            {
                  if(rs==null && id<=countid)
                  {
                      id++;
                      srchIdAUnsold(id);
                  }
                  else
                  {
                      return rs;
                  }
            }
        }catch(Exception e){ System.out.println(e);}
        return null;
        
    }
    public ResultSet srchIdCUnsold(int id)
    {
         int countid=0;
        try{
           // System.out.print("here");
            String sql1="select count(*) from cgrade";
            ps=db.prepareStatement(sql1);
            rs=ps.executeQuery();
            while(rs.next())
            {
                countid=rs.getInt(1);
            }
            String sql="select * from cgrade where status='NotSold' and id="+id;
            ps=db.prepareStatement(sql);
            rs=ps.executeQuery();
            while(rs.next())
            {
                  if(rs==null && id<=countid)
                  {
                      id++;
                      srchIdAUnsold(id);
                  }
                  else
                  {
                      return rs;
                  }
            }
        }catch(Exception e){ System.out.println(e);}
        return null;
        
    }
    public ResultSet srchIdDUnsold(int id)
    {
         int countid=0;
        try{
           // System.out.print("here");
            String sql1="select count(*) from dgrade";
            ps=db.prepareStatement(sql1);
            rs=ps.executeQuery();
            while(rs.next())
            {
                countid=rs.getInt(1);
            }
            String sql="select * from dgrade where status='NotSold' and id="+id;
            ps=db.prepareStatement(sql);
            rs=ps.executeQuery();
            while(rs.next())
            {
                  if(rs==null && id<=countid)
                  {
                      id++;
                      srchIdAUnsold(id);
                  }
                  else
                  {
                      return rs;
                  }
            }
        }catch(Exception e){ System.out.println(e);}
        return null;
        
    }
}
