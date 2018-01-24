package com.fomenko.webmplayer.api.controller;

import com.fomenko.webmplayer.api.Dvach;

import java.io.*;
import java.util.ArrayList;
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

    public boolean fileSave(ArrayList<String> list, String typeFiles){
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
            return true;
        }
        catch(IOException ex){
            System.out.println(ex.getMessage());
            return false;
        }
    }

    public boolean tempFileSave(ArrayList<String> list, String board){

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
            return true;
        }
        catch(IOException ex){
            System.out.println(ex.getMessage());
            return false;
        }
    }

    public boolean fileOpen(String nameBoard) {
        File folder = new File( "temp");
        if (!folder.exists()) {
            folder.mkdir();
        }
        String pathName = "temp/"+nameBoard+".txt";
        File file = new File(pathName);
        if (file.exists()){
            System.out.println("Файл существует");
            return true;
        }else {
            System.out.println("Файла нет");
            try {
                FileWriter writer=new FileWriter(pathName, false);
                writer.write("");
                writer.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return false;
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



}
