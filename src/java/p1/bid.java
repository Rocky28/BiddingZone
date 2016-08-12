package p1;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
@WebServlet(name = "bid", urlPatterns = {"/bid"})
public class bid extends HttpServlet {
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
            throws ServletException, IOException 
    {
    //    processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException 
    {
        HttpSession s=request.getSession(true);
        MnplBean ob=new MnplBean();
        String name=((String)s.getAttribute("info")).trim();
        String gd=((String)s.getAttribute("Grade")).trim();
        if(gd.equals("A"))
        {
         ob.increaseBid((String)s.getAttribute("email"),name,gd,200);
        }
        else if(gd.equals("B"))
        {
            ob.increaseBid((String)s.getAttribute("email"),name,gd,100);
        }
        else if(gd.equals("C"))
        {
            ob.increaseBid((String)s.getAttribute("email"),name,gd,50);
        }
        else
        {
            ob.increaseBid((String)s.getAttribute("email"),name,gd,20);
        }
        String link="Grade"+gd+"pl.jsp";
        response.sendRedirect(link);
    //    processRequest(request, response);
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
