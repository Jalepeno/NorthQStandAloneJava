package com.northqstandalone.maven.models;

public class QMotionModel {

	private boolean armed;
	private float tmp;
	private float humidity;
	private float light;
	BinarySensorModel model;
	
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
	
	
	/*
	 * "BinarySensors": [
        {
            "type_id": 2,
            "battery": 0,
            "pos": 0,
            "serial": "30303039303030313939",
            "armed": 1,
            "firmware": 1001,
            "name": "MotionSensor",
            "trigger": 0,
            "manufacturer": 150,
            "type": 32768,
            "uploaded": 1506090500,
            "product": 3,
            "power": 2,
            "read": 1506090498,
            "node_id": 3,
            "sensors": [
                {
                    "scale": 0,
                    "type": 1,
                    "value": 23.27
                },
                {
                    "scale": 0,
                    "type": 3,
                    "value": 100
                },
                {
                    "scale": 0,
                    "type": 5,
                    "value": 60.68
                }
            ],
            "versionV2": {
                "hardware": 2,
                "zw_app": 5.1,
                "apps": [
                    10.1
                ],
                "zw_proto": 4.5,
                "zw_lib": 3
            },
            "trigger_reset": 300,
            "range_testing": 0,
            "room": 2,
            "range": 0,
            "relays": []
        }
    ]
	 */
}