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
        ConnectDB db = new ConnectDB();
        this.con = db.getConnection();
    }

    public CategoryDAO(ServletContext sc) throws ClassNotFoundException, SQLException {
        this.sc = sc;
        this.con = getConnect(sc);
    }

    private Connection getConnect(ServletContext sc) throws ClassNotFoundException, SQLException {
        return new ConnectDB(sc).getConnection();
    }

    @Override
    public int insertRec(Category obj) {
        int result = 0;
        if (obj == null) {
            return 0;
        }
        try {
            Connection con = getConnect(sc);
            String sqlCommand = "INSERT INTO Categories VALUES ( ?, ?) ";
            PreparedStatement ps = con.prepareStatement(sqlCommand);
            ps.setString(1, obj.getCategoryName());
            ps.setString(2, obj.getMemo());
            result = ps.executeUpdate();
            ps.close();
            con.close();
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(CategoryDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    @Override
    public int updateRec(Category obj) {
        int result = 0;
        if (obj == null) {
            return 0;
        }
        try {
            String sqlCommand = "UPDATE Categories SET "
                    + "categoryName = ?,"
                    + "memo = ? "
                    + "WHERE typeId = ?";
            PreparedStatement ps = con.prepareStatement(sqlCommand);
            ps.setString(1, obj.getCategoryName());
            ps.setString(2, obj.getMemo());
            ps.setInt(3, obj.getTypeId());
            result = ps.executeUpdate();
            ps.close();
        } catch (SQLException ex) {
            Logger.getLogger(CategoryDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    @Override
    public int deleteRec(Category obj) {
        int result = 0;
        if (obj == null) {
            return 0;
        }
        try {
            String sqlCommand = "DELETE FROM Categories WHERE typeId = ?";
            PreparedStatement ps = con.prepareStatement(sqlCommand);
            ps.setInt(1, obj.getTypeId());
            result = ps.executeUpdate();
            ps.close();
        } catch (SQLException ex) {
            Logger.getLogger(CategoryDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    @Override
    public Category getObjectById(String id) {

        if (id == null || id.isEmpty()) {
            return null;
        }
        int typeId = Integer.parseInt(id);
        try {
            String sqlCommand = "SELECT * FROM Categories WHERE typeId = ?";
            PreparedStatement ps = con.prepareStatement(sqlCommand);
            ps.setInt(1, typeId);
            ResultSet rs = ps.executeQuery();
            Category result = null;
            if (rs.next()) {
                result = new Category();
                result.setTypeId(rs.getInt("typeId"));
                result.setCategoryName(rs.getString("categoryName"));
                result.setMemo(rs.getString("memo"));
            }
            rs.close();
            ps.close();
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
            String sqlCommand = "SELECT * FROM Categories";
            PreparedStatement ps = con.prepareStatement(sqlCommand);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Category category = new Category();
                category.setTypeId(rs.getInt("typeId"));
                category.setCategoryName(rs.getString("categoryName"));
                category.setMemo(rs.getString("memo"));
                result.add(category);
            }
            rs.close();
            ps.close();
        } catch (SQLException ex) {
            Logger.getLogger(CategoryDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    public int countCategories() {
        int count = 0;
        try {
            String sql = "SELECT COUNT(*) FROM Categories";
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                count = rs.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return count;
    }

}
