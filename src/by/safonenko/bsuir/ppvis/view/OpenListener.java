package by.safonenko.bsuir.ppvis.view;

import by.safonenko.bsuir.ppvis.model.TrainParser;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.parsers.SAXParser;
import javax.swing.JFrame;
import  java.util.ArrayList;
import by.safonenko.bsuir.ppvis.model.Train;
import by.safonenko.bsuir.ppvis.model.TreeFileChooser;


/**
 * Created by Admin on 17.04.2017.
 */
public class OpenListener implements ActionListener {
    MainWindow mainWindow;

    public void actionPerformed(ActionEvent ae)
        {
            JFrame.setDefaultLookAndFeelDecorated(true);
            TreeFileChooser tree = new TreeFileChooser(1);

            new Thread(new Runnable() {
                @Override
                public void run() {
                    tree.showOpenDialog();
                    try {
                        String path = tree.getPath();
                        SAXParserFactory factory = SAXParserFactory.newInstance();
                        SAXParser saxParser = factory.newSAXParser();
                        ArrayList<Train> trainList = new TrainParser().parse(path, saxParser);
                        mainWindow.controller.open(trainList);
                    } catch (Exception exeption) {
                        exeption.printStackTrace();
                    }
                }
            }).start();

        }

    public OpenListener(MainWindow mainWindow) {
        this.mainWindow = mainWindow;
        }
    }