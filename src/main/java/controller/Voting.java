package controller;

import javafx.scene.control.TextField;
import model.CandidatesManager;
import model.Voters;
import model.Candidate;
import model.PoliticalOrientation;
import model.PoliticalWing;
import java.io.*;
import java.util.List;
import model.CurrentUser;

/**
 * Manages the voting process including calculating, setting, and writing points based on voter characteristics,
 * and handling subsequent voting results.
 */
public class Voting {
    private ScreenController screenController;

    /**
     * Constructor initializes the Voting class with a reference to the ScreenController.
     *
     * @param screenController The controller responsible for managing screen transitions and displaying alerts.
     */
    public Voting(ScreenController screenController) {
        this.screenController = screenController;
    }

    /**
     * Starts the voting process by reading inputs, calculating points, and displaying the result screen.
     * Validates the inputs, calculates points for each candidate, and handles the navigation and alerts based on the outcomes.
     *
     * @param totalField TextField for total number of voters.
     * @param underAgeField TextField for number of voters under a certain age.
     * @param christianField TextField for number of Christian voters.
     * @param uniDegreeField TextField for number of voters with a university degree.
     * @param villageField TextField for number of voters residing in villages.
     * @param pensionerField TextField for number of pensioner voters.
     * @param entrepreneurField TextField for number of entrepreneur voters.
     */
    public void startVoting(TextField totalField, TextField underAgeField, TextField christianField,
                            TextField uniDegreeField, TextField villageField,
                            TextField pensionerField, TextField entrepreneurField) {
        try {
            int total = Integer.parseInt(totalField.getText());
            int underAge = Integer.parseInt(underAgeField.getText());
            int christian = Integer.parseInt(christianField.getText());
            int uniDegree = Integer.parseInt(uniDegreeField.getText());
            int villageResident = Integer.parseInt(villageField.getText());
            int pensioner = Integer.parseInt(pensionerField.getText());
            int entrepreneur = Integer.parseInt(entrepreneurField.getText());

            if (underAge > total || christian > total || uniDegree > total || villageResident > total ||
                    pensioner > total || entrepreneur > total) {
                throw new IllegalArgumentException();
            }

            if (underAge < 0 || christian < 0 || uniDegree < 0 || villageResident < 0 ||
                    pensioner < 0 || entrepreneur < 0) {
                throw new IllegalArgumentException();
            }

            if (total > 10000000 || total < 10000) {
                throw new IllegalArgumentException();
            }

            Voters voters = new Voters(total, underAge, uniDegree, christian, villageResident, pensioner, entrepreneur);
            setPointsFromFile(ElectionSim.candidates);
            resetPointsAndPercents(ElectionSim.candidates);
            calculatePoints(voters, ElectionSim.candidates);
            writePoints(ElectionSim.candidates);
            addToAdmin(ElectionSim.candidates);
            calculatePercents(ElectionSim.candidates);
            screenController.showResultScreen();
        } catch (IllegalArgumentException e) {
            screenController.showAlertVoting();
        }
    }

