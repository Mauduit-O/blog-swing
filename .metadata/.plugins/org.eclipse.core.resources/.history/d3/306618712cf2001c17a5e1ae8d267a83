package view;

import java.awt.Color;

import javax.swing.JPanel;

import model.Article;
import model.User;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Frame;

import javax.swing.JButton;
import javax.swing.JFrame;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JTextField;

import controller.ArticleDao;
import controller.UserDao;

public class Infos_user extends JPanel {
	private JTextField input_nom;
	private JTextField input_prenom;
	private JTextField input_mail;

	/**
	 * Create the panel.
	 */
	public Infos_user(User user) {
		setBounds(0, 0,  1400, 805);
		setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 240, 245));
		panel.setBounds(0, 0,  1400, 805);
		panel.setLayout(null);
		add(panel);
		
		JLabel lblNewLabel = new JLabel("Mes informations");
		lblNewLabel.setFont(new Font("Avenir", Font.PLAIN, 32));
		lblNewLabel.setBounds(558, 89, 291, 62);
		panel.add(lblNewLabel);
		
		JLabel retour = new JLabel("RETOUR");
		retour.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				panel.removeAll();
				Post post = new Post(user);
				panel.add(post);
				panel.repaint();
				panel.revalidate();
				
			}
		});
		retour.setFont(new Font("Avenir", Font.PLAIN, 14));
		retour.setBounds(1275, 16, 104, 32);
		panel.add(retour);
		
		JLabel lPrenom = new JLabel("Prénom");
		lPrenom.setFont(new Font("Lucida Grande", Font.PLAIN, 18));
		lPrenom.setBounds(530, 270, 147, 43);
		panel.add(lPrenom);
		
		JLabel lNom = new JLabel("Nom");
		lNom.setFont(new Font("Lucida Grande", Font.PLAIN, 18));
		lNom.setBounds(530, 194, 147, 43);
		panel.add(lNom);
		
		JLabel lEmail = new JLabel("Email");
		lEmail.setFont(new Font("Lucida Grande", Font.PLAIN, 18));
		lEmail.setBounds(530, 359, 147, 43);
		panel.add(lEmail);
		
		JLabel lPwd = new JLabel("Mot de passe");
		lPwd.setFont(new Font("Lucida Grande", Font.PLAIN, 18));
		lPwd.setBounds(530, 452, 147, 43);
		panel.add(lPwd);
		
		JLabel UserPwd = new JLabel("**************");
		UserPwd.setFont(new Font("Lucida Grande", Font.PLAIN, 18));
		UserPwd.setBounds(764, 452, 245, 43);
		panel.add(UserPwd);
		
		input_nom = new JTextField();
		input_nom.setBounds(674, 194, 229, 46);
		input_nom.setBorder(null);
		panel.add(input_nom);
		input_nom.setColumns(10);
		
		input_prenom = new JTextField();
		input_prenom.setColumns(10);
		input_prenom.setBounds(674, 270, 229, 46);
		input_prenom.setBorder(null);
		panel.add(input_prenom);
		
		input_mail = new JTextField();
		input_mail.setColumns(10);
		input_mail.setBounds(674, 348, 229, 46);
		input_mail.setBorder(null);
		panel.add(input_mail);
		
		UserDao usdDao = new UserDao();
		ArrayList<User> list = new ArrayList<>();
		int ii = user.getId();
		list.add(usdDao.read(ii));
		for (int i = 0; i < list.size(); i++) {
			input_nom.setText(list.get(i).getNom());
			input_prenom.setText(list.get(i).getPrenom());
			input_mail.setText(list.get(i).getMail());
		}
		
		JButton btn_modif = new JButton("MODIFIER");
		btn_modif.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				usdDao.update(new User(input_nom.getText(), input_prenom.getText(), input_mail.getText()), user.getId());
				
			}
		});
		btn_modif.setBackground(new Color(255, 255, 255));
		btn_modif.setFont(new Font("Avenir", Font.PLAIN, 14));
		btn_modif.setBounds(530, 558, 147, 49);
		panel.add(btn_modif);
		
		JButton btn_supp = new JButton("SUPPRIMER");
		btn_supp.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				usdDao.delete(user.getId());
				JOptionPane.showMessageDialog(null, "Au revoir , " + user.getPrenom() + "! \n votre compte à été supprimé.");
				panel.removeAll();
				Login login = new Login();
				panel.add(login);
				panel.repaint();
				panel.revalidate();
			}
		});
		btn_supp.setBackground(new Color(255, 255, 255));
		btn_supp.setFont(new Font("Avenir", Font.PLAIN, 14));
		btn_supp.setBounds(729, 558, 147, 49);
		panel.add(btn_supp);

	}
}
