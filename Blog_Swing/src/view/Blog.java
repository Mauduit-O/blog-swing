package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.UserDao;
import model.User;

import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.regex.Pattern;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Font;
import javax.swing.UIManager;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JCheckBox;

public class Blog extends JFrame {

	private JPanel contentPane_register;
	private JTextField input_name;
	private JTextField input_prenom;
	private JTextField Input_mail;
	private JPasswordField input_mdp;
	private User user;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		 try {
			    UIManager.setLookAndFeel( UIManager.getCrossPlatformLookAndFeelClassName() );
			 } catch (Exception e) {
			            e.printStackTrace();
			 }
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Blog frame = new Blog();
					frame.setVisible(true);
					frame.setLocationRelativeTo(null);
					frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Blog() {
		JPanel panel_inscription = new JPanel();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100,1400, 900);
		contentPane_register = new JPanel();
		contentPane_register.setBackground(new Color(255, 255, 255));
		contentPane_register.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane_register);
		contentPane_register.setLayout(null);
		
		JPanel panel_login_logup = new JPanel();
		panel_login_logup.setLayout(null);
		panel_login_logup.setBackground(new Color(255, 240, 245));
		panel_login_logup.setBounds(0, 0, 1400, 39);
		contentPane_register.add(panel_login_logup);
		
		JLabel inscription_menu = new JLabel("INSCRIPTION");
		inscription_menu.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				JLayeredPane panel_connection = new JLayeredPane();
				panel_connection.removeAll();
				panel_connection.add(panel_inscription);
				panel_connection.repaint();
				panel_connection.revalidate();
			}
		});
		
		
		inscription_menu.setFont(new Font("Dialog", Font.BOLD, 16));
		inscription_menu.setForeground(new Color(255, 182, 193));
		inscription_menu.setBounds(529, 12, 122, 16);
		panel_login_logup.add(inscription_menu);
		
		JLabel login_menu = new JLabel("CONNECTION");
		login_menu.setFont(new Font("Dialog", Font.BOLD, 16));
		login_menu.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				panel_inscription.removeAll();
				Login login = new Login(user);
				panel_inscription.add(login);
				panel_inscription.repaint();
				panel_inscription.revalidate();
			}
		});
		login_menu.setForeground(new Color(255, 182, 193));
		login_menu.setBounds(824, 12, 130, 16);
		panel_login_logup.add(login_menu);
		
		
		JLayeredPane layer_inscription = new JLayeredPane();
		layer_inscription.setBounds(0, 55, 1400, 805);
		contentPane_register.add(layer_inscription);
		layer_inscription.setLayout(null);
		
		panel_inscription.setLayout(null);
		panel_inscription.setBackground(new Color(255, 240, 245));
		panel_inscription.setBounds(0, 0, 1400, 805);
		layer_inscription.add(panel_inscription);
		
		JLabel RegisterTitle = new JLabel("Inscription");
		RegisterTitle.setFont(new Font("Avenir", Font.PLAIN, 34));
		RegisterTitle.setBounds(616, 78, 194, 27);
		panel_inscription.add(RegisterTitle);
		
		JLabel name = new JLabel("Nom");
		name.setFont(new Font("Avenir", Font.PLAIN, 16));
		name.setBounds(461, 185, 82, 16);
		panel_inscription.add(name);
		
		input_name = new JTextField();
		input_name.setBorder(null);
		input_name.setColumns(10);
		input_name.setBounds(655, 174, 266, 37);
		panel_inscription.add(input_name);
		
		
		JLabel prenom = new JLabel("Prenom");
		prenom.setFont(new Font("Avenir", Font.PLAIN, 16));
		prenom.setBounds(461, 278, 82, 16);
		panel_inscription.add(prenom);
		
		input_prenom = new JTextField();
		input_prenom.setBorder(null);
		input_prenom.setColumns(10);
		input_prenom.setBounds(655, 266, 266, 37);
		panel_inscription.add(input_prenom);
		
		JLabel email = new JLabel("Email");
		email.setFont(new Font("Avenir", Font.PLAIN, 16));
		email.setBounds(461, 363, 82, 16);
		panel_inscription.add(email);
		
		Input_mail = new JTextField();
		Input_mail.setBorder(null);
		Input_mail.setColumns(10);
		Input_mail.setBounds(655, 352, 266, 37);
		panel_inscription.add(Input_mail);
		
		JLabel mdp = new JLabel("Mot de passe");
		mdp.setFont(new Font("Avenir", Font.PLAIN, 16));
		mdp.setBounds(461, 449, 110, 16);
		panel_inscription.add(mdp);
		
		input_mdp = new JPasswordField();
		input_mdp.setBorder(null);
		input_mdp.setBounds(655, 438, 266, 37);
		panel_inscription.add(input_mdp);
		
		JCheckBox admin = new JCheckBox("   Admin");
		admin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		admin.setFont(new Font("Avenir", Font.BOLD, 16));
		admin.setBackground(Color.WHITE);
		admin.setBounds(461, 521, 113, 37);
		panel_inscription.add(admin);
		
		JButton btn_creer = new JButton("CR??ER MON COMPTE");
		btn_creer.setBorder(null);
		btn_creer.setFont(new Font("Avenir", Font.BOLD, 12));
		btn_creer.setBackground(new Color(255, 255, 255));
		btn_creer.setBounds(616, 596, 178, 43);
	
		btn_creer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UserDao userDao = new UserDao();
				User user = new User(input_name.getText(), input_prenom.getText(), Input_mail.getText(), String.valueOf(input_mdp.getPassword()), admin.isSelected());
				Pattern ptr = Pattern.compile("^[a-zA-Z0-9_.-]+[@][a-zA-Z0-9-]+[.]+[a-zA-Z0-9]+$");
				
				if(!ptr.matcher(Input_mail.getText()).matches()) {
					JOptionPane.showMessageDialog(null, "L'email saisie n'est pas valide","Error",JOptionPane.ERROR_MESSAGE);
				}else {					
					
					if(!userDao.verifMail(Input_mail.getText())) {
						userDao.create(user);						
						JOptionPane.showMessageDialog(null, "Bienvenue , " + input_prenom.getText() + "! \n votre compte ?? ??t?? cr????.");
						
						panel_inscription.removeAll();
						Login login = new Login(user);
						panel_inscription.add(login);
						panel_inscription.repaint();
						panel_inscription.revalidate();
					}else {
						JOptionPane.showMessageDialog(null, "Ce mail existe deja !","Error",JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});
		panel_inscription.add(btn_creer);

	}
}
