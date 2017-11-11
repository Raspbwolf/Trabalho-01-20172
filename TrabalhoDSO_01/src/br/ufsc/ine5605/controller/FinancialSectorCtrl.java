package br.ufsc.ine5605.controller;

import java.util.ArrayList;  
import java.util.Calendar;
import java.util.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import sun.awt.RepaintArea;

import br.ufsc.ine5605.model.Employment;
import br.ufsc.ine5605.model.FinancialSector;
import br.ufsc.ine5605.model.Horary;
import br.ufsc.ine5605.model.Privileges;
import br.ufsc.ine5605.model.Reasons;
import br.ufsc.ine5605.view.FinancialSectorScreen;
import br.ufsc.ine5605.model.ConversionDates;

/**
 * Classe responsável por transmitir o input do usuário para a classe FinancialSector, onde
 * os mesmos serão devidamente tratados. Também é responsável pela comunicação entre as classes 
 * FinancialSector e FinancialSectorScreen entre si e com as outras classes, quando necessário;
 *  
 * @author Sadi Júnior Domingos Jacinto;
 * @author Marcos Laydner;
 * @author Lucas Varella
 */
public class FinancialSectorCtrl {
	private static final FinancialSectorCtrl instance = new FinancialSectorCtrl();
	private MainController mainCtrl;
	private FinancialSectorScreen financialSectorScreen;
	private FinancialSector financialSector;
	
	/**
	 * Construtor padrão da classe;
	 * @param mainController - recebe uma instância do MainController, possibilitando a comunicação do mesmo
	 * com as outras classes, através do MainController;
	 * 
	 * @author Sadi Júnior Domingos Jacinto;
	 */
	public FinancialSectorCtrl() {
		//this.mainCtrl = mainController;
		//this.financialSectorScreen = new FinancialSectorScreen(this);
		this.financialSector = new FinancialSector();
		this.financialSectorScreen = new FinancialSectorScreen();
	}
	
	
	public static FinancialSectorCtrl getInstance() {
		return instance;
	}


	public void mainMenu() {
		mainCtrl.showMainScreen();
	}
	
	
	public void menu() {
		financialSectorScreen.setVisible(true);
	}
	
	
	public boolean validAccess(int numRegistration, Date hourAccess, Date dateAccess) {
		try {
			if(!mainCtrl.isAccessBloqued(numRegistration)) {
				addAccess(numRegistration, dateAccess, hourAccess, Reasons.BLOCK);
				return false;
			}
			
			if(!mainCtrl.validNumRegistration(numRegistration)) {
				addAccess(numRegistration, dateAccess, hourAccess, Reasons.NONUMREGS);
				return false;
			}
			if(financialSector.isPrivilegeFull(getPrivilegeByNumRegistration(numRegistration))) {
				return true;
			}
			if(financialSector.isPrivilegeNo(getPrivilegeByNumRegistration(numRegistration))) {
				addAccess(numRegistration, dateAccess, hourAccess, Reasons.NOACCESS);
				return false;
			}
			if(!financialSector.isPrivilegeRestrict(getPrivilegeByNumRegistration(numRegistration), getHoraryAccess(numRegistration),hourAccess)) {
				addAccess(numRegistration, dateAccess, hourAccess, Reasons.INCTIME);
				return false;
			}
			
		}catch(Exception e) {
			
		}
		return false;
	}
	
	public void addAccess(int numRegistration, Date date, Date hour, Reasons reason) {
		mainCtrl.addAccess(numRegistration, date, hour, reason);
	}

	public void showAllDeniedAccess() {
		mainCtrl.showAllDeniedAccess();
		
	}

	public void showDeniedAccessByNumRegistration(int numRegistration) {
		mainCtrl.showDeniedAccessByNumRegistration(numRegistration);
		
	}

	public void showDeniedAccessByReason(Reasons reason) {
		mainCtrl.showDeniedAccessByReason(reason);
	}
	/**
	 * Método que, quando invocado, registra e retorna a data atual do sistema no qual 
	 * o mesmo está sendo rodado;
	 * @return Date;
	 * @throws ParseException se ocorrer tal erro, contate o suporte;
	 *
	 *@author Sadi Júnior Domingos Jacinto;
	 */
	public Date getCurrenteDate() throws ParseException {
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		Calendar c = Calendar.getInstance();
		String dateFormat = df.format(c.getTime());
		return df.parse(dateFormat);
	}
	
	/**
	 * Método que busca um Employment baseado no número de Registro de um funcionário;
	 * @deprecated - método criado, mas nunca usado;
	 * @param numRegistration - usado para returnar um Employment que possuí um funcionário com tal número;
	 * @return Employment
	 */
	public Employment getEmploymentByNumRegistration(int numRegistration) {
		return null;
	}

	public Privileges getPrivilegeByNumRegistration(int numRegistration) {
		return mainCtrl.getPrivilegeByNumRegistration(numRegistration);
	}

	public ArrayList<Horary> getHoraryAccess(int numRegistration) {
		return mainCtrl.getHoraryAccess(numRegistration);
	}
	

	public Reasons getReasonBy() {
		return mainCtrl.getReasonBy();		
	}
	
}
