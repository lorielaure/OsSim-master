package edu.upc.fib.ossim.dao;

import static edu.upc.fib.ossim.utils.Constants.FICHIER_PROPERTIES;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import edu.upc.fib.ossim.utils.Constants;

public final class DAOUtils {
	private static Properties mProperties ;

	/*
	 * Constructeur cach� par d�faut (car c'est une classe finale utilitaire,
	 * contenant uniquement des m�thodes appel�es de mani�re statique)
	 */
	private DAOUtils() {
	}

	/* Fermeture silencieuse du resultset */
	public static void fermetureSilencieuse( ResultSet resultSet ) {
		if ( resultSet != null ) {
			try {
				resultSet.close();
			} catch ( SQLException e ) {
				System.out.println( "�chec de la fermeture du ResultSet : " + e.getMessage() );
			}
		}
	}

	/* Fermeture silencieuse du statement */
	public static void fermetureSilencieuse( Statement statement ) {
		if ( statement != null ) {
			try {
				statement.close();
			} catch ( SQLException e ) {
				System.out.println( "�chec de la fermeture du Statement : " + e.getMessage() );
			}
		}
	}

	/* Fermeture silencieuse de la connexion */
	public static void fermetureSilencieuse( Connection connexion ) {
		if ( connexion != null ) {
			try {
				connexion.close();
			} catch ( SQLException e ) {
				System.out.println( "�chec de la fermeture de la connexion : " + e.getMessage() );
			}
		}
	}

	/* Fermetures silencieuses du statement et de la connexion */
	public static void fermeturesSilencieuses( Statement statement, Connection connexion ) {
		fermetureSilencieuse( statement );
		fermetureSilencieuse( connexion );
	}

	/* Fermetures silencieuses du resultset, du statement et de la connexion */
	public static void fermeturesSilencieuses( ResultSet resultSet, Statement statement, Connection connexion ) {
		fermetureSilencieuse( resultSet );
		fermetureSilencieuse( statement );
		fermetureSilencieuse( connexion );
	}

	/*
	 * Initialise la requ�te pr�par�e bas�e sur la connexion pass�e en argument,
	 * avec la requ�te SQL et les objets donn�s.
	 */
	public static PreparedStatement initialisationRequetePreparee( Connection connexion, String sql, boolean returnGeneratedKeys, Object... objets ) throws SQLException {
		PreparedStatement preparedStatement = connexion.prepareStatement( sql, returnGeneratedKeys ? Statement.RETURN_GENERATED_KEYS : Statement.NO_GENERATED_KEYS );
		for ( int i = 0; i < objets.length; i++ ) {
			preparedStatement.setObject( i + 1, objets[i] );
		}
		return preparedStatement;
	}

	public static  Properties getProperties(){
		InputStream fichierProperties = DAOUtils.class.getResourceAsStream( Constants.REQUEST_PROPERTIES );

		if ( fichierProperties == null ) {
			throw new DAOConfigurationException( "Le fichier properties " + Constants.REQUEST_PROPERTIES + " est introuvable." );
		}
		try {
			mProperties =  new Properties();
			mProperties.load( fichierProperties );

		} catch ( IOException e ) {
			throw new DAOConfigurationException( "Impossible de charger le fichier properties " + FICHIER_PROPERTIES, e );
		}

		return mProperties;

	}

}

