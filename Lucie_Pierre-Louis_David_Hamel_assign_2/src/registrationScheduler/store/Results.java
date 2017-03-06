
package registrationScheduler.store;
import registrationScheduler.util.FileProcessor;
import registrationScheduler.student.Student;
import java.util.ArrayList;

public class Results implements FileDisplayInterface {
	// appropriate data structure as private data member
	
	private ArrayList<Student> studentList;
	private float avgScore;
	// appropriate method to save prime number to the data structure

	public Results(){
		this.studentList = new ArrayList<Student>();
	}

	public void setStudentList(ArrayList<Student>studentsIn){
		this.studentList = studentsIn;
	}

	public void setAvgScore(float scoreIn){
		this.avgScore = scoreIn;
	}

    
	public void writeSchedulesToFile(FileProcessor fpIn) {
	fpIn.clearFile();
	//fpIn.write("Test");
	for(int i = 0; i < studentList.size();i++){
		String outString = studentList.get(i).getName() + " ";
		for(int j = 0; j < studentList.get(i).getScheduledCourses().size();j++){
			outString = outString + studentList.get(i).getScheduledCourses().get(j).getName() + " ";
		}
		outString = outString+studentList.get(i).getPreferenceScore() + "\n";
		fpIn.write(outString);
		
		
	}
	fpIn.write("\n");
	fpIn.write("Average Preference Score is: " + avgScore);
    }
} 


