package com.webservice.rest;

import java.io.File;
import java.io.InputStream;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.sun.jersey.core.header.FormDataContentDisposition;
import com.sun.jersey.multipart.FormDataBodyPart;
import com.sun.jersey.multipart.FormDataMultiPart;
import com.sun.jersey.multipart.FormDataParam;
import com.webservice.util.Utility;
import com.webservice.wrapper.PersonClassWrapper;
import com.webservice.wrapper.SampleData;
import com.webservice.wrapper.StudentClassWrapper;

@Path("/webservice")
public class RestWebservice {
	
	/**
	 * get json response
	 * @return StudentClassWrapper
	 */
	@GET
	@Path("/studentsjson")
	@Produces(MediaType.APPLICATION_JSON)	
	public StudentClassWrapper getStudentsjson(){		
		return SampleData.sampleStudentsData();
	}
	
	/*@GET
	@Path("/studentsxml")
	@Produces(MediaType.APPLICATION_XML)	
	public StudentClassWrapper getStudentsxml(){		
		return SampleData.sampleStudentsData();
	}*/
	/**
	 * get json response
	 * @return PersonClassWrapper
	 */
	@GET
	@Path("/personsjson")
	@Produces(MediaType.APPLICATION_JSON)	
	public PersonClassWrapper getPersonsjson(){		
		return SampleData.samplePersonsData();
	}
	
	/*@GET
	@Path("/personsxml")
	@Produces(MediaType.APPLICATION_XML)	
	public PersonClassWrapper getPersonsxml(){		
		return SampleData.samplePersonsData();
	}*/
	
	/**
	 * URL formation using parameters
	 * @param val1
	 * @param val2
	 * @param fileName
	 * @return the Response
	 */
	@GET
	@Path("/{val1}/{val2}/{fileName}")
	@Produces(MediaType.APPLICATION_OCTET_STREAM)
	public Response getFile(@PathParam("val1") String val1,@PathParam("val2") String val2,@PathParam("fileName") String fileName){
		Response response=null;				
		Utility u= new Utility();
		String filePath =u.getPath().concat(val1).concat(File.separator).concat(val2).concat(File.separator).concat(fileName);
		System.out.println(filePath);
		if(new File(filePath).exists()){
			response=Response.ok(new File(filePath), MediaType.APPLICATION_OCTET_STREAM)
				      .header("Content-Disposition", "attachment; filename=\"" + new File(filePath).getName() + "\"" )
				      .build();
		}else{
			response=Response.noContent().status(404).build();
		}
		
		return response;
		
	}
	/**
	 * Webservice to receive a file/upload a file
	 * @param fileInputStream
	 * @param contentDispositionHeader
	 * @return
	 */
	@POST
	@Path("/filetransfer")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	@Produces(MediaType.APPLICATION_JSON)	
	public String uploadFile(@FormDataParam("file") InputStream fileInputStream,
			@FormDataParam("file") FormDataContentDisposition contentDispositionHeader){
			String response=null;
			Utility u = new Utility();
			boolean val=u.saveFile(fileInputStream, u.fileToTransfer());
			if(val){
				response="SUCCESS";
			}else{
				response="Failure";
			}
			return response;
	}
	
	/**
	 * webservice to transfer object and file together
	 * @param formDataMultiPart
	 * @return
	 */
	@POST
	@Path("/objectandfiletransfer")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	@Produces(MediaType.APPLICATION_JSON)
	public String receiveObjectAndFile(FormDataMultiPart formDataMultiPart){
		String response=null;
		Utility u = new Utility();
		//get the object
		PersonClassWrapper personClassWrapper =formDataMultiPart.getField("PersonClassWrapper").getEntityAs(PersonClassWrapper.class);
		//get the file
		FormDataBodyPart fdp = formDataMultiPart.getField("file");
		String fileName = fdp.getContentDisposition().getFileName();
		boolean val =u.saveFile(fdp.getValueAs(InputStream.class), u.getPath()+fileName);
		if(val){
			response="SUCCESS";
		}else{
			response="Failure";
		}
		return response;
	}

}
