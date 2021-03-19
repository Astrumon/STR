package com.course_project.support.updater;

import com.course_project.controllers.ControllerTableCar;
import com.course_project.data_access.model.wagon.Wagon;
import com.course_project.data_access.model.warehouse.WarehouseSet;
import com.course_project.support.AlertGenerator;
import com.course_project.support.ParseId;
import com.course_project.support.manager.WarehouseManager;

import java.util.List;

public class WarehouseUpdater {

    private WarehouseManager warehouseManager;

    public WarehouseUpdater() {
        warehouseManager = new WarehouseManager();
    }



    public void updateCountWagons(String warehouseName, int count) {
        warehouseManager.updateCountWagons(warehouseName, count);
    }

    private int findEmptyPos(String warehouseName) {
        int pos = 0;
        for (WarehouseSet warehouseSet : warehouseManager.getWarehouseSets()) {
            if (warehouseSet.getIdWagon() == 0 && warehouseSet.getNameWarehouse().equals(warehouseName)) {
                pos = warehouseSet.getPosition();
                break;
            }
        }
        return pos;
    }

    public void deleteWagon(String nameWarehouse, List<String> employedWagonsFromList) {
        for (String nameWagon : employedWagonsFromList) {
            Wagon wagon = new Wagon();
            wagon.setIdWagon(ParseId.getLongId(nameWagon, ControllerTableCar.WAGON_PREFIX_NAME));
            wagon.setType(Wagon.PASSENGER_TYPE);
            if (warehouseManager.deleteWagonFromWarehouse(nameWarehouse, wagon)) {
                AlertGenerator.info("Вагон успішно видалено зі складу");
            } else {
                AlertGenerator.error("Виникла помилка при видалені вагону зі складу");
            }
        }

    }


    public void addWagon(String warehouseName, List<String> freeWagonsFromList) {
        for (String nameWagon : freeWagonsFromList) {
            Wagon wagon = new Wagon();
            wagon.setIdWagon(ParseId.getLongId(nameWagon, ControllerTableCar.WAGON_PREFIX_NAME));
            wagon.setType(Wagon.PASSENGER_TYPE);
            if (warehouseManager.addWagonToWarehouse(warehouseName, wagon, findEmptyPos(warehouseName))) {
                AlertGenerator.info("Вагон успішно додано на склад");
            } else {
                AlertGenerator.error("Виникла помилка при додаванні вагону на склад");
            }
        }

    }

    public boolean delete(String warehouseName) {
        return warehouseManager.deleteWarehouse(warehouseName);
    }

}
