package model;

/**
 * Represents a collection of voter characteristics within an electoral simulation.
 * This class encapsulates the distribution of voters based on various attributes such as age, religious affiliation, education level,
 * living area, and occupational status, which are crucial for predicting election outcomes.
 */
public class Voters {
    private int totalVoters;
    private int underAgeVoters;
    private int christianVoters;
    private int universityDegreeVoters;
    private int villageResidentVoters;
    private int pensionerVoters;
    private int entrepreneurVoters;

    /**
     * Constructs a new Voters instance with specified characteristics.
     *
     * @param totalVoters The total number of voters considered in the simulation.
     * @param underAgeVoters The number of voters under a specified age, e.g., 35 years.
     * @param christianVoters The number of voters identifying as Christian.
     * @param universityDegreeVoters The number of voters holding a university degree.
     * @param villageResidentVoters The number of voters residing in a village.
     * @param pensionerVoters The number of voters who are pensioners.
     * @param entrepreneurVoters The number of voters who are entrepreneurs.
     */
    public Voters(int totalVoters, int underAgeVoters, int universityDegreeVoters, int christianVoters,
                  int villageResidentVoters, int pensionerVoters, int entrepreneurVoters) {
        this.totalVoters = totalVoters;
        this.underAgeVoters = underAgeVoters;
        this.universityDegreeVoters = universityDegreeVoters;
        this.christianVoters = christianVoters;
        this.villageResidentVoters = villageResidentVoters;
        this.pensionerVoters = pensionerVoters;
        this.entrepreneurVoters = entrepreneurVoters;
    }

    /**
     * Returns the total number of voters.
     * @return the total number of voters
     */
    public int getTotal() {
        return totalVoters;
    }

    /**
     * Returns the number of voters under the specified age.
     * @return the number of young voters
     */
    public int getUnderAge() {
        return underAgeVoters;
    }

    /**
     * Returns the number of voters identifying as Christian.
     * @return the number of Christian voters
     */
    public int getChristian() {
        return christianVoters;
    }

    /**
     * Returns the number of voters holding a university degree.
     * @return the number of educated voters
     */
    public int getUniversityDegree() {
        return universityDegreeVoters;
    }

    /**
     * Returns the number of voters residing in a village.
     * @return the number of village-resident voters
     */
    public int getVillageResident() {
        return villageResidentVoters;
    }

    /**
     * Returns the number of voters who are pensioners.
     * @return the number of pensioner voters
     */
    public int getPensioner() {
        return pensionerVoters;
    }

    /**
     * Returns the number of voters who are entrepreneurs.
     * @return the number of entrepreneur voters
     */
    public int getEntrepreneur() {
        return entrepreneurVoters;
    }
}
