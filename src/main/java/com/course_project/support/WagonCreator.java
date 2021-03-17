package com.course_project.support;

import com.course_project.data_access.model.wagon.TypePlace;
import com.course_project.data_access.model.wagon.Wagon;

import java.util.List;

public class WagonCreator {
    private WagonManager wagonManager;

    private TypePlace typePlace;

    private Long idWagon;

    private boolean status;

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public TypePlace getTypePlace() {
        return typePlace;
    }

    public void setTypePlace(TypePlace typePlace) {
        this.typePlace = typePlace;
    }

    public Long getIdWagon() {
        return idWagon;
    }

    public void setIdWagon(Long idWagon) {
        this.idWagon = idWagon;
    }

    public WagonCreator() {
        wagonManager = new WagonManager();
    }


    public void createWagon() {

        if (wagonManager.getWagons().size() == 0) {
            create(idWagon);
        }

        int count = 0;
        for (Wagon wagon : wagonManager.getWagons()) {
//                    if (idWagon.equals(wagon.getIdWagon())) {
//                        update(idWagon);
//                        break;
//                    }

            if (!idWagon.equals(wagon.getIdWagon())) {
                count++;
                if (count == wagonManager.getWagons().size()) {
                    create(idWagon);
                }
            }
        }
    }

    private void create(Long idWagon) {

        if (status) {
            if (wagonManager.createCargoWagon(idWagon)) {
                AlertGenerator.info("Грузовий вагон успішно створено");
            } else {
                AlertGenerator.error("Виникла помилка при створенні грузового вагону");
            }
        } else {

            if (wagonManager.createPassengerWagon(idWagon, typePlace)) {
                AlertGenerator.info("Пасажирський вагон успішно створено");
            } else {
                AlertGenerator.error("Виникла помилка при створенні пасажирського вагону");
            }
        }


    }
}
