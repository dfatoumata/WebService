package fr.doranco.jaxrs.jersey.client.launchers;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation.Builder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.logging.LoggingFeature;


public class RemoveJsonResponse {

	public static void main(String[] args) {
		final String BASE_URI = "http://localhost:9991/Jersey2Rest/";

		Client client = ClientBuilder.newClient(new ClientConfig().register(LoggingFeature.class)) ;
//		WebTarget webTarget = client.target("http://localhost:9991/JerseyRest/employes/employe-4");
		
		WebTarget webTarget = client.target(BASE_URI).path("users").path("remove/5");
		Builder builder = webTarget.request(MediaType.APPLICATION_JSON);
		Response response = builder.delete();
		
		if (response.getStatus() !=202) {
			System.out.println("failed HTTP ERROR CODE " +response.getStatus());
			String error = response.readEntity(String.class);
			System.out.println("Error: " +error);
			System.exit(1);
		}
		
		String output = response.readEntity(String.class);
		System.out.println("CODE status: " +response.getStatus() + " OK");
		System.out.println("Output du server");
		System.out.println(output);
		
	}
}
