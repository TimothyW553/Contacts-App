/*
 * Group Members: Timothy Wang and Jim Liu
 * Project Name: Contacts App
 * Work Cited Links:
 *  > Tableview: https://docs.oracle.com/javafx/2/ui_controls/table-view.htm
 *  > Row delete: https://stackoverflow.com/questions/34857007/how-to-delete-row-from-table-column-javafx
 *  > Reading and Writing to CSV file: https://stackabuse.com/reading-and-writing-csvs-in-java/
 *  > Event handling: https://code.makery.ch/blog/javafx-8-event-handling-examples/
 */
package sample;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import java.io.*;
import java.util.*;

public class Main extends Application {

    private TableView<Person> table = new TableView<>(); // table view class for contacts
    private final ObservableList<Person> data = FXCollections.observableArrayList(); // data for all contacts
    final HBox hb = new HBox(); // horizontal rows

    // deletes row given index
    private static void delete(int index) throws IOException {
        File contacts = new File("C:\\Users\\Timothy Wang\\IdeaProjects\\Contacts_App\\src\\sample\\ContactData.csv");
        BufferedReader br = new BufferedReader(new FileReader(contacts));
        String str;
        String text = "";
        for (int i = 0; (str = br.readLine()) != null; i++){
            if(i != index) {
                text += str + "\n";
            }
        }
        BufferedWriter bw = new BufferedWriter(new FileWriter(contacts));
        bw.write("");
        bw.write(text);
        bw.close();

    }

