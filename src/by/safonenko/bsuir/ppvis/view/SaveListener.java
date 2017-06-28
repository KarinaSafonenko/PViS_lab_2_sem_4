package by.safonenko.bsuir.ppvis.view;


import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import by.safonenko.bsuir.ppvis.model.TreeFileChooser;


/**
 * Created by Admin on 17.04.2017.
 */
public class SaveListener implements ActionListener {
    MainWindow mainWindow;

    public void actionPerformed(ActionEvent ae)
    {
       javax.swing.JFrame.setDefaultLookAndFeelDecorated(true);
        TreeFileChooser tree = new TreeFileChooser(2);

        new Thread(new Runnable() {
            @Override
            public void run() {
                tree.showOpenDialog();
                //System.out.println(tree.getPath());
                try {
                    String path = tree.getPath() + "\\";
                    mainWindow.tableOfStations.save(path + tree.getName() + ".xml");
                } catch (Exception exeption) {
                    exeption.printStackTrace();
                }
            }
        }).start();
    }

    public SaveListener(MainWindow mainWindow) {
        this.mainWindow = mainWindow;
    }
}
