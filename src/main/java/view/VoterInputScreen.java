package view;

import controller.Voting;
import controller.ScreenController;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;

/**
 * The VoterInputScreen provides a user interface for users to input characteristics of voters to simulate and predict election results.
 * This screen facilitates the collection of varied voter attributes, which are used in modeling potential election outcomes.
 */
public class VoterInputScreen {
    private Scene scene;

    /**
     * Initializes a new instance of VoterInputScreen, setting up UI components for data entry related to voter attributes.
     * Users can input various characteristics of the voter population to help predict the election winner.
     *
     * @param screenController the controller used to manage transitions between screens and to handle the voting simulation process.
     */
    public VoterInputScreen(ScreenController screenController) {
        Label label = new Label("Enter the total number of voters (ranging from 10,000 to 1,000,000)");
        label.setStyle("-fx-font-size: 14px;");

        TextField votersTotal = new TextField();
        votersTotal.setPromptText("Total number of voters");

        Label labelSecond = new Label("Enter voters characteristics");
        labelSecond.setStyle("-fx-font-size: 14px;");

        TextField votersUnderAge = new TextField();
        votersUnderAge.setPromptText("Number of voters under 35");

        TextField votersReligion = new TextField();
        votersReligion.setPromptText("Number of Christian voters");

        TextField votersEducation = new TextField();
        votersEducation.setPromptText("Number of voters with a university degree");

        TextField votersFromVillage = new TextField();
        votersFromVillage.setPromptText("Number of voters residing in a village");

        TextField votersPensoniers = new TextField();
        votersPensoniers.setPromptText("Number of pensioner voters");

        TextField votersEnt = new TextField();
        votersEnt.setPromptText("Number of entrepreneur voters");

        Button btnVote = new Button("Predict winner");
        btnVote.setOnAction(e -> {
            new Voting(screenController).startVoting(
                    votersTotal, votersUnderAge, votersReligion,
                    votersEducation, votersFromVillage, votersPensoniers, votersEnt
            );
        });

        Region spacer = new Region();
        spacer.setMinHeight(20);

        Region spacer2 = new Region();
        spacer2.setMinHeight(20);

        btnVote.setStyle("-fx-font-size: 15px;");
        btnVote.setMinWidth(130);
        btnVote.setMinHeight(50);

        VBox centerContent = new VBox(10, label, votersTotal, spacer, labelSecond, votersUnderAge,
                votersEducation, votersReligion, votersFromVillage,
                votersPensoniers, votersEnt, spacer2, btnVote);
        centerContent.setAlignment(Pos.CENTER);
        centerContent.setPadding(new Insets(10));

        BorderPane borderPane = new BorderPane();
        borderPane.setCenter(centerContent);

        this.scene = new Scene(borderPane, 600, 500);

        Platform.runLater(() -> {
            centerContent.requestFocus();
        });
    }

    /**
     * Returns the Scene containing the UI elements of the VoterInputScreen.
     * This scene is what is displayed on the stage when the VoterInputScreen is active.
     *
     * @return the scene of the VoterInputScreen
     */
    public Scene getScene() {
        return this.scene;
    }
}
