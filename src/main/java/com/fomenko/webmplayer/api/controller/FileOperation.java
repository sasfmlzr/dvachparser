package com.fomenko.webmplayer.api.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fomenko.webmplayer.api.Dvach;
import com.fomenko.webmplayer.api.model.UrlDvach;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

public final class FileOperation extends AbstractController {

    public FileOperation(Dvach dvach) {
        super(dvach);
    }

    public boolean fileSave(ArrayList<String> list){
        try(FileWriter writer = new FileWriter("files.txt", true))
        {
            // запись всей строки
            StringBuilder text = new StringBuilder();
            for (int countMessages=0; countMessages<=list.size()-1;countMessages++){
                text.append(list.get(countMessages)).append("\r\n");
            }
            writer.write(text.toString());
            // запись по символам
            writer.append('\n');
            writer.flush();
            return true;
        }
        catch(IOException ex){
            System.out.println(ex.getMessage());
            return false;
        }
    }

    public void fileSave(List<String> list, String typeFiles){
        try(FileWriter writer = new FileWriter("files.txt", true))
        {
            // запись всей строки
            StringBuilder text = new StringBuilder();
            for (int countMessages=0; countMessages<=list.size()-1;countMessages++){
                for(String typeFile : Objects.requireNonNull(super.getDvach().getDvachModel().getFilterFile(typeFiles)))
                if (list.get(countMessages).contains(typeFile))
                text.append(list.get(countMessages)).append("\r\n");
            }
            writer.write(text.toString());
            // запись по символам
            writer.append('\n');
            writer.flush();
        }
        catch(IOException ex){
            System.out.println(ex.getMessage());
        }
    }

    public void tempFileSave(HashMap<String,String> hashMap, String board){
        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT);

        try {
            // Здесь происходит самая главная магия
            HashMap<String,String> jsonMap = fileOpenToFindJson(board);
            jsonMap.putAll(hashMap);
            mapper.writeValue(new File("temp/"+board+".json"), jsonMap);
            getDvach().log.info("Запись в " + "temp/"+board+".json " + "успешна");
        } catch(IOException exc) {
            exc.printStackTrace();
        }
    }




    public void tempFileSave(ArrayList<String> list, String board){

        String pathName = "temp/"+board+".txt";
        try(FileWriter writer = new FileWriter(pathName, true))
        {
            StringBuilder text = new StringBuilder();
            for (int countMessages=0; countMessages<=list.size()-1;countMessages++){
                text.append(list.get(countMessages)).append("\r\n");
            }
            writer.write(text.toString());
            writer.append('\n');
            writer.flush();
            System.out.println("Запись в " + pathName + "успешна");
        }
        catch(IOException ex){
            System.out.println(ex.getMessage());
        }
    }

    public void fileOpen(String nameBoard) {
        File folder = new File( "temp");
        if (!folder.exists()) {
            folder.mkdir();
        }
        String pathName = "temp/"+nameBoard+".txt";
        File file = new File(pathName);
        if (file.exists()){
            System.out.println("Файл существует");
        }else {
            System.out.println("Файла нет");
            try {
                FileWriter writer=new FileWriter(pathName, false);
                writer.write("");
                writer.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void fileOpenJson(String nameBoard) {
        File folder = new File( "temp");
        if (!folder.exists()) {
            folder.mkdir();
        }
        String pathName = "temp/"+nameBoard+".json";
        File file = new File(pathName);
        if (file.exists()){
            getDvach().log.info("Файл существует");
        }else {
            getDvach().log.info("Файла нет");
            try {
                FileWriter writer=new FileWriter(pathName, false);
                //writer.write("");
                writer.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public ArrayList<String> fileOpenToFind(String nameBoard) {
        String pathName = "temp/"+nameBoard+".txt";
        File fileTXT = new File(pathName);
            ArrayList<String> link = new ArrayList<>();
            String strLine;
            try {
                BufferedReader br = new BufferedReader
                        (new InputStreamReader(new FileInputStream(fileTXT), "Windows-1251"));
                while ( (strLine=br.readLine())!=null) {
                    link.add(strLine);
                }
            } catch (IOException e) {

                e.printStackTrace();
            }
            return link;
    }

    public HashMap<String,String> fileOpenToFindJson(String nameBoard) {
        String pathName = "temp/"+nameBoard+".json";

        HashMap<String,String> link = new HashMap<>();
        ObjectMapper mapper = new ObjectMapper();
        // Для вывода с отступами
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        try {
            link = mapper.readValue(new File(pathName), HashMap.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return link;
    }

    public List<UrlDvach> loadUrlFromFile(){
        List<UrlDvach> urlDvachList = new ArrayList<>();
        File fileTXT = new File("files.txt");
        String strLine;
        try {
            BufferedReader br = new BufferedReader
                    (new InputStreamReader(new FileInputStream(fileTXT), "Windows-1251"));
            while ( (strLine=br.readLine())!=null) {
                urlDvachList.add(new UrlDvach(strLine));
            }
        } catch (IOException e) {

            e.printStackTrace();
        }
        return urlDvachList;
    }

}
