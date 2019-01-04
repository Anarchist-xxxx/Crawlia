package app;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class Main extends Application{
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader;
        loader = new FXMLLoader(getClass().getResource("frame.fxml"));
        Parent root = loader.load();

        Controller controller = (Controller)loader.getController();
        controller.setThisStage(primaryStage);
        controller.runTaskTray();
        controller.runPandoLiA();

        primaryStage.setTitle("Clawlia");

        //アイコンを設定するよ
        try {
            Image icon = new Image(getClass().getResourceAsStream("/icon.png"));
            primaryStage.getIcons().add(icon);
        } catch(Exception e ) {
            e.printStackTrace();
        }

        primaryStage.setScene(new Scene(root));
        Platform.setImplicitExit(false);

        primaryStage.setOnCloseRequest((WindowEvent event) -> {
            primaryStage.hide();
        });

        primaryStage.show();
    }
}
