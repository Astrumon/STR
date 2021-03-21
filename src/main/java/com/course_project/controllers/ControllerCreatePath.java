package com.course_project.controllers;

import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTimePicker;
import com.course_project.data_access.model.route.RouteSet;
import com.course_project.data_access.model.train.Train;
import com.course_project.support.AlertGenerator;
import com.course_project.support.Checker;
import com.course_project.support.creator.RouteCreator;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class ControllerCreatePath {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private JFXTimePicker timePicker1;

    @FXML
    private JFXDatePicker datePicker1;

    @FXML
    private JFXTimePicker timePicker2;

    @FXML
    private JFXDatePicker datePicker2;

    @FXML
    private TextField textFieldPoint1;

    @FXML
    private TextField textFieldPrice;

    @FXML
    private TextField textFieldPoint2;

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
        if (isCorrectNameFromTown() && isCorrectNameToTown()) {
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
        } else {
            AlertGenerator.error("Невірно заповнені поля");
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

    private boolean isCorrectNameFromTown() {
        return !Checker.checkEmptyValue(textFieldPoint1.getText())
                && Checker.checkStringValue(textFieldPoint1.getText());
    }

    private boolean isCorrectNameToTown() {
        return !Checker.checkEmptyValue(textFieldPoint2.getText())
                && Checker.checkStringValue(textFieldPoint2.getText());
    }

    @FXML
    void buttonSavePathAc(ActionEvent event) {
        if (counterClicker == 0) {
            firstTime();
        } else {
            nextTime();
        }

        routeCreator.create(routeCreator.getFillRoute(train.getName()));
        clearFields();
        choiceBoxNameTrain.setValue(null);

    }

    private void clearFields() {
        textFieldPoint1.clear();
        textFieldPoint2.clear();
        textFieldPrice.clear();
        timePicker1.getEditor().clear();
        timePicker2.getEditor().clear();
        datePicker1.getEditor().clear();
    }

    @FXML
    void initialize() {
        assert textFieldPoint1 != null : "fx:id=\"textFieldPint1\" was not injected: check your FXML file 'createPath.fxml'.";
        assert textFieldPoint2 != null : "fx:id=\"textFieldPint2\" was not injected: check your FXML file 'createPath.fxml'.";
        assert buttonSavePath != null : "fx:id=\"buttonSavePath\" was not injected: check your FXML file 'createPath.fxml'.";
        assert buttonNextPoint != null : "fx:id=\"buttonNextPoint\" was not injected: check your FXML file 'createPath.fxml'.";
        assert choiceBoxNameTrain != null : "fx:id=\"choiceBoxNameTrain\" was not injected: check your FXML file 'createPath.fxml'.";

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
        /*routeSet.setSendTime(timePicker1.getEditor().getText());
        previousValueOfToTime = timePicker2.getEditor().getText();
        routeSet.setArriveTime(timePicker2.getEditor().getText());
        timePicker2.getEditor().clear();
        timePicker1.getEditor().setText(previousValueOfToTime);*/
    }

    private void nextCreateTimeRoute() {
        /*routeSet.setSendTime(previousValueOfToTime);
        routeSet.setArriveTime(timePicker2.getEditor().getText());
        previousValueOfToTime = timePicker2.getEditor().getText();
        timePicker1.getEditor().setText(previousValueOfToTime);
        timePicker2.getEditor().clear();*/
    }

    private void firstCreateDate() {
        routeSet.setDate(datePicker1.getValue().toString());
        previouesValueOfDate = datePicker2.getValue().toString();
        LocalDate date = LocalDate.parse(previouesValueOfDate);
        datePicker1.setValue(date);
        datePicker2.setValue(null);

    }

    private void nextCreateDate() {
        routeSet.setDate(previouesValueOfDate);
        previouesValueOfDate = datePicker2.getValue().toString();
        LocalDate date = LocalDate.parse(previouesValueOfDate);
        datePicker1.setValue(date);
        datePicker2.setValue(null);
    }


}
