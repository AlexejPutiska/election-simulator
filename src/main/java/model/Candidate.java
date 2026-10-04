package model;

/**
 * Represents a candidate in an election simulation.
 */
public class Candidate extends Person {
    private PoliticalWing politicalWing;
    private PoliticalOrientation politicalOrientation;
    private long points;
    private float percent;

    /**
     * Constructs a candidate with specified political affiliations and a name.
     *
     * @param politicalWing The political wing to which the candidate belongs.
     * @param politicalOrientation The political orientation of the candidate.
     * @param name The name of the candidate.
     */
    public Candidate(PoliticalWing politicalWing, PoliticalOrientation politicalOrientation, String name) {
        super();
        this.politicalWing = politicalWing;
        this.politicalOrientation = politicalOrientation;
        setName(name);
        this.points = 0;
    }

    /**
     * Returns the political wing of the candidate.
     *
     * @return The political wing enumeration.
     */
    public PoliticalWing getPoliticalWing() {
        return politicalWing;
    }

    /**
     * Returns a string representation of the candidate's political wing.
     *
     * @return A string describing the political wing.
     */
    public String getPoliticalWingString() {
        switch (this.politicalWing) {
            case LEFT_WING:
                return "left-wing";
            case RIGHT_WING:
                return "right-wing";
            default:
                return "";
        }
    }

    /**
     * Returns the political orientation of the candidate.
     *
     * @return The political orientation enumeration.
     */
    public PoliticalOrientation getPoliticalOrientation() {
        return politicalOrientation;
    }

    /**
     * Returns a string representation of the candidate's political orientation.
     *
     * @return A string describing the political orientation.
     */
    public String getPoliticalOrientationString() {
        switch (this.politicalOrientation) {
            case LIBERAL:
                return "liberal";
            case CONSERVATIVE:
                return "conservative";
            default:
                return "";
        }
    }

    /**
     * Retrieves the total points accumulated by the candidate.
     *
     * @return The total points.
     */
    public long getPoints() {
        return points;
    }

    /**
     * Sets the total points for the candidate.
     *
     * @param points The points to set.
     */
    public void setPoints(long points) {
        this.points = points;
    }

    /**
     * Adds specified points to the current total points of the candidate.
     *
     * @param points The points to add.
     */
    public void addPoints(long points) {
        this.points += points;
    }

    /**
     * Gets the percentage of votes or support received by the candidate.
     *
     * @return The percentage value.
     */
    public float getPercent() {
        return percent;
    }

    /**
     * Sets the percentage of votes or support received by the candidate.
     *
     * @param percent The percentage to set.
     */
    public void setPercent(float percent) {
        this.percent = percent;
    }

    /**
     * Provides a string representation of the candidate, including name, political wing, and orientation.
     *
     * @return A string summarizing the candidate information.
     */
    @Override
    public String toString() {
        return "Candidate{" +
                "name: " + getName() +
                ", political wing: " + getPoliticalWingString() +
                ", political orientation: " + getPoliticalOrientationString() +
                "}";
    }
}