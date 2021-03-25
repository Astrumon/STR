package com.course_project.support.updater;

import com.course_project.data_access.model.wagon.TypePlace;
import com.course_project.data_access.model.wagon.Wagon;
import com.course_project.support.AlertGenerator;
import com.course_project.support.manager.TrainManager;
import com.course_project.support.manager.WagonManager;
import com.course_project.support.manager.WarehouseManager;
/**
 * Класс который реализует всю логику обновления(изменения, удаления) вагонов
 *
 */
public class WagonUpdater {

    private WagonManager wagonManager;

    public WagonUpdater() {
        wagonManager = new WagonManager();
    }


    public void updateWagon(Long idWagon, TypePlace typePlace) {
        for (Wagon wagon : wagonManager.getWagons()) {
            if (idWagon.equals(wagon.getIdWagon())) {
                update(idWagon, typePlace);
                break;
            }
        }
    }


    private void update(Long idWagon, TypePlace typePlace) {
        if (wagonManager.updateWagon(idWagon, typePlace)) {
            AlertGenerator.info("Інформація о вагоні оновлена");
        }
    }

    public void updateWagonsCountTrain(Long idWagon) {

        if (wagonManager.getWagon(idWagon).getTrainName() != null) {
            TrainManager trainManager = new TrainManager();
            String trainName = wagonManager.getWagon(idWagon).getTrainName();
            int count = trainManager.getTrain(trainName).getCountWagon();
            trainManager.updateCountWagons(trainName, --count);
        }
    }


    public void updateWagonsCountWarehouse(Long idWagon) {
        if (wagonManager.getWagon(idWagon).getNameWarehouse() != null) {
            WarehouseManager warehouseManager = new WarehouseManager();
            String warehouseName = wagonManager.getWagon(idWagon).getNameWarehouse();
            int countWarehouse = warehouseManager.getWarehouse(warehouseName).getCountWagons();
            warehouseManager.updateCountWagons(warehouseName, --countWarehouse);
        }
    }

    public TypePlace getTypePlace(Long idWagon) {
       return wagonManager.getTypePlaceDao().findByIdWagon(idWagon);
    }

    public boolean delete(Long idWagon) {
        return wagonManager.deleteWagon(idWagon);
    }
}
