package view;

import controller.ScreenController;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import model.Candidate;
import model.CandidatesManager;
import controller.ElectionSim;
import java.util.List;
import javafx.scene.chart.PieChart;

/**
 * The ResultScreen class displays the predicted chances of victory for each candidate in a pie chart format.
 * It provides a visual representation of each candidate's probability of winning based on simulation results.
 */
public class ResultScreen {
    private Scene scene;

    /**
     * Constructs a new ResultScreen that visualizes the candidates' chances of winning.
     * This screen is used to graphically display the results of a simulated election, showing the percentage chance of victory for each candidate.
     *
     * @param screenController the controller used to manage transitions between screens and to navigate back to the main menu
     */
    public ResultScreen(ScreenController screenController) {
        VBox centerContent = new VBox(10);

        PieChart pieChart = new PieChart();
        pieChart.setTitle("Results");

        CandidatesManager candidatesManager = ElectionSim.candidates;
        List<Candidate> allCandidates = candidatesManager.getCandidates();

        // Adding each candidate's performance to the pie chart
        for (Candidate candidate : allCandidates) {
            String nameWithPercent = String.format("%s: %.2f%%", candidate.getName(), candidate.getPercent());
            PieChart.Data slice = new PieChart.Data(nameWithPercent, candidate.getPercent());
            pieChart.getData().add(slice);
        }

        centerContent.getChildren().add(pieChart);

        Button backButton = new Button("Back to Menu");
        backButton.setOnAction(e -> screenController.showMenuScreen());

        backButton.setStyle("-fx-font-size: 15px;");
        backButton.setMinWidth(120);
        backButton.setMinHeight(40);

        HBox bottomBox = new HBox(backButton);
        bottomBox.setAlignment(Pos.CENTER);

        BorderPane borderPane = new BorderPane();
        borderPane.setCenter(centerContent);
        borderPane.setBottom(bottomBox);
        bottomBox.setPadding(new javafx.geometry.Insets(10, 0, 10, 0));

        this.scene = new Scene(borderPane, 600, 500);

        // Ensure the BorderPane has focus for visual or accessibility reasons
        Platform.runLater(() -> borderPane.requestFocus());
    }

    /**
     * Returns the Scene containing the UI elements of the ResultScreen.
     * This scene is what is displayed on the stage when the ResultScreen is active.
     *
     * @return the scene of the ResultScreen
     */
    public Scene getScene() {
        return this.scene;
    }
}