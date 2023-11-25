package otaviosantos.ensinae;

import dao.UserDao;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import modelos.UserSecurity;
import modelos.User;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;


public class LoginController {

    //Text Fields
    @FXML
    private TextField emailTextField;
    @FXML
    private PasswordField passwordField;
    //Error labels
    @FXML
    private Label emailError;
    @FXML
    private Label passwordError;


    private void generateError(Label label, String error){
        label.setText(error);
    }


    private void removeError(Label label){
        label.setText("");
    }

    private User checkEmail() throws SQLException, NoSuchAlgorithmException {
        String email = this.emailTextField.getText();
        if(email.isEmpty()){
            generateError(this.emailError, "Error: caixa de email vazia");
            this.emailTextField.requestFocus();
            return null;
        }
        removeError(this.emailError);
        User user = UserDao.searchUser(email);
        if(user == null){
            generateError(this.emailError, "Error: Email não cadastrado.");
            return null;
        }
        removeError(this.emailError);
        if(checkPassword(user)) return user;
        return null;
    }

    private boolean checkPassword(User user) throws NoSuchAlgorithmException {
        String password = UserSecurity.sha256(this.passwordField.getText());

        if(password.isEmpty()){
            generateError(this.passwordError, "Error: caixa de senha vazia.");
            return false;
        }else if(!password.equals(user.getPassword())){
            generateError(this.passwordError, "Error: senha inválida.");
            return false;
        }
        removeError(this.passwordError);
        return true;
    }

    public void checkUserInfo(ActionEvent event) throws SQLException, NoSuchAlgorithmException {
        User user = checkEmail();
        if(user != null) {
            if(!user.getStatus()){
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Error");
                alert.setHeaderText("Conta de usuário não foi verificada");
                alert.setContentText("Esta conta de usuário esta registrada porém ainda não foi confirmada, por favor espere até que um ADM autorize o acesso à sua conta.");
                alert.show();
            }else{
                try {
                    switchToHomePage(event);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
    @SuppressWarnings("all")
    private void switchToHomePage(ActionEvent event) throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("HomePage.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.setTitle("Home Page");
        stage.show();
    }

    @SuppressWarnings("all")
    public void returnToRegisterPage(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("RegisterStudantPage.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.setTitle("Registration page");
        stage.show();
    }
}
