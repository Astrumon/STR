package com.course_project.support.manager;

import com.course_project.data_access.dao.impl.wagon_dao_impl.WagonDaoImpl;
import com.course_project.data_access.dao.impl.warehouse_dao_impl.WarehouseDaoImpl;
import com.course_project.data_access.dao.impl.warehouse_dao_impl.WarehouseSetDaoImpl;
import com.course_project.data_access.model.wagon.Wagon;
import com.course_project.data_access.model.warehouse.Warehouse;
import com.course_project.data_access.model.warehouse.WarehouseSet;

import java.util.List;

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
        return warehouseDao.insert(warehouse);
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
        return warehouseSetDao.addWagon(nameWarehouse, wagon, position);
    }


    public void updateCountWagons(String name, int count) {
        Warehouse warehouse = warehouseDao.findByName(name);

        System.out.println(name + "   " + count);
        warehouse.setCountWagons(count);
        warehouseDao.updateCountWagon(warehouse);
    }
}
