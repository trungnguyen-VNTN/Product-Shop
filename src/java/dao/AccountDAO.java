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
import util.ConnectDB;

public class AccountDAO implements Accessible<Account> {

    private ServletContext sc;
    private Connection con;

    public AccountDAO() throws ClassNotFoundException, SQLException {
        ConnectDB db = new ConnectDB();
        this.con = db.getConnection();
    }

    public AccountDAO(ServletContext sc) throws ClassNotFoundException, SQLException {
        ConnectDB db = new ConnectDB(sc);
        this.con = db.getConnection();
    }

    @Override
    public int insertRec(Account obj) {
        int result = 0;
        try {
            String sqlCommand = "INSERT INTO Account VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement ps = con.prepareStatement(sqlCommand);
            ps.setString(1, obj.getAccount());
            ps.setString(2, obj.getPass());
            ps.setString(3, obj.getLastName());
            ps.setString(4, obj.getFirstName());
            ps.setDate(5, obj.getBirthday());
            ps.setBoolean(6, obj.isGender());
            ps.setString(7, obj.getPhone());
            ps.setBoolean(8, obj.isUsed());
            ps.setInt(9, obj.getRoleInSystem());
            result = ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    @Override
    public int updateRec(Account obj) {
        int result = 0;
        try {
            String sqlCommand = "UPDATE Account SET "
                    + "pass = ?,"
                    + "lastName = ?,"
                    + "firstName = ?,"
                    + "birthday = ?,"
                    + "gender = ?,"
                    + "phone = ?,"
                    + "isUse = ?,"
                    + "roleInSystem = ? "
                    + "WHERE account = ?";
            PreparedStatement ps = con.prepareStatement(sqlCommand);
            ps.setString(1, obj.getPass());
            ps.setString(2, obj.getLastName());
            ps.setString(3, obj.getFirstName());
            ps.setDate(4, obj.getBirthday());
            ps.setBoolean(5, obj.isGender());
            ps.setString(6, obj.getPhone());
            ps.setBoolean(7, obj.isUsed());
            ps.setInt(8, obj.getRoleInSystem());
            ps.setString(9, obj.getAccount());
            result = ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    @Override
    public int deleteRec(Account obj) {
        int result = 0;
        try {
            String sqlCommand = "DELETE FROM Account WHERE account = ?";
            PreparedStatement ps = con.prepareStatement(sqlCommand);
            ps.setString(1, obj.getAccount());
            result = ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    @Override
    public Account getObjectById(String id) {
        try {
            String sqlCommand = "SELECT * FROM Account WHERE account = ?";
            PreparedStatement ps = con.prepareStatement(sqlCommand);
            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();
            Account result = null;
            if (rs.next()) {
                result = new Account();
                result.setAccount(rs.getString("account"));
                result.setPass(rs.getString("pass"));
                result.setLastName(rs.getString("lastName"));
                result.setFirstName(rs.getString("firstName"));
                result.setBirthday(rs.getDate("birthday"));
                result.setGender(rs.getBoolean("gender"));
                result.setPhone(rs.getString("phone"));
                result.setUsed(rs.getBoolean("isUse"));
                result.setRoleInSystem(rs.getInt("roleInSystem"));
            }
            if (result != null) {
                return result;
            }
        } catch (SQLException ex) {
            Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public List<Account> listAll() {
        List<Account> result = new ArrayList<>();
        try {
            String sqlCommand = "SELECT * FROM Account";
            PreparedStatement ps = con.prepareStatement(sqlCommand);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Account account = new Account();
                account.setAccount(rs.getString("account"));
                account.setPass(rs.getString("pass"));
                account.setLastName(rs.getString("lastName"));
                account.setFirstName(rs.getString("firstName"));
                account.setBirthday(rs.getDate("birthday"));
                account.setGender(rs.getBoolean("gender"));
                account.setPhone(rs.getString("phone"));
                account.setUsed(rs.getBoolean("isUse"));
                account.setRoleInSystem(rs.getInt("roleInSystem"));
                result.add(account);
            }
        } catch (SQLException ex) {
            Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

}
