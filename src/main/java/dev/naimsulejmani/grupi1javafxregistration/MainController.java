package dev.naimsulejmani.grupi1javafxregistration;

import dev.naimsulejmani.grupi1javafxregistration.models.Person;
import dev.naimsulejmani.grupi1javafxregistration.repositories.PersonRepository;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

public class MainController {
    private PersonRepository repository;

    public void initialize() {
        // eshte nje metode qe thirret e para sikurse logjika e constructor
        repository = new PersonRepository();
        refreshForm();
    }

    @FXML
    private TextField tfName;
    @FXML
    private TextField tfSurname;
    @FXML
    private DatePicker dpBirthdate;
    @FXML
    private CheckBox chbPassive;


    public void resetForm(ActionEvent actionEvent) {
        tfName.clear();
        tfSurname.clear();
        dpBirthdate.setValue(null);
        chbPassive.setSelected(false);
    }


    public void registerForm(ActionEvent actionEvent) {
        Person person = getPerson();
        System.out.println(person);
        if(person != null) {
            repository.add(person);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Success");
            alert.setHeaderText(null);
            alert.setContentText("Person registered successfully");
            alert.showAndWait();
            refreshForm();
        }
    }

    private void refreshForm() {
        resetForm(null);
        System.out.println("FIll List");
        System.out.println("Fill Table");
    }

    private Person getPerson() {

        if (tfName.getText().isBlank()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Please enter a name for the person");
            alert.showAndWait();
            return null;
        }
        Person person = new Person();
        person.setName(tfName.getText());
        person.setSurname(tfSurname.getText());
        person.setBirthdate(dpBirthdate.getValue());
        person.setPassive(chbPassive.isSelected());

        return person;
    }
}
