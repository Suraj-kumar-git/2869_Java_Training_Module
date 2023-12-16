package Static_This_Others;

public class StaticThisFinalETC {
    // Static variable belongs to the class, not to instances
    static int staticVariable = 10;

    // Instance variable
    int instanceVariable;

    // Static method can be called without creating an instance of the class
    static void staticMethod() {
        System.out.println("Static method. Static Variable: " + staticVariable);
    }

    // Instance method
    void instanceMethod() {
        // "this" refers to the current instance of the class
        System.out.println("Instance method. Instance Variable: " + this.instanceVariable);
    }

    // Final variable cannot be modified after initialization
    final int finalVariable = 42;

    // Final method cannot be overridden by subclasses
    final void finalMethod() {
        System.out.println("Final method");
    }

    // The finalize() method is called by the garbage collector before an object is garbage collected
//    @Override
//    protected void finalize() throws Throwable {
//        System.out.println("Finalize method called");
//        super.finalize();
//    }

    // Throw an exception within a method
    void throwExample() {
        throw new IllegalArgumentException("Example of 'throw'");
    }

    // Use "throws" to declare that a method may throw a particular type of exception
    void throwsExample() throws Exception {
        throw new Exception("Example of 'throws'");
    }

    public static void main(String[] args) {
        // Accessing static variable and method
        System.out.println("Static Variable in Main: " + staticVariable);
        staticMethod();

        // Creating an instance of the class
        StaticThisFinalETC obj = new StaticThisFinalETC();

        // Accessing instance variable and method using "this"
        obj.instanceVariable = 5;
        obj.instanceMethod();

        // Accessing final variable and method
        System.out.println("Final Variable in Main: " + obj.finalVariable);
        obj.finalMethod();

        // Using "throw" and "throws"
        try {
            obj.throwExample();
        } catch (IllegalArgumentException e) {
            System.out.println("Caught exception: " + e.getMessage());
        }

        try {
            obj.throwsExample();
        } catch (Exception e) {
            System.out.println("Caught exception: " + e.getMessage());
        } finally {
            // "finally" block is executed whether an exception is thrown or not
            System.out.println("Finally block executed");
        }
    }
    static {
    	System.out.println("Static block executes before main() method & it is always written below main method.");
    }
}

