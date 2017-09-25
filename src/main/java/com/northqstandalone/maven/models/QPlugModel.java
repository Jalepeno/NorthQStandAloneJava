package com.northqstandalone.maven.models;

public class QPlugModel {
    int status;
    public BinarySwitchModel binarySwitch;
//    NorthQRestfulUtils nq = new NorthQRestfulUtils();

    public QPlugModel() {

    }

    public QPlugModel(int status) {
        this.status = status;
    }

    public int getStatus() {
    	if(binarySwitch != null) {
    		return binarySwitch.pos;
    	}
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
    
    public BinarySwitchModel getBinarySwitch() {
		return binarySwitch;
	}
    public void setBinarySwitch(BinarySwitchModel binarySwitch) {
		this.binarySwitch = binarySwitch;
		
		System.out.println("--- switch pos: "+binarySwitch.pos+" ---");
	}

}
