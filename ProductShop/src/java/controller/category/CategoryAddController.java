/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.category;

import dao.CategoryDAO;
import error.CategoryError;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Category;

/**
 *
 * @author PC
 */
public class CategoryAddController extends HttpServlet {

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

          request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");

        String categoryName = request.getParameter("categoryName");
        String memo = request.getParameter("memo");

        CategoryError error = new CategoryError();
        boolean valid = true;

        // ===== VALIDATE CATEGORY NAME =====
        if (categoryName == null || categoryName.trim().isEmpty()) {
            error.setCategoryNameError("Category name is required");
            valid = false;
        } else if (categoryName.length() > 88) {
            error.setCategoryNameError("Category name must be <= 88 characters");
            valid = false;
        }


        if (!valid) {
            request.setAttribute("category_error", error);
            request.getRequestDispatcher("/views/private_views/add_category.jsp")
                    .forward(request, response);
            return;
        }

        try {

            if (memo == null || memo.trim().isEmpty()) {
                memo = "";
            }

            Category cate = new Category(0, categoryName, memo);

            CategoryDAO dao = new CategoryDAO(getServletContext());
            dao.insertRec(cate);

            response.sendRedirect("main_controller?action=categories");

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
        processRequest(request, response);
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
