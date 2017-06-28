package by.safonenko.bsuir.ppvis.view;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.event.KeyEvent;
import javax.swing.KeyStroke;
import java.awt.event.InputEvent;
import javax.swing.JToolBar;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import by.safonenko.bsuir.ppvis.controller.TrainController;
import java.util.List;
import by.safonenko.bsuir.ppvis.model.Train;
import by.safonenko.bsuir.ppvis.model.TrainList;



/**
 * Created by Admin on 30.03.2017.
 */
public class MainWindow {
    public JFrame baseWindow;
    GridBagConstraints gbc;
    GridBagLayout gridLayout;

    JMenuItem jmiOpen;
    JButton jbtnOpen;
    JMenuItem jmiAdd;
    JMenuItem jmiFind;
    JMenuItem jmiDelete;
    JButton jbtnFind;
    JButton jbtnAdd;
    JButton jbtnDelete;
    JMenuItem jmiClose;
    JButton jbtnClose;

    TrainController controller;

    public TrainTable tableOfStations;

    public MainWindow()
    {
        baseWindow = new JFrame();
        gridLayout = new GridBagLayout();
        gbc = new GridBagConstraints();
        gbc.fill = java.awt.GridBagConstraints.HORIZONTAL;
        baseWindow.getContentPane().setLayout(gridLayout);
        baseWindow.setSize(1000,400);
        baseWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        tableOfStations = new TrainTable(this);
        tableOfStations.CreateTrainTable();
        AddTabbleToMainWindow();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1.0;


        controller = new TrainController(this);

        JMenuBar menuLine = new JMenuBar();

        javax.swing.JMenu jmFile = new javax.swing.JMenu("File");

        jmiOpen = new JMenuItem("Open", KeyEvent.VK_O);
        jmiClose = new JMenuItem("Close",KeyEvent.VK_C);
        JMenuItem jmiSave = new JMenuItem("Save", KeyEvent.VK_S);
        JMenuItem jmiExit = new JMenuItem("Exit", KeyEvent.VK_E);

        jmiOpen.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, InputEvent.CTRL_MASK));
        jmiClose.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, InputEvent.CTRL_MASK));
        jmiSave.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_MASK));
        jmiExit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E, InputEvent.CTRL_MASK));

        jmFile.add(jmiOpen);
        jmFile.add(jmiClose);
        jmFile.add(jmiSave);
        jmFile.addSeparator();
        jmFile.add(jmiExit);

        menuLine.add(jmFile);

        JMenu jmOptions = new JMenu("Options");

        jmiAdd = new JMenuItem("Add", KeyEvent.VK_A);
        jmiFind = new JMenuItem("Find", KeyEvent.VK_F);
        jmiDelete = new JMenuItem("Delete", KeyEvent.VK_D);

        jmiAdd.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, InputEvent.CTRL_MASK));
        jmiFind.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F, InputEvent.CTRL_MASK));
        jmiDelete.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_D, InputEvent.CTRL_MASK));

        jmOptions.add(jmiAdd);
        jmOptions.add(jmiFind);
        jmOptions.add(jmiDelete);

        menuLine.add(jmOptions);

        JToolBar jtbModifyDocument = new JToolBar();

        ImageIcon open = new ImageIcon("D:\\q\\Test\\MainWindow\\src\\by\\safonenko\\bsuir\\ppvis\\resorses\\Open.png");
        ImageIcon save = new ImageIcon("D:\\q\\Test\\MainWindow\\src\\by\\safonenko\\bsuir\\ppvis\\resorses\\Save.png");
        ImageIcon close = new ImageIcon("D:\\q\\Test\\MainWindow\\src\\by\\safonenko\\bsuir\\ppvis\\resorses\\Close.png");
        ImageIcon find = new ImageIcon("D:\\q\\Test\\MainWindow\\src\\by\\safonenko\\bsuir\\ppvis\\resorses\\Find.png");
        ImageIcon add = new ImageIcon("D:\\q\\Test\\MainWindow\\src\\by\\safonenko\\bsuir\\ppvis\\resorses\\Add.png");
        ImageIcon delete = new ImageIcon("D:\\q\\Test\\MainWindow\\src\\by\\safonenko\\bsuir\\ppvis\\resorses\\Delete.png");
        ImageIcon exit = new ImageIcon("D:\\q\\Test\\MainWindow\\src\\by\\safonenko\\bsuir\\ppvis\\resorses\\Exit.png");


        jbtnOpen = new JButton(open);
        JButton jbtnSave = new JButton(save);
        jbtnClose = new JButton(close);
        jbtnFind = new JButton(find);
        jbtnAdd = new JButton(add);
        jbtnDelete = new JButton(delete);
        JButton jbtnExit = new JButton(exit);


        jbtnOpen.setToolTipText("Open file");
        jbtnSave.setToolTipText("Save file");
        jbtnClose.setToolTipText("Close file");
        jbtnFind.setToolTipText("Find in table");
        jbtnAdd.setToolTipText("Add to table");
        jbtnDelete.setToolTipText("Delete in table");
        jbtnExit.setToolTipText("Exit");

        jtbModifyDocument.setRollover(true);
        jtbModifyDocument.setFloatable(false);

        jtbModifyDocument.add(jbtnOpen);
        jtbModifyDocument.add(jbtnSave);
        jtbModifyDocument.add(jbtnClose);
        jtbModifyDocument.add(jbtnFind);
        jtbModifyDocument.add(jbtnAdd);
        jtbModifyDocument.add(jbtnDelete);
        jtbModifyDocument.add(jbtnExit);

        gbc.gridx = 0;
        gbc.gridy = 0;
        gridLayout.setConstraints(jtbModifyDocument, gbc);

        gbc.insets = new java.awt.Insets(4,4,4,4);

        gbc.insets = new java.awt.Insets(4,4,4,4);

        baseWindow.getContentPane().add(jtbModifyDocument);


        baseWindow.setJMenuBar(menuLine);
        baseWindow.setVisible(true);


        jmiOpen.addActionListener(new OpenListener(this));
        jbtnOpen.addActionListener(new OpenListener(this));
        jmiExit.addActionListener(new ExitListener());
        jbtnExit.addActionListener(new ExitListener());
        jbtnAdd.addActionListener(new AddDialogListener(this));
        jbtnFind.addActionListener(new FindDialogListener(this));
        jbtnDelete.addActionListener(new DeleteDialogListener(this));
        jmiAdd.addActionListener(new AddDialogListener(this));
        jmiFind.addActionListener(new FindDialogListener(this));
        jmiDelete.addActionListener(new DeleteDialogListener(this));
        jbtnSave.addActionListener(new SaveListener(this));
        jmiSave.addActionListener(new SaveListener(this));
        jbtnClose.addActionListener(new CloseListener(this));

    }
    public void AddTabbleToMainWindow (){
        gbc.gridx = 0;
        gbc.gridy = 1;
        gridLayout.setConstraints(tableOfStations.jtbHeader, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gridLayout.setConstraints(tableOfStations.tableOfStations, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        gridLayout.setConstraints(tableOfStations.jtbMove, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        gridLayout.setConstraints(tableOfStations.information, gbc);

        gbc.gridx = 0;
        gbc.gridy = 5;
        gridLayout.setConstraints(tableOfStations.numberOfReserved, gbc);

        gbc.gridx = 0;
        gbc.gridy = 6;
        gridLayout.setConstraints(tableOfStations.allPages, gbc);

        gbc.gridx = 0;
        gbc.gridy = 7;
        gridLayout.setConstraints(tableOfStations.numberOfPage, gbc);

        baseWindow.getContentPane().add(tableOfStations.information);
        baseWindow.getContentPane().add(tableOfStations.jtbHeader);
        baseWindow.getContentPane().add(tableOfStations.tableOfStations);
        baseWindow.getContentPane().add(tableOfStations.jtbMove);
        baseWindow.getContentPane().add(tableOfStations.numberOfReserved);
        baseWindow.getContentPane().add(tableOfStations.numberOfPage);
        baseWindow.getContentPane().add(tableOfStations.allPages);
    }

    public void addTable(){
        gbc.gridx = 0;
        gbc.gridy = 2;
        gridLayout.setConstraints(tableOfStations.tableOfStations, gbc);
        baseWindow.getContentPane().add(tableOfStations.tableOfStations);
    }

    public void updateTrainInfo(){
        tableOfStations.numberOfReserved.setText("Общее количество занятых строк: " + tableOfStations.reserved);
    }

    public void updatePages(){
        tableOfStations.allPages.setText("Общее количество страниц: " + tableOfStations.pages);
    }

    public void updatePage(){
        tableOfStations.numberOfPage.setText("Текущая страница: " + tableOfStations.page);
    }

    public void setDateTable(TrainList newTrainList){
        tableOfStations.setData(newTrainList);
    }

    public void update() {
        List<Train> trains = controller.getTrains();
        tableOfStations.update(trains);
        tableOfStations.setData(tableOfStations.trainList);
        updateTrainInfo();
        tableOfStations.countPages();
        updatePages();
        updatePage();
        baseWindow.validate();
    }

    public void setNumberOfReserved(List<Train> trainList){
        tableOfStations.reserved = trainList.size();
    }

    public TrainList getTrainList() {
        return tableOfStations.getTrainList();
    }
}

