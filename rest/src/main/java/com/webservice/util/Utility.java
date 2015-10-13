package com.webservice.util;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Properties;

public class Utility {
	
	private static Properties prop = new Properties();
	private static String configFile="config.properties";
	
	private static void loadProp(){
		try {					
			URL url =ClassLoader.getSystemResource(configFile);
			prop.load(url.openStream());		
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static String fileToTransfer() throws ClassNotFoundException{
		loadProp();
		return prop.getProperty("fileToTransfer");
	}
	

}
