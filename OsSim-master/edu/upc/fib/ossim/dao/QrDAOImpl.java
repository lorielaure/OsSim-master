package edu.upc.fib.ossim.dao;

/**
 * @author soukaina
 */

import static edu.upc.fib.ossim.dao.DAOUtils.fermeturesSilencieuses;
import static edu.upc.fib.ossim.dao.DAOUtils.initialisationRequetePreparee;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import edu.upc.fib.ossim.mcq.model.Answer;
import edu.upc.fib.ossim.mcq.model.Bid;
import edu.upc.fib.ossim.mcq.model.Exercice;
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
		Connection connexion = null;
	    PreparedStatement preparedStatement = null;
	    ResultSet resultSet = null;
	    QR qr = new QR();
	    try {
	        /* Récupération d'une connexion depuis la Factory */
	        connexion = factoryDAO.getConnection();
	        preparedStatement = initialisationRequetePreparee( connexion, DAOUtils.getProperties().getProperty(Constants.REQ_INFO_QR), false, IdQR );
	        resultSet = preparedStatement.executeQuery();
	        /* Parcours de la ligne de données de l'éventuel ResulSet retourné */
	        if ( resultSet.next() ) {
	        	qr = mapQrInfo( resultSet );
	        	    
	        }
	    } catch ( SQLException e ) {
	        throw new DAOException( e );
	    } finally {
	        fermeturesSilencieuses( resultSet, preparedStatement, connexion );
	    }

		return qr;
		
	}

	
	public List<Answer> getAnswersQR(int IdQR) {
		Connection connexion = null;
	    PreparedStatement preparedStatement = null;
	    ResultSet resultSet = null;
	    List<Answer> answers = new ArrayList<Answer>();
	    try {
	        /* Récupération d'une connexion depuis la Factory */
	        connexion = factoryDAO.getConnection();
	        preparedStatement = initialisationRequetePreparee( connexion, DAOUtils.getProperties().getProperty(Constants.REQ_ANSWERS_BY_QR), false, IdQR );
	        resultSet = preparedStatement.executeQuery();
	        /* Parcours de la ligne de données de l'éventuel ResulSet retourné */
	        if ( resultSet.next() ) {
	        	answers.add(mapQrAnswers( resultSet ));
	        	    
	        }
	    } catch ( SQLException e ) {
	        throw new DAOException( e );
	    } finally {
	        fermeturesSilencieuses( resultSet, preparedStatement, connexion );
	    }

		return answers;
	}

	
	private Answer mapQrAnswers(ResultSet resultSet)throws SQLException {
		Answer ans=new Answer();
		ans.setText(resultSet.getString("reponse"));
		ans.setValue(resultSet.getBoolean("isAnwerCorrect"));
		return ans;
	}

	public QR findQR(int idQR) {
		
	    QR qr=null;
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
	//Id_exercice, Titre_exo, Exo_type
		 private static QR mapQrInfo( ResultSet resultSet ) throws SQLException {
			 QR qr=new QR();
			 qr.setIdQR(resultSet.getInt("id_qr"));
			 qr.setModuleQR(resultSet.getInt("Mod_QR"));
			 qr.setBlockOnStep(resultSet.getInt("blockOnStep")); 
			 qr.setDifficulty(resultSet.getInt("Difficulty"));
			 qr.setAnswerNumber(resultSet.getInt("AnswerNumber"));
			 qr.setAnswerType(resultSet.getString("answerType"));
			 qr.setEnonce(resultSet.getString("question"));
			 qr.setIncludeAnswers(resultSet.getBoolean("includeAnswers"));
			 return qr;
		 }


	@Override
	public SimulationProcessus getParamQRProc(int idQR) {
		Connection connexion = null;
	    PreparedStatement preparedStatement = null;
	    ResultSet resultSet = null;
	    SimulationProcessus simp = new SimulationProcessus();
	    try {
	        /* Récupération d'une connexion depuis la Factory */
	        connexion = factoryDAO.getConnection();
	        preparedStatement = initialisationRequetePreparee( connexion, DAOUtils.getProperties().getProperty(Constants.REQ_INFO_QR_PROCESSUS), false, idQR );
	        resultSet = preparedStatement.executeQuery();
	        /* Parcours de la ligne de données de l'éventuel ResulSet retourné */
	        if ( resultSet.next() ) {
	        	simp = mapQrProcessParam( resultSet );
	        	    
	        }
	    } catch ( SQLException e ) {
	        throw new DAOException( e );
	    } finally {
	        fermeturesSilencieuses( resultSet, preparedStatement, connexion );
	    }

		return simp;
	}


	private SimulationProcessus mapQrProcessParam(ResultSet resultSet) throws SQLException {
		SimulationProcessus sim = new SimulationProcessus();
		sim.setManagement(resultSet.getString("management"));
		sim.setMultiprograming(resultSet.getBoolean("multiprogramming"));
		sim.setPreemptive(resultSet.getBoolean("Preemptive"));
		sim.setQuantum(resultSet.getInt("Quantum"));
		sim.setVar(resultSet.getBoolean("var"));
		sim.setVerrou(resultSet.getInt("verrou"));
		return sim;
	}


	@Override
	public List<ProcessusSimulationProcessus> getProcQRProcArriving(int idQR) {
		Connection connexion = null;
	    PreparedStatement preparedStatement = null;
	    ResultSet resultSet = null;
	    List<ProcessusSimulationProcessus> procs = new ArrayList<ProcessusSimulationProcessus>();
	    try {
	        /* Récupération d'une connexion depuis la Factory */
	        connexion = factoryDAO.getConnection();
	        preparedStatement = initialisationRequetePreparee( connexion, DAOUtils.getProperties().getProperty(Constants.REQ_PROCESSUS_ARRIVING_BY_QR), false,idQR);
	        resultSet = preparedStatement.executeQuery();
	        /* Parcours de la ligne de données de l'éventuel ResulSet retourné */
	        if ( resultSet.next() ) {
	        	procs.add(mapQrProc(resultSet));
	        }
	    } catch ( SQLException e ) {
	        throw new DAOException( e );
	    } finally {
	        fermeturesSilencieuses( resultSet, preparedStatement, connexion );
	    }

		return procs;	
	}


	private ProcessusSimulationProcessus mapQrProc(ResultSet resultSet) throws SQLException{
		
		ProcessusSimulationProcessus p = new ProcessusSimulationProcessus();
		p.setPid(resultSet.getInt("pid"));
		p.setName(resultSet.getString("p_name"));
		p.setColor(resultSet.getInt("color"));
		p.setBursts(resultSet.getString("bursts"));
		p.setPeriodic(resultSet.getBoolean("periodic"));
		p.setPrio(resultSet.getInt("prio"));
		p.setResources(resultSet.getString("ressources"));
		p.setSubmission(resultSet.getInt("submission"));
		p.setTypeQueue(resultSet.getInt("queue_id"));
		p.setVariables(resultSet.getString("variables"));
		return p;
	}


	@Override
	public List<ProcessusSimulationProcessus> getProcQRProcReady(int idQR) {
		Connection connexion = null;
	    PreparedStatement preparedStatement = null;
	    ResultSet resultSet = null;
	    List<ProcessusSimulationProcessus> procs = new ArrayList<ProcessusSimulationProcessus>();
	    try {
	        /* Récupération d'une connexion depuis la Factory */
	        connexion = factoryDAO.getConnection();
	        preparedStatement = initialisationRequetePreparee( connexion, DAOUtils.getProperties().getProperty(Constants.REQ_PROCESSUS_READY_BY_QR), false,idQR);
	        resultSet = preparedStatement.executeQuery();
	        /* Parcours de la ligne de données de l'éventuel ResulSet retourné */
	        if ( resultSet.next() ) {
	        	procs.add(mapQrProc(resultSet));
	        }
	    } catch ( SQLException e ) {
	        throw new DAOException( e );
	    } finally {
	        fermeturesSilencieuses( resultSet, preparedStatement, connexion );
	    }

		return procs;	
	}


	@Override
	public List<Bid> getBidByProc(int id_Proc) {
		Connection connexion = null;
	    PreparedStatement preparedStatement = null;
	    ResultSet resultSet = null;
	    List<Bid> bids = new ArrayList<Bid>();
	    try {
	        /* Récupération d'une connexion depuis la Factory */
	        connexion = factoryDAO.getConnection();
	        preparedStatement = initialisationRequetePreparee( connexion, DAOUtils.getProperties().getProperty(Constants.REQ_BID_BY_PID), false,id_Proc);
	        resultSet = preparedStatement.executeQuery();
	        /* Parcours de la ligne de données de l'éventuel ResulSet retourné */
	        if ( resultSet.next() ) {
	        	bids.add(mapBid(resultSet));
	        }
	    } catch ( SQLException e ) {
	        throw new DAOException( e );
	    } finally {
	        fermeturesSilencieuses( resultSet, preparedStatement, connexion );
	    }

		return bids;	
	}


	private Bid mapBid(ResultSet resultSet) throws SQLException{
		
		Bid b = new Bid();
		b.setNum_Bid(resultSet.getInt("bid"));
		b.setLoad(resultSet.getBoolean("load"));
		b.setSize_Bid(resultSet.getInt("size"));
		return b;
	}


	@Override
	public List<ProcessusSimulationMemoire> getProcQRMem(int idQR) {
		Connection connexion = null;
	    PreparedStatement preparedStatement = null;
	    ResultSet resultSet = null;
	    List<ProcessusSimulationMemoire> procs = new ArrayList<ProcessusSimulationMemoire>();
	    try {
	        /* Récupération d'une connexion depuis la Factory */
	        connexion = factoryDAO.getConnection();
	        preparedStatement = initialisationRequetePreparee( connexion, DAOUtils.getProperties().getProperty(Constants.REQ_PROCESSUS_MEMOIRY_BY_QR), false,idQR);
	        resultSet = preparedStatement.executeQuery();
	        /* Parcours de la ligne de données de l'éventuel ResulSet retourné */
	        if ( resultSet.next() ) {
	        	procs.add(mapQrProcMem(resultSet));
	        }
	    } catch ( SQLException e ) {
	        throw new DAOException( e );
	    } finally {
	        fermeturesSilencieuses( resultSet, preparedStatement, connexion );
	    }

		return procs;	
	}


	private ProcessusSimulationMemoire mapQrProcMem(ResultSet resultSet) throws SQLException{
		
		ProcessusSimulationMemoire pm = new ProcessusSimulationMemoire();
		pm.setColor(resultSet.getInt("color"));
		pm.setDuration(resultSet.getInt("duration"));
		pm.setName(resultSet.getString("p_name"));
		pm.setPid(resultSet.getInt("pid"));
		pm.setQuantum(resultSet.getInt("quantum"));
		pm.setQuantumOrders(resultSet.getString("quantumOrders"));
		pm.setSize(resultSet.getInt("size"));
		return pm;
	}


	@Override
	public SimulationMemoire getParamQRMem(int idQR) {
		Connection connexion = null;
	    PreparedStatement preparedStatement = null;
	    ResultSet resultSet = null;
	    SimulationMemoire simp = new SimulationMemoire();
	    try {
	        /* Récupération d'une connexion depuis la Factory */
	        connexion = factoryDAO.getConnection();
	        preparedStatement = initialisationRequetePreparee( connexion, DAOUtils.getProperties().getProperty(Constants.REQ_INFO_QR_MEMOIRY), false, idQR );
	        resultSet = preparedStatement.executeQuery();
	        /* Parcours de la ligne de données de l'éventuel ResulSet retourné */
	        if ( resultSet.next() ) {
	        	simp = mapQrMemoryParam( resultSet );
	        	    
	        }
	    } catch ( SQLException e ) {
	        throw new DAOException( e );
	    } finally {
	        fermeturesSilencieuses( resultSet, preparedStatement, connexion );
	    }

		return simp;
	}


	private SimulationMemoire mapQrMemoryParam(ResultSet resultSet) throws SQLException {
		SimulationMemoire s = new SimulationMemoire();
		s.setManagement(resultSet.getString("management"));
		s.setMemorySize(resultSet.getInt("MemorySize"));
		s.setPageSize(resultSet.getInt("pagesize"));
		s.setPolicy(resultSet.getInt("policy"));
		s.setSoSize(resultSet.getInt("sosize"));
		return s;
	}


	@Override
	public void creerQRProcessus(QR q) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void creerQRMemoire(QR q) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void creerReponseQr(List<Answer> listAns) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void creerParamQrProcessus(SimulationProcessus simp) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void creerParamQrMemoire(SimulationMemoire simm) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void creerProcessusQrArriving(
			List<ProcessusSimulationProcessus> listroA) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void creerProcessusQrReady(List<ProcessusSimulationProcessus> listroR) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void creerMemoireProcessusQr(List<ProcessusSimulationMemoire> listpro) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void creerBidMemoire(List<Bid> bid) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void createQR(QR qr) {
		// TODO Auto-generated method stub
		
	}

}
