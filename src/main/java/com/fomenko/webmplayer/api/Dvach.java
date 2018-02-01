package com.fomenko.webmplayer.api;

import com.fomenko.webmplayer.api.controller.FileOperation;
import com.fomenko.webmplayer.api.controller.HttpOperation;
import com.fomenko.webmplayer.api.controller.ParseDvach;
import com.fomenko.webmplayer.api.model.DvachModel;

import java.util.ArrayList;
import java.util.HashMap;

public class Dvach {
    public Dvach(){
        dvachModel= new DvachModel(this);
    //    dvachModel = new DvachModel();
    }
    public ParseDvach parseDvach(){return new ParseDvach(this);}
    public HttpOperation httpOperation(){return new HttpOperation(this);}
    public FileOperation fileOperation(){return new FileOperation(this);}
    private DvachModel dvachModel;

    public DvachModel getDvachModel() {
        return dvachModel;
    }
/*  public DvachModel getDvachModel() {
        return dvachModel;
    }*/
    public void parseDvachAutomate(String board, String typeFileSave, String cookie){
        //String cookie = "usercode_auth=c4879607e360cf191118c33a66832583";
        this.getDvachModel().setCookie(cookie);

        this.getDvachModel().getBoard(board);     //Создался элемент двача

        String threads = this.getDvachModel().getThreads(board);   //Получили борду

        String response = this.httpOperation().getRequest(threads, cookie);     //Получили список тредов

        ArrayList<String> threadsList = this.parseDvach().getNumThread(response);      //Получили номера тредов

        HashMap<String,String> listLinkVideo = this.parseDvach().getListLinkVideoMd5(threadsList,board);    //Получили список файлов в тредах

        HashMap<String,String> result = this.parseDvach().findDownloadedVideo(listLinkVideo, board);    //Получили исключающие треды

        this.fileOperation().fileSave(new ArrayList<>(result.keySet()), typeFileSave); // video, image, all

        System.out.println("Загрузили в файл результат");
    }
}
