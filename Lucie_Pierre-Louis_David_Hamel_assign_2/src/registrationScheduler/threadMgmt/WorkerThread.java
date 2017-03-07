package registrationScheduler.threadMgmt;

import registrationScheduler.util.FileProcessor;
import registrationScheduler.store.Results;
import registrationScheduler.student.Student;
import java.util.ArrayList;
import registrationScheduler.scheduler.Scheduler;
import registrationScheduler.objectPool.Course;
import registrationScheduler.util.Logger;

public class WorkerThread implements Runnable{
	FileProcessor prefFile;
	FileProcessor addDropFile;
	FileProcessor outFile;
	Results result;
	Scheduler scheduler;
	ArrayList<Student> studentList;
	Logger logger;
	
	public WorkerThread(FileProcessor prefFile, FileProcessor addDropFile, FileProcessor outFile, Results resultsIn, Logger loggerIn){
		this.prefFile = prefFile;
		this.addDropFile = addDropFile;
		this.outFile = outFile;
		this.result = resultsIn;
		this.logger = loggerIn;
		loggerIn.writeMessage("Worker Thread constructor called",4);
		this.scheduler = new Scheduler(logger);
		this.studentList = new ArrayList<Student>();
		
	}

	//public synchronized void studentsToResults(){
	//	results
	//}	
	
	/** @return None */	
	public void readPrefFile(){
		prefFile.createScanner();
		while(prefFile.getScanner().hasNextLine()){
			String line = prefFile.readNextLine();
			String[] studentProfile = line.split(" ");
			Student currStudent = new Student(studentProfile[0],logger);
			String[] studentPref = {studentProfile[1],studentProfile[2],studentProfile[3],studentProfile[4],studentProfile[5]};
			currStudent.setCoursePreference(studentPref);
			studentList.add(currStudent);
		}
	}
	
	/** @return None */
	public void readAddDropFile(){
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
		logger.writeMessage("Thread's run method has been called",3);
		readPrefFile();
		studentList = scheduler.createPrefSchedules(studentList);
		readAddDropFile();
		studentList = scheduler.calculatePreferenceScores(studentList);
	        for(int i = 0; i < studentList.size();i++){
			result.addStudent(studentList.get(i));
		}
		result.setAvgScore(scheduler.calculateAveragePreferanceScore(studentList));
	}
}
