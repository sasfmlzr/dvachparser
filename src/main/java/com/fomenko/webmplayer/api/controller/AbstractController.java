package com.fomenko.webmplayer.api.controller;

import com.fomenko.webmplayer.api.Dvach;

public class AbstractController {

    private Dvach dvach;
    public AbstractController(Dvach dvach) {
        this.dvach = dvach;
    }
    protected Dvach getDvach() {
        return dvach;
    }
}