    /**
     * Sets the points for each candidate from a file that stores points from previous voting sessions.
     * This method is used to load saved voting results when a user logs in.
     *
     * @param candidatesManager The manager handling the list of candidates.
     */
    public void setPointsFromFile(CandidatesManager candidatesManager){
        String username = CurrentUser.getInstance().getUsername();
        String filePath = "src/main/resources/files/" + username + ".txt";
        long[] points = new long[4];
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            int index = 0;

            while ((line = reader.readLine()) != null && index < 4) {
                points[index] = Long.parseLong(line.trim());
                index++;
            }

            if (index < 4) {
                throw new IOException();
            }

            index = 0;
            List<Candidate> allCandidates = candidatesManager.getCandidates();
            for (Candidate candidate : allCandidates) {
                candidate.setPoints(points[index]);
                index++;
            }

        } catch (IOException e) {
            setPointsFirstTime(candidatesManager);
            return;
        }
    }

    /**
     * Sets initial points to zero for all candidates when no previous voting data is available for the user.
     *
     * @param candidatesManager The manager handling the list of candidates.
     */
    public void setPointsFirstTime(CandidatesManager candidatesManager){
        updateNumberOfVotedUsers();
        List<Candidate> allCandidates = candidatesManager.getCandidates();
        for (Candidate candidate : allCandidates) {
            candidate.setPoints(0);
        }
    }

    /**
     * Increments the number of users who have voted.
     * This method updates the file that tracks the total number of voters.
     */
    public void updateNumberOfVotedUsers(){
        String filePath = "src/main/resources/files/numberOfVotedUsers.txt";
        try {
            File file = new File(filePath);
            int numberOfVotedUsers = 0;
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                String line = reader.readLine();
                if (line != null) {
                    numberOfVotedUsers = Integer.parseInt(line.trim());
                }
            }
            numberOfVotedUsers++;

            try (BufferedWriter writer = new BufferedWriter(new FileWriter(file, false))) {
                writer.write(String.valueOf(numberOfVotedUsers));
            }

        } catch (IOException e) {
            System.err.println("Error accessing the file: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.err.println("Error parsing number from file: " + e.getMessage());
        }

    }

    /**
     * Resets points and percentages for all candidates.
     * This method is used to clear previous voting data before starting a new voting session.
     *
     * @param candidatesManager The manager handling the list of candidates.
     */
    public void resetPointsAndPercents(CandidatesManager candidatesManager){
        subtractInAdmin(candidatesManager);
        List<Candidate> allCandidates = candidatesManager.getCandidates();
        for (Candidate candidate : allCandidates) {
            candidate.setPoints(0);
            candidate.setPercent(0);
        }
    }

    /**
     * Reduces the overall total points from the admin's records based on the points from the last session.
     *
     * @param candidatesManager The manager handling the list of candidates.
     */
    public void subtractInAdmin(CandidatesManager candidatesManager){
        String filePath = "src/main/resources/files/admin.txt";
        long[] adminPoints = new long[4];

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            int index = 0;

            while ((line = reader.readLine()) != null && index < 4) {
                adminPoints[index] = Long.parseLong(line.trim());
                index++;
            }

            if (index < 4) {
                throw new IOException();
            }

        } catch (IOException e) {
            return;
        }

        List<Candidate> allCandidates = candidatesManager.getCandidates();
        int index = 0;
        for (Candidate candidate : allCandidates) {
            adminPoints[index] -= candidate.getPoints();
            index++;
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, false))) {
            for (int i = 0; i <= 3; i++) {
                writer.write(adminPoints[i] + "\n");
            }
        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }
    }

    /**
     * Calculates points for each candidate based on specified characteristics of the population.
     * Points are assigned to candidates reflecting the alignment of their policies with the selected characteristics.
     *
     * @param voters The characteristics of the population used for calculating the points.
     * @param candidatesManager The manager handling the list of candidates.
     */
    public void calculatePoints(Voters voters, CandidatesManager candidatesManager){
        int totalPoints = voters.getTotal();
        int underAgePoints = voters.getUnderAge();
        int uniDegreePoints = voters.getUniversityDegree();
        int christianPoints = voters.getChristian();
        int villageResidentPoints = voters.getVillageResident();
        int pensionerPoints = voters.getPensioner();
        int entrepreneurPoints = voters.getEntrepreneur();
        List<Candidate> allCandidates = candidatesManager.getCandidates();

        for (Candidate candidate : allCandidates) {

            if (candidate.getPoliticalOrientation() == PoliticalOrientation.LIBERAL) {
                candidate.addPoints(underAgePoints);
                candidate.addPoints(totalPoints-christianPoints);
                candidate.addPoints(totalPoints-villageResidentPoints);
            }
            else if (candidate.getPoliticalOrientation() == PoliticalOrientation.CONSERVATIVE) {
                candidate.addPoints(totalPoints-underAgePoints);
                candidate.addPoints(christianPoints);
                candidate.addPoints(villageResidentPoints);
            }

            if (candidate.getPoliticalWing() == PoliticalWing.LEFT_WING) {
                candidate.addPoints(pensionerPoints);
                candidate.addPoints(totalPoints-entrepreneurPoints);
                candidate.addPoints(totalPoints-uniDegreePoints);
            }
            else if (candidate.getPoliticalWing() == PoliticalWing.RIGHT_WING) {
                candidate.addPoints(totalPoints-pensionerPoints);
                candidate.addPoints(entrepreneurPoints);
                candidate.addPoints(uniDegreePoints);
            }
        }
    }

    /**
     * Writes the current points of each candidate to a user-specific file.
     * This method saves the points to persist changes between sessions.
     *
     * @param candidatesManager The manager handling the list of candidates.
     */
    public void writePoints(CandidatesManager candidatesManager) {
        List<Candidate> allCandidates = candidatesManager.getCandidates();
        String username = CurrentUser.getInstance().getUsername();
        String filePath = "src/main/resources/files/" + username + ".txt";

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, false))) {
            for (Candidate candidate : allCandidates) {
                writer.write(candidate.getPoints() + "\n");
            }
        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }
    }

    /**
     * Adds the current session's points to the overall total points managed by the admin.
     *
     * @param candidatesManager The manager handling the list of candidates.
     */
    public void addToAdmin(CandidatesManager candidatesManager) {
        String filePath = "src/main/resources/files/admin.txt";
        long[] adminPoints = new long[4];

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            int index = 0;

            while ((line = reader.readLine()) != null && index < 4) {
                adminPoints[index] = Long.parseLong(line.trim());
                index++;
            }

            if (index < 4) {
                throw new IOException();
            }

        } catch (IOException e) {
            addToAdminFirst(candidatesManager);
            return;
        }

        List<Candidate> allCandidates = candidatesManager.getCandidates();
        int index = 0;
        for (Candidate candidate : allCandidates) {
            adminPoints[index] += candidate.getPoints();
            index++;
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, false))) {
            for (int i = 0; i <= 3; i++) {
                writer.write(adminPoints[i] + "\n");
            }
        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }
    }

    /**
     * Initially records points for all candidates to the admin's overall total when first used.
     *
     * @param candidatesManager The manager handling the list of candidates.
     */
    public void addToAdminFirst(CandidatesManager candidatesManager){
        List<Candidate> allCandidates = candidatesManager.getCandidates();
        String filePath = "src/main/resources/files/admin.txt";

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, false))) {
            for (Candidate candidate : allCandidates) {
                writer.write(candidate.getPoints() + "\n");
            }
        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }
    }

    /**
     * Calculates and sets the percentage chances of winning for each candidate based on their total points.
     * This final percentage is a calculation of each candidate's points as a proportion of the total points scored by all candidates.
     *
     * @param candidatesManager The manager handling the list of candidates.
     */
    public void calculatePercents(CandidatesManager candidatesManager){
        List<Candidate> allCandidates = candidatesManager.getCandidates();

        float totalPoints = 0;
        float percent = 0;
        float totalpercent = 0;
        for (Candidate candidate : allCandidates) {
            totalPoints += candidate.getPoints();
        }

        for (int i = 0; i < allCandidates.size(); i++) {
            Candidate candidate = allCandidates.get(i);
            if (i == allCandidates.size() - 1) {
                percent = 100 - totalpercent;
                percent = Math.round(percent * 10.0f) / 10.0f;
                candidate.setPercent(percent);
            }
            else {
                percent = ((float)candidate.getPoints()/totalPoints)*100;
                percent = Math.round(percent * 10.0f) / 10.0f;
                candidate.setPercent(percent);
                totalpercent += percent;
            }
        }
    }
}

