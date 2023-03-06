package model;

import config.ConfigManager;
import model.dto.LogsDto;
import model.jdbc.LogsDao;
import model.repository.LogsRepository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Model {

    private List<Task> mythreads;
    private LogsRepository logsRepository;
    private History history;

    /**
     * Simple constructor of Model class
     */
    public Model() throws IOException {
        ConfigManager.getInstance().load();
        mythreads = new ArrayList<>();
        var dao = LogsDao.getInstance();
        LogsRepository repo = new LogsRepository(dao);
        this.history = new History(repo);
    }


    public void executeThread(int nbThread) {
        ExecutorService executor = Executors.newFixedThreadPool(nbThread);
        for (int i = 0; i < mythreads.size(); i++) {
            executor.execute(mythreads.get(i));
        }
        executor.shutdown();
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                while (!executor.isTerminated());
                mythreads.get(0).notifyObservers("end time");
            }
        });
        t.start();


    }


    public List<Task> createThreads(int size, SortType sortChoice) {
        int pas = size / 10;
        ArrayGenerator generator = new ArrayGenerator();
        for (int i = 0; i <= size; i += pas) {
            mythreads.add(new Task(sortChoice, generator.randomArray(i),logsRepository));
        }
        return mythreads;
    }

    public List<Task> getTasks() {
        return mythreads;
    }

    public void getHistoryList() {
       history.notifyObservers("getHistory");

    }
    public History getHistory(){
        return  history;
    }

}
