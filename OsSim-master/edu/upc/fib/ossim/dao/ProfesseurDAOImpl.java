package edu.upc.fib.ossim.dao;

public class ProfesseurDAOImpl implements ProfesseurDAO {
	private FactoryDAO factoryDAO;
	ProfesseurDAOImpl( FactoryDAO daoFactory ) {
        this.factoryDAO = daoFactory;
    } 

}
