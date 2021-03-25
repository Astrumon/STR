package com.course_project.support.manager;

import com.course_project.data_access.dao.impl.wagon_dao_impl.WagonDaoImpl;
import com.course_project.data_access.dao.impl.warehouse_dao_impl.WarehouseDaoImpl;
import com.course_project.data_access.dao.impl.warehouse_dao_impl.WarehouseSetDaoImpl;
import com.course_project.data_access.model.wagon.Wagon;
import com.course_project.data_access.model.warehouse.Warehouse;
import com.course_project.data_access.model.warehouse.WarehouseSet;

import java.util.List;

/**
 * Класс который взаимодействует с DAO складов и их позиций
 */
public class WarehouseManager extends Manager {

    public static final int WAREHOUSE_CAPACITY = 100;

    private WarehouseDaoImpl warehouseDao;

    private WarehouseSetDaoImpl warehouseSetDao;

    private Warehouse warehouse;

    public static Warehouse transfer;

    private List<Warehouse> warehouses;

    public WarehouseManager() {
        warehouseDao = new WarehouseDaoImpl(dataSource);
        warehouseSetDao = new WarehouseSetDaoImpl(dataSource);
        warehouse = new Warehouse();
        warehouse.setCapacity(WAREHOUSE_CAPACITY);
    }

    public Warehouse getWarehouse(String warehouseName) {
        return warehouseDao.findByName(warehouseName);
    }

    public void setWarehouse(Warehouse warehouse) {
        this.warehouse = warehouse;
    }

    public Warehouse transferWarehouse(Warehouse warehouse) {
        return warehouse;
    }

    public List<Warehouse> getWarehouses() {
        return warehouseDao.findAll();
    }

    public boolean deleteWarehouse(String nameWarehouse) {
        if (nameWarehouse == null) {
            return false;
        }
        warehouse.setName(nameWarehouse);
       return warehouseDao.deleteByName(warehouse);
    }

    public List<WarehouseSet> getWarehouseSets() {
        return warehouseSetDao.findAll();
    }

    public boolean createWarehouse(String nameWarehouse) {
        warehouse.setName(nameWarehouse);
        boolean result =  warehouseDao.insert(warehouse);
        createWarehousePositions(warehouse);
        return result;
    }

    public boolean deleteWagonFromWarehouse(String nameWarehouse, Wagon wagon) {
        Wagon wagonWithoutWarehouse = updateWagonInfoAboutWarehouseSet(wagon);
       return updateWarehouseSetInfoAboutWagon(nameWarehouse, wagonWithoutWarehouse);


    }
    private Wagon updateWagonInfoAboutWarehouseSet( Wagon wagon) {
        WagonDaoImpl wagonDao = new WagonDaoImpl(dataSource);

        WarehouseSet warehouseSet = new WarehouseSet();
        Wagon wagonWithoutWarehouse = wagonDao.findByIdWagon(wagon.getIdWagon());
        warehouseSet.setIdWarehouse(null);
        warehouseSet.setIdWagon(wagonWithoutWarehouse.getIdWagon());
        warehouseSet.setNameWarehouse(null);
        warehouseSet.setId(null);
        wagonDao.updateWarehouseSet(warehouseSet, wagonWithoutWarehouse.getIdWarehouseSet());

        return wagonWithoutWarehouse;
    }

    private boolean updateWarehouseSetInfoAboutWagon(String nameWarehouse, Wagon wagon) {
        WarehouseSet warehouseSet = warehouseSetDao.findByName(nameWarehouse);
        warehouseSet.setIdWagon(null);
        warehouseSet.setId(wagon.getIdWarehouseSet());
       return warehouseSetDao.updateWagon(warehouseSet);
    }


    public boolean addWagonToWarehouse(String nameWarehouse, Wagon wagon, int position) {
        boolean result;
        String warehouseNameOfWagon = wagon.getNameWarehouse();
        wagon.setNameWarehouse(nameWarehouse);
        WarehouseSet warehouseSet = getFilledWarehouseSet(wagon, position);

        if (isEmptyWarehouseName(warehouseNameOfWagon) && warehouseSet.getIdWarehouse() != null) {
            result = warehouseSetDao.addWagon(nameWarehouse, wagon, position);

            updateWagonWarehouseSetInfo(warehouseSet);
            counterWagons(nameWarehouse);
            return result;
        } else  {
            return false;
        }
    }

    public WarehouseSet getFilledWarehouseSet(Wagon wagon, int position) {

        WarehouseSet wSet = new WarehouseSet();
        for (WarehouseSet warehouseSet : warehouseSetDao.findAll()) {

            if (warehouseSet.getNameWarehouse().equals(wagon.getNameWarehouse()) && samePosition(warehouseSet, position) && warehouseSet.getIdWagon() == 0) {
                wSet = warehouseSet;
                wSet.setIdWagon(wagon.getIdWagon());
                wSet.setPosition(position);
                break;
            }
        }

        return wSet;
    }

    private boolean samePosition(WarehouseSet warehouseSet, int position) {
        return warehouseSet.getPosition() == position;
    }

    private boolean isEmptyWarehouseName(String nameWarehouse) {
        return nameWarehouse == null;
    }

    private void updateWagonWarehouseSetInfo(WarehouseSet warehouseSet) {
        WagonDaoImpl wagonDao = new WagonDaoImpl(dataSource);
        wagonDao.updateWarehouseSet(warehouseSet, warehouseSet.getId());
    }




    private void counterWagons(String warehouseName) {
        int count = 0;

        for (WarehouseSet warehouseSet : warehouseSetDao.findAll()) {
            if (warehouseSet.getIdWagon() != 0 && warehouseSet.getNameWarehouse().equals(warehouseName)) {
                count++;
            }
        }

        updateCountWagons(warehouseName, count);
    }

    public void updateCountWagons(String name, int count) {
        Warehouse warehouse = warehouseDao.findByName(name);
        warehouse.setCountWagons(count);
        warehouseDao.updateCountWagon(warehouse);
    }


    private void createWarehousePositions(Warehouse warehouse) {
        WarehouseSetDaoImpl warehouseSetDao = new WarehouseSetDaoImpl(dataSource);
        for (int i = 1; i <= warehouse.getCapacity(); i++) {
            warehouseSetDao.insert(new WarehouseSet(warehouse.getName(), i, warehouse.getId()));
        }
    }





}
