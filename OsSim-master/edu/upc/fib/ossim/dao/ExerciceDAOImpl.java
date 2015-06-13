package edu.upc.fib.ossim.dao;

import java.util.List;

import edu.upc.fib.ossim.mcq.model.Exercice;
import edu.upc.fib.ossim.mcq.model.QR;

public class ExerciceDAOImpl implements ExerciceDAO {

	private FactoryDAO factoryDAO;

	public ExerciceDAOImpl(FactoryDAO daoFactory) {
		this.factoryDAO = daoFactory;
	}

	@Override
	public List<QR> getListQRByExo(int IdExo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void creerExercice(Exercice Exo) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Exercice> getListExercicePublies() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Exercice> getListTestPublies() {
		// TODO Auto-generated method stub
		return null;
	}

}
