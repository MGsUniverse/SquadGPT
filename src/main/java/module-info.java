module com.mgsuniverse.gptxi {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires com.almasb.fxgl.all;
    requires json;

    opens com.mgsuniverse.SquadGPT to javafx.fxml;
    exports com.mgsuniverse.SquadGPT;
}