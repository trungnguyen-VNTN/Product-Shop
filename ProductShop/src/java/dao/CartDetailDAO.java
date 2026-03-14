package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletContext;
import model.Account;
import model.CartDetail;
import model.Category;
import model.Product;
import util.ConnectDB;

public class CartDetailDAO {

    private Connection con;
    private ServletContext sc;

    public CartDetailDAO() throws ClassNotFoundException, SQLException {
        ConnectDB db = new ConnectDB();
        this.con = db.getConnection();
    }

    public CartDetailDAO(ServletContext sc) throws ClassNotFoundException, SQLException {
        this.sc = sc;
        this.con = getConnect(sc);
    }

    private Connection getConnect(ServletContext sc) throws ClassNotFoundException, SQLException {
        return new ConnectDB(sc).getConnection();
    }

    public int insertRec(CartDetail obj) {

        String sql = "INSERT INTO cartDetail VALUES(?,?,?)";

        try ( PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, obj.getCartId());
            ps.setString(2, obj.getProductId().getProductId());
            ps.setInt(3, obj.getQuantity());

            return ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return 0;
    }

    public int updateRec(CartDetail obj) {

        String sql = "UPDATE cartDetail SET quantity=? WHERE cartId=? AND productId=?";

        try ( PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, obj.getQuantity());
            ps.setInt(2, obj.getCartId());
            ps.setString(3, obj.getProductId().getProductId());

            return ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return 0;
    }

    public int deleteRec(int cartId, String productId) {

        String sql = "DELETE FROM cartDetail WHERE cartId=? AND productId=?";

        try ( PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, cartId);
            ps.setString(2, productId);

            return ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return 0;
    }

    public CartDetail getObjectById(int cartId, String productId) {

        String sql
                = "SELECT cd.cartId, cd.quantity, "
                + "p.productId, p.productName, p.productImage, p.brief, p.postedDate, p.unit, p.price, p.discount, "
                + "c.typeId, c.categoryName, "
                + "a.account, a.firstName, a.lastName "
                + "FROM cartDetail cd "
                + "JOIN products p ON cd.productId = p.productId "
                + "JOIN categories c ON p.typeId = c.typeId "
                + "JOIN accounts a ON p.account = a.account "
                + "WHERE cd.productId = ? "
                + "AND cd.cartId = ?";

        try ( PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, productId);
            ps.setInt(2, cartId);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return toCartDetail(rs);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public List<CartDetail> listByCartId(int cartId) {

        List<CartDetail> list = new ArrayList<>();

        String sql
                = "SELECT cd.cartId, cd.quantity, "
                + "p.productId, p.productName, p.productImage, p.brief, p.postedDate, p.unit, p.price, p.discount, "
                + "c.typeId, c.categoryName, "
                + "a.account, a.firstName, a.lastName "
                + "FROM cartDetail cd "
                + "JOIN products p ON cd.productId = p.productId "
                + "JOIN categories c ON p.typeId = c.typeId "
                + "JOIN accounts a ON p.account = a.account "
                + "WHERE cd.cartId = ?";

        try ( PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, cartId);

            try ( ResultSet rs = ps.executeQuery()) {

                while (rs.next()) {

                    CartDetail cd = toCartDetail(rs);
                    list.add(cd);

                }

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    private CartDetail toCartDetail(ResultSet rs) throws Exception {

        CartDetail cd = new CartDetail();

        cd.setCartId(rs.getInt("cartId"));
        cd.setQuantity(rs.getInt("quantity"));

        Product p = new Product();

        p.setProductId(rs.getString("productId"));
        p.setProductName(rs.getString("productName"));
        p.setProductImage(rs.getString("productImage"));
        p.setBrief(rs.getString("brief"));
        p.setPostedDate(rs.getDate("postedDate"));
        p.setUnit(rs.getString("unit"));
        p.setPrice(rs.getInt("price"));
        p.setDiscount(rs.getInt("discount"));

        Category c = new Category();
        c.setTypeId(rs.getInt("typeId"));
        c.setCategoryName(rs.getString("categoryName"));

        p.setType(c);

        Account a = new Account();
        a.setAccount(rs.getString("account"));
        a.setFirstName(rs.getString("firstName"));
        a.setLastName(rs.getString("lastName"));

        p.setAccount(a);

        cd.setProductId(p);

        return cd;
    }

    public int countCartDetail(int cartId) throws Exception {
        int count = 0;
        String sql = "SELECT COUNT(*) FROM CartDetail WHERE cartId = ?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setInt(1, cartId);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            count = rs.getInt(1);
        }
        return count;
    }

    public void increaseQuantity(int cartId, String productId) {

        String sql = "UPDATE cartDetail SET quantity = quantity + 1 WHERE productId=? AND cartId = ?";

        try ( PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, productId);
            ps.setInt(2, cartId);
            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void decreaseQuantity(int cartId, String productId) {

        String sql = "UPDATE cartDetail SET quantity = quantity - 1 WHERE productId=? AND cartId = ? AND quantity > 1";

        try ( PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, productId);
            ps.setInt(2, cartId);
            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public int clearCartDetail(int cartId) {

        String sql = "DELETE FROM cartDetail WHERE cartId=?";

        try ( PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, cartId);

            return ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return 0;
    }
}
