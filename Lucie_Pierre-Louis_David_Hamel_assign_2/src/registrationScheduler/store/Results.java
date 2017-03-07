
package registrationScheduler.store;
import registrationScheduler.util.FileProcessor;
import registrationScheduler.student.Student;
import java.util.ArrayList;
import registrationScheduler.util.Logger;

public class Results implements FileDisplayInterface{
	// appropriate data structure as private data member
	private ArrayList<Student> studentList;
	private float avgScore;
	private Logger logger;

	public Results(Logger loggerIn){
		this.studentList = new ArrayList<Student>();
		this.logger = loggerIn;
		loggerIn.writeMessage("Results constructor called",4);
	}

	/** @return None */
	public synchronized void setStudentList(ArrayList<Student>studentsIn){
		this.studentList = studentsIn;
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
		logger.writeMessage(outString,2);
		fpIn.write(outString);
	}
	fpIn.write("\n");
	fpIn.write("Average Preference Score is: " + avgScore);
	logger.writeMessage("Average Preference Score is: " + avgScore,0);
    }
	
	public synchronized void writeSchedulesToScreen() {
	for(int i = 0; i < studentList.size();i++){
		String outString = studentList.get(i).getName() + " ";
		for(int j = 0; j < studentList.get(i).getScheduledCourses().size();j++){
			outString = outString + studentList.get(i).getScheduledCourses().get(j).getName() + " ";
		}
		outString = outString+ studentList.get(i).getPreferenceScore();
		logger.writeMessage(outString,2);
		System.out.println(outString);
	}
	System.out.println("Average Preference Score is: " + avgScore);
	logger.writeMessage("Logger: Average Preference Score is: " + avgScore,0);
    }
	
} 


