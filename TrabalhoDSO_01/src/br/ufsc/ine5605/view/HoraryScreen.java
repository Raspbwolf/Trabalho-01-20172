package br.ufsc.ine5605.view;


import java.text.ParseException;
import java.sql.Date;
import java.util.Scanner;

import br.ufsc.ine5605.controller.HoraryCtrl;
import br.ufsc.ine5605.model.Horary;

public class HoraryScreen {
	private HoraryCtrl horaryCtrl;
	private Scanner keyboard;
	
	public HoraryScreen(HoraryCtrl horaryCtrl) {
		this.horaryCtrl = horaryCtrl;
		this.keyboard = new Scanner(System.in);
	}
	/**
	 * Método que cria e retorna um objeto do tipo Horary, para uso posterior pela 
	 * classe EmploymentRestrictAccess;
	 * @return Objeto Horary
	 */
	public Horary menuAdd() {
		try {
			
			
			System.out.println("Please enter the hours of access to the financial sector allowed to the employment: ");
			System.out.println("Hour Begin: ");
			/*
			 * Formatar para Date depois;
			 * Criar um try catch aqui para caso o usuário digite um horário
			 * que não se encaixa no padrão Date formatado(hh:mm);
			 */
			Date hourBegin = horaryCtrl.strToDateHour(keyboard.nextLine());
			System.out.println("Hour Finish: ");
			Date hourFinish = horaryCtrl.strToDateHour(keyboard.nextLine());
			return horaryCtrl.addHorary(hourBegin, hourFinish);
				
		}catch(ParseException e) {
			System.out.println("The typed time does not follow the formatting pattern hh:mm");
			menuAdd();
		}catch(NullPointerException e) {
			System.out.println("The typed time does not follow the formatting pattern hh:mm");
			menuAdd();
		}
		return null;
		
	}
	
	public Horary edit(Horary horary) {
		try {
			int option = 0;
			do {
				System.out.println("Please enter the number associated with the time that you wish to edit");
				System.out.println("1 for " + horary.getHourBegin());
				System.out.println("2 for " + horary.getHourFinish());
				
				option = horaryCtrl.conversionStringToInt(keyboard.nextLine());
				switch(option) {
				case 1:
					System.out.println("Enter the new time");
					Date newHorary = horaryCtrl.strToDateHour(keyboard.nextLine());
					Date horaryConvert = horaryCtrl.strToDateHour(horary.getHourFinish());
					return horaryCtrl.addHorary(newHorary, horaryConvert);
					
				case 2:
					System.out.println("Enter the new time");
					newHorary = horaryCtrl.strToDateHour(keyboard.nextLine());
					horaryConvert = horaryCtrl.strToDateHour(horary.getHourBegin());
					return horaryCtrl.addHorary(horaryConvert, newHorary);
					
				default:
					System.out.println("Please enter a valid option");
				}
			} while(option != 1 || option != 2);
			
		}catch(NumberFormatException e) {
			System.out.println("Please enter only numbers");
			edit(horary);
		}catch(ParseException e) {
			System.out.println("The typed time does not follow the formatting pattern hh:mm");
			edit(horary);
		}
		return null;
		
	}

}