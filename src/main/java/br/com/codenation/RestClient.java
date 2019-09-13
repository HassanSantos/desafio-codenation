package br.com.codenation;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.sun.jersey.multipart.MultiPart;
import com.sun.jersey.multipart.file.StreamDataBodyPart;
import com.sun.jersey.multipart.impl.MultiPartWriter;

public class RestClient {
	private final String URI = "https://api.codenation.dev/v1";
	private final String PATH = "/challenge/dev-ps/submit-solution";
	
	private Client client;
	
	public RestClient() {
		this.client= ClientBuilder.newBuilder().register(MultiPartWriter.class).build();
	}
	
	public int sendResult (String json, String token) {
		
		MultiPart multipart = new MultiPart();
		multipart.setMediaType(MediaType.MULTIPART_FORM_DATA_TYPE);
		
		InputStream stream = new ByteArrayInputStream(json.getBytes());
		
		StreamDataBodyPart part = new StreamDataBodyPart("answer", stream);
		multipart.bodyPart(part);
		
		Response response = client
				.target(this.URI)
				.path(this.PATH)
				.queryParam("token", token)
				.request( MediaType.APPLICATION_JSON)
				.post(Entity.entity(multipart, MediaType.MULTIPART_FORM_DATA));
		return response.getStatus();
		
		
	}
	
	
	
}
