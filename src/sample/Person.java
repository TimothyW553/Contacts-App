package sample;

// important JavaFX String
import javafx.beans.property.SimpleStringProperty;

public class Person {

    // Instance variables
    private SimpleStringProperty firstName; // private String for first name
    private SimpleStringProperty lastName; // private String for last name
    private SimpleStringProperty email; // private String for email
    private SimpleStringProperty phoneNumber; // private String for phone number
    private SimpleStringProperty address; // private String for address

    // Constructor for Person object
    Person(String fName, String lName, String email, String pNumber, String address) {
        this.firstName = new SimpleStringProperty(fName); // initialize first name
        this.lastName = new SimpleStringProperty(lName); // initialize last name
        this.email = new SimpleStringProperty(email); // initialize email
        this.phoneNumber = new SimpleStringProperty(pNumber); // initialize phone number
        this.address = new SimpleStringProperty(address); // initialize address
    }

    // Accessor Method for getting first name
    public String getFirstName() {
        return firstName.get();
    }

    // Mutator Method for changing first name
    public void setFirstName(String fName) {
        firstName.set(fName);
    }

    // Accessor Method for getting last name
    public String getLastName() {
        return lastName.get();
    }

    // Mutator Method for changing last name
    public void setLastName(String fName) {
        lastName.set(fName);
    }

    // Accessor Method for getting email
    public String getEmail() {
        return email.get();
    }

    // Mutator Method for changing email
    public void setEmail(String fName) {
        email.set(fName);
    }

    // Accessor Method for getting phone number
    public String getPhoneNumber() {
        return phoneNumber.get();
    }

    // Mutator Method for changing phone number
    public void setPhoneNumber(String pNumber) {
        phoneNumber.set(pNumber);
    }

    // Accessor Method for getting address
    public String getAddress() {
        return address.get();
    }

    // Mutator Method for changing address
    public void setAddress(String loc) {
        address.set(loc);
    }
}
