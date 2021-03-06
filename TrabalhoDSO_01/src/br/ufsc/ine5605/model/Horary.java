package br.ufsc.ine5605.model;

import java.io.Serializable;
import java.text.DateFormat; 
import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * Classe que contém os atributos e métodos responsáveis pela criação de um objeto Horary;
 * @author Sadi Júnior Domingos Jacinto;
 *
 */
public class Horary implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Date hourBegin;
	private Date hourFinish;
	private String name;
	
	/**
	 * Construtor padrão da classe;
	 * @param hourBegin - Date contendo a hora de início
	 * @param hourFinish - Date contendo a hora de fim;
	 */
	public Horary(String name, Date hourBegin, Date hourFinish) {
		this.name = name;
		this.hourBegin = hourBegin;
		this.hourFinish = hourFinish;
	}
	
	public String getHourBegin() {
		DateFormat dateFormat = new SimpleDateFormat("HH:mm");
		String returned = dateFormat.format(hourBegin);
		return returned;
	}
	
	public String getHourFinish() {
		DateFormat dateFormat = new SimpleDateFormat("HH:mm");
		String returned = dateFormat.format(hourFinish);
		return returned;
	}

	public String getName() {
		// TODO Auto-generated method stub
		return this.name;
	}

	public void setName(String name) {
		// TODO Auto-generated method stub
		this.name = name;
	}

	public void setHourBegin(Date hour) {
		// TODO Auto-generated method stub
		this.hourBegin = hour;
	}

	public void setHourFinish(Date strToDateHour) {
		// TODO Auto-generated method stub
		this.hourFinish = strToDateHour;
	}
}
