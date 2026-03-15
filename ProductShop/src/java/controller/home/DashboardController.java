/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.home;

import dao.AccountDAO;
import dao.CategoryDAO;
import dao.OrderDAO;
import dao.OrderDetailDAO;
import dao.ProductDAO;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author PC
 */
public class DashboardController extends HttpServlet {

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
        try {
            AccountDAO accountDAO = new AccountDAO(getServletContext());
            CategoryDAO categoryDAO = new CategoryDAO(getServletContext());
            ProductDAO productDAO = new ProductDAO(getServletContext());
            OrderDAO orderDAO = new OrderDAO(getServletContext());
            OrderDetailDAO orderDetailDAO = new OrderDetailDAO(getServletContext());

            int totalAccounts = accountDAO.countAccounts();
            int totalCategories = categoryDAO.countCategories();
            int totalProducts = productDAO.countProducts();
            int totalSales = orderDAO.getTotalSales();
            int productsSold = orderDetailDAO.countProductsSold();
            int totalOrders = orderDAO.countOrders();
            

            request.setAttribute("totalAccounts", totalAccounts);
            request.setAttribute("totalCategories", totalCategories);
            request.setAttribute("totalProducts", totalProducts);
            request.setAttribute("productsSold", productsSold);
            request.setAttribute("totalSales", totalSales);
            request.setAttribute("totalOrders", totalOrders);

            request.getRequestDispatcher("views/private_views/private.jsp")
                    .forward(request, response);

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
