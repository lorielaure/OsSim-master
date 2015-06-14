package edu.upc.fib.ossim.mcq.model;

public class Simulation {
	
	private int idSimulation;
	private String management; // l'algorithme de la simulation (FIX, VAR, PAG, SEG pour la mémoire) 
	
	
	public Simulation() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Simulation(String management) {
		super();
		this.management = management;
	}
	public int getIdSimulation() {
		return idSimulation;
	}
	public void setIdSimulation(int idSimulation) {
		this.idSimulation = idSimulation;
	}
	public String getManagement() {
		return management;
	}
	public void setManagement(String management) {
		this.management = management;
	}
	
	
}
