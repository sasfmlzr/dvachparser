package com.fomenko.webmplayer;

import uk.co.caprica.vlcj.player.embedded.DefaultAdaptiveRuntimeFullScreenStrategy;

import java.awt.*;

final class VlcjPlayerFullScreenStrategy extends DefaultAdaptiveRuntimeFullScreenStrategy {

    VlcjPlayerFullScreenStrategy(Window window) {
        super(window);
    }}
/*
    @Override
    protected void beforeEnterFullScreen() {
        application().post(BeforeEnterFullScreenEvent.INSTANCE);
    }

    @Override
    protected  void afterExitFullScreen() {
        application().post(AfterExitFullScreenEvent.INSTANCE);
    }
}*/