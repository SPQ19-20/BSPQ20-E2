package es.deusto.BSPQ20_E2.Netflix.client.gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import es.deusto.BSPQ20_E2.Netflix.pojo.Film;
import es.deusto.BSPQ20_E2.Netflix.pojo.User;
import es.deusto.BSPQ20_E2.Netflix.server.db.DB;
/**
 * Main window with
 * @author Jorge
 *
 */
public class MainWindow extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int rowCount;
	private JTable table;
	private ArrayList<Film> films;
	private ArrayList<Film> queryResults;

	private JLabel lblName = new JLabel("");
	private JLabel lblPrice1 = new JLabel("");
	private JButton btnBuy = new JButton("Buy");
	private JTextField tfSearch;

	public MainWindow(User u) {
		getContentPane().setBackground(Color.BLACK);
		setSize(700, 500);
		setResizable(false);
		getContentPane().setLayout(null);

		JLabel lblHello = new JLabel("Welcome, " + u.getName());
		lblHello.setForeground(Color.RED);
		lblHello.setHorizontalAlignment(SwingConstants.RIGHT);
		lblHello.setFont(new Font("Tahoma", Font.BOLD, 23));
		lblHello.setBounds(213, 12, 257, 53);
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
			films = DB.retrieveFilms();
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
		panel.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 0, 413, 402);
		panel.add(scrollPane);
		table = new JTable();
		table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				int seleccion = table.getSelectedRow();
				lblName.setText(String.valueOf(table.getValueAt(seleccion, 0)));
				lblPrice1.setText(String.valueOf(table.getValueAt(seleccion, 4)));
			}
		});
		btnBuy.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				PaymentWindow dp;
				if (table.getModel() == model)
					dp = new PaymentWindow(u, films.get(table.getSelectedRow()));
				else
					dp = new PaymentWindow(u, queryResults.get(table.getSelectedRow()));

				dispose();
			}
		});
		scrollPane.setViewportView(table);
		table.setModel(model);
		lblName.setBounds(527, 59, 117, 27);
		panel.add(lblName);
		lblName.setForeground(Color.WHITE);

		JLabel lblSelectedFilm = new JLabel("Selected Film: ");
		lblSelectedFilm.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblSelectedFilm.setBounds(423, 59, 94, 27);
		panel.add(lblSelectedFilm);
		lblSelectedFilm.setForeground(Color.RED);

		JLabel lblPrice = new JLabel("Price: ");
		lblPrice.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblPrice.setForeground(Color.RED);
		lblPrice.setBounds(471, 104, 46, 14);
		panel.add(lblPrice);

		lblPrice1.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblPrice1.setForeground(Color.WHITE);
		lblPrice1.setBounds(527, 104, 101, 27);
		panel.add(lblPrice1);

		btnBuy.setForeground(Color.RED);
		btnBuy.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnBuy.setBackground(Color.BLACK);
		btnBuy.setBounds(585, 379, 89, 23);
		panel.add(btnBuy);

		tfSearch = new JTextField();
		tfSearch.setBackground(Color.LIGHT_GRAY);
		tfSearch.setBounds(423, 233, 152, 50);
		panel.add(tfSearch);
		tfSearch.setColumns(10);

		JButton btnSearch = new JButton("Search");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					queryResults = DB.searchFilms(tfSearch.getText());
					DefaultTableModel mdlSearch = new DefaultTableModel(colNames, 0);

					for (int i = 0; i < queryResults.size(); i++) {
						if (queryResults.get(i) != null) {
							Object[] fila = new Object[5];
							fila[0] = queryResults.get(i).getTitle();
							fila[1] = queryResults.get(i).getGenre();
							fila[2] = queryResults.get(i).getDirector();
							fila[3] = queryResults.get(i).getYear();
							fila[4] = queryResults.get(i).getPrice();

							mdlSearch.addRow(fila);
						}

					}

					table.setModel(mdlSearch);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		btnSearch.setForeground(Color.RED);
		btnSearch.setBackground(Color.BLACK);
		btnSearch.setBounds(585, 232, 89, 23);
		panel.add(btnSearch);

		JButton btnReset = new JButton("Reset query");
		btnReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tfSearch.setText("");
				table.setModel(model);

			}
		});
		btnReset.setFont(new Font("Tahoma", Font.PLAIN, 10));
		btnReset.setForeground(Color.RED);
		btnReset.setBackground(Color.BLACK);
		btnReset.setBounds(585, 260, 89, 23);
		panel.add(btnReset);
		setVisible(true);
	}
}
