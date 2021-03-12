package com.course_project.data_access.tests;

import com.course_project.data_access.DataSource;
import com.course_project.data_access.dao.impl.train_dao_impl.TrainDaoImpl;
import com.course_project.data_access.dao.impl.train_dao_impl.TrainSetDaoImpl;
import com.course_project.data_access.dao.impl.wagon_dao_impl.WagonDaoImpl;
import com.course_project.data_access.model.train.Train;
import com.course_project.data_access.model.train.TrainSet;
import com.course_project.data_access.model.wagon.Wagon;

import java.util.List;

public class TestTrain {
    public static final String PATH_DB = "jdbc:sqlite:C:\\Users\\Xiaomi\\IdeaProjects\\Course Project\\database\\railway.db";

    public static void main(String[] args) {
        DataSource dataSource = new DataSource();
        dataSource.setUrl(PATH_DB);

        TrainSetDaoImpl trainSetDao = new TrainSetDaoImpl(dataSource);

        Train train = new Train();
        train.setName("train#2");
        train.setCountWagon(2);
        Train train1 = new Train();
        train1.setName("train#4");
        train1.setCountWagon(5);

        TrainDaoImpl trainDao = new TrainDaoImpl(dataSource);
       // trainDao.insert(train1);
       // trainDao.insert(train);
        trainDao.deleteByName(train);
        // trainSetDao.deleteByTrainName(train1);
        showAllTrain(trainDao.findAll());


        Wagon wagon = new Wagon();
        wagon.setType(1);
        wagon.setIdWagon(3l);




        Wagon wagon1 = new Wagon();
        wagon1.setType(1);
        wagon1.setIdWagon(2l);



        WagonDaoImpl wagonDao = new WagonDaoImpl(dataSource);
       // wagonDao.insert(wagon);
       // wagonDao.insert(wagon1);


      //  trainSetDao.addWagon("train#4", wagon,1);
       // trainSetDao.addWagon("train#2", wagon1, 1);
        showAllTrainSet(trainSetDao.findAll());

    }

    public static void showTrainSet(TrainSet trainSet) {
        System.out.println(trainSet.toString());
    }

    public static void showAllTrainSet(List<TrainSet> trainSets) {
        for (TrainSet trainSet : trainSets) {
            System.out.println(trainSet);
        }
    }
    public static void showAllTrain(List<Train> trains) {
        for (Train train : trains) {
            System.out.println(train);
        }
    }
}
