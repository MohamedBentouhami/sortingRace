package model;

import java.util.ArrayList;
import java.util.List;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Model {

    private List<Task> mythreads;

    /**
     * Simple constructor of Model class
     */
    public Model() {
        mythreads = new ArrayList<>();
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
            mythreads.add(new Task(sortChoice, generator.randomArray(i)));
        }
        return mythreads;
    }

    public List<Task> getTasks() {
        return mythreads;
    }
}
