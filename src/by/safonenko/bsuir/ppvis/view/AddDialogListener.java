package by.safonenko.bsuir.ppvis.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class AddDialogListener implements ActionListener {
    MainWindow mainWindow;
    public void actionPerformed(ActionEvent ae){
        new AdditionDialog(mainWindow);
    }
    public AddDialogListener (MainWindow mainWindow){
            this.mainWindow = mainWindow;
    }
}
