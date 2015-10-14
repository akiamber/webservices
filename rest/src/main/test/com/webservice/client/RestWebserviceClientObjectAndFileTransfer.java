package com.webservice.client;

import java.io.File;

import javax.ws.rs.core.MediaType;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.api.json.JSONConfiguration;
import com.sun.jersey.multipart.FormDataMultiPart;
import com.sun.jersey.multipart.file.FileDataBodyPart;
import com.sun.jersey.multipart.impl.MultiPartWriter;
import com.webservice.util.Utility;
import com.webservice.wrapper.PersonClassWrapper;
import com.webservice.wrapper.SampleData;

public class RestWebserviceClientObjectAndFileTransfer {

	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//comment either of one when using thru this file
		System.out.println("File Transfer with object :  "+objectAndFileTransfer());

	}
	/**
	 * Client to post/transfer object and a file together by webservice
	 * @return String
	 */
	static String objectAndFileTransfer(){
		ClientConfig clientConfig = new DefaultClientConfig();
		 clientConfig.getFeatures().put(JSONConfiguration.FEATURE_POJO_MAPPING, Boolean.TRUE);
		 clientConfig.getClasses().add(MultiPartWriter.class);
		 Client client = Client.create(clientConfig);		
		 
		 WebResource webResource = client.resource(RestWebserviceClientFileTransfer.URL+"objectandfiletransfer");
		 
		 File file = new File(new Utility().fileToTransfer());
			FileDataBodyPart filedatabodypart = new FileDataBodyPart("file", file,
					MediaType.APPLICATION_OCTET_STREAM_TYPE);

			FormDataMultiPart formDataMultiPart = new FormDataMultiPart();
			
			PersonClassWrapper pclswrapper=SampleData.samplePersonsData();
			formDataMultiPart.field("PersonClassWrapper", pclswrapper, MediaType.APPLICATION_JSON_TYPE);
			formDataMultiPart.bodyPart(filedatabodypart);
			ClientResponse response = webResource.type(MediaType.MULTIPART_FORM_DATA)
					.accept(MediaType.APPLICATION_JSON).
					post(ClientResponse.class, formDataMultiPart);
			
			return response.getEntity(String.class);
		
	}
	
	/**
	 * Client to get object Person object using webservice
	 */
	static void getPersonClassDetails(){
		
		ClientConfig clientConfig = new DefaultClientConfig();
		clientConfig.getFeatures().put(JSONConfiguration.FEATURE_POJO_MAPPING, Boolean.TRUE);
		 Client client = Client.create(clientConfig);
		 WebResource webResource = client
					.resource(RestWebserviceClientFileTransfer.URL+"personsjson");
		 PersonClassWrapper pclswrapper =webResource
			.accept(MediaType.APPLICATION_JSON).get(PersonClassWrapper.class);
		 System.out.println("No of Persons : "+pclswrapper.getPerson().size());
	}

}
