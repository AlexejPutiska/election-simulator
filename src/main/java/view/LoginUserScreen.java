package view;

import controller.Login;
import controller.ScreenController;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.HPos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;

/**
 * The LoginUserScreen class provides the user interface for user login.
 * It features input fields for username and password, a login button, and a button to navigate to the new user registration screen.
 */

public class LoginUserScreen {
    private Scene scene;
    private Button btnLogin;
    private Button btnNew;
    private TextField txtUsername;
    private PasswordField txtPassword;
    private Login login;

    /**
     * Constructs a new LoginUserScreen that includes form fields for user authentication and buttons for login and registration.
     *
     * @param screenController the controller used to manage screen transitions and to handle login actions
     */
    public LoginUserScreen(ScreenController screenController) {
        login = new Login(screenController);
        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setPadding(new Insets(25, 25, 25, 25));

        Label lblUsername = new Label("Username:");
        lblUsername.setFont(Font.font("Arial", 16));
        txtUsername = new TextField();
        txtUsername.setPrefColumnCount(10);

        Label lblPassword = new Label("Password:");
        lblPassword.setFont(Font.font("Arial", 16));
        txtPassword = new PasswordField();
        txtPassword.setPrefColumnCount(10);

        btnLogin = new Button("Login");
        btnLogin.setFont(Font.font("Arial", 16));
        btnLogin.setOnAction(e -> {
            String username = txtUsername.getText();
            String password = txtPassword.getText();
            login.validateLogin(username, password);
            txtUsername.clear();
            txtPassword.clear();
        });

        btnNew = new Button("New User");
        btnNew.setFont(Font.font("Arial", 16));
        btnNew.setOnAction(e -> {
            txtUsername.clear();
            txtPassword.clear();
            screenController.showNewUserScreen();
        });

        gridPane.add(lblUsername, 0, 1);
        gridPane.add(txtUsername, 1, 1);
        gridPane.add(lblPassword, 0, 2);
        gridPane.add(txtPassword, 1, 2);
        gridPane.add(btnLogin, 1, 3);

        GridPane.setColumnSpan(btnNew, 2);
        GridPane.setHalignment(btnNew, HPos.CENTER);
        gridPane.add(btnNew, 0, 0, 2, 1);
        GridPane.setMargin(btnNew, new Insets(-300, 0, 0, 0));

        this.scene = new Scene(gridPane, 600, 500);
    }

    /**
     * Returns the Scene containing the UI elements of the LoginUserScreen.
     * This scene is what is displayed on the stage when the LoginUserScreen is active.
     *
     * @return the scene of the LoginUserScreen
     */
    public Scene getScene() {
        return this.scene;
    }
}
