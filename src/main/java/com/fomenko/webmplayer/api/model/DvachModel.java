package com.fomenko.webmplayer.api.model;

import com.fomenko.webmplayer.api.Dvach;

public final class DvachModel extends Dvach {

    public enum boardEnum{
        b,fet,hc,e,a,fur, gg
    }

    public enum filterFile{
        jpg,webm,all
    }

    public String getBoard(String board){
        try {
            return boardEnum.valueOf(board).toString();
        }catch (IllegalArgumentException e) {
            System.out.println("Беда");
        }
       return null;
    }

    public String getThreads(String board){
        return "https://2ch.hk/"+ board+"/catalog.json";
    }

    /*
    public String getCurrentThread(String board){
        return "https://2ch.hk/"+ board+"/catalog.json";
    }*/


}
