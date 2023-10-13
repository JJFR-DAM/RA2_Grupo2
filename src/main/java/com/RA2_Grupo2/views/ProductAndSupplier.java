package com.RA2_Grupo2.views;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.RA2_Grupo2.methods.SQL_Methods;
import com.RA2_Grupo2.methods.WindowsPreset;
import com.RA2_Grupo2.objects.Product;
import com.RA2_Grupo2.objects.Supplier;

@SuppressWarnings("serial")
public class ProductAndSupplier extends JFrame {

	// Attributes.

	private JPanel ptable, pbutton;
	public static JTable table;
	private JScrollBar scrollBar;
	private JButton bInsert, bDelete, bUpdate, bDetails, bLogout;
	private JComboBox<String> jcb1, jcb2;

	private static int option = 0;
	private static String[] options = { "Product", "Supplier" };
	private static String[] filter1 = { "Name", "Category", "Price" };
	private static List<Product> listP = new ArrayList<>();
	private static List<Supplier> listS = new ArrayList<>();

	// Constructor.

	public ProductAndSupplier() {

		// Windows Properties.

		super("Products & Supplier");
		setSize(720, 555);
		WindowsPreset.windowPreset(this);
		getContentPane().setBackground(Color.LIGHT_GRAY);

		// Panels.

		ptable = new JPanel();
		ptable.setBounds(10, 45, 565, 465);
		pbutton = new JPanel();
		pbutton.setBounds(576, 45, 130, 465);
		getContentPane().setLayout(null);

		// Table.

		ptable.setLayout(null);
		ptable.setBackground(Color.LIGHT_GRAY);
		table = new JTable();
		table.setBorder(null);
		table.setBackground(Color.WHITE);
		table.setGridColor(Color.LIGHT_GRAY);
		table.setGridColor(Color.BLACK);
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBackground(Color.GRAY);
		scrollPane.setLocation(0, 0);
		scrollPane.setSize(565, 465);
		ptable.add(scrollPane);

		getContentPane().add(ptable);

		// Adding scroll bar to the table.

		scrollBar = new JScrollBar();
		scrollPane.setRowHeaderView(scrollBar);
		getContentPane().add(ptable);

		refreshTable();

		// Buttons.

		bHandler handler = new bHandler();

		// Button for create.

		bInsert = new JButton();
		bInsert.setBounds(32, 10, 65, 65);
		WindowsPreset.buttonPreset(bInsert, "Insert a product in the table", "src/main/resources/icons/insertar.png");
		bInsert.addActionListener(handler);

		// Button for delete.

		bDelete = new JButton();
		bDelete.setBounds(32, 85, 65, 65);
		WindowsPreset.buttonPreset(bDelete, "Delete a product of the table", "src/main/resources/icons/borrar.png");
		bDelete.addActionListener(handler);

		// Button for update.

		bUpdate = new JButton();
		bUpdate.setBounds(32, 160, 65, 65);
		WindowsPreset.buttonPreset(bUpdate, "Update a product of the table", "src/main/resources/icons/actualizar.png");
		bUpdate.addActionListener(handler);

		// Button for read.

		bDetails = new JButton();
		bDetails.setBounds(32, 235, 65, 65);
		WindowsPreset.buttonPreset(bDetails, "Details a product of the table", "src/main/resources/icons/lupa.png");
		bDetails.addActionListener(handler);

		// Button for close the program.

		bLogout = new JButton();
		bLogout.setBounds(32, 350, 65, 65);
		WindowsPreset.buttonPreset(bLogout, "Logout", "src/main/resources/icons/volver.png");
		bLogout.addActionListener(handler);

		pbutton.setLayout(null);
		pbutton.setBackground(Color.LIGHT_GRAY);
		pbutton.add(bInsert);
		pbutton.add(bDelete);
		pbutton.add(bUpdate);
		pbutton.add(bDetails);
		pbutton.add(bLogout);

		getContentPane().add(pbutton);

		jcb1 = new JComboBox<String>(options);
		jcb1.setBounds(10, 10, 130, 25);
		jcb1.addActionListener(handler);
		getContentPane().add(jcb1);

		jcb2 = new JComboBox<String>(filter1);
		jcb2.setBounds(496, 10, 200, 25);
		jcb2.addActionListener(handler);
		getContentPane().add(jcb2);

		setVisible(true);
	}

	// List's Getter & Setter.

	public static List<Product> getListP() {
		return listP;
	}

	public static void setListP(List<Product> listP) {
		ProductAndSupplier.listP = listP;
	}

	public static List<Supplier> getListS() {
		return listS;
	}

	public static void setListS(List<Supplier> listS) {
		ProductAndSupplier.listS = listS;
	}

