package com.fomenko.webmplayer.api.controller;

import com.fomenko.webmplayer.api.Dvach;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.util.ArrayList;

public final class ParseDvach extends Dvach {
    private JsonParser parser;
    public  ArrayList<String> getNumThread(String response){
        parser = new JsonParser();
        JsonObject mainObjects = parser.parse(response).getAsJsonObject();
        ArrayList<String> threadsList= new ArrayList<>();
        JsonArray pItems = mainObjects.getAsJsonArray("threads");
        for (int i=0; i<=pItems.size()-1;i++){
            threadsList.add(pItems.get(i).getAsJsonObject().get("num").getAsString()) ;
        }
        parser=null;
        return threadsList;
    }

    public ArrayList<String> getListLinkVideo(ArrayList<String> threadsList, String board){
        ArrayList<String> listLinkVideo = new ArrayList<>();
        for (int i=0;i<=threadsList.size()-1;i++){
            String current_thread = "https://2ch.hk/"+board+"/res/"+threadsList.get(i)+".json";
            parser = new JsonParser();
            JsonArray pItem = parser.parse(super.httpOperation().getRequest(current_thread)).getAsJsonObject().getAsJsonArray("threads").get(0).
                    getAsJsonObject().getAsJsonArray("posts");
            for (JsonElement videoFilesPath : pItem) {
                JsonArray videoFilePath = videoFilesPath.getAsJsonObject().get("files").getAsJsonArray();
                if (videoFilePath.size()>0){
                    listLinkVideo.add("https://2ch.hk"+videoFilePath.get(0).getAsJsonObject().get("path").getAsString() );
                }
            }
        }
        parser=null;
        return listLinkVideo;
    }


    public ArrayList<String> findDownloadedVideo(ArrayList<String> downloadLinkListVideo, String board){
        ArrayList<String> result = new ArrayList<>();
        ArrayList<String> tempLink;
        if (super.fileOperation().fileOpen(board)){
            tempLink = super.fileOperation().fileOpenToFind(board);
            boolean add=false;
            for (int i=0; i<=downloadLinkListVideo.size()-1; i++){
                add=false;
                for (int j=0; j<=tempLink.size()-1;j++){

                    if (downloadLinkListVideo.get(i).equals(tempLink.get(j)))
                    {
                        add=true;
                    }

                    if (add)
                        break;

                }
                if (!add)
                result.add(downloadLinkListVideo.get(i));
            }
            super.fileOperation().tempFileSave(result,board);
        }
        return result;
    }







}
