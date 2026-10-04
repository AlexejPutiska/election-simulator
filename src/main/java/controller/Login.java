package controller;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import model.CurrentUser;

/**
 * Handles the authentication of users by verifying credentials against stored values in a text file.
 */
public class Login {

    private ScreenController screenController;

    /**
     * Constructs a Login object with a reference to the ScreenController to manage UI navigation based on login outcomes.
     *
     * @param screenController The controller responsible for managing screen transitions within the application.
     */
    public Login(ScreenController screenController) {
        this.screenController = screenController;
    }

    /**
     * Validates user credentials against the entries in a predetermined file.
     * If the credentials are valid, it sets the current user in the application and navigates to the main menu.
     * If the credentials are invalid, it displays a login error alert.
     *
     * @param username The username entered by the user.
     * @param password The password entered by the user.
     */
    public void validateLogin(String username, String password) {
        try (BufferedReader reader = new BufferedReader(new FileReader("src/main/resources/files/users.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(":");
                if (parts[0].equals(username) && parts[1].equals(password)) {
                    CurrentUser.getInstance().setUsername(username);
                    screenController.showMenuScreen();
                    return;
                }
            }
            screenController.showAlertLogin();
        } catch (IOException e) {
            screenController.showAlertLogin();
        }
    }
}
