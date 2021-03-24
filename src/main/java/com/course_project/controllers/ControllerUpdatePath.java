package com.course_project.controllers;


import com.course_project.data_access.model.route.Route;
import com.course_project.data_access.model.route.RouteSet;
import com.course_project.support.AlertGenerator;
import com.course_project.support.creator.RouteCreator;
import com.course_project.support.manager.RouteManager;
import com.course_project.support.updater.RouteUpdater;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;

public class ControllerUpdatePath {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;


    @FXML
    private TextField textFieldPoint1;

    @FXML
    private TextField textFieldPrice;

    @FXML
    private TextField textFieldPoint2;

    @FXML
    private Button buttonNextPoint;

    @FXML
    private Button buttonPreviousPoint;

    @FXML
    private Button buttonAddNewPoint;

    @FXML
    private Button buttonReviewPath;

    @FXML
    private ChoiceBox<String> choiceBoxNameTrain;

    @FXML
    private Button buttonSavePath;

    @FXML
    private Button buttonDeletePath;

    @FXML
    private DatePicker datePicker1;

    @FXML
    private DatePicker datePicker2;

    @FXML
    private TextField textFieldTime1;
    @FXML
    private TextField textFieldTime2;

    private int count;
    private int routeSetSize = 0;

    private Route route;
    private RouteUpdater routeUpdater;
    int countClickAddButton = 0;


    @FXML
    void buttonAddNewPointAc(ActionEvent event) {

        if (countClickAddButton == 0) {

            buttonSavePath.setVisible(false);
            buttonNextPoint.setDisable(true);
            buttonPreviousPoint.setDisable(true);
            textFieldPoint1.setText(textFieldPoint2.getText());
            datePicker1.setValue(datePicker2.getValue());
            textFieldTime1.setText(textFieldTime2.getText());

            textFieldPoint2.clear();
            datePicker2.getEditor().clear();
            textFieldTime2.setText("00:00");

            buttonAddNewPoint.setText("Зберегти нову точку");
            ++countClickAddButton;
        } else if (countClickAddButton == 1){
            buttonNextPoint.setDisable(false);
            buttonPreviousPoint.setDisable(false);
            buttonSavePath.setVisible(true);
            RouteCreator routeCreator = new RouteCreator();

            RouteSet routeSet = getFilledRouteSetForAddBtn();
            if (routeSet != null) {
                routeCreator.create(routeSet);
                routeUpdater.updateRouteSet(routeSet);

                countClickAddButton = 0;
                buttonAddNewPoint.setText("Додати нову точку");
            }
        }
    }

    private RouteSet getFilledRouteSetForAddBtn() {
        RouteSet routeSet = new RouteSet();

        if (routeUpdater.isValidPrice(textFieldPrice.getText())) {
            routeSet.setPrice(Integer.parseInt(textFieldPrice.getText()));
        } else {
            return null;
        }

        if (routeUpdater.isValidPoint(textFieldPoint1.getText())) {
            routeSet.setFromTown(textFieldPoint1.getText());
        } else {
            return null;
        }

        if (routeUpdater.isValidPoint(textFieldPoint2.getText())) {
            routeSet.setToTown(textFieldPoint2.getText());
        } else {
            return null;
        }


        if (routeUpdater.isValidDate(datePicker1.getValue().toString())) {
            routeSet.setDateSend(datePicker1.getValue().toString());
        } else {
            return null;
        }


        if (routeUpdater.isValidDate(datePicker2.getValue().toString())) {
            routeSet.setDateArrive(datePicker2.getValue().toString());
        } else {
            return null;
        }

        if (routeUpdater.isValidTime(textFieldTime1.getText())) {
            routeSet.setSendTime(textFieldTime1.getText());
        } else {
            return null;
        }

        if (routeUpdater.isValidTime(textFieldTime2.getText())) {
            routeSet.setArriveTime(textFieldTime2.getText());
        }else {
            return null;
        }

        routeSet.setTrainName(this.route.getTrainName());
        routeSet.setIdRoute(this.route.getIdRoute()+1);

        return routeSet;
    }

    @FXML
    void buttonReviewPathAc(ActionEvent event) {
        AlertGenerator.tableRoute(routeUpdater.getRouteManager().getRouteSetsByRouteId(route.getIdRoute()+1));
    }

    @FXML
    void buttonDeletePathAc(ActionEvent event) {
        routeUpdater.delete(route);
    }

