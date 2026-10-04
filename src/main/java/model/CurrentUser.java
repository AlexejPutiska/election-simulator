package model;

/**
 * Represents the currently logged-in user in the application.
 * This class is a singleton to ensure that only one instance of the current user exists throughout the application lifecycle.
 * It extends the Person class, inheriting its basic properties, and adds functionality for handling user session data.
 */
public class CurrentUser extends Person {
    private static CurrentUser instance;

    private String username;
    private String filename;

    /**
     * Private constructor to prevent instantiation from outside the class.
     */
    private CurrentUser() {
    }

    /**
     * Provides access to the singleton instance of the CurrentUser.
     * If no instance exists, a new one is created and returned.
     *
     * @return The single instance of CurrentUser.
     */
    public static CurrentUser getInstance() {
        if (instance == null) {
            instance = new CurrentUser();
        }
        return instance;
    }

    /**
     * Sets the username of the current user and updates the associated filename for storing user data.
     *
     * @param username The username to set for the current user.
     */
    public void setUsername(String username) {
        this.username = username;
        this.filename = username + ".txt";
    }

    /**
     * Returns the username of the current user.
     *
     * @return The username of the current user.
     */
    public String getUsername() {
        return username;
    }

    /**
     * Returns the filename associated with the current user.
     * This filename is used for data storage.
     *
     * @return The filename associated with the current user.
     */
    public String getFilename() {
        return filename;
    }

    /**
     * Provides a string representation of the current user, including username and filename.
     *
     * @return A string representation of the current user.
     */
    @Override
    public String toString() {
        return super.toString() + ", Username: " + username + ", Filename: " + filename;
    }
}
