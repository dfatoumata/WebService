package fr.doranco.jaxrs.jersey.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import fr.doranco.jaxrs.connexion.DataSourceConnexion;
import fr.doranco.jaxrs.jersey.entity.Employe;

public class EmployeDao implements IEmployeDao {

	@Override
	public List<Employe> getEmployes() throws Exception {
		Connection connexion = null;
		List<Employe> employes = new ArrayList<Employe>();
		try {
			connexion = DataSourceConnexion.getInstance().getConnexion();
			String requete = "SELECT * FROM employe";
			PreparedStatement ps = connexion.prepareStatement(requete);

			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Employe employe = new Employe();
				employe.setId(rs.getInt("id"));
				employe.setNom(rs.getString("nom"));
				employe.setPrenom(rs.getString("prenom"));
				employe.setPosteOccupe(rs.getString("posteOccupe"));
				employes.add(employe);
			}

		} finally {
			if (connexion != null) {
				connexion.close();
			}
		}
		return employes;
	}

	@Override
	public Employe getEmployeById(Integer id) throws Exception {
		Connection connexion = null;
		Employe employe = new Employe();
		try {
			connexion = DataSourceConnexion.getInstance().getConnexion();
			String requete = "SELECT * FROM employe where id = ? ";
			PreparedStatement ps = connexion.prepareStatement(requete);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			
			if (rs.next()) {
				employe.setId(rs.getInt("id"));
				employe.setNom(rs.getString("nom"));
				employe.setPrenom(rs.getString("prenom"));
				employe.setPosteOccupe(rs.getString("posteOccupe"));
			}

		} finally {
			if (connexion != null) {
				connexion.close();
			}
		}
		return employe;
	}

	@Override
	public Employe addEmploye(Employe employe) throws Exception {
		Connection connexion = null;
		try {

			connexion = DataSourceConnexion.getInstance().getConnexion();
			String requete = "INSERT INTO employe(nom, prenom, posteOccupe) VALUES(?, ?, ?)";
			PreparedStatement ps = connexion.prepareStatement(requete, Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, employe.getNom());
			ps.setString(2, employe.getPrenom());
			ps.setString(3, employe.getPosteOccupe());
		
			ps.executeUpdate();
			ResultSet rs = ps.getGeneratedKeys();
			if (rs.next()) {
				employe.setId(rs.getInt(1));
			}

		} finally {
			if (connexion != null) {
				connexion.close();
			}
		}
		return employe;
	}

	@Override
	public void updateEmploye(Employe employe) throws Exception {
		
		
		Connection connexion = null;
		try {
			connexion = DataSourceConnexion.getInstance().getConnexion();
			String requete = "UPDATE employe SET nom = ?, prenom = ?, posteOccupe = ?  WHERE id = ? ";
			PreparedStatement ps = connexion.prepareStatement(requete);

			ps.setString(1, employe.getNom());
			ps.setString(2, employe.getPrenom());
			ps.setString(3, employe.getPosteOccupe());			
			ps.setInt(4, employe.getId());

			int nbLines = ps.executeUpdate();
			
			if (nbLines != 1) {
				throw new SQLException("Erreur lors de la mise à jour de l'employe (id = " + employe.getId() + ")");
			}

		} finally {
			if (connexion != null) {
				connexion.close();
			}
		}
		
	}

	@Override
	public void removeEmploye(Integer id) throws Exception {
		if (id == null || id <= 0) {
			throw new IllegalArgumentException("Les paramètres doivent être non nuls et non vides !");
		}

		Connection connexion = null;
		try {
			connexion = DataSourceConnexion.getInstance().getConnexion();
			String requete = "DELETE FROM employe WHERE id = ? ";
			PreparedStatement ps = connexion.prepareStatement(requete);
			ps.setInt(1, id);

			int nbLines = ps.executeUpdate();
			
			if (nbLines != 1) {
				throw new SQLException("Erreur lors de la suppression de l'employe (id = " + id + ")");
			}

		} finally {
			if (connexion != null) {
				connexion.close();
			}
		}		
	}

}
