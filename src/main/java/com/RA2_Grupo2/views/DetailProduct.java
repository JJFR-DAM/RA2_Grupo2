package com.RA2_Grupo2.views;

import java.awt.Color;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import com.RA2_Grupo2.methods.WindowsPreset;
import com.RA2_Grupo2.objects.Product;

@SuppressWarnings("serial")
public class DetailProduct extends JFrame {

	// Attributes declaration.

	private JLabel jlName, jlDescription, jlCat, jlPrice, jlQuantity, jlImg;
	private JTextField jtName, jtCat, jtPrice, jtQuantity;
	private JTextArea jtDescription;
	private JScrollPane jsDescription;
	private Product product;

	public static String url = "src/main/resources/defaultImages/default.png";

	// Constructor.

	public DetailProduct(Product p) {

		// Frame Properties.

		super("Detail");
		setSize(700, 365);
		WindowsPreset.windowPreset(this);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		getContentPane().setLayout(null);

		product = p;

		// Label's & TextField's configurations.

		jlName = new JLabel("Name:");
		jlName.setBounds(10, 40, 175, 35);
		jlName.setHorizontalAlignment(SwingConstants.CENTER);
		jlName.setBackground(Color.GRAY);
		getContentPane().add(jlName);
		jtName = new JTextField();
		jtName.setBounds(185, 40, 193, 35);
		jtName.setColumns(10);
		jtName.setToolTipText("Insert name");
		jtName.setText(product.getName());
		jtName.setEditable(false);
		getContentPane().add(jtName);

		jlCat = new JLabel("Category:");
		jlCat.setBounds(10, 85, 175, 35);
		jlCat.setHorizontalAlignment(SwingConstants.CENTER);
		jlCat.setBackground(Color.GRAY);
		getContentPane().add(jlCat);
		jtCat = new JTextField();
		jtCat.setBounds(185, 85, 193, 35);
		jtCat.setColumns(10);
		jtCat.setToolTipText("Insert category");
		jtCat.setText(product.getCategory());
		jtCat.setEditable(false);
		getContentPane().add(jtCat);

		jlPrice = new JLabel("Price:");
		jlPrice.setBounds(10, 130, 175, 35);
		jlPrice.setHorizontalAlignment(SwingConstants.CENTER);
		jlPrice.setBackground(Color.GRAY);
		getContentPane().add(jlPrice);
		jtPrice = new JTextField();
		jtPrice.setBounds(185, 130, 193, 35);
		jtPrice.setColumns(10);
		jtPrice.setHorizontalAlignment(SwingConstants.CENTER);
		jtPrice.setToolTipText("Insert price");
		jtPrice.setText(String.valueOf(product.getPrice()));
		jtPrice.setEditable(false);
		getContentPane().add(jtPrice);

		jlQuantity = new JLabel("Quantity:");
		jlQuantity.setBounds(10, 175, 175, 35);
		jlQuantity.setHorizontalAlignment(SwingConstants.CENTER);
		jlQuantity.setBackground(Color.GRAY);
		getContentPane().add(jlQuantity);
		jtQuantity = new JTextField();
		jtQuantity.setBounds(185, 175, 193, 35);
		jtQuantity.setColumns(10);
		jtQuantity.setHorizontalAlignment(SwingConstants.CENTER);
		jtQuantity.setToolTipText("Insert quantity");
		jtQuantity.setText(String.valueOf(product.getQuantity()));
		jtQuantity.setEditable(false);
		getContentPane().add(jtQuantity);

		jlDescription = new JLabel("Description:");
		jlDescription.setBounds(10, 237, 175, 35);
		jlDescription.setHorizontalAlignment(SwingConstants.CENTER);
		jlDescription.setBackground(Color.GRAY);
		getContentPane().add(jlDescription);
		jtDescription = new JTextArea();
		jtDescription.setColumns(10);
		jtDescription.setToolTipText("Insert description");
		jtDescription.setText(product.getDescription());
		jtDescription.setEditable(false);
		jsDescription = new JScrollPane(jtDescription);
		jsDescription.setBounds(185, 220, 193, 70);
		getContentPane().add(jsDescription);

		jlImg = new JLabel();
		jlImg.setBounds(388, 40, 288, 250);
		jlImg.setHorizontalAlignment(SwingConstants.CENTER);
		jlImg.setBackground(Color.GRAY);
		jlImg.setIcon(
				new ImageIcon(new ImageIcon(p.getImage()).getImage().getScaledInstance(320, 250, Image.SCALE_DEFAULT)));
		getContentPane().add(jlImg);

		setVisible(true);
	}
}
