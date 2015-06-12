package edu.upc.fib.ossim.dao;

import java.util.List;

import edu.upc.fib.ossim.mcq.model.QR;

public class QrDAOImpl implements QrDAO {

	private FactoryDAO factoryDAO;

	public QrDAOImpl(FactoryDAO daoFactory) {
		this.factoryDAO = daoFactory;
	}

	@Override
	public List getInfoQR(int IdQR) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<QR> getAnswersQR(int IdQR) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List getProcQR(int idQR) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List getBidByProc(int id_Proc) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List getProcQRMem(int idQR) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List getProcQRProcArriving(int idQR) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List getProcQRProcReady(int idQR) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List getParamQRProc(int idQR) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List getParamQRMem(int idQR) {
		// TODO Auto-generated method stub
		return null;
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
	public void creerReponseQr() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void creerParamQrProcessus() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void creerParamQrMemoire() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void creerProcessusQr() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void creerProcessusQrArriving() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void creerProcessusQrReady() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void creerMemoireProcessusQr() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void creerBidMemoire() {
		// TODO Auto-generated method stub
		
	}
	
}
