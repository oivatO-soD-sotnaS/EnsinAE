package otaviosantos.ensinae;

import dao.UserDao;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import vo.Discipline;
import vo.User;

import java.sql.SQLException;

public class CreateDisciplinePageController {


    private User activeUser;

    @FXML
    private TextArea disciplineDescriptionTextArea;

    @FXML
    private TextField disciplineNameTextField;

    @FXML
    private Label nameError;

    @FXML
    private Label descriptionError;

    @FXML
    private TextField disciplineCodeTextArea;

    @FXML
    private Label codeError;

    private void generateError(Label label, String error){
        label.setText(error);
    }

    private void removeError(Label label){
        label.setText("");
    }
    private boolean checkName(){
        String name = this.disciplineNameTextField.getText();
        if(name.isEmpty()){
            generateError(this.nameError, "Nome não pode ser vazio");
            this.disciplineNameTextField.requestFocus();
            return false;
        }
        if(name.length() > 128){
            generateError(this.nameError, "Nome deve " +
                    "conter no máximo 128 caracteres");
            this.disciplineNameTextField.requestFocus();
            return false;
        }
        removeError(this.nameError);
        return true;
    }
    private boolean checkDescription(){
        String description = this.disciplineDescriptionTextArea.getText();
        if(description.isEmpty()){
            generateError(this.descriptionError, "Descrição não pode ser vazia.");
            this.disciplineDescriptionTextArea.requestFocus();
            return false;
        }
        if(description.length() > 512){
            generateError(this.descriptionError, "Descrição deve " +
                    "conter no máxsimo 512 caracteres.");
            this.disciplineDescriptionTextArea.requestFocus();
            return false;
        }
        removeError(this.descriptionError);
        return true;
    }

    private boolean checkAccessCode(){
        String accessCode = this.disciplineCodeTextArea.getText();
        if(accessCode.isEmpty()){
            generateError(this.codeError, "Código de acesso não pode" +
                    "ser vazio.");
            this.disciplineDescriptionTextArea.requestFocus();
            return false;
        }
        if(accessCode.length() > 8){
            generateError(this.codeError, "Código de acesso deve " +
                    "conter no máximo 8 caracteres");
            this.disciplineDescriptionTextArea.requestFocus();
            return false;
        }
        removeError(this.codeError);
        return true;
    }
    private Discipline createDiscipline(){
        return new Discipline(0,
                this.activeUser,
                this.disciplineNameTextField.getText(),
                this.disciplineDescriptionTextArea.getText().replaceAll("\n", System.getProperty("line.separator")),
                this.disciplineCodeTextArea.getText());
    }

    void setActiveUser(User user){
        this.activeUser = user;
    }
    @FXML
    void createDiscipline(ActionEvent event) {
        if(checkName() && checkDescription() && checkAccessCode()){
            try {
                UserDao.createDiscipline(createDiscipline());
            } catch (SQLException e) {
                System.out.println(e.getMessage());
                if(e.getMessage().equals(String.format("Duplicate entry '%s'" +
                        " for key 'discipline.name'"
                        , this.disciplineNameTextField.getText()))){
                    generateError(this.nameError, "Esta disciplina já esta cadastrada.");
                    this.disciplineNameTextField.requestFocus();
                }else{
                    generateError(this.codeError, "Este código de disciplina já esta sendo utilizado.");
                    this.disciplineCodeTextArea.requestFocus();
                }
                throw new RuntimeException(e);
            }
        }
    }

}
