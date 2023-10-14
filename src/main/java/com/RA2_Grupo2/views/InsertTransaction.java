package com.RA2_Grupo2.views;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import com.RA2_Grupo2.methods.SQL_Methods;
import com.RA2_Grupo2.methods.WindowsPreset;
import com.RA2_Grupo2.objects.Product;
import com.RA2_Grupo2.objects.Supplier;
import com.RA2_Grupo2.objects.Transaction;

@SuppressWarnings("serial")
public class InsertTransaction extends JFrame {

	// Attributes declaration.

	private JLabel jlProduct, jlSupplier, jlQuantity;
	private JTextField jtQuantity;
	private JComboBox<Object> jcProduct, jcSupplier;
	private JButton jbconfirm, jbcancel;
	private ArrayList<Product> products = new ArrayList<Product>();
	private ArrayList<Supplier> suppliers = new ArrayList<Supplier>();
	private String option;

	public static String url = "src/main/resources/defaultImages/default.png";

	// Constructor.

	public InsertTransaction(String o) {

		// Windows Properties.
		super(o);

		setSize(400, 320);
		WindowsPreset.windowPreset(this);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		getContentPane().setLayout(null);

		option = o;
		ArrayList<String> productsNames = new ArrayList<String>();
		ArrayList<String> suppliersNames = new ArrayList<String>();
		try {
			products = SQL_Methods.getProducts();
			suppliers = SQL_Methods.getSuppliers();
			for (Product p : products) {
				if (p.getDeleted() == 0) {
					productsNames.add(p.getName());
				}
			}
			for (Supplier s : suppliers) {
				if (s.getDeleted() == 0) {
					suppliersNames.add(s.getName());
				}
			}
		} catch (SQLException sql) {
		}

		// Label's & TextField's configurations.

		jlProduct = new JLabel("Product:");
		jlProduct.setBounds(10, 40, 175, 35);
		jlProduct.setHorizontalAlignment(SwingConstants.CENTER);
		jlProduct.setBackground(Color.GRAY);
		getContentPane().add(jlProduct);
		jcProduct = new JComboBox<Object>(productsNames.toArray());
		jcProduct.setBounds(185, 40, 193, 35);
		getContentPane().add(jcProduct);

		jlSupplier = new JLabel("Supplier:");
		jlSupplier.setBounds(10, 85, 175, 35);
		jlSupplier.setHorizontalAlignment(SwingConstants.CENTER);
		jlSupplier.setBackground(Color.GRAY);
		getContentPane().add(jlSupplier);
		jcSupplier = new JComboBox<Object>(suppliersNames.toArray());
		jcSupplier.setBounds(185, 85, 193, 35);
		getContentPane().add(jcSupplier);

		jlQuantity = new JLabel("Quantity:");
		jlQuantity.setBounds(10, 130, 175, 35);
		jlQuantity.setHorizontalAlignment(SwingConstants.CENTER);
		jlQuantity.setBackground(Color.GRAY);
		getContentPane().add(jlQuantity);
		jtQuantity = new JTextField();
		jtQuantity.setBounds(185, 130, 193, 35);
		jtQuantity.setColumns(10);
		jtQuantity.setToolTipText("Insert quantity");
		getContentPane().add(jtQuantity);

		// Button's configurations.

		bHandler handler = new bHandler();

		// Button to confirm the insertion.

		jbconfirm = new JButton();
		jbconfirm.setBounds(235, 200, 65, 65);
		WindowsPreset.buttonPreset(jbconfirm, "Insert the transaction", "src/main/resources/icons/confirmar.png");
		jbconfirm.setBackground(new Color(89, 166, 89));
		jbconfirm.addActionListener(handler);
		getContentPane().add(jbconfirm);

		// Button to cancel the insertion.

		jbcancel = new JButton();
		jbcancel.setBounds(100, 200, 65, 65);
		WindowsPreset.buttonPreset(jbcancel, "Cancel", "src/main/resources/icons/volver.png");
		jbcancel.setBackground(new Color(166, 89, 89));
		jbcancel.addActionListener(handler);
		getContentPane().add(jbcancel);

		setVisible(true);
	}

	private class bHandler implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource().equals(jbconfirm)) {
				if (Integer.valueOf(jtQuantity.getText()) != 0) {
					if (option == "Sum") {
						try {
							Transaction t = new Transaction();
							Product p = products.get(jcProduct.getSelectedIndex());
							t.setId(SQL_Methods.getMaxIdFromTable("transactions") + 1);
							t.setProductId(p.getId());
							t.setSupplierId(suppliers.get(jcSupplier.getSelectedIndex()).getId());
							t.setQuantity(Integer.valueOf(jtQuantity.getText()));
							SQL_Methods.insertTransaction(t, option);
							p.setQuantity(p.getQuantity() + Integer.valueOf(jtQuantity.getText()));
							SQL_Methods.updateProduct(p);

							TransactionHistory.refreshTable();
							dispose();
						} catch (SQLException sql) {
						} catch (NumberFormatException NFE) {
							JOptionPane.showMessageDialog(null, "You are trying to use wrong format for quantity.");
						}
					} else if (option == "Subtract") {
						try {
							Transaction t = new Transaction();
							Product p = products.get(jcProduct.getSelectedIndex());
							if (p.getQuantity() >= Integer.valueOf(jtQuantity.getText())) {
								t.setId(SQL_Methods.getMaxIdFromTable("transactions") + 1);
								t.setProductId(p.getId());
								t.setSupplierId(suppliers.get(jcSupplier.getSelectedIndex()).getId());
								t.setQuantity(Integer.valueOf(jtQuantity.getText()));
								SQL_Methods.insertTransaction(t, option);
								p.setQuantity(p.getQuantity() - Integer.valueOf(jtQuantity.getText()));
								SQL_Methods.updateProduct(p);
								TransactionHistory.refreshTable();
								dispose();
							} else {
								JOptionPane.showMessageDialog(null, "You can't sell more than you have.");
							}
						} catch (SQLException sql) {
						} catch (NumberFormatException NFE) {
							JOptionPane.showMessageDialog(null, "You are trying to use wrong format for quantity.");
						}
					}
				} else
					JOptionPane.showMessageDialog(null, "You can't operate with zero products");

			} else if (e.getSource().equals(jbcancel)) {
				dispose();
			}
		}
	}
}
