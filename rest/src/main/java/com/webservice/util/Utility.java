package com.webservice.util;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Properties;

public class Utility {
	
	private static Properties prop = new Properties();
	private static String configFile="config.properties";
	
	private  void loadProp(){
		try {								
			InputStream in=this.getClass().getClassLoader().getResourceAsStream(configFile);			
			prop.load(in);		
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public  String fileToTransfer(){
		loadProp();
		return prop.getProperty("fileToTransfer");
	}
	
	public  String getPath(){
		loadProp();
		return prop.getProperty("path");
	}

}
