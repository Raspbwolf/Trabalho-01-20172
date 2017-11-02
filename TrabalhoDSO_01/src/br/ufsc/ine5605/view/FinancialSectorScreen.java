package br.ufsc.ine5605.view;

import java.awt.CardLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;   
import java.util.Date;
import java.util.Scanner;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import br.ufsc.ine5605.controller.FinancialSectorCtrl;
import br.ufsc.ine5605.model.Reasons;

/**
 * Classe responsavel por realizar o input e output de dados referentes as Setor Financeiro;
 * 
 * @author Sadi Junior Domingos Jacinto;
 */

public class FinancialSectorScreen extends JFrame implements ActionListener {
	private FinancialSectorCtrl financialSectorCtrl;
	private JButton btFinancialSector;
	private JButton btDeniedAccess;
	private JButton btAllDeniedAccess;
	private JButton btDeniedAccessByNumber;
	private JButton btDeniedAccessByReason;
	
	public FinancialSectorScreen(FinancialSectorCtrl financialSectorCtrl) {
		super("Menu Financial Sector");
		this.financialSectorCtrl = financialSectorCtrl;
		config();
	}
	
	public void config() {
		
		Container container = getContentPane();
//		GridBagConstraints cons = new GridBagConstraints();
		container.setLayout(new GridBagLayout());
		
//		cons.fill = GridBagConstraints.BOTH; 
//		cons.gridx = 0;  
//        cons.gridy = 0;
		
        setSize(300, 200);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		
		
		//Config. botão de acesso ao Setor Financeiro;
		btFinancialSector = new JButton("Financial Sector");
		container.add(btFinancialSector);
        
        //Config. botão de acesso aos Acessos Negados;
        btDeniedAccess = new JButton("Denied Access");
        container.add(btDeniedAccess);
        
        btFinancialSector.addActionListener(this);
        btDeniedAccess.addActionListener(this);
        
        setVisible(true);
	}
	
