package otaviosantos.ensinae;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import org.w3c.dom.events.MouseEvent;

public class DisciplinePageController {
    @FXML
    private TableColumn<?, ?> cpf;

    @FXML
    private TableColumn<?, ?> email;

    @FXML
    private TableColumn<?, ?> name;

    @FXML
    private Button removeStudantButton;

    @FXML
    private TextField searchBar;

    @FXML
    private Label studantCPF;

    @FXML
    private Label studantEmail;

    @FXML
    private Label studantName;

    @FXML
    private TableView<?> studantsTableView;

    @FXML
    private TableColumn<?, ?> surname;

    @FXML
    void getStudantInfo(MouseEvent event) {

    }

    @FXML
    void removeStudant(ActionEvent event) {

    }

    @FXML
    void switchToHomePage(ActionEvent event) {

    }

    @FXML
    void updateTable(ActionEvent event) {

    }

    public void getStudantInfo(javafx.scene.input.MouseEvent mouseEvent) {
    }
}
