package es.deusto.BSPQ20_E2.Netflix.demo;

import javax.swing.JFrame;

import es.deusto.BSPQ20_E2.Netflix.pojo.User;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.JPanel;

public class MainWindow extends JFrame {
private int rowCount;
	public MainWindow(User u) {
		setSize(700,500);
		setResizable(false);
		getContentPane().setLayout(null);
		
		JLabel lblHello = new JLabel("Hello, " + u.getName());
		lblHello.setHorizontalAlignment(SwingConstants.RIGHT);
		lblHello.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblHello.setBounds(554, 11, 130, 14);
		getContentPane().add(lblHello);
		
		JLabel lblYourCurrentSalary = new JLabel("Your current salary is " + u.getSalary() + "$");
		lblYourCurrentSalary.setHorizontalAlignment(SwingConstants.RIGHT);
		lblYourCurrentSalary.setBounds(480, 36, 204, 14);
		getContentPane().add(lblYourCurrentSalary);
		
		JPanel panel = new JPanel();
		String[] colNames = {"Title", "Genre", "Director", "Year", "Price"};
		DefaultTableModel table = new DefaultTableModel(colNames, rowCount);
		panel.setBounds(10, 61, 674, 399);
		getContentPane().add(panel);
		// TODO Auto-generated constructor stub
		setVisible(true);
	}
}
