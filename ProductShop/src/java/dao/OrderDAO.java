package dao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import model.Account;
import model.Order;
import util.ConnectDB;

public class OrderDAO implements Accessible<Order>{

    private Connection con;

    public OrderDAO() throws Exception{
        con = new ConnectDB().getConnection();
    }

    @Override
    public int insertRec(Order obj) {

        String sql =
        "INSERT INTO orders(account, shippingAddress, phone, totalAmount, status) VALUES(?,?,?,?,?)";

        try(PreparedStatement ps = con.prepareStatement(sql)){

            ps.setString(1, obj.getAccount().getAccount());
            ps.setString(2, obj.getShippingAddress());
            ps.setString(3, obj.getPhone());
            ps.setInt(4, obj.getTotalAmount());
            ps.setInt(5, obj.getStatus());

            return ps.executeUpdate();

        }catch(Exception e){
            e.printStackTrace();
        }

        return 0;
    }

    @Override
    public int updateRec(Order obj) {

        String sql =
        "UPDATE orders SET shippingAddress=?, phone=?, totalAmount=?, status=? WHERE orderId=?";

        try(PreparedStatement ps = con.prepareStatement(sql)){

            ps.setString(1, obj.getShippingAddress());
            ps.setString(2, obj.getPhone());
            ps.setInt(3, obj.getTotalAmount());
            ps.setInt(4, obj.getStatus());
            ps.setInt(5, obj.getOrderId());

            return ps.executeUpdate();

        }catch(Exception e){
            e.printStackTrace();
        }

        return 0;
    }

    @Override
    public int deleteRec(Order obj) {

        String sql = "DELETE FROM orders WHERE orderId=?";

        try(PreparedStatement ps = con.prepareStatement(sql)){

            ps.setInt(1, obj.getOrderId());

            return ps.executeUpdate();

        }catch(Exception e){
            e.printStackTrace();
        }

        return 0;
    }

    @Override
    public Order getObjectById(String id) {

        String sql =
        "SELECT o.*, a.* " +
        "FROM orders o JOIN accounts a ON o.account = a.account " +
        "WHERE o.orderId=?";

        try(PreparedStatement ps = con.prepareStatement(sql)){

            ps.setInt(1, Integer.parseInt(id));

            ResultSet rs = ps.executeQuery();

            if(rs.next()){
                return toOrder(rs);
            }

        }catch(Exception e){
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public List<Order> listAll() {

        List<Order> list = new ArrayList<>();

        String sql =
        "SELECT o.*, a.* " +
        "FROM orders o JOIN accounts a ON o.account = a.account";

        try(PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery()){

            while(rs.next()){

                Order o = toOrder(rs);
                list.add(o);

            }

        }catch(Exception e){
            e.printStackTrace();
        }

        return list;
    }

    private Order toOrder(ResultSet rs) throws Exception{

        Order o = new Order();

        o.setOrderId(rs.getInt("orderId"));
        o.setOrderDate(rs.getDate("orderDate"));
        o.setShippingAddress(rs.getString("shippingAddress"));
        o.setPhone(rs.getString("phone"));
        o.setTotalAmount(rs.getInt("totalAmount"));
        o.setStatus(rs.getInt("status"));

        Account a = new Account();

        a.setAccount(rs.getString("account"));
        a.setPass(rs.getString("pass"));
        a.setLastName(rs.getString("lastName"));
        a.setFirstName(rs.getString("firstName"));
        a.setBirthday(rs.getDate("birthday"));
        a.setGender(rs.getBoolean("gender"));
        a.setPhone(rs.getString("phone"));
        a.setUsed(rs.getBoolean("isUse"));
        a.setRoleInSystem(rs.getInt("roleInSystem"));

        o.setAccount(a);

        return o;
    }

}