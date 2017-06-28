package by.safonenko.bsuir.ppvis.view;

import javax.swing.JLabel;
import java.util.Date;
import javax.swing.JDialog;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import javax.swing.JTextField;
import javax.swing.JButton;
import  java.awt.Container;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JOptionPane;
import by.safonenko.bsuir.ppvis.model.Train;
import  by.safonenko.bsuir.ppvis.controller.TrainController;
import net.sourceforge.jdatepicker.impl.JDatePanelImpl;
import net.sourceforge.jdatepicker.impl.JDatePickerImpl;
import  net.sourceforge.jdatepicker.impl.UtilDateModel;
import javax.swing.JSpinner;
import javax.swing.SpinnerDateModel;
import javax.swing.JSpinner.DateEditor;
import java.util.Calendar;

/**
 * Created by Admin on 14.04.2017.
 */
public class FindingDialog {
    public TrainTable tableOfStations;
    GridBagConstraints gbc;
    GridBagLayout gridLayout;
    public  JDialog findDialog;
    MainWindow mainWindow;
    TrainController controller;
    java.util.Calendar cal;


    FindingDialog(MainWindow mainWindow){
        this.mainWindow = mainWindow;
        findDialog = new JDialog(mainWindow.baseWindow, "Find in table", true);
        findDialog.setSize(700,720);
        gridLayout = new GridBagLayout();
        gbc = new GridBagConstraints();
        Container contentPane = findDialog.getContentPane();
        contentPane.setLayout(gridLayout);
        tableOfStations = new TrainTable(mainWindow);
        tableOfStations.CreateTrainTable();

        controller = new TrainController(this, mainWindow);

        JLabel numberOfTrain = new JLabel("Номер поезда: ");
        JTextField trainNumber = new JTextField(15);
        JLabel dateOfLeaving = new JLabel("Дата отправления:");
        UtilDateModel model = new UtilDateModel();
        JDatePanelImpl datePanel = new JDatePanelImpl(model);
        JDatePickerImpl leavingDate = new JDatePickerImpl(datePanel);
        leavingDate.setPreferredSize(new java.awt.Dimension(170,25));
        JButton jbtnFindFirst = new JButton("Найти");
        JLabel timeOfLeaving = new JLabel("Время отправления (от/до):");
        JSpinner leavingTimeFrom = new JSpinner( new SpinnerDateModel());
        DateEditor timeEditor1 = new DateEditor(leavingTimeFrom, "HH:mm");
        leavingTimeFrom.setEditor(timeEditor1);
        leavingTimeFrom.setPreferredSize(new java.awt.Dimension(170,25));
        leavingTimeFrom.setValue(new Date());
        JSpinner leavingTimeTo = new JSpinner( new SpinnerDateModel());
        DateEditor timeEditor2 = new DateEditor(leavingTimeTo, "HH:mm");
        leavingTimeTo.setEditor(timeEditor2);
        leavingTimeTo.setPreferredSize(new java.awt.Dimension(170,25));
        leavingTimeTo.setValue(new Date());
        JLabel timeOfArriving = new JLabel("Время прибытия (от/до): ");
        JSpinner arrivingTimeFrom = new JSpinner( new SpinnerDateModel());
        DateEditor timeEditor3 = new DateEditor(arrivingTimeFrom, "HH:mm");
        arrivingTimeFrom.setEditor(timeEditor3);
        arrivingTimeFrom.setPreferredSize(new java.awt.Dimension(170,25));
        arrivingTimeFrom.setValue(new Date());
        JSpinner arrivingTimeTo = new JSpinner( new SpinnerDateModel());
        DateEditor timeEditor4 = new DateEditor(arrivingTimeTo, "HH:mm");
        arrivingTimeTo.setEditor(timeEditor4);
        arrivingTimeTo.setPreferredSize(new java.awt.Dimension(170,25));
        arrivingTimeTo.setValue(new Date());
        JButton jbtnFindSecond = new JButton("Найти");
        JLabel stationOfLeaving = new JLabel("Станция отправления : ");
        JTextField leavingStation = new JTextField(15);
        JButton jbtnFindThird = new JButton("Найти");
        JLabel time = new JLabel("Время в пути:");
        JSpinner wayTime = new JSpinner( new SpinnerDateModel());
        DateEditor timeEditor5 = new DateEditor(wayTime, "HH:mm");
        wayTime.setEditor(timeEditor5);
        wayTime.setPreferredSize(new java.awt.Dimension(170,25));
        wayTime.setValue(new Date());
        JButton jbtnFindForth = new JButton("Найти");

        gbc.gridx = 0;
        gbc.gridy = 0;
        gridLayout.setConstraints(numberOfTrain, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gridLayout.setConstraints(trainNumber, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gridLayout.setConstraints(dateOfLeaving , gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        gridLayout.setConstraints(leavingDate, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        gridLayout.setConstraints(jbtnFindFirst, gbc);

        gbc.gridx = 0;
        gbc.gridy = 5;
        gridLayout.setConstraints(timeOfLeaving, gbc);

        gbc.gridx = 0;
        gbc.gridy = 6;
        gridLayout.setConstraints(leavingTimeFrom, gbc);

        gbc.gridx = 0;
        gbc.gridy = 7;
        gridLayout.setConstraints(leavingTimeTo, gbc);

        gbc.gridx = 0;
        gbc.gridy = 8;
        gridLayout.setConstraints(timeOfArriving, gbc);

        gbc.gridx = 0;
        gbc.gridy = 9;
        gridLayout.setConstraints(arrivingTimeFrom, gbc);

        gbc.gridx = 0;
        gbc.gridy = 10;
        gridLayout.setConstraints(arrivingTimeTo, gbc);

        gbc.gridx = 0;
        gbc.gridy = 11;
        gridLayout.setConstraints(jbtnFindSecond, gbc);


        gbc.gridx = 0;
        gbc.gridy = 12;
        gridLayout.setConstraints(stationOfLeaving, gbc);

        gbc.gridx = 0;
        gbc.gridy = 13;
        gridLayout.setConstraints(leavingStation, gbc);

        gbc.gridx = 0;
        gbc.gridy = 14;
        gridLayout.setConstraints(jbtnFindThird, gbc);

        gbc.gridx = 0;
        gbc.gridy = 15;
        gridLayout.setConstraints(time, gbc);

        gbc.gridx = 0;
        gbc.gridy = 16;
        gridLayout.setConstraints(wayTime, gbc);

        gbc.gridx = 0;
        gbc.gridy = 17;
        gridLayout.setConstraints(jbtnFindForth, gbc);

        addTable();

        jbtnFindThird.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String station = leavingStation.getText();
                int numberOfFound = controller.findTrains(station);
                update();
                JOptionPane.showMessageDialog(findDialog, "Количество найденных записей:" + numberOfFound);
                findDialog.setVisible(true);
            }
        });

        jbtnFindFirst.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int number = Integer.valueOf(trainNumber.getText());
                Date currentLeavingDate = (Date) leavingDate.getModel().getValue();
                cal = Calendar.getInstance();
                cal.setTime(currentLeavingDate);
                clearTime(cal);
                currentLeavingDate = cal.getTime();
                int numberOfFound = controller.findTrains(currentLeavingDate, number);
                update();
                JOptionPane.showMessageDialog(findDialog, "Количество найденных записей:" + numberOfFound);
                findDialog.setVisible(true);
            }
        });

        jbtnFindSecond.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Date fromLeaveTime = (Date)leavingTimeFrom.getValue();
                cal = Calendar.getInstance();
                cal.setTime(fromLeaveTime);
                clearDate(cal);
                fromLeaveTime = cal.getTime();
                Date toLeaveTime = (Date)leavingTimeTo.getValue();
                cal = Calendar.getInstance();
                cal.setTime(toLeaveTime);
                clearDate(cal);
                toLeaveTime = cal.getTime();
                Date fromArriveTime = (Date)arrivingTimeFrom.getValue();
                cal = Calendar.getInstance();
                cal.setTime(fromArriveTime);
                clearDate(cal);
                fromArriveTime = cal.getTime();
                Date toArriveTime = (Date)arrivingTimeTo.getValue();
                cal = Calendar.getInstance();
                cal.setTime(toArriveTime);
                clearDate(cal);
                toArriveTime = cal.getTime();
                int numberOfFound = controller.findTrains(fromLeaveTime, toLeaveTime, fromArriveTime, toArriveTime);
                update();
                JOptionPane.showMessageDialog(findDialog, "Количество найденных записей:" + numberOfFound);
                findDialog.setVisible(true);
            }
        });

        jbtnFindForth.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Date currentTime = (Date) wayTime.getValue();
                cal = Calendar.getInstance();
                cal.setTime(currentTime);
                clearDate(cal);
                currentTime = cal.getTime();
                int numberOfFound = controller.findTrains(currentTime);
                update();
                JOptionPane.showMessageDialog(findDialog, "Количество найденных записей:" + numberOfFound);
                findDialog.setVisible(true);
            }
        });

        contentPane.add(numberOfTrain);
        contentPane.add(trainNumber);
        contentPane.add(dateOfLeaving);
        contentPane.add(leavingStation);
        contentPane.add(jbtnFindFirst);
        contentPane.add(jbtnFindSecond);
        contentPane.add(jbtnFindThird);
        contentPane.add(jbtnFindForth);
        contentPane.add(leavingDate);
        contentPane.add(timeOfLeaving);
        contentPane.add(time);
        contentPane.add(wayTime);
        contentPane.add(leavingTimeFrom);
        contentPane.add(leavingTimeTo);
        contentPane.add(timeOfArriving);
        contentPane.add(arrivingTimeFrom);
        contentPane.add(arrivingTimeTo);
        contentPane.add(stationOfLeaving);

        findDialog.setVisible(true);

    }


    public void addTable(){

        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1.0;

        gbc.gridx = 0;
        gbc.gridy = 18;
        gridLayout.setConstraints(tableOfStations.jtbHeader, gbc);

        gbc.gridx = 0;
        gbc.gridy = 19;
        gridLayout.setConstraints(tableOfStations.tableOfStations, gbc);

        gbc.gridx = 0;
        gbc.gridy = 20;
        gridLayout.setConstraints(tableOfStations.jtbMove, gbc);

        gbc.gridx = 0;
        gbc.gridy = 21;
        gridLayout.setConstraints(tableOfStations.information, gbc);

        gbc.gridx = 0;
        gbc.gridy = 22;
        gridLayout.setConstraints(tableOfStations.numberOfReserved, gbc);

        gbc.gridx = 0;
        gbc.gridy = 23;
        gridLayout.setConstraints(tableOfStations.allPages, gbc);

        gbc.gridx = 0;
        gbc.gridy = 24;
        gridLayout.setConstraints(tableOfStations.numberOfPage, gbc);

        findDialog.add(tableOfStations.information);
        findDialog.add(tableOfStations.jtbHeader);
        findDialog.add(tableOfStations.tableOfStations);
        findDialog.add(tableOfStations.jtbMove);
        findDialog.add(tableOfStations.numberOfReserved);
        findDialog.add(tableOfStations.numberOfPage);
        findDialog.add(tableOfStations.allPages);
    }

    public void update() {
        java.util.List<Train> trains = controller.getTrains();
        tableOfStations.update(trains);
        tableOfStations.setData(tableOfStations.trainList);
        updateTrainInfo();
        tableOfStations.countPages();
        updatePage();
        updatePages();
        findDialog.validate();
    }

    public void updateTrainInfo(){
        tableOfStations.numberOfReserved.setText("Общее количество занятых строк: " + tableOfStations.reserved);
    }
    public void updatePage(){
        tableOfStations.numberOfPage.setText("Текущая страница: " + tableOfStations.page);
    }

    public void updatePages(){
        tableOfStations.allPages.setText("Общее количество страниц: " + tableOfStations.pages);
    }

    public void clearDate(Calendar cal){
        cal.set(1970, Calendar.JANUARY, 1);
        cal.set(Calendar.MILLISECOND,0);
        cal.set(Calendar.SECOND,0);
    }

    public void clearTime(Calendar cal){
        cal.set(Calendar.HOUR,0);
        cal.set(Calendar.HOUR_OF_DAY,0);
        cal.set(Calendar.MINUTE,0);
        cal.set(Calendar.SECOND,0);
        cal.set(Calendar.MILLISECOND,0);
    }

}
