package view;

import controller.ScreenController;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.application.Platform;
import model.Candidate;
import model.CandidatesManager;
import model.CurrentUser;
import controller.ElectionSim;
import java.util.List;
import controller.CandidatePercent;

/**
 * The CandidatesScreen class provides a user interface to display the list of candidates along with their details
 * and computed chances of winning based on simulation data.
 */
public class CandidatesScreen {
    private Scene scene;

    /**
     * Constructs a new CandidatesScreen which displays all candidates and their chances of winning.
     * This screen is used to visualize the candidate data within the ElectionSim application.
     *
     * @param screenController the controller used to manage transitions between screens
     */
    public CandidatesScreen(ScreenController screenController) {
        VBox centerContent = new VBox(30);
        centerContent.setAlignment(Pos.CENTER);

        Label titleLabel = new Label("Candidates");
        titleLabel.setStyle("-fx-font-size: 24px; -fx-font-weight: bold;");
        centerContent.getChildren().add(titleLabel);

        CandidatesManager candidatesManager = ElectionSim.candidates;
        List<Candidate> allCandidates = candidatesManager.getCandidates();

        boolean fileHasData = CandidatePercent.calculatePercent(ElectionSim.candidates,
                CurrentUser.getInstance().getUsername());
        if (fileHasData) {
            for (Candidate candidate : allCandidates) {
                Text nameText = new Text(String.format("%s, %s, %s, chance of winning: ", candidate.getName(),
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

        Button backButton = new Button("Back to Menu");
        backButton.setOnAction(e -> screenController.showMenuScreen());
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
     * Returns the Scene containing the UI elements of the CandidatesScreen.
     * This scene is what is displayed on the stage when the CandidatesScreen is active.
     *
     * @return the scene of the CandidatesScreen
     */
    public Scene getScene() {
        return this.scene;
    }
}