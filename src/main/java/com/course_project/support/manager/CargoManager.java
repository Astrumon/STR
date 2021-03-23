package com.course_project.support.manager;

import com.course_project.data_access.dao.impl.CargoDaoImpl;
import com.course_project.data_access.model.Cargo;

import java.util.List;

public class CargoManager extends Manager {
    public static Cargo transfer;
    private CargoDaoImpl cargoDao;
    private Cargo cargo;

    public CargoManager() {
        cargoDao = new CargoDaoImpl(dataSource);
        cargo = new Cargo();
    }

    public boolean create(Cargo cargo) {
        return cargoDao.insert(cargo);
    }

    public boolean delete(Cargo cargo) {
        return cargoDao.delete(cargo);
    }

    public boolean update(Cargo cargo) {
        return cargoDao.update(cargo);
    }

    public List<Cargo> getCargos() {
        return cargoDao.findByAll();
    }

    public Cargo getCargo(Long id) {
        return cargoDao.findById(id);
    }
}
