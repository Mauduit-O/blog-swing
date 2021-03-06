package view;

import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;

import controller.ArticleDao;
import controller.CommentaireDao;
import model.Article;
import model.Commentaire;
import model.User;

import javax.swing.JTextArea;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.JScrollPane;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.awt.event.ActionEvent;
import javax.swing.JTextPane;
import javax.swing.border.Border;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Article_details extends JPanel {
	private JTextField input_titre;
	private JTextField input_auteur;
	private JTextField input_date;
	
	/**
	 * Create the panel.
	 */
	@SuppressWarnings("unlikely-arg-type")
	public Article_details(int article_id,  User user) {
		setBounds(0, 0, 1400, 805);
		setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 1400, 805);
		add(panel);
		panel.setLayout(null);
		
		JPanel panel_article = new JPanel();
		panel_article.setBackground(new Color(255, 240, 245));
		panel_article.setBounds(0, 0, 755, 805);
		panel.add(panel_article);
		panel_article.setLayout(null);
		
		JLabel titre = new JLabel("Titre");
		titre.setFont(new Font("Avenir", Font.PLAIN, 22));
		titre.setBounds(128, 65, 88, 31);
		panel_article.add(titre);
		
		input_titre = new JTextField();
		input_titre.setBounds(261, 60, 332, 45);
		input_titre.setBorder(null);
		panel_article.add(input_titre);
		input_titre.setColumns(10);
		
		JLabel resume = new JLabel("Résumé");
		resume.setFont(new Font("Avenir", Font.PLAIN, 22));
		resume.setBounds(128, 137, 88, 31);
		panel_article.add(resume);
		
		input_auteur = new JTextField();
		input_auteur.setColumns(10);
		input_auteur.setBounds(261, 335, 332, 45);
		input_auteur.setBorder(null);
		panel_article.add(input_auteur);
		
		JLabel lblAuteur = new JLabel("Auteur");
		lblAuteur.setFont(new Font("Avenir", Font.PLAIN, 22));
		lblAuteur.setBounds(128, 347, 88, 16);
		panel_article.add(lblAuteur);
		
		input_date = new JTextField();
		input_date.setColumns(10);
		input_date.setBounds(261, 412, 332, 45);
		input_date.setBorder(null);
		panel_article.add(input_date);
		
		JLabel lblDate = new JLabel("Date");
		lblDate.setFont(new Font("Avenir", Font.PLAIN, 22));
		lblDate.setBounds(128, 417, 103, 31);
		panel_article.add(lblDate);
		
		JLabel lblContenu = new JLabel("Contenu");
		lblContenu.setFont(new Font("Avenir", Font.PLAIN, 22));
		lblContenu.setBounds(128, 507, 88, 22);
		panel_article.add(lblContenu);
		
		JTextArea input_resume = new JTextArea();
		input_resume.setBounds(261, 137, 332, 157);
		panel_article.add(input_resume);
		
		JTextArea input_contenu = new JTextArea();
		input_contenu.setBounds(261, 503, 332, 157);
		panel_article.add(input_contenu);
		
		
		
		//
		ArticleDao artDao = new ArticleDao();	
		
		ArrayList<Article> listAuteur = new ArrayList<>();
		listAuteur.addAll(artDao.read());
		for (Article e : listAuteur) {
			//int us = user.getId();
//			if ( e.getAuteur().getId() == user.getId()) {
//				System.out.println("c ok");
//				
//			} else {
//				System.out.println("vous n'étes pas l'auteur de larticle");
//			}
//			System.out.println("user"+user.getId());
//			System.out.println("auteur"+e.getAuteur());
	
		
			if( user.isAdmin() == true || user.getId() == e.getAuteur().getId() ) {
				JButton btn_modifier = new JButton("MODIFIER");
				btn_modifier.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						artDao.update(new Article(input_titre.getText(), input_resume.getText(), input_contenu.getText()), article_id);
						JOptionPane.showMessageDialog(null, "" + user.getPrenom() + "! \n Votre article a bien été modifié.");
					}
				});
				btn_modifier.setFont(new Font("Avenir", Font.PLAIN, 14));
				btn_modifier.setBackground(Color.WHITE);
				btn_modifier.setBounds(261, 699, 143, 44);
				panel_article.add(btn_modifier);
				
				JButton btn_supprimer = new JButton("SUPPRIMER");
				btn_supprimer.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						artDao.delete(article_id);
						JOptionPane.showMessageDialog(null, "" + user.getPrenom() + "! \n Votre article a bien été supprimé.");
					}
				});
				btn_supprimer.setFont(new Font("Avenir", Font.PLAIN, 14));
				btn_supprimer.setBackground(Color.WHITE);
				btn_supprimer.setBounds(450, 699, 143, 44);
				panel_article.add(btn_supprimer);
			} else {
				System.out.println("vous ne pouvez pas modifier larticle");
			}
		}
					
		ArticleDao articleDao = new ArticleDao();
		ArrayList<Article> list = new ArrayList<>();
		list.addAll(articleDao.findById(article_id));
		for (int i = 0; i < list.size(); i++) {
			input_titre.setText(list.get(i).getTitre());
			input_resume.setText(list.get(i).getResume());
			input_contenu.setText(list.get(i).getContenu());
			input_date.setText(list.get(i).getDate_creation().toString());
			input_auteur.setText(list.get(i).getAuteur().getPrenom());
		}
		
		JPanel panel_commentaire = new JPanel();
		panel_commentaire.setBackground(new Color(248, 248, 255));
		panel_commentaire.setBounds(755, 0, 755, 805);
		panel.add(panel_commentaire);
		panel_commentaire.setLayout(null);
		
		JLabel lblCommentaire = new JLabel("Commentaires");
		lblCommentaire.setFont(new Font("Avenir", Font.PLAIN, 32));
		lblCommentaire.setBounds(234, 8, 217, 70);
		panel_commentaire.add(lblCommentaire);
		
		JLabel lbladd_commentaire = new JLabel("Écriver votre commentaire");
		lbladd_commentaire.setFont(new Font("Avenir", Font.PLAIN, 18));
		lbladd_commentaire.setBounds(43, 440, 257, 25);
		panel_commentaire.add(lbladd_commentaire);
		
		JTextArea input_add_com = new JTextArea();
		input_add_com.setBounds(43, 477, 567, 153);
		panel_commentaire.add(input_add_com);

		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(43, 90, 544, 289);
		panel_commentaire.add(scrollPane);
		//scrollPane.setWheelScrollingEnabled(true);
		JPanel container_com = new JPanel();
		container_com.setBounds(52, 123, 537, 289);
		scrollPane.setViewportView(container_com);
		container_com.setBackground(Color.WHITE);
		container_com.setLayout(null);

		CommentaireDao comDao = new CommentaireDao();
		
		JButton btn_add_com = new JButton("AJOUTER");
		btn_add_com.setBackground(new Color(255, 255, 255));
		btn_add_com.setFont(new Font("Avenir", Font.PLAIN, 14));
		btn_add_com.setBounds(281, 662, 143, 44);
		panel_commentaire.add(btn_add_com);
		
		JLabel lRetour = new JLabel("RETOUR");
		lRetour.setBounds(518, 10, 122, 31);
		panel_commentaire.add(lRetour);
		lRetour.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(user.isAdmin() == true) {
					panel.removeAll();
					Admin admin = new Admin(user);
					panel.add(admin);
					panel.repaint();
					panel.revalidate();
				} else {
					panel.removeAll();
					Post post = new Post(user);
					panel.add(post);
					panel.repaint();
					panel.revalidate();
				}
			}
		});
		lRetour.setFont(new Font("Avenir", Font.PLAIN, 14));
		
		btn_add_com.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String com = input_add_com.getText();
				Commentaire addCommentaire = new Commentaire(com, user, article_id);
				System.out.println(user);
				if (comDao.create(addCommentaire)) {
					JOptionPane.showMessageDialog(btn_add_com, "Votre commentaire :  " +com+ " a bien été créé" );
					
				} else {
					 JOptionPane.showMessageDialog(null,"Error , ", "l'article n'a pas pu être ajouter", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
	
		ArrayList<JTextPane> liste = new ArrayList<JTextPane>();
		List<Commentaire> comm = comDao.read(article_id);
			for (int i = 0; i < comm.size(); i++) {
				String dateFormat = new SimpleDateFormat("dd/MM/yyyy").format((Date)comm.get(i).getDate_com());
				JTextPane jpostUser  = new JTextPane();
				jpostUser.setBackground(new Color(248, 248, 255));
				Border lineborder = BorderFactory.createLineBorder(Color.WHITE, 4); 
				jpostUser.setBorder(lineborder);
				jpostUser.setText("\n" + "    " + comm.get(i).getAuteur().getPrenom() + ", le : " + dateFormat + "\n" + "    " + comm.get(i).getCommentaire());
				jpostUser.setBounds(25,(i*70), 463, 70);
				liste.add(jpostUser);
		        liste.get(i);
				container_com.add(jpostUser);
			}
		
	}
}
