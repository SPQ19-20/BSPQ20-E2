package es.deusto.BSPQ20_E2.Netflix.client.gui;

/**

 * Window to be displayed in the last step of buying a film and storing the transaction in the DB.
 * @category GUI
 * @author Jorge El Busto
 * @see Payment Window.
 */
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import es.deusto.BSPQ20_E2.Netflix.client.Internationalization;
import es.deusto.BSPQ20_E2.Netflix.pojo.Film;
import es.deusto.BSPQ20_E2.Netflix.pojo.User;
import es.deusto.BSPQ20_E2.Netflix.server.db.DB;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.util.Locale;
import java.util.ResourceBundle;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;

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

		JLabel lblUser = new JLabel(Internationalization.resourceBundle.getString("lblUser"));
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

		JLabel lblPasswd = new JLabel(Internationalization.resourceBundle.getString("lblPasswd"));
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

		JButton btnLogin = new JButton(Internationalization.resourceBundle.getString("btnLogin"));
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				/**
				 * After logging in again due to security issues, film will be bought. Each 5
				 * films bought, the app will implement a reward system in which the user will
				 * be gifted with a new film.
				 */
				if ((tfUser.getText().equals(u.getCode())) && (tfPasswd.getText().equals(u.getPassword()))) {
					DB.buyFilm(f, u);
					JOptionPane.showMessageDialog(null, "Film " + f.getTitle() + " has been bought successfully");
					if (DB.getBoughtFilmsCount(u) > 0 && DB.getBoughtFilmsCount(u) % 5 == 0) {
						JOptionPane.showMessageDialog(null,
								"Congratulations, you've bought " + DB.getBoughtFilmsCount(u) + " films! You will get "
										+ DB.freeFilm(u).getTitle() + " for free.");
					}
					dispose();
					MainWindow updated = new MainWindow(u);
				} else
					JOptionPane.showMessageDialog(null,
							"Wrong user or password. Buying process could not be completed.");

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

		JComboBox comboBox = new JComboBox(Internationalization.Idiomas);
		comboBox.addActionListener(new ActionListener() {
			/**
			 * Combobox is configured for internationalisation like in the other windows.
			 */
			public void actionPerformed(ActionEvent e) {
				Internationalization.resourceBundle = ResourceBundle.getBundle("SystemMessages",
						Locale.forLanguageTag(comboBox.getSelectedItem().toString()), Internationalization.loader);
				lblUser.setText(Internationalization.resourceBundle.getString("lblUser"));
				lblPasswd.setText(Internationalization.resourceBundle.getString("lblPasswd"));
				btnLogin.setText(Internationalization.resourceBundle.getString("btnLogin"));
				revalidate();
			}
		});
		comboBox.setBounds(282, 6, 52, 27);
		panel.add(comboBox);
		setVisible(true);
	}
}
