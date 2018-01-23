package com.fomenko.webmplayer.api.model;

import com.fomenko.webmplayer.api.Dvach;

public final class DvachModel extends Dvach {

    public enum boardEnum{
        b,fet,hc,e,a,fur, gg
    }

    public enum filterFile {
        image("jpg","png"),
        video("webm","mp4") ,
        all("jpg","png","webm","mp4") ;
        private final String[] typeFiles;
        filterFile(String... typeFiles) {
            this.typeFiles = typeFiles;
        }
        public String[] getTypeFiles() {
            return typeFiles;
        }
    }



    public String getBoard(String board){
        try {
            return boardEnum.valueOf(board).toString();
        }catch (IllegalArgumentException e) {
            System.out.println("Такой борды нет");
        }
       return null;
    }
   /* @Deprecated
    public String getFilterFile(String typeFile){
        try {
            return filterFile.valueOf(typeFile).toString();
        }catch (IllegalArgumentException e) {
            System.out.println("Такого типа файлов нет");
        }
        return null;
    }
*/
    public String[] getFilterFile(String typeFile){
        try {
            return filterFile.valueOf(typeFile).getTypeFiles();
        }catch (IllegalArgumentException e) {
            System.out.println("Такого типа файлов нет");
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
