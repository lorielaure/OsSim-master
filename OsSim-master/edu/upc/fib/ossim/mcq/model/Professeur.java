package edu.upc.fib.ossim.mcq.model;

public class Professeur {
	
	private int id_Prodesseur;
	private String Login_Professeur;
	private String motDePasse_Professeur;
	private String nomPrenom_Professeur;
	
	
	public Professeur() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Professeur(int id_Prodesseur, String login_Professeur,
			String motDePasse_Professeur, String nomPrenom_Professeur) {
		super();
		this.id_Prodesseur = id_Prodesseur;
		Login_Professeur = login_Professeur;
		this.motDePasse_Professeur = motDePasse_Professeur;
		this.nomPrenom_Professeur = nomPrenom_Professeur;
	}

	public int getId_Prodesseur() {
		return id_Prodesseur;
	}
	public void setId_Prodesseur(int id_Prodesseur) {
		this.id_Prodesseur = id_Prodesseur;
	}
	public String getLogin_Professeur() {
		return Login_Professeur;
	}
	public void setLogin_Professeur(String login_Professeur) {
		Login_Professeur = login_Professeur;
	}
	public String getMotDePasse_Professeur() {
		return motDePasse_Professeur;
	}
	public void setMotDePasse_Professeur(String motDePasse_Professeur) {
		this.motDePasse_Professeur = motDePasse_Professeur;
	}
	public String getNomPrenom_Professeur() {
		return nomPrenom_Professeur;
	}
	public void setNomPrenom_Professeur(String nomPrenom_Professeur) {
		this.nomPrenom_Professeur = nomPrenom_Professeur;
	}
	
	

}
