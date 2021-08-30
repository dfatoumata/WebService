package fr.doranco.jaxws.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import fr.doranco.entity.Etudiant;
import fr.doranco.jaxws.connexion.DataSourceConnexion;

public class EtudiantDAO implements IEtudiantDAO {

	@Override
	public List<Etudiant> getEtudiants() throws Exception {
		Connection connexion = null;
		List<Etudiant> etudiants = new ArrayList<Etudiant>();
		try {
			connexion = DataSourceConnexion.getInstance().getConnexion();
			String requete = "SELECT * FROM etudiant";
			PreparedStatement ps = connexion.prepareStatement(requete);

			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Etudiant etudiant = new Etudiant();
				etudiant.setId(rs.getInt("id"));
				etudiant.setNom(rs.getString("nom"));
				etudiant.setPrenom(rs.getString("prenom"));
				etudiant.setSpecialite(rs.getString("specialite"));
				etudiant.setAge(rs.getInt("age"));				
				etudiants.add(etudiant);
			}

		} finally {
			if (connexion != null) {
				connexion.close();
			}
		}
		return etudiants;
	}

	@Override
	public Etudiant getEtudiantById(Integer id) throws Exception {
		Connection connexion = null;
		Etudiant etudiant = new Etudiant();
		try {
			connexion = DataSourceConnexion.getInstance().getConnexion();
			String requete = "SELECT * FROM etudiant where id = ? ";
			PreparedStatement ps = connexion.prepareStatement(requete);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			
			if (rs.next()) {
				etudiant.setId(rs.getInt("id"));
				etudiant.setNom(rs.getString("nom"));
				etudiant.setPrenom(rs.getString("prenom"));
				etudiant.setSpecialite(rs.getString("specialite"));
				etudiant.setAge(rs.getInt("age"));
			}

		} finally {
			if (connexion != null) {
				connexion.close();
			}
		}
		return etudiant;
	}

	@Override
	public Etudiant addEtudiant(Etudiant etudiant) throws Exception {

		Connection connexion = null;
		try {

			connexion = DataSourceConnexion.getInstance().getConnexion();
			String requete = "INSERT INTO etudiant(nom, prenom, specialite, age) VALUES(?, ?, ?, ?)";
			PreparedStatement ps = connexion.prepareStatement(requete, Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, etudiant.getNom());
			ps.setString(2, etudiant.getPrenom());
			ps.setString(3, etudiant.getSpecialite());			
			if (etudiant.getAge() != null) {
				ps.setInt(4, etudiant.getAge());
			} else {
				ps.setNull(4, Types.INTEGER);
			}
			ps.executeUpdate();
			ResultSet rs = ps.getGeneratedKeys();
			if (rs.next()) {
				etudiant.setId(rs.getInt(1));
			}

		} finally {
			if (connexion != null) {
				connexion.close();
			}
		}
		return etudiant;

	}

}
