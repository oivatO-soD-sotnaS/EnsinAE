package otaviosantos.ensinae;


import dao.UserDao;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import models.User;
import dto.DisciplineTableDto;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

public class HomePageController implements Initializable{

    private User activeUser;
    private Integer tableIndex;

    @FXML
    private Button createDisciplineButton;

    @FXML
    private Button createProfessorButton;

    @FXML
    private TableView<DisciplineTableDto> tableView;

    @FXML
    private TableColumn<DisciplineTableDto, Integer> disciplineId;

    @FXML
    private TableColumn<DisciplineTableDto, String> disciplineName;

    @FXML
    private TableColumn<DisciplineTableDto, String> disciplineProfessor;


    @FXML
    private TableColumn<DisciplineTableDto, String> professorEmail;

    @FXML
    private TableColumn<DisciplineTableDto, String> disciplineDescription;

    @FXML
    private TableColumn<DisciplineTableDto, String> disciplineAccessCode;

    @FXML
    private Button enterDisciplineButton;

    @FXML
    private Button removeRegistrationButton;

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
    private TextField searchBar;

    @FXML
    public void changeUserInfo() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ChangeUserInfoPage.fxml"));
        Parent root = fxmlLoader.load();
        Stage stage = new Stage();
        ChangeUserInfoPageController changeUserInfoPageController = fxmlLoader.getController();
        changeUserInfoPageController.setUser(this.activeUser);
        stage.setTitle("Mudança de Informações do Usuário");
        stage.setResizable(false);
        stage.setScene(new Scene(root));
        stage.show();
    }
    @FXML
    public void createProfessor() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("RegisterProfessorPage.fxml"));
        Parent root = fxmlLoader.load();
        Stage stage = new Stage();
        stage.setTitle("Registro de Professor");
        stage.setResizable(false);
        stage.setScene(new Scene(root));
        stage.show();
    }

    @FXML
    public void createDiscipline() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("CreateDisciplinePage.fxml"));
        Parent root = fxmlLoader.load();
        Stage stage = new Stage();
        CreateDisciplinePageController createDisciplinePageController = fxmlLoader.getController();
        createDisciplinePageController.setActiveUser(this.activeUser);
        stage.setTitle("Registro de Disciplina");
        stage.setResizable(false);
        stage.setScene(new Scene(root));
        stage.show();
    }

    @FXML
    public void enterDiscipline() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("EnterDisciplinePage.fxml"));
        Parent root = fxmlLoader.load();
        Stage stage = new Stage();
        EnterDisciplinePageController enterDisciplinePageController = fxmlLoader.getController();
        enterDisciplinePageController.setActiveUser(this.activeUser);
        stage.setTitle("Registro de Disciplina");
        stage.setResizable(false);
        stage.setScene(new Scene(root));
        stage.show();
    }

    @FXML
    public void getOutOfDiscipline() {
        int id_discipline = this.disciplineId.getCellData(this.tableIndex);
        try {
            UserDao.removeUserRegistration(this.activeUser.id_user(), id_discipline);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        updateTable();
        this.selectedDisciplineName.setText("...");
        this.selectedDisciplineDescription.setText("...");
        this.enterDisciplineButton.setDisable(true);
        this.removeRegistrationButton.setDisable(true);
    }

    @FXML
    public void getInDiscipline(){

    }
    @FXML
    public void updateTable(){
        String pattern = String.format("%s%s",this.searchBar.getText(),"%");
        assert this.activeUser != null;
        try {
            List<DisciplineTableDto> disciplines = UserDao.listDisciplinesUserIsIn(this.activeUser.id_user(), pattern);
            ObservableList<DisciplineTableDto> innitialData = FXCollections.observableList(disciplines);
            this.tableView.setItems(innitialData);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void initHomePage(User user){
        //init user info
        this.activeUser = user;
        this.userName.setText(activeUser.name());
        this.userEmail.setText(activeUser.email());
        this.userCPF.setText(activeUser.cpf());
        //change UI depending on user access level
        if(user.access_level().equals("professor")){
            this.createDisciplineButton.setVisible(true);
            this.removeRegistrationButton.setVisible(true);
        }else if(user.access_level().equals("ADM")){
            this.createProfessorButton.setVisible(true);
        }
        //init table view
        updateTable();
    }

    @FXML
    public void getDisciplineInfo(){
        this.tableIndex = this.tableView.getSelectionModel().getSelectedIndex();
        if(tableIndex <= -1)
            return;
        this.selectedDisciplineName.setText(this.disciplineName.getCellData(tableIndex));
        this.selectedDisciplineDescription.setText(this.disciplineDescription.getCellData(tableIndex));

        this.enterDisciplineButton.setDisable(false);
        this.removeRegistrationButton.setDisable(false);
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        assert this.tableView != null;
        this.disciplineId.setCellValueFactory(new PropertyValueFactory<>("id"));
        this.disciplineName.setCellValueFactory(new PropertyValueFactory<>("name"));
        this.disciplineProfessor.setCellValueFactory(new PropertyValueFactory<>("disciplineProfessor"));
        this.professorEmail.setCellValueFactory(new PropertyValueFactory<>("professorEmail"));
        this.disciplineDescription.setCellValueFactory(new PropertyValueFactory<>("disciplineDescription"));
        this.disciplineAccessCode.setCellValueFactory(new PropertyValueFactory<>("disciplineAccessCode"));
    }
}