    // reads a file and converts to string
    private static String readFile(String fileName) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(fileName));
        try {
            StringBuilder sb = new StringBuilder();
            String line = br.readLine();

            while (line != null) {
                sb.append(line);
                sb.append("\n");
                line = br.readLine();
            }
            return sb.toString();
        } finally {
            br.close();
        }
    }

    // writes to a file
    private static void writeUsingBufferedWriter(String data, int noOfLines) {
        File file = new File("C:\\Users\\Timothy Wang\\IdeaProjects\\Contacts_App\\src\\sample\\ContactData.csv");
        FileWriter fr = null;
        BufferedWriter br = null;
        String dataWithNewLine = data + System.getProperty("line.separator");
        try{
            fr = new FileWriter(file, true);
            br = new BufferedWriter(fr);
            for(int i = noOfLines; i > 0; i--){
                br.write(dataWithNewLine);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                br.close();
                fr.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        // Create a scene in the stage
        Scene scene = new Scene(new Group());
        stage.setTitle("ICS4U Contacts App");
        stage.setWidth(1280);
        stage.setHeight(720);

        final Label label = new Label("Contacts App");
        label.setFont(new Font("Consolas", 20));

        table.setEditable(true);

        // Header for first name
        TableColumn firstNameCol = new TableColumn("First Name");
        firstNameCol.setMinWidth(200);
        firstNameCol.setCellValueFactory(new PropertyValueFactory<Person, String>("firstName"));
        firstNameCol.setCellFactory(TextFieldTableCell.forTableColumn());
        firstNameCol.setOnEditCommit((EventHandler<CellEditEvent<Person, String>>) t -> t.getTableView().getItems().get(
                            t.getTablePosition().getRow()).setFirstName(t.getNewValue())
        );

        // Header for last name
        TableColumn lastNameCol = new TableColumn("Last Name");
        lastNameCol.setMinWidth(200);
        lastNameCol.setCellValueFactory(new PropertyValueFactory<Person, String>("lastName"));
        lastNameCol.setCellFactory(TextFieldTableCell.forTableColumn());
        lastNameCol.setOnEditCommit(
                (EventHandler<CellEditEvent<Person, String>>) t -> t.getTableView().getItems().get(
                        t.getTablePosition().getRow()).setLastName(t.getNewValue())
        );

        // Header for email
        TableColumn emailCol = new TableColumn("Email");
        emailCol.setMinWidth(300);
        emailCol.setCellValueFactory(new PropertyValueFactory<Person, String>("email"));
        emailCol.setCellFactory(TextFieldTableCell.forTableColumn());
        emailCol.setOnEditCommit(
                (EventHandler<CellEditEvent<Person, String>>) t -> t.getTableView().getItems().get(
                        t.getTablePosition().getRow()).setEmail(t.getNewValue())
        );

        // Header for phone number
        TableColumn phoneCol = new TableColumn("Phone Number");
        phoneCol.setMinWidth(300);
        phoneCol.setCellValueFactory(new PropertyValueFactory<Person, String>("phoneNumber"));
        phoneCol.setCellFactory(TextFieldTableCell.forTableColumn());
        phoneCol.setOnEditCommit(
                (EventHandler<CellEditEvent<Person, String>>) t -> t.getTableView().getItems().get(
                        t.getTablePosition().getRow()).setPhoneNumber(t.getNewValue())
        );

        // Header for address
        TableColumn addressCol = new TableColumn("Address");
        addressCol.setMinWidth(240);
        addressCol.setCellValueFactory(new PropertyValueFactory<Person, String>("Address"));
        addressCol.setCellFactory(TextFieldTableCell.forTableColumn());
        addressCol.setOnEditCommit(
                (EventHandler<CellEditEvent<Person, String>>) t -> t.getTableView().getItems().get(
                        t.getTablePosition().getRow()).setAddress(t.getNewValue())
        );

        table.setItems(data);
        table.getColumns().addAll(firstNameCol, lastNameCol, emailCol, phoneCol, addressCol);

        // Text field for new contact - first name
        final TextField addFirstName = new TextField();
        addFirstName.setMaxWidth(firstNameCol.getPrefWidth());
        addFirstName.setPromptText("First Name");

        // Text field for new contact - last name
        final TextField addLastName = new TextField();
        addLastName.setMaxWidth(lastNameCol.getPrefWidth());
        addLastName.setPromptText("Last Name");

        // Text field for new contact - email
        final TextField addEmail = new TextField();
        addEmail.setMaxWidth(emailCol.getPrefWidth());
        addEmail.setPromptText("Email");

        // Text field for new contact - phone number
        final TextField addPhoneNumber = new TextField();
        addPhoneNumber.setMaxWidth(phoneCol.getPrefWidth());
        addPhoneNumber.setPromptText("Phone Number");

        // Text field for new contact - address
        final TextField addAddress = new TextField();
        addAddress.setMaxWidth(addressCol.getPrefWidth());
        addAddress.setPromptText("Address");

        // Add button for new contact
        final Button addButton = new Button("Add");

        // Delete button for existing contact
        final Button deleteButton = new Button("Delete");

        // Initialize table from CSV file
        try {
            String contacts = readFile("C:\\Users\\Timothy Wang\\IdeaProjects\\Contacts_App\\src\\sample\\ContactData.csv");
            String[] arr = contacts.split("\n");
            for(int i = 0; i < arr.length; i++) {
                String[] details = arr[i].split(", ");
                data.add(new Person(details[0], details[1], details[2], details[3], details[4]));
            }
        } catch (IOException b) {
            b.printStackTrace();
        }

        // Deletes contact by getting row and deleting index
        deleteButton.setOnAction(e -> {
            try {
                Person selectedItem = table.getSelectionModel().getSelectedItem();
                String tempFN = selectedItem.getFirstName();
                String tempLN = selectedItem.getLastName();
                String tempE = selectedItem.getEmail();
                String tempPN = selectedItem.getPhoneNumber();
                String tempAD = selectedItem.getAddress();
                for (int i = 0; i < data.size(); i++) {
                    if (data.get(i).getFirstName().equals(tempFN) && data.get(i).getLastName().equals(tempLN)
                            && data.get(i).getEmail().equals(tempE) && data.get(i).getPhoneNumber().equals(tempPN)
                            && data.get(i).getAddress().equals(tempAD)) {
                        data.remove(i);
                        delete(i);
                    }
                }
                table.getItems().remove(selectedItem);
            } catch(IOException b) {
                b.printStackTrace();
            }
        });

        // Adds contact from text field
        addButton.setOnAction(e -> {
            String curFN = addFirstName.getText();
            String curLN = addLastName.getText();
            String curE = addEmail.getText();
            String curPN = addPhoneNumber.getText();
            String curAD = addAddress.getText();
            data.add(new Person(curFN, curLN, curE, curPN, curAD));
            String personInfo = curFN + ", " + curLN + ", " + curE + ", " + curPN + ", " + curAD;
            // FirstName, LastName, Email, PhoneNumber, Address
            writeUsingBufferedWriter(personInfo, 1);
            addFirstName.clear();
            addLastName.clear();
            addEmail.clear();
            addPhoneNumber.clear();
            addAddress.clear();
        });

        // puts contact in horizontal row
        hb.getChildren().addAll(addFirstName, addLastName, addEmail, addPhoneNumber, addAddress, addButton, deleteButton);
        hb.setSpacing(5);

        final VBox vbox = new VBox();
        vbox.setSpacing(5);
        vbox.setPadding(new Insets(10, 10, 10, 10));
        vbox.getChildren().addAll(label, table, hb);

        ((Group) scene.getRoot()).getChildren().addAll(vbox);

        stage.setScene(scene);
        stage.show();
    }
}
