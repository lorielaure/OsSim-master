package edu.upc.fib.ossim.dao;


import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import static edu.upc.fib.ossim.utils.Constants.*;
public class FactoryDAO {
	
	
    private String              url;     
    private String              username;
    private String              password;
    
    FactoryDAO( String url, String username, String password ) {
        this.url = url;
        this.username = username;
        this.password = password;
    }
    /*
     * M�thode charg�e de r�cup�rer les informations de connexion � la base de
     * donn�es, charger le driver JDBC et retourner une instance 
     */
    public static FactoryDAO getInstance() throws DAOConfigurationException {
        Properties properties = new Properties();
        String url;
        String driver;
        String nomUtilisateur;
        String motDePasse;
        
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        InputStream fichierProperties = classLoader.getResourceAsStream( FICHIER_PROPERTIES );
        
        if ( fichierProperties == null ) {
            throw new DAOConfigurationException( "Le fichier properties " + FICHIER_PROPERTIES + " est introuvable." );
        }
        try {
            properties.load( fichierProperties );
            url = properties.getProperty( PROPERTY_URL );
            driver = properties.getProperty( PROPERTY_DRIVER );
            nomUtilisateur = properties.getProperty( PROPERTY_NOM_UTILISATEUR );
            motDePasse = properties.getProperty( PROPERTY_MOT_DE_PASSE );
        } catch ( IOException e ) {
            throw new DAOConfigurationException( "Impossible de charger le fichier properties " + FICHIER_PROPERTIES, e );
        }
        try {
            Class.forName( driver );
        } catch ( ClassNotFoundException e ) {
            throw new DAOConfigurationException( "Le driver est introuvable dans le classpath.", e );
        }
        FactoryDAO instance = new FactoryDAO( url, nomUtilisateur, motDePasse );
        return instance;
    }
    /* M�thode charg�e de fournir une connexion � la base de donn�es */
     /* package */ 
    Connection getConnection() throws SQLException {
        return DriverManager.getConnection( url, username, password );
    } 
     
     public EtudiantDAO getEtudiantDao() {
         return new EtudiantDAOImpl( this );
     } 
     
     public ProfesseurDAO getProfesseurDAO() {
         return new ProfesseurDAOImpl( this );
     } 
 	 	
 	public ExerciceDAO getExerciceDAO(){
 		return new ExerciceDAOImpl(this);
 	}
 	
 	public TestRealiseDAO getTestRealiseDAO(){
 		return new TestRealiseDAOImpl(this);
 	}
 	
 	public QrDAO getQrDAO(){
 		return new QrDAOImpl(this);
 	}
	
}
