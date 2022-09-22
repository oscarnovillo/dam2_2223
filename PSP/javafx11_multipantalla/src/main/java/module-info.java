module javafx11.multipantalla {


    requires javafx.graphics;
    requires javafx.controls;
    requires javafx.fxml;
    requires MaterialFX;

    requires lombok;
    requires org.apache.logging.log4j;

    requires jakarta.inject;
    requires jakarta.cdi;

    exports ui.main to javafx.graphics;
    exports ui.pantallas.principal;
    exports ui.pantallas.pantallaNueva;
    exports ui.pantallas.pantalla1;
    exports ui.pantallas.login;
    exports ui.pantallas.detalle;
    exports ui.pantallas.listado;
    exports common.config;
    exports ui.pantallas.common;
    exports common;
    exports dao.impl;
    exports domain.usecases;

//    opens ui.pantallas.principal to javafx.fxml;
    opens ui.pantallas.pantalla1 to javafx.fxml;
    opens ui.pantallas.pantallaNueva to javafx.fxml;
    opens ui.pantallas.listado to javafx.fxml;
    opens ui.pantallas.login to  javafx.fxml;
    opens ui.pantallas.detalle to  javafx.fxml;

    opens domain.modelo to javafx.base;
    opens ui.pantallas.principal;
    opens ui.main;
    opens config;
    opens css;
    opens fxml;

//    exports ui;
//    opens domain.modelo to javafx.base;
//    exports ui.pantallas.principal;
//
//    opens ui.pantallas.principal to javafx.fxml;

}
