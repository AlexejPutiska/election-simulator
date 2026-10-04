package view;

import controller.NumberOfVotedUsers;
import controller.ScreenController;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.scene.text.TextFlow;
import javafx.application.Platform;
import model.Candidate;
import model.CandidatesManager;
import controller.ElectionSim;
import java.util.List;
import controller.CandidatePercent;

/**
 * The AdminScreen class provides a user interface where administrative users can view detailed election results.
 * It shows the total number of users who voted and percentage results for each candidate.
 */
public class AdminScreen {
    private Scene scene;

    /**
     * Constructs a new AdminScreen which includes displays for the total number of users who voted and the voting results for each candidate.
     * This screen is specifically designed for administrators to review election data.
     *
     * @param screenController the controller used to manage transitions between screens and to handle administrative actions
     */

    public AdminScreen(ScreenController screenController) {
        VBox centerContent = new VBox(30);
        centerContent.setAlignment(Pos.CENTER);

        int numberOfVotedUsers = NumberOfVotedUsers.getNumberOfVotedUsers();
        Text titleLabelText = new Text("Number of voted users - ");
        titleLabelText.setStyle("-fx-font-size: 24px;");
        Text numberText = new Text(String.valueOf(numberOfVotedUsers));
        numberText.setStyle("-fx-font-weight: bold; -fx-font-size: 24px;");

        TextFlow titleTextFlow = new TextFlow(titleLabelText, numberText);
        titleTextFlow.setTextAlignment(TextAlignment.CENTER);
        titleTextFlow.setMaxWidth(Double.MAX_VALUE);
        centerContent.getChildren().add(titleTextFlow);

        CandidatesManager candidatesManager = ElectionSim.candidates;
        List<Candidate> allCandidates = candidatesManager.getCandidates();
        String adminUsername = "admin";
        boolean fileHasData = CandidatePercent.calculatePercent(ElectionSim.candidates, adminUsername);
        if (fileHasData) {
            for (Candidate candidate : allCandidates) {
                Text nameText = new Text(String.format("%s, %s, %s - ", candidate.getName(),
                        candidate.getPoliticalOrientationString(), candidate.getPoliticalWingString()));
                Text percentText = new Text(String.format("%.2f%%", candidate.getPercent()));
                percentText.setStyle("-fx-font-weight: bold;");

                TextFlow textFlow = new TextFlow(nameText, percentText);
                Label candidateLabel = new Label();
                candidateLabel.setGraphic(textFlow);
                candidateLabel.setStyle("-fx-font-size: 17px;");
                centerContent.getChildren().add(candidateLabel);
            }
        } else {
            for (Candidate candidate : allCandidates) {
                Label candidateLabel = new Label(String.format("%s, %s, %s", candidate.getName(),
                        candidate.getPoliticalOrientationString(), candidate.getPoliticalWingString()));
                candidateLabel.setStyle("-fx-font-size: 17px;");
                centerContent.getChildren().add(candidateLabel);
            }
        }

        Button backButton = new Button("Back to login");
        backButton.setOnAction(e -> screenController.showLoginScreen());
        backButton.setStyle("-fx-font-size: 15px;");
        backButton.setMinWidth(120);
        backButton.setMinHeight(40);

        HBox bottomBox = new HBox(backButton);
        bottomBox.setAlignment(Pos.CENTER);
        bottomBox.setPadding(new javafx.geometry.Insets(10, 0, 10, 0));

        BorderPane borderPane = new BorderPane();
        borderPane.setCenter(centerContent);
        borderPane.setBottom(bottomBox);

        this.scene = new Scene(borderPane, 600, 500);
        Platform.runLater(borderPane::requestFocus);
    }

    /**
     * Returns the Scene containing the UI elements of the AdminScreen.
     * This scene is what is displayed on the stage when the AdminScreen is active.
     *
     * @return the scene of the AdminScreen
     */
    public Scene getScene() {
        return this.scene;
    }
}