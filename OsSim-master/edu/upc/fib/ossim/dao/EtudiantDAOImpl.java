package edu.upc.fib.ossim.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import edu.upc.fib.ossim.mcq.model.Etudiant;
import static edu.upc.fib.ossim.dao.DAOUtils.*;

public class EtudiantDAOImpl implements EtudiantDAO{
	
	private FactoryDAO factoryDAO;
	private static final String SQL_SELECT_PAR_LOGIN = "SELECT id, nomprenom_etudiant, login FROM Etudiant WHERE login = ?";
	private static final String SQL_INSERT = "INSERT INTO Etudiant (login, password, nomprenom_etudiant) VALUES (?, ?, ?)";
	private static final String SQL_UPDATE_MDP = "UPDATE ETUDIANT SET password= ? where id = ?";

	

	EtudiantDAOImpl( FactoryDAO daoFactory ) {
        this.factoryDAO = daoFactory;
    } 
	@Override
	public void creer(Etudiant etudiant) throws DAOException {
		Connection connexion = null;
	    PreparedStatement preparedStatement = null;
	    ResultSet valeursAutoGenerees = null;
	    try {
	        /* Récupération d'une connexion depuis la Factory */
	        connexion = factoryDAO.getConnection();
	        preparedStatement = initialisationRequetePreparee( connexion, SQL_INSERT, true, etudiant.getLogin(), etudiant.getMotDePasseEtudiant(), etudiant.getNomPrenomEtudiant() );
	        int statut = preparedStatement.executeUpdate();
	        /* Analyse du statut retourné par la requête d'insertion */
	        if ( statut == 0 ) {
	            throw new DAOException( "Échec de la création de l'etudiant, aucune ligne ajoutée dans la table." );
	        }
	        /* Récupération de l'id auto-généré par la requête d'insertion */
	        valeursAutoGenerees = preparedStatement.getGeneratedKeys();
	        if ( valeursAutoGenerees.next() ) {
	            /* Puis initialisation de la propriété id du bean Utilisateur avec sa valeur */
	        	etudiant.setIdEtudiant( valeursAutoGenerees.getLong( 1 ) );
	        } else {
	            throw new DAOException( "Échec de la création de l'etudiant en base, aucun ID auto-généré retourné." );
	        }
	    } catch ( SQLException e ) {
	        throw new DAOException( e );
	    } finally {
	        fermeturesSilencieuses( valeursAutoGenerees, preparedStatement, connexion );
	    } 
		
	}

	@Override
	public Etudiant trouver(String login) throws DAOException {
		Connection connexion = null;
	    PreparedStatement preparedStatement = null;
	    ResultSet resultSet = null;
	    Etudiant utilisateur = null;
	    try {
	        /* Récupération d'une connexion depuis la Factory */
	        connexion = factoryDAO.getConnection();
	        preparedStatement = initialisationRequetePreparee( connexion, SQL_SELECT_PAR_LOGIN, false, login );
	        resultSet = preparedStatement.executeQuery();
	        /* Parcours de la ligne de données de l'éventuel ResulSet retourné */
	        if ( resultSet.next() ) {
	            utilisateur = map( resultSet );
	        }
	    } catch ( SQLException e ) {
	        throw new DAOException( e );
	    } finally {
	        fermeturesSilencieuses( resultSet, preparedStatement, connexion );
	    }
	    return utilisateur; 
	}

	@Override
	public void reinitialiserMdp(long id, String mdp) throws DAOException {
		Connection connexion = null;
	    PreparedStatement preparedStatement = null;
		try {
			connexion = factoryDAO.getConnection();
			preparedStatement = initialisationRequetePreparee( connexion, SQL_UPDATE_MDP, false, mdp, id);
			int statut = preparedStatement.executeUpdate();
	        /* Analyse du statut retourné par la requête d'insertion */
	        if ( statut == 0 ) {
	            throw new DAOException( "Échec de la mise à jour de l'etudiant, aucune ligne modifiée dans la table." );
	        }	
		 } catch ( SQLException e ) {
		        throw new DAOException( e );
		    } finally {
		        fermeturesSilencieuses( preparedStatement, connexion );
		    } 
		
	}
	 /*
     * Simple méthode utilitaire permettant de faire la correspondance (le
     * mapping) entre une ligne issue de la table des utilisateurs (un
     * ResultSet) et un bean Utilisateur.
     */
    private static Etudiant map( ResultSet resultSet ) throws SQLException {
    	Etudiant etudiant = new Etudiant();
    	etudiant.setIdEtudiant(resultSet.getLong( "id" ) );
        etudiant.setNomPrenomEtudiant( resultSet.getString( "nomprenom_etudiant" ) );
        etudiant.setMotDePasseEtudiant( resultSet.getString( "password" ) );
        etudiant.setLogin(resultSet.getString( "login" ) );
        return etudiant;
    } 
}
