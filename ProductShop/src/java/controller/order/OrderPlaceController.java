/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.order;

import dao.CartDAO;
import dao.CartDetailDAO;
import dao.OrderDAO;
import dao.OrderDetailDAO;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Account;
import model.Cart;
import model.CartDetail;

/**
 *
 * @author PC
 */
@WebServlet(name = "OrderPlaceController", urlPatterns = {"/order_place"})
public class OrderPlaceController extends HttpServlet {

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
        HttpSession session = request.getSession();

        Account user = (Account) session.getAttribute("user");
        String account = user.getAccount();
        String address = request.getParameter("address");
        String phone = user.getPhone();

        CartDAO cartDAO = new CartDAO(getServletContext());
        CartDetailDAO cartDetailDAO = new CartDetailDAO(getServletContext());
        OrderDAO orderDAO = new OrderDAO(getServletContext());
        OrderDetailDAO orderDetailDAO = new OrderDetailDAO(getServletContext());

        Cart cart = cartDAO.getCartByAccount(user.getAccount());
        List<CartDetail> list = cartDetailDAO.listByCartId(cart.getCartId());

        int total = 0;
        for (CartDetail c : list) {
            total += c.getQuantity() * (c.getProductId().getPrice() - (c.getProductId().getPrice() * c.getProductId().getDiscount() / 100));
        }

        int orderId = orderDAO.createOrder(account, address, phone, total);
        orderDetailDAO.createOrderDetail(orderId, list);
        cartDetailDAO.clearCartDetail(cart.getCartId());

        response.sendRedirect("main_controller?action=orderSuccess&orderId=" + orderId);
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
            Logger.getLogger(OrderPlaceController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(OrderPlaceController.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(OrderPlaceController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(OrderPlaceController.class.getName()).log(Level.SEVERE, null, ex);
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
