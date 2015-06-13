package edu.upc.fib.ossim.dao;

import java.util.List;

import edu.upc.fib.ossim.mcq.model.TestRealise;

public class TestRealiseDAOImpl implements TestRealiseDAO {

	private FactoryDAO factoryDAO;

	public TestRealiseDAOImpl(FactoryDAO daoFactory) {
		this.factoryDAO = daoFactory;
	}

	@Override
	public List<TestRealise> getListTestsByEtudiant(long idEtudiant) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<TestRealise> getListEtudiantsByTest(long idExercice) {
		// TODO Auto-generated method stub
		return null;
	}
	

}
