package com.fomenko.webmplayer.api.model;

import java.net.MalformedURLException;
import java.net.URL;

public class UrlDvach {
    private URL url;

    private String body;
    private String board;
    private String file;

    public UrlDvach(String urlCurrent)  {
        try {
            this.url=new URL(urlCurrent);
            body = url.getPath();
            String[] temp = body.split("/");
            board = temp[1];
            file = temp[4];
        } catch (MalformedURLException e) {
            System.out.println("неверная ссылка - " + urlCurrent);
        }

    }

        public URL getUrl () {
        return url;
    }

        public String getBody () {
        return body;
    }

        public String getBoard () {
        return board;
    }

        public String getFile () {
        return file;
    }
}
