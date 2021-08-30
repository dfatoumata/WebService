package fr.doranco.jaxrs.jersey.client.launchers;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation.Builder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.client.ClientResponse;
import org.glassfish.jersey.logging.LoggingFeature;


import fr.doranco.jaxrs.jersey.entity.Utilisateur;

public class UpdateJsonResponse {

	public static void main(String[] args) {
		
		final String BASE_URI = "http://localhost:9991/Jersey2Rest/";

		Client client = ClientBuilder.newClient(new ClientConfig().register(LoggingFeature.class)) ;
//		WebTarget webTarget = client.target("http://localhost:9991/JerseyRest/employes/employe-4");
		
		WebTarget webTarget = client.target(BASE_URI).path("users").path("update");
		Utilisateur utilisateur = new Utilisateur("toto", "titi");
		utilisateur.setId(5);

		Builder builder = webTarget.request(MediaType.APPLICATION_JSON);
		Response response = builder.put(Entity.entity(utilisateur, MediaType.APPLICATION_JSON));
		if (response.getStatus() !=201) {
			System.out.println("failed HTTP ERROR CODE" +response.getStatus());
			String error = response.readEntity(String.class);
			System.out.println("Error: " +error);
			System.exit(1);
		}
		
		String output = response.readEntity(String.class);
		System.out.println("CODE status: " +response.getStatus() + " OK");
		System.out.println("Output du server");
		System.out.println(output);
		
		System.out.println("------------------------------------------------------------------");
		webTarget = client.target(BASE_URI).path("users").path("updatePrenom/7/fatou");
		builder = webTarget.request(MediaType.APPLICATION_JSON);
		response = builder.put(Entity.entity(utilisateur, MediaType.APPLICATION_JSON));
		if (response.getStatus() !=201) {
			System.out.println("failed HTTP ERROR CODE" +response.getStatus());
			String error = response.readEntity(String.class);
			System.out.println("Error: " +error);
			System.exit(1);
		}
		
		Utilisateur output2 = response.readEntity(Utilisateur.class);
		System.out.println("CODE status: " +response.getStatus() + " OK");
		System.out.println("Output du server");
		System.out.println(output2);
	}
}
