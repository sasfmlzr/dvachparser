package com.fomenko.webmplayer.controller;

import com.fomenko.webmplayer.Main;
import com.fomenko.webmplayer.api.Dvach;
import com.fomenko.webmplayer.api.controller.FileOperation;
import com.fomenko.webmplayer.api.controller.HttpOperation;
import com.fomenko.webmplayer.api.controller.ParseDvach;
import com.fomenko.webmplayer.api.model.DvachModel;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.sun.jna.Memory;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.image.PixelFormat;
import javafx.scene.image.PixelWriter;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.web.WebView;
import uk.co.caprica.vlcj.component.EmbeddedMediaPlayerComponent;
import uk.co.caprica.vlcj.discovery.NativeDiscovery;
import uk.co.caprica.vlcj.player.direct.BufferFormat;
import uk.co.caprica.vlcj.player.direct.DefaultDirectMediaPlayer;
import uk.co.caprica.vlcj.player.embedded.DefaultAdaptiveRuntimeFullScreenStrategy;
import uk.co.caprica.vlcj.player.embedded.EmbeddedMediaPlayer;
import uk.co.caprica.vlcj.player.embedded.FullScreenStrategy;

import java.net.URL;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class MainWindowController extends AnchorPane implements Initializable {
    public final static String fxmlPath="/com/fomenko/webmplayer/views/MainWindow.fxml";

    @FXML
    private AnchorPane root;
    @FXML
    private MediaView mediaView;
    @FXML
    private Button back, next, download;
    @FXML
    private
    WebView embeddedWV;
    @FXML private Canvas canvas;
    private static int countVideo=0;
    private MediaPlayer player;
    private Media m;
    private ArrayList<String>listLinkVideo;


    EmbeddedMediaPlayerComponent mediaPlayerComponent;


    public EmbeddedMediaPlayerComponent mediaPlayerComponent() {
        return mediaPlayerComponent;
    }

    PixelWriter pixelWriter;
    PixelFormat pixelFormat;
    public MainWindowController(){


    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {


     //   listLinkVideo = null;
     //   listLinkVideo = runRequest();
    //    assert listLinkVideo != null;
     //   m = new Media("https://2ch.hk"+listLinkVideo.get(countVideo));

       // m = new Media("video.mp4");

      //  player = new MediaPlayer(m);
      //  mediaView.setMediaPlayer(player);
      //  player.play();



     //
      //  embeddedMediaPlayer.setFullScreenStrategy(new VlcjPlayerFullScreenStrategy(canvas));


    }

    @FXML
    private void playVideo(){
        pixelWriter = canvas.getGraphicsContext2D().getPixelWriter();
        pixelFormat = PixelFormat.getByteBgraInstance();
        mediaPlayerComponent = new EmbeddedMediaPlayerComponent();
        EmbeddedMediaPlayer embeddedMediaPlayer = mediaPlayerComponent.getMediaPlayer();
       // embeddedMediaPlayer.setFullScreenStrategy(   new DefaultAdaptiveRuntimeFullScreenStrategy(Main.getWindow()));

        mediaPlayerComponent.getMediaPlayer().playMedia("video.mp4");

    }





    @FXML
    private void setBackVideo(){

        countVideo--;
        System.out.println("--");
        m = new Media("https://2ch.hk"+listLinkVideo.get(countVideo));
        player = new MediaPlayer(m);
        mediaView.setMediaPlayer(player);
        player.play();
    }
    @FXML
    private void setNextVideo(){
        countVideo++;
        System.out.println("++");
        player.stop();
        m = new Media("https://2ch.hk"+listLinkVideo.get(countVideo));
        player = new MediaPlayer(m);
        mediaView.setMediaPlayer(player);
        player.play();


    }
    @FXML
    private void downloadVideo(){
        System.out.println(player.getStatus());
        System.out.println(player.getError());
    }









}
