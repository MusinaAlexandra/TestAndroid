package com.example.testandoid;

public class JokeData {
    private String id;
    private String joke;
    private int status;

    public void setJoke(String joke) {
        this.joke = joke;
    }

    public String getJoke() {
        return joke;
    }

    public int getStatus() {
        return status;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
