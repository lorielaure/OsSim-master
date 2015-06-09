package edu.upc.fib.ossim.mcq.model;

public class Etudiant {
	
	private int id_Etudiant;
	private String login;
	private String motDePasse_Etudiant;
	private String nomPrenom_Etudiant;
	
	
	public Etudiant() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Etudiant(int id_Etudiant, String login, String motDePasse,
			String nomPrenom_Etudiant) {
		super();
		this.id_Etudiant = id_Etudiant;
		this.login = login;
		this.motDePasse_Etudiant = motDePasse;
		this.nomPrenom_Etudiant = nomPrenom_Etudiant;
	}

	public String getNomPrenom_Etudiant() {
		return nomPrenom_Etudiant;
	}
	public void setNomPrenom_Etudiant(String nomPrenom_Etudiant) {
		this.nomPrenom_Etudiant = nomPrenom_Etudiant;
	}
	public int getId_Etudiant() {
		return id_Etudiant;
	}
	public void setId_Etudiant(int id_Etudiant) {
		this.id_Etudiant = id_Etudiant;
	}
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getMotDePasse() {
		return motDePasse_Etudiant;
	}
	public void setMotDePasse(String motDePasse) {
		this.motDePasse_Etudiant = motDePasse;
	}
	
	

}
