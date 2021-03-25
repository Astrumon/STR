package com.course_project.controllers;

import com.course_project.FxmlLoader;
import com.course_project.data_access.model.Operator;
import com.course_project.data_access.model.route.Route;
import com.course_project.support.NumberIDGenerator;
import com.course_project.support.manager.OperatorManager;
import com.course_project.support.manager.TicketManager;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.util.Callback;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Данный класс реализует логику контроллера графического интерфейса экрана таблицы операторов
 * Создает таблицу в которой перечислены созданные записи операторов, данные предоставляет класс OperatorManager
 * Содержит обработку двойного нажатия на строку таблицы, передает нужные данные контроллеру ControllerUpdateOperator
 */
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
    private TableView<Operator> tableOperator;

    @FXML
    private TableColumn tblNumber;

    @FXML
    private TableColumn tblLogin;

    @FXML
    private TableColumn tblPassword;

    private ObservableList<Operator> operators;

    @FXML
    void initialize() {
        assert stackPaneOperator != null : "fx:id=\"stackPaneOperator\" was not injected: check your FXML file 'tableOperator.fxml'.";
        assert anchorPaneTableOperator != null : "fx:id=\"anchorPaneTableOperator\" was not injected: check your FXML file 'tableOperator.fxml'.";
        assert tableOperator != null : "fx:id=\"tableOperator\" was not injected: check your FXML file 'tableOperator.fxml'.";
        assert tblNumber != null : "fx:id=\"tblNumber\" was not injected: check your FXML file 'tableOperator.fxml'.";
        assert tblLogin != null : "fx:id=\"tblLogin\" was not injected: check your FXML file 'tableOperator.fxml'.";
        assert tblPassword != null : "fx:id=\"tblPassword\" was not injected: check your FXML file 'tableOperator.fxml'.";

        fillTable();
        clickToEdit();
    }

    public void fillTable() {
        OperatorManager operatorManager = new OperatorManager();

        operators = tableOperator.getItems();
        operators.addAll(operatorManager.getOperators());

        tblLogin.setCellValueFactory(new PropertyValueFactory<Route, String>("login"));

        tblPassword.setCellValueFactory(new PropertyValueFactory<Route, String>("password"));

        tblNumber.setCellValueFactory(new Callback<TableColumn.CellDataFeatures, ObservableValue>() {
            @Override
            public ObservableValue call(TableColumn.CellDataFeatures cellDataFeatures) {
                return new SimpleIntegerProperty(NumberIDGenerator.generate(operators, cellDataFeatures));
            }
        });
    }

    public void clickToEdit() {
        tableOperator.setRowFactory(tv -> {
            TableRow<Operator> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (!row.isEmpty())) {
                    OperatorManager.transfer = row.getItem();

                    FxmlLoader object = new FxmlLoader();
                    Pane view = object.getPage("updateOperator");

                    stackPaneOperator.getChildren().remove(anchorPaneTableOperator);
                    stackPaneOperator.getChildren().add(view);
                }
            });
            return row;
        });
    }
}
