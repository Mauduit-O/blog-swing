package view;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import controller.UserDao;
import model.User;

import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.Color;
import java.awt.Component;

import javax.swing.JLayeredPane;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Login extends JPanel {
	public  JLayeredPane panel_connection = new JLayeredPane();
	private JTextField input_email;
	private JPasswordField input_pwd;

	public Login() {
		setBackground(new Color(255, 240, 245));
		setLayout(null);
		setBounds(0, 55, 1400, 805);
		panel_connection.setBackground(new Color(255, 228, 225));
		panel_connection.setBounds(0, 0, 1400, 799);
		add(panel_connection);
		
		JLabel pwd = new JLabel("Mot de passe");
		pwd.setFont(new Font("Avenir", Font.PLAIN, 16));
		pwd.setBounds(486, 331, 123, 16);
		panel_connection.add(pwd);
		
		JLabel loginTitle = new JLabel("Connection");
		loginTitle.setFont(new Font("Avenir", Font.PLAIN, 34));
		loginTitle.setBounds(596, 108, 194, 27);
		panel_connection.add(loginTitle);
		
		JLabel mail = new JLabel("Email");
		mail.setFont(new Font("Avenir", Font.PLAIN, 16));
		mail.setBounds(486, 225, 61, 16);
		panel_connection.add(mail);
		
		input_email = new JTextField();
		input_email.setColumns(10);
		input_email.setBounds(621, 210, 251, 43);
		input_email.setBorder(null);
		panel_connection.add(input_email);
		
		input_pwd = new JPasswordField();
		input_pwd.setBounds(621, 318, 251, 43);
		input_pwd.setBorder(null);
		panel_connection.add(input_pwd);
		
		JButton btn_login = new JButton("SE CONNECTER");
		btn_login.setBorder(null);
		btn_login.setFont(new Font("Avenir", Font.PLAIN, 14));
		btn_login.setBackground(new Color(255, 255, 255));
		btn_login.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String email_saisie = input_email.getText();
				String pwd_saisie = String.valueOf(input_pwd.getPassword());

				UserDao usDao = new UserDao();

				if(usDao.verifLogin(email_saisie, pwd_saisie)) {
					panel_connection.removeAll();
					Post post = new Post(usDao.UserByMail(email_saisie));
					panel_connection.add(post);
					panel_connection.repaint();
					panel_connection.revalidate();

				}else {
					JOptionPane.showMessageDialog(null, "Impossible de se connecter");
				}
			}
		});
		btn_login.setBackground(Color.WHITE);
		btn_login.setBounds(583, 442, 200, 50);
		panel_connection.add(btn_login);
	}
}
