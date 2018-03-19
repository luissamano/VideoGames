package VideoGames;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import com.mysql.jdbc.Connection;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author marti
 */
public class Games extends HttpServlet {

    Connection conn;
    String user = "root";
    String password = "";

    String nombre, compañia, categoria, clasificacion, piezas, precio, reseña;

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet Store</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>" + request.getParameter("Nombre") + "</h1>");
            out.println("<h2>Se ha registrado exitosamente en la base de datos</h2>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        nombre = request.getParameter("Nombre");
        compañia = request.getParameter("Marca");
        clasificacion = request.getParameter("Clasificacion");        
        categoria = request.getParameter("Categoria");
        piezas = request.getParameter("Piezas");
        precio = request.getParameter("Precio");
        reseña = request.getParameter("Resumen");

        //processRequest(request, response);
        try {

            Class.forName("com.mysql.jdbc.Driver");
            conn = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/store?user=" + user + "&pasword=" + password);

            String query = String.format("INSERT INTO store.games "
                    + "(nombre, studio, categoria, clasificacion, piezas, precio, resumen) "
                    + "VALUES ('" + nombre + "', '" + compañia + "', '" + clasificacion + "', '"+ categoria +"', '" + piezas + "', '" + precio + "', '" + reseña + "')");

            PreparedStatement st = conn.prepareStatement(query);
            st.execute();
            conn.close();
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(Games.class.getName()).log(Level.SEVERE, null, ex);
        }
        //processRequest(request, response);

        response.setContentType("text/html;charset=UTF-8");
        response.sendRedirect("index.html");

    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

//    public void connDB() {
//
//        try {
//            } catch (ClassNotFoundException ex) {
//            Logger.getLogger(Games.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (SQLException ex) {
//            Logger.getLogger(Games.class.getName()).log(Level.SEVERE, null, ex);
//        }
//
//    }
}
