package edu.upc.fib.ossim.dao;

import edu.upc.fib.ossim.mcq.model.Etudiant;

public interface EtudiantDAO {
	
	void creer( Etudiant etudiant ) throws DAOException;
	Etudiant trouver( long id ) throws DAOException; 
	void reinitialiserMdp( long id, String mdp ) throws DAOException;

}
