package com.course_project.support;

import com.course_project.data_access.dao.impl.train_dao_impl.TrainDaoImpl;
import com.course_project.data_access.dao.impl.train_dao_impl.TrainSetDaoImpl;
import com.course_project.data_access.model.train.Train;
import com.course_project.data_access.model.train.TrainSet;
import com.course_project.data_access.model.wagon.Wagon;

import java.util.List;

public class TrainManager extends Manager implements UpdatableCountWagons {

    public static final int TRAIN_CAPACITY = 30;

    private TrainDaoImpl trainDao;

    private TrainSetDaoImpl trainSetDao;

    private Train train;

    public TrainManager() {
        trainDao = new TrainDaoImpl(dataSource);
        trainSetDao = new TrainSetDaoImpl(dataSource);
        train = new Train();
        train.setCapacity(TRAIN_CAPACITY);
    }


    public List<Train> getTrains() {
        return trainDao.findAll();
    }

    public boolean createTrain(String nameTrain) {
        train.setName(nameTrain);
        return trainDao.insert(train);
    }

    public boolean addWagonToTrain(String nameTrain, Wagon wagon, int position) {
        return trainSetDao.addWagon(nameTrain, wagon, position);
    }

    public boolean deleteTrain(String nameTrain) {
        train.setName(nameTrain);
        return trainDao.deleteByName(train);
    }

    public List<TrainSet> getTrainSets() {
        return trainSetDao.findAll();
    }

    @Override
    public void updateCountWagons(String name) {
        Train train = trainDao.findByName(name);
        int countWagon = train.getCountWagon();
        train.setCountWagon(--countWagon);
        trainDao.updateCountWagon(train);
    }
}
