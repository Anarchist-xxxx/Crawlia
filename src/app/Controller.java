package app;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.stage.Window;
import model.Post5ch;
import model.Thread5ch;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    @FXML
    TableView<Thread5ch> crawlingList;

    @FXML
    TableColumn crawlingKey;

    @FXML
    TableColumn crawlingTitle;

    @FXML
    TextArea dbLog;

    private PandoLiA pandolia;

    private Stage thisStage;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        crawlingKey.setCellValueFactory(new PropertyValueFactory<Thread5ch, String>("key"));
        crawlingTitle.setCellValueFactory(new PropertyValueFactory<Thread5ch, String>("Title"));

        LogBridge bridge = new LogBridge();
        bridge.setCrawlingList(crawlingList);
        bridge.setDbLog(dbLog);

        pandolia = new PandoLiA();
        pandolia.setLogParser(bridge);
    }

    public void setThisStage(Stage stage) {
        this.thisStage = stage;
    }

    public void runTaskTray() {
        TaskTray tasktray = new TaskTray(thisStage);

        tasktray.run();
    }

    public void runPandoLiA() {
        Thread pt = new Thread(pandolia);
        pt.start();
    }
}
