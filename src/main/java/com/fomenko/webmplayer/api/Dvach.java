package com.fomenko.webmplayer.api;

import com.fomenko.webmplayer.api.controller.FileOperation;
import com.fomenko.webmplayer.api.controller.HttpOperation;
import com.fomenko.webmplayer.api.controller.ParseDvach;
import com.fomenko.webmplayer.api.model.DvachModel;

public class Dvach {
    public Dvach(){}
    public ParseDvach parseDvach(){return new ParseDvach();}
    public HttpOperation httpOperation(){return new HttpOperation();}
    public FileOperation fileOperation(){return new FileOperation();}

    public DvachModel dvachModel(){return new DvachModel();}
}
