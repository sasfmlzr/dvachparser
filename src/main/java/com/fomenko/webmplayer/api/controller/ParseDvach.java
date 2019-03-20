package com.fomenko.webmplayer.api.controller;

import com.fomenko.webmplayer.api.Dvach;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public final class ParseDvach extends AbstractController {
    private JsonParser parser;

    public ParseDvach(Dvach dvach) {
        super(dvach);
    }

    public  ArrayList<String> getNumThread(String response){
        parser = new JsonParser();
        JsonObject mainObjects = new JsonObject();
        try {
            mainObjects = parser.parse(response).getAsJsonObject();
        } catch (IllegalStateException e){
            e.printStackTrace();
        }

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
            String request;
            if (super.getDvach().getDvachModel().getCookie()==null){
                request=super.getDvach().httpOperation().getRequest(current_thread);
            }else {
                request=super.getDvach().httpOperation().getRequest(current_thread, super.getDvach().getDvachModel().getCookie());
            }
            if (!request.equals("404")){
            JsonArray pItem = parser.parse(request).getAsJsonObject().getAsJsonArray("threads").get(0).
                    getAsJsonObject().getAsJsonArray("posts");
            for (JsonElement videoFilesPath : pItem) {
                JsonArray videoFilePath = videoFilesPath.getAsJsonObject().get("files").getAsJsonArray();
                if (videoFilePath.size()>0){
                    listLinkVideo.add("https://2ch.hk"+videoFilePath.get(0).getAsJsonObject().get("path").getAsString() );
                }
            }
            }
        }
        parser=null;
        return listLinkVideo;
    }

    public ArrayList<String> findDownloadedVideo(ArrayList<String> downloadLinkListVideo, String board){
        ArrayList<String> result = new ArrayList<>();
        ArrayList<String> tempLink;
        super.getDvach().fileOperation().fileOpen(board);
            tempLink = super.getDvach().fileOperation().fileOpenToFind(board);
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
        super.getDvach().fileOperation().tempFileSave(result,board);
        return result;
    }

    public HashMap<String, String> findDownloadedVideo(HashMap<String, String> downloadLinkListVideo, String board){
        HashMap<String, String> result = new HashMap<>();
        HashMap<String, String> tempLink;
        super.getDvach().fileOperation().fileOpenJson(board);
        tempLink = super.getDvach().fileOperation().fileOpenToFindJson(board);
        boolean add=false;

            for (Map.Entry<String, String> entryDownload: downloadLinkListVideo.entrySet()){
                add=false;
                for (Map.Entry<String, String> entryTempLink: tempLink.entrySet()) {
                    if (entryDownload.getKey().equals(entryTempLink.getKey()))
                    {
                        add=true;
                    }

                    if (add)
                        break;
                }
                if (!add)
                    result.put(entryDownload.getKey(),entryDownload.getValue());
            }
        super.getDvach().fileOperation().tempFileSave(result,board);
        return result;
    }

    public HashMap<String, String> getListLinkVideoMd5(ArrayList<String> threadsList, String board){
        HashMap<String, String> listLinkVideo = new HashMap<>();
        for (int i=0;i<=threadsList.size()-1;i++){
            String current_thread = "https://2ch.hk/"+board+"/res/"+threadsList.get(i)+".json";
            parser = new JsonParser();
            String request;
            if (super.getDvach().getDvachModel().getCookie()==null){
                request=super.getDvach().httpOperation().getRequest(current_thread);
            }else {
                request=super.getDvach().httpOperation().getRequest(current_thread, super.getDvach().getDvachModel().getCookie());
            }
            if (!request.equals("404")){
                JsonArray pItem = parser.parse(request).getAsJsonObject().getAsJsonArray("threads").get(0).
                        getAsJsonObject().getAsJsonArray("posts");
                for (JsonElement videoFilesPath : pItem) {
                    JsonArray videoFilePath = videoFilesPath.getAsJsonObject().get("files").getAsJsonArray();
                    if (videoFilePath.size()>0){
                        if (videoFilePath.get(0).getAsJsonObject().has("md5"))
                        listLinkVideo.put("https://2ch.hk"+videoFilePath.get(0).getAsJsonObject().get("path").getAsString(),videoFilePath.get(0).getAsJsonObject().get("md5").getAsString() );
                    }
                }
            }
        }
        parser=null;
        return listLinkVideo;
    }
}
