/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.account;

import dao.AccountDAO;
import error.AccountError;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Account;

/**
 *
 * @author PC
 */
public class AccountUpdateController extends HttpServlet {

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
            throws ServletException, IOException, ClassNotFoundException, SQLException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        String account = request.getParameter("account");
        String pass = request.getParameter("pass");
        String lastName = request.getParameter("lastName");
        String firstName = request.getParameter("firstName");
        String birthdayStr = request.getParameter("birthday");
        String genderStr = request.getParameter("gender");
        String phone = request.getParameter("phone");
        boolean used = Boolean.parseBoolean(request.getParameter("used"));
        int roleInSystem = Integer.parseInt(request.getParameter("roleInSystem"));
        AccountError error = new AccountError();
        boolean valid = true;


        // ===== VALIDATE PASSWORD =====
        if (pass.length() > 20) {
            error.setPassError("Password must be <= 20 characters");
            valid = false;
        }

        // ===== VALIDATE FIRST NAME =====
        if (firstName.length() > 30) {
            error.setFirstNameError("First name must be <= 30 characters");
            valid = false;
        }
        
        // ===== VALIDATE LAST NAME =====
        if (firstName.length() > 50) {
            error.setFirstNameError("First name must be <= 30 characters");
            valid = false;
        }

        // ===== VALIDATE PHONE =====
        if (!phone.matches("^(03|05|07|08|09)[0-9]{8}$")) {
            error.setPhoneError("Phone must start with 03/05/07/08/09 and contain 10 digits");
            valid = false;
        }

        // ===== PARSE BIRTHDAY =====
        Date birthday = null;
        if (birthdayStr != null && !birthdayStr.isEmpty()) {
            birthday = java.sql.Date.valueOf(birthdayStr);
        }
        


        // ===== IF VALIDATION FAIL =====
        if (!valid) {
            request.setAttribute("account_error", error);
            request.getRequestDispatcher("/views/private_views/update_account.jsp")
                    .forward(request, response);
            return;
        }

        try {

            // convert gender
            boolean gender;

            if ("1".equals(genderStr)) {
                gender = true;   
            } else {
                gender = false;  
            }

            Account acc = new Account(
                    account, pass, lastName, firstName,
                    birthday, gender, phone,
                    used, roleInSystem
            );
            
            AccountDAO dao = new AccountDAO(getServletContext());
            dao.updateRec(acc);

            response.sendRedirect("main_controller?action=accounts");

        } catch (Exception e) {
            e.printStackTrace();
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
        try {
            processRequest(request, response);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AccountUpdateController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(AccountUpdateController.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        try {
            processRequest(request, response);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AccountUpdateController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(AccountUpdateController.class.getName()).log(Level.SEVERE, null, ex);
        }
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

}
