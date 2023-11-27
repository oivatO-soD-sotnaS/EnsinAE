package otaviosantos.ensinae;

import dao.UserDao;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import models.UserAccesLevels;
import models.UserSecurity;
import DTO.User;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

public class RegisterStudantController {

    //Text Fields
    @FXML
    private TextField nameField;
    @FXML
    private TextField surnameField;
    @FXML
    private TextField emailField;
    @FXML
    private TextField cpfField;
    @FXML
    private PasswordField passwordField;
    //Error labels
    @FXML
    private Label nameError;
    @FXML
    private Label surnameError;
    @FXML
    private Label emailError;
    @FXML
    private Label cpfError;
    @FXML
    private Label passwordError;
    //methods

    private void generateError(Label label, String error){
        label.setText(error);
    }

    private void removeError(Label label){
        label.setText("");
    }

    private boolean checkName() {
        String name = this.nameField.getText();
        String output = UserSecurity.checkName(name);
        if(output.equals("valid name")){
            removeError(this.nameError);
            return true;
        }
        generateError(this.nameError, output);
        return false;
    }

    private boolean checkSurname(){
        String surname = this.surnameField.getText();
        String output = UserSecurity.checkSurname(surname);
        if(output.equals("valid surname")){
            removeError(this.nameError);
            return true;
        }
        generateError(this.surnameError, output);
        return false;
    }

    private boolean checkEmail(){
        String email = this.emailField.getText();
        String output = UserSecurity.checkEmail(email);
        if(output.equals("valid email")){
            removeError(this.emailError);
            return true;
        }
        generateError(this.emailError, output);
        this.emailField.requestFocus();
        return false;
    }

    private boolean checkCPF(){
        String cpf = this.cpfField.getText();
        String output = UserSecurity.checkCPF(cpf);
        if(output.equals("valid cpf")){
            removeError(this.cpfError);
            return true;
        }
        generateError(this.cpfError, output);
        this.cpfField.requestFocus();
        return false;
    }

    private boolean checkPassword() {
        String password = this.passwordField.getText();
        String output = UserSecurity.checkPassword(password);
        if(output.equals("valid password")){
            removeError(this.passwordError);
            return true;
        }
        generateError(this.passwordError, output);
        this.passwordField.requestFocus();
        return false;
    }

    private User createUser() throws NoSuchAlgorithmException {
        User user =  new User();
        user.setName(this.nameField.getText());
        user.setSurname(this.surnameField.getText());
        user.setEmail(this.emailField.getText());
        user.setCpf(this.cpfField.getText());
        user.setPassword(UserSecurity.sha256(this.passwordField.getText()));
        user.setAccess_level(UserAccesLevels.STUDANT.getAccessLevel());
        user.setStatus(false);
        return user;
    }

    public void checkInfo(ActionEvent event){
        if(checkName() && checkSurname() && checkEmail() && checkCPF() && checkPassword()){
            try {
                UserDao.insertUser(createUser());
                switchToLoginPage(event);
            } catch (NoSuchAlgorithmException | IOException e) {
                throw new RuntimeException(e);
            } catch (SQLException e) {
                generateError(this.cpfError, "Error: CPF já esta cadastrado");
                throw new RuntimeException(e);
            }
        }
    }
    @SuppressWarnings("all")
    public void switchToLoginPage(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("LoginPage.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.setTitle("Login page");
        stage.show();
    }
}