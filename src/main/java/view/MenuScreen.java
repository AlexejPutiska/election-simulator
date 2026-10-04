package view;

import controller.ScreenController;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

/**
 * The MenuScreen class provides a central navigation hub for the ElectionSim application,
 * offering buttons to access different functionalities like the simulation, candidate viewing,
 * and returning to the login screen.
 */

public class MenuScreen {
    private Scene scene;
    private Button btnTryElectionSim;
    private Button btnShowCandidates;
    private Button btnBackToLogin;

    /**
     * Constructs a new MenuScreen which contains navigation buttons to various features of the application.
     * This screen serves as a central point for accessing all major functionalities of the ElectionSim application.
     *
     * @param screenController the controller used to manage transitions between different screens
     */
    public MenuScreen(ScreenController screenController) {
        this.btnTryElectionSim = new Button("Simulation");
        this.btnTryElectionSim.setOnAction(e -> screenController.showVoterInputScreen());

        this.btnShowCandidates = new Button("Candidates");
        this.btnShowCandidates.setOnAction(e -> screenController.showCandidatesScreen());

        this.btnBackToLogin = new Button("Back to Login");
        this.btnBackToLogin.setOnAction(e -> screenController.showLoginScreen());

        AnchorPane anchorPane = new AnchorPane();
        anchorPane.getChildren().addAll(btnBackToLogin,btnShowCandidates,btnTryElectionSim);

        AnchorPane.setTopAnchor(btnTryElectionSim, 0.0);
        AnchorPane.setRightAnchor(btnTryElectionSim, 0.0);
        AnchorPane.setLeftAnchor(btnTryElectionSim, 0.0);
        AnchorPane.setBottomAnchor(btnTryElectionSim, 279.0);

        AnchorPane.setTopAnchor(btnShowCandidates, 220.0);
        AnchorPane.setRightAnchor(btnShowCandidates, 0.0);
        AnchorPane.setLeftAnchor(btnShowCandidates, 0.0);
        AnchorPane.setBottomAnchor(btnShowCandidates, 59.0);

        AnchorPane.setTopAnchor(btnBackToLogin, 440.0);
        AnchorPane.setRightAnchor(btnBackToLogin, 0.0);
        AnchorPane.setLeftAnchor(btnBackToLogin, 0.0);
        AnchorPane.setBottomAnchor(btnBackToLogin, 0.0);


        btnTryElectionSim.setStyle("-fx-focus-color: transparent; -fx-faint-focus-color: transparent;" +
                "-fx-font-size: 22px;");
        btnShowCandidates.setStyle("-fx-focus-color: transparent; -fx-faint-focus-color: transparent;" +
                "-fx-font-size: 22px;");
        btnBackToLogin.setStyle("-fx-focus-color: transparent; -fx-faint-focus-color: transparent;" +
                "-fx-font-size: 18px;");

        this.scene = new Scene(anchorPane, 600, 500);
    }

    /**
     * Returns the Scene containing the UI elements of the MenuScreen.
     * This scene is what is displayed on the stage when the MenuScreen is active.
     *
     * @return the scene of the MenuScreen
     */
    public Scene getScene() {
        return this.scene;
    }
}