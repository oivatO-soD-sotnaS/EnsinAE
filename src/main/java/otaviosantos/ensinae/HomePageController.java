package otaviosantos.ensinae;


import dao.UserDao;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import vo.Discipline;
import vo.User;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class HomePageController implements Initializable{

    private User activeUser;

    @FXML
    private TextField searchBar;
    @FXML
    private Button createDisciplineButton;

    @FXML
    private Button dropDisciplineButton;

    @FXML
    private Button createProfessorButton;
    @FXML
    private TableView<Discipline> disciplinesTableView;

    @FXML
    private TableColumn<Discipline, Integer> tableDisciplineID;

    @FXML
    private TableColumn<Discipline, String> tableDisciplineName;

    @FXML
    private TableColumn<Discipline, String> tableDisciplineProfessorName;

    @FXML
    private TableColumn<Discipline, String> tableDisciplineProfessorEmail;

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
    void changeUserInfo(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ChangeUserInfoPage.fxml"));
        Parent root = fxmlLoader.load();
        Stage stage = new Stage();
        stage.setTitle("Registro de Disciplina");
        stage.setResizable(false);
        stage.setScene(new Scene(root));
        stage.show();
    }
    @FXML
    void createProfessor(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("RegisterProfessorPage.fxml"));
        Parent root = fxmlLoader.load();
        Stage stage = new Stage();
        stage.setTitle("Registro de Disciplina");
        stage.setResizable(false);
        stage.setScene(new Scene(root));
        stage.show();
    }

    @FXML
    void createDiscipline(ActionEvent event) throws IOException {

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
    void searchDiscipline(KeyEvent event) {
        System.out.println(this.searchBar.getText());
    }


    void innitHomePage(User user){
        //innit user info
        this.activeUser = user;
        this.userName.setText(activeUser.name());
        this.userEmail.setText(activeUser.email());
        this.userCPF.setText(activeUser.cpf());

        //innit table of disciplines
//        try {
//            this.disciplinesTableView.setItems((ObservableList<Discipline>) UserDao.listDisciplines(this.activeUser));
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }

        //change UI depending on user access level
        if(user.access_level().equals("professor")){
            this.createDisciplineButton.setVisible(true);
            this.dropDisciplineButton.setVisible(true);
        }else if(user.access_level().equals("ADM")){
            this.createProfessorButton.setVisible(true);
        }
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //prepare table columns for adding data
//        this.tableDisciplineID.setCellValueFactory(new PropertyValueFactory<Discipline, Integer>("tableDisciplineID"));
//        this.tableDisciplineName.setCellValueFactory(new PropertyValueFactory<Discipline, String>("tableDisciplineName"));
//        this.tableDisciplineProfessorName.setCellValueFactory(new PropertyValueFactory<Discipline, String>("tableDisciplineProfessorName"));
//        this.tableDisciplineProfessorEmail.setCellValueFactory(new PropertyValueFactory<Discipline, String>("tableDisciplineProfessorEmail"));
    }
}

