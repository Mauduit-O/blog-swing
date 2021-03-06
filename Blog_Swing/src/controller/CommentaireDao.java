package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import Dao.Database_connection;
import Dao.Idao;
import model.Article;
import model.Commentaire;
import model.User;

public class CommentaireDao implements Idao<Commentaire>{
	
	Connection connect = new Database_connection().getConnection();
	
	@Override
	public boolean create(Commentaire commentaire) {
		Boolean bool = false;
		try {
			PreparedStatement create_com = connect.prepareStatement("INSERT INTO commentaires (commentaire, date, auteur, art_id) VALUES (?,now(),?,?)");
			create_com.setString(1, commentaire.getCommentaire());
			create_com.setObject(2, commentaire.getAuteur().getId());
			create_com.setInt(3, commentaire.getArt_id());
			create_com.executeUpdate();
			bool = true;
			
			} catch (SQLException e) {
			e.printStackTrace();
		}
		return bool;
	}
	
	public List<Commentaire> read(int article_id) {
		List<Commentaire> listeCom = new ArrayList<>();
		
		try {
			PreparedStatement req = connect.prepareStatement("	SELECT *, u.id AS user FROM commentaires c, user u WHERE art_id =? AND "
					+ "c.auteur = u.id");
			
			req.setInt(1, article_id);
			ResultSet rs = req.executeQuery();
			
			while(rs.next()) {
				Date sqlDate = (rs.getDate("date"));
				
				Commentaire com = new Commentaire(
						rs.getInt("id"),
						rs.getString("commentaire"),
						sqlDate,
						new User(rs.getInt("user"), rs.getString("nom"), rs.getString("prenom"), rs.getString("email")),
						rs.getInt("art_id")
						
					); 
				listeCom.add(com);
			} 
		}catch(Exception e) {
			e.printStackTrace();
		}
		return listeCom;
	}
	
	public ArrayList<Article> findById(int i) {
		ArrayList<Article> liste_com = new ArrayList<>();
		try {
			PreparedStatement read_article = connect.prepareStatement("SELECT * FROM commantaire Inner JOIN user On user.id=commantaire.auteur WHERE commantaire.id = ?"); //Inner JOIN user On user.id=article.id Where 
			read_article.setInt(1, i);
			ResultSet rs = read_article.executeQuery();
			
			while (rs.next()) {
				Article article = new Article();
				
				article.setId(rs.getInt("id"));
				article.setTitre(rs.getString("titre"));
				article.setResume(rs.getString("resume"));
				article.setContenu(rs.getString("contenu"));
				article.setDate_creation(rs.getDate("date_creation"));
				article.setAuteur( new User(rs.getInt("id"), rs.getString("nom"), rs.getString("prenom"), rs.getString("email")));
				
				liste_com.add(article);
			}
			read_article.close();
			
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("l'affichage n'a pas ??t?? effectuer");
		}
		return liste_com;
	}

}
