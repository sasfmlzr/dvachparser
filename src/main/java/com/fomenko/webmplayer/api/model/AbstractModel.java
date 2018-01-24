package com.fomenko.webmplayer.api.model;

import com.fomenko.webmplayer.api.Dvach;

public class AbstractModel {
    private Dvach dvach;
    public AbstractModel(Dvach dvach) {
        this.dvach = dvach;
    }
    protected Dvach getDvach() {
        return dvach;
    }
}
