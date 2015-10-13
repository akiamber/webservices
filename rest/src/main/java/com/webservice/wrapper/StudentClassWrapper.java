package com.webservice.wrapper;

import java.util.List;

import com.webservice.entity.Student;

public class StudentClassWrapper {

	private List<Student> student;
	private int flag;
	/**
	 * @return the student
	 */
	public List<Student> getStudent() {
		return student;
	}
	/**
	 * @return the flag
	 */
	public int getFlag() {
		return flag;
	}
	/**
	 * @param student the student to set
	 */
	public void setStudent(List<Student> student) {
		this.student = student;
	}
	/**
	 * @param flag the flag to set
	 */
	public void setFlag(int flag) {
		this.flag = flag;
	}
}
