package by.safonenko.bsuir.ppvis.controller;

import by.safonenko.bsuir.ppvis.view.MainWindow;
import by.safonenko.bsuir.ppvis.model.Train;
import java.util.Date;
import java.util.ArrayList;
import by.safonenko.bsuir.ppvis.model.TrainList;
import java.util.List;
import by.safonenko.bsuir.ppvis.view.FindingDialog;


/**
 * Created by Admin on 26.04.2017.
 */
public class TrainController {
        MainWindow mainWindow;
        TrainList trainList;
        FindingDialog dialog;

    public TrainController(MainWindow mainWindow) {
        this.mainWindow = mainWindow;
        trainList = new TrainList();
    }

    public TrainController(FindingDialog dialog, MainWindow mainWindow) {
        this.mainWindow = mainWindow;
        this.dialog = dialog;
        trainList = new TrainList();
    }

    public void open(List<Train> trainList){
        TrainList newTrainList = new TrainList();
        newTrainList.update(trainList);
        this.trainList = newTrainList;
        mainWindow.setNumberOfReserved(trainList);
        mainWindow.updateTrainInfo();
        mainWindow.setDateTable(newTrainList);
        mainWindow.update();
    }

    public void addTrain(Train newTrain){
        trainList = mainWindow.getTrainList();
        trainList.add(newTrain);
        mainWindow.update();
    }

    public int deleteTrains(String station){
        int numberOfDeleted = mainWindow.tableOfStations.deleteTrains(station);
        trainList = mainWindow.getTrainList();
        mainWindow.update();
        return numberOfDeleted;
    }

    public int deleteTrains(Date fromLeaveTime, Date toLeaveTime, Date fromArriveTime, Date toArriveTime){
        int numberOfDeleted = mainWindow.tableOfStations.deleteTrains(fromLeaveTime, toLeaveTime, fromArriveTime, toArriveTime);
        trainList = mainWindow.getTrainList();
        mainWindow.update();
        return numberOfDeleted;
    }

    public int findTrains(Date fromLeaveTime, Date toLeaveTime, Date fromArriveTime, Date toArriveTime){
        ArrayList<Train> listOfFound = new ArrayList<>();
        listOfFound = mainWindow.tableOfStations.findTrains(fromLeaveTime, toLeaveTime, fromArriveTime, toArriveTime);
        int numberOfFound = listOfFound.size();
        trainList.update(listOfFound);
        return numberOfFound;
    }

    public int deleteTrains(Date wayTime){
        int numberOfDeleted = mainWindow.tableOfStations.deleteTrains(wayTime);
        trainList = mainWindow.getTrainList();
        mainWindow.update();
        return numberOfDeleted;
    }

    public int findTrains(String station){
        ArrayList<Train> listOfFound = new ArrayList<>();
        listOfFound = mainWindow.tableOfStations.findTrains(station);
        int numberOfFound = listOfFound.size();
        trainList.update(listOfFound);
        return numberOfFound;
    }

    public int findTrains(Date wayTime){
        ArrayList<Train> listOfFound = new ArrayList<>();
        listOfFound = mainWindow.tableOfStations.findTrains(wayTime);
        int numberOfFound = listOfFound.size();
        trainList.update(listOfFound);
        return numberOfFound;
    }

    public int deleteTrains(Date leavingDate, int trainNumber){
        int numberOfDeleted = mainWindow.tableOfStations.deleteTrains(leavingDate, trainNumber);
        trainList = mainWindow.getTrainList();
        mainWindow.update();
        return numberOfDeleted;
    }

    public int findTrains(Date leavingDate, int trainNumber){
        ArrayList<Train> listOfFound = new ArrayList<>();
        listOfFound = mainWindow.tableOfStations.findTrains(leavingDate, trainNumber);
        int numberOfFound = listOfFound.size();
        trainList.update(listOfFound);
        return numberOfFound;
    }

    public List<Train> getTrains() {
        return trainList.getTrains();
    }
}
