package br.com.codenation;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

public class RestClient {
	private final String URI = "https://api.codenation.dev/v1/challenge/dev-ps/submit-solution";
	private final String PATH = "challenge/dev-ps/submit-solution";
	
	private Client client;
	
	public RestClient() {
		this.client= ClientBuilder.newClient();
	}
	
	public int sendResult (String json, String token) {
		
		//TODO mudar submit pra formdata (no momento está incorreto)
		
		Response response = client
				.target(this.URI)
				.path(this.PATH)
				.queryParam("token", token)
				.request(json, MediaType.APPLICATION_JSON)
				.post(Entity.entity(json, MediaType.APPLICATION_JSON));
		return response.getStatus();
		
		
	}
	
	
	
}
