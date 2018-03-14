package com.fomenko.webmplayer.api.controller;

import com.fomenko.webmplayer.api.Dvach;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
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

}
