package com.northqstandalone.maven.models;

import java.util.ArrayList;

public class BinarySensorModel {
	
		public int type_id;
		public int battery;
		public int pos;
		public String serial;
		public int armed;
		public int firmware;
		public String name;
		public int trigger;
		public int manufacture;
		public int type;
		public long uploaded;
		public int product;
		public int power;
		public long read;
		public int node_id;
		public ArrayList<SensorModel> sensors;
		public VersionV2Model versionV2;
		public int trigger_reset;
		public int range_testing;
		public int room;
		public int range;
		public float[] relays;
		
		public void print() {
			System.out.println("type_id: "+type_id+
					"\nbattery: "+battery+
					"\npos: "+pos+
					"\narmed: "+armed+
					"\nname"+name+
					"\n"+getSensorValues()
					);
			
		}
         
		
		private String getSensorValues(){
			String s="{";
			for(SensorModel sm : sensors) {
				s += "\n"+sm.getStrings();
				
			}
			s+="}";
			return s;
		}

}
