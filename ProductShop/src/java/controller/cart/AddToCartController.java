/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.cart;

import dao.CartDAO;
import dao.CartDetailDAO;
import dao.ProductDAO;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Account;
import model.Cart;
import model.CartDetail;
import model.Product;

/**
 *
 * @author PC
 */
@WebServlet(name = "AddToCartController", urlPatterns = {"/cart_add"})
public class AddToCartController extends HttpServlet {

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
        
        try {
            HttpSession session = request.getSession();
            Account acc = (Account) session.getAttribute("user");
            String account = acc.getAccount();
            String productId = request.getParameter("productId");
            int quantity = Integer.parseInt(request.getParameter("quantity"));

            CartDAO cartDAO = new CartDAO(getServletContext());
            CartDetailDAO cartDetailDAO = new CartDetailDAO(getServletContext());
            ProductDAO productDAO = new ProductDAO(getServletContext());

            Product product = productDAO.getObjectById(productId);
            Cart cart = cartDAO.getCartByAccount(account);

            if (cart == null) {
                cart = new Cart(0, acc);
                cartDAO.insertRec(cart);
                cart = cartDAO.getCartByAccount(account);
            }

            int cartId = cart.getCartId();
            CartDetail detail = cartDetailDAO.getObjectById(cartId, productId);

            if (detail == null) {
                CartDetail newCartDetail = new CartDetail(cartId, product, quantity);
                cartDetailDAO.insertRec(newCartDetail);

            } else {

                int newQuantity = detail.getQuantity() + quantity;
                CartDetail updatedCartDetail = new CartDetail(cartId, product, newQuantity);
                cartDetailDAO.updateRec(updatedCartDetail);
            }

            session.setAttribute("message", "Add to cart successfully!");
            response.sendRedirect("main_controller?action=detail&id=" + productId);

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
