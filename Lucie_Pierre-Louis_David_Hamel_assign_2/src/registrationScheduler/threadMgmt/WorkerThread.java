package registrationScheduler.threadMgmt;

import registrationScheduler.util.FileProcessor;
import registrationScheduler.store.Results;
import registrationScheduler.student.Student;
import java.util.ArrayList;
import registrationScheduler.scheduler.Scheduler;

public class WorkerThread implements Runnable{
	FileProcessor prefFile;
	FileProcessor addDropFile;
	Results result;
	//should they share this or each get their own??
	Scheduler scheduler;
	//should each worker have it's own arrayList
	//should they share this??
	//how do threads work???
	ArrayList<Student> studentList= new ArrayList<Student>();
	
	public WorkerThread(FileProcessor prefFile, FileProcessor addDropFile, Results resultsIn,Scheduler schedulerIn){
		this.prefFile = prefFile;
		this.addDropFile = addDropFile;
		this.result = resultsIn;
		this.scheduler = schedulerIn
	}
	
	public void readPrefFile(){
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
	
	public void run(){
		readPrefFile();
		studentList = scheduler.createSchedules(studentList);
		
	}
}
