package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import controller.ArticleDao;
import controller.CommentaireDao;
import controller.UserDao;
import model.Article;
import model.Commentaire;
import model.User;

public class Admin extends JPanel {
	private JTable table_1;
	private int article_id;
	private int nbrCom;
	private int art;
	
	public Admin(User user) {
			setBackground(new Color(255, 255, 255));
			setBounds(0, 0,  1400, 805);
			setLayout(null);
			
			JPanel panel_tableau = new JPanel();
			panel_tableau.setBackground(new Color(255, 255, 255));
			panel_tableau.setBounds(0, 6,  1400, 862);
			add(panel_tableau);
			panel_tableau.setLayout(null);
			
			JLabel title_tableau = new JLabel("Hello ");
			title_tableau.setHorizontalAlignment(SwingConstants.CENTER);
			title_tableau.setFont(new Font("Montserrat", Font.PLAIN, 34));
			title_tableau.setBounds(424, 35, 488, 30);
			panel_tableau.add(title_tableau);
			
			JButton btn_add_art = new JButton("New article");
			btn_add_art.setBorder(null);
			btn_add_art.setBackground(new Color(255, 240, 245));
			btn_add_art.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					panel_tableau.removeAll();
					Add_article addArticle = new Add_article(user);
					panel_tableau.add(addArticle);
					panel_tableau.repaint();
					panel_tableau.revalidate();
				
				}
			});
			btn_add_art.setBounds(615, 675, 169, 55);
			panel_tableau.add(btn_add_art);
			
			JPanel tableau = new JPanel();
			tableau.setBackground(new Color(255, 240, 245));
			tableau.setBounds(57, 109, 1300, 505);
			panel_tableau.add(tableau);
			tableau.setLayout(null);

		
			JScrollPane scrollPane_1 = new JScrollPane();
			scrollPane_1.setBounds(0, 0, 1300, 511);
			tableau.add(scrollPane_1);
			
			table_1 = new JTable();
			table_1.getTableHeader().setBackground(Color.WHITE);
			scrollPane_1.setViewportView(table_1);
			table_1.setBackground(new Color(255, 240, 245));
			table_1.setRowHeight(100);
			table_1.setModel(liste());
			

			String imgUrl="/img/compte.png";		
			Image img;
			try {
				img = ImageIO.read(getClass().getResource(imgUrl));
				JLabel lblCompte = new JLabel("   Admin " + user.getPrenom());
				lblCompte.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						panel_tableau.removeAll();
						Infos_user infos = new Infos_user(user);
						panel_tableau.add(infos);
						panel_tableau.repaint();
						panel_tableau.revalidate();
						
					}
				});
				lblCompte.setBounds(1172, 35, 185, 44);
				panel_tableau.add(lblCompte);
				lblCompte.setIcon(new ImageIcon(img));	
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			
			table_1.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					int id = table_1.getSelectedRow();
					article_id =  (int) table_1.getModel().getValueAt(id, 0);
					panel_tableau.removeAll();
					Article_details show = new Article_details(article_id, user);
					panel_tableau.add(show);
					panel_tableau.repaint();
					panel_tableau.revalidate();	
					
				}
			});
			
				ArticleDao readArt = new ArticleDao();
				List<Article> listArticle = new ArrayList<>();
				listArticle.addAll(readArt.read());
				
				UserDao userDao = new UserDao();
				ArrayList<User> listUser = new ArrayList<>();
				listUser.addAll(userDao.read());
				
				JLabel Nbr_art_user = new JLabel("NOMBRE D'ARTICLE: " + listArticle.size() + "     NOMBRE D'UTILISATEUR: " + listUser.size());
				Nbr_art_user.setFont(new Font("Avenir", Font.PLAIN, 16));
				Nbr_art_user.setBounds(57, 634, 650, 34);
				panel_tableau.add(Nbr_art_user);
		
		}
		
		public DefaultTableModel liste() {
			String [] col = {"ID","Titre","R??sum??", "Date cr??ation", "Auteur","Nbr Commentaire","Show","Update", "Delete"};
			DefaultTableModel tab = new DefaultTableModel(null, col);
			ArticleDao readArt = new ArticleDao();
			List<Article> listArticle = new ArrayList<>();
			listArticle.addAll(readArt.read());
			
			for (Article article : listArticle) {
				art = article.getId();
				CommentaireDao comDao = new CommentaireDao();
				ArrayList<Commentaire> listCom = new ArrayList<>();
				listCom.addAll(comDao.read(art));
				nbrCom = listCom.size();
				 
				 tab.addRow(new Object[] {
						 article.getId(),
						 article.getTitre(),
						 article.getResume(),
						 article.getDate_creation(),
						 article.getAuteur().getPrenom(),
						 nbrCom,
						 "D??tail",
						 "Modifier l'article",
						 "Supprimer l'article"
						 });
		} return tab;		
	}
}