package es.deusto.BSPQ20_E2.Netflix.client.gui;

import java.awt.GridLayout;

import static javax.swing.JOptionPane.ERROR_MESSAGE;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

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

public class Login extends JFrame {
	private static final long serialVersionUID = 1L;
	private Client client;
	private JTextField tfUser;
	private JTextField tfPasswd;

	public Login() {

		client = ClientBuilder.newClient();

		final WebTarget appTarget = client.target("http://localhost:8080/myapp");
		final WebTarget usersTarget = appTarget.path("users");
		setSize(459, 248);
		setTitle("Netflix - Login");
		setResizable(false);
		getContentPane().setLayout(null);

		JPanel panel = new JPanel();
		panel.setBounds(0, 126, 434, 83);
		getContentPane().add(panel);
		panel.setLayout(null);

		JLabel lblUser = new JLabel("User:");
		lblUser.setBounds(10, 0, 119, 36);
		panel.add(lblUser);

		tfUser = new JTextField();
		tfUser.setBounds(129, 0, 129, 36);
		panel.add(tfUser);
		tfUser.setColumns(10);

		JLabel lblPasswd = new JLabel("Password:");
		lblPasswd.setBounds(10, 42, 119, 30);
		panel.add(lblPasswd);

		tfPasswd = new JTextField();
		tfPasswd.setBounds(129, 42, 129, 30);
		panel.add(tfPasswd);
		tfPasswd.setColumns(10);

		JPanel panel_1 = new JPanel();
		panel_1.setBounds(258, 0, 176, 72);
		panel.add(panel_1);
		panel_1.setLayout(new GridLayout(0, 2, 0, 0));

		JButton btnLogin = new JButton("Login");
		panel_1.add(btnLogin);

		JButton btnRegister = new JButton("Register");
		btnRegister.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				RegWindow w = new RegWindow();
				setVisible(false);
				dispose();

			}
		});
		panel_1.add(btnRegister);
	}
}
