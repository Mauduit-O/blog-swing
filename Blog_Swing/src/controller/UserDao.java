package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;

import Dao.Database_connection;
import Dao.Idao;
import model.Article;
import model.Commentaire;
import model.User;

public class UserDao implements Idao<User> {
	
	Connection connect = new Database_connection().getConnection();
		
	@Override
	public boolean create(User user) {
		try { 
			PreparedStatement insert_user = connect.prepareStatement("INSERT INTO user (nom, prenom, email, pwd, admin) VALUES (?,?,?,PASSWORD(?), ?)");
	
				insert_user.setString(1, user.getNom());
				insert_user.setString(2, user.getPrenom());
				insert_user.setString(3, user.getMail());
				insert_user.setString(4, user.getPwd());
				insert_user.setBoolean(5, user.isAdmin());
				insert_user.executeUpdate();
				
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	

	public User read(int i) {
		User user = new User();
		try {
			PreparedStatement read_user = connect.prepareStatement("SELECT * FROM user WHERE id=? ");
			read_user.setInt(1, i);

			ResultSet rs = read_user.executeQuery();
			
			if(rs.next()) {
				user.setId(rs.getInt("id"));
				user.setNom(rs.getString("nom"));
				user.setPrenom(rs.getString("prenom"));
				user.setMail(rs.getString("email"));
				user.setPwd(rs.getString("pwd"));
				user.setAdmin(rs.getBoolean("admin"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return user;
	}
	
	public List<User> read() {
		List<User> listeUsers = new ArrayList<>();
		User user = new User();
		try {
			PreparedStatement req = connect.prepareStatement("SELECT * FROM user");
			ResultSet rs = req.executeQuery();
			while(rs.next()) {
				user.setId(rs.getInt("id"));
				user.setNom(rs.getString("nom"));
				user.setPrenom(rs.getString("prenom"));
				user.setMail(rs.getString("email"));
				user.setPwd(rs.getString("pwd"));
				user.setAdmin(rs.getBoolean("admin"));
				listeUsers.add(user);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return listeUsers;
	}
	
	
	public boolean verifLogin(String mail, String password) {
		User user = new User();
		Boolean bool = false;
		try {
			PreparedStatement verifLog = connect.prepareStatement("SELECT * FROM user WHERE email=? AND pwd=PASSWORD(?) ");
			verifLog.setString(1, mail);
			verifLog.setString(2, password);
			
			ResultSet rs = verifLog.executeQuery();
			
			if(rs.next()) {
				user.setId(rs.getInt("id"));
				user.setNom(rs.getString("nom"));
				user.setPrenom(rs.getString("prenom"));
				user.setMail(rs.getString("email"));
				user.setPwd(rs.getString("pwd"));
				user.setAdmin(rs.getBoolean("admin"));
				bool = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return bool;
	}
	
	
	public Boolean verifMail(String mail) {
		User user = new User();
		Boolean bool = false;
		try {
			PreparedStatement verifEmail = connect.prepareStatement("SELECT * FROM user WHERE email=? ");
			verifEmail.setString(1, mail);

			ResultSet rs = verifEmail.executeQuery();
			
			if(rs.next()) {
				user.setId(rs.getInt("id"));
				user.setNom(rs.getString("nom"));
				user.setPrenom(rs.getString("prenom"));
				user.setMail(rs.getString("email"));
				user.setPwd(rs.getString("pwd"));
				user.setAdmin(rs.getBoolean("admin"));
			bool = true;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return bool;
	}
	
	
	public User UserByMail(String mail) {
		User user = new User();
		try {
			PreparedStatement verifEmail = connect.prepareStatement("SELECT * FROM user WHERE email=? ");
			verifEmail.setString(1, mail);

			ResultSet rs = verifEmail.executeQuery();
			
			if(rs.next()) {
				user.setId(rs.getInt("id"));
				user.setNom(rs.getString("nom"));
				user.setPrenom(rs.getString("prenom"));
				user.setMail(rs.getString("email"));
				user.setPwd(rs.getString("pwd"));
				user.setAdmin(rs.getBoolean("admin"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return user;
	}
	
	public Boolean update(User user, int i) {
		try {	
			PreparedStatement update_user = connect.prepareStatement("UPDATE user SET nom = ?, prenom = ?, email = ? WHERE id LIKE ? ");
			update_user.setString(1, user.getNom());
			update_user.setString(2, user.getPrenom());
			update_user.setString(3, user.getMail());
			update_user.setInt(4,i);
			update_user.executeUpdate();
			update_user.close();
				return true;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public Boolean delete(int i) {

		try {
			String queryDElete = "DELETE FROM user WHERE id = ?";
		    PreparedStatement delete_user = connect.prepareStatement(queryDElete);
		    delete_user.setInt(1, i);
		    delete_user.execute(); 
		    delete_user.close();
		    return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
}