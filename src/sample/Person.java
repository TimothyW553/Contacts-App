package sample;

import javafx.beans.property.SimpleStringProperty;

public class Person {

    // Instance variables
    private SimpleStringProperty firstName; // private String for first name
    private SimpleStringProperty lastName; // private String for last name
    private SimpleStringProperty email; // private String for email
    private SimpleStringProperty phoneNumber; // private String for phone number
    private SimpleStringProperty address; // private String for address

    // Constructor
    Person(String fName, String lName, String email, String pNumber, String address) {
        this.firstName = new SimpleStringProperty(fName); // initialize first name
        this.lastName = new SimpleStringProperty(lName); // initialize last name
        this.email = new SimpleStringProperty(email); // initialize email 
        this.phoneNumber = new SimpleStringProperty(pNumber); // initialize phone number
        this.address = new SimpleStringProperty(address); // initialize address
    }

    public String getFirstName() {
        return firstName.get();
    }

    public void setFirstName(String fName) {
        firstName.set(fName);
    }

    public String getLastName() {
        return lastName.get();
    }

    public void setLastName(String fName) {
        lastName.set(fName);
    }

    public String getEmail() {
        return email.get();
    }

    public void setEmail(String fName) {
        email.set(fName);
    }

    public String getPhoneNumber() {
        return phoneNumber.get();
    }

    public void setPhoneNumber(String pNumber) {
        phoneNumber.set(pNumber);
    }

    public String getAddress() {
        return address.get();
    }

    public void setAddress(String loc) {
        address.set(loc);
    }
}
