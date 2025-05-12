import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class BankAppUI extends Application {

    private AccountManager accountManager = new AccountManager();

    @Override
    public void start(Stage primaryStage) {
        Button loginButton = new Button("Log In");

        VBox loginLayout = new VBox(20);
        loginLayout.getChildren().add(loginButton);

        loginButton.setOnAction(e -> showBalance(primaryStage));

        Scene loginScene = new Scene(loginLayout, 300, 200);

        primaryStage.setTitle("Bank App");
        primaryStage.setScene(loginScene);
        primaryStage.show();
    }

    private void showBalance(Stage primaryStage) {
        String accountNumber = "12345";

        Account account = accountManager.getAccount(accountNumber);

        if (account != null) {
            showAccountBalance(primaryStage, account);
        } else {
            showErrorMessage("Account not found.");
        }
    }

    private void showAccountBalance(Stage primaryStage, Account account) {
        VBox balanceLayout = new VBox(20);

        Text balanceText = new Text("Your Account Balance: $" + account.getBalance());

        Button logoutButton = new Button("Log Out");
        logoutButton.setOnAction(e -> showLoginScreen(primaryStage));

        balanceLayout.getChildren().addAll(balanceText, logoutButton);

        Scene balanceScene = new Scene(balanceLayout, 300, 200);

        primaryStage.setScene(balanceScene);
    }

    private void showErrorMessage(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText("Account Error");
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void showLoginScreen(Stage primaryStage) {
        Button loginButton = new Button("Log In");

        VBox loginLayout = new VBox(20);
        loginLayout.getChildren().add(loginButton);

        loginButton.setOnAction(e -> showBalance(primaryStage));

        Scene loginScene = new Scene(loginLayout, 300, 200);

        primaryStage.setScene(loginScene);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
