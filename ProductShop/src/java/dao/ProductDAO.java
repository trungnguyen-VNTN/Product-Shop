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
import model.Account;
import model.Category;
import model.Product;
import util.ConnectDB;

public class ProductDAO implements Accessible<Product> {

    private ServletContext sc;
    private Connection con;

    public ProductDAO() throws ClassNotFoundException, SQLException {
        ConnectDB db = new ConnectDB();
        this.con = db.getConnection();
    }

    public ProductDAO(ServletContext sc) throws ClassNotFoundException, SQLException {
        this.sc = sc;
        this.con = getConnect(sc);
    }

    private Connection getConnect(ServletContext sc) throws ClassNotFoundException, SQLException {
        return new ConnectDB(sc).getConnection();
    }

    @Override
    public int insertRec(Product obj) {
        if (obj == null || obj.getProductId() == null || obj.getProductId().isEmpty()) {
            return 0;
        }
        int result = 0;
        PreparedStatement ps = null;
        try {
            String sqlCommand = "INSERT INTO Product VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            ps = con.prepareStatement(sqlCommand);
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
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
            } catch (SQLException e) {
            }
        }
        return result;
    }

    @Override
    public int updateRec(Product obj) {
        if (obj == null || obj.getProductId() == null || obj.getProductId().isEmpty()) {
            return 0;
        }
        int result = 0;
        PreparedStatement ps = null;
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
            ps = con.prepareStatement(sqlCommand);
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
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
            } catch (SQLException e) {
            }
        }
        return result;
    }

    @Override
    public int deleteRec(Product obj) {
        if (obj == null || obj.getProductId() == null || obj.getProductId().isEmpty()) {
            return 0;
        }
        int result = 0;
        PreparedStatement ps = null;
        try {
            String sqlCommand = "DELETE FROM Product WHERE productId = ?";
            ps = con.prepareStatement(sqlCommand);
            ps.setString(1, obj.getProductId());
            result = ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
            } catch (SQLException e) {
            }
        }
        return result;
    }

    @Override
    public Product getObjectById(String id) {

        if (id == null || id.isEmpty()) {
            return null;
        }

        PreparedStatement ps = null;
        ResultSet rs = null;

        String sqCommand = "SELECT p.*, "
                + "a.account AS a_account, "
                + "a.pass, "
                + "a.firstName, "
                + "a.lastName, "
                + "a.birthday, "
                + "a.gender, "
                + "a.phone, "
                + "a.roleInSystem, "
                + "a.isUse, "
                + "c.typeId AS c_typeId, "
                + "c.categoryName, "
                + "c.memo "
                + "FROM Products p "
                + "JOIN Categories c ON p.typeId = c.typeId "
                + "JOIN Accounts a ON p.account = a.account "
                + "WHERE p.productId = ?";

        try {
            ps = con.prepareStatement(sqCommand);
            ps.setString(1, id);
            rs = ps.executeQuery();
            if (rs.next()) {
                return toProduct(rs);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
            } catch (SQLException e) {
            }
            try {
                if (ps != null) {
                    ps.close();
                }
            } catch (SQLException e) {
            }
        }

        return null;
    }

    @Override
    public List<Product> listAll() {
        String sqlCommand
                = "SELECT p.*,"
                + "a.account AS a_account, "
                + "a.pass, "
                + "a.firstName, "
                + "a.lastName, "
                + "a.birthday, "
                + "a.gender, "
                + "a.phone, "
                + "a.roleInSystem, "
                + "a.isUse, "
                + "c.typeId AS c_typeId, "
                + "c.categoryName, "
                + "c.memo "
                + "FROM Products p "
                + "JOIN Categories c ON p.typeId = c.typeId "
                + "JOIN Accounts a ON p.account = a.account ";

        List<Product> list = new ArrayList<>();
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            ps = con.prepareStatement(sqlCommand);
            rs = ps.executeQuery();
            while (rs.next()) {
                Product pro = toProduct(rs);
                list.add(pro);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
            } catch (SQLException e) {
            }
            try {
                if (ps != null) {
                    ps.close();
                }
            } catch (SQLException e) {
            }
        }

        return list;
    }

    public List<Product> listByCategory(int categoryId) {

        if (categoryId <= 0) {
            return new ArrayList<>();
        }

        String sqlCommand
                = "SELECT p.*, "
                + "       a.account AS a_account, "
                + "       a.pass, "
                + "       a.firstName, "
                + "       a.lastName, "
                + "       a.birthday, "
                + "       a.gender, "
                + "       a.phone, "
                + "       a.roleInSystem, "
                + "       a.isUse, "
                + "       c.typeId AS c_typeId, "
                + "       c.categoryName, "
                + "       c.memo "
                + "FROM Products p "
                + "JOIN Categories c ON p.typeId = c.typeId "
                + "JOIN Accounts a ON p.account = a.account "
                + "WHERE p.typeId = ?";

        List<Product> list = new ArrayList<>();
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            ps = con.prepareStatement(sqlCommand);
            ps.setInt(1, categoryId);
            rs = ps.executeQuery();
            while (rs.next()) {
                Product pro = toProduct(rs);
                list.add(pro);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName())
                    .log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
            } catch (SQLException e) {
            }
            try {
                if (ps != null) {
                    ps.close();
                }
            } catch (SQLException e) {
            }
        }
        return list;
    }

    private Product toProduct(ResultSet rs) throws SQLException {

        Product pro = new Product();

        pro.setProductId(rs.getString("productId"));
        pro.setProductName(rs.getString("productName"));
        pro.setProductImage(rs.getString("productImage"));
        pro.setBrief(rs.getString("brief"));
        pro.setPostedDate(rs.getDate("postedDate"));
        pro.setPrice(rs.getInt("price"));
        pro.setDiscount(rs.getInt("discount"));
        pro.setUnit(rs.getString("unit"));

        Account acc = new Account();
        acc.setAccount(rs.getString("a_account"));
        acc.setBirthday(rs.getDate("birthday"));
        acc.setFirstName(rs.getString("firstName"));
        acc.setGender(rs.getBoolean("gender"));
        acc.setUsed(rs.getBoolean("isUse"));
        acc.setLastName(rs.getString("lastName"));
        acc.setPass(rs.getString("pass"));
        acc.setPhone(rs.getString("phone"));
        acc.setRoleInSystem(rs.getInt("roleInSystem"));

        Category cat = new Category();
        cat.setCategoryName(rs.getString("categoryName"));
        cat.setMemo(rs.getString("memo"));
        cat.setTypeId(rs.getInt("c_typeId"));

        pro.setAccount(acc);
        pro.setType(cat);

        return pro;
    }
}
