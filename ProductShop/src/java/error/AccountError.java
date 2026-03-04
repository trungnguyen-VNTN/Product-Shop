
package error;

public class AccountError {
    
    private String accountError;
    private String passError;
    private String lastNameError;
    private String firstNameError;
    private String birthdayError;
    private String phoneError;

    public AccountError() {
    }

    public AccountError(String accountError, String passError, String lastNameError, String firstNameError, String birthdayError, String phoneError, String roleError) {
        this.accountError = accountError;
        this.passError = passError;
        this.lastNameError = lastNameError;
        this.firstNameError = firstNameError;
        this.birthdayError = birthdayError;
        this.phoneError = phoneError;
    }

    public String getAccountError() {
        return accountError;
    }

    public void setAccountError(String accountError) {
        this.accountError = accountError;
    }

    public String getPassError() {
        return passError;
    }

    public void setPassError(String passError) {
        this.passError = passError;
    }

    public String getLastNameError() {
        return lastNameError;
    }

    public void setLastNameError(String lastNameError) {
        this.lastNameError = lastNameError;
    }

    public String getFirstNameError() {
        return firstNameError;
    }

    public void setFirstNameError(String firstNameError) {
        this.firstNameError = firstNameError;
    }

    public String getBirthdayError() {
        return birthdayError;
    }

    public void setBirthdayError(String birthdayError) {
        this.birthdayError = birthdayError;
    }

    public String getPhoneError() {
        return phoneError;
    }

    public void setPhoneError(String phoneError) {
        this.phoneError = phoneError;
    }


    
    
}
