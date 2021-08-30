package fr.doranco.jaxrs.utils;

import org.json.JSONObject;

import fr.doranco.jaxrs.jersey.entity.Employe;

public final class Strings {

	
	private Strings() {
		// TODO Auto-generated constructor stub
	}
	
	public static final String getEmployeAuFormatXmlString(Employe employe) {
		
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("<employe>");
		
		stringBuilder.append("<id>");
		stringBuilder.append(employe.getId());
		stringBuilder.append("</id>");
		
		stringBuilder.append("<nom>");
		stringBuilder.append(employe.getNom());
		stringBuilder.append("</nom>");
		
		stringBuilder.append("<prenom>");
		stringBuilder.append(employe.getPrenom());
		stringBuilder.append("</prenom>");
		
		stringBuilder.append("<posteOccupe>");
		stringBuilder.append(employe.getPosteOccupe());
		stringBuilder.append("</posteOccupe>");
		
		stringBuilder.append("</employe>");
			
		return stringBuilder.toString();		
	}
	
	public static final String getEmployeAuFormatJsonString(Employe employe) {
		
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("id", employe.getId());
		jsonObject.put("prenom", employe.getPrenom());
		jsonObject.put("nom", employe.getNom());
		jsonObject.put("posteOccupe", employe.getPosteOccupe());
		return jsonObject.toString();		
	}
}
