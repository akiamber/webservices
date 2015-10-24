package com.webservice.util;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
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
	
	public Boolean saveFile(InputStream inputstream,
			String pathToSaveAt) {
		Boolean res = Boolean.FALSE;
		try {
			OutputStream outpuStream = new FileOutputStream(new File(
					pathToSaveAt));
			int read = 0;
			byte[] bytes = new byte[1024];

			outpuStream = new FileOutputStream(new File(pathToSaveAt));
			while ((read = inputstream.read(bytes)) != -1) {
				outpuStream.write(bytes, 0, read);
			}
			outpuStream.flush();
			outpuStream.close();
			res = Boolean.TRUE;

		} catch (IOException e) {

			e.printStackTrace();
		}
		return res;
	}
	
	public boolean saveFile(byte[] filebytes, String pathToSaveAt){		
		
		FileOutputStream fos;
		try {
			fos = new FileOutputStream(pathToSaveAt);
			 BufferedOutputStream outputStream = new BufferedOutputStream(fos);
	         outputStream.write(filebytes);
	         outputStream.close();
	         return true;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return false;
	}

}
