package otaviosantos.ensinae;

import dao.DisciplineDAO;
import dao.RegistrationDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import models.User;

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
    public void addPendingRegistration(ActionEvent event) {
        try {
            if(DisciplineDAO.addRegistration(this.activeUser.id_user(), this.accessCodeTextField.getText(), false)){
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Sucesso");
                alert.setHeaderText(null);
                alert.setContentText("Seu pedido de mátricula foi registrado, aguarda a confirmação do professor responsável.");
                alert.showAndWait();

                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.close();
            }else
                codeError.setText("Código inexistente.");
        } catch (SQLException e) {
            codeError.setText("Você já está inscrito nesta disciplina!!");
            throw new RuntimeException(e);
        }
    }

}

