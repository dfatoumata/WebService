package fr.doranco.jaxrs.jersey.server;

import java.net.URI;

import javax.ws.rs.core.UriBuilder;

import com.sun.jersey.api.container.httpserver.HttpServerFactory;
import com.sun.jersey.api.core.PackagesResourceConfig;
import com.sun.jersey.api.core.ResourceConfig;
import com.sun.net.httpserver.HttpServer;


public class EmployeWebResourceLauncher {
	
	public static final URI BASE_URI = UriBuilder.fromUri("http://localhost/JerseyRest/").port(9991).build();

	public static void main(String[] args) {
		
		try {	
			ResourceConfig resourceConfig = new PackagesResourceConfig("fr.doranco.jaxrs.jersey.server");
			HttpServer server = HttpServerFactory.create(BASE_URI, resourceConfig);
			server.start();
			Thread.sleep(2000);
			System.err.println("Jersey demarré" +BASE_URI);
			System.out.println("================================================");	
			System.out.println("taper dans le navigateur " +BASE_URI+"application.wadl");
			System.out.println("========================================================");	
			System.out.println("");		

//			System.in.read();		
//			System.out.println("Arret Programme en cours");		
//			server.stop(0);
//			Thread.sleep(2000);
//			System.out.println("Programme arrété");		
			
//			Thread.sleep(2000);
//			System.err.println("====== Serveur démarré avec succès =======");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
