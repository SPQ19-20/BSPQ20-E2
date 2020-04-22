package es.deusto.BSPQ20_E2.Netflix.client.gui;
/**
 * Window used for the last step of the transaction of buying a film
 * @author Jorge El Busto
 */
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import es.deusto.BSPQ20_E2.Netflix.pojo.Film;
import es.deusto.BSPQ20_E2.Netflix.pojo.User;
import es.deusto.BSPQ20_E2.Netflix.server.db.DB;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class PaymentWindow extends JFrame {
	private JTextField tfUser;
	private JTextField tfPasswd;

	public PaymentWindow(User u, Film f) {
		setTitle("PayPal - Payment Window");
		setSize(350, 248);
		setResizable(false);
		getContentPane().setLayout(null);

		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBounds(0, 0, 344, 209);
		getContentPane().add(panel);
		panel.setLayout(null);

		JLabel lblNetflix = new JLabel("Pay");
		lblNetflix.setFont(new Font("Tahoma", Font.BOLD, 43));
		lblNetflix.setForeground(new Color(0, 0, 255));
		lblNetflix.setBounds(100, 31, 86, 70);
		panel.add(lblNetflix);

		JLabel lblUser = new JLabel("User:");
		lblUser.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblUser.setForeground(new Color(0, 0, 255));
		lblUser.setBounds(43, 132, 64, 36);
		panel.add(lblUser);

		tfUser = new JTextField();
		tfUser.setForeground(new Color(255, 255, 255));
		tfUser.setBackground(Color.DARK_GRAY);
		tfUser.setBounds(90, 141, 124, 20);
		panel.add(tfUser);
		tfUser.setColumns(10);

		JLabel lblPasswd = new JLabel("Password:");
		lblPasswd.setForeground(new Color(0, 0, 255));
		lblPasswd.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblPasswd.setBounds(10, 168, 77, 30);
		panel.add(lblPasswd);

		tfPasswd = new JPasswordField();
		tfPasswd.setForeground(new Color(255, 255, 255));
		tfPasswd.setBackground(Color.DARK_GRAY);
		tfPasswd.setBounds(90, 172, 124, 20);
		panel.add(tfPasswd);
		tfPasswd.setColumns(10);

		JButton btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				DB.buyFilm(f, u);
				u.setSalary(u.getSalary()-f.getPrice());
				JOptionPane.showMessageDialog(null, "Film " + f.getTitle() + " has been bought successfully");
				dispose();
				MainWindow updated = new MainWindow(u);
			}
		});
		btnLogin.setBackground(Color.BLACK);
		btnLogin.setForeground(new Color(0, 0, 255));
		btnLogin.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnLogin.setBounds(224, 174, 110, 20);
		panel.add(btnLogin);
		
		JLabel lblPal = new JLabel("Pal");
		lblPal.setForeground(new Color(30, 144, 255));
		lblPal.setFont(new Font("Tahoma", Font.BOLD, 43));
		lblPal.setBounds(179, 45, 96, 42);
		panel.add(lblPal);
		setVisible(true);
	}
}
