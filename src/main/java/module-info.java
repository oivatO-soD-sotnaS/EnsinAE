module otaviosantos.ensinae {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    opens otaviosantos.ensinae to javafx.fxml;
    exports otaviosantos.ensinae;
    exports otaviosantos.ensinae.Controllers;
    opens otaviosantos.ensinae.Controllers to javafx.fxml;
}