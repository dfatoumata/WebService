package fr.doranco.jaxrs.connexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ResourceBundle;


public final class DataSourceConnexion {
	static Connection connexion;
	static DataSourceConnexion dataSourceConnexion = null;	

	
	private DataSourceConnexion() throws Exception {
	}
	
	public static synchronized  DataSourceConnexion getInstance() throws Exception {
		if (dataSourceConnexion==null) {
			dataSourceConnexion = new DataSourceConnexion();
		}		
		return dataSourceConnexion;
	}
	
	public  Connection getConnexion() throws Exception {
		if (connexion==null || connexion.isClosed()) {
		ResourceBundle rb = ResourceBundle.getBundle("fr.doranco.jaxrs.dbfile");
		String user = rb.getString("user");
		String password = rb.getString("password");
		String url = rb.getString("url");
		String driver = rb.getString("driver");
		Class.forName(driver);		
		connexion = DriverManager.getConnection(url, user, password);
		}
		return connexion;
	}
	
}
