package controller;

import java.io.*;


/**
 * Utility class to retrieve the number of users who have voted.
 * This class reads a predefined file to obtain the total count of voted users.
 */
public class NumberOfVotedUsers {

    /**
     * Retrieves the total number of users who have voted from a stored file.
     * This number is read from "numberOfVotedUsers.txt".
     *
     * @return the number of users who have voted as an integer. Returns 0 if there is an error accessing or parsing the file.
     */
    public static int getNumberOfVotedUsers(){
        String filePath = "src/main/resources/files/numberOfVotedUsers.txt";
        try {
            File file = new File(filePath);
            int number = 0;
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                String line = reader.readLine();
                if (line != null) {
                    number = Integer.parseInt(line.trim());
                }
            }

            return number;

        } catch (IOException e) {
            System.err.println("Error accessing the file: " + e.getMessage());
            return 0;
        } catch (NumberFormatException e) {
            System.err.println("Error parsing number from file: " + e.getMessage());
            return 0;
        }
    }
}
