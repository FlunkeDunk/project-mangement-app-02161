package dtu.superPlanner;

/**
 * @author Emanuel
 */
public enum FixedActivityType {
    Vacation ("Vacation"),
    Sick ("Sick"),
    Emergency ("Emergency");

    private final String name;       

    private FixedActivityType(String s) {
        name = s;
    }

    public String toString() {
       return this.name;
    }
}
