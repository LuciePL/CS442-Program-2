package registrationScheduler.scheduler;

import registrationScheduler.student.Student;
import registrationScheduler.objectPool.Course;
import registrationScheduler.objectPool.CoursePool;
import java.util.ArrayList;
import registrationScheduler.util.Logger;

public class Scheduler{
	private Course courseList[] = new Course[8];
	private CoursePool pool;
	Logger logger;
	
	public Scheduler(Logger loggerIn){
		this.logger = logger;
		logger.writeMessage("Scheduler constructor called",4);
		String courseNames[] = new String[] {"A","B","C","D","E","F","G","H"};
		for(int i=0; i< courseNames.length;i++){
			Course temp = new Course(courseNames[i],logger);
			courseList[i] = temp;
		}
		
		//pool = new CoursePool(courseNames);
		}
	
	/**@return A list of all the students after they have been 
	assigned courses based on their preferences*/
	public ArrayList<Student> createPrefSchedules(ArrayList<Student> studentList){
		int maxStudents = 60;
		for(int i=0; i<studentList.size();i++){
			Student currentStudent = studentList.get(i);
			for(int j =0; j<5; j++){
				for(int k = 0; k<courseList.length; k++){
					String pref = currentStudent.getCoursePreference()[j];
					if(pref.equals(courseList[k].getName())){
						if(courseList[k].getCount()<maxStudents){
							currentStudent.addScheduledCourse(courseList[k]);
							courseList[k].addToCount();
						}
					}
				}
			}
		}
		return studentList;
	}
	
	/**@return A student after they have had a specified 
	course(s) removed from their enrolled courses*/
	public Student dropCourses(Student student, ArrayList<String> courses){
		for(int i =0; i<courses.size();i++){
			for(int j = 0; j < courseList.length; j++){
				if(courseList[j].getName().equals(courses.get(i))){
					student.dropScheduledCourse(courseList[j]);
					courseList[j].subtractCount();
				}
			}
		}
		return student;
		
	}
	
	/**@return A student after they have had a(n) specified course(s)
	added to their enrolled courses*/
	public Student addCourses(Student student, ArrayList<String> courses){
		for(int i =0; i<courses.size();i++){
			if(student.getNumberOfCourses() <5){
				for(int j = 0; j < courseList.length; j++){
					if(courseList[j].getName().equals(courses.get(i))){
						student.addScheduledCourse(courseList[j]);
						courseList[j].addToCount();
					}
				}
			}
		}
		return student;
	}

	/**@return A list of students after the preference scores for
	each student in the list has been calculated*/
	public ArrayList<Student> calculatePreferenceScores(ArrayList<Student>studentList){
		for(int i = 0; i < studentList.size(); i++){
			int correctClasses = 0;
			int unwantedClasses = 0;
			String[] courseWants = studentList.get(i).getCoursePreference();
			for(int j = 0; j < courseWants.length; j++){
				for(int k = 0;k < studentList.get(i).getScheduledCourses().size() ; k++){
					if(courseWants[j].equals(studentList.get(i).getScheduledCourses().get(k).getName())){
						//System.out.println(6-j + "\n");
						studentList.get(i).setPreferenceScore(6-j);
						correctClasses++;
					}
					
				}
				unwantedClasses = studentList.get(i).getScheduledCourses().size() - correctClasses;
				studentList.get(i).setPreferenceScore(unwantedClasses);
			}
		}
		return studentList;
	}

	/** @return The average preference score of all students*/
	public float calculateAveragePreferanceScore(ArrayList<Student>studentList){
		int totalPref = 0;
		for(int i = 0; i < studentList.size(); i++){
			totalPref = totalPref + studentList.get(i).getPreferenceScore();
		}

		return totalPref/80;
	}
	
	
	
	
}
