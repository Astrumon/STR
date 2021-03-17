package com.course_project.support;

import com.course_project.data_access.dao.impl.train_dao_impl.TrainDaoImpl;
import com.course_project.data_access.dao.impl.train_dao_impl.TrainSetDaoImpl;
import com.course_project.data_access.dao.impl.wagon_dao_impl.WagonDaoImpl;
import com.course_project.data_access.model.train.Train;
import com.course_project.data_access.model.train.TrainSet;
import com.course_project.data_access.model.wagon.Wagon;

import java.util.List;

public class TrainManager extends Manager {

    public static final int TRAIN_CAPACITY = 30;

    private TrainDaoImpl trainDao;

    private TrainSetDaoImpl trainSetDao;

    private Train train;

    public static Train transfer;

    public TrainManager() {
        trainDao = new TrainDaoImpl(dataSource);
        trainSetDao = new TrainSetDaoImpl(dataSource);
        train = new Train();
        train.setCapacity(TRAIN_CAPACITY);
    }

    public Train getTrain(String name) {
        return trainDao.findByName(name);
    }


    public List<Train> getTrains() {
        return trainDao.findAll();
    }

    public boolean createTrain(String nameTrain, int typeTrain) {
        System.out.println("TYPE MNG: " + typeTrain);
        train.setName(nameTrain);
        train.setType(typeTrain);
        return trainDao.insert(train);
    }

    public boolean addWagonToTrain(String nameTrain, Wagon wagon, int position) {
        int trainType = trainDao.findByName(nameTrain).getType();
        System.out.println("WAGONTYPE " + wagon.getType() + " TRAINTYPE " + trainType);

        if (wagon.getType() == trainType) {
            return trainSetDao.addWagon(nameTrain, wagon, position);
        }
        return false;
    }

    public boolean deleteTrain(String nameTrain) {
        if (trainDao.findByName(nameTrain) == null ) {
            return false;
        }
        train.setName(nameTrain);
        return trainDao.deleteByName(train);
    }

    public List<TrainSet> getTrainSets() {
        return trainSetDao.findAll();
    }


    public void updateCountWagons(String name, int count) {
        Train train = trainDao.findByName(name);
        train.setCountWagon(count);
        trainDao.updateCountWagon(train);
    }

    public boolean deleteWagonFromTrain(String nameTrain, Wagon wagon) {
        Wagon wagonWithoutWarehouse = updateWagonInfoAboutTrainSet(wagon);
       return updateTrainSetInfoAboutWagon(nameTrain, wagonWithoutWarehouse);
    }

    private Wagon updateWagonInfoAboutTrainSet( Wagon wagon) {
        WagonDaoImpl wagonDao = new WagonDaoImpl(dataSource);

        TrainSet trainSet = new TrainSet();
        Wagon wagonWithoutTrain = wagonDao.findByIdWagon(wagon.getIdWagon());
        trainSet.setIdTrain(null);
        trainSet.setIdWagon(wagonWithoutTrain.getIdWagon());
        trainSet.setName(null);
        trainSet.setId(null);
        trainSet.setPosWagon(0);
        wagonDao.updateTrainSet(trainSet, wagonWithoutTrain.getIdWarehouseSet());

        return wagonWithoutTrain;
    }

    private boolean updateTrainSetInfoAboutWagon(String nameTrain, Wagon wagon) {
        TrainSet trainSet = trainSetDao.findByName(nameTrain);
        trainSet.setIdWagon(null);
        trainSet.setId(wagon.getIdTrainSet());
       return trainSetDao.updateWagon(trainSet);
    }
}
