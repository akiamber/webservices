package com.webservice.wrapper;

import java.util.List;

import com.webservice.entity.Person;

public class PersonClassWrapper {
	
	private List<Person> person;
	private int flag;

	/**
	 * @return the person
	 */
	public List<Person> getPerson() {
		return person;
	}

	/**
	 * @param person the person to set
	 */
	public void setPerson(List<Person> person) {
		this.person = person;
	}

	/**
	 * @return the flag
	 */
	public int getFlag() {
		return flag;
	}

	/**
	 * @param flag the flag to set
	 */
	public void setFlag(int flag) {
		this.flag = flag;
	}

	
}
