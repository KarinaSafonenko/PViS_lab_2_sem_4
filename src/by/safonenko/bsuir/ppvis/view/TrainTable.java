package by.safonenko.bsuir.ppvis.view;

import by.safonenko.bsuir.ppvis.model.Train;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JToolBar;
import javax.swing.table.JTableHeader;
import by.safonenko.bsuir.ppvis.model.TrainList;
import java.util.List;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.xml.transform.TransformerException;
import java.io.IOException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import  java.io.FileOutputStream;
import javax.xml.transform.Transformer;
import javax.xml.transform.OutputKeys;



/**
 * Created by Admin on 14.04.2017.
 */
public class TrainTable {
    public int kol = 10;
    public int reserved = 0;
    public int page = 1;
    public int pages = 1;
    private int numberOfTrains = 0;

    public JTable tableOfStations;
    JLabel information;
    JLabel numberOfReserved;
    JLabel numberOfPage;
    JLabel allPages;
    JTextField tableSize;
    JTableHeader jtbHeader;
    JToolBar jtbMove;
    JButton jbtnForward;
    JButton jbtnToTheEnd;
    JButton jbtnTotheBegining;
    JButton jbtnBack;
    JButton jbtnChangeRowsNumber;

    SimpleDateFormat formatForDate;
    SimpleDateFormat formatForTime;

    MainWindow mainWindow;

    TrainList trainList;

   public TrainTable(MainWindow mainWindow){
       trainList = new TrainList();
       this.mainWindow = mainWindow;
   }