	public static void refreshTable() {
		DefaultTableModel dtm = new DefaultTableModel() {

			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}

		};
		if (option == 0) {
			String[] columns = { "CAT", "NAME", "PRICE" };
			dtm.setColumnIdentifiers(columns);
			try {
				listP = SQL_Methods.getProducts();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			Iterator<Product> iter = listP.iterator();
			while (iter.hasNext()) {
				Product p = iter.next();
				if (p.getDeleted() == 0) {
					dtm.addRow(new Object[] { p.getCategory(), p.getName(), p.getPrice() });
				}
			}
			ProductAndSupplier.table.setModel(dtm);
		} else {
			String[] columns = { "NAME", "PHONE" };
			dtm.setColumnIdentifiers(columns);
			try {
				listS = SQL_Methods.getSuppliers();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			Iterator<Supplier> iter = listS.iterator();
			while (iter.hasNext()) {
				Supplier s = iter.next();
				if (s.getDeleted() == 0) {
					dtm.addRow(new Object[] { s.getName(), s.getPhone() });
				}
			}
			ProductAndSupplier.table.setModel(dtm);

		}
	}

	private class bHandler implements ActionListener {
		@SuppressWarnings("unused")
		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource().equals(bInsert)) {
				if (option == 0) {
					InsertProduct2 ip = new InsertProduct2();
				} else {
					InsertSupplier is = new InsertSupplier();
				}
			} else if (e.getSource().equals(bDelete)) {
				if (option == 0) {
					int option = table.getSelectedRow();
					if (option >= 0) {
						int resp = JOptionPane.showConfirmDialog(null, "Are you sure?", "Confirmation",
								JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);
						if (resp == 0) {
							Product p = listP.get(option);
							if (!p.getImage().split("/")[3].equals("defaultImages")) {
								new File(getListP().get(option).getImage()).delete();
							}
							try {
								SQL_Methods.deleteProduct(p);
								ProductAndSupplier.refreshTable();
							} catch (SQLException e1) {
								JOptionPane.showMessageDialog(null, "Something went wrong. Try again later.", "Warning",
										JOptionPane.WARNING_MESSAGE);
							}
						}
					} else {
						JOptionPane.showMessageDialog(null, "You must select one row.", "Warning",
								JOptionPane.WARNING_MESSAGE);
					}
				} else {
					int option = table.getSelectedRow();
					if (option >= 0) {
						int resp = JOptionPane.showConfirmDialog(null, "Are you sure?", "Confirmation",
								JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);
						if (resp == 0) {
							Supplier s = listS.get(option);
							try {
								SQL_Methods.deleteSupplier(s);
								ProductAndSupplier.refreshTable();
							} catch (SQLException e1) {
								JOptionPane.showMessageDialog(null, "Something went wrong. Try again later.", "Warning",
										JOptionPane.WARNING_MESSAGE);
							}
						}
					} else {
						JOptionPane.showMessageDialog(null, "You must select one row.", "Warning",
								JOptionPane.WARNING_MESSAGE);
					}
				}
			} else if (e.getSource().equals(bUpdate)) {
				int option = table.getSelectedRow();
				if (option >= 0) {
					if (ProductAndSupplier.option == 0) {
						UpdateProduct2 up = new UpdateProduct2(listP.get(option));
					} else {
						UpdateSupplier us = new UpdateSupplier(listS.get(option));
					}
				} else {
					JOptionPane.showMessageDialog(null, "You must select one row.", "Warning",
							JOptionPane.WARNING_MESSAGE);
				}
			} else if (e.getSource().equals(bDetails)) {
				int option = table.getSelectedRow();
				if (option >= 0) {
					if (ProductAndSupplier.option == 0) {
						DetailProduct dp = new DetailProduct(listP.get(option));
					} else {
						DetailSupplier ds = new DetailSupplier(listS.get(option));
					}

				} else {
					JOptionPane.showMessageDialog(null, "You must select one row.", "Warning",
							JOptionPane.WARNING_MESSAGE);
				}
			} else if (e.getSource().equals(bLogout)) {
				dispose();
				Inventory i = new Inventory();
			} else if (e.getSource().equals(jcb1)) {
				if (jcb1.getSelectedIndex() == 0) {
					option = 0;
					bInsert.setToolTipText("Insert a product in the table");
					bDelete.setToolTipText("Delete a product of the table");
					bUpdate.setToolTipText("Update a product of the table");
					bDetails.setToolTipText("Details a product of the table");
					refreshTable();
					jcb2.setVisible(true);
				} else {
					option = 1;
					bInsert.setToolTipText("Insert a supplier in the table");
					bDelete.setToolTipText("Delete a supplier of the table");
					bUpdate.setToolTipText("Update a supplier of the table");
					bDetails.setToolTipText("Details a supplier of the table");
					refreshTable();
					jcb2.setVisible(false);
				}

			} else if (e.getSource().equals(jcb2)) {

			}
		}

	}
}