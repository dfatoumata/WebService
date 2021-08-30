package fr.doranco.jaxrs.jersey.client.launchers;

import javax.ws.rs.core.MediaType;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.WebResource.Builder;

import fr.doranco.jaxrs.jersey.entity.Employe;

public class AddXmlResponse {

	public static void main(String[] args) {
		Client client =   Client.create();
		WebResource webResource = client.resource("http://localhost:9991/JerseyRest/employes/add");
		Builder builder = webResource.type(MediaType.APPLICATION_XML).accept(MediaType.APPLICATION_XML);
		
		Employe employe = new Employe("TataCliXml", "Titti", "Concepteur");
		ClientResponse response = builder.post(ClientResponse.class, employe);
		if (response.getStatus() == 201) {
			Employe output = response.getEntity(Employe.class);
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
