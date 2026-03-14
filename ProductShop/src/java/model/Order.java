package model;

import java.sql.Date;

public class Order {

    private int orderId;
    private Account account;
    private Date orderDate;
    private String shippingAddress;
    private String phone;
    private int totalAmount;
    private int status;

    public Order() {
    }

    public Order(int orderId, Account account, Date orderDate, String shippingAddress, String phone, int totalAmount, int status) {
        this.orderId = orderId;
        this.account = account;
        this.orderDate = orderDate;
        this.shippingAddress = shippingAddress;
        this.phone = phone;
        this.totalAmount = totalAmount;
        this.status = status;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public String getShippingAddress() {
        return shippingAddress;
    }

    public void setShippingAddress(String shippingAddress) {
        this.shippingAddress = shippingAddress;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(int totalAmount) {
        this.totalAmount = totalAmount;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

}
