package model;

import java.sql.Date;

public class Article {
	
	private int id;
	private String titre;
	private String resume;
	private String contenu;
	private Date date_creation;
	private User auteur;
	
	public Article() {
	}
	
	
	public Article(int id, String titre, String resume, String contenu, Date date_creation, User auteur) {
		this.id = id;
		this.titre = titre;
		this.resume = resume;
		this.contenu = contenu;
		this.date_creation = date_creation;
		this.auteur = auteur;
	}


	public Article(String titre, String resume, String contenu) {
		this.titre = titre;
		this.resume = resume;
		this.contenu = contenu;
	}


	public Article(String titre, String resume, String contenu, User auteur) {
		this.titre = titre;
		this.resume = resume;
		this.contenu = contenu;
		this.auteur = auteur;
	}


	public Article( String titre, String resume, String contenu, Date date_creation, User auteur) {
		this.titre = titre;
		this.resume = resume;
		this.contenu = contenu;
		this.date_creation = date_creation;
		this.auteur = auteur;
	}

	public Article(int id, String titre, String resume, String contenu, java.util.Date sqlDate, User auteur) {
		this.id = id;
		this.titre = titre;
		this.resume = resume;
		this.contenu = contenu;
		this.date_creation = (Date) sqlDate;
		this.auteur = auteur;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitre() {
		return titre;
	}

	public void setTitre(String titre) {
		this.titre = titre;
	}

	public String getResume() {
		return resume;
	}

	public void setResume(String resume) {
		this.resume = resume;
	}

	public String getContenu() {
		return contenu;
	}

	public void setContenu(String contenu) {
		this.contenu = contenu;
	}

	public Date getDate_creation() {
		return date_creation;
	}

	public void setDate_creation(Date date_creation) {
		this.date_creation = date_creation;
	}

	public User getAuteur() {
		return auteur;
	}

	public void setAuteur(User auteur) {
		this.auteur = auteur;
	}

	@Override
	public String toString() {
		return "Article id" + id + " titre: " + titre + " resume: " + resume + ", contenu: " + contenu
				+ " date_creation: " + date_creation + " auteur: " + auteur + "\n";
	}
	
}
