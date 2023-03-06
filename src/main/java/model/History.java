package model;

import model.jdbc.LogsDao;
import model.repository.LogsRepository;
import utils.Observable;
import utils.Observer;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class History implements Observable {

    private List<Observer> listObserver;
    private LogsRepository repository;

    public History(LogsRepository repo) {
        listObserver = new ArrayList<>();
        repository = repo;
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

             if (notify.equals("getHistory")) {
                observer.update(repository.getAll());
            }
        }
    }
}
