package edu.upc.fib.ossim.dao;

import java.util.List;

import edu.upc.fib.ossim.mcq.model.QR;

public interface QrDAO {
	  List getInfoQR(int IdQR);
	  List<QR>getAnswersQR(int IdQR);
	  List getProcQR(int idQR);
	  List getBidByProc(int id_Proc);
	  List getProcQRMem(int idQR);
	  List getProcQRProcArriving(int idQR);
	  List getProcQRProcReady(int idQR);
	  List getParamQRProc(int idQR);
	  List getParamQRMem(int idQR);
	  void creerQRProcessus(QR q);
	  void creerQRMemoire(QR q);
	  void creerReponseQr();
	  void creerParamQrProcessus();
	  void creerParamQrMemoire();
	  void creerProcessusQr();
	  void creerProcessusQrArriving();
	  void creerProcessusQrReady();
	  void creerMemoireProcessusQr();
	  void creerBidMemoire();

}
