package otaviosantos.ensinae;

import dao.UserDao;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import modelos.Hashing;
import modelos.User;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;


public class LoginController {

    //Text Fields
    @FXML
    private TextField emailTextField;
    @FXML
    private TextField passwordTextField;
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

    private boolean checkEmail() throws SQLException, NoSuchAlgorithmException {
        String email = this.emailTextField.getText();
        if(email.isEmpty()){
            generateError(this.emailError, "Error: caixa de email vazia");
            this.emailTextField.requestFocus();
            return false;
        }
        removeError(this.emailError);
        User user = UserDao.searchUser(email);
        if(user == null){
            generateError(this.emailError, "Error: Email não cadastrado.");
            return false;
        }
        removeError(this.emailError);
        return checkPassword(user);
    }

    private boolean checkPassword(User user) throws NoSuchAlgorithmException {
        String password = Hashing.hash256(this.passwordTextField.getText());

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

    public void checkUserInfo() throws SQLException, NoSuchAlgorithmException {
        if(checkEmail())
            System.out.println("Usuária encontrado.");
    }

    @SuppressWarnings("all")
    public void returnToRegisterPage(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("RegisterPage.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.setTitle("Registration page");
        stage.show();
    }
}