   public void CreateTrainTable(){

       formatForDate = new SimpleDateFormat(" dd.MM.yyyy");
       formatForTime = new SimpleDateFormat(" HH:mm");

       String[] headings = {"Номер поезда", "Станция отправления", "Станция прибытия", "Дата и время отправления",
               "Дата и время прибытия", "Время в пути"};
       Object[][] info = new Object[kol][6];

       tableOfStations = new JTable(info, headings);
       information = new JLabel("Количество строк таблицы на странице: "+ kol);
       numberOfReserved = new JLabel("Общее количество занятых строк: " + reserved);
       numberOfPage = new JLabel("Текущая страница: " + page);
       allPages = new JLabel("Общее количество страниц: " + pages);
       jtbHeader = tableOfStations.getTableHeader();
       tableSize = new JTextField(10);

       ImageIcon forward = new ImageIcon("D:\\q\\Test\\MainWindow\\src\\by\\safonenko\\bsuir\\ppvis\\resorses\\forward.png");
       ImageIcon toTheEnd = new ImageIcon("D:\\q\\Test\\MainWindow\\src\\by\\safonenko\\bsuir\\ppvis\\resorses\\toTheEnd.png");
       ImageIcon toTheBegining = new ImageIcon("D:\\q\\Test\\MainWindow\\src\\by\\safonenko\\bsuir\\ppvis\\resorses\\toTheBegining.png");
       ImageIcon back = new ImageIcon("D:\\q\\Test\\MainWindow\\src\\by\\safonenko\\bsuir\\ppvis\\resorses\\back.png");

       jbtnForward = new JButton(forward);
       jbtnToTheEnd = new JButton(toTheEnd);
       jbtnTotheBegining = new JButton(toTheBegining);
       jbtnBack = new JButton(back);
       jbtnChangeRowsNumber = new JButton("Изменить количество записей на странице");

       jbtnForward.addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent e) {
               System.out.println("There");
               if (page != pages){
                   page++;
                   setData(trainList);
                   numberOfPage.setText("Текущая страница: " + page);
                   tableOfStations.updateUI();
               }
           }
       });

       jbtnBack.addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent e) {
               if (page != 1){
                   page--;
                   setData(trainList);
                   numberOfPage.setText("Текущая страница: " + page);
                   tableOfStations.updateUI();
               }
           }
       });

       jbtnTotheBegining.addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent e) {
               page=1;
               setData(trainList);
               numberOfPage.setText("Текущая страница: " + page);
               tableOfStations.updateUI();
           }
       });

       jbtnToTheEnd.addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent e) {
               page=pages;
               setData(trainList);
               numberOfPage.setText("Текущая страница: " + page);
               tableOfStations.updateUI();
           }
       });

       jbtnChangeRowsNumber.addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent e) {
               kol = Integer.parseInt(tableSize.getText());
               tableOfStations.setVisible(false);
               information.setText("Количество строк таблицы на странице: " + kol);
               updateTable();
               mainWindow.addTable();
               allPages.setText("Общее количество страниц: " + pages);
               tableOfStations.updateUI();
           }
       });


       jtbMove = new JToolBar();

       jtbMove.setRollover(true);
       jtbMove.setFloatable(false);

       jtbMove.add(jbtnTotheBegining);
       jtbMove.add(jbtnBack);
       jtbMove.add(jbtnForward);
       jtbMove.add(jbtnToTheEnd);
       jtbMove.add(jbtnChangeRowsNumber);
       jtbMove.add(tableSize);
   }
    public void setActionsForWindow(){
        jbtnForward.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("There");
                if (page != pages){
                    page++;
                    setData(trainList);
                    numberOfPage.setText("Текущая страница: " + page);
                    tableOfStations.updateUI();
                }
            }
        });

        jbtnBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (page != 1){
                    page--;
                    setData(trainList);
                    numberOfPage.setText("Текущая страница: " + page);
                    tableOfStations.updateUI();
                }
            }
        });

        jbtnTotheBegining.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                page=1;
                setData(trainList);
                numberOfPage.setText("Текущая страница: " + page);
                tableOfStations.updateUI();
            }
        });

        jbtnToTheEnd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                page=pages;
                setData(trainList);
                numberOfPage.setText("Текущая страница: " + page);
                tableOfStations.updateUI();
            }
        });

        jbtnChangeRowsNumber.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                kol = Integer.parseInt(tableSize.getText());
                tableOfStations.setVisible(false);
                information.setText("Количество строк таблицы на странице: " + kol);
                updateTable();
                tableOfStations.updateUI();
                allPages.setText("Общее количество страниц: " + pages);
                tableOfStations.updateUI();
            }
        });

    }

   public void updateTable(){
       String[] headings = {"Номер поезда", "Станция отправления", "Станция прибытия", "Дата и время отправления",
               "Дата и время прибытия", "Время в пути"};
       Object[][] info = new Object[kol][6];
       tableOfStations = new JTable(info, headings);
       setData(trainList);
       mainWindow.tableOfStations = this;
   }
    public void setData(TrainList trainList) {
        this.trainList = trainList;
        numberOfTrains = trainList.getSize();
        countPages();
        int firstTrainOnPage = (page-1)*kol;
        int lastTrainOnPage = page*kol-1;
        if (numberOfTrains <=lastTrainOnPage) lastTrainOnPage = numberOfTrains - 1;
        int bound = lastTrainOnPage - firstTrainOnPage;
        int firstRow;
        for (firstRow = 0; firstRow<=bound; firstRow++, firstTrainOnPage++){
            tableOfStations.setValueAt(trainList.getTrains().get(firstTrainOnPage).getTrainNumber(),firstRow, 0 );
            tableOfStations.setValueAt(trainList.getTrains().get(firstTrainOnPage).getLeavingStation(), firstRow, 1);
            tableOfStations.setValueAt(trainList.getTrains().get(firstTrainOnPage).getArrivingStation(), firstRow, 2);
            tableOfStations.setValueAt(formatForDate.format(trainList.getTrains().get(firstTrainOnPage).getLeavingDate()) + " " + formatForTime.format(trainList.getTrains().get(firstTrainOnPage).getLeavingTime()), firstRow, 3);
            tableOfStations.setValueAt(formatForDate.format(trainList.getTrains().get(firstTrainOnPage).getArrivingDate()) + " " + formatForTime.format(trainList.getTrains().get(firstTrainOnPage).getArrivingTime()), firstRow, 4);
            tableOfStations.setValueAt(formatForTime.format(trainList.getTrains().get(firstTrainOnPage).getWayTime()), firstRow, 5);
        }
        for (; firstRow<kol; firstRow++){
            tableOfStations.setValueAt(null, firstRow, 0 );
            tableOfStations.setValueAt(null, firstRow, 1);
            tableOfStations.setValueAt(null, firstRow, 2);
            tableOfStations.setValueAt(null, firstRow, 3);
            tableOfStations.setValueAt(null, firstRow, 4);
            tableOfStations.setValueAt(null, firstRow, 5);
        }
    }

    public void clear(){
        trainList.getTrains().clear();
        setData(trainList);
        mainWindow.update();
    }

   public void save(String path) throws TransformerException, IOException {
       writeParamXML(paramLangXML(), path);
   }

    public int deleteTrains (String station){
        ArrayList<Train> newTrainList = new ArrayList<>();
        newTrainList = findTrains(station);
        int kol = newTrainList.size();
        trainList.getTrains().removeAll(newTrainList);
        return  kol;
    }

    private javax.xml.parsers.DocumentBuilder paramLangXML() {
        DocumentBuilder builder = null;
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        try {
            builder = factory.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
        return builder;
    }

    private void writeParamXML(DocumentBuilder builder, String path) throws TransformerException, IOException {

        Document document = builder.newDocument();
        Element RootElement = document.createElement("trainList");

        for (Train train : trainList.getTrains()) {
            Element tableRow = document.createElement("train");

            Element trainNumber = document.createElement("number");
            trainNumber.appendChild(document.createTextNode(String.valueOf(train.getTrainNumber())));
            tableRow.appendChild(trainNumber);

            Element leavingStation = document.createElement("leavingStation");
            leavingStation.appendChild(document.createTextNode(train.getLeavingStation()));
            tableRow.appendChild(leavingStation);


            Element arrivingStation = document.createElement("arrivingStation");
            arrivingStation.appendChild(document.createTextNode(train.getArrivingStation()));
            tableRow.appendChild(arrivingStation);

            Element leavingDate = document.createElement("leavingDate");
            leavingDate.appendChild(document.createTextNode(String.valueOf(formatForDate.format(train.getLeavingDate()))));
            tableRow.appendChild(leavingDate);

            Element leavingTime = document.createElement("leavingTime");
            leavingTime.appendChild(document.createTextNode(String.valueOf(formatForTime.format(train.getLeavingTime()))));
            tableRow.appendChild(leavingTime);

            Element arrivingDate = document.createElement("arrivingDate");
            arrivingDate.appendChild(document.createTextNode(String.valueOf(formatForDate.format(train.getArrivingDate()))));
            tableRow.appendChild(arrivingDate);


            Element arrivingTime = document.createElement("arrivingTime");
            arrivingTime.appendChild(document.createTextNode(String.valueOf(formatForTime.format(train.getArrivingTime()))));
            tableRow.appendChild(arrivingTime);

            Element wayTime = document.createElement("wayTime");
            wayTime.appendChild(document.createTextNode(String.valueOf(formatForTime.format(train.getWayTime()))));
            tableRow.appendChild(wayTime);

            RootElement.appendChild(tableRow);
        }

        document.appendChild(RootElement);

       Transformer t = TransformerFactory.newInstance().newTransformer();
        t.setOutputProperty(OutputKeys.METHOD, "xml");
        t.setOutputProperty(OutputKeys.INDENT, "yes");
        t.transform(new DOMSource(document), new StreamResult(new FileOutputStream(path)));
    }

    public int deleteTrains (Date wayTime){
        ArrayList<Train> newTrainList = new ArrayList<>();
        newTrainList = findTrains(wayTime);
        int kol = newTrainList.size();
        trainList.getTrains().removeAll(newTrainList);
        return  kol;
    }

    public int deleteTrains (Date leavingDate, int trainNumber){
        ArrayList<Train> newTrainList = new ArrayList<>();
        newTrainList = findTrains(leavingDate, trainNumber);
        int kol = newTrainList.size();
        trainList.getTrains().removeAll(newTrainList);
        return  kol;
    }

    public int deleteTrains (Date leavingTimeFrom, Date leavingTimeTo, Date arrivingTimeFrom, Date arrivingTimeTo){
        ArrayList<Train> newTrainList = new ArrayList<>();
        newTrainList = findTrains(leavingTimeFrom, leavingTimeTo, arrivingTimeFrom, arrivingTimeTo);
        int kol = newTrainList.size();
        trainList.getTrains().removeAll(newTrainList);
        return  kol;
    }

    public ArrayList<Train> findTrains (String station){
        ArrayList<Train> newTrainList = new ArrayList<>();
        int listNumber = trainList.getSize();
        for (int i=0; i<listNumber; i++){
            if (trainList.getTrains().get(i).getLeavingStation().equals(station)) {
                newTrainList.add(trainList.getTrains().get(i));
            }
        }
        return  newTrainList;
    }

    public ArrayList<Train> findTrains (Date wayTime){
        ArrayList<Train> newTrainList = new ArrayList<>();
        int listNumber = trainList.getSize();
        for (int i=0; i<listNumber; i++){
            if (trainList.getTrains().get(i).getWayTime().equals(wayTime)) {
                newTrainList.add(trainList.getTrains().get(i));
            }
        }
        return  newTrainList;
    }

    public ArrayList<Train> findTrains (Date leavingDate, int trainNumber){
        ArrayList<Train> newTrainList = new ArrayList<>();
        int listNumber = trainList.getSize();
        for (int i=0; i<listNumber; i++){
            if (trainList.getTrains().get(i).getTrainNumber() == trainNumber && trainList.getTrains().get(i).getLeavingDate().equals(leavingDate)) {
                newTrainList.add(trainList.getTrains().get(i));
            }
        }
        return  newTrainList;
    }

    public  ArrayList<Train> findTrains (Date leavingTimeFrom, Date leavingTimeTo, Date arrivingTimeFrom, Date arrivingTimeTo){
        ArrayList<Train> newTrainList = new ArrayList<>();
        int listNumber = trainList.getSize();
        for (int i=0; i<listNumber; i++){
            if (trainList.getTrains().get(i).getLeavingTime().compareTo(leavingTimeFrom) > 0 && trainList.getTrains().get(i).getLeavingTime().compareTo(leavingTimeTo) < 0 &&
                    trainList.getTrains().get(i).getArrivingTime().compareTo(arrivingTimeFrom) > 0 &&  trainList.getTrains().get(i).getArrivingTime().compareTo(arrivingTimeTo) < 0) {
                newTrainList.add(trainList.getTrains().get(i));
            }
        }
        return  newTrainList;
    }

    public void countPages(){
        int numberOfNotes = trainList.getSize();
        double numberOfPages = (double) numberOfNotes / kol;
        pages = (int)Math.ceil(numberOfPages);
        if (pages == 0) pages=1;
    }

    public void update(List<Train> trains) {
        trainList.update(trains);
        reserved = trainList.getSize();
        if (reserved<kol) page = 1;
    }

    public TrainList getTrainList() {
        return trainList;
    }

    public void setTrainList(TrainList newTrainList){
        trainList = newTrainList;
    }

}
