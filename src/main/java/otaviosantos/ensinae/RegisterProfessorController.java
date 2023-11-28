package otaviosantos.ensinae;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class RegisterProfessorController {

    @FXML
    private TextField cpfTextField;

    @FXML
    private Label cprError;

    @FXML
    private Label emailError;

    @FXML
    private TextField emailTextField;

    @FXML
    private Label nameError;

    @FXML
    private TextField nameTextField;

    @FXML
    private Label passwordError;

    @FXML
    private PasswordField passwordTextField;

    @FXML
    private Label surnameError;

    @FXML
    private TextField surnameTextField;

    
    @FXML
    void checkInfo(ActionEvent event) {

    }

}

