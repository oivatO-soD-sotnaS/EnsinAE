module otaviosantos.ensinae {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires java.datatransfer;

    opens otaviosantos.ensinae to javafx.fxml;
    exports otaviosantos.ensinae;
    exports models;
    opens models to javafx.fxml;
    exports DTO;
    opens DTO to javafx.fxml;
}