package by.safonenko.bsuir.ppvis.model;

import java.util.ArrayList;
import java.util.List;
/**
 * Created by Admin on 03.05.2017.
 */
public class TrainList {

    List<Train> trains;

   public  TrainList(){
        trains = new ArrayList<>();
    }

    public void add(Train newTrain) {
        trains.add(newTrain);
    }

    public int getSize(){
        return  trains.size();
    }

    public List<Train> getTrains() {
        return trains;
    }

    public void update(List<Train> trains) {
        this.trains = trains;
    }

}
