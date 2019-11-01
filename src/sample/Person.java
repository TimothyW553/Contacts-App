package sample;

import javafx.beans.property.SimpleStringProperty;

public class Person {

    private SimpleStringProperty firstName;
    private SimpleStringProperty lastName;
    private SimpleStringProperty email;
    private SimpleStringProperty phoneNumber;
    private SimpleStringProperty address;

    Person(String fName, String lName, String email, String pNumber, String address) {
        this.firstName = new SimpleStringProperty(fName);
        this.lastName = new SimpleStringProperty(lName);
        this.email = new SimpleStringProperty(email);
        this.phoneNumber = new SimpleStringProperty(pNumber);
        this.address = new SimpleStringProperty(address);
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