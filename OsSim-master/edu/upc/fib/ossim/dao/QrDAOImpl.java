package edu.upc.fib.ossim.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import edu.upc.fib.ossim.mcq.model.Answer;
import edu.upc.fib.ossim.mcq.model.Bid;
import edu.upc.fib.ossim.mcq.model.ProcessusSimulationMemoire;
import edu.upc.fib.ossim.mcq.model.ProcessusSimulationProcessus;
import edu.upc.fib.ossim.mcq.model.QR;
import edu.upc.fib.ossim.mcq.model.Simulation;
import edu.upc.fib.ossim.mcq.model.SimulationMemoire;
import edu.upc.fib.ossim.mcq.model.SimulationProcessus;
import edu.upc.fib.ossim.utils.Constants;

public class QrDAOImpl implements QrDAO {

	private FactoryDAO factoryDAO;

	public QrDAOImpl(FactoryDAO daoFactory) {
		this.factoryDAO = daoFactory;
	}

	
	public QR getInfoQR(int IdQR) {
		// TODO Auto-generated method stub
		return null;
	}

	
	public List<Answer> getAnswersQR(int IdQR) {
		// TODO Auto-generated method stub
		return null;
	}

	
	public List getProcQR(int idQR) {
		// TODO Auto-generated method stub
		return null;
	}

	
	public List getBidByProc(int id_Proc) {
		// TODO Auto-generated method stub
		return null;
	}

	
	public List getProcQRMem(int idQR) {
		// TODO Auto-generated method stub
		return null;
	}

	
	public List<ProcessusSimulationProcessus> getProcQRProcArriving(int idQR) {
		// TODO Auto-generated method stub
		return null;
	}

	
	public List<ProcessusSimulationProcessus> getProcQRProcReady(int idQR) {
		// TODO Auto-generated method stub
		return null;
	}

	
	public void creerQRProcessus(QR q) {
		// TODO Auto-generated method stub
		
	}

	
	public void creerQRMemoire(QR q) {
		// TODO Auto-generated method stub
		
	}

	
	public void creerReponseQr() {
		// TODO Auto-generated method stub
		
	}

	
	public void creerParamQrProcessus() {
		// TODO Auto-generated method stub
		
	}

	
	public void creerParamQrMemoire() {
		// TODO Auto-generated method stub
		
	}

	
	public void creerProcessusQr() {
		// TODO Auto-generated method stub
		
	}

	
	public void creerProcessusQrArriving() {
		// TODO Auto-generated method stub
		
	}

	
	public void creerProcessusQrReady() {
		// TODO Auto-generated method stub
		
	}

	
	public void creerMemoireProcessusQr() {
		// TODO Auto-generated method stub
		
	}

	
	public void creerBidMemoire() {
		// TODO Auto-generated method stub
		
	}

	public SimulationProcessus getParamQRProc(int idQR) {
		// TODO Auto-generated method stub
		return null;
	}

	public SimulationMemoire getParamQRMem(int idQR) {
		// TODO Auto-generated method stub
		return null;
	}

	public QR findQR(int idQR) {
		
	    QR qr=new QR();
	    List<Answer> answers=new ArrayList<Answer>();
	    
	    qr=getInfoQR(idQR);
	    
	    answers=getAnswersQR(idQR);
	    qr.setAnswerList(answers);
	    if(qr.getModuleQR()==Constants.MODULE_MEMOIRE){
	    	SimulationMemoire simulation;
	    	simulation=getParamQRMem(idQR);
	    	
	    	List<ProcessusSimulationMemoire> listProcessMemoire=getProcQRMem(idQR);
	    	if(qr.getSimulation().getManagement()=="PAG"){
	    		List<Bid> bids=null;
	    		for(int i=0; i<listProcessMemoire.size();i++){
	    			bids=getBidByProc(listProcessMemoire.get(i).getPid());
	    			listProcessMemoire.get(i).setList_Bid(bids);
	    		}	
	    	}
	    	simulation.setListeProcessus(listProcessMemoire);
	    	qr.setSimulation(simulation);
	    	
	    }else if(qr.getModuleQR()==Constants.MODULE_PROCESS){
	    	SimulationProcessus simulation=new SimulationProcessus();
	    	simulation=getParamQRProc(idQR);
	    	List<ProcessusSimulationProcessus> listProcessArriving=getProcQRProcArriving(idQR);
	    	List<ProcessusSimulationProcessus> listProcessReady=getProcQRProcReady(idQR);
	    	
	    	listProcessArriving.addAll(listProcessReady);
	    	simulation.setListeProcessus(listProcessArriving);
	    	
	    	qr.setSimulation(simulation);
	    	
	    }

		return qr;
	}
	
}
