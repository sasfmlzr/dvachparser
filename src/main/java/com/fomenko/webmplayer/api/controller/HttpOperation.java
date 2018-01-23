package com.fomenko.webmplayer.api.controller;

import com.fomenko.webmplayer.api.Dvach;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public final class HttpOperation extends Dvach {

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
            e.printStackTrace();
            System.out.println("Итератор сбился");
        }
        return "Ошибка запроса";
    }

}
