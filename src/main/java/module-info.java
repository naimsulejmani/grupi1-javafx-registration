module dev.naimsulejmani.grupi1javafxregistration {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.microsoft.sqlserver.jdbc;
    requires static lombok;
    requires java.sql;


    opens dev.naimsulejmani.grupi1javafxregistration.models;
    opens dev.naimsulejmani.grupi1javafxregistration to javafx.fxml;
    exports dev.naimsulejmani.grupi1javafxregistration;
}