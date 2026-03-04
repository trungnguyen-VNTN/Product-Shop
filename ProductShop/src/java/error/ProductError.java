
package error;


public class ProductError {
    
    private String productIdError;
    private String productNameError;
    private String productImageError;
    private String briefError;
    private String postedDateError;
    private String typeIdError;
    private String accountError;
    private String unitError;
    private String priceError;
    private String discountError;

    public ProductError() {
    }

    public ProductError(String productIdError, String productNameError, String productImageError, String briefError, String postedDateError, String typeIdError, String accountError, String unitError, String priceError, String discountError) {
        this.productIdError = productIdError;
        this.productNameError = productNameError;
        this.productImageError = productImageError;
        this.briefError = briefError;
        this.postedDateError = postedDateError;
        this.typeIdError = typeIdError;
        this.accountError = accountError;
        this.unitError = unitError;
        this.priceError = priceError;
        this.discountError = discountError;
    }

    public String getProductIdError() {
        return productIdError;
    }

    public void setProductIdError(String productIdError) {
        this.productIdError = productIdError;
    }

    public String getProductNameError() {
        return productNameError;
    }

    public void setProductNameError(String productNameError) {
        this.productNameError = productNameError;
    }

    public String getProductImageError() {
        return productImageError;
    }

    public void setProductImageError(String productImageError) {
        this.productImageError = productImageError;
    }

    public String getBriefError() {
        return briefError;
    }

    public void setBriefError(String briefError) {
        this.briefError = briefError;
    }

    public String getPostedDateError() {
        return postedDateError;
    }

    public void setPostedDateError(String postedDateError) {
        this.postedDateError = postedDateError;
    }

    public String getTypeIdError() {
        return typeIdError;
    }

    public void setTypeIdError(String typeIdError) {
        this.typeIdError = typeIdError;
    }

    public String getAccountError() {
        return accountError;
    }

    public void setAccountError(String accountError) {
        this.accountError = accountError;
    }

    public String getUnitError() {
        return unitError;
    }

    public void setUnitError(String unitError) {
        this.unitError = unitError;
    }

    public String getPriceError() {
        return priceError;
    }

    public void setPriceError(String priceError) {
        this.priceError = priceError;
    }

    public String getDiscountError() {
        return discountError;
    }

    public void setDiscountError(String discountError) {
        this.discountError = discountError;
    }
    
    
}
