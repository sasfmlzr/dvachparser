package com.fomenko.webmplayer.api.model;

import com.fomenko.webmplayer.api.Dvach;

public final class DvachModel extends AbstractModel {

    public DvachModel(Dvach dvach) {
        super(dvach);
    }

    public enum boardEnum{
        b(),
        vg,
        news,
        po,
        fag,
        sex,
        v,
        soc,
        a,
        hw,
        mov,
        au,
        mobi,
        wm,
        pr,
        cc,
        s,
        cq,
        wrk,
        hc,
        fa,
        me,
        ga,
        fet,
        fiz,
        sp,
        gsg,
        tv,
        mus,
        mu,
        ma,
        rf,
        tes,
        vn,
        e,
        ftb,
        d,
        mlp,
        psy,
        c,
        wh,
        sn,
        pa,
        biz,
        fg,
        diy,
        un,
//////////////////////////
        fur,        //
        gg          //
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

    private String cookie;

    public String getCookie() {
        return cookie;
    }

    public void setCookie(String cookie) {
        this.cookie = cookie;
    }

    public String getBoard(String board){
        try {
            return boardEnum.valueOf(board).toString();
        }catch (IllegalArgumentException e) {
            getDvach().log.error("Такой борды нет");
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
            getDvach().log.error("Такого типа файлов нет");
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
