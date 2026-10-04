package controller;

/**
 * Manages the authentication process for administrative users.
 * This class is responsible for checking administrative login credentials and handling subsequent UI navigation.
 */
public class LoginAdmin {
    private ScreenController screenController;

    /**
     * Constructs a LoginAdmin object with a reference to the ScreenController.
     * The ScreenController is used to manage screen transitions based on the result of the login attempt.
     *
     * @param screenController The controller responsible for managing UI navigation.
     */
    public LoginAdmin(ScreenController screenController) {
        this.screenController = screenController;
    }

    /**
     * Validates the login credentials for an administrative user.
     * If the credentials match predefined values, the method directs the user to the administrative screen.
     * Otherwise, it triggers an alert indicating a login failure.
     *
     * @param username The username entered by the user.
     * @param password The password entered by the user.
     */
    public void validateLogin(String username, String password) {
        String correct = "admin";
        if (correct.equals(username) && correct.equals(password)) {
            screenController.showAdminScreen();
        } else {
            screenController.showAlertLogin();
        }
    }

}
