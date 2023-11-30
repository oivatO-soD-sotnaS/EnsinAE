package otaviosantos.ensinae;

import dao.UserDao;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import models.UserAccesLevels;
import models.UserSecurity;
import vo.User;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

public class RegisterProfessorController {

    @FXML
    private TextField cpfTextField;

    @FXML
    private Label cpfError;

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

    private void generateError(Label label, String error){
        label.setText(error);
    }

    private void removeError(Label label){
        label.setText("");
    }

    private boolean checkName(){
        String name = this.nameTextField.getText();
        String output = UserSecurity.checkName(name);
        if(output.equals("valid name")){
            removeError(this.nameError);
            return true;
        }
        generateError(this.nameError, output);
        this.nameTextField.requestFocus();
        return false;
    }
    private boolean checkSurname(){
        String surname = this.surnameTextField.getText();
        String output = UserSecurity.checkSurname(surname);
        if(output.equals("valid surname")){
            removeError(this.surnameError);
            return true;
        }
        generateError(this.surnameError, output);
        this.surnameTextField.requestFocus();
        return false;
    }

    private boolean checkEmail(){
        String email = this.emailTextField.getText();
        String output = UserSecurity.checkEmail(email);
        if(output.equals("valid email")){
            removeError(this.emailError);
            return true;
        }
        generateError(this.emailError, output);
        this.emailTextField.requestFocus();
        return false;
    }

    private boolean checkCPF(){
        String cpf = this.cpfTextField.getText();
        String output = UserSecurity.checkCPF(cpf);
        if(output.equals("valid cpf")){
            removeError(this.cpfError);
            return true;
        }
        generateError(this.cpfError, output);
        this.cpfTextField.requestFocus();
        return false;
    }

    private boolean checkPassword() {
        String password = this.passwordTextField.getText();
        String output = UserSecurity.checkPassword(password);
        if(output.equals("valid password")){
            removeError(this.passwordError);
            return true;
        }
        generateError(this.passwordError, output);
        this.passwordTextField.requestFocus();
        return false;
    }

    private User createProfessor() throws NoSuchAlgorithmException {
        return new User(0,
                this.nameTextField.getText(),
                this.surnameTextField.getText(),
                this.emailTextField.getText(),
                this.cpfTextField.getText(),
                UserSecurity.sha256(this.passwordTextField.getText()),
                UserAccesLevels.PROFESSOR.getAccessLevel(),
                true);
    }
    @FXML
    void checkInfo(ActionEvent event) {
        if(checkName() && checkSurname() && checkEmail() && checkCPF() && checkPassword()){
            try {
                UserDao.insertUser(createProfessor());
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Aviso");
                alert.setHeaderText("Conta de professor cadastrada no sistema.");
                alert.setContentText("Nova conta para professores cadastrada no sistema com sucesso!");
                alert.show();
            } catch (NoSuchAlgorithmException e) {
                throw new RuntimeException(e);
            } catch (SQLException e) {
                generateError(this.cpfError, "Error: CPF já esta cadastrado");
                this.cpfTextField.requestFocus();
                throw new RuntimeException(e);
            }
        }
    }

}

