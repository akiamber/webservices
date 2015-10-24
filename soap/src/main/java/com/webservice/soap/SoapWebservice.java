package com.webservice.soap;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.jws.WebService;
import javax.xml.ws.soap.MTOM;

import com.webservice.entity.Student;
import com.webservice.util.Utility;
import com.webservice.wrapper.SampleData;
import com.webservice.wrapper.StudentClassWrapper;

@MTOM(enabled=true, threshold=10240)//file of size 10kb or more can only be sent
@WebService(endpointInterface="com.webservice.soap.ISoapWebservice")
public class SoapWebservice implements ISoapWebservice {

	@Override
	public StudentClassWrapper getPersonDetails() {
		// TODO Auto-generated method stub
		return SampleData.sampleStudentsData();
	}

	@Override
	public boolean setStudentDetails(Student student) {
		// TODO Auto-generated method stub
		if(student!=null){
			System.out.println("Name: "+student.getFirstName()+" "+student.getLastName()+
					" Age: "+student.getAge());
			return true;
		}else{
			return false;
		}
			
	}

	
	@Override	
	public boolean postFile(byte[] filebytes) {
		// TODO Auto-generated method stub
		if(filebytes!=null){
			Utility u = new Utility();
			boolean val=u.saveFile(filebytes, u.fileToTransfer());
			return val;
		}
		return false;
	}

	
	@Override
	public byte[] getFile(String val1, String val2, String fileName) {
		// TODO Auto-generated method stub
		Utility u= new Utility();
		String filePath =u.getPath().concat(val1).concat(File.separator).concat(val2).concat(File.separator).concat(fileName);
		File file=new File(filePath);
		if(file.exists()){
			try{
				FileInputStream stream =new FileInputStream(new File(filePath));
				BufferedInputStream inputStream = new BufferedInputStream(stream);
				byte[] fileBytes = new byte[(int) file.length()];
				inputStream.read(fileBytes);
	            inputStream.close();
				return fileBytes;
			}catch(IOException e){
				return null;
			}						
		}else{
			return null;
		}
	}

}
