module com.br.vinydev.task {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.base;
    requires javafx.graphics;
    requires java.xml;
    requires org.apache.poi.poi;
    requires org.apache.poi.ooxml;
    requires org.apache.logging.log4j;
    opens com.br.vinydev.task to javafx.fxml;
    exports com.br.vinydev.task;
}
