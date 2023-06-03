package main;

import controller.Controller;
import javafx.application.Application;
import javafx.stage.Stage;
import model.Model;
import view.MyView;

/**
 * the class that launches the sorting race application,
 * which makes it possible to visualise the complexity of sorting algorithms
 */
public class App extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        MyView view = new MyView(stage);
        Model model = new Model();
        Controller controller = new Controller(view, model);
        view.setController(controller);

    }

}
