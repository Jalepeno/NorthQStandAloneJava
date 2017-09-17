package com.northqstandalone.maven.models;

public class QPlugModel {
    int status;
//    NorthQRestfulUtils nq = new NorthQRestfulUtils();

    public QPlugModel() {

    }

    public QPlugModel(int status) {
        this.status = status;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

}
