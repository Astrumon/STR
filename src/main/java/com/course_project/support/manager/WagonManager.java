package com.course_project.support.manager;


import com.course_project.data_access.dao.impl.wagon_dao_impl.PlaceDaoImpl;
import com.course_project.data_access.dao.impl.wagon_dao_impl.TypePlaceDaoImpl;
import com.course_project.data_access.dao.impl.wagon_dao_impl.WagonDaoImpl;
import com.course_project.data_access.model.wagon.Place;
import com.course_project.data_access.model.wagon.TypePlace;
import com.course_project.data_access.model.wagon.Wagon;

import java.util.List;

public class WagonManager extends Manager {

    public static Wagon transfer;
    private WagonDaoImpl wagonDao;
    private TypePlaceDaoImpl typePlaceDao;
    private PlaceDaoImpl placeDao;
    private Wagon wagon;
    private List<Wagon> wagons;

    public WagonManager() {
        wagonDao = new WagonDaoImpl(dataSource);
        typePlaceDao = new TypePlaceDaoImpl(dataSource);
        placeDao = new PlaceDaoImpl(dataSource);
        wagon = new Wagon();

    }

    public TypePlaceDaoImpl getTypePlaceDao() {
        return typePlaceDao;
    }

    public boolean deleteWagon(Long idWagon) {

        if (isWagonNull(idWagon)) {
            return false;
        }

        wagon.setIdWagon(idWagon);
        wagonDao.delete(wagon);
        return deleteWagonPlace(idWagon);
    }

    public Wagon getWagon(Long idWagon) {
        return wagonDao.findByIdWagon(idWagon);
    }

   public List<Wagon> getWagonsByTrainName(String trainName) {
        return wagonDao.findByTrainName(trainName);
   }

    private boolean isWagonNull(Long idWagon) {
        return wagonDao.findByIdWagon(idWagon) == null;
    }

    private boolean deleteWagonPlace(Long idWagon) {
        return typePlaceDao.deleteByIdWagon(idWagon);
    }

    public boolean createPassengerWagon(Long idWagon, TypePlace typePlace, int typeWagon) {
        wagon.setIdWagon(idWagon);
        wagon.setType(typeWagon);
        wagonDao.insert(wagon);

        if (wagon.checkType(wagon.getType()) == Wagon.PASSENGER_TYPE) {
            typePlace.setIdWagon(wagon.getIdWagon());
            typePlaceDao.insert(typePlace);
            wagonDao.setCountSeats(typePlace);
            createPlace(typePlace.getIdTypePlace());
        } else {
            return false;
        }
        return wagonDao.setTypePlace(wagon, typePlace);
    }

    private static class NumberGenerator {
        static int number = 1;
    }

    public boolean createCargoWagon(Long idWagon) {
        wagon.setIdWagon(idWagon);
        wagon.setType(Wagon.CARGO_TYPE);
        wagonDao.insert(wagon);
        return true;
    }

    public boolean updateWagon(Long idWagon, TypePlace typePlace) {
        typePlace.setIdWagon(idWagon);
        boolean result =  typePlaceDao.update(typePlace);

        updatePlaces(typePlace);
        return result;
    }

    private void updatePlaces(TypePlace typePlace) {
        wagonDao.setCountSeats(typePlace);

        Place place = new Place();
        place.setIdWagon(typePlace.getIdWagon());
        placeDao.delete(place);
        typePlace.setIdTypePlace(typePlaceDao.findByIdWagon(typePlace.getIdWagon()).getIdTypePlace());
        createPlace(typePlace.getIdTypePlace());
    }

    private void createPlace(Long idCountTypePlace) {
        TypePlace typePlace = typePlaceDao.findById(idCountTypePlace);
        NumberGenerator.number = 1;

        createTypePlace(typePlace, TypePlace.VIP);
        createTypePlace(typePlace, TypePlace.MIDDLE);
        createTypePlace(typePlace, TypePlace.LOW);
        createTypePlace(typePlace, TypePlace.SEATS);
    }

    private void createTypePlace(TypePlace typePlace, int type) {
        Place place = new Place();
        place.setType(type);
        place.setIdWagon(typePlace.getIdWagon());
        place.setIdCountType(typePlace.getIdTypePlace());

        for (int i = 1; i <= typePlace.defineType(type); i++) {
            place.setNumber(NumberGenerator.number++);

            placeDao.insert(place);
            if (i == typePlace.getAllPlace()) {
                NumberGenerator.number = 1;
            }
        }
    }

    public List<Wagon> getWagons() {
        return wagonDao.findAll();
    }

    public List<Place> getPlacesByIdWagon(Long idWagon) {
        return placeDao.findByIdWagon(idWagon);
    }
}
