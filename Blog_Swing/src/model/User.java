package model;

public class User {
	private int id;
	private String nom;
	private String prenom;
	private String mail;
	private String pwd;
	
	public User() {
	}
	
	public User(int id, String nom, String prenom, String mail, String pwd) {
		this.id = id;
		this.nom = nom;
		this.prenom = prenom;
		this.mail = mail;
		this.pwd = pwd;
	}
	
	public User(int id, String nom, String prenom, String mail) {
		this.id = id;
		this.nom = nom;
		this.prenom = prenom;
		this.mail = mail;
	}
	
	public User( String nom, String prenom, String mail) {
		this.nom = nom;
		this.prenom = prenom;
		this.mail = mail;
	}
	
	public User(String nom, String prenom, String mail, String pwd) {
		this.nom = nom;
		this.prenom = prenom;
		this.mail = mail;
		this.pwd = pwd;
	}
	
	public User(String mail, String pwd) {
		this.mail = mail;
		this.pwd = pwd;
	}
	
	public User(String mail) {
		this.mail = mail;
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getPrenom() {
		return prenom;
	}
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", nom=" + nom + ", prenom=" + prenom + ", mail=" + mail + ", pwd=" + pwd + "]";
	}

}
