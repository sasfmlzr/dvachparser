package com.fomenko.webmplayer;

import com.fomenko.webmplayer.api.Dvach;

import java.util.ArrayList;
import java.util.HashMap;

public class ConsoleMap {

    public static void main(String[] args) {
        String myCookie = "usercode_auth=c4879607e360cf191118c33a66832583";
        Dvach dvach = new Dvach();
        dvach.getDvachModel().setCookie(myCookie);
        System.out.println("Создался элемент двача");

        String board = dvach.getDvachModel().getBoard("b");

        System.out.println("Получили борду");

        String threads = dvach.getDvachModel().getThreads(board);

        System.out.println("Получили список тредов");

        String response = dvach.httpOperation().getRequest(threads, myCookie);

        System.out.println("Получили ответ");

        ArrayList<String> threadsList = dvach.parseDvach().getNumThread(response);

        System.out.println("Получили номера тредов");
        //ArrayList<String> listLinkVideo = dvach.parseDvach().getListLinkVideo(threadsList,board);
      //  ArrayList<String> listLinkVideo = dvach.parseDvach().getListLinkVideo(threadsList,board);
        HashMap<String,String> listLinkVideo = dvach.parseDvach().getListLinkVideoMd5(threadsList,board);

        System.out.println("Получили список файлов в тредах");
     //   ArrayList<String> result = dvach.parseDvach().findDownloadedVideo(listLinkVideo, board);
        HashMap<String,String> result = dvach.parseDvach().findDownloadedVideo(listLinkVideo, board);
        System.out.println("Получили исключающие треды");
        dvach.fileOperation().fileSave(new ArrayList<>(result.keySet()), "video"); // video, image, all
      //  dvach.fileOperation().fileSave(result);
        System.out.println("Загрузили в файл результат");
    }

}
