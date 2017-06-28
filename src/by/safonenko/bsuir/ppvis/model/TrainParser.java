package by.safonenko.bsuir.ppvis.model;

import java.util.ArrayList;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import javax.xml.parsers.SAXParser;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import  java.util.Calendar;

/**
 * Created by Admin on 17.04.2017.
 */
public class TrainParser {

    ArrayList<Train> currentTrainList;
    Train currentTrain;
    String tag;
    String dateAndTime;
    DateFormat format;
    Calendar cal;

    final String TRAIN = "train";
    final String TRAIN_NUMBER = "number";
    final String LEAVING_DATE = "leavingDate";
    final String LEAVING_TIME = "leavingTime";
    final String LEAVING_STATION = "leavingStation";
    final String ARRIVING_STATION = "arrivingStation";
    final String ARRIVING_DATE = "arrivingDate";
    final String ARRIVING_TIME = "arrivingTime";
    final String WAY_TIME = "wayTime";

    public TrainParser(){
        currentTrainList = new ArrayList<Train>();
        currentTrain = new Train();
    }

    public ArrayList<Train> parse(String path, SAXParser parser) throws Exception {
        parser.parse(path, new DefaultHandler() {

            public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
                tag = qName;
                switch (tag) {
                    case TRAIN:
                        currentTrain = new Train();
                       currentTrainList.add(currentTrain);
                        break;
                }

            }

            public void characters(char ch[], int start, int length) throws SAXException {
                switch (tag) {
                    case TRAIN_NUMBER:
                        currentTrain.trainNumber = Integer.valueOf(new String(ch, start, length));
                        break;
                    case LEAVING_STATION:
                        currentTrain.leavingStation = new String(ch, start, length);
                        break;
                    case ARRIVING_STATION:
                        currentTrain.arrivingStation = new String(ch, start, length);
                        break;
                    case LEAVING_DATE:
                        dateAndTime = new String(ch, start, length);
                        format = new SimpleDateFormat("dd.MM.yy");
                        try {
                            currentTrain.leavingDate = format.parse(dateAndTime);
                        } catch (java.text.ParseException e) {
                            e.printStackTrace();
                        }
                        break;
                    case LEAVING_TIME:
                        dateAndTime = new String(ch, start, length);
                        format = new SimpleDateFormat("HH:mm");
                        try {
                            currentTrain.leavingTime = format.parse(dateAndTime);
                        } catch (java.text.ParseException e) {
                            e.printStackTrace();
                        }
                        cal = java.util.Calendar.getInstance();
                        cal.setTime(currentTrain.leavingTime);
                        clearDate(cal);
                        currentTrain.leavingTime = cal.getTime();
                        break;
                    case ARRIVING_DATE:
                        dateAndTime = new String(ch, start, length);
                        format = new SimpleDateFormat("dd.MM.yy");
                        try {
                            currentTrain.arrivingDate = format.parse(dateAndTime);
                        } catch (java.text.ParseException e) {
                            e.printStackTrace();
                        }
                        break;
                    case ARRIVING_TIME:
                        dateAndTime = new String(ch, start, length);
                        format = new SimpleDateFormat("HH:mm");
                        try {
                            currentTrain.arrivingTime = format.parse(dateAndTime);
                        } catch (java.text.ParseException e) {
                            e.printStackTrace();
                        }
                        cal = java.util.Calendar.getInstance();
                        cal.setTime(currentTrain.arrivingTime);
                        clearDate(cal);
                        currentTrain.arrivingTime = cal.getTime();
                        break;

                    case WAY_TIME:
                        String time = new String(ch, start, length);
                        format = new java.text.SimpleDateFormat("HH:mm");
                        try {
                            currentTrain.wayTime = format.parse(time);
                        } catch (java.text.ParseException e) {
                            e.printStackTrace();
                        }
                        cal = java.util.Calendar.getInstance();
                        cal.setTime(currentTrain.wayTime);
                        clearDate(cal);
                        currentTrain.wayTime = cal.getTime();
                        break;
                }
            }

            private void clearDate(Calendar cal){
                cal.clear(java.util.Calendar.DAY_OF_MONTH);
                cal.clear(java.util.Calendar.DAY_OF_WEEK);
                cal.clear(java.util.Calendar.MONTH);
                cal.clear(java.util.Calendar.YEAR);
            }

            public void endElement(String uri, String localName, String qName) throws SAXException {
                tag = "";
            }

            public void startDocument() throws SAXException {
            }

            public void endDocument() throws SAXException {
            }
        });
        return currentTrainList;
    }
}