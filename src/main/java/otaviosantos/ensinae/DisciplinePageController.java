package otaviosantos.ensinae;

import dao.DisciplineDAO;
import dao.RegistrationDAO;
import dto.DisciplineStudantDTO;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class DisciplinePageController implements Initializable {
    //            this.showPendingRegistrationsButton.setVisible(true);

    private Integer id_discipline;

    private Integer tableCurrentIndex;

    private Boolean currentStudantsStatusSearch;

    @FXML
    private Label emailLabel;

    @FXML
    private Label nameLabel;

    @FXML
    private Label cpfLabel;

    @FXML
    private TableView<DisciplineStudantDTO> tableView;

    @FXML
    private TableColumn<DisciplineStudantDTO, String> surnameColumn;

    @FXML
    private TableColumn<DisciplineStudantDTO, Integer> id_userColumn;

    @FXML
    private TableColumn<DisciplineStudantDTO, String> nameColumn;

    @FXML
    private TableColumn<DisciplineStudantDTO, String> cpfColumn;

    @FXML
    private TableColumn<DisciplineStudantDTO, String> emailColumn;

    @FXML
    private Button removeStudantButton;

    @FXML
    private Button addStudantsButton;

    @FXML
    private Button showActiveRegistrationButton;

    @FXML
    private Button showPendingRegistrationsButton;

    @FXML
    private TextField searchBar;

    public void showPendingRegistrations(){
        this.removeStudantButton.setVisible(false);
        this.addStudantsButton.setVisible(true);
        this.currentStudantsStatusSearch = false;
        updateTable(this.searchBar.getText(), this.currentStudantsStatusSearch);
    }

    public void showActiveRegistration() {
        this.addStudantsButton.setVisible(false);
        this.removeStudantButton.setVisible(true);
        this.currentStudantsStatusSearch = true;
        updateTable(this.searchBar.getText(), this.currentStudantsStatusSearch);
    }

    @FXML
    public void removeStudant() {
        try {
            int id_user = RegistrationDAO.searchUser(this.emailColumn.getCellData(this.tableCurrentIndex)).id_user();
            DisciplineDAO.removeUserRegistration(
                    id_user,
                    this.id_discipline
            );
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        updateTable(this.searchBar.getText(), this.currentStudantsStatusSearch);
    }

    @FXML
    public void updatePendingRegistration(){
        try {
            DisciplineDAO.updateRegistration(this.emailLabel.getText());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        updateTable(this.searchBar.getText(), this.currentStudantsStatusSearch);
    }
    @FXML
    public void searchStudant(){
        updateTable(this.searchBar.getText(), this.currentStudantsStatusSearch);
    }
    @FXML
    public void updateTable(String pattern, Boolean status) {
        try {
            ObservableList<DisciplineStudantDTO> initialData =
                    FXCollections.observableList(DisciplineDAO.getDisciplineStudants(status, this.id_discipline, pattern));
            this.tableView.setItems(initialData);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void initDisciplinePage(Integer id_discipline, String professorEmail, String userEmail){
        this.currentStudantsStatusSearch = true;
        this.id_discipline = id_discipline;
        if(professorEmail.equals(userEmail)){
            this.removeStudantButton.setVisible(true);
            this.addStudantsButton.setVisible(true);
            this.showActiveRegistrationButton.setVisible(true);
            this.showPendingRegistrationsButton.setVisible(true);
        }
        updateTable("", this.currentStudantsStatusSearch);
    }

    public void getStudantInfo() {
        this.tableCurrentIndex = this.tableView.getSelectionModel().getSelectedIndex();

        this.nameLabel.setText(this.nameColumn.getCellData(this.tableCurrentIndex)
                + this.surnameColumn.getCellData(this.tableCurrentIndex));
        this.emailLabel.setText(this.emailColumn.getCellData(this.tableCurrentIndex));
        this.cpfLabel.setText(this.cpfColumn.getCellData(this.tableCurrentIndex));

        this.removeStudantButton.setDisable(false);
        this.addStudantsButton.setDisable(false);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.id_userColumn.setCellValueFactory(new PropertyValueFactory<>("id_user"));
        this.nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        this.surnameColumn.setCellValueFactory(new PropertyValueFactory<>("surname"));
        this.emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
        this.cpfColumn.setCellValueFactory(new PropertyValueFactory<>("cpf"));
    }
}
