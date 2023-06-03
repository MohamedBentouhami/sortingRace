package controller;

import model.Model;
import model.SortType;
import view.MyView;

import java.util.concurrent.ExecutionException;

public class Controller {

    private MyView view;
    private Model model;

    /**
     * Simple constructor of Controller class
     *
     * @param view
     * @param model
     */
    public Controller(MyView view, Model model) {
        this.view = view;
        this.model = model;
    }

    /**
     * add attributes tasks of model class as observer
     */
    public void addObserverToList() {

        for (int i = 0; i < model.getTasks().size(); i++) {
            model.getTasks().get(i).addOberver(view);
        }
    }

    /**
     * Execute the tasks of sorting array of given size using
     * the given number of threads
     *
     * @param sortChoice the type of sort given
     * @param size       the size of array
     * @param nbThread   le number of threads
     */
    public void execute(SortType sortChoice, int size, int nbThread) throws InterruptedException, ExecutionException {
        model.createThreads(size, sortChoice);
        addObserverToList();
        model.executeThread(nbThread);
    }
}
