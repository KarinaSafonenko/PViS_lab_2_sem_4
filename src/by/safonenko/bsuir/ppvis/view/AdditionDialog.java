package by.safonenko.bsuir.ppvis.view;

import javax.swing.JLabel;
import javax.swing.JDialog;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Container;
import by.safonenko.bsuir.ppvis.model.Train;
import net.sourceforge.jdatepicker.impl.UtilDateModel;
import net.sourceforge.jdatepicker.impl.JDatePanelImpl;
import net.sourceforge.jdatepicker.impl.JDatePickerImpl;
import java.util.Date;
import javax.swing.JSpinner;
import javax.swing.SpinnerDateModel;
import javax.swing.JSpinner.DateEditor;
import java.util.Calendar;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * Created by Admin on 14.04.2017.
 */
public class AdditionDialog {

    JTextField trainNumber;
    JTextField leavingStation;
    JTextField arrivingStation;
    JDatePickerImpl leavingDate;
    JSpinner leavingTime;
    JDatePickerImpl arrivingDate;
    JSpinner arrivingTime;
    JButton jbtnAdd;
    MainWindow mainWindow;
    Calendar cal;
    int inMillies = 3600000;
    int inMinutes = 60000;


    AdditionDialog(MainWindow mainWindow){
        this.mainWindow = mainWindow;
        JDialog addDialog = new JDialog(mainWindow.baseWindow, "Add to table", true);
        addDialog.setSize(400,400);
        GridBagLayout gridLayout = new GridBagLayout();
        GridBagConstraints gbc = new GridBagConstraints();
        final Container contentPane = addDialog.getContentPane();
        contentPane.setLayout(gridLayout);

        JLabel numberOfTrain = new JLabel("Номер поезда: ");
        trainNumber = new JTextField(15);
        JLabel outStation = new JLabel("Станция отправления:");
        leavingStation = new JTextField(15);
        JLabel inStation = new JLabel("Станция прибытия:");
        arrivingStation = new JTextField(15);
        JLabel dataOfLeaving = new JLabel("Дата отправления: ");
        UtilDateModel model1 = new UtilDateModel();
        JDatePanelImpl datePanel1 = new JDatePanelImpl(model1);
        leavingDate = new JDatePickerImpl(datePanel1);
        leavingDate.setPreferredSize(new java.awt.Dimension(170,25));
        JLabel timeOfLeaving = new JLabel("Время отправления: ");
        leavingTime = new JSpinner( new SpinnerDateModel());
        DateEditor timeEditor1 = new DateEditor(leavingTime, "HH:mm");
        leavingTime.setEditor(timeEditor1);
        leavingTime.setPreferredSize(new Dimension(170,25));
        leavingTime.setValue(new Date());
        JLabel dataOfArriving = new JLabel("Дата прибытия: ");
        UtilDateModel model3 = new UtilDateModel();
        JDatePanelImpl datePanel3 = new JDatePanelImpl(model3);
        arrivingDate = new JDatePickerImpl(datePanel3);
        arrivingDate.setPreferredSize(new Dimension(170,25));
        JLabel timeOfArriving = new JLabel("Время прибытия: ");
        arrivingTime = new JSpinner( new SpinnerDateModel());
        DateEditor timeEditor2 = new DateEditor(arrivingTime, "HH:mm");
        arrivingTime.setEditor(timeEditor2);
        arrivingTime.setPreferredSize(new Dimension(170,25));
        arrivingTime.setValue(new Date());

        jbtnAdd = new JButton("Добавить");

        gbc.gridx = 0;
        gbc.gridy = 0;
        gridLayout.setConstraints(numberOfTrain, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gridLayout.setConstraints(trainNumber, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gridLayout.setConstraints(outStation , gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        gridLayout.setConstraints(leavingStation, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        gridLayout.setConstraints(inStation, gbc);

        gbc.gridx = 0;
        gbc.gridy = 5;
        gridLayout.setConstraints(arrivingStation, gbc);

        gbc.gridx = 0;
        gbc.gridy = 6;
        gridLayout.setConstraints(dataOfLeaving, gbc);

        gbc.gridx = 0;
        gbc.gridy = 7;
        gridLayout.setConstraints(leavingDate, gbc);

        gbc.gridx = 0;
        gbc.gridy = 8;
        gridLayout.setConstraints(timeOfLeaving, gbc);

        gbc.gridx = 0;
        gbc.gridy = 9;
        gridLayout.setConstraints(leavingTime, gbc);

        gbc.gridx = 0;
        gbc.gridy = 10;
        gridLayout.setConstraints(dataOfArriving, gbc);

        gbc.gridx = 0;
        gbc.gridy = 11;
        gridLayout.setConstraints(arrivingDate, gbc);

        gbc.gridx = 0;
        gbc.gridy = 12;
        gridLayout.setConstraints(timeOfArriving, gbc);

        gbc.gridx = 0;
        gbc.gridy = 13;
        gridLayout.setConstraints(arrivingTime, gbc);

        gbc.insets = new java.awt.Insets(6,6,6,6);

        gbc.gridx = 0;
        gbc.gridy = 16;
        gridLayout.setConstraints(jbtnAdd, gbc);

        jbtnAdd.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int number = Integer.parseInt(getTrainNumber().getText());

                String leavingStation = getLeavingStation().getText();

                String arrivingStation = getArrivingStation().getText();

                Date leaveDate = (Date) leavingDate.getModel().getValue();
                cal = Calendar.getInstance();
                cal.setTime(leaveDate);
                clearTime(cal);
                leaveDate = cal.getTime();

                Date leaveTime = (Date) leavingTime.getValue();
                cal = Calendar.getInstance();
                cal.setTime(leaveTime);
                clearDate(cal);
                leaveTime = cal.getTime();

                Date arriveDate = (Date) arrivingDate.getModel().getValue();
                cal = Calendar.getInstance();
                cal.setTime(arriveDate);
                clearTime(cal);
                arriveDate = cal.getTime();

                Date arriveTime = (Date) arrivingTime.getValue();
                cal = Calendar.getInstance();
                cal.setTime(arriveTime);
                clearDate(cal);
                arriveTime = cal.getTime();

                cal = Calendar.getInstance();
                Calendar tempCal = Calendar.getInstance();
                tempCal.setTime(leaveTime);
                cal.setTime(leaveDate);
                union(cal, tempCal);

                Calendar calSecond = Calendar.getInstance();
                tempCal = Calendar.getInstance();
                tempCal.setTime(arriveTime);
                calSecond.setTime(arriveDate);
                union(calSecond, tempCal);

                long diff = (calSecond.getTimeInMillis() - cal.getTimeInMillis());
                int hours = (int) diff / inMillies;
                int minutes = (int)(diff-(hours*inMillies))/inMinutes;

                cal = Calendar.getInstance();
                clearDate(cal);
                clearTime(cal);
                cal.set(Calendar.HOUR_OF_DAY, hours);
                cal.set(Calendar.MINUTE, minutes);

                Date way = cal.getTime();

                Train newTrain = new Train(number, leavingStation, arrivingStation, leaveDate, leaveTime, arriveDate, arriveTime, way);
                mainWindow.controller.addTrain(newTrain);
            }
        });

        gbc.insets = new java.awt.Insets(4,4,4,4);


        contentPane.add(numberOfTrain);
        contentPane.add(trainNumber);
        contentPane.add(outStation);
        contentPane.add(leavingStation);
        contentPane.add(inStation);
        contentPane.add(arrivingStation);
        contentPane.add(dataOfLeaving);
        contentPane.add(leavingDate);
        contentPane.add(timeOfLeaving);
        contentPane.add(leavingTime);
        contentPane.add(dataOfArriving);
        contentPane.add(arrivingDate);
        contentPane.add(timeOfArriving);
        contentPane.add(arrivingTime);
        contentPane.add(jbtnAdd);

        addDialog.setVisible(true);
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

    public void union(Calendar cal, Calendar tempCal){
        clearDate(tempCal);
        clearTime(cal);
        cal.set(Calendar.HOUR, tempCal.get(Calendar.HOUR));
        cal.set(Calendar.HOUR_OF_DAY, tempCal.get(Calendar.HOUR_OF_DAY));
        cal.set(Calendar.MINUTE, tempCal.get(Calendar.MINUTE));
        cal.set(Calendar.SECOND, tempCal.get(Calendar.SECOND));
        cal.set(Calendar.MILLISECOND, tempCal.get(Calendar.MILLISECOND));
    }

    public JTextField getTrainNumber() {
        return trainNumber;
    }

    public JTextField getLeavingStation() {
        return leavingStation;
    }

    public JTextField getArrivingStation() {
        return arrivingStation;
    }


}
