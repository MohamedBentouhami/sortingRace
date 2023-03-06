package model;


import model.dto.LogsDto;
import model.repository.LogsRepository;
import utils.Observable;
import utils.Observer;

import java.sql.SQLException;
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
    private LogsRepository logsRepository;


    public Task(SortType type, int[] array, LogsRepository logsRepository) {
        this.array = array;
        sortRecord = new SortRecord(type);
        listObserver = new ArrayList<>();
        logsRepository = logsRepository;
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
        try {
            logsRepository.add(new LogsDto(0,LocalDateTime.now().toString(),
                    sortRecord.toString(),(int)sortRecord.getNbOperation()));
        } catch (SQLException e) {
            e.printStackTrace();
        }


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
            }else if(notify.equals("getHistory")){

                observer.update(logsRepository.getAll());
            }
        }
    }
}
