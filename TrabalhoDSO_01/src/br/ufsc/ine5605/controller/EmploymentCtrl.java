package br.ufsc.ine5605.controller;
 

import java.util.ArrayList;

import br.ufsc.ine5605.model.Employee;
import br.ufsc.ine5605.model.Employment;
import br.ufsc.ine5605.model.EmploymentDAO;
import br.ufsc.ine5605.model.EmploymentRestrictAccess;
import br.ufsc.ine5605.model.Privileges;
import br.ufsc.ine5605.model.Horary;
import br.ufsc.ine5605.view.EmploymentScreenI;
import br.ufsc.ine5605.model.Screen2;

/**
 * Classe responsável pela comunicação entre as classes Employment, EmploymentRestrictAccess e EmploymentScreen entre si e
 * com outras classes do sistema;
 * @author Sadi Júnior Domingos Jacinto;
 *
 */
public class EmploymentCtrl implements Screen2 {
	private static final EmploymentCtrl instance = new EmploymentCtrl();
	private EmploymentScreenI employmentScreen;
	//private Employment employment;
	//private EmploymentRestrictAccess employmentRestrictAccess;
	private EmploymentDAO employmentDAO;
	private EmploymentRestrictAccess empDeEnvio;
	private static int code = 1000;
	
	/**
	 * Construtor padrão da classe;
	 * @param mainCtrl - recebe uma instância do MainController, o que permite a comunicação com o mesmo;
	 */
	public EmploymentCtrl() {
		this.employmentScreen = new EmploymentScreenI();
		this.employmentDAO = new EmploymentDAO();
		}

	public static EmploymentCtrl getInstance() {
		return instance;
	}

	public void mainMenu() {
		MainController.getInstance().showMainScreen();
	}
	
	public void menu() {
		
		employmentScreen.updateData();
		employmentScreen.setVisible(true);
	}
	
	/**
	 * Cria um novo Employment e o adiciona a um ArrayList<Employment>; 
	 * @param name - String contendo o nome do Employment;
	 * @param option - Privilégio que o Employment terá;
	 */
	
	public void addEmployment(String name, Privileges option) {
		Employment generic = new Employment(getCode() , name, option);
		employmentDAO.put(generic);
		employmentScreen.updateData();
	}
	
	
	/**
	 * Cria um novo EmploymentRestrictAccess e o adciona ao ArrayList<Employment>;
	 * @param name - String contendo o nome do cargo;
	 * @param option - Privilégio que o Employment terá, por default será Privileges.Restrict;
	 * @return EmploymentRestrictAccess - o novo EmploymentRestrictAccess criado; 
	 */
	public EmploymentRestrictAccess addEmploymentRestrictAccess(String name, Privileges option) {
		EmploymentRestrictAccess em = new EmploymentRestrictAccess(0, name, option);
		employmentDAO.put(em);
		//setCode(getCode() + 1 );
		return em;
	}
	
	/**
	 * Deleta do sistema um Employment;
	 * @param employment - a instância do Employment que será deletada;
	 */
	public void delEmployment(Employment e) {
		employmentDAO.remove((Integer)e.getCode());
	}
	
	public ArrayList<Employment> getEmployments() {
		return new ArrayList<Employment>(employmentDAO.getList());
	}
	
	public void listAllEmployments() {
		
	}
	/*
	public Horary addHorary() {
		return MainController.getInstance().addHorary();
	}
	*/
	/**
	 * Busca um Employment no ArrayList<Employment> baseado no index;
	 * @param num - int contendo o index do objeto no ArrayList;
	 * @return Employment - Retorna a instância encontrada no index do ArrayList
	 * @throws NullPointerException Ocorre quando o usuário tenta acessar um indíce inexistente;
	 */
	public Employment getEmployment(int num) throws NullPointerException {
		try {
			return getEmployments().get(num);
		} catch(NullPointerException e) {
			throw e;
		}		
	}
	public Employment findEmploymentByCode(int code) {
		if(employmentDAO.get(code) == null) {
			return employmentDAO.get(code);
		}else {
			for(Employment e : getEmployments()) {
				if(e.getCode() == code) {
					return e;
				}
			}
			return new Employment(0001, "erro", Privileges.Full);
		}
	}

	public static int getCode() {
		return code;
	}

	public static void setCode(int code) {
		EmploymentCtrl.code = code;
	}

	public void setHorary(EmploymentRestrictAccess e, Horary horary) {
		e.addHorary(horary);
	}

	public ArrayList<Horary> getHorarys(EmploymentRestrictAccess e)  {
		return e.getHorarys();
	}
	/*
	public Horary editHorary(Horary horary) {
		return MainController.getInstance().editHorary(horary);
		
	}*/
	/**
	 * Cria e adiciona um novo Employment. A única diferença com o outro método é a adição de um novo parâmetro de entrada;
	 * @param code2 - int do código do Employment;
	 * @param name - String do nome do Employment;
	 * @param privilege - Privilégio do Employment;
	 * @return Employment - A instância criada;
	 */
	public Employment addEmployment(int code2, String name, Privileges privilege) {
		Employment e = new Employment(code2, name, privilege);
		employmentDAO.put(e);
		return e;
	}
	
	/**
	 * Cria e adiciona um novo EmploymentRestrictAccess. A única diferença com o outro método é a adição de um novo parâmetro de entrada;
	 * @param code2 - int do código do EmploymentRestrictAccess;
	 * @param name - String do nome do EmploymentRestrictAccess;
	 * @param privilege - Privilégio do EmploymentRestrictAccess;
	 * @return EmploymentRestrictAccess - A instância criada;
	 */
	public EmploymentRestrictAccess addEmploymentRestrictAccess(int code2,
			String name, Privileges privilege) {
		EmploymentRestrictAccess e = new EmploymentRestrictAccess(code, name, privilege);
		employmentDAO.put(e);
		return e;
	}
	
	
	public int conversionStringToInt(String data) throws NumberFormatException {
		try {
			int num = Integer.parseInt(data);	
			return num;
		} catch(NumberFormatException e ) {
			throw new NumberFormatException();
		}
	}

	public Privileges stringToPrivilege(String txt) {
		
		Privileges pConvertido = null;
		
		if (txt.equals("Full")) {
			pConvertido = Privileges.Full;
		} else if (txt.equals("Restricted")) {
			pConvertido = Privileges.Restricted;
		} else if (txt.equals("No")) {
			pConvertido = Privileges.No;
		}
		
		return pConvertido;
	
	}
	
	public void setEmpDeEnvio(EmploymentRestrictAccess emp) {
		this.empDeEnvio = emp;
	}
	
	public EmploymentRestrictAccess getEmpDeEnvio() {
		return empDeEnvio;
	}
//	public double conversionStringToDouble(String data)
//			throws NumberFormatException {
//		try {
//			double num = Double.parseDouble(data);	
//			return num;
//		} catch(NumberFormatException e ) {
//			throw new NumberFormatException();
//		}
//	}

	public boolean validCode(int code) {
		for(Employment e : getEmployments()) {
			if(e.getCode() == code) {
				return true;
			}
		}
		return false;
	}

	public void clearData() {
		employmentDAO.clearData();
	}

}
