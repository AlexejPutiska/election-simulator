package controller;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.File;
import java.io.IOException;

/**
 * Manages the registration of new users, ensuring username uniqueness and creating user-specific storage files.
 */
public class NewUserRegister {
    private ScreenController screenController;

    /**
     * Constructs a NewUserRegister with a reference to the ScreenController.
     *
     * @param screenController The controller used for managing screen transitions and displaying alerts.
     */
    public NewUserRegister(ScreenController screenController) {
        this.screenController = screenController;
    }

    /**
     * Registers a new user with a username and password. Checks for empty fields, username uniqueness,
     * and restricted usernames.
     * If registration is successful, it creates a file for the user.
     *
     * @param username The username of the new user.
     * @param password The password of the new user.
     */
    public void registration(String username, String password) {
        if (username.isEmpty() || password.isEmpty()) {
            screenController.showAlertRegistration();
            return;
        }
        String adminUsername = "admin";
        String numberOfVotedUsersUsername = "numberOfVotedUsers";
        if (adminUsername.equals(username) || numberOfVotedUsersUsername.equals(username)) {
            screenController.showAlertRegistrationTaken();
            return;
        }
        try (PrintWriter out = new PrintWriter(new FileWriter("src/main/resources/files/users.txt", true))) {
            if (userExists(username)){
                throw new IllegalArgumentException();
            }
            out.println(username + ":" + password);
            createPersonalFile(username);
            screenController.showLoginUserScreen();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        catch (IllegalArgumentException e){
            screenController.showAlertRegistrationTaken();
        }
    }

    /**
     * Checks if the given username already exists in the user file.
     *
     * @param username The username to check.
     * @return true if the username exists, false otherwise.
     */
    private boolean userExists(String username) {
        if ("users".equals(username)) {
            return true;
        }
        try (BufferedReader br = new BufferedReader(new FileReader("src/main/resources/files/users.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(":");
                if (parts.length > 0 && parts[0].equals(username)) {
                    return true;
                }
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    /**
     * Creates a file for the user to store personal data or preferences.
     *
     * @param username The username for which to create the file.
     * @throws IOException If there is an issue creating the file.
     */
    private void createPersonalFile(String username) throws IOException {
        File file = new File("src/main/resources/files/" + username + ".txt");
        if (!file.createNewFile()) {
            throw new IOException("Could not create personal file for user.");
        }
    }
}