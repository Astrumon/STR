package com.course_project.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import com.course_project.Admin;
import com.course_project.FxmlLoader;
import com.course_project.data_access.model.Operator;
import com.course_project.database.DataSource;
import com.course_project.support.manager.OperatorManager;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.layout.*;
import com.course_project.data_access.model.wagon.Wagon;

/**
 * Данный класс реализует логику контроллера графического интерфейса главного экрана программы
 * Содержит обработку нажатий кнопок: "Квитки", "Вантаж", "Увійти"
 * Каждая кнопка содержит переход на конкретную сцену
 */
public class ControllerBasisOperatorInterface {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private AnchorPane anchorPaneBasisOperatorInterface;

    @FXML
    private BorderPane mainPane;

    @FXML
    private GridPane mainTopGridPane;

    @FXML
    private Label lableUserName;

    @FXML
    private StackPane stackPane;

    @FXML
    private Button buttonExit;

    @FXML
    private Button buttonCreateStorage;

    @FXML
    private Button buttonCreateCar;

    @FXML
    private Button buttonCreateTrain;

    @FXML
    private Button buttonCreatePath;

    @FXML
    private Button buttonCreateTicket;

    @FXML
    private Button buttonCleanStorage;

    @FXML
    private Button buttonCleanCar;

    @FXML
    private Button buttonCleanTrain;

    @FXML
    private Button buttonCleanPath;

    @FXML
    private Button buttonCleanTicket;

    @FXML
    private Button buttonOperators;

    @FXML
    private Button buttonCreateOperator;

    @FXML
    private Button buttonCleanOperator;

    @FXML
    private AnchorPane anchorPaneInStackPane;

    @FXML
    private TableView<Wagon> tableCar;

    @FXML
    void buttonCargoTransportationAc(ActionEvent event) {
        FxmlLoader object = new FxmlLoader();
        Pane view = object.getPage("tableTrucking");
        mainPane.setCenter(view);

        ObservableList<Node> childs = stackPane.getChildren();
        Node requiredNode = childs.get(childs.indexOf(anchorPaneInStackPane));
        requiredNode.toFront();
    }

    @FXML
    void buttonOperatorsAc(ActionEvent event) {
        FxmlLoader object = new FxmlLoader();
        Pane view = object.getPage("tableOperator");
        mainPane.setCenter(view);

        ObservableList<Node> childs = stackPane.getChildren();
        Node requiredNode = childs.get(childs.indexOf(buttonCreateOperator));
        requiredNode.toFront();
    }

    @FXML
    void buttonExitAc(ActionEvent event) {
        FxmlLoader object = new FxmlLoader();
        Parent root = object.getPage("basisUserInterface");
        Scene scene = buttonExit.getScene();
        StackPane mainStackPane = (StackPane) scene.getRoot();
        mainStackPane.getChildren().remove(anchorPaneBasisOperatorInterface);
        mainStackPane.getChildren().add(root);
    }

    @FXML
    void buttonCar(ActionEvent event) {
        FxmlLoader object = new FxmlLoader();
        Pane view = object.getPage("tableCar");
        mainPane.setCenter(view);

        ObservableList<Node> childs = stackPane.getChildren();
        Node requiredNode = childs.get(childs.indexOf(buttonCreateCar));
        requiredNode.toFront();
    }

    @FXML
    void buttonPath(ActionEvent event) {
        FxmlLoader object = new FxmlLoader();
        Pane view = object.getPage("tablePath");
        mainPane.setCenter(view);

        ObservableList<Node> childs = stackPane.getChildren();
        Node requiredNode = childs.get(childs.indexOf(buttonCreatePath));
        requiredNode.toFront();
    }

    @FXML
    void buttonStorage(ActionEvent event) {
        FxmlLoader object = new FxmlLoader();
        Pane view = object.getPage("tableStorage");
        mainPane.setCenter(view);

        ObservableList<Node> childs = stackPane.getChildren();
        Node requiredNode = childs.get(childs.indexOf(buttonCreateStorage));
        requiredNode.toFront();
    }

    @FXML
    void buttonTicket(ActionEvent event) {
        FxmlLoader object = new FxmlLoader();
        Pane view = object.getPage("tableTicket");
        mainPane.setCenter(view);

        ObservableList<Node> childs = stackPane.getChildren();
        Node requiredNode = childs.get(childs.indexOf(anchorPaneInStackPane));
        requiredNode.toFront();
    }

