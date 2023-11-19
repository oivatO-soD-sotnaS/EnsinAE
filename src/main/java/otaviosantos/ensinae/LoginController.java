package otaviosantos.ensinae;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;


public class LoginController {

    private Stage stage;
    private Scene scene;
    private Parent root;
    //Text Fields
    @FXML
    private TextField loginEmailTextField;
    @FXML
    private TextField passwordLoginTextField;
    //Error labels
    @FXML
    private Label emailError;
    @FXML
    private Label passwordError;


    public void checkUserInfo(){

    }
    public void returnToRegisterPage(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("RegisterPage.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.setTitle("Registration page");
        stage.show();
    }
}
