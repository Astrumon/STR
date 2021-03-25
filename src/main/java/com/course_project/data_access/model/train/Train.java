package com.course_project.data_access.model.train;

import com.course_project.data_access.model.wagon.Wagon;

/**
 * POJO класс для наименований поездов
 */
public class Train {
    public static final String TABLE_NAME = "train";
    public static final String ID_COLUMN = "id";
    public static final String NAME_COLUMN = "name";
    public static final String COUNT_WAGON_COLUMN = "count_wagon";
    public static final String CAPACITY_COLUMN = "capacity";
    public static final String TYPE_COLUMN = "type";
    public static final String ID_ROUTE_COLUMN = "id_route";


    public static final int PASSENGER_TYPE = Wagon.PASSENGER_TYPE;
    public static final int CARGO_TYPE = Wagon.CARGO_TYPE;

    private Long id, idRoute;
    private String name;
    private int countWagon, capacity;
    private int type;

    public Long getIdRoute() {
        return idRoute;
    }

    public void setIdRoute(Long idRoute) {
        this.idRoute = idRoute;
    }

    public Train() {

    }
    private void checkType(int type) {
        if (!(type > 2)) {
            this.type = type;
        } else {
            this.type = PASSENGER_TYPE;
        }
    }

    public String defineType(int type) {
        String result = "";
        switch (type) {
            case PASSENGER_TYPE :result = " passenger ";
                break;
            case CARGO_TYPE : result = " cargo ";
                break;
        }

        return result;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public Train(String name, int countWagon) {
        this.name = name;
        this.countWagon = countWagon;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        checkType(type);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCountWagon() {
        return countWagon;
    }

    public void setCountWagon(int countWagon) {
        this.countWagon = countWagon;
    }

    @Override
    public String toString() {
        return "Train[name= " +  this.name
                + ", type= " + defineType(this.type)
                + ", capacity= " + this.capacity
                + ", idRoute= " + this.idRoute
                + ", countWagon= " + this.countWagon + "]";
    }
}
