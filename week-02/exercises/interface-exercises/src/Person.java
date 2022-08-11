public class Person extends Wallet{

    private final String firstName;
    private final String lastName;


    public Person(String firstName, String lastName) {
        super(0.0, String.format("%s's Wallet", firstName));
        // Wallet constructor
        //super();
        this.firstName = firstName;
        this.lastName = lastName;
    }


    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getFullName() {
        return String.format("%s %s", firstName, lastName);
    }
}
