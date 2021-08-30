package fr.doranco.jaxrs.jersey.client.launchers;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;

import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.logging.LoggingFeature;

import fr.doranco.jaxrs.jersey.entity.Utilisateur;

import java.net.URI;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation.Builder;
import javax.ws.rs.client.WebTarget;

public class GetJsonToResponse {

	public static void main(String[] args) {
		
		final String BASE_URI = "http://localhost:9991/Jersey2Rest/";

		Client client = ClientBuilder.newClient(new ClientConfig().register(LoggingFeature.class)) ;
//		WebTarget webTarget = client.target("http://localhost:9991/JerseyRest/employes/employe-4");
		
		WebTarget webTarget = client.target(BASE_URI).path("users").path("get/5");

		Builder builder = webTarget.request(MediaType.APPLICATION_JSON);
		Response response = builder.get();
		if (response.getStatus() !=200) {
			System.out.println("failed HTTP ERROR CODE" +response.getStatus());
			String error = response.readEntity(String.class);
			System.out.println("Error: " +error);
			System.exit(1);
		}
		
		Utilisateur output = response.readEntity(Utilisateur.class);
		System.out.println("CODE status: " +response.getStatus() + "OK");
		System.out.println("Output du server");
		System.out.println(output);

	
	}

}
