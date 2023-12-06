package otaviosantos.ensinae;


import dao.DisciplineDAO;
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
import dto.UserDisciplinesDTO;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

public class HomePageController implements Initializable{

    private User activeUser;
    private Integer tableCurrentIndex;


    @FXML
    private Hyperlink atualizeUsersLink;

    @FXML
    private Button createDisciplineButton;

    @FXML
    private Button createProfessorButton;

    @FXML
    private TableView<UserDisciplinesDTO> tableView;

    @FXML
    private TableColumn<UserDisciplinesDTO, Integer> disciplineId;

    @FXML
    private TableColumn<UserDisciplinesDTO, String> disciplineName;

    @FXML
    private TableColumn<UserDisciplinesDTO, String> disciplineProfessor;


    @FXML
    private TableColumn<UserDisciplinesDTO, String> professorEmail;

    @FXML
    private TableColumn<UserDisciplinesDTO, String> disciplineDescription;

    @FXML
    private TableColumn<UserDisciplinesDTO, String> disciplineAccessCode;

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
    public void showChangeUserInfoPage() throws IOException {
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
    public void showAtualizeUsersPage() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("AtualizeUsersPage.fxml"));
        Parent root = fxmlLoader.load();
        Stage stage = new Stage();
        stage.setTitle("Autorização de novos Usuários");
        stage.setResizable(false);
        stage.setScene(new Scene(root));
        stage.show();
    }

    @FXML
    public void showCreateProfessorPage() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("RegisterProfessorPage.fxml"));
        Parent root = fxmlLoader.load();
        Stage stage = new Stage();
        stage.setTitle("Registro de Professor");
        stage.setResizable(false);
        stage.setScene(new Scene(root));
        stage.show();
    }

    @FXML
    public void showCreateDisciplinePage() throws IOException {
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
    public void showEnterDisciplinePage() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("EnterDisciplinePage.fxml"));
        Parent root = fxmlLoader.load();
        Stage stage = new Stage();
        EnterDisciplinePageController enterDisciplinePageController = fxmlLoader.getController();
        enterDisciplinePageController.setActiveUser(this.activeUser);
        stage.setTitle("Cadastro em Disciplina");
        stage.setResizable(false);
        stage.setScene(new Scene(root));
        stage.show();
    }

    @FXML
    public void showDisciplinePage() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("DisciplinePage.fxml"));
        Parent root = fxmlLoader.load();
        Stage stage = new Stage();
        DisciplinePageController disciplinePageController = fxmlLoader.getController();
        disciplinePageController.initDisciplinePage(
                this.disciplineId.getCellData(this.tableCurrentIndex),
                this.professorEmail.getCellData(this.tableCurrentIndex),
                this.userEmail.getText());
        stage.setTitle(this.disciplineName.getCellData(this.tableCurrentIndex));
        stage.setResizable(false);
        stage.setScene(new Scene(root));
        stage.show();
    }

    @FXML
    public void removeRegistration() {
        int id_discipline = this.disciplineId.getCellData(this.tableCurrentIndex);
        try {
            DisciplineDAO.removeUserRegistration(this.activeUser.id_user(), id_discipline);
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
    public void getDisciplineInfo(){
        this.tableCurrentIndex = this.tableView.getSelectionModel().getSelectedIndex();
        if(tableCurrentIndex <= -1)
            return;
        this.selectedDisciplineName.setText(this.disciplineName.getCellData(tableCurrentIndex));
        this.selectedDisciplineDescription.setText(this.disciplineDescription.getCellData(tableCurrentIndex));

        this.enterDisciplineButton.setDisable(false);
        this.removeRegistrationButton.setDisable(false);
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
            this.atualizeUsersLink.setVisible(true);
        }
        //init table view
        updateTable();
    }

    @FXML
    public void updateTable(){
        String pattern = String.format("%s%s",this.searchBar.getText(),"%");
        assert this.activeUser != null;
        try {
            List<UserDisciplinesDTO> disciplines = DisciplineDAO.getUserDisciplines(this.activeUser.id_user(), pattern);
            ObservableList<UserDisciplinesDTO> innitialData = FXCollections.observableList(disciplines);
            this.tableView.setItems(innitialData);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        assert this.tableView != null;
        this.disciplineId.setCellValueFactory(new PropertyValueFactory<>("id_discipline"));
        this.disciplineName.setCellValueFactory(new PropertyValueFactory<>("name"));
        this.disciplineProfessor.setCellValueFactory(new PropertyValueFactory<>("disciplineProfessor"));
        this.professorEmail.setCellValueFactory(new PropertyValueFactory<>("professorEmail"));
        this.disciplineDescription.setCellValueFactory(new PropertyValueFactory<>("disciplineDescription"));
        this.disciplineAccessCode.setCellValueFactory(new PropertyValueFactory<>("disciplineAccessCode"));
    }
}

