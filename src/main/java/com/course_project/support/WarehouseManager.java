package com.course_project.support;

import com.course_project.data_access.dao.impl.warehouse_dao_impl.WarehouseDaoImpl;
import com.course_project.data_access.dao.impl.warehouse_dao_impl.WarehouseSetDaoImpl;
import com.course_project.data_access.model.wagon.Wagon;
import com.course_project.data_access.model.warehouse.Warehouse;
import com.course_project.data_access.model.warehouse.WarehouseSet;

import java.util.List;

public class WarehouseManager extends Manager implements UpdatableCountWagons{

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

    public Warehouse getWarehouse() {
        return warehouse;
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

    public boolean addWagonToWarehouse(String nameWarehouse, Wagon wagon, int position) {
        return warehouseSetDao.addWagon(nameWarehouse, wagon, position);
    }


    @Override
    public void updateCountWagons(String name) {
        Warehouse warehouse = warehouseDao.findByName(name);

        int countWagons = warehouse.getCountWagons();
        warehouse.setCountWagons(--countWagons);
        warehouseDao.updateCountWagon(warehouse);
    }
}
