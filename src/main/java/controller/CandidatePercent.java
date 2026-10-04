package controller;

import model.Candidate;
import model.CandidatesManager;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

/**
 * Utility class for calculating the percentage chance of victory for each candidate.
 * This percentage is based on the points accumulated by each candidate from the characteristics of the population.
 */
public class CandidatePercent {

    /**
     * Calculates and updates the percentage chance of victory for each candidate based on points stored in a file.
     * These points are derived from various characteristics of the population and not from direct voting results.
     *
     * @param candidatesManager The manager that holds and manages the list of all candidates.
     * @param username The username associated with the data file to be read. This username determines the file path.
     * @return true if the calculation and updates were successful, false if an error occurs (e.g., file not found or data corruption).
     */
    public static boolean calculatePercent(CandidatesManager candidatesManager, String username) {
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

            List<Candidate> allCandidates = candidatesManager.getCandidates();
            long total = 0;
            float percent;
            float totalpercent = 0;
            for (long value : points) {
                total += value;
            }

            for (int i = 0; i < allCandidates.size(); i++) {
                Candidate candidate = allCandidates.get(i);
                if (i == allCandidates.size() - 1) {
                    percent = 100 - totalpercent;
                    percent = Math.round(percent * 10.0f) / 10.0f;
                    candidate.setPercent(percent);
                }
                else {
                    percent = ((float)points[i] / total) * 100;
                    percent = Math.round(percent * 10.0f) / 10.0f;
                    candidate.setPercent(percent);
                    totalpercent += percent;
                }
            }
            return true;
        } catch (IOException e) {
            return false;
        }
    }
}
