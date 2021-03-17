package com.course_project.support;

import com.course_project.controllers.ControllerTableCar;
import com.course_project.data_access.model.wagon.Wagon;
import com.course_project.data_access.model.warehouse.Warehouse;
import com.course_project.data_access.model.warehouse.WarehouseSet;

import java.util.List;

public class WarehouseCreator {

    private WarehouseManager warehouseManager;

    private List<String> wagonsFromList;

    private String nameWarehouse;

    public String getNameWarehouse() {
        return nameWarehouse;
    }

    public void setNameWarehouse(String nameWarehouse) {
        this.nameWarehouse = nameWarehouse;
    }

    public List<String> getWagonsFromList() {
        return wagonsFromList;
    }

    public void setWagonsFromList(List<String> wagonsFromList) {
        this.wagonsFromList = wagonsFromList;
    }

    public WarehouseCreator() {
        warehouseManager = new WarehouseManager();
    }




    public void createWarehouse(String nameWarehouse) {

        if (warehouseManager.getWarehouses().size() == 0) {
            createFirstWarehouse(nameWarehouse);
        }

        int count = 0;
        for (Warehouse warehouse : warehouseManager.getWarehouses()) {
            if (!warehouse.getName().equals(nameWarehouse)) {
                count++;
                if (count == warehouseManager.getWarehouses().size()) {
                    if (warehouseManager.createWarehouse(nameWarehouse)) {
                        AlertGenerator.info("Склад успішно створено");
                    } else {
                        AlertGenerator.error("Виникла помилка при створені складу");
                    }
                }
            }
        }
    }

    private void createFirstWarehouse(String nameWarehouse) {
        if (warehouseManager.createWarehouse(nameWarehouse)) {
            AlertGenerator.info("Склад успішно створено");
        } else {
            AlertGenerator.error("Виникла помилка при створені складу");
        }
    }

    public void addWagon(String nameWarehouse) {

        for (String nameWagon : wagonsFromList) {

            Wagon wagon = new Wagon();
            wagon.setIdWagon(ParseId.getLongId(nameWagon, ControllerTableCar.WAGON_PREFIX_NAME));
            wagon.setType(Wagon.PASSENGER_TYPE);
            if (warehouseManager.addWagonToWarehouse(nameWarehouse, wagon, findEmptyPos())) {
                AlertGenerator.info("Вагон успішно додано на склад");
            } else {
                AlertGenerator.error("Виникла помилка при додаванні вагону на склад");
            }
        }
    }

    private int findEmptyPos() {

        int pos = 0;
        for (WarehouseSet warehouseSet : warehouseManager.getWarehouseSets()) {

            if (warehouseSet.getIdWagon() == 0 && warehouseSet.getNameWarehouse().equals(nameWarehouse)) {
                pos = warehouseSet.getPosition();
                break;

            }
        }

        return pos;
    }
}
