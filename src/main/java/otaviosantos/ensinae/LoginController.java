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
import models.UserSecurity;
import vo.User;

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

    private User searchUser() throws SQLException, NoSuchAlgorithmException {
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
            this.emailTextField.requestFocus();
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
            this.passwordField.requestFocus();
            return false;
        }else if(!password.equals(user.password())){
            generateError(this.passwordError, "Error: senha inválida.");
            this.passwordField.requestFocus();
            return false;
        }
        removeError(this.passwordError);
        return true;
    }

    public void checkUserInfo(ActionEvent event) {
        User user;
        try {
            user = searchUser();
            if(user == null)
                return;
            if(!user.status()){
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Error");
                alert.setHeaderText("Conta de usuário não foi verificada");
                alert.setContentText("Esta conta de usuário esta registrada porém ainda não foi confirmada, por favor espere até que um ADM autorize o acesso à sua conta.");
                alert.show();
            }else{
                switchToHomePage(event, user);
            }
        } catch (SQLException | NoSuchAlgorithmException | IOException e) {
            throw new RuntimeException(e);
        }
    }
    @SuppressWarnings("all")
    private void switchToHomePage(ActionEvent event, User user) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("HomePage.fxml"));
        Parent root = fxmlLoader.load();
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        HomePageController homePageController = fxmlLoader.getController();
        homePageController.innitHomePage(user);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.setTitle("Página Inicial");
        stage.show();
    }

    @SuppressWarnings("all")
    public void returnToRegisterPage(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("RegisterStudantPage.fxml"));
        Parent root = fxmlLoader.load();
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.setTitle("Página de Registro");
        stage.show();
    }
}
