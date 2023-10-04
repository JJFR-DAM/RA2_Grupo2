package com.RA2_Grupo2.methods;

import java.awt.Color;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;

public class WindowsPreset {

	static Color c = Color.LIGHT_GRAY;

	public static void windowPreset(JFrame jf) {

		jf.getContentPane().setBackground(c);
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jf.setLocationRelativeTo(null);
		jf.setResizable(false);
		Image icon1 = Toolkit.getDefaultToolkit().getImage("src/main/resources/icons/icon.png");
		jf.setIconImage(icon1);
		jf.getContentPane().setLayout(null);

	}

	public static void buttonPreset(JButton jb, String description, String path) {

		jb.setToolTipText(description);
		jb.setBackground(c);
		jb.setBorderPainted(false);
		Icon ic = new ImageIcon(path);
		jb.setIcon(ic);
	}

}
