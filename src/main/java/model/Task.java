package model;


import utils.Observable;
import utils.Observer;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * class that represents the task of sorting arrays
 */
public class Task implements Runnable, Observable {

    private int[] array;
    private SortRecord sortRecord;
    private List<Observer> listObserver;

    public Task(SortType type, int[] array) {
        this.array = array;
        sortRecord = new SortRecord(type);
        listObserver = new ArrayList<>();
    }


    @Override
    public void run() {

        LocalDateTime debut = LocalDateTime.now();
        sortRecord.sort(array);
        LocalDateTime fin = LocalDateTime.now();
        Duration duree = Duration.between(debut, fin);
        sortRecord.setNbOperation(sortRecord.getNbOperation());
        sortRecord.setDuration(duree.toMillis());
        sortRecord.setPas(array.length);
        notifyObservers("end sort");

    }

    public SortRecord getSortRecord() {
        return sortRecord;
    }

    @Override
    public void addOberver(Observer obs) {
        listObserver.add(obs);
    }

    @Override
    public void removeObserver(Observer obs) {
        listObserver.remove(obs);
    }


    @Override
    public void notifyObservers(String notify) {
        for (Observer observer : listObserver) {
            if (notify.equals("end sort")) {
                observer.update(sortRecord);
            }else if(notify.equals("end time")){
                observer.update(LocalDateTime.now());
            }
        }
    }
}
