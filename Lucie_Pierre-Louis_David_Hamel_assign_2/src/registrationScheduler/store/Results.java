
package registrationScheduler.store;
import registrationScheduler.util.FileProcessor;
import registrationScheduler.student.Student;
import java.util.ArrayList;
import registrationScheduler.util.Logger;

public class Results implements FileDisplayInterface{
	private ArrayList<Student> studentList;
	private float avgScore;
	private Logger logger;

	public Results(Logger loggerIn){
		this.studentList = new ArrayList<Student>();
		this.logger = loggerIn;
		logger.writeMessage("Results constructor called",4);
	}

	/** @return None */
	public synchronized void setStudentList(ArrayList<Student>studentsIn){
		this.studentList = studentsIn;
	}

	/** @return None */
	public void addStudent(Student studentIn){
		boolean inList = false;
		for(int i = 0; i < studentList.size(); i++){
			if(studentList.get(i).getName().equals(studentIn.getName())){
				inList = true;
			}
		}
		if(!inList){
			studentList.add(studentIn);
			logger.writeMessage("Logger : "+studentIn.getName() +" added to the Results data structure",2);
		}	
	}

	/** @return None */
	public synchronized void setAvgScore(float scoreIn){
		this.avgScore = scoreIn;
	}

    	/** @return None */
	public synchronized void writeSchedulesToFile(FileProcessor fpIn) {
	fpIn.clearFile();
	//fpIn.write("Test");
	for(int i = 0; i < studentList.size();i++){
		String outString = studentList.get(i).getName() + " ";
		for(int j = 0; j < studentList.get(i).getScheduledCourses().size();j++){
			outString = outString + studentList.get(i).getScheduledCourses().get(j).getName() + " ";
		}
		String prefScoreString = studentList.get(i).getPreferenceScore() + "\n";
		outString = outString+ prefScoreString;
		fpIn.write(outString);
	}
	fpIn.write("\n");
	fpIn.write("Average preference score is: " + avgScore);
	logger.writeMessage("The average preference value is " + avgScore,0);
    }
	
	public synchronized void writeSchedulesToScreen() {
	for(int i = 0; i < studentList.size();i++){
		String outString = studentList.get(i).getName() + " ";
		for(int j = 0; j < studentList.get(i).getScheduledCourses().size();j++){
			outString = outString + studentList.get(i).getScheduledCourses().get(j).getName() + " ";
		}
		outString = outString+ studentList.get(i).getPreferenceScore();
		System.out.println(outString);
	}
	System.out.println("Average preference score is: " + avgScore);
	logger.writeMessage("The average preference value is " + avgScore,0);
    }
	
} 


