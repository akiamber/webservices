package com.webservice.wrapper;

import java.util.ArrayList;
import java.util.List;

import com.webservice.entity.Person;
import com.webservice.entity.Student;

public class SampleData {

	private static List<Person> setPersonSampleData(){
		List<Person> persons =new ArrayList<Person>();
		for(int i=0;i<3;i++){
			Person p= new Person();
			p.setAge(25+i);
			p.setExperience(2.5f+i);
			p.setFirstName("Person-"+i+"-fName");
			p.setLastName("Person-"+i+"-lName");
			p.setWork("professional");
			persons.add(p);
		}
				
		return persons;
		
	}
	
	private static List<Student> setStudentSampleData(){
		List<Student> students =new ArrayList<Student>();
		for(int i=0;i<3;i++){
			Student s= new Student();
			s.setAge(25+i);
			s.setExperience(2.5f+i);
			s.setFirstName("Student-"+i+"-fName");
			s.setLastName("Student-"+i+"-lName");
			s.setWork("learning");
			s.setMajor("maths");
			s.setPercentage(65.7f+i);
			students.add(s);
		}
				
		return students;
		
	}
	
	public static StudentClassWrapper sampleStudentsData(){
		StudentClassWrapper sclswrapper = new StudentClassWrapper();
		sclswrapper.setFlag(1);//transfering primitive data type
		sclswrapper.setStudent(setStudentSampleData());
		return sclswrapper;
	}
	
	public static PersonClassWrapper samplePersonsData(){
		PersonClassWrapper pclswrapper = new PersonClassWrapper();
		pclswrapper.setFlag(1);//transfering primitive data type
		pclswrapper.setPerson(setPersonSampleData());
		return pclswrapper;
	}
}
