package com.course_project.controllers;


import com.course_project.FxmlLoader;
import com.course_project.data_access.model.route.RouteSet;
import com.course_project.data_access.model.train.Train;
import com.course_project.support.AlertGenerator;
import com.course_project.support.Checker;
import com.course_project.support.creator.RouteCreator;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class ControllerCreatePath {

    @FXML
    DatePicker datePicker2;
    @FXML
    private ResourceBundle resources;
    @FXML
    private URL location;
    @FXML
    private DatePicker datePicker1;
    @FXML
    private StackPane stackPanePathCreate;

    @FXML
    private AnchorPane anchorPaneCreatePath;


    @FXML
    private TextField textFieldPoint1;

    @FXML
    private TextField textFieldPrice;

    @FXML
    private TextField textFieldPoint2;

    @FXML
    private TextField textFieldTime1;

    @FXML
    private TextField textFieldTime2;

    @FXML
    private Button buttonSavePath;

    @FXML
    private Button buttonNextPoint;

    @FXML
    private ChoiceBox<String> choiceBoxNameTrain;

    private RouteCreator routeCreator;
    private RouteSet routeSet;
    private Train train;

    private String previousValueOfToTown = "";
    private String previousValueOfToTime = "";
    private String previouesValueOfDate = "";
    private int counterClicker = 0;


    @FXML
    void buttonNextPointAc(ActionEvent event) {
        if (checkFields()) {
            if (counterClicker == 0) {
                if (!isSameTown() && !isEmptyTrainName()) {
                    firstTime();
                    ++counterClicker;
                } else {
                    AlertGenerator.error("Назви точок відправлень не повинні бути однаковими ");
                }
            } else if (counterClicker >= 1) {
                if (!isSameTown() && !isEmptyTrainName()) {
                    nextTime();
                    ++counterClicker;
                } else {
                    AlertGenerator.error("Назви точок відправлень не повинні бути однаковими ");
                }
            }
        }

        if (choiceBoxNameTrain.getItems().size() != 1) {
            choiceBoxNameTrain.getItems().setAll(routeCreator.getTrainWithoutRoute());
        }
    }

    private void firstTime() {
        int price;
        firstCreateTownRoute();
        firstCreateTimeRoute();
        firstCreateDate();
        routeCreator.setIdRouteToTrain(train.getName());
        price = Integer.parseInt(textFieldPrice.getText());
        routeCreator.setRouteSet(routeSet);
        routeCreator.create(routeCreator.getFillRouteSet(train.getName(), price));
    }

    private void nextTime() {
        int price;
        nextCreateTownRoute();
        nextCreateTimeRoute();
        nextCreateDate();
        price = Integer.parseInt(textFieldPrice.getText());
        routeCreator.setRouteSet(routeSet);
        routeCreator.create(routeCreator.getFillRouteSet(train.getName(), price));
    }


    private boolean isSameTown() {
        return textFieldPoint1.getText().equals(textFieldPoint2.getText());
    }

    private boolean isEmptyTrainName() {
        return train.getName() == null;
    }


    @FXML
    void buttonSavePathAc(ActionEvent event) {
            if (checkFields()) {
                if (counterClicker == 0) {
                    firstTime();
                } else if (!textFieldTime2.getText().isEmpty() && !textFieldPoint2.getText().isEmpty() && !datePicker2.getEditor().getText().isEmpty()) {
                    nextTime();
                }

                routeCreator.create(routeCreator.getFillRoute(train.getName()));
                clearFields();
                choiceBoxNameTrain.setValue(null);
                getBackScene();
            }
    }

    private void getBackScene() {
        FxmlLoader object = new FxmlLoader();
        Pane view = object.getPage("tablePath");
        stackPanePathCreate.getChildren().remove(anchorPaneCreatePath);
        stackPanePathCreate.getChildren().add(view);

    }

    private void clearFields() {
        textFieldPoint1.clear();
        textFieldPoint2.clear();
        textFieldPrice.clear();
        textFieldTime2.setText("00:00");
        textFieldTime1.setText("00:00");
        datePicker1.getEditor().clear();
        datePicker2.getEditor().clear();
    }

    @FXML
    void initialize() {
        assert textFieldPoint1 != null : "fx:id=\"textFieldPint1\" was not injected: check your FXML file 'createPath.fxml'.";
        assert textFieldPoint2 != null : "fx:id=\"textFieldPint2\" was not injected: check your FXML file 'createPath.fxml'.";
        assert buttonSavePath != null : "fx:id=\"buttonSavePath\" was not injected: check your FXML file 'createPath.fxml'.";
        assert buttonNextPoint != null : "fx:id=\"buttonNextPoint\" was not injected: check your FXML file 'createPath.fxml'.";
        assert choiceBoxNameTrain != null : "fx:id=\"choiceBoxNameTrain\" was not injected: check your FXML file 'createPath.fxml'.";
        assert datePicker1 != null : "fx:id=\"datePicker1\" was not injected: check your FXML file 'createPath.fxml'.";

        routeCreator = new RouteCreator();
        routeCreator.generateIdRoute();

        train = new Train();
        routeSet = new RouteSet();
        choiceBoxInit();
        getInfoFromBox();


    }


    private void choiceBoxInit() {
        choiceBoxNameTrain.getItems().addAll(routeCreator.getTrainWithoutRoute());
    }

    private void setTrainFromBox(String name) {

        clearFields();

        counterClicker = 0;
        ++RouteCreator.idRoute;
        train = routeCreator.getTrainManager().getTrain(name);

    }

    private void getInfoFromBox() {
        choiceBoxNameTrain.valueProperty().addListener((obc, oldItem, newItem) -> {
            setTrainFromBox(newItem);
        });
    }

    private void firstCreateTownRoute() {
            routeSet.setFromTown(textFieldPoint1.getText());
            previousValueOfToTown = textFieldPoint2.getText();
            routeSet.setToTown(textFieldPoint2.getText());
            textFieldPoint2.clear();
            textFieldPoint1.setText(previousValueOfToTown);
    }

    private void nextCreateTownRoute() {
            routeSet.setFromTown(previousValueOfToTown);
            routeSet.setToTown(textFieldPoint2.getText());
            previousValueOfToTown = textFieldPoint2.getText();
            textFieldPoint1.setText(previousValueOfToTown);
            textFieldPoint2.clear();
    }

    private void firstCreateTimeRoute() {
            routeSet.setSendTime(textFieldTime1.getText());
            previousValueOfToTime = textFieldTime2.getText();
            routeSet.setArriveTime(textFieldTime2.getText());
            textFieldTime2.setText("00:00");
            textFieldTime1.setText(previousValueOfToTime);
    }

    private void nextCreateTimeRoute() {

            routeSet.setSendTime(previousValueOfToTime);
            routeSet.setArriveTime(textFieldTime2.getText());
            previousValueOfToTime = textFieldTime2.getText();
            textFieldTime1.setText(previousValueOfToTime);
            textFieldTime2.setText("00:00");
    }

    private void firstCreateDate() {

            routeSet.setDateSend(datePicker1.getValue().toString());
            previouesValueOfDate = datePicker2.getValue().toString();
            routeSet.setDateArrive(datePicker2.getValue().toString());
            LocalDate date = LocalDate.parse(previouesValueOfDate);
            datePicker1.setValue(date);
            datePicker2.setValue(null);

    }

    private void nextCreateDate() {
            routeSet.setDateSend(previouesValueOfDate);
            routeSet.setDateArrive(datePicker2.getValue().toString());
            previouesValueOfDate = datePicker2.getValue().toString();
            LocalDate date = LocalDate.parse(previouesValueOfDate);
            datePicker1.setValue(date);
            datePicker2.setValue(null);
    }

    public boolean checkFields() {

        return routeCreator.isValidPoint(textFieldPoint1.getText())
                && routeCreator.isValidPoint(textFieldPoint1.getText())
                && routeCreator.isValidPoint(textFieldPoint2.getText())
                && !datePicker1.getEditor().getText().isEmpty()
                && !datePicker2.getEditor().getText().isEmpty()
                && routeCreator.isValidTime(textFieldTime1.getText())
                && routeCreator.isValidTime(textFieldTime2.getText())
                && routeCreator.isValidPrice(textFieldPrice.getText());
    }


}
