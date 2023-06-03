package view;

import controller.Controller;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.SortRecord;
import utils.Observer;

import java.io.IOException;
import java.time.LocalDateTime;

public class MyView implements Observer {

    private FxmlController fxmlController;

    public MyView(Stage stage) throws IOException {
        stage.setTitle("Sorting Race");
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/sort.fxml"));
        VBox root = loader.load();
        fxmlController = loader.getController();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }


    @Override
    synchronized public void update(Object args) {

        if (args.getClass() == SortRecord.class) {
            SortRecord sr = (SortRecord) args;
            fxmlController.addSortRecord(sr);
        }else if(args.getClass() == LocalDateTime.class){
            fxmlController.setEndTime((LocalDateTime) args);
        }

    }

    public void setController(Controller controller) {
        fxmlController.setController(controller);
    }
}
