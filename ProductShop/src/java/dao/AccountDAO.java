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
        this.sc = sc;
        this.con = getConnect(sc);
    }

    private Connection getConnect(ServletContext sc) throws ClassNotFoundException, SQLException {
        return new ConnectDB(sc).getConnection();
    }

    @Override
    public int insertRec(Account obj) {
        if (obj == null || obj.getAccount() == null || obj.getAccount().isEmpty()) {
            return 0;
        }
        int result = 0;
        PreparedStatement ps = null;
        try {
            String sqlCommand = "INSERT INTO Accounts VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            ps = con.prepareStatement(sqlCommand);
            ps.setString(1, obj.getAccount());
            ps.setString(2, obj.getPass());
            ps.setString(3, obj.getLastName());
            ps.setString(4, obj.getFirstName());
            ps.setDate(5, obj.getBirthday());
            ps.setBoolean(6, obj.isGender());
            ps.setString(7, obj.getPhone());
            ps.setBoolean(8, obj.isUsed());
            ps.setInt(9, obj.getRoleInSystem());
            ps.setInt(10, obj.getPriceSegment());
            result = ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
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
    public int updateRec(Account obj) {
        if (obj == null || obj.getAccount() == null || obj.getAccount().isEmpty()) {
            return 0;
        }
        int result = 0;
        PreparedStatement ps = null;
        try {
            String sqlCommand = "UPDATE Accounts SET "
                    + "pass = ?,"
                    + "lastName = ?,"
                    + "firstName = ?,"
                    + "birthday = ?,"
                    + "gender = ?,"
                    + "phone = ?,"
                    + "isUse = ?,"
                    + "roleInSystem = ?, "
                    + "priceSegment = ? "
                    + "WHERE account = ?";
            ps = con.prepareStatement(sqlCommand);
            ps.setString(1, obj.getPass());
            ps.setString(2, obj.getLastName());
            ps.setString(3, obj.getFirstName());
            ps.setDate(4, obj.getBirthday());
            ps.setBoolean(5, obj.isGender());
            ps.setString(6, obj.getPhone());
            ps.setBoolean(7, obj.isUsed());
            ps.setInt(8, obj.getRoleInSystem());
            ps.setInt(9, obj.getPriceSegment());
            ps.setString(10, obj.getAccount());
            result = ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
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
    public int deleteRec(Account obj) {
        if (obj == null || obj.getAccount() == null || obj.getAccount().isEmpty()) {
            return 0;
        }
        int result = 0;
        PreparedStatement ps = null;
        try {
            String sqlCommand = "DELETE FROM Accounts WHERE account = ?";
            ps = con.prepareStatement(sqlCommand);
            ps.setString(1, obj.getAccount());
            result = ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
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
    public Account getObjectById(String id) {
        if (id == null || id.isEmpty()) {
            return null;
        }
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            String sqlCommand = "SELECT * FROM Accounts WHERE account = ?";
            ps = con.prepareStatement(sqlCommand);
            ps.setString(1, id);
            rs = ps.executeQuery();
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
                result.setPriceSegment(rs.getInt("priceSegment"));
            }
            if (result != null) {
                return result;
            }
        } catch (SQLException ex) {
            Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
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
    public List<Account> listAll() {
        List<Account> result = new ArrayList<>();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            String sqlCommand = "SELECT * FROM Accounts";
            ps = con.prepareStatement(sqlCommand);
            rs = ps.executeQuery();
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
                account.setPriceSegment(rs.getInt("priceSegment"));
                result.add(account);
            }
        } catch (SQLException ex) {
            Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
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
        return result;
    }

    public List<Account> listByRole(int role) {
        List<Account> result = new ArrayList<>();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            String sqlCommand = "SELECT * FROM Accounts WHERE roleInSystem = ? ";
            ps = con.prepareStatement(sqlCommand);
            ps.setInt(1, role);
            rs = ps.executeQuery();
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
                account.setPriceSegment(rs.getInt("priceSegment"));
                result.add(account);
            }
        } catch (SQLException ex) {
            Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
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
        return result;
    }

    public int updateIsUsed(String acc, boolean isUsed) {
        if (acc == null || acc.isEmpty()) {
            return 0;
        }
        int change = 0;
        int result = 0;
        if (isUsed) {
            change = 1;
        } else {
            change = 0;
        }
        PreparedStatement ps = null;
        try {
            String sqlCommand
                    = "UPDATE Accounts SET "
                    + "isUse = ? "
                    + "WHERE account = ?";
            ps = con.prepareStatement(sqlCommand);
            ps.setInt(1, change);
            ps.setString(2, acc);
            result = ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
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

    public Account loginSuccess(String acc, String pass) {
        Account result = null;
        try {
            if (acc == null || pass == null || acc.isEmpty() || pass.isEmpty()) {
                return null;
            }
            String sqlCommand = "SELECT * FROM Accounts WHERE account = ? AND pass = ?";
            PreparedStatement ps = con.prepareStatement(sqlCommand);
            ps.setString(1, acc);
            ps.setString(2, pass);
            ResultSet rs = ps.executeQuery();
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
                result.setPriceSegment(rs.getInt("priceSegment"));
            }
            rs.close();
            ps.close();
            if (result != null) {
                return result;
            }
        } catch (SQLException ex) {
            Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    public int countAccounts() {
        int count = 0;
        try {
            String sql = "SELECT COUNT(*) FROM Accounts";
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

    public void updatePriceSegment(String account, int segment) throws Exception {

        String sql = "UPDATE accounts SET priceSegment = ? WHERE account = ?";

        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, segment);
            ps.setString(2, account);

            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
