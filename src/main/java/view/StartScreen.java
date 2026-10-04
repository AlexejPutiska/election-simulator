package view;

import controller.ScreenController;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.text.FontWeight;
/**
 The StartScreen class provides the initial user interface for the ElectionSim application.
 * It displays the application's name, a brief description, and a button to proceed to the login screen.
 * This screen serves as the entry point for users interacting with the application.
 */
public class StartScreen {
    private Scene scene;

    /**
     * Constructs a new StartScreen with a title, description, and a start button.
     * This method initializes the UI components and configures their properties and layout.
     * The StartScreen acts as the initial view that users see when they launch the application.
     *
     * @param screenController the controller used to manage transitions between different screens
     */
    public StartScreen(ScreenController screenController) {
        BorderPane borderPane = new BorderPane();

        Label titleLabel = new Label("ElectionSim");
        titleLabel.setFont(javafx.scene.text.Font.font("Arial", FontWeight.BOLD, 55));

        Label descriptionLabel = new Label("Immerse yourself in the electoral process! " +
                "Select from four unique candidates representing diverse political ideologies. " +
                "Input voter demographics and watch as ElectionSim calculates the win probabilities " +
                "based on voters characteristics. Your input decides!");
        descriptionLabel.setFont(javafx.scene.text.Font.font("Arial", FontWeight.NORMAL, 20));
        descriptionLabel.setWrapText(true);
        descriptionLabel.setMaxWidth(475);
        descriptionLabel.setTextAlignment(javafx.scene.text.TextAlignment.JUSTIFY);

        Button startButton = new Button("Start");
        startButton.setFont(javafx.scene.text.Font.font("Arial", 20));
        startButton.setOnAction(e -> {
            screenController.showLoginScreen();

        });

        VBox vbox = new VBox(10);
        vbox.setAlignment(Pos.CENTER);

        vbox.getChildren().addAll(titleLabel, descriptionLabel, startButton);
        vbox.setMargin(startButton, new Insets(100, 0, 0, 0));
        vbox.setMargin(titleLabel, new Insets(0, 0, 30, 0));

        borderPane.setCenter(vbox);

        this.scene = new Scene(borderPane, 600, 500);
        Platform.runLater(() -> {
            borderPane.requestFocus();
        });
    }

    /**
     * Returns the Scene containing the UI elements of the StartScreen.
     * This scene is what is displayed on the stage when the StartScreen is active.
     *
     * @return the scene of the StartScreen
     */
    public Scene getScene() {
        return this.scene;
    }
}