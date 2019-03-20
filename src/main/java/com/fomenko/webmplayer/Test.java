package com.fomenko.webmplayer;

import com.fomenko.webmplayer.api.Dvach;
import org.apache.logging.log4j.*;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;

public class Test {

    public static void main(String[] args) throws IOException {
        System.out.println("ff");
        Dvach dvach=new Dvach();
     //   dvach.fileOperation().fileOpen("b");
        org.apache.logging.log4j.Logger  logger = LogManager.getLogger("name");
        String myCookie = "usercode_auth=c4879607e360cf191118c33a66832583";
        //dvach.parseDvachAutomate("b","video", myCookie);
        logger.trace("trace");
        logger.debug("debug");
        logger.info("info");
        logger.warn("warn");
        logger.error("error");
        logger.fatal("fatal");

        URL website = new URL("https://2ch.hk/b/src/172421037/15210383071170.webm");

        ReadableByteChannel rbc = Channels.newChannel(website.openStream());
        FileOutputStream fos = new FileOutputStream(website.getFile());
        fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);

        logger.fatal("fatal");
    }
}
