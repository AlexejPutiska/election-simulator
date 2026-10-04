package view;

import controller.ScreenController;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

/**
 * The LoginScreen class provides the interface for user authentication in the ElectionSim application.
 * It features two main buttons, one for logging in as a standard user and one for logging in as an admin.
 */

public class LoginScreen {
    private Scene scene;
    private Button btnTryElectionSim;
    private Button btnShowCandidates;

    /**
     * Constructs the login screen with buttons for user and admin login.
     * Each button is configured to navigate to the respective screen based on the user's choice of login type.
     *
     * @param screenController the controller used to manage transitions between screens
     */

    public LoginScreen(ScreenController screenController) {
        this.btnTryElectionSim = new Button("Login as user");
        this.btnTryElectionSim.setOnAction(e -> screenController.showLoginUserScreen());

        this.btnShowCandidates = new Button("Login as admin");
        this.btnShowCandidates.setOnAction(e -> screenController.showLoginAdminScreen());

        AnchorPane anchorPane = new AnchorPane();
        anchorPane.getChildren().addAll(btnTryElectionSim, btnShowCandidates);

        AnchorPane.setTopAnchor(btnTryElectionSim, 0.0);
        AnchorPane.setRightAnchor(btnTryElectionSim, 0.0);
        AnchorPane.setLeftAnchor(btnTryElectionSim, 0.0);
        AnchorPane.setBottomAnchor(btnTryElectionSim, 249.0);

        AnchorPane.setTopAnchor(btnShowCandidates, 249.0);
        AnchorPane.setRightAnchor(btnShowCandidates, 0.0);
        AnchorPane.setLeftAnchor(btnShowCandidates, 0.0);
        AnchorPane.setBottomAnchor(btnShowCandidates, 0.0);

        btnTryElectionSim.setStyle("-fx-focus-color: transparent; -fx-faint-focus-color: transparent;" +
                "-fx-font-size: 22px;");
        btnShowCandidates.setStyle("-fx-focus-color: transparent; -fx-faint-focus-color: transparent;" +
                "-fx-font-size: 22px;");

        this.scene = new Scene(anchorPane, 600, 500);
    }

    /**
     * Returns the Scene containing the UI elements of the LoginScreen.
     * This scene is what is displayed on the stage when the LoginScreen is active.
     *
     * @return the scene of the LoginScreen
     */
    public Scene getScene() {
        return this.scene;
    }
}