    @FXML
    void buttonTrain(ActionEvent event) {
        FxmlLoader object = new FxmlLoader();
        Pane view = object.getPage("tableTrain");
        mainPane.setCenter(view);

        ObservableList<Node> childs = stackPane.getChildren();
        Node requiredNode = childs.get(childs.indexOf(buttonCreateTrain));
        requiredNode.toFront();

    }

    @FXML
    void buttonCleanCarAc(ActionEvent event) {
        FxmlLoader object = new FxmlLoader();
        Pane view = object.getPage("createCar");
        mainPane.setCenter(view);
    }

    @FXML
    void buttonCleanPathAc(ActionEvent event) {
        FxmlLoader object = new FxmlLoader();
        Pane view = object.getPage("createPath");
        mainPane.setCenter(view);
    }

    @FXML
    void buttonCleanStorageAc(ActionEvent event) {
        FxmlLoader object = new FxmlLoader();
        Pane view = object.getPage("createStorage");
        mainPane.setCenter(view);
    }

    @FXML
    void buttonCleanTicketAc(ActionEvent event) {

    }

    @FXML
    void buttonCleanTrainAc(ActionEvent event) {
        FxmlLoader object = new FxmlLoader();
        Pane view = object.getPage("createTrain");
        mainPane.setCenter(view);
    }

    @FXML
    void buttonCleanOperatorAc(ActionEvent event) {
        FxmlLoader object = new FxmlLoader();
        Pane view = object.getPage("createOperator");
        mainPane.setCenter(view);
    }

    @FXML
    void buttonCreateCarAc(ActionEvent event) {
        FxmlLoader object = new FxmlLoader();
        Pane view = object.getPage("createCar");
        mainPane.setCenter(view);

        ObservableList<Node> childs = stackPane.getChildren();
        Node requiredNode = childs.get(childs.indexOf(buttonCleanCar));
        requiredNode.toFront();

    }

    @FXML
    void buttonCreatePathAc(ActionEvent event) {
        FxmlLoader object = new FxmlLoader();
        Pane view = object.getPage("createPath");
        mainPane.setCenter(view);

        ObservableList<Node> childs = stackPane.getChildren();
        Node requiredNode = childs.get(childs.indexOf(buttonCleanPath));
        requiredNode.toFront();
    }

    @FXML
    void buttonCreateStorageAc(ActionEvent event) {
        FxmlLoader object = new FxmlLoader();
        Pane view = object.getPage("createStorage");
        mainPane.setCenter(view);

        ObservableList<Node> childs = stackPane.getChildren();
        Node requiredNode = childs.get(childs.indexOf(buttonCleanStorage));
        requiredNode.toFront();
    }

    @FXML
    void buttonCreateTicketAc(ActionEvent event) {

    }

    @FXML
    void buttonCreateTrainAc(ActionEvent event) {
        FxmlLoader object = new FxmlLoader();
        Pane view = object.getPage("createTrain");
        mainPane.setCenter(view);

        ObservableList<Node> childs = stackPane.getChildren();
        Node requiredNode = childs.get(childs.indexOf(buttonCleanTrain));
        requiredNode.toFront();
    }

    @FXML
    void buttonCreateOperatorAc(ActionEvent event) {
        FxmlLoader object = new FxmlLoader();
        Pane view = object.getPage("createOperator");
        mainPane.setCenter(view);

        ObservableList<Node> childs = stackPane.getChildren();
        Node requiredNode = childs.get(childs.indexOf(buttonCleanOperator));
        requiredNode.toFront();
    }

    @FXML
    void initialize() {
        assert mainPane != null : "fx:id=\"mainPane\" was not injected: check your FXML file 'basisOperatorInterface.fxml'.";
        assert mainTopGridPane != null : "fx:id=\"mainTopGridPane\" was not injected: check your FXML file 'basisOperatorInterface.fxml'.";
        DataSource dataSource = new DataSource();
        dataSource.setConfig();

        lableUserName.setText("Адміністратор");
        if (OperatorManager.login != null) {
            buttonOperators.setVisible(false);
            lableUserName.setText("Оператор: " + OperatorManager.login);
            OperatorManager.login = null;
        }  else if (Admin.status){
            buttonOperators.setVisible(true);
        }



    }
}
