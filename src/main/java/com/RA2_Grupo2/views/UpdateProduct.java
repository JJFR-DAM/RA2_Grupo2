package com.RA2_Grupo2.views;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.sql.SQLException;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.filechooser.FileNameExtensionFilter;

import com.RA2_Grupo2.methods.SQL_Methods;
import com.RA2_Grupo2.methods.WindowsPreset;
import com.RA2_Grupo2.objects.Product;

@SuppressWarnings("serial")
public class UpdateProduct extends JFrame {

	// Attributes declaration.

	private JLabel jlName, jlDescription, jlCat, jlPrice, jlImg;
	private JTextField jtName, jtCat, jtPrice;
	private JTextArea jtDescription;
	private JScrollPane jsDescription;
	private JButton jbselector, jbconfirm, jbcancel;
	private Product product;

	public static String url = "src/main/resources/defaultImages/default.png";

	// Constructor.

	public UpdateProduct(Product p) {

		// Frame Properties.

		super("Update");
		setSize(400, 455);
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
		getContentPane().add(jtPrice);

		jlDescription = new JLabel("Description:");
		jlDescription.setBounds(10, 192, 175, 35);
		jlDescription.setHorizontalAlignment(SwingConstants.CENTER);
		jlDescription.setBackground(Color.GRAY);
		getContentPane().add(jlDescription);
		jtDescription = new JTextArea();
		jtDescription.setColumns(10);
		jtDescription.setToolTipText("Insert description");
		jtDescription.setText(product.getDescription());
		jsDescription = new JScrollPane(jtDescription);
		jsDescription.setBounds(185, 175, 193, 70);
		getContentPane().add(jsDescription);

		jlImg = new JLabel("Image:");
		jlImg.setBounds(10, 255, 175, 35);
		jlImg.setHorizontalAlignment(SwingConstants.CENTER);
		jlImg.setBackground(Color.GRAY);
		getContentPane().add(jlImg);

		// Button's configurations.

		// Handler

		bHandler handler = new bHandler();

		// Button to select the image.

		jbselector = new JButton("Select Image");
		jbselector.setToolTipText("Open JFileChooser");
		jbselector.setBounds(185, 255, 193, 35);
		jbselector.setBackground(Color.LIGHT_GRAY);
		getContentPane().add(jbselector);
		jbselector.addActionListener(handler);

		// Button to confirm the insertion.

		jbconfirm = new JButton();
		jbconfirm.setBounds(235, 320, 65, 65);
		WindowsPreset.buttonPreset(jbconfirm, "Insert the product", "src/main/resources/icons/confirmar.png");
		jbconfirm.setBackground(new Color(89, 166, 89));
		jbconfirm.addActionListener(handler);
		getContentPane().add(jbconfirm);

		// Button to cancel the insertion.

		jbcancel = new JButton();
		jbcancel.setBounds(100, 320, 65, 65);
		WindowsPreset.buttonPreset(jbcancel, "Cancel", "src/main/resources/icons/volver.png");
		jbcancel.setBackground(new Color(166, 89, 89));
		jbcancel.addActionListener(handler);
		getContentPane().add(jbcancel);

		setVisible(true);
	}

	// Handler implementation.

	private class bHandler implements ActionListener {

		@SuppressWarnings("deprecation")
		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource().equals(jbselector)) {

				/*
				 * Configuration of the selector, creation of the image and adding it to the
				 * entity and local files.
				 */

				JFileChooser jfc = new JFileChooser();
				jfc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
				FileNameExtensionFilter filter = new FileNameExtensionFilter("JPG, PNG & GIF Images", "jpg", "gif",
						"png");
				jfc.setFileFilter(filter);
				int selection = jfc.showOpenDialog(null);

				// Deletion of the image that the video game had before.

				if (!product.getImage().split("/")[3].equals("defaulImages")) {
					new File(product.getImage()).delete();
				}

				File img1 = jfc.getSelectedFile();
				getContentPane().add(jfc);
				jfc.setVisible(true);

				/*
				 * When the JFileChooser select an image, the program create it with an non
				 * repeatable absolute path.
				 */

				if (JFileChooser.APPROVE_OPTION == selection) {
					Date d = new Date();
					url = "src/main/resources/images/" + jtName.getText().replace(" ", "_") + "_" + d.getYear()
							+ d.getTime() + ".jpg";
					Path p = Path.of(url).toAbsolutePath();
					try {
						Files.copy(img1.toPath(), p, StandardCopyOption.REPLACE_EXISTING);
					} catch (IOException IOE) {
						IOE.printStackTrace();
					}
					jfc.setVisible(false);

					// If selector is canceled, the program load a default image.

				} else if (JFileChooser.CANCEL_OPTION == selection) {
					url = "src/main/resources/defaultImages/default.png";
					jfc.setVisible(false);

				}
			}

			/*
			 * If the confirm button is pressed, check null values, format exception and do
			 * the update.
			 */

			else if (e.getSource().equals(jbconfirm)) {
				if (jtDescription.getText().isBlank() || jtName.getText().isBlank() || jtCat.getText().isBlank()
						|| jtPrice.getText().isBlank()) {
					JOptionPane.showMessageDialog(getContentPane(),
							"You must fill every field to complete the data insertion. Try again.");
				} else {
					float price = 0.0f;
					int quantity = 0;
					try {
						price = Float.parseFloat(jtPrice.getText());
						product.setName(jtName.getText());
						product.setCategory(jtCat.getText());
						product.setDescription(jtDescription.getText());
						product.setImage(url);
						product.setPrice(price);
						product.setQuantity(quantity);
						SQL_Methods.updateProduct(product);
						ProductAndSupplier.refreshTable();
						dispose();
					} catch (NumberFormatException NFE) {
						JOptionPane.showMessageDialog(null, "You are trying to use wrong format for price.");
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				}
			}

			// If cancel button is pressed close the insertion view.

			else if (e.getSource().equals(jbcancel)) {
				dispose();
			}
		}
	}
}