    @FXML
    void buttonNextPointAc(ActionEvent event) {
        if (count != routeSetSize) {
            ++count;
            initData(count);
        }
        if (count == routeSetSize) {
            buttonAddNewPoint.setVisible(true);
        }
    }

    @FXML
    void buttonPreviousPointAc(ActionEvent event) {
        if (count != 0) {
            --count;
            initData(count);
        }

        if (count != routeSetSize) {
            buttonAddNewPoint.setVisible(false);
        }

    }

    @FXML
    void buttonSavePathAc(ActionEvent event) {
       RouteSet routeSet = getUpdatedRouteSet();

       if (routeSet != null) {
           routeUpdater.updateRouteSet(routeSet);
       }
    }


    private RouteSet getUpdatedRouteSet() {
       List<RouteSet> routeSets = routeUpdater.getRouteManager().getRouteSetsByRouteId(route.getIdRoute()+1);

        int index = count;
        if (routeUpdater.isValidPrice(textFieldPrice.getText())) {
            routeSets.get(index).setPrice(Integer.parseInt(textFieldPrice.getText()));
        }else {
            return null;
        }

        if (routeUpdater.isValidPoint(textFieldPoint1.getText())) {
            routeSets.get(index).setFromTown(textFieldPoint1.getText());
        }else {
            return null;
        }

        if (routeUpdater.isValidPoint(textFieldPoint2.getText())) {
            routeSets.get(index).setToTown(textFieldPoint2.getText());
        }else {
            return null;
        }

        if (routeUpdater.isValidDate(datePicker1.getValue().toString())) {
            routeSets.get(index).setDateSend(datePicker1.getValue().toString());
        }else {
            return null;
        }

        if (routeUpdater.isValidDate(datePicker2.getValue().toString())) {
            routeSets.get(index).setDateArrive(datePicker2.getValue().toString());
        }else {
            return null;
        }

        if (routeUpdater.isValidTime(textFieldTime1.getText())) {
            routeSets.get(index).setSendTime(textFieldTime1.getText());
        }else {
            return null;
        }

        if (routeUpdater.isValidTime(textFieldTime2.getText())) {
            routeSets.get(index).setArriveTime(textFieldTime2.getText());
        }else {
            return null;
        }


        return routeSets.get(index);
    }

    @FXML
    void initialize() {
        assert textFieldPoint1 != null : "fx:id=\"textFieldPint1\" was not injected: check your FXML file 'updatePath.fxml'.";
        assert textFieldPoint2 != null : "fx:id=\"textFieldPint2\" was not injected: check your FXML file 'updatePath.fxml'.";
        assert buttonNextPoint != null : "fx:id=\"buttonNextPoint\" was not injected: check your FXML file 'updatePath.fxml'.";
        assert buttonPreviousPoint != null : "fx:id=\"buttonPreviousPoint\" was not injected: check your FXML file 'updatePath.fxml'.";
        assert choiceBoxNameTrain != null : "fx:id=\"choiceBoxNameTrain\" was not injected: check your FXML file 'updatePath.fxml'.";
        assert buttonSavePath != null : "fx:id=\"buttonSavePath\" was not injected: check your FXML file 'updatePath.fxml'.";
        assert buttonDeletePath != null : "fx:id=\"buttonDeletePath\" was not injected: check your FXML file 'updatePath.fxml'.";


        count = 0;
        route = RouteManager.transfer;
        routeUpdater = new RouteUpdater();
        routeSetSize  = routeUpdater.getRouteManager().getRouteSetsByRouteId(route.getIdRoute()+1).size()-1;
        if (count != routeSetSize) {
            buttonAddNewPoint.setVisible(false);
        }


         initData(count);
    }

    private void initData(int count) {

        List<RouteSet> routeSets = routeUpdater.getRouteManager().getRouteSetsByRouteId(route.getIdRoute()+1);
        choiceBoxNameTrain.setValue(route.getTrainName());
        textFieldPoint1.setText(routeSets.get(count).getFromTown());
        textFieldPoint2.setText(routeSets.get(count).getToTown());
        textFieldPrice.setText(String.valueOf(routeSets.get(count).getPrice()));
        textFieldTime1.setText(routeSets.get(count).getSendTime());
        textFieldTime2.setText(routeSets.get(count).getArriveTime());
        LocalDate date1 = LocalDate.parse(routeSets.get(count).getDateSend());
        LocalDate date2 = LocalDate.parse(routeSets.get(count).getDateArrive());

        datePicker1.setValue(date1);
        datePicker2.setValue(date2);
    }


}
