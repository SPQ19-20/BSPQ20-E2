package es.deusto.BSPQ20_E2.Netflix.demo;

import javax.swing.JFrame;

import es.deusto.BSPQ20_E2.Netflix.pojo.Film;
import es.deusto.BSPQ20_E2.Netflix.pojo.User;
import javax.swing.JLabel;
import java.awt.Font;
import java.sql.Connection;
import java.util.ArrayList;

import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.JPanel;
import javax.swing.JTable;

public class MainWindow extends JFrame {
	private int rowCount;
	private JTable table;
	private Connection con;
	private ArrayList<Film> films;

	public MainWindow(User u) {
		setSize(700, 500);
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
		String[] colNames = { "Title", "Genre", "Director", "Year", "Price" };
		DefaultTableModel model = new DefaultTableModel(colNames, rowCount);
		try {
			con = DemoDB.connect();
			films = DemoDB.retrieveFilms(con);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for (int i = 0; i < films.size(); i++) {
			model.setValueAt(films.get(i).getTitle(), i, 0);
			model.setValueAt(films.get(i).getGenre(), i, 1);
			model.setValueAt(films.get(i).getDirector(), i, 2);
			model.setValueAt(films.get(i).getYear(), i, 3);
			model.setValueAt(films.get(i).getPrice(), i, 4);
		}
		panel.setBounds(10, 61, 674, 399);
		getContentPane().add(panel);

		table = new JTable();
		table.setModel(model);
		panel.add(table);
		setVisible(true);
	}
}
