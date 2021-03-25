package com.course_project.data_access.model;

/**
 * POJO класс для билетов
 */
public class Ticket {
    public static final String TABLE_NAME = "ticket";
    public static final String ID_COLUMN = "id";
    public static final String ID_TICKET_COLUMN = "id_ticket";
    public static final String FROM_TOWN_COLUMN = "from_town";
    public static final String TO_TOWN_COLUMN = "to_town";
    public static final String TIME_START_COLUMN = "time_start";
    public static final String TIME_END_COLUMN = "time_end";
    public static final String PRICE_COLUMN = "price";
    public static final String LINEN_COLUMN = "linen";
    public static final String STATUS_COLUMN = "status";
    public static final String ID_ROUTE_COLUMN = "id_route";
    public static final String DATE_SEND_COLUMN = "date_send";
    public static final String DATE_ARRIVE_COLUMN = "date_arrive";
    public static final String TYPE_PLACE_COLUMN = "type_place";
    public static final String PLACE_COLUMN = "place";
    public static final String ID_WAGON_COLUMN = "id_wagon";
    public static final String TRAIN_NAME_COLUMN = "train_name";
    public static final String CONTACT_COLUMN = "contact";


    public static final int STATUS_FREE = 0;
    public static final int STATUS_SOLD = 1;

    private Long id, idTicket, idRoute, idWagon;
    private String toTown, fromTown, timeStart, timeEnd, dateSend, dateArrive, trainName, contact;
    private int price, status, placeNumber, typePlace;
    private boolean linen;

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public Long getIdWagon() {
        return idWagon;
    }

    public void setIdWagon(Long idWagon) {
        this.idWagon = idWagon;
    }

    public String getTrainName() {
        return trainName;
    }

    public void setTrainName(String trainName) {
        this.trainName = trainName;
    }

    public int getPlaceNumber() {
        return placeNumber;
    }

    public void setPlaceNumber(int placeNumber) {
        this.placeNumber = placeNumber;
    }

    public int getTypePlace() {
        return typePlace;
    }

    public void setTypePlace(int typePlace) {
        this.typePlace = typePlace;
    }

    public String getDateSend() {
        return dateSend;
    }

    public void setDateSend(String dateSend) {
        this.dateSend = dateSend;
    }

    public String getDateArrive() {
        return dateArrive;
    }

    public void setDateArrive(String dateArrive) {
        this.dateArrive = dateArrive;
    }

    public Long getIdRoute() {
        return idRoute;
    }

    public void setIdRoute(Long idRoute) {
        this.idRoute = idRoute;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdTicket() {
        return idTicket;
    }

    public void setIdTicket(Long idTicket) {
        this.idTicket = idTicket;
    }

    public String getToTown() {
        return toTown;
    }

    public void setToTown(String toTown) {
        this.toTown = toTown;
    }

    public String getFromTown() {
        return fromTown;
    }

    public void setFromTown(String fromTown) {
        this.fromTown = fromTown;
    }

    public String getTimeStart() {
        return timeStart;
    }

    public void setTimeStart(String timeStart) {
        this.timeStart = timeStart;
    }

    public String getTimeEnd() {
        return timeEnd;
    }

    public void setTimeEnd(String timeEnd) {
        this.timeEnd = timeEnd;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public boolean isLinen() {
        return linen;
    }

    public void setLinen(boolean linen) {
        this.linen = linen;
    }

    public String toString() {
        return "Ticket[ id= " + this.id
                + ", toTown= " + this.toTown
                + ", fromTown= " + this.fromTown
                + ", timeStart= " + this.timeStart
                + ", timeEnd= " + this.timeEnd
                + ", price= " + this.price
                + ", linen= " + this.linen
                + ", status= " + this.status
                + ", idRoute= " + this.idRoute
                + ", dateSend= " + this.dateSend
                + ", dateArrive= " + this.dateArrive
                + ", idWagon= " + this.idWagon
                + ", placeNumber= " + this.placeNumber
                + ", typePlace= " + this.typePlace
                + ", trainName= " + this.trainName
                + ", contact= " + this.contact
                + "]";
    }
}
