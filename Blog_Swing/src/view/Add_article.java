package view;

import javax.swing.JPanel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;

import controller.ArticleDao;
import model.Article;
import model.User;

import javax.swing.JTextArea;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;

public class Add_article extends JPanel {
	private JTextField input_titre;

	/**
	 * Create the panel.
	 */
	public Add_article(User user) {
		
		System.out.println(user);
		setLayout(null);
		setBounds(0, 0, 1400, 836);
		
		JPanel panel_ajout_art = new JPanel();
		panel_ajout_art.setBackground(new Color(255, 240, 245));
		panel_ajout_art.setBounds(0, 0, 1400, 860);
		add(panel_ajout_art);
		panel_ajout_art.setLayout(null);
		
		JLabel title_add_article = new JLabel("Ajouter un article");
		title_add_article.setBounds(470, 60, 247, 27);
		panel_ajout_art.add(title_add_article);
		title_add_article.setFont(new Font("Avenir", Font.PLAIN, 28));
		
		JLabel label_title = new JLabel("Titre");
		label_title.setFont(new Font("Avenir", Font.PLAIN, 16));
		label_title.setBounds(335, 159, 61, 16);
		panel_ajout_art.add(label_title);
		
		JLabel label_resume = new JLabel("Résumé");
		label_resume.setFont(new Font("Avenir", Font.PLAIN, 16));
		label_resume.setBounds(335, 223, 61, 16);
		panel_ajout_art.add(label_resume);
		
		JLabel label_contenu = new JLabel("Contenu");
		label_contenu.setFont(new Font("Avenir", Font.PLAIN, 16));
		label_contenu.setBounds(335, 360, 61, 16);
		panel_ajout_art.add(label_contenu);
		
		input_titre = new JTextField();
		input_titre.setColumns(10);
		input_titre.setBounds(418, 144, 400, 47);
		input_titre.setBorder(null);
		panel_ajout_art.add(input_titre);
		
		JTextArea input_contenu = new JTextArea();
		input_contenu.setBounds(335, 388, 483, 284);
		panel_ajout_art.add(input_contenu);
		
		JTextArea input_resume = new JTextArea();
		input_resume.setBounds(335, 251, 483, 84);
		panel_ajout_art.add(input_resume);
		
		JButton add_article = new JButton("AJOUTER");
		add_article.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String titre = input_titre.getText();
				String resume = input_contenu.getText();
				String contenu = input_contenu.getText();
				
				Article addarticle = new Article(titre, resume, contenu, user);
				ArticleDao artDao = new ArticleDao();
				
				if (artDao.create(addarticle)) {
					JOptionPane.showMessageDialog(add_article, "Votre article " +titre+ " a bien été créé" );
					panel_ajout_art.removeAll();
					Post post = new Post(user);
					panel_ajout_art.add(post);
					panel_ajout_art.repaint();
					panel_ajout_art.revalidate();
					
				} else {
					 JOptionPane.showMessageDialog(null,"Error , ", "l'article n'a pas pu être ajouter", JOptionPane.ERROR_MESSAGE);
				}
				
			}
		});
		add_article.setBackground(new Color(255, 255, 255));
		add_article.setFont(new Font("Avenir", Font.PLAIN, 14));
		add_article.setBounds(423, 696, 145, 47);
		panel_ajout_art.add(add_article);
		
		JButton btn_retour = new JButton("RETOUR");
		btn_retour.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panel_ajout_art.removeAll();
				Post post = new Post(user);
				panel_ajout_art.add(post);
				panel_ajout_art.repaint();
				panel_ajout_art.revalidate();
			}
		});
		btn_retour.setFont(new Font("Avenir", Font.PLAIN, 14));
		btn_retour.setBackground(Color.WHITE);
		btn_retour.setBounds(605, 696, 145, 47);
		panel_ajout_art.add(btn_retour);

	}
}
