package model;

/**
 * Represents a basic person entity with a name.
 * This class serves as a superclass for more specific types of people entities.
 */
public class Person {
    private String name;

    /**
     * Retrieves the name of the person.
     *
     * @return The current name of the person.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the person.
     *
     * @param name The new name to be set for this person.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Provides a string representation of this person, primarily showing the person's name.
     *
     * @return A string that represents the person by their name.
     */
    @Override
    public String toString() {
        return "Name: " + name;
    }
}