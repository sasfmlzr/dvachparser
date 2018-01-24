package com.fomenko.webmplayer.api;

import com.fomenko.webmplayer.api.controller.FileOperation;
import com.fomenko.webmplayer.api.controller.HttpOperation;
import com.fomenko.webmplayer.api.controller.ParseDvach;
import com.fomenko.webmplayer.api.model.DvachModel;

public class Dvach {
    public Dvach(){
        dvachModel= new DvachModel(this);
    //    dvachModel = new DvachModel();
    }
    public ParseDvach parseDvach(){return new ParseDvach(this);}
    public HttpOperation httpOperation(){return new HttpOperation(this);}
    public FileOperation fileOperation(){return new FileOperation(this);}
    private DvachModel dvachModel;

    public DvachModel getDvachModel() {
        return dvachModel;
    }
/*  public DvachModel getDvachModel() {
        return dvachModel;
    }*/
}
