package com.course_project.controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;

public class ControllerTableOperator {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private StackPane stackPaneOperator;

    @FXML
    private AnchorPane anchorPaneTableOperator;

    @FXML
    private TableView<?> tableOperator;

    @FXML
    private TableColumn<?, ?> tblNumber;

    @FXML
    private TableColumn<?, ?> tblLogin;

    @FXML
    private TableColumn<?, ?> tblPassword;

    @FXML
    void initialize() {
        assert stackPaneOperator != null : "fx:id=\"stackPaneOperator\" was not injected: check your FXML file 'tableOperator.fxml'.";
        assert anchorPaneTableOperator != null : "fx:id=\"anchorPaneTableOperator\" was not injected: check your FXML file 'tableOperator.fxml'.";
        assert tableOperator != null : "fx:id=\"tableOperator\" was not injected: check your FXML file 'tableOperator.fxml'.";
        assert tblNumber != null : "fx:id=\"tblNumber\" was not injected: check your FXML file 'tableOperator.fxml'.";
        assert tblLogin != null : "fx:id=\"tblLogin\" was not injected: check your FXML file 'tableOperator.fxml'.";
        assert tblPassword != null : "fx:id=\"tblPassword\" was not injected: check your FXML file 'tableOperator.fxml'.";

    }

    //TODO переход на создание/редактирование
    /*FxmlLoader object = new FxmlLoader();
    Pane view = object.getPage("updateOperator");

    stackPaneOperator.getChildren().remove(anchorPaneTableOperator);
    stackPaneOperator.getChildren().add(view);*/
}
