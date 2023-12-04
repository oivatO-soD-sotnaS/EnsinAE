module otaviosantos.ensinae {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires java.datatransfer;

    opens otaviosantos.ensinae to javafx.fxml;
    exports otaviosantos.ensinae;
    exports dto;
    opens dto to javafx.fxml;
    exports models;
    opens models to javafx.fxml;
    exports util;
    opens util to javafx.fxml;
}