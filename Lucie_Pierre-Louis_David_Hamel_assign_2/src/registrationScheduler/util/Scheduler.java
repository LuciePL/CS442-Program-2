package registrationScheduler.scheduler;

import registrationScheduler.student.Student;
import registrationScheduler.objectPool.Course;
import java.util.ArrayList;

public class Scheduler{
	private Course courseList[] = new Course[8];
	
	public Scheduler(){
		Course a = new Course("A");
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
		courseList[7] = h;
	}
	
	/**@return the list of students with their scheduled courses*/
	public ArrayList<Student> createSchedules(ArrayList<Student> studentList){
		int maxStudents = 20;
		for(int i=0; i<studentList.size();i++){
			Student currentStudent = studentList.get(i);
			int score =1;
			for(int j =0; j<4; j++){
				for(int k = 0; k<courseList.length; k++){
					String pref = currentStudent.getCoursePreference()[j];
					if(pref.equals(courseList[k].getName())){
							if(courseList[k].getCount()<maxStudents){
								currentStudent.addScheduledCourse(courseList[k]);
								courseList[k].addToCount();
								currentStudent.setPreferenceScore(score);
							}
					}
				}
				score++;
			}
			if(currentStudent.getNumberOfCourses()<4){
				score = 5;
				for(int k = 0; k<courseList.length; k++){
					if(courseList[k].getCount()<maxStudents){
						currentStudent.addScheduledCourse(courseList[k]);
						courseList[k].addToCount();
						currentStudent.setPreferenceScore(score);
						if(currentStudent.getNumberOfCourses()==4){
							break;
						}
					}
					
				}
				if(currentStudent.getNumberOfCourses() != 4){
					while(currentStudent.getNumberOfCourses() != 4){
						currentStudent.setPreferenceScore(6);
						//fix numberOfCourses
					}
				}
			}
		}
		return studentList;
	}
	
	
	
	
}
