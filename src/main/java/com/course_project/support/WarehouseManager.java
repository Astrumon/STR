package com.course_project.support;

import com.course_project.data_access.dao.impl.wagon_dao_impl.WagonDaoImpl;
import com.course_project.data_access.dao.impl.warehouse_dao_impl.WarehouseDaoImpl;
import com.course_project.data_access.dao.impl.warehouse_dao_impl.WarehouseSetDaoImpl;
import com.course_project.data_access.dao.wagon_dao.WagonDao;
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

    public boolean deleteWagonFromWarehouse(String nameWarehouse, Wagon wagon) {

        WagonDaoImpl wagonDao = new WagonDaoImpl(dataSource);

        WarehouseSet warehouseSet = new WarehouseSet();
        Wagon wagon1 = wagonDao.findByIdWagon(wagon.getIdWagon());
        warehouseSet.setIdWarehouse(null);
        warehouseSet.setIdWagon(wagon1.getIdWagon());
        warehouseSet.setNameWarehouse(null);
        warehouseSet.setId(null);
        Long idWarehouseSet = wagon1.getIdWarehouseSet();
        wagonDao.updateWarehouseSet(warehouseSet, idWarehouseSet);
        System.out.println(wagon1);

        WarehouseSet warehouseSet1 = warehouseSetDao.findByName(nameWarehouse);
        System.out.println(warehouseSet1);
        warehouseSet1.setIdWagon(null);
        warehouseSet1.setId(wagon1.getIdWarehouseSet());
        warehouseSetDao.updateWagon(warehouseSet1);



        return true;


    }

    public boolean addWagonToWarehouse(String nameWarehouse, Wagon wagon, int position) {
        return warehouseSetDao.addWagon(nameWarehouse, wagon, position);
    }


    public void updateCountWagons(String name, int count) {
        Warehouse warehouse = warehouseDao.findByName(name);

       // int countWagons = warehouse.getCountWagons();
        //warehouse.setCountWagons(--countWagons);
        System.out.println(name + "   " + count);
        warehouse.setCountWagons(count);
        warehouseDao.updateCountWagon(warehouse);
    }
}
