package fr.doranco.jaxrs.jersey.client.launchers;

import javax.ws.rs.core.MediaType;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.WebResource.Builder;

public class GetXmlToResponse {

	public static void main(String[] args) {
		Client client =   Client.create();
		WebResource webResource = client.resource("http://localhost:9991/JerseyRest/employes/employe-4");
		Builder builder = webResource.accept(MediaType.APPLICATION_XML)
				.header("content-type", MediaType.APPLICATION_XML);
		ClientResponse response = builder.get(ClientResponse.class);
		if (response.getStatus() !=200) {
			System.out.println("failed HTTP ERROR CODE" +response.getStatus());
			String error = response.getEntity(String.class);
			System.out.println("Error: " +error);
			System.exit(1);
		}
		
		String output = response.getEntity(String.class);
		System.out.println("CODE status: " +response.getStatus() + "OK");
		System.out.println("Output du server");
		System.out.println(output);

	}

}
