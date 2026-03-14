package model;

public class Cart {

    private int cartId;
    private Account account;

    public Cart() {
    }

    public Cart(int cartId, Account account) {
        this.cartId = cartId;
        this.account = account;
    }

    public int getCartId() {
        return cartId;
    }

    public void setCartId(int cartId) {
        this.cartId = cartId;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

}
