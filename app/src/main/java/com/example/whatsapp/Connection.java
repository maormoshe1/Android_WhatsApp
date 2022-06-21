package com.example.whatsapp;

public class Connection {
    private String from;
    private String to;
    private String server;
    private String content;

    public Connection(String from, String to, String server, String content) {
        this.from = from;
        this.to = to;
        this.server = server;
        this.content = content;
    }


    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getServer() {
        return server;
    }

    public void setServer(String server) {
        this.server = server;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
