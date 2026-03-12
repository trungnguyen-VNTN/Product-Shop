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
import model.Cart;
import util.ConnectDB;

public class CartDAO implements Accessible<Cart> {

    private ServletContext sc;
    private Connection con;

    public CartDAO() throws ClassNotFoundException, SQLException {
        ConnectDB db = new ConnectDB();
        this.con = db.getConnection();
    }

    public CartDAO(ServletContext sc) throws ClassNotFoundException, SQLException {
        this.sc = sc;
        this.con = getConnect(sc);
    }

    private Connection getConnect(ServletContext sc) throws ClassNotFoundException, SQLException {
        return new ConnectDB(sc).getConnection();
    }

    @Override
    public int insertRec(Cart obj) {

        if (obj == null || obj.getAccount() == null) {
            return 0;
        }

        int result = 0;
        PreparedStatement ps = null;

        try {

            String sqlCommand = "INSERT INTO cart VALUES (?)";
            ps = con.prepareStatement(sqlCommand);

            ps.setString(1, obj.getAccount().getAccount());

            result = ps.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(CartDAO.class.getName()).log(Level.SEVERE, null, ex);
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
    public int updateRec(Cart obj) {

        if (obj == null || obj.getCartId() <= 0) {
            return 0;
        }

        int result = 0;
        PreparedStatement ps = null;

        try {

            String sqlCommand = "UPDATE cart SET account = ? WHERE cartId = ?";
            ps = con.prepareStatement(sqlCommand);

            ps.setString(1, obj.getAccount().getAccount());
            ps.setInt(2, obj.getCartId());

            result = ps.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(CartDAO.class.getName()).log(Level.SEVERE, null, ex);
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
    public int deleteRec(Cart obj) {

        if (obj == null || obj.getCartId() <= 0) {
            return 0;
        }

        int result = 0;
        PreparedStatement ps = null;

        try {

            String sqlCommand = "DELETE FROM cart WHERE cartId = ?";
            ps = con.prepareStatement(sqlCommand);

            ps.setInt(1, obj.getCartId());

            result = ps.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(CartDAO.class.getName()).log(Level.SEVERE, null, ex);
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
    public Cart getObjectById(String id) {

        if (id == null || id.isEmpty()) {
            return null;
        }

        PreparedStatement ps = null;
        ResultSet rs = null;

        String sqlCommand
                = "SELECT c.*, "
                + "a.account AS a_account, "
                + "a.pass, "
                + "a.firstName, "
                + "a.lastName, "
                + "a.birthday, "
                + "a.gender, "
                + "a.phone, "
                + "a.roleInSystem, "
                + "a.isUse "
                + "FROM cart c "
                + "JOIN accounts a ON c.account = a.account "
                + "WHERE c.cartId = ?";

        try {

            ps = con.prepareStatement(sqlCommand);
            ps.setInt(1, Integer.parseInt(id));

            rs = ps.executeQuery();

            if (rs.next()) {
                return toCart(rs);
            }

        } catch (SQLException ex) {
            Logger.getLogger(CartDAO.class.getName()).log(Level.SEVERE, null, ex);
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
    public List<Cart> listAll() {

        String sqlCommand
                = "SELECT c.*, "
                + "a.account AS a_account, "
                + "a.pass, "
                + "a.firstName, "
                + "a.lastName, "
                + "a.birthday, "
                + "a.gender, "
                + "a.phone, "
                + "a.roleInSystem, "
                + "a.isUse "
                + "FROM cart c "
                + "JOIN accounts a ON c.account = a.account";

        List<Cart> list = new ArrayList<>();

        PreparedStatement ps = null;
        ResultSet rs = null;

        try {

            ps = con.prepareStatement(sqlCommand);
            rs = ps.executeQuery();

            while (rs.next()) {

                Cart cart = toCart(rs);
                list.add(cart);

            }

        } catch (SQLException ex) {
            Logger.getLogger(CartDAO.class.getName()).log(Level.SEVERE, null, ex);
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

    private Cart toCart(ResultSet rs) throws SQLException {

        Cart cart = new Cart();

        cart.setCartId(rs.getInt("cartId"));

        Account acc = new Account();

        acc.setAccount(rs.getString("a_account"));
        acc.setPass(rs.getString("pass"));
        acc.setFirstName(rs.getString("firstName"));
        acc.setLastName(rs.getString("lastName"));
        acc.setBirthday(rs.getDate("birthday"));
        acc.setGender(rs.getBoolean("gender"));
        acc.setPhone(rs.getString("phone"));
        acc.setRoleInSystem(rs.getInt("roleInSystem"));
        acc.setUsed(rs.getBoolean("isUse"));

        cart.setAccount(acc);

        return cart;
    }

    public Cart getCartByAccount(String account) {

        if (account == null || account.isEmpty()) {
            return null;
        }

        PreparedStatement ps = null;
        ResultSet rs = null;

        String sqlCommand
                = "SELECT c.*, "
                + "a.account AS a_account, "
                + "a.pass, "
                + "a.firstName, "
                + "a.lastName, "
                + "a.birthday, "
                + "a.gender, "
                + "a.phone, "
                + "a.roleInSystem, "
                + "a.isUse "
                + "FROM cart c "
                + "JOIN accounts a ON c.account = a.account "
                + "WHERE a.account = ?";

        try {

            ps = con.prepareStatement(sqlCommand);
            ps.setString(1, account);

            rs = ps.executeQuery();

            if (rs.next()) {
                return toCart(rs);
            }

        } catch (SQLException ex) {
            Logger.getLogger(CartDAO.class.getName()).log(Level.SEVERE, null, ex);
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
}
