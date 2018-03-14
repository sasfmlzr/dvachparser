package com.fomenko.webmplayer.api.controller;

import com.fomenko.webmplayer.api.Dvach;
import com.fomenko.webmplayer.api.model.UrlDvach;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("Duplicates")
public final class HttpOperation extends AbstractController {

    public HttpOperation(Dvach dvach) {
        super(dvach);
    }

    public String getRequest(String url) {
        URL obj;
        try {
            obj = new URL(url);

            HttpURLConnection connection = (HttpURLConnection) obj.openConnection();
            connection.setRequestMethod("GET");
            if(connection.getResponseCode()!=404){
                InputStreamReader inputStreamReader = new InputStreamReader(connection.getInputStream());
                BufferedReader in = new BufferedReader(inputStreamReader);
                String inputLine;
                StringBuilder response = new StringBuilder();
                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();
                return response.toString();
            }
            return "404";

        } catch (IOException e) {
            getDvach().log.error("Итератор сбился");
            e.printStackTrace();
        }
        return "Ошибка запроса";
    }
    public String getRequest(String url, String cookie) {
        URL obj;
        try {
            obj = new URL(url);
            HttpURLConnection connection = (HttpURLConnection) obj.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Cookie",cookie);
            if(connection.getResponseCode()!=404){
                InputStreamReader inputStreamReader = new InputStreamReader(connection.getInputStream());
                BufferedReader in = new BufferedReader(inputStreamReader);
                String inputLine;
                StringBuilder response = new StringBuilder();
                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();
                return response.toString();
            }
            return "404";

        } catch (IOException e) {
            getDvach().log.error("Итератор сбился");
            e.printStackTrace();
        }
        return "Ошибка запроса";
    }

    public  void downloadFromTXT() throws IOException {

        List<UrlDvach> listUrlDvach;
        listUrlDvach = getDvach().fileOperation().loadUrlFromFile();
        getDvach().log.info("Всего файлов:" + listUrlDvach.size());
        int count = 0;
        for (UrlDvach urlDvach:listUrlDvach){
            count++;
            getDvach().log.info("Файл "+urlDvach.getFile() + " №" + count + " начал загрузку");
            URL website = urlDvach.getUrl();
            URLConnection urlConnection = website.openConnection();
            urlConnection.setRequestProperty("Cookie",getDvach().getDvachModel().getCookie());
            urlConnection.connect();
           // Object s =urlConnection.getContent();
            InputStream s =urlConnection.getInputStream();
            ReadableByteChannel rbc = Channels.newChannel(s);
            FileOutputStream fos = new FileOutputStream("downloaded/"+urlDvach.getFile());
            fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
            getDvach().log.info("Успешно");
        }
    }
}
