package edu.upc.fib.ossim.dao;

import java.util.List;

import edu.upc.fib.ossim.mcq.model.TestRealise;

public interface TestRealiseDAO {
	List<TestRealise> getListTestsByEtudiant(long idEtudiant); //label exercice | note
	List<TestRealise> getListEtudiantsByTest(long idExercice); //nometudiant |note

}
