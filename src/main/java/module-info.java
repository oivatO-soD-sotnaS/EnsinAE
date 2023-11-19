module otaviosantos.ensinae {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    opens otaviosantos.ensinae to javafx.fxml;
    exports otaviosantos.ensinae;
}