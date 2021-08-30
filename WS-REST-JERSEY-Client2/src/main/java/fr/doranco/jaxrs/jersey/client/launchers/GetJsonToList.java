package fr.doranco.jaxrs.jersey.client.launchers;

import java.util.List;

import javax.ws.rs.core.MediaType;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.GenericType;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.WebResource.Builder;

import fr.doranco.jaxrs.jersey.entity.Employe;

public class GetJsonToList {
	
	public static void main(String[] args) {
		Client client =   Client.create();
		WebResource webResource = client.resource("http://localhost:9991/JerseyRest/employes/liste");
		Builder builder = webResource.accept(MediaType.APPLICATION_JSON)
				.header("content-type", MediaType.APPLICATION_JSON);
		ClientResponse response = builder.get(ClientResponse.class);
		if (response.getStatus() !=200) {
			System.out.println("failed HTTP ERROR CODE" +response.getStatus());
			String error = response.getEntity(String.class);
			System.out.println("Error: " +error);
			System.exit(1);
		}
		
		List<Employe> employes =response.getEntity(new GenericType<List<Employe>>() {});		
		System.out.println("Output du server JSON");
		employes.forEach(e->System.out.println(e));

	}
}
