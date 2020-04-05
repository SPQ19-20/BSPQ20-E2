package es.deusto.BSPQ20_E2.Netflix.demo;

import javax.swing.JFrame;

import es.deusto.BSPQ20_E2.Netflix.pojo.Film;
import es.deusto.BSPQ20_E2.Netflix.pojo.User;
import javax.swing.JLabel;
import java.awt.Font;
import java.sql.Connection;
import java.util.ArrayList;

import javax.swing.SwingConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.Color;

public class MainWindow extends JFrame {
	private int rowCount;
	private JTable table;
	private Connection con;
	private ArrayList<Film> films;

	public MainWindow(User u) {
		getContentPane().setBackground(Color.BLACK);
		setSize(700, 500);
		setResizable(false);
		getContentPane().setLayout(null);

		JLabel lblHello = new JLabel("Hello, " + u.getName());
		lblHello.setForeground(Color.RED);
		lblHello.setHorizontalAlignment(SwingConstants.RIGHT);
		lblHello.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblHello.setBounds(554, 11, 130, 14);
		getContentPane().add(lblHello);

		JLabel lblYourCurrentSalary = new JLabel("Your current salary is " + u.getSalary() + "$");
		lblYourCurrentSalary.setForeground(Color.RED);
		lblYourCurrentSalary.setHorizontalAlignment(SwingConstants.RIGHT);
		lblYourCurrentSalary.setBounds(480, 36, 204, 14);
		getContentPane().add(lblYourCurrentSalary);

		JPanel panel = new JPanel();
		panel.setBackground(Color.BLACK);
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
			Object[] fila = new Object[5];
			fila[0] = films.get(i).getTitle();
			fila[1] = films.get(i).getGenre();
			fila[2] = films.get(i).getDirector();
			fila[3] = films.get(i).getYear();
			fila[4] = films.get(i).getPrice();

			model.addRow(fila);
		}
		panel.setBounds(10, 61, 674, 399);
		getContentPane().add(panel);

		JScrollPane scrollPane = new JScrollPane();
		panel.add(scrollPane);
		table = new JTable();
		table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			
			@Override
			public void valueChanged(ListSelectionEvent e) {
				dPaymentWindow dp = new dPaymentWindow(u, films.get(table.getSelectedRow()));				
				dispose();
			}
		});
		scrollPane.setViewportView(table);
		table.setModel(model);
		setVisible(true);
	}
}
