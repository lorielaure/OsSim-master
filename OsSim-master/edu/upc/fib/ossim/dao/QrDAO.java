package edu.upc.fib.ossim.dao;

import java.util.List;

import edu.upc.fib.ossim.mcq.model.Answer;
import edu.upc.fib.ossim.mcq.model.Bid;
import edu.upc.fib.ossim.mcq.model.ProcessusSimulationMemoire;
import edu.upc.fib.ossim.mcq.model.ProcessusSimulationProcessus;
import edu.upc.fib.ossim.mcq.model.QR;
import edu.upc.fib.ossim.mcq.model.SimulationMemoire;
import edu.upc.fib.ossim.mcq.model.SimulationProcessus;

public interface QrDAO {
	  QR getInfoQR(int IdQR); 
	  List<Answer> getAnswersQR(int IdQR); 
	  SimulationProcessus getParamQRProc(int idQR);
	  List<ProcessusSimulationProcessus> getProcQRProcArriving(int idQR); //
	  List<ProcessusSimulationProcessus> getProcQRProcReady(int idQR); //
	  List<Bid> getBidByProc(int id_Proc);
	  List<ProcessusSimulationMemoire> getProcQRMem(int idQR);	  
	  SimulationMemoire getParamQRMem(int idQR);
	  
	  void creerQRProcessus(QR q);
	  void creerQRMemoire(QR q);
	  void creerReponseQr(List<Answer> listAns);
	  void creerParamQrProcessus(SimulationProcessus simp);
	  void creerParamQrMemoire(SimulationMemoire simm);	  
	  void creerProcessusQrArriving(List<ProcessusSimulationProcessus> listroA);
	  void creerProcessusQrReady(List<ProcessusSimulationProcessus> listroR);
	  void creerMemoireProcessusQr(List<ProcessusSimulationMemoire> listpro);
	  void creerBidMemoire(List<Bid> bid);
	  
	  QR findQR(int idQR);
	  void createQR(QR qr);

}
