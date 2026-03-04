package controller.product;

import dao.AccountDAO;
import dao.CategoryDAO;
import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ProductAddViewController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ClassNotFoundException, SQLException {

        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");

        // ===== DAO =====
        CategoryDAO categoryDao = new CategoryDAO(getServletContext());
        AccountDAO accountDao = new AccountDAO(getServletContext());

        // ===== LOAD DATA =====
        request.setAttribute("category_list", categoryDao.listAll());
        request.setAttribute("account_list", accountDao.listAll());

        // ===== FORWARD =====
        request.getRequestDispatcher("/views/private_views/add_product.jsp")
                .forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}