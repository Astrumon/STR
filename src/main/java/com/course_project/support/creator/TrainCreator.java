package com.course_project.support.creator;

import com.course_project.controllers.ControllerTableCar;
import com.course_project.data_access.model.train.Train;
import com.course_project.data_access.model.train.TrainSet;
import com.course_project.data_access.model.wagon.Wagon;
import com.course_project.support.AlertGenerator;
import com.course_project.support.ParseId;
import com.course_project.support.manager.TrainManager;
import com.course_project.support.manager.WagonManager;

import java.util.List;

public class TrainCreator {

    private int typeTrain;

    private TrainManager trainManager;

    private List<String> wagonsFromList;

    private String nameTrain;

    public String getNameTrain() {
        return nameTrain;
    }

    public TrainCreator() {
        trainManager = new TrainManager();
    }

    public void setNameTrain(String nameTrain) {
        this.nameTrain = nameTrain;
    }

    public int getTypeTrain() {
        return typeTrain;
    }

    public void setTypeTrain(int typeTrain) {
        this.typeTrain = typeTrain;
    }

    public TrainManager getTrainManager() {
        return trainManager;
    }

    public void setTrainManager(TrainManager trainManager) {
        this.trainManager = trainManager;
    }

    public List<String> getWagonsFromList() {
        return wagonsFromList;
    }

    public void setWagonsFromList(List<String> wagonsFromList) {
        this.wagonsFromList = wagonsFromList;
    }

    public void createTrain(String nameTrain) {
        if (trainManager.getTrains().size() == 0) {
            createFirstTrain(nameTrain, typeTrain);
        }

        int count = 0;
        for (Train train : trainManager.getTrains()) {
            if (!train.getName().equals(nameTrain)) {
                count++;
                if (count == trainManager.getTrains().size()) {
                    if (trainManager.createTrain(nameTrain, typeTrain)) {
                        AlertGenerator.info("Потяг успішно створено");
                    } else {
                        AlertGenerator.error("Виникла помилка при створені потягу");
                    }
                }
            }
        }
    }

    public void createFirstTrain(String nameTrain, int typeTrain) {
        System.out.println("TYPE: " + typeTrain );
        if (trainManager.createTrain(nameTrain, typeTrain)) {
            AlertGenerator.info("Потяг успішно створено");
        } else {
            AlertGenerator.error("Виникла помилка при створені потягу");
        }
    }

    public void addWagon(String nameTrain) {

        WagonManager wagonManager = new WagonManager();

        for (String nameWagon : wagonsFromList) {
            Long idWagon = ParseId.getLongId(nameWagon, ControllerTableCar.WAGON_PREFIX_NAME);

            Wagon wagon = new Wagon();
            wagon.setIdWagon(idWagon);
            wagon.setType(wagonManager.getWagon(idWagon).getType());

            if (trainManager.addWagonToTrain(nameTrain, wagon, findEmptyPos())) {
                AlertGenerator.info("Вагон успішно приєднано до потягу");
            } else {
                AlertGenerator.error("Виникла помилка при приєднанні вагону до потягу");
            }
        }
    }

    public int findEmptyPos() {
        int pos = 0;
        for (TrainSet trainSet : trainManager.getTrainSets()) {
            if (trainSet.getIdWagon() == 0 && trainSet.getName().equals(nameTrain)) {
                pos = trainSet.getPosWagon();
                break;
            }
        }

        return pos;
    }
}
