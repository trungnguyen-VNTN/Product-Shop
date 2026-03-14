package controller.home;

import dao.ProductDAO;
import java.io.IOException;
import java.net.URLEncoder;
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

        /* thêm product mới vào cookie */
        String newValue = product.getProductId() + "-" + product.getPrice() + "_";
        viewed = viewed + newValue;

        /* giới hạn tối đa 10 sản phẩm */
        String[] arr = viewed.split("_");

        if (arr.length > 10) {

            StringBuilder temp = new StringBuilder();

            for (int i = arr.length - 10; i < arr.length; i++) {

                if (!arr[i].isEmpty()) {
                    temp.append(arr[i]).append("_");
                }
            }

            viewed = temp.toString();
        }

        /* encode cookie để tránh lỗi ký tự */
        String encodedViewed = URLEncoder.encode(viewed, "UTF-8");

        Cookie cookie = new Cookie("viewedProducts", encodedViewed);
        cookie.setMaxAge(60 * 60 * 24 * 7); // 7 ngày
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