

/**
 * @author Laure
 *
 */
/*public class EtudiantDAO implements Dao<Etudiant> {

	private Connection mConnection;
	private PreparedStatement preparedStatement = null;
	private ResultSet resultSet = null;

	public Etudiant find(String login, String password) {

		String QUERY = "select * from Etudiant where loginEtud=? and passwordEtud=?";

		// Statements allow to issue SQL queries to the database
		try {

			preparedStatement = mConnection .prepareStatement(QUERY);
			preparedStatement.setString(1, login);
			preparedStatement.setString(2, password);

			resultSet = preparedStatement.executeQuery();
			return writeResultSet(resultSet);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;

	}

	public EtudiantDAO() {
		// TODO Auto-generated constructor stub
	}
	public EtudiantDAO(Connection ct) {
		mConnection = ct;
	}


	private Etudiant writeResultSet(ResultSet resultSet) throws SQLException {
		// ResultSet is initially before the first data set
		Etudiant etudiant = null;
		while (resultSet.next()) {
			// It is possible to get the columns via name
			// also possible to get the columns via the column number
			// which starts at 1
			// e.g. resultSet.getSTring(2);
			String loginEtud = resultSet.getString("loginEtud");
			String passwordEtud = resultSet.getString("passwordEtud");
			long idEtudiant = resultSet.getLong("idEtudiant");

			//		      
			//		      System.out.println("loginEtud: " + loginEtud);
			//		      System.out.println("passwordEtud: " + passwordEtud);
			//		      System.out.println("idEtudiant: " + idEtudiant);
			etudiant = new Etudiant(idEtudiant, loginEtud, passwordEtud);
		}
		return etudiant;
	}


}
*/
package edu.upc.fib.ossim.dao;

import edu.upc.fib.ossim.mcq.model.Etudiant;

public interface EtudiantDAO {
	
	void creer( Etudiant etudiant ) throws DAOException;
	Etudiant trouver( long id ) throws DAOException; 
	Etudiant chercher (String login, String motDePasseEtudiant) throws DAOException;
	void reinitialiserMdp( long id, String mdp ) throws DAOException;

}

