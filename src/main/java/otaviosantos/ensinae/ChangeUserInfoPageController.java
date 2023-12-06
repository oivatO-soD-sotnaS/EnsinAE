package otaviosantos.ensinae;

import dao.RegistrationDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.stage.Stage;
import util.UserSecurity;
import models.User;

import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

public class ChangeUserInfoPageController {

    private User activeUser;


    @FXML
    private Label newPasswordError;

    @FXML
    private PasswordField newPasswordTextField;

    @FXML
    private Label cpfError;

    @FXML
    private TextField emailTextField;

    @FXML
    private Label emailError;

    @FXML
    private TextField cpfTextField;

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
    private boolean checkOldPassword() {
        try {
            if(!UserSecurity.sha256(this.passwordTextField.getText()).equals(this.activeUser.password())) {
                generateError(this.passwordError, "Esta senha deve ser identica a sua senha anterior!");
                return false;
            }
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
        removeError(this.passwordError);
        return true;
    }
    private boolean checkNewPassword(){
        String password = this.newPasswordTextField.getText();
        if(password.isEmpty())
            return true;
        String output = UserSecurity.checkPassword(password);
        if(output.equals("valid password")){
            removeError(this.newPasswordError);
            return true;
        }
        generateError(this.newPasswordError, output);
        this.newPasswordTextField.requestFocus();
        return false;
    }
    private User createUpdatedUser() throws NoSuchAlgorithmException {
        return new User(this.activeUser.id_user(),
                this.nameTextField.getText(),
                this.surnameTextField.getText(),
                this.emailTextField.getText(),
                this.cpfTextField.getText(),
                this.newPasswordTextField.getText().isEmpty() ?
                        UserSecurity.sha256(this.passwordTextField.getText()) :
                        UserSecurity.sha256(this.newPasswordTextField.getText()),
                this.activeUser.access_level(),
                this.activeUser.status());
    }
    private void updateTextFields(){
        this.nameTextField.setText(activeUser.name());
        this.surnameTextField.setText(activeUser.surname());
        this.cpfTextField.setText(activeUser.cpf());
        this.emailTextField.setText(activeUser.email());
    }
    void setUser(User user){
        this.activeUser = user;
        updateTextFields();
    }
    @FXML
    public void updateUser(ActionEvent event){
        if(checkName() && checkSurname() && checkCPF() && checkEmail() && checkOldPassword() && checkNewPassword()){
            try {
                RegistrationDAO.updateUser(createUpdatedUser());

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Sucesso");
                alert.setHeaderText(null);
                alert.setContentText("Suas informações pessoais foram alteradas com sucesso!");
                alert.showAndWait();

                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.close();
            } catch (SQLException e) {
                if(e.getMessage().equals(
                        String.format("Duplicate entry '%s' for key 'user.email'", this.emailTextField.getText()))
                ){
                    generateError(this.emailError, "Error: EMAIL já esta cadastrado");
                    this.cpfTextField.requestFocus();
                }else {
                    generateError(this.cpfError, "Error: CPF já esta cadastrado");
                    this.cpfTextField.requestFocus();
                }
                throw new RuntimeException(e);
            } catch (NoSuchAlgorithmException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
