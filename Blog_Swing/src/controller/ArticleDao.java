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
import model.User;

public class ArticleDao implements Idao<Article> {

	Connection connect = new Database_connection().getConnection();
	
	@Override
	public boolean create(Article article) {
		Boolean bool = false;
		try {
			PreparedStatement create_article = connect.prepareStatement("INSERT INTO article (titre, resume, contenu, date_creation, auteur) VALUES (?,?,?,now(),?)");
			create_article.setString(1, article.getTitre());
			create_article.setString(2, article.getResume());
			create_article.setString(3, article.getContenu());
			create_article.setObject(4, article.getAuteur().getId());
			// article.getAuteur().getId() pour recupérer seulement l'id de lauteur et pas tout ce qui conecrne le user car user est l'auteur il a comme type user. mettre en int l'auteur ds la base de donnée
			create_article.executeUpdate();
			bool = true;
			
			} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return bool;
	}

	public List<Article> read() {
		List<Article> listeArticles = new ArrayList<>();
		
		try {
			PreparedStatement req = connect.prepareStatement("SELECT * FROM article INNER JOIN user"+
					" ON user.id=article.auteur");
			ResultSet rs = req.executeQuery();
			while(rs.next()) {
				Date sqlDate = (rs.getDate("date_creation"));
				
				Article article = new Article(
						rs.getInt("id"),
						rs.getString("titre"),
						rs.getString("resume"),
						rs.getString("contenu"),
						sqlDate,
						new User(rs.getInt("auteur"), rs.getString("nom"), rs.getString("prenom"), rs.getString("email"))
					);
				listeArticles.add(article);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return listeArticles;
	}
	
	public ArrayList<Article> findById(int i) {
		
		ArrayList<Article> liste_article = new ArrayList<>();
		
		try {
			PreparedStatement read_article = connect.prepareStatement("SELECT * FROM article Inner JOIN user On user.id=article.auteur WHERE article.id = ?"); //Inner JOIN user On user.id=article.id Where 
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
				
				liste_article.add(article);
			}
			read_article.close();
			
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("l'affichage n'a pas été effectuer");
		}
		return liste_article;
	}
	
	public Boolean update(Article article, int i) {
		try {	
			PreparedStatement update_article = connect.prepareStatement("UPDATE article SET titre = ?, resume = ?, contenu = ?, date_modif = now() WHERE id LIKE ? ");
			update_article.setString(1, article.getTitre());
			update_article.setString(2, article.getResume());
			update_article.setString(3, article.getContenu());
			update_article.setInt(4,i);
			update_article.executeUpdate();
			update_article.close();
				return true;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public Boolean delete(int i) {

		try {
			String queryDElete = "DELETE FROM article WHERE id = ?";
		    PreparedStatement delete_article = connect.prepareStatement(queryDElete);
		    delete_article.setInt(1, i);
		    delete_article.execute(); 
		    delete_article.close();
		    return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

}
