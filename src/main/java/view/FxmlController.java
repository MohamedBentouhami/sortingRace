package view;

import controller.Controller;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import model.SortRecord;
import model.SortType;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.ExecutionException;


public class FxmlController {

    @FXML
    private MenuItem aboutItem;

    @FXML
    private LineChart<?, ?> chart;

    @FXML
    private ChoiceBox<String> configurationChoice;

    @FXML
    private TableColumn<?, ?> durationCol;

    @FXML
    private Label leftStatus;

    @FXML
    private TableColumn<?, ?> nameCol;

    @FXML
    private ProgressBar progressBar;

    @FXML
    private MenuItem quitItem;

    @FXML
    private Label rightStatus;

    @FXML
    private TableColumn<?, ?> sizeCol;

    @FXML
    private ChoiceBox<String> sortChoice;

    @FXML
    private Button start;

    @FXML
    private TableColumn<?, ?> swapCol;

    @FXML
    private TableView table;

    @FXML
    private Spinner<Integer> threadSpinner;

    @FXML
    private Font x3;

    @FXML
    private Color x4;

    private LocalDateTime timeStart;

    private LocalDateTime timeEnd;

    private Controller controller;

    private ObservableList<SortRecord> list;
    static double progress;
    private XYChart.Series seriesBubble;
    private XYChart.Series seriesMerge;


    public FxmlController() {
        list = FXCollections.observableArrayList();
        progress = 0;

    }

    public void initialize() {
        leftStatus.setText("Thread actifs : " + Thread.activeCount());
        String[] sortType = {"tri bulle", "tri fusion"};
        String[] numberElement = {"Very easy : 0-100 | 10", "Easy : 0-1 000 | 100",
                "Moderate : 0-10 000 | 1 000", "Hard: 0-100 000 | 10 000"};
        sortChoice.getItems().addAll(sortType);
        configurationChoice.getItems().addAll(numberElement);
        sortChoice.getSelectionModel().selectFirst();
        configurationChoice.getSelectionModel().selectFirst();
        initializeTable();
        initializeSpinner();
        progressBar.setProgress(0);
        seriesBubble = new XYChart.Series();
        seriesMerge = new XYChart.Series();
        seriesBubble.setName("tri bulle");
        seriesMerge.setName("tri fusion");
    }


    public void initializeSpinner() {
        SpinnerValueFactory<Integer> value =
                new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 15);
        value.setValue(1);
        threadSpinner.setValueFactory(value);
    }

    public void initializeTable() {

        nameCol.setCellValueFactory(new PropertyValueFactory<>("type"));
        durationCol.setCellValueFactory(new PropertyValueFactory<>("duration"));
        swapCol.setCellValueFactory(new PropertyValueFactory<>("nbOperation"));
        sizeCol.setCellValueFactory(new PropertyValueFactory<>("pas"));
        table.setItems(list);
    }

    @FXML
    public void handle(ActionEvent event) throws InterruptedException, ExecutionException {
        timeStart = LocalDateTime.now();
        rightStatus.setVisible(false);
        var sort = getSortChoice(sortChoice.getValue());
        var size = getNbSize(configurationChoice.getValue());
        var nbThread = threadSpinner.getValue();
        controller.execute(sort, size, nbThread);
        progressBar.setProgress(0);

    }

    public SortType getSortChoice(String choice) {
        switch (choice) {
            case "tri bulle":
                return SortType.BUBBLE;
            case "tri fusion":
                return SortType.MERGE;
        }
        return null;
    }

    public int getNbSize(String choice) {
        switch (choice) {
            case "Very easy : 0-100 | 10":
                return 100;
            case "Easy : 0-1 000 | 100":
                return 1000;
            case "Moderate : 0-10 000 | 1 000":
                System.out.println("oui");
                return 10000;
            case "Hard: 0-100 000 | 10 000":
                return 100000;
        }

        return 0;
    }


    public void setController(Controller controller) {
        this.controller = controller;
    }

    /**
     * allows you to modify the graphical interface following the addition of a sortRecord
     *
     * @param record given following an array sort
     */
    public void addSortRecord(SortRecord record) {
        progress += 0.1;
        progressBar.setProgress(progress);

        Platform.runLater(() -> {
                    setNbActifThread(Thread.activeCount());
                    list.add(record);
                    table.setItems(list);
                    chart.setAnimated(false);
                    if (record.getType() == SortType.MERGE) {
                        XYChart.Data mergeData = new XYChart.Data
                                (record.getPas(), record.getNbOperation());
                        seriesMerge.getData().addAll(mergeData);
                    }
                    if (record.getType() == SortType.BUBBLE) {
                        XYChart.Data bubbleData = new XYChart.Data
                                (record.getPas(), record.getNbOperation());
                        seriesBubble.getData().add(bubbleData);
                    }
                    if (!chart.getData().isEmpty()) {
                        chart.getData().clear();
                    }
                    chart.setAnimated(true);
                    chart.getData().addAll(seriesBubble, seriesMerge);
                }
        );

    }

    public void setNbActifThread(int nbActifThread) {
        leftStatus.setText("Thread actifs : " + nbActifThread);

    }

    public void setEndTime(LocalDateTime args) {
        timeEnd = args;
        Duration duree = Duration.between(timeStart, timeEnd);
        long d = duree.toMillis();
        Platform.runLater(() -> {
            rightStatus.setText("Derniere execution | Debut : " + dataFormater(timeStart) + "- " +
                    "Fin : " + dataFormater(timeEnd) + "- Duree : Duration : " + d + "ms");
            rightStatus.setVisible(true);
        });
    }

    public String dataFormater(LocalDateTime time) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        return time.format(dateTimeFormatter);
    }

}
