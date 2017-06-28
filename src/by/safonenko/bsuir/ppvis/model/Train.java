package by.safonenko.bsuir.ppvis.model;

import java.util.Date;
import java.sql.Time;

/**
 * Created by Admin on 14.04.2017.
 */
public class Train {

    int trainNumber;

    Date leavingDate;
    Date leavingTime;
    String leavingStation;
    String arrivingStation;
    Date arrivingDate;
    Date arrivingTime;
    Date wayTime;

    public Train(){

    }
    public Train (int trainNumber,String leavingStation, String arrivingStation, Date leavingDate, Date leavingTime,
                  Date arrivingDate, Date arrivingTime, Date wayTime){
        this.trainNumber = trainNumber;
        this.leavingDate = leavingDate;
        this.leavingTime = leavingTime;
        this.leavingStation = leavingStation;
        this.arrivingStation = arrivingStation;
        this.arrivingDate = arrivingDate;
        this.arrivingTime = arrivingTime;
        this.wayTime = wayTime;
    }

    public Date getLeavingDate() {
        return leavingDate;
    }

    public Date getLeavingTime() {
        return leavingTime;
    }

    public Date getArrivingDate() {
        return arrivingDate;
    }

    public Date getArrivingTime() {
        return arrivingTime;
    }

    public Date getWayTime() {
        return wayTime;
    }

    public int getTrainNumber() {
        return trainNumber;
    }

    public String getLeavingStation() {
        return leavingStation;
    }

    public String getArrivingStation() {
        return arrivingStation;
    }

}
