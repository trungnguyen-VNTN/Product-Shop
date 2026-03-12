package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import model.Account;
import model.Category;
import model.OrderDetail;
import model.Product;
import util.ConnectDB;


public class OrderDetailDAO implements Accessible<OrderDetail>{

    private Connection con;

    public OrderDetailDAO() throws Exception{
        con = new ConnectDB().getConnection();
    }

    @Override
    public int insertRec(OrderDetail obj) {

        String sql =
        "INSERT INTO orderDetail(orderId, productId, quantity, price, discount) VALUES(?,?,?,?,?)";

        try(PreparedStatement ps = con.prepareStatement(sql)){

            ps.setInt(1, obj.getOrderId());
            ps.setString(2, obj.getProductId().getProductId());
            ps.setInt(3, obj.getQuantity());
            ps.setInt(4, obj.getPrice());
            ps.setInt(5, obj.getDiscount());

            return ps.executeUpdate();

        }catch(Exception e){
            e.printStackTrace();
        }

        return 0;
    }

    @Override
    public int updateRec(OrderDetail obj) {

        String sql =
        "UPDATE orderDetail SET quantity=?, price=?, discount=? WHERE orderId=? AND productId=?";

        try(PreparedStatement ps = con.prepareStatement(sql)){

            ps.setInt(1, obj.getQuantity());
            ps.setInt(2, obj.getPrice());
            ps.setInt(3, obj.getDiscount());
            ps.setInt(4, obj.getOrderId());
            ps.setString(5, obj.getProductId().getProductId());

            return ps.executeUpdate();

        }catch(Exception e){
            e.printStackTrace();
        }

        return 0;
    }

    @Override
    public int deleteRec(OrderDetail obj) {

        String sql =
        "DELETE FROM orderDetail WHERE orderId=? AND productId=?";

        try(PreparedStatement ps = con.prepareStatement(sql)){

            ps.setInt(1, obj.getOrderId());
            ps.setString(2, obj.getProductId().getProductId());

            return ps.executeUpdate();

        }catch(Exception e){
            e.printStackTrace();
        }

        return 0;
    }

    @Override
    public OrderDetail getObjectById(String id) {

        String sql =
        "SELECT od.*, " +
        "p.productId, p.productName, p.productImage, p.brief, p.postedDate, p.unit, p.price AS productPrice, p.discount AS productDiscount, " +
        "c.typeId, c.categoryName, " +
        "a.account, a.pass, a.lastName, a.firstName, a.birthday, a.gender, a.phone AS accountPhone, a.isUse, a.roleInSystem " +
        "FROM orderDetail od " +
        "JOIN products p ON od.productId = p.productId " +
        "JOIN categories c ON p.typeId = c.typeId " +
        "JOIN accounts a ON p.account = a.account " +
        "WHERE od.productId = ?";

        try(PreparedStatement ps = con.prepareStatement(sql)){

            ps.setString(1, id);

            ResultSet rs = ps.executeQuery();

            if(rs.next()){
                return toOrderDetail(rs);
            }

        }catch(Exception e){
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public List<OrderDetail> listAll() {

        List<OrderDetail> list = new ArrayList<>();

        String sql =
        "SELECT od.*, " +
        "p.productId, p.productName, p.productImage, p.brief, p.postedDate, p.unit, p.price AS productPrice, p.discount AS productDiscount, " +
        "c.typeId, c.categoryName, " +
        "a.account, a.pass, a.lastName, a.firstName, a.birthday, a.gender, a.phone AS accountPhone, a.isUse, a.roleInSystem " +
        "FROM orderDetail od " +
        "JOIN products p ON od.productId = p.productId " +
        "JOIN categories c ON p.typeId = c.typeId " +
        "JOIN accounts a ON p.account = a.account";

        try(PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery()){

            while(rs.next()){

                OrderDetail od = toOrderDetail(rs);
                list.add(od);

            }

        }catch(Exception e){
            e.printStackTrace();
        }

        return list;
    }

    private OrderDetail toOrderDetail(ResultSet rs) throws Exception{

        OrderDetail od = new OrderDetail();

        od.setOrderId(rs.getInt("orderId"));
        od.setQuantity(rs.getInt("quantity"));
        od.setPrice(rs.getInt("price"));
        od.setDiscount(rs.getInt("discount"));

        Product p = new Product();

        p.setProductId(rs.getString("productId"));
        p.setProductName(rs.getString("productName"));
        p.setProductImage(rs.getString("productImage"));
        p.setBrief(rs.getString("brief"));
        p.setPostedDate(rs.getDate("postedDate"));
        p.setUnit(rs.getString("unit"));
        p.setPrice(rs.getInt("productPrice"));
        p.setDiscount(rs.getInt("productDiscount"));

        Category c = new Category();
        c.setTypeId(rs.getInt("typeId"));
        c.setCategoryName(rs.getString("categoryName"));

        p.setType(c);

        Account a = new Account();
        a.setAccount(rs.getString("account"));
        a.setPass(rs.getString("pass"));
        a.setLastName(rs.getString("lastName"));
        a.setFirstName(rs.getString("firstName"));
        a.setBirthday(rs.getDate("birthday"));
        a.setGender(rs.getBoolean("gender"));
        a.setPhone(rs.getString("accountPhone"));
        a.setUsed(rs.getBoolean("isUse"));
        a.setRoleInSystem(rs.getInt("roleInSystem"));

        p.setAccount(a);

        od.setProductId(p);

        return od;
    }

}