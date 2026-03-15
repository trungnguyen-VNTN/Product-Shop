package controller.home;

import dao.ProductDAO;
import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Product;

public class ProductDetailController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ClassNotFoundException, SQLException {

        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");

        String productId = request.getParameter("id");

        ProductDAO dao = new ProductDAO(getServletContext());
        Product product = dao.getObjectById(productId);

        if (product == null) {
            response.sendRedirect("product_list_home");
            return;
        }
        int price = product.getPrice();

        /* ---------------- COOKIE: VIEWED PRODUCTS ---------------- */
        Cookie[] cookies = request.getCookies();
        String viewed = "";
        if (cookies != null) {
            for (Cookie c : cookies) {
                if ("viewedProducts".equals(c.getName())) {
                    viewed = c.getValue();
                }
            }
        }

        String newProduct = productId + "-" + price;

        StringBuilder updated = new StringBuilder();

        updated.append(newProduct);

        String[] arr = viewed.split("_");

        for (String s : arr) {

            if (!s.startsWith(productId + "-") && !s.isEmpty()) {
                updated.append("_").append(s);
            }
        }
        String[] finalArr = updated.toString().split("_");
        String result = "";
        for (int i = 0; i < finalArr.length && i < 20; i++) {

            if (i > 0) {
                result += "_";
            }

            result += finalArr[i];
        }

        Cookie cookie = new Cookie("viewedProducts", result);
        cookie.setMaxAge(7 * 24 * 60 * 60);
        cookie.setPath("/"); 
        response.addCookie(cookie);

        /* ---------------- SEND DATA TO JSP ---------------- */
        request.setAttribute("product", product);

        request.getRequestDispatcher("views/public_views/detail.jsp")
                .forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public String getServletInfo() {
        return "Product Detail Controller";
    }
}
