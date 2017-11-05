package br.ufsc.ine5605.view;

import java.awt.CardLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import br.ufsc.ine5605.controller.EmploymentCtrl;

public class EmploymentScreenI extends JFrame {
	
	private EmploymentCtrl ctrl;
	private JLabel lbGuide;
	private JButton btRegister;
	private ButtonManager btManager;
	private JTable tbEmployments;
	private JScrollPane spLista;
	private JTextField tfNome;
	private JPanel pSetup;
	private JPanel pMain;
	private JPanel pRegister;
	
	public EmploymentScreenI(EmploymentCtrl ctrl) {
		super("Employment Sector");
		this.ctrl = ctrl;
		btManager = new ButtonManager();
		mainConfig();
	}
	
	private void mainConfig() {
		Container container = getContentPane();
		GridBagConstraints cons = new GridBagConstraints();
		container.setLayout(new GridBagLayout());
		
		// Panel geral
		pSetup = new JPanel(new CardLayout());
		
		pMain = new JPanel(new GridBagLayout());
		
		//Guide Label 
		lbGuide = new JLabel();
		lbGuide.setText("What do you want to do?");
		cons.fill = GridBagConstraints.CENTER; 
		cons.gridx = 2;  
		cons.gridy = 0;
		pMain.add(lbGuide, cons);
		
		
		// JTable de Employments
		tbEmployments = new JTable();
		tbEmployments.setPreferredScrollableViewportSize(new Dimension(500, 70));
		tbEmployments.setFillsViewportHeight(true);
		cons.fill = GridBagConstraints.CENTER; 
		cons.gridx = 0;  
		cons.gridy = 2;
		cons.gridwidth = 3;
		cons.gridheight = 2;
		spLista = new JScrollPane(tbEmployments);
		pMain.add(spLista, cons);
		
		// Register Button
		btRegister = new JButton();
		btRegister.setText("Register new Employment");
		cons.fill = GridBagConstraints.BOTH; 
		cons.gridx = 1;  
        cons.gridy = 5;
        pMain.add(btRegister, cons);
		btRegister.addActionListener(btManager);
		
		pSetup.add(pMain, "pMain");
		
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setSize(600, 600);
		setLocationRelativeTo(null);
		
	}
	
	private void updateData() {
		
		DefaultTableModel modelTbEmployments = new DefaultTableModel();
		modelTbEmployments.addColumn("Position");
		modelTbEmployments.addColumn("Employee");
	
		tbEmployments.setModel(modelTbEmployments);
		this.repaint();
	
	}
	
	private void registerConfig() {
		Container container = getContentPane();
		GridBagConstraints cons = new GridBagConstraints();
		container.setLayout(new GridBagLayout());
		
		
		//Text field para o nome do cargo
		tfNome = new JTextField();
		tfNome.setText("Nome do Cargo");
		cons.fill = GridBagConstraints.BOTH; 
		cons.gridx = 1;  
		cons.gridy = 0;
		container.add(tfNome, cons);
		

		
		
	}
	
	//puta vida bixo
	
	
	
	private class ButtonManager implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			
			if(e.getSource().equals(btRegister)) {
				registerConfig();
			}
		}
		
	}
}
