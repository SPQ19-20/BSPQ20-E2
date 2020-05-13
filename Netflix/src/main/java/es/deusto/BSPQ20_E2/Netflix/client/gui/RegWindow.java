package es.deusto.BSPQ20_E2.Netflix.client.gui;

import javax.swing.JFrame;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.logging.Level;
import org.apache.log4j.Logger;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;

import es.deusto.BSPQ20_E2.Netflix.server.db.DB;

import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;

/**
 * 
 * Window to be displayed if a new user wants to be registered. The user itself
 * will have to introduce several information such as username, password,
 * salary...
 * 
 * @category GUI
 * @author Jorge El Busto
 * @see Register Window.
 */
public class RegWindow extends JFrame {
	private final static Logger LOGGER = Logger.getLogger(RegWindow.class.getName());
	private Font f;
	private JTextField tfUser;
	private JTextField tfName;
	private JTextField tfSalary;
	private JTextField tfSurname;
	private JPasswordField tfPassword;

	public RegWindow() {

		setResizable(false);
		getContentPane().setBackground(Color.BLACK);
		getContentPane().setLayout(null);
		try {
			f = Font.createFont(Font.TRUETYPE_FONT,
					new FileInputStream(new File("src/main/resources/Bebas-Regular.ttf"))).deriveFont(Font.PLAIN, 50);
		} catch (FontFormatException | IOException e1) {
			// TODO Auto-generated catch block
			LOGGER.warn(e1.getMessage());
		}
		JLabel lblNewLabel = new JLabel("NETFLIX");
		lblNewLabel.setForeground(Color.RED);
		lblNewLabel.setFont(f);
		lblNewLabel.setBounds(97, 26, 174, 66);
		getContentPane().add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("User:");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_1.setForeground(Color.RED);
		lblNewLabel_1.setBounds(23, 128, 116, 14);
		getContentPane().add(lblNewLabel_1);

		JLabel lblName = new JLabel("Name:");
		lblName.setHorizontalAlignment(SwingConstants.RIGHT);
		lblName.setForeground(Color.RED);
		lblName.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblName.setBounds(23, 159, 116, 14);
		getContentPane().add(lblName);

		JLabel lblSalary = new JLabel("Salary:");
		lblSalary.setHorizontalAlignment(SwingConstants.RIGHT);
		lblSalary.setForeground(Color.RED);
		lblSalary.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblSalary.setBounds(23, 252, 116, 14);
		getContentPane().add(lblSalary);

		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setHorizontalAlignment(SwingConstants.RIGHT);
		lblPassword.setForeground(Color.RED);
		lblPassword.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblPassword.setBounds(23, 221, 116, 14);
		getContentPane().add(lblPassword);

		tfUser = new JTextField();
		tfUser.setBounds(149, 127, 174, 20);
		getContentPane().add(tfUser);
		tfUser.setColumns(10);

		tfName = new JTextField();
		tfName.setColumns(10);
		tfName.setBounds(149, 158, 174, 20);
		getContentPane().add(tfName);

		tfSalary = new JTextField();
		tfSalary.setColumns(10);
		tfSalary.setBounds(149, 251, 174, 20);
		getContentPane().add(tfSalary);

		tfSurname = new JTextField();
		tfSurname.setColumns(10);
		tfSurname.setBounds(149, 189, 174, 20);
		getContentPane().add(tfSurname);

		JLabel lblSurname = new JLabel("Surname");
		lblSurname.setHorizontalAlignment(SwingConstants.RIGHT);
		lblSurname.setForeground(Color.RED);
		lblSurname.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblSurname.setBounds(23, 190, 116, 14);
		getContentPane().add(lblSurname);
		JButton btnNewButton = new JButton("Register");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				/**
				 * Action listener to retrieve the data from several text fields and to pass
				 * them into the DB user registering method.
				 */
				if((tfUser.getText()!="")&&(tfName.getText()!="")&&(tfSurname.getText()!="")&&(tfPassword.getText()!="")&&(tfSalary.getText()!="")) {
					DB.register(tfUser.getText(), tfName.getText(), tfSurname.getText(), tfPassword.getText(),
							Double.parseDouble(tfSalary.getText()));
					dispose();
					Login l = new Login();
					l.setVisible(true);
				} else JOptionPane.showMessageDialog(null, "User could not be registered - missing parameters");
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnNewButton.setForeground(Color.RED);
		btnNewButton.setBackground(Color.LIGHT_GRAY);
		btnNewButton.setBounds(107, 305, 144, 23);
		getContentPane().add(btnNewButton);

		tfPassword = new JPasswordField();
		tfPassword.setBounds(149, 220, 174, 20);
		getContentPane().add(tfPassword);
		setSize(400, 403);
		setVisible(true);
	}
}
