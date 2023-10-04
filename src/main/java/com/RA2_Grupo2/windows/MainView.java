package com.RA2_Grupo2.windows;

import java.awt.Color;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import com.RA2_Grupo2.methods.WindowsPreset;
import com.RA2_Grupo2.objects.Product;
import com.RA2_Grupo2.objects.Supplier;
import com.RA2_Grupo2.objects.Transaction;

@SuppressWarnings("serial")
public class MainView extends JFrame {

	// Attributes.

	private JPanel ptable, pbutton;
	public static JTable table;
	private JButton bInsert, bDelete, bUpdate, bDetails, bLogout;
	private JScrollBar scrollBar;
	private static int option = 0;
	private static String[] options = { "Product", "Supplier" };
	private static ArrayList<Product> listP = new ArrayList<>();
	private static ArrayList<Supplier> listS = new ArrayList<>();
	private static ArrayList<Transaction> listT = new ArrayList<>();

	// Constructor.

	public MainView() {

		// Windows Properties.

		super(options[option] + "s");
		setSize(720, 500);
		WindowsPreset.windowPreset(this);

		// Panels.

		ptable = new JPanel();
		ptable.setBounds(0, 0, 565, 463);
		pbutton = new JPanel();
		pbutton.setBounds(563, 0, 143, 443);

		// Table.

		ptable.setLayout(null);
		ptable.setBackground(Color.LIGHT_GRAY);
		table = new JTable();
		table.setBorder(null);
		table.setBackground(Color.WHITE);
		table.setGridColor(Color.GRAY);
		table.setGridColor(Color.BLACK);
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBackground(Color.GRAY);
		scrollPane.setLocation(0, 0);
		scrollPane.setSize(555, 463);
		ptable.add(scrollPane);

		getContentPane().add(ptable);

		// Adding scroll bar to the table.

		scrollBar = new JScrollBar();
		scrollPane.setRowHeaderView(scrollBar);
		getContentPane().add(ptable);

		// Buttons.

		// Button for create.

		bInsert = new JButton();
		bInsert.setBounds(36, 55, 65, 65);
		String description = "Insert a " + options[option] + " in the table";
		WindowsPreset.buttonPreset(bInsert, description, "src/main/resources/icons/insertar.png", null);

		// Button for delete.

		bDelete = new JButton();
		bDelete.setBounds(36, 125, 65, 65);
		description = "Delete a " + options[option] + " of the table";
		WindowsPreset.buttonPreset(bDelete, description, "src/main/resources/icons/borrar.png", null);

		// Button for update.

		bUpdate = new JButton();
		bUpdate.setBounds(40, 195, 65, 65);
		description = "Update a " + options[option] + " of the table";
		WindowsPreset.buttonPreset(bUpdate, description, "src/main/resources/icons/actualizar.png", null);

		// Button for read.

		bDetails = new JButton();
		bDetails.setBounds(40, 265, 65, 65);
		description = "Details a " + options[option] + " of the table";
		WindowsPreset.buttonPreset(bDetails, description, "src/main/resources/icons/lupa.png", null);

		// Button for close the program.

		bLogout = new JButton();
		bLogout.setBounds(42, 380, 65, 65);
		WindowsPreset.buttonPreset(bLogout, "Logout", "src/main/resources/icons/cerrar-sesion.png", null);

		pbutton.setLayout(null);
		pbutton.setBackground(Color.LIGHT_GRAY);
		pbutton.add(bInsert);
		pbutton.add(bDelete);
		pbutton.add(bUpdate);
		pbutton.add(bDetails);
		pbutton.add(bLogout);

		getContentPane().add(pbutton);
		setVisible(true);
	}

	// List's Getter & Setter.

	public static ArrayList<Product> getListP() {
		return listP;
	}

	public static void setListP(ArrayList<Product> listP) {
		MainView.listP = listP;
	}

	public static ArrayList<Supplier> getListS() {
		return listS;
	}

	public static void setListS(ArrayList<Supplier> listS) {
		MainView.listS = listS;
	}

	public static ArrayList<Transaction> getListT() {
		return listT;
	}

	public static void setListT(ArrayList<Transaction> listT) {
		MainView.listT = listT;
	}

}