package com.RA2_Grupo2.windows;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JSpinner;
import javax.swing.JTextField;

import com.RA2_Grupo2.methods.SQL_Methods;
import com.RA2_Grupo2.methods.WindowsPreset;
import com.RA2_Grupo2.objects.Product;
import javax.swing.JLabel;

@SuppressWarnings("serial")
public class UpdateProductView extends JFrame{

	private JTextField name, description, image, price;
	private JComboBox category;
	private JSpinner quantity;
	private JButton updateButton;
	private JButton backButton;
	private JLabel lblNewLabel;
	private JLabel lblNewLabel_1;
	private JLabel lblNewLabel_2;
	private JButton btnNewButton;
	private JLabel lblNewLabel_3;
	private JLabel lblNewLabel_4;
	private JLabel lblNewLabel_5;
	
	public UpdateProductView(Product p) {
		setTitle("EDITAR PRODUCTO");
		setSize(519, 340);
		WindowsPreset.windowPreset(this);
		getContentPane().setLayout(null);
		
		name = new JTextField();
		name.setEditable(true);
		name.setBounds(33, 55, 163, 36);
		name.setColumns(10);
		name.setText(p.getName());
		getContentPane().add(name);
		
		description = new JTextField();
		description.setEditable(true);
		description.setBounds(229, 55, 163, 55);
		description.setColumns(10);
		description.setText(p.getDescription());
		getContentPane().add(description);
		
		category.setBounds(33, 150, 163, 38);
		getContentPane().add(category);
		
		image = new JTextField();
		image.setEditable(true);
		image.setBounds(229, 152, 163, 36);
		image.setColumns(10);
		image.setText(p.getImage());
		getContentPane().add(image);
		
		price = new JTextField();
		price.setEditable(true);
		price.setBounds(33, 234, 163, 36);
		price.setColumns(10);
		price.setText(Float.toString(p.getPrice()));
		getContentPane().add(price);
		
		quantity.setBounds(229, 234, 52, 36);
		getContentPane().add(quantity);
		
		updateButton = new JButton("EDITAR PRODUCTO");
		updateButton.setBounds(324, 222, 160, 53);
		updateButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				p.setName(name.toString());
				p.setDescription(description.toString());
				p.setImage("test");
				p.setCategory("test");
				p.setPrice(Float.parseFloat(price.toString()));
				p.setQuantity(Integer.parseInt(quantity.toString()));
				updateProduct(p);
			}
		});
		getContentPane().add(updateButton);
		
		backButton = new JButton("VOLVER");
		backButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				MainView mw = new MainView();
				dispose();
			}
		});
		backButton.setBounds(410, 10, 85, 21);
		getContentPane().add(backButton);
		
		lblNewLabel = new JLabel("NOMBRE");
		lblNewLabel.setBounds(34, 41, 79, 13);
		getContentPane().add(lblNewLabel);
		
		lblNewLabel_1 = new JLabel("DECRIPCIÓN");
		lblNewLabel_1.setBounds(229, 41, 85, 13);
		getContentPane().add(lblNewLabel_1);
		
		lblNewLabel_2 = new JLabel("CATEGORÍA");
		lblNewLabel_2.setBounds(33, 134, 99, 13);
		getContentPane().add(lblNewLabel_2);
		
		btnNewButton = new JButton("...");
		btnNewButton.setToolTipText("Seleccionar imagen");
		btnNewButton.setBounds(402, 159, 52, 21);
		getContentPane().add(btnNewButton);
		
		lblNewLabel_3 = new JLabel("PRECIO");
		lblNewLabel_3.setBounds(33, 222, 45, 13);
		getContentPane().add(lblNewLabel_3);
		
		lblNewLabel_4 = new JLabel("IMAGEN");
		lblNewLabel_4.setBounds(229, 134, 45, 13);
		getContentPane().add(lblNewLabel_4);
		
		lblNewLabel_5 = new JLabel("CANTIDAD");
		lblNewLabel_5.setBounds(229, 222, 85, 13);
		getContentPane().add(lblNewLabel_5);
		
		setVisible(true);
	}
	
	public void updateProduct(Product p) {
		
		if(p.getName().equals("") || p.getDescription().equals("") || p.getCategory().equals("") || p.getImage().equals("")) {
        	JOptionPane.showMessageDialog(null, "Para editar un producto de la base de datos debe rellenar todos los datos", "", JOptionPane.INFORMATION_MESSAGE);
		} else {
			int res = JOptionPane.showConfirmDialog(null, "¿Está seguro de querer editar el producto?", "EDITAR PRODUCTO", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
	    	if(res == 0) {
	    		SQL_Methods.updateProduct(p);
	    	} else JOptionPane.showMessageDialog(null, "Se ha cancelado la operación para editar producto.", "EDITAR PRODUCTO CANCELADO", JOptionPane.INFORMATION_MESSAGE);
		}
	}
}
