package model;

import java.util.ArrayList;
import java.util.List;

/**
 * Manages a collection of Candidate objects representing political candidates in an election simulation.
 * This class initializes a predefined list of candidates with specific political orientations and wings.
 */
public class CandidatesManager {
    private List<Candidate> candidates;

    /**
     * Constructs a CandidatesManager and initializes the list of candidates with predefined values.
     */
    public CandidatesManager() {
        candidates = new ArrayList<>();
        // Predefined candidates with their respective political wings and orientations.
        candidates.add(new Candidate(PoliticalWing.LEFT_WING, PoliticalOrientation.LIBERAL, "Camila Hale"));
        candidates.add(new Candidate(PoliticalWing.RIGHT_WING, PoliticalOrientation.LIBERAL, "Paul Ford"));
        candidates.add(new Candidate(PoliticalWing.LEFT_WING, PoliticalOrientation.CONSERVATIVE, "Luis Pedro"));
        candidates.add(new Candidate(PoliticalWing.RIGHT_WING, PoliticalOrientation.CONSERVATIVE, "Henry Blair"));
    }

    /**
     * Returns the list of all candidates managed by this manager.
     * This list is used throughout the application to perform operations like voting simulations and result calculations.
     *
     * @return a list of all the candidates.
     */
    public List<Candidate> getCandidates() {
        return candidates;
    }
}