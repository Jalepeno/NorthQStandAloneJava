package com.northqstandalone.maven.models;

public class QMotionModel {

	private boolean armed;
	private float tmp;
	private float humidity;
	private float light;

	public QMotionModel() {

	}

	public QMotionModel(boolean armed, float tmp, float humidity, float light) {
		this.armed = armed;
		this.tmp = tmp;
		this.humidity = humidity;
		this.light = light;
	}

	// --- Getters ---//
	public float getHumidity() {
		return humidity;
	}

	public float getLight() {
		return light;
	}

	public float getTmp() {
		return tmp;
	}

	public boolean isArmed() {
		return armed;
	}

	// --- Setters --- //
	public void setArmed(boolean armed) {
		this.armed = armed;
	}

	public void setHumidity(float humidity) {
		this.humidity = humidity;
	}

	public void setLight(float light) {
		this.light = light;
	}

	public void setTmp(float tmp) {
		this.tmp = tmp;
	}

}