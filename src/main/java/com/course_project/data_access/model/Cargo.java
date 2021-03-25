package com.course_project.data_access.model;

/**
 * POJO класс для грузоперевозок
 */
public class Cargo {
    public static final String TABLE_NAME = "cargo";
    public static final String ID_COLUMN = "id";
    public static final String FROM_TOWN_COlUMN = "from_town";
    public static final String TO_TOWN_COlUMN = "to_town";
    public static final String DATE_SEND_COLUMN = "date_send";
    public static final String DATE_ARRIVE_COLUMN = "date_arrive";
    public static final String EMAIL_COLUMN = "email";
    public static final String TEXT_COLUMN = "text";

    private Long id;
    private String fromTown, toTown, dateSend, dateArrive, email, text;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String toString() {
        return "Cargo["
                + "id= " + this.id
                + ", fromTown= " + this.fromTown
                + ", toTown= " + this.toTown
                + ", dateSend= " + this.dateSend
                + ", dateArrive= " + this.dateArrive
                + ", email= " + this.email
                + ", text= " + this.text
                + "]";
    }
}
