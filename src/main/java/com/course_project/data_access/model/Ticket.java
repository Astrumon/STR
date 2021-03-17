package com.course_project.data_access.model;

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

    private Long id, idTicket;
    private String toTown, fromTown, timeStart, timeEnd;
    private int price;
    private boolean linen;


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
        return "Route[ id= " + this.id
                + ", toTown= " + this.toTown
                + ", fromTown= " + this.fromTown
                + ", timeStart= " + this.timeStart
                + ", timeEnd= " + this.timeEnd
                + ", price= " + this.price
                + ", linen= " + this.linen
                + "]";
    }
}
