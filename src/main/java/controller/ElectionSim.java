package controller;

import javafx.application.Application;
import javafx.stage.Stage;
import model.CandidatesManager;

/**
 * Main class for the ElectionSim application that sets up the primary user interface and initializes the application.
 * This class extends Application from JavaFX and serves as the entry point for running the application.
 */

public class ElectionSim extends Application {
    /**
     * Static instance of CandidatesManager to manage and provide access to the candidates across the application.
     */
    public static CandidatesManager candidates = new CandidatesManager();

    /**
     * Starts the primary stage of the application, setting up the initial user interface and scene.
     * This method is called as part of the JavaFX lifecycle when the application is launched.
     *
     * @param primaryStage The primary stage for this application, onto which the application scenes are set.
     */
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setResizable(false);
        ScreenController screenController = new ScreenController(primaryStage);
        screenController.showStartScreen();
        primaryStage.setTitle("ElectionSim");
        primaryStage.show();
    }

    /**
     * The main method that launches the JavaFX application.
     * This is the entry point method that gets called by the Java runtime when the program starts.
     *
     * @param args Command-line arguments passed to the application.
     */
    public static void main(String[] args) {
        launch(args);
    }
}
