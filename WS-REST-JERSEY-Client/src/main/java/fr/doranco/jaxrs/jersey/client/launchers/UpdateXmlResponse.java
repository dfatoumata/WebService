package fr.doranco.jaxrs.jersey.client.launchers;

import javax.ws.rs.core.MediaType;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.WebResource.Builder;

import fr.doranco.jaxrs.jersey.entity.Employe;

public class UpdateXmlResponse {

	public static void main(String[] args) {
		Client client =   Client.create();
		WebResource webResource = client.resource("http://localhost:9991/JerseyRest/employes/update");
		Builder builder = webResource.type(MediaType.APPLICATION_XML).accept(MediaType.APPLICATION_XML);
		
		Employe employe = new Employe("TataCliXmlUpdate", "Titti", "Concepteur");
		employe.setId(6);
		ClientResponse response = builder.put(ClientResponse.class, employe);
		if (response.getStatus() == 202) {
			String output = response.getEntity(String.class);
			System.out.println("CODE status: " +response.getStatus() + " OK");
			System.out.println("Output du server");
			System.out.println(output);
			System.exit(1);
		}
		
		System.out.println("failed HTTP ERROR CODE" +response.getStatus());
		String error = response.getEntity(String.class);
		System.out.println("Error: " +error);
	}

}
