package controller;

import javafx.scene.control.Alert;
import javafx.stage.Stage;
import view.*;

/**
 * Manages the transitions between different screens in the application.
 * This class is responsible for handling all the navigation logic,
 * including displaying various screens and alerts related to user interactions.
 */
public class ScreenController {

    private Stage primaryStage;
    private StartScreen startScreen;
    private LoginScreen loginScreen;
    private LoginAdminScreen loginAdminScreen;
    private LoginUserScreen loginUserScreen;
    private NewUserScreen newUserScreen;
    private MenuScreen menuScreen;
    private CandidatesScreen candidatesScreen;
    private AdminScreen adminScreen;
    private VoterInputScreen voterInputScreen;
    private ResultScreen resultScreen;

    /**
     * Constructs a ScreenController with the primary stage of the application.
     * Initializes screens used in the application.
     *
     * @param primaryStage The main stage into which the screens are placed.
     */
    public ScreenController(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.startScreen = new StartScreen(this);
        this.loginScreen = new LoginScreen(this);
        this.loginAdminScreen = new LoginAdminScreen(this);
        this.loginUserScreen = new LoginUserScreen(this);
        this.menuScreen = new MenuScreen(this);
        this.newUserScreen = new NewUserScreen(this);
        this.voterInputScreen = new VoterInputScreen(this);
    }

    public void showStartScreen() {
        primaryStage.setScene(startScreen.getScene());
    }

    public void showLoginScreen() {
        primaryStage.setScene(loginScreen.getScene());
    }

    public void showAdminScreen() {
        this.adminScreen = new AdminScreen(this);
        primaryStage.setScene(adminScreen.getScene());
    }

    public void showLoginAdminScreen() {
        primaryStage.setScene(loginAdminScreen.getScene());
    }
    public void showLoginUserScreen() {
        primaryStage.setScene(loginUserScreen.getScene());
    }

    public void showNewUserScreen() {
        primaryStage.setScene(newUserScreen.getScene());
    }
    public void showMenuScreen() {
        primaryStage.setScene(menuScreen.getScene());
    }

    public void showCandidatesScreen() {
        this.candidatesScreen = new CandidatesScreen(this);
        primaryStage.setScene(candidatesScreen.getScene());
    }

    public void showVoterInputScreen() {
        this.voterInputScreen = new VoterInputScreen(this);
        primaryStage.setScene(voterInputScreen.getScene());
    }

    public void showResultScreen() {
        this.resultScreen = new ResultScreen(this);
        primaryStage.setScene(resultScreen.getScene());
    }

    /**
     * Displays an alert for registration errors when required fields are not filled.
     */
    public void showAlertRegistration() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Input Error");
        alert.setHeaderText(null);
        alert.setContentText("Please enter both username and password.");
        alert.showAndWait();
    }

    /**
     * Displays an alert indicating the username chosen during registration is already taken.
     */
    public void showAlertRegistrationTaken() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Input Error");
        alert.setHeaderText(null);
        alert.setContentText("The username you entered is already taken. Please choose a different username.");
        alert.showAndWait();
    }

    /**
     * Displays an alert when user inputs for voting simulations are invalid.
     */
    public void showAlertVoting() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Input Error");
        alert.setHeaderText(null);
        alert.setContentText("Please ensure all fields contain valid numbers");
        alert.showAndWait();
    }

    /**
     * Displays an alert when the username or password entered during login is incorrect.
     */
    public void showAlertLogin() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Input Error");
        alert.setHeaderText(null);
        alert.setContentText("Invalid username or password. Please try again.");
        alert.showAndWait();
    }
}