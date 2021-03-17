package com.course_project.support;


import com.course_project.data_access.dao.impl.wagon_dao_impl.TypePlaceDaoImpl;
import com.course_project.data_access.dao.impl.wagon_dao_impl.WagonDaoImpl;
import com.course_project.data_access.model.wagon.TypePlace;
import com.course_project.data_access.model.wagon.Wagon;

import java.util.List;

public class WagonManager extends Manager {

    private WagonDaoImpl wagonDao;

    private TypePlaceDaoImpl typePlaceDao;

    private Wagon wagon;

    private List<Wagon> wagons;

    public WagonManager() {
        wagonDao = new WagonDaoImpl(dataSource);
        typePlaceDao = new TypePlaceDaoImpl(dataSource);
        wagon = new Wagon();

    }

    public boolean deleteWagon(Long idWagon) {

        if (isWagonNull(idWagon)) {
            return false;
        }

        wagon.setIdWagon(idWagon);
        wagonDao.delete(wagon);
        return deleteWagonPlace(idWagon);
    }

    private boolean isWagonNull(Long idWagon) {
        return wagonDao.findByIdWagon(idWagon) == null;
    }

    private boolean deleteWagonPlace(Long idWagon) {
        return typePlaceDao.deleteByIdWagon(idWagon);
    }

    public boolean createPassengerWagon(Long idWagon, TypePlace typePlace) {
        wagon.setIdWagon(idWagon);
        wagon.setType(1);
        wagonDao.insert(wagon);
        return wagonDao.setTypePlace(wagon, typePlace);
    }

    public boolean createCargoWagon(Long idWagon) {
        wagon.setIdWagon(idWagon);
        wagon.setType(Wagon.CARGO_TYPE);
        wagonDao.insert(wagon);
        return true;
    }

    public boolean updateWagon(Long idWagon, TypePlace typePlace) {
        typePlace.setIdWagon(idWagon);
        return typePlaceDao.update(typePlace);
    }

    public List<Wagon> getWagons() {
        return wagonDao.findAll();
    }
}
