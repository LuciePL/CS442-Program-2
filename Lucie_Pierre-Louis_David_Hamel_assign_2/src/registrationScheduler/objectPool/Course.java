package registrationScheduler.objectPool;

import registrationScheduler.student.Student;
import java.util.ArrayList;
import registrationScheduler.util.Logger;

public class Course{
	private String courseName;
	private int studentCount;
	private Logger logger;
	private int maxStudents;
	
	public Course(String courseName, Logger loggerIn){
		this.courseName = courseName;
		this.studentCount = 0;
		this.logger = loggerIn;
		loggerIn.writeMessage("Course constructor called",4);
		this.maxStudents = 60;
	}
	
	/**@return the course name*/
	public String getName(){
		return this.courseName;
	}
	
	/**@return the number of students in the course*/
	public int getCount(){
		return this.studentCount;
	}
	
	/**@return none*/
	public void addToCount(){
		this.studentCount++;
	}
	
	/**@return none*/
	public void subtractCount(){
		this.studentCount--;
	}
	
	/**@return the course name*/
	@Override
	public String toString(){
		return this.courseName;
	}
}

