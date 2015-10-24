package com.webservice.client;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.soap.MTOMFeature;

import org.junit.Before;
import org.junit.Test;

import com.webservice.entity.Student;
import com.webservice.soap.ISoapWebservice;
import com.webservice.util.Utility;
import com.webservice.wrapper.StudentClassWrapper;

public class SoapWebServiceClient {
	
	static URL url;
	static QName qname;
	static Service service;
	static ISoapWebservice isoapwebservice;
	static String val1="Videos";
	static String val2="Sample Videos";
	static String fileName="Wildlife.wmv";
	
	public static void main(String[] args){
		try {
			url = new URL("http://localhost:8080/soap/services/sample?wsdl");
			qname= new QName("http://soap.webservice.com/","SoapWebserviceService");
			service = Service.create(url, qname);
			isoapwebservice = service.getPort(ISoapWebservice.class,new MTOMFeature());//enabling mtom at client
			//System.out.println("Student list Size: "+getNoOfStudents());
			//System.out.println("Save Student Details: "+setStudentDetails());
			System.out.println("Post a file to Server: "+postFile() );
			//System.out.println("Get a file from Server: "+getFile() );
			
		} catch (Exception  e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static int getNoOfStudents(){		
		StudentClassWrapper students =isoapwebservice.getPersonDetails();
		if(students!=null){
			return students.getStudent().size();
		}else{
			return 0;
		}		
	}
	
	public static boolean setStudentDetails(){
		Student s= new Student();
		s.setAge(21);
		s.setExperience(0);
		s.setFirstName("FirstName1");
		s.setLastName("lastName1");
		return isoapwebservice.setStudentDetails(s);
	}
	
	
	public static boolean postFile(){
		Utility u = new Utility();
		String filePath = u.getPath().concat(fileName);
		try {
			File file=new File(filePath);
			FileInputStream stream =new FileInputStream(new File(filePath));
			BufferedInputStream inputStream = new BufferedInputStream(stream);
			byte[] fileBytes = new byte[(int) file.length()];
			inputStream.read(fileBytes);
            inputStream.close();			
			return  isoapwebservice.postFile(fileBytes);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
	
	public static boolean getFile(){
		byte[] filebytes =isoapwebservice.getFile(val1, val2, fileName);
		if(filebytes!= null){
			//save file
			Utility u = new Utility();
			String filePath=u.getPath().concat(File.separator).concat(fileName);
			return u.saveFile(filebytes, filePath);
		}else{
			return false;
		}
	}
	/*@Before
	public void init(){
		
		
	}*/
	
	/*@Test
	public void testStudent(){
		
		
	}
*/
}
