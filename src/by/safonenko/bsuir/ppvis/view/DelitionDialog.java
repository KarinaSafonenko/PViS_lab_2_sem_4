package by.safonenko.bsuir.ppvis.view;

import javax.swing.JLabel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JDialog;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Container;
import java.util.Date;
import javax.swing.JOptionPane;
import net.sourceforge.jdatepicker.impl.JDatePickerImpl;
import net.sourceforge.jdatepicker.impl.UtilDateModel;
import net.sourceforge.jdatepicker.impl.JDatePanelImpl;
import  javax.swing.JSpinner;
import javax.swing.JSpinner.DateEditor;
import javax.swing.SpinnerDateModel;
import java.util.Calendar;
import java.awt.Dimension;
/**
 * Created by Admin on 14.04.2017.
 */
public class DelitionDialog {
    MainWindow mainWindow;
    java.util.Calendar cal;
    DelitionDialog(MainWindow mainWindow){
        this.mainWindow = mainWindow;
        JDialog delDialog = new JDialog(mainWindow.baseWindow, "Delete in table", true);
        delDialog.setSize(500,500);
        GridBagLayout gridLayout = new GridBagLayout();
        GridBagConstraints gbc = new GridBagConstraints();
        final Container contentPane = delDialog.getContentPane();
        contentPane.setLayout(gridLayout);

        JLabel numberOfTrain = new JLabel("Номер поезда: ");
        JTextField trainNumber = new JTextField(15);
        JLabel dateOfLeaving = new JLabel("Дата отправления:");
        UtilDateModel model = new UtilDateModel();
        JDatePanelImpl datePanel = new JDatePanelImpl(model);
        JDatePickerImpl leavingDate = new JDatePickerImpl(datePanel);
        leavingDate.setPreferredSize(new Dimension(170,25));
        JButton jbtnDelFirst = new JButton("Удалить");
        JLabel timeOfLeaving = new JLabel("Время отправления (от/до):");
        JSpinner leavingTimeFrom = new JSpinner( new SpinnerDateModel());
        DateEditor timeEditor1 = new DateEditor(leavingTimeFrom, "HH:mm");
        leavingTimeFrom.setEditor(timeEditor1);
        leavingTimeFrom.setPreferredSize(new Dimension(170,25));
        leavingTimeFrom.setValue(new Date());
        JSpinner leavingTimeTo = new JSpinner( new SpinnerDateModel());
        DateEditor timeEditor2 = new DateEditor(leavingTimeTo, "HH:mm");
        leavingTimeTo.setEditor(timeEditor2);
        leavingTimeTo.setPreferredSize(new Dimension(170,25));
        leavingTimeTo.setValue(new Date());
        JLabel timeOfArriving = new JLabel("Время прибытия (от/до): ");
        JSpinner arrivingTimeFrom = new JSpinner( new SpinnerDateModel());
        DateEditor timeEditor3 = new DateEditor(arrivingTimeFrom, "HH:mm");
        arrivingTimeFrom.setEditor(timeEditor3);
        arrivingTimeFrom.setPreferredSize(new Dimension(170,25));
        arrivingTimeFrom.setValue(new Date());
        JSpinner arrivingTimeTo = new JSpinner( new SpinnerDateModel());
        DateEditor timeEditor4 = new DateEditor(arrivingTimeTo, "HH:mm");
        arrivingTimeTo.setEditor(timeEditor4);
        arrivingTimeTo.setPreferredSize(new Dimension(170,25));
        arrivingTimeTo.setValue(new Date());
        JButton jbtnDelSecond = new JButton("Удалить");
        JLabel stationOfLeaving = new JLabel("Станция отправления : ");
        JTextField leavingStation = new JTextField(15);
        JButton jbtnDelThird = new JButton("Удалить");
        JLabel time = new JLabel("Время в пути:");
        JSpinner wayTime = new JSpinner( new SpinnerDateModel());
        DateEditor timeEditor5 = new DateEditor(wayTime, "HH:mm");
        wayTime.setEditor(timeEditor5);
        wayTime.setPreferredSize(new Dimension(170,25));
        wayTime.setValue(new Date());
        JButton jbtnDelForth = new JButton("Удалить");

        jbtnDelThird.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String station = leavingStation.getText();
                int numberOfDeleted = mainWindow.controller.deleteTrains(station);
                JOptionPane.showMessageDialog(delDialog, "Количество удаленных записей:" + numberOfDeleted);
            }
        });

        jbtnDelFirst.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int number = Integer.valueOf(trainNumber.getText());
                Date currentLeavingDate = (Date) leavingDate.getModel().getValue();
                cal = Calendar.getInstance();
                cal.setTime(currentLeavingDate);
                clearTime(cal);
                currentLeavingDate = cal.getTime();
                int numberOfDeleted = mainWindow.controller.deleteTrains(currentLeavingDate, number);
                JOptionPane.showMessageDialog(delDialog, "Количество удаленных записей:" + numberOfDeleted);
            }
        });

        jbtnDelSecond.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) {

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
                int numberOfDeleted = mainWindow.controller.deleteTrains(fromLeaveTime, toLeaveTime, fromArriveTime, toArriveTime);
                JOptionPane.showMessageDialog(delDialog, "Количество удаленных записей:" + numberOfDeleted);

            }
        });


        jbtnDelForth.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) {
                Date currentTime = (Date) wayTime.getValue();
                cal = Calendar.getInstance();
                cal.setTime(currentTime);
                clearDate(cal);
                currentTime = cal.getTime();
                int numberOfDeleted = mainWindow.controller.deleteTrains(currentTime);
                JOptionPane.showMessageDialog(delDialog, "Количество удаленных записей:" + numberOfDeleted);
            }
        });

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
        gridLayout.setConstraints(jbtnDelFirst, gbc);

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
        gridLayout.setConstraints(jbtnDelSecond, gbc);

        gbc.gridx = 0;
        gbc.gridy = 12;
        gridLayout.setConstraints(stationOfLeaving, gbc);

        gbc.gridx = 0;
        gbc.gridy = 13;
        gridLayout.setConstraints(leavingStation, gbc);

        gbc.gridx = 0;
        gbc.gridy = 14;
        gridLayout.setConstraints(jbtnDelThird, gbc);

        gbc.gridx = 0;
        gbc.gridy = 15;
        gridLayout.setConstraints(time, gbc);

        gbc.gridx = 0;
        gbc.gridy = 16;
        gridLayout.setConstraints(wayTime, gbc);

        gbc.gridx = 0;
        gbc.gridy = 17;
        gridLayout.setConstraints(jbtnDelForth, gbc);

        gbc.insets = new java.awt.Insets(4,4,4,4);

        contentPane.add(numberOfTrain);
        contentPane.add(trainNumber);
        contentPane.add(dateOfLeaving);
        contentPane.add(leavingStation);
        contentPane.add(jbtnDelFirst);
        contentPane.add(jbtnDelSecond);
        contentPane.add(jbtnDelThird);
        contentPane.add(jbtnDelForth);
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

        delDialog.setVisible(true);

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
