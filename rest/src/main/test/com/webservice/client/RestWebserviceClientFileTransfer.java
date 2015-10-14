package com.webservice.client;

import java.io.File;
import java.io.InputStream;

import javax.ws.rs.core.MediaType;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.multipart.FormDataMultiPart;
import com.sun.jersey.multipart.file.FileDataBodyPart;
import com.sun.jersey.multipart.impl.MultiPartWriter;
import com.webservice.util.Utility;

public class RestWebserviceClientFileTransfer {
	static String URL="http://localhost:8080/rest/webservice/";
	static String val1="Videos";
	static String val2="Sample Videos";
	static String fileName="Wildlife.wmv";
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//comment either of one when using thru this file
		//System.out.println("File Transfer :  "+FileTransfer());
		System.out.println("File Transfer using pathparam :  "+FileTransferByPathParam());
		

	}
	/**
	 * client to post/upload a file using webservice
	 * @return String
	 */
	static String FileTransfer(){
		ClientConfig cc = new DefaultClientConfig();
		cc.getClasses().add(MultiPartWriter.class);
		Client client = Client.create(cc);
		
		//configure webresource
		WebResource webResource = client.resource(URL+"filetransfer");
		
		File file = new File(new Utility().fileToTransfer());
		FileDataBodyPart filedatabodypart = new FileDataBodyPart("file",file,
				MediaType.APPLICATION_OCTET_STREAM_TYPE);
		
		FormDataMultiPart formDataMultiPart = new FormDataMultiPart();
		formDataMultiPart.bodyPart(filedatabodypart);
		String response = webResource.type(MediaType.MULTIPART_FORM_DATA).accept(MediaType.APPLICATION_JSON)
				.post(String.class,formDataMultiPart);
		return response;				
	}
	
	/**
	 * client to get a file by pathparam by webservice
	 * @return String
	 */
	static String FileTransferByPathParam(){
		String response="Failure";
		ClientConfig cc = new DefaultClientConfig();
		cc.getClasses().add(MultiPartWriter.class);
		Client client = Client.create(cc);
		//configure webresource
		WebResource webResource = client.resource(URL).path(val1).path(val2).path(fileName);
		//InputStream in = webResource.accept(MediaType.APPLICATION_OCTET_STREAM).get(InputStream.class);
		ClientResponse clresponse = webResource.accept(MediaType.APPLICATION_OCTET_STREAM).get(ClientResponse.class);
		//InputStream in =clresponse.getEntity(InputStream.class);
		InputStream in =clresponse.getEntityInputStream();
		if(in!=null){
			response="Success";
		}
		//TODO:the inputstream can be saved at desired location
		return response;
		
		
	}

}
