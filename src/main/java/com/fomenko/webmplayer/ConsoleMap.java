package com.fomenko.webmplayer;

import com.fomenko.webmplayer.api.Dvach;

import java.util.ArrayList;

public class ConsoleMap {

    public static void main(String[] args) {

        Dvach dvach = new Dvach();

        System.out.println("Создался элемент двача");

        String board = dvach.dvachModel().getBoard("b");

        System.out.println("Получили борду");

        String threads = dvach.dvachModel().getThreads(board);

        System.out.println("Получили список тредов");

        String response = dvach.httpOperation().getRequest(threads);

        System.out.println("Получили ответ");

        ArrayList<String> threadsList = dvach.parseDvach().getNumThread(response);

        System.out.println("Получили номера тредов");
        //ArrayList<String> listLinkVideo = dvach.parseDvach().getListLinkVideo(threadsList,board);
        ArrayList<String> listLinkVideo = dvach.parseDvach().getListLinkVideo(threadsList,board);

        System.out.println("Получили список файлов в тредах");
        ArrayList<String> result = dvach.parseDvach().findDownloadedVideo(listLinkVideo, board);

        System.out.println("Получили исключающие треды");
        dvach.fileOperation().fileSave(result, "video");

        System.out.println("Загрузили в файл результат");
    }

}
