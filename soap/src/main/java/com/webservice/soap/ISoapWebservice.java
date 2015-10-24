/**
 * 
 */
package com.webservice.soap;

import java.io.File;
import java.io.InputStream;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;





import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;

import com.webservice.entity.Student;
import com.webservice.wrapper.StudentClassWrapper;

/**
 * @author akiamber
 *
 */
@WebService
//@SOAPBinding(style = Style.RPC)
public interface ISoapWebservice {
	
	@WebMethod(operationName="studentDetails")
	public StudentClassWrapper getPersonDetails();
	
	@WebMethod(operationName="setStudentDetails")
	public boolean setStudentDetails(@WebParam(name="student") Student student);
	
	@WebMethod(operationName="getFile")
	public byte[] getFile(@WebParam(name="val1")String val1,@WebParam(name="val2")String val2, @WebParam(name="fileName") String fileName);
	
	@WebMethod(operationName="postFile")
	public boolean postFile(@WebParam(name="file")byte[] file);
	
	
		
	

}
