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
import model.Category;
import util.ConnectDB;

public class CategoryDAO implements Accessible<Category> {

    private Connection con;
    private ServletContext sc;

    public CategoryDAO() throws ClassNotFoundException, SQLException {
    }

    public CategoryDAO(ServletContext sc) throws ClassNotFoundException, SQLException {
        this.sc = sc;
    }
    
    private Connection getConnect(ServletContext sc) throws ClassNotFoundException, SQLException{
        ConnectDB db = new ConnectDB(sc);
        this.con = db.getConnection();
        return con;
    }

    @Override
    public int insertRec(Category obj) {
        int result = 0;
        try {
            Connection con = getConnect(sc);
            String sqlCommand = "INSERT INTO Category VALUES (?, ?, ?) ";
            PreparedStatement ps = con.prepareStatement(sqlCommand);
            ps.setInt(1, obj.getTypeId());
            ps.setString(2, obj.getCategoryName());
            ps.setString(3, obj.getMemo());
            result = ps.executeUpdate();
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(CategoryDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    @Override
    public int updateRec(Category obj) {
        int result = 0;
        try {
            String sqlCommand = "UPDATE Category SET "
                    + "categoryName = ?,"
                    + "memo = ? "
                    + "WHERE typeId = ?";
            PreparedStatement ps = con.prepareStatement(sqlCommand);
            ps.setString(1, obj.getCategoryName());
            ps.setString(2, obj.getMemo());
            ps.setInt(3, obj.getTypeId());
            result = ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(CategoryDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    @Override
    public int deleteRec(Category obj) {
        int result = 0;
        try {
            String sqlCommand = "DELETE FROM Category WHERE typeId = ?";
            PreparedStatement ps = con.prepareStatement(sqlCommand);
            ps.setInt(1, obj.getTypeId());
            result = ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(CategoryDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    @Override
    public Category getObjectById(String id) {
        try {
            String sqlCommand = "SELECT * FROM Category WHERE typeId = ?";
            PreparedStatement ps = con.prepareStatement(sqlCommand);
            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();
            Category result = null;
            if (rs.next()) {
                result = new Category();
                result.setTypeId(rs.getInt("typeId"));
                result.setCategoryName(rs.getString("categoryName"));
                result.setMemo(rs.getString("memo"));
            }
            if (result != null) {
                return result;
            }
        } catch (SQLException ex) {
            Logger.getLogger(CategoryDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public List<Category> listAll() {
        List<Category> result = new ArrayList<>();
        try {
            String sqlCommand = "SELECT * FROM Category";
            PreparedStatement ps = con.prepareStatement(sqlCommand);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Category category = new Category();
                category.setTypeId(rs.getInt("typeId"));
                category.setCategoryName(rs.getString("categoryName"));
                category.setMemo(rs.getString("memo"));
                result.add(category);
            }
        } catch (SQLException ex) {
            Logger.getLogger(CategoryDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

}
