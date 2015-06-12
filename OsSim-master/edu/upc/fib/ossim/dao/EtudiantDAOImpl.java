package edu.upc.fib.ossim.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import edu.upc.fib.ossim.mcq.model.Etudiant;
import static edu.upc.fib.ossim.dao.DAOUtils.*;
import edu.upc.fib.ossim.utils.Constants;

public class EtudiantDAOImpl implements EtudiantDAO{
	
	private FactoryDAO factoryDAO;
	private static final String SQL_INSERT = "INSERT INTO Etudiant (login, password, nomprenom_etudiant) VALUES (?, ?, ?)";

	

	EtudiantDAOImpl( FactoryDAO daoFactory ) {
        this.factoryDAO = daoFactory;
    } 
	@Override
	public void creer(Etudiant etudiant) throws DAOException {
		Connection connexion = null;
	    PreparedStatement preparedStatement = null;
	    ResultSet valeursAutoGenerees = null;
	    try {
	        /* R�cup�ration d'une connexion depuis la Factory */
	        connexion = factoryDAO.getConnection();
	        preparedStatement = initialisationRequetePreparee( connexion, SQL_INSERT, true, etudiant.getLogin(), etudiant.getMotDePasseEtudiant(), etudiant.getNomPrenomEtudiant() );
	        int statut = preparedStatement.executeUpdate();
	        /* Analyse du statut retourn� par la requ�te d'insertion */
	        if ( statut == 0 ) {
	            throw new DAOException( "�chec de la cr�ation de l'etudiant, aucune ligne ajout�e dans la table." );
	        }
	        /* R�cup�ration de l'id auto-g�n�r� par la requ�te d'insertion */
	        valeursAutoGenerees = preparedStatement.getGeneratedKeys();
	        if ( valeursAutoGenerees.next() ) {
	            /* Puis initialisation de la propri�t� id du bean Utilisateur avec sa valeur */
	        	etudiant.setIdEtudiant( valeursAutoGenerees.getLong( 1 ) );
	        } else {
	            throw new DAOException( "�chec de la cr�ation de l'etudiant en base, aucun ID auto-g�n�r� retourn�." );
	        }
	    } catch ( SQLException e ) {
	        throw new DAOException( e );
	    } finally {
	        fermeturesSilencieuses( valeursAutoGenerees, preparedStatement, connexion );
	    } 
		
	}

	@Override
	public Etudiant trouver(long id) throws DAOException {
		Connection connexion = null;
	    PreparedStatement preparedStatement = null;
	    ResultSet resultSet = null;
	    Etudiant utilisateur = null;
	    try {
	        /* R�cup�ration d'une connexion depuis la Factory */
	        connexion = factoryDAO.getConnection();
	        preparedStatement = initialisationRequetePreparee( connexion, Constants.REQ_ETUDIANT_BY_ID, false, id );
	        resultSet = preparedStatement.executeQuery();
	        /* Parcours de la ligne de donn�es de l'�ventuel ResulSet retourn� */
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
			preparedStatement = initialisationRequetePreparee( connexion, Constants.REQ_INIT_MDP_ETUDIANT, false, mdp, id);
			int statut = preparedStatement.executeUpdate();
	        /* Analyse du statut retourn� par la requ�te d'insertion */
	        if ( statut == 0 ) {
	            throw new DAOException( "�chec de la mise � jour de l'etudiant, aucune ligne modifi�e dans la table." );
	        }	
		 } catch ( SQLException e ) {
		        throw new DAOException( e );
		    } finally {
		        fermeturesSilencieuses( preparedStatement, connexion );
		    } 
		
	}
	 /*
     * Simple m�thode utilitaire permettant de faire la correspondance (le
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
