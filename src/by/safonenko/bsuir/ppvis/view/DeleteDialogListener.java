package by.safonenko.bsuir.ppvis.view;

import  javax.swing.JFrame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Admin on 14.04.2017.
 */
public class DeleteDialogListener implements ActionListener {
    MainWindow mainWindow;
    public void actionPerformed(ActionEvent ae)
    {
        new DelitionDialog(mainWindow);
    }

    public DeleteDialogListener (MainWindow mainWindow)
    {
        this.mainWindow = mainWindow;
    }

}
