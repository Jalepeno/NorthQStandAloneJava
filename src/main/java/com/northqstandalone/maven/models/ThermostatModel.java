package com.northqstandalone.maven.models;

import java.util.ArrayList;

public class ThermostatModel {
	
	public long uploaded;
	public int product;
	public int firmware;
	public int rooom;
	public int battery;
	public int interval;
	public VersionV2Model versionV2;
	public long read;
	public int range;
	public int node_id;
	public int scale;
	public int range_testing;
	public String serial;
	public int controle_mode;
	public int type;
	public ArrayList<SensorModel> sensors;
	public int manufacturer;
	public float temperature;

	
	/*
	 * "uploaded": 1506080855,
            "product": 4,
            "firmware": 101,
            "room": 3,
            "battery": 91,
            "interval": 300,
            "versionV2": {
                "hardware": 0,
                "zw_app": 1.1,
                "apps": [],
                "zw_proto": 3.67,
                "zw_lib": 6
            },
            "read": 1506080854,
            "range": 0,
            "node_id": 4,
            "scale": 0,
            "range_testing": 0,
            "serial": "unknown",
            "control_mode": 0,
            "type": 5,
            "sensors": [],
            "manufacturer": 2,
            "temperature": 22
	 */
}
