package com.fomenko.webmplayer;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.net.URI;
import java.net.URL;
import java.nio.file.Path;

public class VideoTest extends Application {

    public static void main(String[] args) {
        Application.launch(args);
    }
    StackPane root;
    @Override
    public void start(Stage primaryStage) {

        root = new StackPane();

      //  Media m = new Media("file:///" + System.getProperty("user.dir").replace('\\', '/') + "/" + "video.mp4");
        Media m = new Media("https://2ch.hk/b/src/168535761/15156585956131.webm");
       System.out.println();
        MediaPlayer player = new MediaPlayer( m);
  //      MediaPlayer player = new MediaPlayer( new Media(getClass().getResource("video.mp4").toExternalForm()));
        MediaView mediaView = new MediaView(player);


        root.getChildren().add( mediaView);

        Scene scene = new Scene(root, 1024, 768);

        primaryStage.setScene(scene);
        primaryStage.show();


        player.play();


        primaryStage.setOnCloseRequest(event -> {
            Platform.exit();
            System.exit(0);
        });





    }

    File fileInputMessage;


    private void hndlOpenFile() {

        FileChooser fileChooser = new FileChooser();//Класс работы с диалогом выборки и сохранения
        fileChooser.setTitle("Open Document");//Заголовок диалога
        FileChooser.ExtensionFilter extFilter =
                new FileChooser.ExtensionFilter("Text files (*.txt)", "*.txt");//Расширение
        fileChooser.getExtensionFilters().add(extFilter);
        fileInputMessage = fileChooser.showOpenDialog(root.getScene().getWindow());//Указываем текущую сцену CodeNote.mainStage
        if (fileInputMessage != null) {
            //Open

            System.out.println("Файл открыт");
        }
    }


}