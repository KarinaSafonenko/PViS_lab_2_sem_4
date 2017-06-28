package by.safonenko.bsuir.ppvis.view;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;



/**
 * Created by Admin on 17.04.2017.
 */

public class CloseListener implements ActionListener {
    MainWindow mainWindow;

    public void actionPerformed(ActionEvent ae)
    {
       mainWindow.tableOfStations.clear();
    }

    public CloseListener(MainWindow mainWindow) {
        this.mainWindow = mainWindow;
    }
}