package otaviosantos.ensinae;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.regex.Pattern;

public class RegisterController {

    private Stage stage;
    private Scene scene;
    private Parent root;
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
    private TextField passwordField;
    @FXML
    private DatePicker birthdayField;
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
        if (this.nameField.getText().isEmpty()) {
            generateError(this.nameError, "Error: nome não pode ser vazio.");
            this.nameField.requestFocus();
            return false;
        }
        removeError(this.nameError);
        return true;
    }
     private boolean checkSurname(){
        if(this.surnameField.getText().isEmpty()){
            generateError(this.surnameError, "Error: sobrenome não pode ser vazio.");
            this.surnameField.requestFocus();
            return false;
        }
        removeError(this.surnameError);
        return true;
    }

    private boolean checkEmail(){
        String email = this.emailField.getText();
        String emailRegex = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@"
                + "[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";
        if(email == null || email.isEmpty()){
            generateError(this.emailError, "Error: caixa de email vazia.");
            this.emailField.requestFocus();
            return false;
        }
        Pattern pattern = Pattern.compile(emailRegex);
        if(!pattern.matcher(email).matches()){
            generateError(this.emailError, "Error: padrão de email inválido");
            this.emailField.requestFocus();
            return false;
        }
        removeError(this.emailError);
        return true;
    }
    private boolean checkCPF(){
        String cpf = this.cpfField.getText();
        if(cpf.isEmpty() || cpf.length() != 11){
            generateError(this.cpfError, "Error: caixa de cpf vazia ou incompleta.");
            return false;
        }
        cpf = cpf.replace( ".", "");
        cpf = cpf.replace("-","");
        int weight = 1;
        int sum = 0;
        for(int index = 0; index < 9; index++){
            sum += (Integer.parseInt(String.valueOf(cpf.charAt(index))) * weight++);
        }
        int firstVerifierDigit = sum % 11;
        if(firstVerifierDigit == 10) firstVerifierDigit = 0;
        if(Integer.parseInt(String.valueOf(cpf.charAt(9))) != firstVerifierDigit){
            generateError(this.cpfError, "Error: cpf inválido");
            this.cpfError.requestFocus();
            return false;
        }
        weight = 0;
        sum = 0;
        for(int index = 0; index < 10; index++){
            sum += (Integer.parseInt(String.valueOf(cpf.charAt(index))) * weight++);
        }
        int secondVerifierDigit = sum % 11;
        if(secondVerifierDigit == 10) firstVerifierDigit = 0;
        if(Integer.parseInt(String.valueOf(cpf.charAt(10))) != secondVerifierDigit){
            generateError(this.cpfError, "Error: cpf inválido");
            this.cpfError.requestFocus();
            return false;
        }
        removeError(this.cpfError);
        return true;
    }
    private boolean checkPassword(){
        if(this.passwordField.getText().length() < 8 || this.passwordField.getText().length() > 12){
            generateError(this.passwordError, "Error: senha deve ter entre 8 e 12 caracteres.");
            return false;
        }
        String password = this.passwordField.getText();
        String passwordRegex = "[^a-zA-Z0-9]";
        Pattern pattern = Pattern.compile(passwordRegex);
        if(!pattern.matcher(password).find()){
            generateError(this.passwordError, "Erro: senha deve contar no mínimo um caractere especial");
            return false;
        }
        removeError(this.passwordError);
        return true;
    }
    public void checkInfo(){
        if(checkName() && checkSurname() && checkEmail() && checkCPF() && checkPassword()){
            System.out.println("comprovado!");
        }
    }
    public void switchToLoginPage(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("LoginPage.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.setTitle("Login page");
        stage.show();
    }
}