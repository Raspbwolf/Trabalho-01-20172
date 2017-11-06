package br.ufsc.ine5605.view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date; 
import java.text.ParseException;
import java.util.Scanner;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import br.ufsc.ine5605.controller.EmployeeCtrl;
import br.ufsc.ine5605.model.*;

/**
 * Tela responsÃ¡vel pela interaÃ§Ã£o do usuÃ¡rio com as funcionalidades relacionadas aos Employees;
 * @author Sadi JÃºnior Domingos Jacinto;
 */
public class EmployeeScreenI extends JFrame{
	
	private EmployeeCtrl employeeCtrl;
	private JLabel lbEmpScrn;
	private JButton btAdd;
	private JButton btEdit;
	private JButton btFSector;
	private ButtonManager btManager;
	private CardLayout cardLayout;
	private JList<String> lsEmployees;
	private JPanel screen;
	private JPanel pnAdd;
	private JButton btSave;
	private JPanel pnMain;
	private JComboBox cbEmployments;
	private JButton btReturn;
	//ok?... please
	private JButton btMainMenu;

	
	/**
	 * Construtor padrÃ£o da classe
	 * @param employeeCtrl - Recebe uma instÃ¢ncia do EmployeeCtrl, o que permite a comunicaÃ§Ã£o com outras classes;
	 */
	public EmployeeScreenI(EmployeeCtrl employeeCtrl) {
		super("Employee Management and Data");
		this.employeeCtrl = employeeCtrl;
		btManager = new ButtonManager();
		config();
		
	}
	public void config() {
		Container container = getContentPane();
		GridBagConstraints cons = new GridBagConstraints();
		container.setLayout(new GridBagLayout());
		
		cons.fill = GridBagConstraints.BOTH;

		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(800, 300);
		setLocationRelativeTo(null);
		setResizable(true);
		
		
		
		//Doin a lil' panel thingy
		screen = new JPanel(new CardLayout());
		
		//Main panel
		pnMain = new JPanel(new GridBagLayout());
		//Screen label
		lbEmpScrn = new JLabel("Please Select desired Employee to manage, or add a new one.");
		cons.gridx = 0;
		cons.gridy = 0;
		pnMain.add(lbEmpScrn, cons);
						
		
		
		//List of emps, now selectable(?)
		lsEmployees = new JList();
		lsEmployees.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		lsEmployees.setLayoutOrientation(JList.VERTICAL);
		lsEmployees.setVisibleRowCount(10);
		cons.gridheight = 20;
		cons.gridwidth = 30;
		cons.gridx = 0;
		cons.gridy = 4;
		JScrollPane spListEmp = new JScrollPane(lsEmployees);
		pnMain.add(spListEmp, cons);
		
		//addEmployee button
		cons.gridheight = 2;
		cons.gridwidth = 4;
		btAdd = new JButton("Add new Employee");
		cons.gridx = 0;
		cons.gridy = 50;
		btAdd.addActionListener(btManager);
		pnMain.add(btAdd, cons);
						
							
		//edit button
		btEdit = new JButton("Edit selected employee");
		cons.gridx = 4;
		cons.gridy = 50;
		btEdit.addActionListener(btManager);
		pnMain.add(btEdit, cons);
		
		//Main Menu Button
		btMainMenu = new JButton("Main Menu");
		cons.gridx = 8;
		cons.gridy = 50;
		btMainMenu.addActionListener(btManager);
		pnMain.add(btMainMenu, cons);
		
		
		
		screen.add(pnMain, "Employee Screen");
		
		//Add employee panel
		pnAdd = new JPanel(new GridBagLayout());
			//Labels
		
			//Text Fields, labels
		cons.gridheight = 2;
		cons.gridwidth = 4;
		cons.gridx = 0;
		cons.gridy = 0;
		pnAdd.add(new JLabel("Employee's Name:"), cons);
		cons.gridx = 4;
		cons.gridy = 0;
		pnAdd.add(new JTextField(10), cons);
		cons.gridx = 0;
		cons.gridy = 4;
		pnAdd.add(new JLabel("Employee's Birthday: "), cons);
		cons.gridx = 4;
		cons.gridy = 4;
		pnAdd.add(new JTextField(10), cons);
		cons.gridx = 0;
		cons.gridy = 8;
		pnAdd.add(new JLabel("Employee's Phone:"), cons);
		cons.gridx = 4;
		cons.gridy = 8;
		pnAdd.add(new JTextField(10), cons);
		cons.gridx = 0;
		cons.gridy = 12;
		pnAdd.add(new JLabel("Employee's Salary:"), cons);
		cons.gridx = 4;
		cons.gridy = 12;
		pnAdd.add(new JTextField(10), cons);
		
		//combo employments
		cons.gridx = 0;
		cons.gridy = 16;
		pnAdd.add(new JLabel("Employee's Employment: "), cons);
		cbEmployments = new JComboBox(stringListCb());
		cons.gridx = 4;
		cons.gridy = 16;
		cons.insets = new Insets(0, 0, 0,0);
		cbEmployments.addActionListener(btManager);
		pnAdd.add(cbEmployments, cons);
		
			//Save button
		btSave = new JButton("Save");
		cons.gridx = 4;
		cons.gridy = 20;
		btSave.addActionListener(btManager);
		pnAdd.add(btSave, cons);
		
			//Return button
		btReturn = new JButton("Return");
		cons.gridx = 0;
		cons.gridy = 20;
		btReturn.addActionListener(btManager);
		pnAdd.add(btReturn, cons);
		
		
		screen.add(pnAdd, "Add Employee");
		container.add(screen);
		cardLayout = (CardLayout) screen.getLayout();
		
		
		
		
		
	}
	private class ButtonManager implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			
			if(e.getSource().equals(btAdd)) {
				
				cardLayout.show(screen, "Add Employee");
			
			} else if(e.getSource().equals(btReturn)) {
				
				cardLayout.show(screen, "Employee Screen");
			
			}else if(e.getSource().equals(btMainMenu)) {
				
				setVisible(false);
				
			}
			/* else if(e.getSource().equals(but)) {
				employeeCtrl.action();
			}
			*/
		}
	}
	private void updateData() {
		DefaultListModel<String> model = new DefaultListModel<String>();
		if(employeeCtrl.getEmployees() != null) {

			try {
				for(Employee employee : employeeCtrl.getEmployees()) {
					model.addElement(employee.getName());
				}
				this.repaint();
			}finally {
				this.repaint();
			}
		}else {
			model.addElement("Please add employees.");
		}
		model.addElement("Please add employees.");
		lsEmployees.setModel(model);
		this.repaint();
	}
	/**
	 * 
	 * @return returns a String list, containing the names of all available employments
	 */
	private String[] stringListCb() {
		try {
			
		
		String[] naosei = new String[employeeCtrl.getEmployments().size()];
		int i = 0;
		for(Employment emp : employeeCtrl.getEmployments()) {
			naosei[i] = emp.getName();
			i++;
		}
		this.repaint();
		return naosei;
		}finally {
			final String[] error = {"Please add Employments first."};
			return error;
		}
	}
	
