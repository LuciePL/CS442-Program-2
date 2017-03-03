package registrationScheduler.objectPool;

import registrationScheduler.student.Student;
import java.util.ArrayList;


public class Course extends ObjectPool{
	private String courseName;
	private int studentCount;
	private int maxStudents;
	
	public Course(String courseName){
		this.courseName = courseName;
		this.studentCount = 0;
		this.maxStudents = 60;
	}
	
	@Override
	protected Course create(){
		return new Course(this.courseName);
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
	
	public void subtractCount(){
		this.studentCount--;
	}
	
	/**@return the course name*/
	@Override
	public String toString(){
		return this.courseName;
	}
}

