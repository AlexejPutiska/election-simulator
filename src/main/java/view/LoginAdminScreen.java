package view;

import controller.ScreenController;
import controller.LoginAdmin;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;

/**
 * The LoginAdminScreen class provides the user interface for administrator login in the ElectionSim application.
 * It features text fields for entering username and password and a button to submit the login credentials.
 */
public class LoginAdminScreen {
    private Scene scene;
    private Button btnLogin;
    private TextField txtUsername;
    private PasswordField txtPassword;

    private LoginAdmin loginAdmin;

    /**
     * Constructs a new LoginAdminScreen which contains fields for inputting login credentials and a button to submit them.
     * This screen is used specifically for administrator login, providing entry into the admin part of the application.
     *
     * @param screenController the controller used to manage transitions between screens and handling post-login actions
     */

    public LoginAdminScreen(ScreenController screenController) {
        loginAdmin = new LoginAdmin(screenController);
        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setPadding(new Insets(25, 25, 25, 25));

        Label lblUsername = new Label("Admin username:");
        lblUsername.setFont(Font.font("Arial", 16));
        txtUsername = new TextField();
        txtUsername.setPrefColumnCount(10);

        Label lblPassword = new Label("Admin password:");
        lblPassword.setFont(Font.font("Arial", 16));
        txtPassword = new PasswordField();
        txtPassword.setPrefColumnCount(10);

        btnLogin = new Button("Login");
        btnLogin.setFont(Font.font("Arial", 16));
        btnLogin.setOnAction(e -> {
            String username = txtUsername.getText();
            String password = txtPassword.getText();
            txtUsername.clear();
            txtPassword.clear();
            loginAdmin.validateLogin(username, password);
        });

        gridPane.add(lblUsername, 0, 0);
        gridPane.add(txtUsername, 1, 0);
        gridPane.add(lblPassword, 0, 1);
        gridPane.add(txtPassword, 1, 1);
        gridPane.add(btnLogin, 1, 2);

        this.scene = new Scene(gridPane, 600, 500);

        Platform.runLater(() -> {
            gridPane.requestFocus();
        });
    }

    /**
     * Returns the Scene containing the UI elements of the LoginAdminScreen.
     * This scene is what is displayed on the stage when the LoginAdminScreen is active.
     *
     * @return the scene of the LoginAdminScreen
     */
    public Scene getScene() {
        return this.scene;
    }
}