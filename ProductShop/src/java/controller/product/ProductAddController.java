/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.product;

import dao.AccountDAO;
import dao.CategoryDAO;
import dao.ProductDAO;
import error.ProductError;
import java.io.File;
import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.util.Enumeration;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import model.Account;
import model.Category;
import model.Product;

/**
 *
 * @author PC
 */
@MultipartConfig
public class ProductAddController extends HttpServlet {

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


// ===== GET PARAM =====
        String productId = request.getParameter("productId");
        String productName = request.getParameter("productName");
        String brief = request.getParameter("brief");
        String postedDateStr = request.getParameter("postedDate");
        String typeId = request.getParameter("typeId");
        String account = request.getParameter("account");
        String unit = request.getParameter("unit");
        String priceStr = request.getParameter("price");
        String discountStr = request.getParameter("discount");

// upload
        Part filePart = request.getPart("fileImage");
        String imageNameInput = request.getParameter("imageName");

        ProductError error = new ProductError();
        boolean valid = true;

// ===== VALIDATE =====
        if (productId == null || productId.length() > 10) {
            error.setProductIdError("Product ID must be <= 10 characters");
            valid = false;
        }

        if (productName == null || productName.length() > 500) {
            error.setProductNameError("Product name must be <= 500 characters");
            valid = false;
        }

        if (brief != null && brief.length() > 2000) {
            error.setBriefError("Brief must be <= 2000 characters");
            valid = false;
        }

// PRICE
        int price = 0;
        try {
            price = Integer.parseInt(priceStr);
            if (price < 0) {
                error.setPriceError("Price must be >= 0");
                valid = false;
            }
        } catch (Exception e) {
            error.setPriceError("Invalid price");
            valid = false;
        }

// DISCOUNT
        int discount = 0;
        try {
            discount = Integer.parseInt(discountStr);
            if (discount < 0 || discount > 100) {
                error.setDiscountError("Discount must be 0 - 100");
                valid = false;
            }
        } catch (Exception e) {
            error.setDiscountError("Invalid discount");
            valid = false;
        }

// DATE
        Date postedDate = null;
        if (postedDateStr != null && !postedDateStr.isEmpty()) {
            postedDate = java.sql.Date.valueOf(postedDateStr);
        }

// CHECK DUPLICATE
        ProductDAO dao = new ProductDAO(getServletContext());
        if (dao.getObjectById(productId) != null) {
            error.setProductIdError("Product ID already exists");
            valid = false;
        }

// ===== IF ERROR =====
        if (!valid) {

            CategoryDAO catDao = new CategoryDAO(getServletContext());
            AccountDAO accDao = new AccountDAO(getServletContext());

            request.setAttribute("category_list", catDao.listAll());
            request.setAttribute("account_list", accDao.listAll());
            request.setAttribute("product_error", error);

            request.getRequestDispatcher("views/private_views/add_product.jsp")
                    .forward(request, response);
            return;
        }

        try {

            // ===== HANDLE IMAGE UPLOAD =====
            String dbImagePath = "";   // cái sẽ lưu vào DB

            if (filePart != null && filePart.getSize() > 0) {

                String originalFileName = filePart.getSubmittedFileName();
                String extension = "";

                int i = originalFileName.lastIndexOf(".");
                if (i > 0) {
                    extension = originalFileName.substring(i);
                }

                // tên file cuối cùng
                String newFileName = imageNameInput + extension;

                // đường dẫn vật lý
                String uploadPath = getServletContext().getRealPath("/images/sanPham");
                File uploadDir = new File(uploadPath);

                if (!uploadDir.exists()) {
                    uploadDir.mkdirs();
                }

                // lưu file vào server
                filePart.write(uploadPath + File.separator + newFileName);

                dbImagePath = "/images/sanPham/" + newFileName;
            }
            AccountDAO accDao = new AccountDAO(getServletContext());
            CategoryDAO catDao = new CategoryDAO(getServletContext());
            Account acc = accDao.getObjectById(account);
            Category category = catDao.getObjectById(typeId);
            System.out.println("Category = " + category.getCategoryName());
            System.out.println("Account = " + acc.getAccount());

            // ===== CREATE PRODUCT =====
            Product product = new Product(
                    productId,
                    productName,
                    dbImagePath,
                    brief,
                    postedDate,
                    category,
                    acc,
                    unit,
                    price,
                    discount
            );
            int result = dao.insertRec(product);
            System.out.println("Insert result = " + result);

            if (result > 0) {
                System.out.println("INSERT SUCCESS");
            } else {
                System.out.println("INSERT FAILED");
            }

            response.sendRedirect("main_controller?action=products");

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
            Logger.getLogger(ProductAddController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ProductAddController.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(ProductAddController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ProductAddController.class.getName()).log(Level.SEVERE, null, ex);
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
