 package cz.mypoc;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
 
 
public class Dispatcher extends HttpServlet {
 
    private static final long serialVersionUID = 1462376190610692513L;
    private static final Logger LOGGER = Logger.getLogger(Dispatcher.class.getName());
     
    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
    * @param request servlet request
    * @param response servlet response
    */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) {
    PrintWriter out = null;
    Connection cnn = null;
    
    try {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        out = response.getWriter();
        cnn = createConnection();

        out.println("<h1>Hello World</h1>");

        String SELECT = "SELECT ID, DESCRIPTION FROM TEST_TABLE ORDER BY ID;";
        try (Statement st = cnn.createStatement()) {
            try (ResultSet rs = st.executeQuery(SELECT)) {
                out.println("<table>");
                out.println("<tr>");
                while (rs.next()) {
                    out.println("<td>");
                    out.println(rs.getInt("ID"));
                    out.println("</td><td>");
                    out.println(rs.getInt("DESCRIPTION"));
                    out.println("</td>");
                }
                out.println("</tr>");
                out.println("</table>");
            }
        }
        
    } catch (Exception ex) {
        LOGGER.log(Level.SEVERE, "Could not dispatch page", ex);
    } finally {
        if (out != null) {
            out.close();
        }
        closeConnection(cnn);
    }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
    * @param request servlet request
    * @param response servlet response
    */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        processRequest(request, response);
    }


    /** 
     * Handles the HTTP <code>POST</code> method.
    * @param request servlet request
    * @param response servlet response
    */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        processRequest(request, response);
    }


    /** 
     * Returns a short description of the servlet.
    * @return a String containing servlet description
    */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
    

    private Connection createConnection() throws javax.naming.NamingException, SQLException {
        Context initCtx = new InitialContext();
        Context envCtx = (Context) initCtx.lookup("java:comp/env");
        DataSource ds = (DataSource) envCtx.lookup("jdbc/MYPOC");
        return ds.getConnection();
    }

    
    private void closeConnection(Connection cnn) {
        if (cnn != null) {
            try {
                cnn.close();
            } catch (SQLException ex) {
                LOGGER.log(Level.SEVERE, null, ex);
            }
        }
    }
}
