package com.webservice.entity;

public class Student extends Person{

	private String major;
	private float percentage;
	/**
	 * @return the major
	 */
	public String getMajor() {
		return major;
	}
	/**
	 * @return the percentage
	 */
	public float getPercentage() {
		return percentage;
	}
	/**
	 * @param major the major to set
	 */
	public void setMajor(String major) {
		this.major = major;
	}
	/**
	 * @param percentage the percentage to set
	 */
	public void setPercentage(float percentage) {
		this.percentage = percentage;
	}
	
	
}
