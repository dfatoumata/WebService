package fr.doranco.jaxrs.jersey.server;

import java.net.URI;
import java.util.logging.Level;

import javax.ws.rs.core.UriBuilder;

import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;



public class UtilisateurWebResourceLauncher {
	
	public static final URI BASE_URI = UriBuilder.fromUri("http://localhost/Jersey2Rest/").port(9991).build();

	public static void main(String[] args) {
		
		try {
			
			ResourceConfig resourceConfig = new ResourceConfig(UtilisateurWebResource.class);			
			HttpServer server = GrizzlyHttpServerFactory.createHttpServer(BASE_URI, resourceConfig);			
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
