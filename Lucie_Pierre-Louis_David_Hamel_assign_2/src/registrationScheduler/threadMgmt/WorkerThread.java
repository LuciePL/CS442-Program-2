package registrationScheduler.threadMgmt;

import registrationScheduler.util.FileProcessor;
import registrationScheduler.store.Results;
import registrationScheduler.student.Student;
import java.util.ArrayList;
import registrationScheduler.scheduler.Scheduler;
import registrationScheduler.objectPool.Course;

public class WorkerThread implements Runnable{
	FileProcessor prefFile;
	FileProcessor addDropFile;
	FileProcessor outFile;
	Results result;
	Scheduler scheduler;
	ArrayList<Student> studentList;
	
	public WorkerThread(FileProcessor prefFile, FileProcessor addDropFile, FileProcessor outFile, Results resultsIn){
		this.prefFile = prefFile;
		this.addDropFile = addDropFile;
		this.outFile = outFile;
		this.result = resultsIn;
		this.scheduler = new Scheduler();
		this.studentList = new ArrayList<Student>();
	}

	//public synchronized void studentsToResults(){
	//	results
	//}	
	
	/** @return None */	
	public synchronized void readPrefFile(){
		prefFile.createScanner();
		while(prefFile.getScanner().hasNextLine()){
			String line = prefFile.readNextLine();
			String[] studentProfile = line.split(" ");
			Student currStudent = new Student(studentProfile[0]);
			String[] studentPref = {studentProfile[1],studentProfile[2],studentProfile[3],studentProfile[4],studentProfile[5]};
			currStudent.setCoursePreference(studentPref);
			studentList.add(currStudent);
		}
	}
	
	/** @return None */
	public synchronized void readAddDropFile(){
		addDropFile.createScanner();
		while(addDropFile.getScanner().hasNextLine()){
			String line = addDropFile.readNextLine();
			String[] addDropProfile = line.split(" ");
			Student currStudent;
			ArrayList<String> addDropCourses = new ArrayList<String>();
			for(int i = 0; i< studentList.size();i++){
				if(studentList.get(i).getName().equals(addDropProfile[0])){
					for(int j=2; j<addDropProfile.length;j++){
						addDropCourses.add(addDropProfile[j]);
					}
					try{
						if(Integer.parseInt(addDropProfile[1]) ==0){
							studentList.set(i,scheduler.dropCourses(studentList.get(i),addDropCourses));
						}
						else{
							studentList.set(i,scheduler.addCourses(studentList.get(i),addDropCourses));
						}
					}catch(NumberFormatException e){
						System.exit(1);
					}
					finally{
						
					}
					}
					
				}
			}
		}

	
	/** @return None */
	public void run(){
		readPrefFile();
		studentList = scheduler.createPrefSchedules(studentList);
		/*for(int i = 0; i< studentList.size();i++){
			ArrayList<Course> studentCourses = studentList.get(i).getScheduledCourses();
			System.out.println(studentList.get(i).getName());
			for(int j=0; j < studentCourses.size();j++){
				System.out.println(studentCourses.get(j));
			}
		}*/
		readAddDropFile();
		studentList = scheduler.calculatePreferenceScores(studentList);
		//studentList = scheduler.addDropSchedules(studentList);
		result.setStudentList(studentList);
		result.setAvgScore(scheduler.calculateAveragePreferanceScore(studentList));
		result.writeSchedulesToFile(outFile);
	}
}
