package com.fomenko.webmplayer;

import com.fomenko.webmplayer.controller.MainWindowController;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.Window;
import uk.co.caprica.vlcj.binding.LibVlc;
import uk.co.caprica.vlcj.discovery.NativeDiscovery;

import java.io.IOException;

import static com.fomenko.webmplayer.controller.MainWindowController.fxmlPath;
public class Main extends Application {

    AnchorPane root;
    public Scene scene;

    public Window getWindow(){
        return scene.getWindow();
    }

    @Override
    public void start(Stage primaryStage) {
      /*  AnchorPane root;
        try {
            root = FXMLLoader.load(getClass().getResource(fxmlPath));
        } catch (IOException e) {
            System.out.println("FXML view не загрузилась: " + fxmlPath);
            e.printStackTrace();
        }
        assert root != null;

*/

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(MainWindowController.class.getResource(fxmlPath));
        try {
            root = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        scene = new Scene(root);
        scene.setRoot(root);



        primaryStage = new Stage();
        primaryStage.setScene(scene);
        primaryStage.setMinWidth(1000);
        primaryStage.setMinHeight(900);
        primaryStage.setTitle("Message parser");
        primaryStage.show();

        primaryStage.setOnCloseRequest(event -> {
            Platform.exit();
            System.exit(0);
        });
    }


    public static void main(final String[] args) {
        boolean found = new NativeDiscovery().discover();
        System.out.println(found);
      //  System.out.println(LibVlc.INSTANCE.libvlc_get_version());

        Application.launch(args);
    }







}
