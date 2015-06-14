
/**
 * 
 */



/**
 * @author Laure
 *
 */
/*public class ProfesseurDAO implements Dao<Professeur> {

	private Connection mConnection;
	private PreparedStatement preparedStatement = null;
	private ResultSet resultSet = null;

	public Professeur find(String login, String password) {	
		String QUERY = "select * from Professeur where loginProf=? and passwordProf=?";

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

	public ProfesseurDAO() {
		// TODO Auto-generated constructor stub
	}

	public ProfesseurDAO( Connection ct) {
		mConnection = ct;
	}
	private Professeur writeResultSet(ResultSet resultSet) throws SQLException {
		// ResultSet is initially before the first data set
		Professeur professeur = null;
		while (resultSet.next()) {
			// It is possible to get the columns via name
			// also possible to get the columns via the column number
			// which starts at 1
			// e.g. resultSet.getSTring(2);
			String loginProf = resultSet.getString("loginProf");
			String passwordProf = resultSet.getString("passwordProf");
			long idProfesseur = resultSet.getLong("idProfesseur");

			//		      
			//		      System.out.println("loginEtud: " + loginEtud);
			//		      System.out.println("passwordEtud: " + passwordEtud);
			//		      System.out.println("idEtudiant: " + idEtudiant);
			professeur = new Professeur(idProfesseur, loginProf, passwordProf);
		}
		return professeur;
	}
}*/

package edu.upc.fib.ossim.dao;


import edu.upc.fib.ossim.mcq.model.Professeur;

public interface ProfesseurDAO {
	
	Professeur chercher (String loginProfesseur, String motDePasseProfesseur) throws DAOException;

}

