package otaviosantos.ensinae;

import dao.UserDao;
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

    private Integer id_discipline;

    private Integer tableCurrentIndex;

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
    private TextField searchBar;


    @FXML
    public void removeStudant() {
        try {
            int id_user = UserDao.searchUser(this.emailColumn.getCellData(this.tableCurrentIndex)).id_user();
            UserDao.removeUserRegistration(
                    id_user,
                    this.id_discipline
            );
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        updateTable(this.searchBar.getText());
    }
    @FXML
    public void searchStudant(){
        updateTable(this.searchBar.getText());
    }
    @FXML
    public void updateTable(String pattern) {
        try {
            ObservableList<DisciplineStudantDTO> initialData =
                    FXCollections.observableList(UserDao.getDisciplineStudants(this.id_discipline, pattern));
            this.tableView.setItems(initialData);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void initDisciplinePage(Integer id_discipline, String professorEmail, String userEmail){
        this.id_discipline = id_discipline;
        if(professorEmail.equals(userEmail)){
            this.removeStudantButton.setVisible(true);
        }
        updateTable("");
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.id_userColumn.setCellValueFactory(new PropertyValueFactory<>("id_user"));
        this.nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        this.surnameColumn.setCellValueFactory(new PropertyValueFactory<>("surname"));
        this.emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
        this.cpfColumn.setCellValueFactory(new PropertyValueFactory<>("cpf"));
    }

    public void getStudantInfo() {
        this.tableCurrentIndex = this.tableView.getSelectionModel().getSelectedIndex();

        this.nameLabel.setText(this.nameColumn.getCellData(this.tableCurrentIndex)
                + this.surnameColumn.getCellData(this.tableCurrentIndex));
        this.emailLabel.setText(this.emailColumn.getCellData(this.tableCurrentIndex));
        this.cpfLabel.setText(this.cpfColumn.getCellData(this.tableCurrentIndex));

        this.removeStudantButton.setDisable(false);
    }
}
