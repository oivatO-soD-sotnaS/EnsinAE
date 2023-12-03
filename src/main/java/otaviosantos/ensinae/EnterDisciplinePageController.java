package otaviosantos.ensinae;

import dao.UserDao;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import vo.User;

import java.sql.SQLException;

public class EnterDisciplinePageController {

    private User activeUser;
    @FXML
    private TextField accessCodeTextField;

    @FXML
    private Label codeError;


    public void setActiveUser(User user){
        this.activeUser = user;
    }
    @FXML
    public void addPendingRegistration() {
        try {
            UserDao.addRegistration(this.activeUser.id_user(), this.accessCodeTextField.getText());
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Sucesso");
            alert.setHeaderText(null);
            alert.setContentText("Seu pedido de mátricula foi registrado, aguarda a confirmação do professor responsável.");
            alert.showAndWait();
        } catch (SQLException e) {
            codeError.setText("Código inexistente.");
            throw new RuntimeException(e);
        }
    }

}

