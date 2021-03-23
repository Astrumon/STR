package com.course_project.support.updater;

import com.course_project.controllers.ControllerTableCar;
import com.course_project.data_access.model.train.Train;
import com.course_project.data_access.model.train.TrainSet;
import com.course_project.data_access.model.wagon.Wagon;
import com.course_project.support.AlertGenerator;
import com.course_project.support.ParseId;
import com.course_project.support.manager.TrainManager;
import com.course_project.support.manager.WagonManager;

import java.util.List;

public class TrainUpdater extends Updater{

    private TrainManager trainManager;

    public TrainManager getTrainManager() {
        return trainManager;
    }


    public TrainUpdater() {
        trainManager = new TrainManager();
        wagonManager = new WagonManager();
    }

    public void updateCountWagons(String trainName, int count) {

        trainManager.updateCountWagons(trainName, count);
    }

    public void deleteWagon(String nameWarehouse, List<String> employedWagonsFromList) {
        for (String nameWagon : employedWagonsFromList) {
            if (trainManager.deleteWagonFromTrain(nameWarehouse, getWagonWithIdAndType(nameWagon))) {
                AlertGenerator.info("Вагон успішно видалено з потягу");
            } else {
                AlertGenerator.error("Виникла помилка при видалені вагону з потягу");
            }
        }
    }

    public void addWagon(String trainName, List<String> freeWagonsFromList) {
        for (String nameWagon : freeWagonsFromList) {
            if (trainManager.addWagonToTrain(trainName, getWagonWithIdAndType(nameWagon), findEmptyPos(trainName))) {
                AlertGenerator.info("Вагон успішно додано до потяга");
            } else {
                AlertGenerator.error("Виникла помилка при додаванні вагону до потягу");
            }
        }
    }

    private int findEmptyPos(String trainName) {
        int pos = 0;
        for (TrainSet trainSet : trainManager.getTrainSets()) {
            if (trainSet.getIdWagon() == 0 && trainSet.getName().equals(trainName)) {
                pos = trainSet.getPosWagon();
                break;
            }
        }
        System.out.println("POS: " + pos);
        return pos;
    }

    public boolean delete(String trainName) {
        return trainManager.deleteTrain(trainName);
    }


}
