package es.deusto.BSPQ20_E2.Netflix.client.gui;

import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.Color;
import java.awt.Font;

public class PaymentWindow extends JFrame {
	private JTextField tfUser;
	private JTextField tfPasswd;
	public PaymentWindow() {
		setTitle("PayPal - Payment Window");
		setSize(350,248);
		setResizable(false);
		getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.BLACK);
		panel.setBounds(0, 0, 344, 209);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblNetflix = new JLabel("Netflix");
		lblNetflix.setFont(new Font("Tahoma", Font.BOLD, 43));
		lblNetflix.setForeground(Color.RED);
		lblNetflix.setBounds(100, 31, 171, 70);
		panel.add(lblNetflix);
		
		JLabel lblUser = new JLabel("User:");
		lblUser.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblUser.setForeground(Color.RED);
		lblUser.setBounds(43, 132, 64, 36);
		panel.add(lblUser);
		
		tfUser = new JTextField();
		tfUser.setBackground(Color.DARK_GRAY);
		tfUser.setBounds(90, 141, 124, 20);
		panel.add(tfUser);
		tfUser.setColumns(10);
		
		JLabel lblPasswd = new JLabel("Password:");
		lblPasswd.setForeground(Color.RED);
		lblPasswd.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblPasswd.setBounds(10, 168, 77, 30);
		panel.add(lblPasswd);
		
		tfPasswd = new JTextField();
		tfPasswd.setBackground(Color.DARK_GRAY);
		tfPasswd.setBounds(90, 172, 124, 20);
		panel.add(tfPasswd);
		tfPasswd.setColumns(10);
		
		JButton btnLogin = new JButton("Login");
		btnLogin.setBackground(Color.BLACK);
		btnLogin.setForeground(Color.RED);
		btnLogin.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnLogin.setBounds(224, 174, 110, 20);
		panel.add(btnLogin);
	}
}
