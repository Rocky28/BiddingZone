package p1;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
@WebServlet(name = "addplayer", urlPatterns = {"/addplayer"})
public class addplayer extends HttpServlet {
/*    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
        } finally {            
            out.close();
        }
    }*/

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
          MnplBean ob=new MnplBean();
        HttpSession s=request.getSession(true);
       
        String name=((String)s.getAttribute("info")).trim();
        String gd=((String)s.getAttribute("Grade")).trim();
        int id=(Integer)s.getAttribute("id");
        id++;
        s.setAttribute("id", id);
        String soldstat=(String)s.getAttribute("Sold");
        if(gd.equals("A"))
        {
         System.out.print(name);
         ob.addPlayer(name,gd);
         if(soldstat.equals("Y"))
         {
             response.sendRedirect("GradeApl.jsp");
         }
         else
         {
             response.sendRedirect("Unsold_GradeApl.jsp");
         }
        }
        else if(gd.equals("B"))
        {
             ob.addPlayer(name,gd);
         if(soldstat.equals("Y"))
         {
             response.sendRedirect("GradeBpl.jsp");
         }
         else
         {
             response.sendRedirect("Unsold_GradeBpl.jsp");
         }
        }
        else if(gd.equals("C"))
        {
             ob.addPlayer(name,gd);
         if(soldstat.equals("Y"))
         {
             response.sendRedirect("GradeCpl.jsp");
         }
         else
         {
             response.sendRedirect("Unsold_GradeCpl.jsp");
         }
        }
        else
        {
             ob.addPlayer(name,gd);
            if(soldstat.equals("Y"))
         {
             response.sendRedirect("GradeDpl.jsp");
         }
         else
         {
             response.sendRedirect("Unsold_GradeDpl.jsp");
         }
        }
//        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException 
    {
      /**  MnplBean ob=new MnplBean();
        HttpSession s=request.getSession(true);
        System.out.print("here");
        String name=((String)s.getAttribute("info")).trim();
        String gd=((String)s.getAttribute("Grade")).trim();
        int id=(Integer)s.getAttribute("id");
        id++;
        s.setAttribute("id", id);
        if(gd.equals("A"))
        {
            System.out.print("here");
         ob.addPlayer(name,gd);
         response.sendRedirect("GradeApl.jsp");
        }
        if(gd.equals("B"))
        {
             ob.addPlayer(name,gd);
         response.sendRedirect("GradeBpl.jsp");
        }
        if(gd.equals("C"))
        {
             ob.addPlayer(name,gd);
         response.sendRedirect("GradeCpl.jsp");
        }
        else
        {
             ob.addPlayer(name,gd);
            response.sendRedirect("GradeDpl.jsp");
        }*/
  //      processRequest(request, response);
    }

    /** 
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
