package otaviosantos.ensinae;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import DTO.User;

import java.net.URL;
import java.util.ResourceBundle;

public class HomePageController implements Initializable{

    @FXML
    private Button createDisciplineButton;

    @FXML
    private Button dropDisciplineButton;

    @FXML
    private Button createProfessorButton;
    @FXML
    private TableView<?> disciplinesTableView;

    @FXML
    private TableColumn<?, ?> tableDisciplineID;

    @FXML
    private TableColumn<?, ?> tableDisciplineName;

    @FXML
    private TableColumn<?, ?> tableDisciplineProfessor;

    @FXML
    private TableColumn<?, ?> tableDisciplineProfessorEmail;

    @FXML
    private Label selectedDisciplineDescription;

    @FXML
    private Label selectedDisciplineName;

    @FXML
    private Label userCPF;

    @FXML
    private Label userEmail;

    @FXML
    private Label userName;

    @FXML
    void changeUserInfo(ActionEvent event) {

    }

    @FXML
    void createDiscipline(ActionEvent event) {

    }

    @FXML
    void dropDiscipline(ActionEvent event) {

    }

    @FXML
    void enterDiscipline(ActionEvent event) {

    }

    @FXML
    void getOutOfDiscipline(ActionEvent event) {

    }

    @FXML
    void refreshTable(MouseEvent event) {

    }

    @FXML
    void searchDiscipline(MouseEvent event) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
    private void checkUserAccessLevel(User user){
        if(!user.getAccess_level().equals("professor")){
            this.createDisciplineButton.setVisible(true);
            this.dropDisciplineButton.setVisible(true);
        }else if(!user.getAccess_level().equals("ADM")){
            this.createProfessorButton.setVisible(true);
        }
    }
}

