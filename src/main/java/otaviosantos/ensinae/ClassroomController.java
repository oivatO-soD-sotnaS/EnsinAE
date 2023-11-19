package otaviosantos.ensinae;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;

public class ClassroomController implements Initializable {

    //List views
    @FXML
    private ListView<String> nameListView;
    @FXML
    private ListView<String> surnameListView;
    @FXML
    private ListView<String> cpfListView;
    @FXML
    private ListView<String> emailListView;
    @FXML
    private ListView<String> incomingRequestsListView;

    //Other elements
    @FXML
    private Label studantLabel;
    @FXML
    private Label subjectLabel;
    @FXML
    private AnchorPane admPanel;
    private String currentStudant;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        String[] names = {"Otávio","Vitor","Davyd","Felipe","Guilherme","Julia"};
        String[] surnames = {"Santos","nao sei","Viana","Sturm","Pazinato","Silva"};
        String[] cpfs = {"09090107908","12345678900","12345678900","12345678900","12345678900","12345678900"};
        String[] emails = {"otaviocheroso@gmail.com","vitor@gmail.com","davyd@gmail.com","felipe@gmail.com","guilherme@gmail.com","julia@gmail.com"};
        String[] requests = {"João","Mirian","Isabela"};
        nameListView.getItems().addAll(names);
        surnameListView.getItems().addAll(surnames);
        cpfListView.getItems().addAll(cpfs);
        emailListView.getItems().addAll(emails);
        incomingRequestsListView.getItems().addAll(requests);
    }
    public void aceptStudant(){
//        String[] names2 = {"Otávio","Vitor","Davyd","Felipe","Guilherme","Julia","Mayara"};
//        nameListView.getItems().removeAll(nameListView.getItems());
//        nameListView.getItems().addAll(names2);
    }
    public void removeStudant(){

    }
}
