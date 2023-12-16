package OOP;
abstract class AbstractEntity {
    private int id;
    private String name;
    private double rate;

    public abstract int getId();
    // other abstract methods...

    // Accessor method for id
    protected int internalGetId() {
        return id;
    }

    // Mutator method for id
    protected void internalSetId(int id) {
        this.id = id;
    }

    // other methods...
}

public class Abstraction extends AbstractEntity {

    @Override
    public int getId() {
        // Access the id using the accessor method from the abstract class
        return internalGetId();
    }

    // other implementations of abstract methods...

    public static void main(String[] args) {
        Abstraction abstraction = new Abstraction();
        abstraction.internalSetId(42);

        // Access the id using the getId() method
        System.out.println("ID: " + abstraction.getId());
    }
}
