package config;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

import controller.ApiController;
import qualitypg.QualityGate;
import qualitypg.QualityProfile;

public class FileExplorer {
	
	JFileChooser fc = new JFileChooser(System.getProperty("user.home") + "/Desktop");
	JFrame frame;
	JPanel fcontain;
	JPanel tcontain;
	JButton but_add;
	JButton but_change;
	JButton but_create;
	JLabel headline;
	JPanel menu;
	ApiController a;
	String path = "";
	JTabbedPane testPane;
	
	public FileExplorer(ApiController a) {
		
		this.a = a;
		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		} catch(Exception e) {
			System.out.println("Cock");
		}
		
		frame = new JFrame("Sonarqube Configapp");
		frame.setSize(720, 480);
		frame.setResizable(false);
		frame.setLayout(new java.awt.BorderLayout());
		
		fc.setAcceptAllFileFilterUsed(false);
		fc.setFileFilter(new FileNameExtensionFilter("JSON Configs", "json"));
		
		but_add = new JButton("Neues Erstellen");
		but_add.addActionListener(e -> addButtonPressed());
		but_change = new JButton("Liste der Konfigurationsdatei");
		but_change.addActionListener(e -> listButtonPressed());
		but_create = new JButton("Erstelle eine neue Konfigurationsdatei");
		
		headline = new JLabel();
		headline.setText("Choose File/Edit Configfiles/Remove Gate or Profile");
		headline.setFont(new Font("Univers", Font.PLAIN, 26));
		headline.setForeground(new Color(255, 255, 255));
		
		fcontain = new JPanel();
		fcontain.setBackground(new Color(255,255,255));
		fcontain.add(but_change);
		fcontain.add(but_add);
		fcontain.add(but_create);
		
	
		tcontain = new JPanel();
		tcontain.setBackground(new Color(0,135,69));
		tcontain.add(headline);
		
		frame.add(tcontain, java.awt.BorderLayout.PAGE_START);
		frame.add(fcontain, java.awt.BorderLayout.CENTER);
		frame.setVisible(true);
	}
	
	public void addButtonPressed() {
		
		int action = fc.showOpenDialog(frame);
		
		if(action == JFileChooser.APPROVE_OPTION) {
			
			Reader r = new Reader(fc.getSelectedFile().getAbsolutePath());
			if(r.getWhich() == true) {
				a.createQuality((QualityGate) r.getQualitygp());
			} else {
				a.createQuality((QualityProfile) r.getQualitygp());
			}
		}
		
	}
	
	public void listButtonPressed() {
		
		final ArrayList<QualityGate> list = a.getQualityGates();
		
		
		JFrame listFrame;
		JPanel buttons;
		JScrollPane scrollpainqg;
		JScrollPane scrollpainqp;
		JTabbedPane pane;
		
		
		listFrame = new JFrame("Liste von QualityGates/QualityProfiles");
		listFrame.setSize(720, 480);
		listFrame.setResizable(false);
		
		pane = new JTabbedPane(JTabbedPane.TOP, JTabbedPane.SCROLL_TAB_LAYOUT);
		
		
		buttons = new JPanel();
		buttons.setLayout(new BoxLayout(buttons, BoxLayout.PAGE_AXIS));
		for(int i = 0; i < list.size(); i++) {
			final int temp = i;
			JButton but = new JButton(list.get(i).getName());
			but.addActionListener(e -> butGates(list.get(temp)));
			buttons.add(but);
		}
		
		
		scrollpainqg = new JScrollPane(buttons);
		scrollpainqp = new JScrollPane();
		
		pane.addTab("QualityGates", scrollpainqg);
		pane.addTab("QualityProfiles", scrollpainqp);
		
		listFrame.add(tcontain, java.awt.BorderLayout.PAGE_START);
		listFrame.add(pane, java.awt.BorderLayout.CENTER);
		listFrame.setVisible(true);
		
	}
	
	
	public void butGates(QualityGate i) {
		
		
		
	}
}