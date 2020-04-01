package es.deusto.BSPQ20_E2.Netflix.gui;

import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class PaymentWindow extends JFrame {
	private JTextField tfUser;
	private JTextField tfPasswd;
	public PaymentWindow() {
		setTitle("PayPal - Payment Window");
		setSize(350,248);
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
		panel_1.setBounds(258, 0, 166, 72);
		panel.add(panel_1);
		panel_1.setLayout(new GridLayout(0, 2, 0, 0));
		
		JButton btnLogin = new JButton("Login");
		panel_1.add(btnLogin);
	}
}
