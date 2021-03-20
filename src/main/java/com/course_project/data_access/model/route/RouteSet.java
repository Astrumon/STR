package com.course_project.data_access.model.route;

public class RouteSet {
    public static final String TABLE_NAME = "route_set";
    public static final String FROM_TOWN_COLUMN = "from_point";
    public static final String TO_TOWN_COLUMN = "to_point";
    public static final String ID_COLUMN = "id";
    public static final String ID_ROUTE_COLUMN = "id_route";
    public static final String SEND_TIME_COLUMN = "send_time";
    public static final String ARRIVE_TIME_COLUMN = "arrive_time";
    public static final String TRAIN_NAME_COLUMN = "train_name";
    public static final String PRICE_COLUMN = "price";
    public static final String DATE_COLUMN = "date";

    private Long id, idRoute;
    private String date, fromTown, toTown, sendTime, arriveTime, trainName;
    private int price;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdRoute() {
        return idRoute;
    }

    public void setIdRoute(Long idRoute) {
        this.idRoute = idRoute;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getFromTown() {
        return fromTown;
    }

    public void setFromTown(String fromTown) {
        this.fromTown = fromTown;
    }

    public String getToTown() {
        return toTown;
    }

    public void setToTown(String toTown) {
        this.toTown = toTown;
    }

    public String getSendTime() {
        return sendTime;
    }

    public void setSendTime(String sendTime) {
        this.sendTime = sendTime;
    }

    public String getArriveTime() {
        return arriveTime;
    }

    public void setArriveTime(String arriveTime) {
        this.arriveTime = arriveTime;
    }

    public String getTrainName() {
        return trainName;
    }

    public void setTrainName(String trainName) {
        this.trainName = trainName;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String toString() {
        return "routeSet[ "
                + "id= " + this.id
                + ", idRoute= " + this.idRoute
                + ", trainName= " + this.trainName
                + ", fromTown= " + this.fromTown
                + ", toTown= " + this.toTown
                + ", sendTime= " + this.sendTime
                + ", arriveTime= " + this.arriveTime
                + ", price= " + this.price
                + ", date= " + this.date
                + "]";
    }
}
