package model;

import java.util.Date;

public class Commentaire {
	private int id;
	private String commentaire;
	private Date date_com;
	private User auteur;
	private int art_id;
	
	public Commentaire() {
	}
		
	public Commentaire(String commentaire, User auteur, int art_id) {
		this.commentaire = commentaire;
		this.auteur = auteur;
		this.art_id = art_id;
	}


	public int getArt_id() {
		return art_id;
	}

	public void setArt_id(int art_id) {
		this.art_id = art_id;
	}

	public Commentaire(String commentaire, User auteur) {
		this.commentaire = commentaire;
		this.auteur = auteur;
	}
	
	public Commentaire(String commentaire, Date date_com, User auteur) {
		this.commentaire = commentaire;
		this.date_com = date_com;
		this.auteur = auteur;
	}
	
	public Commentaire(int id, String commentaire, Date date_com, User auteur) {
		this.id = id;
		this.commentaire = commentaire;
		this.date_com = date_com;
		this.auteur = auteur;
	}
	
	public Commentaire(int id, String commentaire, Date date_com, User auteur, int art_id) {
		this.id = id;
		this.commentaire = commentaire;
		this.date_com = date_com;
		this.auteur = auteur;
		this.art_id = art_id;
	}
	
	public int getId() {
		return id;
	}
	public String getCommentaire() {
		return commentaire;
	}
	public void setCommentaire(String commentaire) {
		this.commentaire = commentaire;
	}
	public Date getDate_com() {
		return date_com;
	}
	public void setDate_com(Date date_com) {
		this.date_com = date_com;
	}
	public User getAuteur() {
		return auteur;
	}
	public void setAuteur(User auteur) {
		this.auteur = auteur;
	}

	@Override
	public String toString() {
		return "Commentaire " + id + " " + auteur.getId() + ", art_id : " + art_id  +"\n";
	}
}
