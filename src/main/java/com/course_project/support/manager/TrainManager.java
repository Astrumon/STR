package com.course_project.support.manager;

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
        train.setName(nameTrain);
        train.setType(typeTrain);
        boolean result =  trainDao.insert(train);

        createTrainSetPosition(train);
        return result;
    }

    private void createTrainSetPosition(Train train) {
        for (int i = 1; i <= train.getCapacity(); i++) {
            trainSetDao.insert(new TrainSet(train.getName(), i, train.getId()));
        }
    }


    public boolean addWagonToTrain(String nameTrain, Wagon wagon, int position) {
        boolean result;
        int trainType = trainDao.findByName(nameTrain).getType();

        if (wagon.checkType(wagon.getType()) == trainType) {
            String trainNameOfWagon = wagon.getTrainName();
            wagon.setTrainName(nameTrain);
            TrainSet trainSet = getFilledTrainSet(wagon, position);

            if (isEmptyTrainName(trainNameOfWagon) && trainSet.getId() != null) {
                result = trainSetDao.addWagon(nameTrain, wagon, position);

                updateWagonTrainSetInfo(trainSet);
                counterWagons(nameTrain);
                return result;
            } else {
                return false;
            }

        }
        return false;
    }

    public TrainSet getFilledTrainSet(Wagon wagon, int position) {
        TrainSet tSet = new TrainSet();
        for (TrainSet trainSet : trainSetDao.findAll()) {

            if (trainSet.getName().equals(wagon.getTrainName()) && samePosition(trainSet, position) && trainSet.getIdWagon() == 0) {
                tSet = trainSet;
                tSet.setIdWagon(wagon.getIdWagon());
                tSet.setPosWagon(position);
                break;
            }
        }

        return tSet;
    }

    private boolean samePosition(TrainSet trainSet, int position) {
        return trainSet.getPosWagon() == position;
    }

    private boolean isEmptyTrainName(String  nameTrain) {
        return nameTrain == null;
    }

    private void  updateWagonTrainSetInfo(TrainSet trainSet) {
        trainDao.updateTrainSet(trainSet, trainSet.getId());
        WagonDaoImpl wagonDao = new WagonDaoImpl(dataSource);
        Wagon wagon = wagonDao.findByIdWagon(trainSet.getIdWagon());
        wagon.setPosTrain(trainSet.getPosWagon());
        wagonDao.updatePosTrain(wagon);
    }

    private void counterWagons(String trainName) {
        int count = 0;
        for (TrainSet trainSet : trainSetDao.findAll()) {
            if (trainSet.getIdWagon() != 0 && trainSet.getName().equals(trainName)) {
                count++;
            }
        }
        updateCountWagons(trainName, count);
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

    public boolean updateIdRoute(Train train) {
       return trainDao.updateRoute(train);
    }
}
