package com.RA2_Grupo2.views;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import com.RA2_Grupo2.methods.SQL_Methods;
import com.RA2_Grupo2.methods.WindowsPreset;
import com.RA2_Grupo2.objects.Product;

@SuppressWarnings("serial")
public class InsertProduct extends JFrame {

	private JTextField name, description, image, quantity;
	private JButton insertButton;
	private JButton backButton;
	private JLabel lblNewLabel;
	private JLabel lblNewLabel_1;
	private JLabel lblNewLabel_2;
	private JLabel lblNewLabel_5;
	private JTextField category;
	private JTextField price;

	public InsertProduct() {
		setTitle("INSERTAR PRODUCTO");
		setSize(519, 340);
		WindowsPreset.windowPreset(this);
		getContentPane().setLayout(null);

		name = new JTextField();
		name.setToolTipText("NOMBRE");
		name.setEditable(true);
		name.setBounds(33, 55, 163, 36);
		name.setColumns(10);
		getContentPane().add(name);

		description = new JTextField();
		description.setToolTipText("DESCRIPCIÓN");
		description.setEditable(true);
		description.setBounds(229, 55, 163, 55);
		description.setColumns(10);
		getContentPane().add(description);

		image = new JTextField();
		image.setEditable(true);
		image.setBounds(229, 152, 163, 36);
		image.setColumns(10);
		getContentPane().add(image);

		insertButton = new JButton("INSERTAR PRODUCTO");
		insertButton.setBounds(301, 227, 169, 48);
		insertButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				insertProduct();
			}
		});
		getContentPane().add(insertButton);

		backButton = new JButton("VOLVER");
		backButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				@SuppressWarnings("unused")
				Main m = new Main();
				dispose();
			}
		});
		backButton.setBounds(410, 10, 85, 21);
		getContentPane().add(backButton);

		lblNewLabel = new JLabel("NOMBRE");
		lblNewLabel.setBounds(33, 43, 94, 13);
		getContentPane().add(lblNewLabel);

		lblNewLabel_1 = new JLabel("DESCRIPCIÓN");
		lblNewLabel_1.setBounds(229, 43, 94, 13);
		getContentPane().add(lblNewLabel_1);

		lblNewLabel_2 = new JLabel("IMAGEN");
		lblNewLabel_2.setBounds(229, 137, 141, 13);
		getContentPane().add(lblNewLabel_2);

		JButton selectImageButton = new JButton("...");
		selectImageButton.setBounds(402, 159, 43, 21);
		getContentPane().add(selectImageButton);

		JLabel lblNewLabel_3 = new JLabel("PRECIO");
		lblNewLabel_3.setBounds(33, 221, 76, 13);
		getContentPane().add(lblNewLabel_3);

		JLabel lblNewLabel_4 = new JLabel("CATEGORÍA");
		lblNewLabel_4.setBounds(33, 137, 94, 13);
		getContentPane().add(lblNewLabel_4);

		lblNewLabel_5 = new JLabel("CANTIDAD");
		lblNewLabel_5.setBounds(229, 221, 67, 13);
		getContentPane().add(lblNewLabel_5);

		quantity = new JTextField();
		quantity.setBounds(229, 234, 62, 36);
		getContentPane().add(quantity);
		quantity.setColumns(10);

		category = new JTextField();
		category.setBounds(31, 152, 165, 36);
		getContentPane().add(category);
		category.setColumns(10);

		price = new JTextField();
		price.setBounds(33, 235, 165, 33);
		getContentPane().add(price);
		price.setColumns(10);

		setVisible(true);
	}

	@SuppressWarnings("unlikely-arg-type")
	public void insertProduct() {
		if (name.getText().isBlank() || description.getText().isBlank() || category.getText().isBlank()
				|| price.equals("")) {
			JOptionPane.showMessageDialog(null,
					"Para introducir un producto a la base de datos debe rellenar todos los datos", "",
					JOptionPane.INFORMATION_MESSAGE);
		} else {
			int res = JOptionPane.showConfirmDialog(null,
					"¿Está seguro de querer añadir el producto a la base de datos?", "AÑADIR PRODUCTO",
					JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
			if (res == 0) {
				Product newP = new Product(name.getText(), description.getText(), Float.parseFloat(price.getText()),
						Integer.parseInt(quantity.getText()), category.getText(), "test");
				try {
					newP.setId(SQL_Methods.getMaxIdFromTable("products") + 1);
					SQL_Methods.insertProduct(newP);
					JOptionPane.showMessageDialog(null, "Producto añadido correctamente.", "PRODUCTO AÑADIDO",
							JOptionPane.INFORMATION_MESSAGE);
					dispose();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else
				JOptionPane.showMessageDialog(null, "Se ha cancelado la operación para añadir producto.",
						"AÑADIR PRODUCTO CANCELADO", JOptionPane.INFORMATION_MESSAGE);
		}
	}
}
