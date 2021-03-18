package com.course_project.data_access.model.wagon;

import javafx.beans.property.SimpleLongProperty;

public class Wagon {
    public static final String TABLE_NAME = "wagon";
    public static final String ID_COLUMN = "id";
    public static final String ID_WAGON_COLUMN = "id_wagon";
    public static final String NAME_WAREHOUSE_COLUMN = "name_warehouse";
    public static final String POSITION_TRAIN_COLUMN = "pos_train";
    public static final String TYPE_COLUMN = "type";
    public static final String TRAIN_NAME_COLUMN = "train_name";
    public static final String ID_TRAIN_SET_COLUMN = "id_train_set";
    public static final String ID_WAREHOUSE_SET_COLUMN = "id_warehouse_set";
    public static final String ID_COUNT_TYPE_PLACE_COLUMN = "id_count_type_place";
    public static final String COUNT_SEATS_COLUMN = "count_seats";

    public static final int PASSENGER_TYPE = 1;
    public static final int CARGO_TYPE = 2;
    public static final int LYING_TYPE = 3;
    public static final int SEATING_TYPE = 4;



    private String trainName;
    private Long id, idTrainSet, idWarehouseSet;
    private Long idWagon;
    private String nameWarehouse;
    private int posTrain, type;
    private Long idCountTypePlace;
    private int countSeats;

    public int getCountSeats() {
        return countSeats;
    }

    public void setCountSeats(int countSeats) {
        this.countSeats = countSeats;
    }

    private SimpleLongProperty idWagonProperty = new SimpleLongProperty();




    public Long getIdCountTypePlace() {
        return idCountTypePlace;
    }

    public void setIdCountTypePlace(Long idCountTypePlace) {
        this.idCountTypePlace = idCountTypePlace;
    }

    public Long getIdTrainSet() {
        return idTrainSet;
    }

    public void setIdTrainSet(Long idTrainSet) {
        this.idTrainSet = idTrainSet;
    }

    public Long getIdWarehouseSet() {
        return idWarehouseSet;
    }

    public void setIdWarehouseSet(Long idWarehouseSet) {
        this.idWarehouseSet = idWarehouseSet;
    }

    public Wagon() {

    }

    public Wagon(Long idWagon, String trainName, String nameWarehouse, int posTrain, int type) {
        this.idWagon = idWagon;
        this.nameWarehouse = nameWarehouse;
        this.posTrain = posTrain;
        this.trainName = trainName;

        checkType(type);
    }

    public Wagon( Long idWagon, String trainName, String nameWarehouse,
                 int posTrain, int type, Long idCountTypePlace) {
        this( idWagon, trainName, nameWarehouse, posTrain, type);
        this.idCountTypePlace = idCountTypePlace;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int checkType(int type) {
       if (type == CARGO_TYPE) {
           this.type = type;
           return CARGO_TYPE;
       } else {
           this.type = PASSENGER_TYPE;
           return PASSENGER_TYPE;
       }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdWagon() {
        return idWagon;
    }

    public void setIdWagon(Long idWagon) {
        this.idWagon = idWagon;
    }

    public String getNameWarehouse() {
        return nameWarehouse;
    }

    public void setNameWarehouse(String nameWarehouse) {
        this.nameWarehouse = nameWarehouse;
    }

    public int getPosTrain() {
        return posTrain;
    }

    public void setPosTrain(int posTrain) {
        this.posTrain = posTrain;
    }


    public String getTrainName() {
        return trainName;
    }

    public void setTrainName(String trainName) {
        this.trainName = trainName;
    }

    public String defineType(int type) {
        String result = "";
        switch (type) {

            case CARGO_TYPE : result = " cargo ";
            break;
            default: result = " passenger ";
        }

        return result;
    }

    @Override
    public String toString() {
        return "Wagon [id= " + this.id
                + ", nameWarehouse= " + this.nameWarehouse + ", idWagon= " + this.idWagon
                + ", posTrain= " + this.posTrain + ", trainName= " + this.trainName
                + ", type= " + defineType(this.type)
                + ", idTrainSet = " + this.idTrainSet
                + ", idWarehouseSet = " + this.idWarehouseSet
                + ", idCountTypePlace = " + this.idCountTypePlace
                +"]";
    }
}
