package com.course_project.support.updater;

import com.course_project.controllers.ControllerTableCar;
import com.course_project.data_access.model.train.TrainSet;
import com.course_project.data_access.model.wagon.Wagon;
import com.course_project.support.ParseId;
import com.course_project.support.manager.Manager;
import com.course_project.support.manager.TrainManager;
import com.course_project.support.manager.WagonManager;

public class Updater {



    protected WagonManager wagonManager;

    public Updater() {
        wagonManager = new WagonManager();
    }

    protected Wagon getWagonWithIdAndType(String wagonName) {
        Wagon wagon = new Wagon();
        wagon.setIdWagon(ParseId.getLongId(wagonName, ControllerTableCar.WAGON_PREFIX_NAME));
        wagon.setType(wagonManager.getWagon(wagon.getIdWagon()).getType());

        return wagon;
    }


    public WagonManager getWagonManager() {
        return wagonManager;
    }
}
