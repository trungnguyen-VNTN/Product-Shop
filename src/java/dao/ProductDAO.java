
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletContext;
import model.Product;
import util.ConnectDB;

public class ProductDAO implements Accessible<Product>{

    private ServletContext sc;
    private Connection con;

    public ProductDAO() throws ClassNotFoundException, SQLException {
        ConnectDB db = new ConnectDB();
        this.con = db.getConnection();
    }
    
    public ProductDAO(ServletContext sc) throws ClassNotFoundException, SQLException {
        ConnectDB db = new ConnectDB(sc);
        this.con = db.getConnection();
    }
    
    @Override
    public int insertRec(Product obj) {
        int result = 0;
        try {
            String sqlCommand = "INSERT INTO Product VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement ps = con.prepareStatement(sqlCommand);
            ps.setString(1, obj.getProductId());
            ps.setString(2, obj.getProductName());
            ps.setString(3, obj.getProductImage());
            ps.setString(4, obj.getBrief());
            ps.setDate(5, obj.getPostedDate());
            ps.setInt(6, obj.getType().getTypeId());
            ps.setString(7, obj.getAccount().getAccount());
            ps.setString(8, obj.getUnit());
            ps.setInt(9, obj.getPrice());
            ps.setInt(10, obj.getDiscount());
            result = ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    @Override
    public int updateRec(Product obj) {
        int result = 0;
        try {
            String sqlCommand = "UPDATE Product SET "
                    + "productName = ?,"
                    + "productImage = ?,"
                    + "brief = ?,"
                    + "postedDate = ?,"
                    + "typeId = ?,"
                    + "account = ?,"
                    + "unit = ?,"
                    + "price = ? "
                    + "price = ?,"
                    + "WHERE productId = ?";
            PreparedStatement ps = con.prepareStatement(sqlCommand);
            ps.setString(1, obj.getProductName());
            ps.setString(2, obj.getProductImage());
            ps.setString(3, obj.getBrief());
            ps.setDate(4, obj.getPostedDate());
            ps.setInt(5, obj.getType().getTypeId());
            ps.setString(6, obj.getAccount().getAccount());
            ps.setString(7, obj.getUnit());
            ps.setInt(8, obj.getPrice());
            ps.setInt(9, obj.getDiscount());
            ps.setString(10, obj.getProductId());
            result = ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    @Override
    public int deleteRec(Product obj) {
        int result = 0;
        try {
            String sqlCommand = "DELETE FROM Product WHERE productId = ?";
            PreparedStatement ps = con.prepareStatement(sqlCommand);
            ps.setString(1, obj.getProductId());
            result = ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    @Override
    public Product getObjectById(String id) {
        try {
            String sqlCommand = "SELECT * FROM Product WHERE productId = ?";
            PreparedStatement ps = con.prepareStatement(sqlCommand);
            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();
            Product result = null;
            if (rs.next()) {
                result = new Product();
                result.setProductId(rs.getString("productId"));
                result.setProductName(rs.getString("productName"));
                result.setProductImage(rs.getString("productImage"));
                result.setBrief(rs.getString("brief"));
                result.setPostedDate(rs.getDate("postedDate"));
                result.setType(categoryDAO.getObjectById(rs.getString("typeId")));
                result.setAccount(accountDAO.getObjectById(rs.getString("account")));
                result.setUnit(rs.getString("unit"));
                result.setPrice(rs.getInt("price"));
                result.setDiscount(rs.getInt("discount"));
            }
            if (result != null) {
                return result;
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public List<Product> listAll() {
        List<Product> result = new ArrayList<>();
        try {
            String sqlCommand = "SELECT * FROM Product";
            PreparedStatement ps = con.prepareStatement(sqlCommand);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Product prouduct = new Product();
                prouduct.setProductId(rs.getString("productId"));
                prouduct.setProductName(rs.getString("productName"));
                prouduct.setProductImage(rs.getString("productImage"));
                prouduct.setBrief(rs.getString("brief"));
                prouduct.setPostedDate(rs.getDate("postedDate"));
                prouduct.setType(rs.getBoolean("gender"));
                prouduct.setAccount(rs.getString("phone"));
                prouduct.setUnit(rs.getString("unit"));
                prouduct.setPrice(rs.getInt("price"));
                prouduct.setDiscount(rs.getInt("discount"));
                result.add(prouduct);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }
    }
    
}
