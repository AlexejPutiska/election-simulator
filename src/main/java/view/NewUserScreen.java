package view;

import controller.ScreenController;
import controller.NewUserRegister;
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
 * The NewUserScreen class provides a user interface for new users to register.
 * It features input fields for setting up a new username and password, and a button to submit the registration.
 */
public class NewUserScreen {
    private Scene scene;
    private Button btnLogin;
    private TextField txtUsername;
    private PasswordField txtPassword;
    private NewUserRegister newUserRegister;

    /**
     * Constructs a new user registration screen that includes form fields for registration credentials and a button to submit them.
     * This screen is used specifically for new user account creation within the ElectionSim application.
     *
     * @param screenController the controller used to manage screen transitions and to handle registration actions
     */
    public NewUserScreen(ScreenController screenController) {
        newUserRegister = new NewUserRegister(screenController);
        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setPadding(new Insets(25, 25, 25, 25));

        Label lblUsername = new Label("New username:");
        lblUsername.setFont(Font.font("Arial", 16));
        txtUsername = new TextField();
        txtUsername.setPrefColumnCount(10);

        Label lblPassword = new Label("New password:");
        lblPassword.setFont(Font.font("Arial", 16));
        txtPassword = new PasswordField();
        txtPassword.setPrefColumnCount(10);

        btnLogin = new Button("Register");
        btnLogin.setFont(Font.font("Arial", 16));
        btnLogin.setOnAction(e -> {
            String username = txtUsername.getText();
            String password = txtPassword.getText();
            newUserRegister.registration(username, password);
            txtUsername.clear();
            txtPassword.clear();
        });

        gridPane.add(lblUsername, 0, 0);
        gridPane.add(txtUsername, 1, 0);
        gridPane.add(lblPassword, 0, 1);
        gridPane.add(txtPassword, 1, 1);
        gridPane.add(btnLogin, 1, 2);

        this.scene = new Scene(gridPane, 600, 500);
    }

    /**
     * Returns the Scene containing the UI elements of the NewUserScreen.
     * This scene is what is displayed on the stage when the NewUserScreen is active.
     *
     * @return the scene of the NewUserScreen
     */
    public Scene getScene() {
        return this.scene;
    }
}