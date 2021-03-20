package com.course_project.controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;

public class ControllerTableTrucking {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private StackPane stackPaneTrucking;

    @FXML
    private AnchorPane anchorPaneTableTrucking;

    @FXML
    private TableView<?> tableTrucking;

    @FXML
    private TableColumn<?, ?> tblNumber;

    @FXML
    private TableColumn<?, ?> tblEmail;

    @FXML
    private TableColumn<?, ?> tblStartAndEndPoint;

    @FXML
    void initialize() {
        assert stackPaneTrucking != null : "fx:id=\"stackPaneTrucking\" was not injected: check your FXML file 'tableTrucking.fxml'.";
        assert anchorPaneTableTrucking != null : "fx:id=\"anchorPaneTableTrucking\" was not injected: check your FXML file 'tableTrucking.fxml'.";
        assert tableTrucking != null : "fx:id=\"tableTrucking\" was not injected: check your FXML file 'tableTrucking.fxml'.";
        assert tblNumber != null : "fx:id=\"tblNumber\" was not injected: check your FXML file 'tableTrucking.fxml'.";
        assert tblEmail != null : "fx:id=\"tblEmail\" was not injected: check your FXML file 'tableTrucking.fxml'.";
        assert tblStartAndEndPoint != null : "fx:id=\"tblStartAndEndPoint\" was not injected: check your FXML file 'tableTrucking.fxml'.";

    }
    //TODO переход на создание/редактирование
    /*FxmlLoader object = new FxmlLoader();
    Pane view = object.getPage("updateTrucking");

    stackPaneTrucking.getChildren().remove(anchorPaneTableTrucking);
    stackPaneTrucking.getChildren().add(view);*/
}
