package com.course_project.data_access.tests;

import com.course_project.database.DataSource;
import com.course_project.data_access.dao.impl.wagon_dao_impl.WagonDaoImpl;
import com.course_project.data_access.model.wagon.Wagon;

public class TestWagon {
    public static void main(String[] args) {
        DataSource dataSource = new DataSource();
        dataSource.setUrl(TestTrain.PATH_DB);

        Wagon wagon = new Wagon();
        wagon.setIdWagon(1l);
        wagon.setType(1);


        WagonDaoImpl wagonDao = new WagonDaoImpl(dataSource);
        System.out.println(wagonDao.insert(wagon));
        //wagon.setId(1l);
       //wagonDao.delete(wagon);

    }
}