//	delEmployee();
//	listEmployees();
//employeeCtrl.mainMenu();
//public void addEmployee() {
//
//System.out.println("Please enter the following information");
//
//System.out.println("Name: ");
//System.out.println("Date of birth: ");
//System.out.println("Phone: ");
//System.out.println("Salary: ");
//System.out.println("Please, enter the number corresponding to the chosen employment: ");
//employeeCtrl.listEmployments();
//Employment gen = employeeCtrl.findEmploymentByIndex(option);
//Employee generic = employeeCtrl.addEmployee(name, birthDay, phone, salary);
//employeeCtrl.addContract(gen, generic);
//
//System.out.println("-------------------------------------------------------------------------");
//System.out.println("Congratulations, you have created a new employee with the following characteristics: ");
//}		
///**
//* Tela responsÃ¡vel pela interaÃ§Ã£o do usuÃ¡rio com os mÃ©todos de ediÃ§Ã£o dos atributos do FuncionÃ¡rio;
//*/
//public void editEmployee() {
//System.out.println("--------------------------------------------------------------------------------");
//System.out.println("Please enter the number corresponding to your choice: ");
//listEmployees();
//System.out.println("Please enter the number corresponding to the characteristic you want to change: ");
//
//System.out.println("1 - Name");
//System.out.println("2 - Birthday");
//System.out.println("3 - Phone");
//System.out.println("4 - Salary");
//System.out.println("5 - Employment");
//System.out.println("Or 0 to exit");
//	System.out.println("Enter a new name: ");
//	System.out.println("Enter a new birthday: ");
//	System.out.println("Enter a new phone");
//	System.out.println("Enter a new salary");
//	System.out.println("Enter the number corresponding to the new employment");
//
//}
//
///**
//* Tela responsÃ¡vel pela interaÃ§Ã£o do usuÃ¡rio com o mÃ©todo de remoÃ§Ã£o de Employees;
//*/
//public void delEmployee() {
//System.out.println("Select an employee to fire");
//employeeCtrl.delEmployee(option);
//
	/*
	public void addEmployee() {
		try {
			System.out.println("Please enter the following information");
			
			System.out.println("Name: ");
			Scanner keyboard = new Scanner(System.in);
			String name = keyboard .nextLine();
			
			System.out.println("Date of birth: ");
		
			Date birthDay = employeeCtrl.strToDate(keyboard.nextLine());
			
			
			System.out.println("Phone: ");
			int phone = employeeCtrl.conversionStringToInt(keyboard.nextLine()); 
			
			
			System.out.println("Salary: ");
			double salary = employeeCtrl.conversionStringToDouble(keyboard.nextLine());
			
			
			System.out.println("Please, enter the number corresponding to the chosen employment: ");
			
			employeeCtrl.listEmployments();
			
			int option = employeeCtrl.conversionStringToInt(keyboard.nextLine()) - 1;
			Employment gen = employeeCtrl.findEmploymentByIndex(option);
		
			Employee generic = employeeCtrl.addEmployee(name, birthDay, phone, salary);
			
			System.out.println("-------------------------------------------------------------------------");
			System.out.println("Congratulations, you have created a new employee with the following characteristics: ");
			System.out.println("Number of registration - " + generic.getNumRegistration());
			System.out.println("Name - " + generic.getName());
			System.out.println("Birthday - " + generic.getDateBirth());
			System.out.println("Phone - " + generic.getPhone());
			System.out.println("Salary - " + generic.getSalary());
			System.out.println("Employment - " + generic.getEmployment().getEmployment().getName());
		
		
		} catch(ParseException e) {
			System.out.println("The date format entered by the user is not correct\n"+ 
							   "Please try again based on this format:\n" + "dd/MM/yyyy");
			System.out.println("-------------------------------------------------------------------------");
			addEmployee();
		}
		
	}	*/
	
	
}
