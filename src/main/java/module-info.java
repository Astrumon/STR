module com.course_project {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;

    requires sqlite.jdbc;
    requires java.sql;
    requires commons.validator;


    opens com.course_project to javafx.fxml;
    opens com.course_project.controllers  to javafx.fxml;
    opens com.course_project.data_access.model.train to javafx.base;
    opens com.course_project.data_access.model.warehouse to javafx.base;
    opens com.course_project.data_access.model.wagon to javafx.base;
    opens com.course_project.data_access.model.route to javafx.base;
    opens com.course_project.data_access.model to javafx.base;




   exports com.course_project.controllers to javafx.fxml;

    exports com.course_project;
}
