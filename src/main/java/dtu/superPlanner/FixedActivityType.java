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

    public boolean equalsName(String otherName) {
        // (otherName == null) check is not needed because name.equals(null) returns false 
        return name.equals(otherName);
    }

    public String toString() {
       return this.name;
    }
}
