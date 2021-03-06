package br.ufsc.ine5605.model;

import java.io.*;
import java.util.*;

public class EmployeeDAO implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private HashMap<Integer, Employee> cacheEmployees = new HashMap<Integer, Employee>();
	private final String filename = "employees.dat";
	
	public EmployeeDAO() {
		load();
	}
	
	
	public Employee get(int numReg) {
		return cacheEmployees.get(numReg);
	}
	public void put(Employee emp) {
		emp.setNumRegistration(cacheEmployees.size() + 17100001);
		cacheEmployees.put(emp.getNumRegistration(), emp);
		persist();
	}public void put(Integer code, Employee emp) {
		cacheEmployees.put(code, emp);
	}
	
	//returns a collection of employees.  
	public Collection<Employee> getList() {
		return cacheEmployees.values();
	}
	
	public void persist() {
		try {			
			FileOutputStream fot = new FileOutputStream(filename);
			
			ObjectOutputStream oos = new ObjectOutputStream(fot);
			oos.writeObject(cacheEmployees);
			
			oos.flush();
			fot.flush();
			
			oos.close();
			fot.close();
			oos = null;
			fot = null;
			
		} catch (FileNotFoundException e) {
			System.out.println(e);
		} catch (IOException e) {
			System.out.println(e);
		}
		
	}
	public void load() {
		try {		
			FileInputStream fiut = new FileInputStream(filename);
		
			ObjectInputStream ois = new ObjectInputStream(fiut);
			
			this.cacheEmployees = (HashMap<Integer, Employee>) ois.readObject();
			
		} catch (FileNotFoundException e) {
			persist();
		} catch (IOException e) {
			System.out.println(e);
		} catch (ClassNotFoundException e) {
			System.out.println(e);
		}
	}
	public void remove(int index) {
		cacheEmployees.remove((Integer) index);
		persist();
	}


	public void override(Employee employee) {
		cacheEmployees.remove(employee.getNumRegistration());
		cacheEmployees.put(employee.getNumRegistration(), employee);
		persist();
	}
	
	
	public void clearData() {
		try {
			for(Employee e : cacheEmployees.values()) {
				cacheEmployees.remove(e.getNumRegistration());
			}
			
	
		} catch (ConcurrentModificationException e) {
			clearData();
		}
		persist();
		load();
		
	}
	
}
