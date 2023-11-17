package otaviosantos.ensinae.Controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;


public class LoginController {

    @FXML
    private TextField loginCpfTextField;
    @FXML
    private TextField passwordLoginTextField;
    @FXML
    private Label cpfError;
    @FXML
    private Label passwordError;

    private void generateCpfError(int errorType){
        if(errorType == 1){
            cpfError.setText("Erro: CPF Não cadastrado.");
        }else if(errorType == 2){
            cpfError.setText("Erro: CPF inválido.");
        }
        loginCpfTextField.setText("");
    }
    private void generatePasswordError(){
        passwordError.setText("Erro: senha incorreta.");
        loginCpfTextField.setText("");
    }

    private boolean checkCPF(){
        return true;
    }
    private boolean checkPassword(){
        System.out.println(this.passwordLoginTextField.getCharacters());
        System.out.println(this.passwordLoginTextField.getCharacters() == "otavio");
        return false;
    }

    public void checkUserInfo(){
        System.out.println("Teste");
        checkPassword();
    }
}
