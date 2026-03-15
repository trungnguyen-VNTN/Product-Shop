package controller.home;

import dao.AccountDAO;
import dao.CategoryDAO;
import dao.ProductDAO;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Account;
import model.Category;
import model.Product;

@WebServlet(name = "ProductListHomeController", urlPatterns = {"/product_list_home"})
public class ProductListHomeController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ClassNotFoundException, SQLException, Exception {

        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");

        ProductDAO productDAO = new ProductDAO(getServletContext());
        CategoryDAO categoryDAO = new CategoryDAO(getServletContext());

        /* ---------------- CATEGORY ---------------- */
        List<Category> categoryList = categoryDAO.listAll();
        request.setAttribute("categoryList", categoryList);

        /* ---------------- NEW ARRIVALS ---------------- */
        List<Product> newProducts = productDAO.getNewestProducts();
        request.setAttribute("newProducts", newProducts);

        /* ---------------- BEST SELLERS ---------------- */
        List<Product> bestSellerProducts = productDAO.getBestSellerProducts();
        request.setAttribute("bestSellerProducts", bestSellerProducts);

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

        /* ---------------- RECENTLY VIEWED ---------------- */
        double sum = 0;
        int count = 0;

        if (viewed != null && !viewed.isEmpty()) {

            String[] arr = viewed.split("_");

            for (String s : arr) {

                if (!s.isEmpty()) {

                    String[] parts = s.split("-");

                    if (parts.length == 2) {


                        try {

                            String priceStr = parts[1].replaceAll("[^0-9.]", "");

                            if (!priceStr.isEmpty()) {

                                double price = Double.parseDouble(priceStr);


                                sum += price;
                                count++;
                            }

                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }


        /* ---------------- RECOMMENDED PRODUCTS ---------------- */
        List<Product> recommendedProducts = new ArrayList<>();

        if (count > 0) {

            double avg = sum / count;
            int segment = 0;

            if (avg < 5000000) {

                recommendedProducts = productDAO.getProductsByPriceRange(0, 5000000);
                segment = 0;

            } else if (avg <= 15000000) {

                recommendedProducts = productDAO.getProductsByPriceRange(5000000, 15000000);
                segment = 1;

            } else {

                recommendedProducts = productDAO.getProductsAbovePrice(15000000);
                segment = 2;
            }

            /* ---------------- SAVE SEGMENT INTO DB ---------------- */
            HttpSession session = request.getSession();
            Account user = (Account) session.getAttribute("user");

            if (user != null) {

                AccountDAO accountDAO = new AccountDAO(getServletContext());

                accountDAO.updatePriceSegment(user.getAccount(), segment);

                user.setPriceSegment(segment);
                session.setAttribute("user", user);
            }
        }
        if (recommendedProducts.size() > 4) {
            recommendedProducts = recommendedProducts.subList(0, 4);
        }
        request.setAttribute("recommendedProducts", recommendedProducts);

        request.getRequestDispatcher("/index.jsp").forward(request, response);
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
        return "Product List Home Controller";
    }
}
