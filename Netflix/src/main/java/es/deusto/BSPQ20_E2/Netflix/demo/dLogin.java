package es.deusto.BSPQ20_E2.Netflix.demo;

import java.awt.GridLayout;

import static javax.swing.JOptionPane.ERROR_MESSAGE;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.sql.Connection;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.ws.rs.ProcessingException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import java.util.List;

import es.deusto.BSPQ20_E2.Netflix.pojo.User;
import java.awt.Font;
import java.awt.Color;
import javax.swing.UIManager;

public class dLogin extends JFrame {
	private static final long serialVersionUID = 1L;
	private Client client;
	private JTextField tfUser;
	private JTextField tfPasswd;

	public dLogin() {

		client = ClientBuilder.newClient();

		final WebTarget appTarget = client.target("http://localhost:8080/myapp");
		final WebTarget usersTarget = appTarget.path("users");
		setSize(459, 248);
		setTitle("Netflix - Login");
		setResizable(false);
		getContentPane().setLayout(null);

		JPanel panel = new JPanel();
		panel.setBackground(Color.BLACK);
		panel.setBounds(0, 0, 453, 219);
		getContentPane().add(panel);
		panel.setLayout(null);

		JLabel lblNetflix = new JLabel("Netflix");
		lblNetflix.setFont(new Font("Tahoma", Font.BOLD, 50));
		lblNetflix.setForeground(Color.RED);
		lblNetflix.setBounds(145, 36, 188, 68);
		panel.add(lblNetflix);

		tfUser = new JTextField();
		tfUser.setForeground(new Color(255, 255, 255));
		tfUser.setBackground(Color.DARK_GRAY);
		tfUser.setBounds(121, 139, 123, 20);
		panel.add(tfUser);
		tfUser.setColumns(10);

		JLabel lblPasswd = new JLabel("Password:");
		lblPasswd.setForeground(Color.RED);
		lblPasswd.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblPasswd.setBounds(37, 178, 88, 25);
		panel.add(lblPasswd);

		tfPasswd = new JPasswordField();
		tfPasswd.setForeground(new Color(255, 255, 255));
		tfPasswd.setBackground(Color.DARK_GRAY);
		tfPasswd.setBounds(121, 183, 123, 20);
		panel.add(tfPasswd);
		tfPasswd.setColumns(10);

		JLabel lblUser = new JLabel("User:");
		lblUser.setForeground(Color.RED);
		lblUser.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblUser.setBounds(68, 131, 57, 36);
		panel.add(lblUser);

		JButton btnLogin = new JButton("Login");
		btnLogin.setForeground(Color.RED);
		btnLogin.setBackground(Color.BLACK);
		btnLogin.setBounds(259, 178, 88, 30);
		panel.add(btnLogin);
		btnLogin.setFont(new Font("Tahoma", Font.BOLD, 14));

		JButton btnRegister = new JButton("Register");
		btnRegister.setIcon(null);
		btnRegister.setBackground(UIManager.getColor("Button.focus"));
		btnRegister.setForeground(Color.RED);
		btnRegister.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnRegister.setBounds(357, 178, 81, 30);
		panel.add(btnRegister);
		btnRegister.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				RegWindow w = new RegWindow();
				setVisible(false);
				dispose();

			}
		});
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Connection con;
				try {
					con = DemoDB.connect();
					User u = DemoDB.logged(con, tfUser.getText(), tfPasswd.getText());
					if (u != null) {
						MainWindow mw = new MainWindow(u);
						dispose();
					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		});
	}
}
