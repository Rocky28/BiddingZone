package p1;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.*;
import java.util.HashMap;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.*;
import javax.servlet.http.HttpSession;
import net.sf.jasperreports.engine.*;


@WebServlet(name = "genPdf", urlPatterns = {"/genPdf"})
public class genPdf extends HttpServlet {

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
      //  processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException 
    {
        HttpSession s=request.getSession(true);
         System.out.print("herer");
        try{
        String emailaddres="sinha.aot@gmail.com";//(String)s.getAttribute("email");
        Connection connection=null;
        ServletOutputStream servletOutputStream = response.getOutputStream();
        String reportStream =getServletContext().getRealPath("") + File.separator+"/reports/DBreport.jrxml";
        String reportStream1 =getServletContext().getRealPath("") + File.separator+"/reports/DBreport.jasper";
        HashMap parameterMap = new HashMap();
        parameterMap.put("emailaddres", emailaddres);
        System.out.print("herer");
                Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
                connection = DriverManager.getConnection("jdbc:ucanaccess://C:/Users/rocky/Documents/NetBeansProjects/BiddingZoneNew/MOu.accdb");
                System.setProperty("jasper.reports.compile.class.path", getServletContext().getRealPath("") + File.separator+"../../lib/jasperreports-5.5.1/jasperreports-5.5.1.jar");
                JasperReport jr=JasperCompileManager.compileReport(reportStream);
                JasperPrint jp=JasperFillManager.fillReport(reportStream1, parameterMap, connection); 
                JasperExportManager.exportReportToPdfStream(jp, servletOutputStream);
                connection.close();
                System.out.print("herer");
                response.setContentType("application/pdf");
                System.out.print("herer");
                servletOutputStream.flush();
                servletOutputStream.close();
           }catch (Exception e){
               System.out.println(e);
           }
    //    processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