	public void listDeniedAccess() {
		Container container = getContentPane();
		container.setLayout(new FlowLayout());
		
        setSize(300, 200);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		JLabel lbChoice = new JLabel("Please, select your option to list");
		
		btAllDeniedAccess = new JButton("All denied access");
		btDeniedAccessByNumber = new JButton("Denied access by number of registration");
		btDeniedAccessByReason = new JButton("Denied access by reason of negation");

		container.add(lbChoice);
		setLocationRelativeTo(lbChoice);
		
		container.add(btAllDeniedAccess);
		container.add(btDeniedAccessByNumber);
		container.add(btDeniedAccessByReason);
		
		btAllDeniedAccess.addActionListener(this);
		btDeniedAccessByNumber.addActionListener(this);
		btDeniedAccessByReason.addActionListener(this);
		
        setVisible(true);
	
	}
	
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource().equals(btFinancialSector)) {
			
		
		}else if(e.getSource().equals(btDeniedAccess)){
			listDeniedAccess();
		
		}else if(e.getSource().equals(btAllDeniedAccess)) {
			
			
		}else if(e.getSource().equals(btDeniedAccessByNumber)) {
			
			
		}else if(e.getSource().equals(btDeniedAccessByReason)) {
			
			
		}
		
	}

}
/*
public class FinancialSectorScreen {
	private FinancialSectorCtrl financialSectorCtrl;
	private Scanner keyboard;

	/**
	 * Construtor padrao da classe FinancialSectorScrenn;
	 * 
	 * @param financialSectorCtrl - recebe uma insta�ncia do FinancialSectorCtrl, para que possa 
	 * transferir o input para que o programa os trate;
	 * 
	 * @author Sadi Junior Domingos Jacinto;
	 */
	/*public FinancialSectorScreen(FinancialSectorCtrl financialSectorCtrl) {
		this.financialSectorCtrl = financialSectorCtrl;
		keyboard = new Scanner(System.in);
	}
	
	/**
	 * Menu principal do Setor Financeiro;
	 * Redireciona o usuario para outros metodos baseado no input do mesmo;
	 * 
	 * @throws NumberFormatException sendo que tal excecao ocorre quando o usuario digita um 
	 * caracter alfabetico ou nao-imprima�vel num campo destinado a� numeros inteiros;
	 * 
	 * @author Sadi Junior Domingos Jacinto;
	 */
	/*public void menuFinancialSector() {//Metodo ja revisado;
		
		try {
		
			int option = 0;
			do {
			
				System.out.println("Please, select your option: ");
				System.out.println("1 - Enter Financial Sector");
				System.out.println("2 - Denied Accesses Report");
				System.out.println("0 - Exit");
				System.out.println("-------------------------------------------------------------------------");
				option = financialSectorCtrl.conversionStringToInt(keyboard.nextLine());
				
				switch(option) {
				
				case 1:
					enterFinancialSector();
					break;
					
				case 2:
					showDeniedAccess();
					break;
				
				case 0:
					System.out.println("Goodbye");
					break;
				
				default:
					System.out.println("The number you entered is not valid");	
				}
				
			}while(option != 0);
			financialSectorCtrl.mainMenu();
			
		}catch(NumberFormatException e) {
			System.out.println("Please enter only valid numbers");
			menuFinancialSector();
		}
	}
	
	/**
	 * Menu de entrada do Setor Financeiro;
	 * Durante a excecucao do metodo, sao requeridos dois dados:
	 * 1 - Numero de registro, sendo valido ou nao e 
	 * 2 - Hora de acesso;
	 * 
	 * @throws NumberFormatException sendo que tal excecao ocorre quando o usuario digita um 
	 * caracter alfabetico ou nao-imprima�vel num campo destinado a� numeros inteiros;
	 * 
	 * @throws ParseException ocorre caso o usuario digite um horario fora do padrao HH:mm 
	 * no momento onde e requerido ao mesmo a hora de acesso;
	 * 
	 * @throws NullPointerException caso esse erro ocorra, por favor, contate urgentemente o suporte;
	 * 
	 * @author Sadi Junior Domingos Jacinto;
	 */
	/*public void enterFinancialSector() {//Metodo revisado, OK;
		
		try {
			System.out.println("Welcome to the entrance of the Financial Sector, noble adventurer");
			System.out.println("To continue your quest for capital, please enter your registration number and," + 
								"\nif you are worthy, you may enter this sacred place");
			System.out.println("Number of registration: ");
			int num = financialSectorCtrl.conversionStringToInt(keyboard.nextLine());
			System.out.println("Time of access: ");
			Date hourAccess = financialSectorCtrl.strToDateHour(keyboard.nextLine());
			
			//aqui eu pego a hora atual do sistema, no caso o dia
			
			Date dateAccess = financialSectorCtrl.getCurrenteDate();
			
			boolean valid = financialSectorCtrl.validAccess(num, hourAccess, dateAccess);
			
			if(valid) {
				System.out.println("You are worthy of entering this sacred place");
			}else {
				System.out.println("You are not worthy of entrance, adventurer, for:");
				System.out.println(financialSectorCtrl.getReasonBy());
			}
		
		}catch(NumberFormatException e) {
			System.out.println("Please enter only valid numbers");
			enterFinancialSector();
		
		}catch(ParseException e) {
			System.out.println("A conversion error has occurred, make sure you have entered the required data correctly");
			enterFinancialSector();
		
		}catch(NullPointerException e) {
			System.out.println("If you are reading this message, \nit means that an unexpected error has occurred. \nPlease contact support for help");
			enterFinancialSector();
		}
		

	}
	
	/**
	 * Menu que exibe um relatorio de acessos negados, sendo que tais acessos podem ser filtrados de 
	 * acordo com a vontade do usuario;
	 * 
	 * @throws NumberFormatException ocorrendo quando o usuario digita um caracter 
	 * alfabetico ou nao-imprima�vel num campo destinado a� numeros inteiros;
	 * 
	 * @throws IndexOutOfBoundsException ocorre quando o usuario tenta listar os acessos negados
	 * quando nao existe nenhum acesso negado cadastrado no sistema;
	 * 
	 * @throws NullPointerException caso esse erro ocorra, por favor, contate urgentemente o suporte;
	 * 
	 * @author Sadi Junior Domingos Jacinto;
	 */
	/*public void showDeniedAccess() {//Metodo revisado, Ok;
		try {
			System.out.println("Please select your option: ");
			System.out.println("1 - Denied Accesses");
			System.out.println("2 - Denied Accesses by Registration number");
			System.out.println("3 - Denied Accesses by denial reason");
			System.out.println("0 - Exit");
			
			int option = financialSectorCtrl.conversionStringToInt(keyboard.nextLine());
			switch(option) {
			
			case 1:
				financialSectorCtrl.showAllDeniedAccess();
				break;
				
			case 2: 
				System.out.println("Please enter the registration number: ");
				int numRegistration = financialSectorCtrl.conversionStringToInt(keyboard.nextLine());
				financialSectorCtrl.showDeniedAccessByNumRegistration(numRegistration);
				break;
				
			case 3:
				System.out.println("Please select your reason of denial: ");
				System.out.println("1 - The number registration does not exist");
				System.out.println("2 - You do not have access");
				System.out.println("3 - Restricted time of access");
				System.out.println("4 - Access blocked");
				int reason = financialSectorCtrl.conversionStringToInt(keyboard.nextLine());
				
				switch(reason) {
				
				case 1:
					financialSectorCtrl.showDeniedAccessByReason(Reasons.NONUMREGS);
					break;
					
				case 2:
					financialSectorCtrl.showDeniedAccessByReason(Reasons.NOACCESS);
					break;
					
				case 3:
					financialSectorCtrl.showDeniedAccessByReason(Reasons.INCTIME);
					break;
					
				case 4:
					financialSectorCtrl.showDeniedAccessByReason(Reasons.BLOCK);
					break;
					
				default:
					System.out.println("The number you entered is not valid");
				}
				break;
				
			case 0:
				financialSectorCtrl.mainMenu();
				break;
				
			default:
				System.out.println("The number you entered is not valid");

			}
			
		} catch(NumberFormatException e) {
			System.out.println("Please enter only a valid number");
			showDeniedAccess();
		
		} catch(IndexOutOfBoundsException e) {
			System.out.println("There is no denied access to the system");
			showDeniedAccess();
		
		} catch(NullPointerException e) {
			System.out.println("There is no denied access to the system");
			showDeniedAccess();
		}
		

	}
}
*/