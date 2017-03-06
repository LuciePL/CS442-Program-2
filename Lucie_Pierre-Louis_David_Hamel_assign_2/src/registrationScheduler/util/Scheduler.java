package registrationScheduler.scheduler;

import registrationScheduler.student.Student;
import registrationScheduler.objectPool.Course;
import registrationScheduler.objectPool.CoursePool;
import java.util.ArrayList;

public class Scheduler{
	private Course courseList[] = new Course[8];
	private CoursePool pool;
	
	public Scheduler(){
		String courseNames[] = new String[] {"A","B","C","D","E","F","G","H"};
		for(int i=0; i< courseNames.length;i++){
			Course temp = new Course(courseNames[i]);
			courseList[i] = temp;
		}
		/*Course a = new Course("A");
		courseList[0] = a;
		Course b = new Course("B");
		courseList[1] = b;
		Course c = new Course("C");
		courseList[2] = c;
		Course d = new Course("D");
		courseList[3] = d;
		Course e = new Course("E");
		courseList[4] = e;
		Course f = new Course("F");
		courseList[5] = f;
		Course g = new Course("G");
		courseList[6] = g;
		Course h = new Course("H");
		courseList[7] = h;*/
		//pool = new CoursePool(courseNames);
		}
		//pool.init(courseList);
	
	
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
	
	
	
	
}
