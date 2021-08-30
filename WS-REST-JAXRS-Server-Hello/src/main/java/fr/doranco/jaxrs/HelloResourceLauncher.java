package fr.doranco.jaxrs;

import java.net.URI;
import java.util.logging.Level;

import javax.ws.rs.core.UriBuilder;

import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.logging.LoggingFeature;
import org.glassfish.jersey.server.ResourceConfig;

public class HelloResourceLauncher {
	public static final URI BASE_URI = UriBuilder.fromUri("http://localhost/helloWs/").port(9991).build();
	
	public static void main(String[] args) {

		try {
			ResourceConfig resourceConfig = new ResourceConfig();
			resourceConfig.registerClasses(HelloWebResource.class);
			resourceConfig.property(LoggingFeature.LOGGING_FEATURE_LOGGER_LEVEL_SERVER, Level.WARNING.getName());
			
			HttpServer server = GrizzlyHttpServerFactory.createHttpServer(BASE_URI, resourceConfig);
			
			server.start();
			Thread.sleep(2000);
			System.err.println("Jersey demarré" +BASE_URI);
			
			System.out.println("taper dans le navigateur " +BASE_URI+"application.wadl");
			
			System.in.read();
			server.shutdown();
			
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}
