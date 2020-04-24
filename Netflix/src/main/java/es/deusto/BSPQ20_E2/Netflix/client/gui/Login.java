package es.deusto.BSPQ20_E2.Netflix.client.gui;

import java.awt.GridLayout;

import static javax.swing.JOptionPane.ERROR_MESSAGE;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
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
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import es.deusto.BSPQ20_E2.Netflix.pojo.User;
import es.deusto.BSPQ20_E2.Netflix.server.db.DB;
import es.deusto.BSPQ20_E2.Netflix.client.*;

import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.awt.Color;
import javax.swing.UIManager;
import javax.swing.SwingConstants;
import javax.swing.JComboBox;

/**
 * Window for the login process
 * 
 * @author Jorge El Busto
 *
 */
public class Login extends JFrame {
	private final static Logger LOGGER = Logger.getLogger(Login.class.getName());
	private static final long serialVersionUID = 1L;
	private Client client;
	private JTextField tfUser;
	private JTextField tfPasswd;
	private Font f;
	private URLClassLoader loader;
	

	public Login() {
		try {
			f = Font.createFont(Font.TRUETYPE_FONT,
					new FileInputStream(new File("src/main/resources/Bebas-Regular.ttf"))).deriveFont(Font.PLAIN, 50);
		} catch (FontFormatException | IOException e1) {
			// TODO Auto-generated catch block
			LOGGER.log(Level.WARNING, e1.getMessage());
		}
		client = ClientBuilder.newClient();

		final WebTarget appTarget = client.target("http://localhost:8080/myapp");
		final WebTarget usersTarget = appTarget.path("users");
		setSize(479, 264);
		setTitle("Netflix - Login");
		setResizable(false);
		getContentPane().setLayout(null);
		try {
			File file = new File("src/main/resources/");
			URL[] urls = { file.toURI().toURL() };
			loader = new URLClassLoader(urls);
			Locale.setDefault(Locale.forLanguageTag("en"));
			Internationalization.resourceBundle = ResourceBundle.getBundle("SystemMessages", Locale.getDefault(), loader);
			LOGGER.log(Level.INFO, "Language: " + Internationalization.resourceBundle.getLocale().toString());
		} catch (Exception o) {
			LOGGER.log(Level.WARNING, o.getMessage());
		}
		JPanel panel = new JPanel();
		panel.setBackground(Color.BLACK);
		panel.setBounds(0, 0, 473, 235);
		getContentPane().add(panel);
		panel.setLayout(null);

		JLabel lblNetflix = new JLabel("Netflix");
		lblNetflix.setHorizontalAlignment(SwingConstants.CENTER);
		lblNetflix.setFont(f);
		lblNetflix.setForeground(Color.RED);
		lblNetflix.setBounds(145, 36, 188, 68);
		panel.add(lblNetflix);

		tfUser = new JTextField();
		tfUser.setForeground(new Color(255, 255, 255));
		tfUser.setBackground(Color.DARK_GRAY);
		tfUser.setBounds(121, 139, 123, 20);
		panel.add(tfUser);
		tfUser.setColumns(10);

		JLabel lblPasswd = new JLabel(Internationalization.resourceBundle.getString("lblPasswd"));
		lblPasswd.setHorizontalAlignment(SwingConstants.RIGHT);
		lblPasswd.setForeground(Color.RED);
		lblPasswd.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblPasswd.setBounds(28, 178, 88, 25);
		panel.add(lblPasswd);

		tfPasswd = new JPasswordField();
		tfPasswd.setForeground(new Color(255, 255, 255));
		tfPasswd.setBackground(Color.DARK_GRAY);
		tfPasswd.setBounds(121, 183, 123, 20);
		panel.add(tfPasswd);
		tfPasswd.setColumns(10);

		JLabel lblUser = new JLabel(Internationalization.resourceBundle.getString("lblUser"));
		lblUser.setHorizontalAlignment(SwingConstants.RIGHT);
		lblUser.setForeground(Color.RED);
		lblUser.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblUser.setBounds(28, 131, 88, 36);
		panel.add(lblUser);

		JButton btnLogin = new JButton(Internationalization.resourceBundle.getString("btnLogin"));
		btnLogin.setForeground(Color.RED);
		btnLogin.setBackground(Color.BLACK);
		btnLogin.setBounds(259, 178, 88, 30);
		panel.add(btnLogin);
		btnLogin.setFont(new Font("Tahoma", Font.BOLD, 11));

		JButton btnRegister = new JButton(Internationalization.resourceBundle.getString("btnRegister"));
		btnRegister.setBackground(Color.BLACK);
		btnRegister.setForeground(Color.RED);
		btnRegister.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnRegister.setBounds(357, 178, 106, 30);
		panel.add(btnRegister);

		JComboBox comboBox = new JComboBox(Internationalization.Idiomas);
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Internationalization.resourceBundle = ResourceBundle.getBundle("SystemMessages", Locale.forLanguageTag(comboBox.getSelectedItem().toString()), loader);
				lblUser.setText(Internationalization.resourceBundle.getString("lblUser"));
				lblPasswd.setText(Internationalization.resourceBundle.getString("lblPasswd"));
				btnRegister.setText(Internationalization.resourceBundle.getString("btnRegister"));
				btnLogin.setText(Internationalization.resourceBundle.getString("btnLogin"));
				revalidate();
			}
		});
		comboBox.setBounds(415, 6, 52, 27);
		panel.add(comboBox);

		btnRegister.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				RegWindow w = new RegWindow();
				dispose();

			}
		});
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Connection con;
				try {
					User u = DB.logged(tfUser.getText(), tfPasswd.getText());
					if (u.getSalary() > 0) {
						LOGGER.log(Level.INFO, "Logged in with " + u.toString());
						MainWindow mw = new MainWindow(u);
						dispose();
					} else
						JOptionPane.showMessageDialog(null, "Wrong password or username.");
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		});
	}
}