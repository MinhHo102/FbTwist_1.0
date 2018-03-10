package com.example.mho23.fbtwist.data;

/**
 * Created by mho23 on 3/9/18.
 */

public class customPostClass {
    private String url;
    private String json;

    public customPostClass(String url, String json) {
        this.url = url;
        this.json = json;
    }

    public String getUrl() {
        return url;
    }

    public String getJson() {
        return json;
    }
}
