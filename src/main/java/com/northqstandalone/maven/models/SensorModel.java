package com.northqstandalone.maven.models;

public class SensorModel {
	
	public int scale;
	public int type;
	public float value;


	public String getStrings() {
		return "(scale: "+scale+
				" - type: "+type+
				" - value: "+value+")";
	}
	
}
