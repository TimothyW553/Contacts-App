package sample;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
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
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import java.io.*;

public class Main extends Application {

    private TableView<Person> table = new TableView<>();
    private final ObservableList<Person> data = FXCollections.observableArrayList();
    final HBox hb = new HBox();

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
        Scene scene = new Scene(new Group());
        stage.setTitle("ICS4U Contacts App");
        stage.setWidth(1280);
        stage.setHeight(720);

        final Label label = new Label("Contacts App");
        label.setFont(new Font("Arial", 20));

        table.setEditable(true);

        TableColumn firstNameCol = new TableColumn("First Name");
        firstNameCol.setMinWidth(200);
        firstNameCol.setCellValueFactory(
                new PropertyValueFactory<Person, String>("firstName"));
        firstNameCol.setCellFactory(TextFieldTableCell.forTableColumn());
        firstNameCol.setOnEditCommit(
                new EventHandler<CellEditEvent<Person, String>>() {
                    @Override
                    public void handle(CellEditEvent<Person, String> t) {
                        ((Person) t.getTableView().getItems().get(
                                t.getTablePosition().getRow())
                        ).setFirstName(t.getNewValue());
                    }
                }
        );

        TableColumn lastNameCol = new TableColumn("Last Name");
        lastNameCol.setMinWidth(200);
        lastNameCol.setCellValueFactory(
                new PropertyValueFactory<Person, String>("lastName"));
        lastNameCol.setCellFactory(TextFieldTableCell.forTableColumn());
        lastNameCol.setOnEditCommit(
                new EventHandler<CellEditEvent<Person, String>>() {
                    @Override
                    public void handle(CellEditEvent<Person, String> t) {
                        ((Person) t.getTableView().getItems().get(
                                t.getTablePosition().getRow())
                        ).setLastName(t.getNewValue());
                    }
                }
        );

        TableColumn emailCol = new TableColumn("Email");
        emailCol.setMinWidth(300);
        emailCol.setCellValueFactory(
                new PropertyValueFactory<Person, String>("email"));
        emailCol.setCellFactory(TextFieldTableCell.forTableColumn());
        emailCol.setOnEditCommit(
                new EventHandler<CellEditEvent<Person, String>>() {
                    @Override
                    public void handle(CellEditEvent<Person, String> t) {
                        ((Person) t.getTableView().getItems().get(
                                t.getTablePosition().getRow())
                        ).setEmail(t.getNewValue());
                    }
                }
        );

        TableColumn phoneCol = new TableColumn("Phone Number");
        phoneCol.setMinWidth(300);
        phoneCol.setCellValueFactory(
                new PropertyValueFactory<Person, String>("phoneNumber"));
        phoneCol.setCellFactory(TextFieldTableCell.forTableColumn());
        phoneCol.setOnEditCommit(
                new EventHandler<CellEditEvent<Person, String>>() {
                    @Override
                    public void handle(CellEditEvent<Person, String> t) {
                        ((Person) t.getTableView().getItems().get(
                                t.getTablePosition().getRow())
                        ).setPhoneNumber(t.getNewValue());
                    }
                }
        );

        TableColumn addressCol = new TableColumn("Address");
        addressCol.setMinWidth(240);
        addressCol.setCellValueFactory(
                new PropertyValueFactory<Person, String>("Address"));
        addressCol.setCellFactory(TextFieldTableCell.forTableColumn());
        addressCol.setOnEditCommit(
                new EventHandler<CellEditEvent<Person, String>>() {
                    public void handle(CellEditEvent<Person, String> t) {
                        ((Person) t.getTableView().getItems().get(
                                t.getTablePosition().getRow())
                        ).setAddress(t.getNewValue());
                    }
                }
        );
        table.setItems(data);
        table.getColumns().addAll(firstNameCol, lastNameCol, emailCol, phoneCol, addressCol);

        final TextField addFirstName = new TextField();
        addFirstName.setMaxWidth(firstNameCol.getPrefWidth());
        addFirstName.setPromptText("First Name");

        final TextField addLastName = new TextField();
        addLastName.setMaxWidth(lastNameCol.getPrefWidth());
        addLastName.setPromptText("Last Name");

        final TextField addEmail = new TextField();
        addEmail.setMaxWidth(emailCol.getPrefWidth());
        addEmail.setPromptText("Email");

        final TextField addPhoneNumber = new TextField();
        addPhoneNumber.setMaxWidth(phoneCol.getPrefWidth());
        addPhoneNumber.setPromptText("Phone Number");

        final TextField addAddress = new TextField();
        addAddress.setMaxWidth(addressCol.getPrefWidth());
        addAddress.setPromptText("Address");

        final Button addButton = new Button("Add");

        try {
            String contacts = readFile("C:\\Users\\Timothy Wang\\IdeaProjects\\Contacts_App\\src\\sample\\ContactData.csv");
            System.out.println(contacts);
            String[] arr = contacts.split("\n");
            for(int i = 0; i < arr.length; i++) {
                String[] details = arr[i].split(", ");
                data.add(new Person(details[0], details[1], details[2], details[3], details[4]));
            }

        } catch (IOException b) {
            b.printStackTrace();
        }
        addButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                data.add(new Person(
                        addFirstName.getText(),
                        addLastName.getText(),
                        addEmail.getText(),
                        addPhoneNumber.getText(),
                        addAddress.getText()));
                String personInfo = addFirstName.getText() +
                             ", " + addLastName.getText() +
                             ", " + addEmail.getText() +
                             ", " + addPhoneNumber.getText() +
                             ", " + addAddress.getText();
                // FirstName, LastName, Email, PhoneNumber, Address
                writeUsingBufferedWriter(personInfo, 1);
                System.out.println(personInfo);
                addFirstName.clear();
                addLastName.clear();
                addEmail.clear();
                addPhoneNumber.clear();
                addAddress.clear();
            }
        });

        hb.getChildren().addAll(addFirstName, addLastName, addEmail, addPhoneNumber, addAddress,addButton);
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
