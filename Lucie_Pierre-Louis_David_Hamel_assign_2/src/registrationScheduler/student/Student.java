package registrationScheduler.student;

import registrationScheduler.objectPool.Course;
import java.util.ArrayList;
import java.util.Hashtable;
//import org.apache.commons.collections4;

public class Student{
	private String[] coursePreference = new String[4];//this array should be 5 long i think
	private ArrayList<Course> scheduledCourses= new ArrayList<Course>();
	private String name;
	private int preferenceScore;
	private int numberOfCourses;
	//private Multimap<Integer, String> addDrop = new Multimap<Integer,String>();
	
	public Student(String name){
		this.name = name;
		this.preferenceScore = 0;
		this.numberOfCourses = 0;
	}
	
	/** @return Name of the student*/
	public String getName(){
		return this.name;
	}
	
	/**@return Preference score of the student*/
	public int getPreferenceScore(){
		return this.preferenceScore;
	}
	
	/**@return The number of courses the student is taking*/
	public int getNumberOfCourses(){
		return this.numberOfCourses;
	}
	
	/**@return none*/
	public void setCoursePreference(String courses[]){
		this.coursePreference = courses;
	}
	
	/**@return the preference score of the student*/
	public String[] getCoursePreference(){
		return this.coursePreference;
	}
	
	/**@return The list of courses the student has been scheduled for*/
	public ArrayList<Course> getScheduledCourses(){
		return this.scheduledCourses;
	}
	/**@return none*/
	public void addScheduledCourse(Course course){
		this.scheduledCourses.add(course);
		numberOfCourses++;
	}
	
	public void dropScheduledCourse(Course course){
		for(int i =0; i < scheduledCourses.size(); i++){
			if(scheduledCourses.get(i).getName().equals(course.getName())){
				scheduledCourses.remove(i);
			}
		}
		numberOfCourses--;
	}
	
	public void setPreferenceScore(int score){
		preferenceScore+=score;
	}
	
	
	/**@return the student's name*/
	@Override
	public String toString(){
		return this.name;
	}
	
	
}
