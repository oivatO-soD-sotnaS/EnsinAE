package otaviosantos.ensinae;

import dao.RegistrationDAO;
import dto.InactiveUserDTO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

public class AtualizeUsersPageController implements Initializable {

    @FXML
    private Label cpfLabel;

    @FXML
    private Label emailLabel;

    @FXML
    private Label surnameLabel;

    @FXML
    private Label nameLabel;

    @FXML
    private Button acceptUserButton;

    @FXML
    private TableColumn<InactiveUserDTO, String> email;

    @FXML
    private TableColumn<InactiveUserDTO, String> cpf;

    @FXML
    private TableColumn<InactiveUserDTO, Integer> id_user;

    @FXML
    private TableColumn<InactiveUserDTO, String> name;

    @FXML
    private TableColumn<InactiveUserDTO, String> surname;

    @FXML
    private TableView<InactiveUserDTO> tableView;

    @FXML
    public void getUserInfo(){
        int tableCurrentIndex = this.tableView.getSelectionModel().getSelectedIndex();

        if(tableCurrentIndex <= -1)
            return;

        this.acceptUserButton.setDisable(false);
        //Set labels with selected user info
        this.nameLabel.setText(this.name.getCellData(tableCurrentIndex));
        this.surnameLabel.setText(this.surname.getCellData(tableCurrentIndex));
        this.emailLabel.setText(this.email.getCellData(tableCurrentIndex));
        this.cpfLabel.setText(this.cpf.getCellData(tableCurrentIndex));
    }


    @FXML
    public void setUserActive(){

        try {
            RegistrationDAO.setUserActive(this.emailLabel.getText());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        this.updateTable();
    }

    private void updateTable(){
        try {
            List<InactiveUserDTO> inactiveUsers = RegistrationDAO.getInactiveUsers();
            ObservableList<InactiveUserDTO> initialData = FXCollections.observableList(inactiveUsers);
            this.tableView.setItems(initialData);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        assert this.tableView != null;
        this.id_user.setCellValueFactory(new PropertyValueFactory<>("id_user"));
        this.name.setCellValueFactory(new PropertyValueFactory<>("name"));
        this.surname.setCellValueFactory(new PropertyValueFactory<>("surname"));
        this.email.setCellValueFactory(new PropertyValueFactory<>("email"));
        this.cpf.setCellValueFactory(new PropertyValueFactory<>("cpf"));

        this.updateTable();
    }
}
