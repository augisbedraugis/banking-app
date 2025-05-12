public class AccountManager {

    public Account getAccount(String accountNumber) {
        if (accountNumber.equals("12345")) {
            return new Account("12345", "John Doe", 5000.0);
        } else {
            return null;
        }
    }
}
