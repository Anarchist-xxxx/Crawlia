package app;

import javafx.application.Platform;
import javafx.stage.Stage;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class TaskTray implements Runnable {

    Stage thisStage;

    public TaskTray(Stage stage) {
        this.thisStage = stage;
    }


    public void run() {
        try {
            //アイコンの用意！
            BufferedImage image = ImageIO.read(new File("icon.png"));
            TrayIcon icon = new TrayIcon(image);
            icon.setImageAutoSize(true);

            //メニューつくる
            PopupMenu menu = new PopupMenu();

            MenuItem openItem = new MenuItem("Open log window");
            openItem.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    Platform.runLater(
                            () -> thisStage.show()
                    );

                }
            });

            MenuItem mascotItem = new MenuItem("【*^▲^*】");
            mascotItem.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    new Mascot().start();
                }
            });

            MenuItem exitItem = new MenuItem("exit");
            exitItem.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    Platform.setImplicitExit(true);
                    Platform.exit();
                    System.exit(0);
                }
            });

            menu.add(openItem);
            menu.add(mascotItem);
            menu.addSeparator();
            menu.add(exitItem);
            icon.setPopupMenu(menu);

            SystemTray.getSystemTray().add(icon);

        } catch(IOException e) {
            e.printStackTrace();
        } catch(AWTException e) {
            e.printStackTrace();
        }
    }
}
