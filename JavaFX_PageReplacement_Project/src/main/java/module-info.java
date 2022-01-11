module com.cio.kurumsal.JavaFX_PageReplacement_Project {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.cio.kurumsal.JavaFX_PageReplacement_Project to javafx.fxml;
    exports com.cio.kurumsal.JavaFX_PageReplacement_Project;

}
