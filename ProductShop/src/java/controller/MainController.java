/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author PC
 */
@MultipartConfig
public class MainController extends HttpServlet {

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
        String action = request.getParameter("action");

        if (action == null) {
            action = "home";
        }

        switch (action) {

            case "home":
                request.getRequestDispatcher("/product_list_home")
                        .forward(request, response);
                break;

            case "login":
                request.getRequestDispatcher("views/public_views/login.jsp")
                        .forward(request, response);
                break;

            case "logout":
                request.getRequestDispatcher("/logout")
                        .forward(request, response);
                break;

            case "detail":
                request.getRequestDispatcher("/product_detail")
                        .forward(request, response);
                break;
            //-------------------PRIVATE--------------------------//
            case "private":
                request.getRequestDispatcher("/dashboard")
                        .forward(request, response);
                break;
            //-------------------ACCOUNT--------------------------//
            case "private_accounts":
                request.getRequestDispatcher("/account_list")
                        .forward(request, response);
                break;

            case "private_updateAccountView":
                request.getRequestDispatcher("/account_update_view")
                        .forward(request, response);
                break;
                
            case "private_updateAccount":
                request.getRequestDispatcher("/account_update")
                        .forward(request, response);
                break;
                
            case "private_toggleAccountStatus":
                request.getRequestDispatcher("/account_toggle")
                        .forward(request, response);
                break;

            case "private_deleteAccount":
                request.getRequestDispatcher("/account_delete")
                        .forward(request, response);
                break;

            case "private_addAccountView":
                request.getRequestDispatcher("/views/private_views/add_account.jsp")
                        .forward(request, response);
                break;

            case "private_addAccount":
                request.getRequestDispatcher("/account_add")
                        .forward(request, response);
                break;

                //-------------------CATEGORY--------------------------//
            case "private_categories":
                request.getRequestDispatcher("/category_list")
                        .forward(request, response);
                break;
                
            case "private_updateCategoryView":
                request.getRequestDispatcher("/category_update_view")
                        .forward(request, response);
                break;
                
            case "private_updateCategory":
                request.getRequestDispatcher("/category_update")
                        .forward(request, response);
                break;
                
            case "private_deleteCategory":
            request.getRequestDispatcher("/category_delete")
                    .forward(request, response);
            break;
                            
            case "private_addCategoryView":
                request.getRequestDispatcher("/views/private_views/add_category.jsp")
                        .forward(request, response);
                break;
                
             case "private_addCategory":
                request.getRequestDispatcher("/category_add")
                        .forward(request, response);
                break;
            
            //-------------------PRODUCT--------------------------//
            case "private_products":
                request.getRequestDispatcher("/product_list")
                        .forward(request, response);
                break;

            case "private_updateProductView":
                request.getRequestDispatcher("/product_update_view")
                        .forward(request, response);
                break;
                
            case "private_updateProduct":
                request.getRequestDispatcher("/product_update")
                        .forward(request, response);
                break;

            case "private_deleteProduct":
                request.getRequestDispatcher("/product_delete")
                        .forward(request, response);
                break;

            case "private_addProductView":
                request.getRequestDispatcher("/product_add_view")
                        .forward(request, response);
                break;

            case "private_addProduct":
                request.getRequestDispatcher("/product_add")
                        .forward(request, response);
                break;
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
