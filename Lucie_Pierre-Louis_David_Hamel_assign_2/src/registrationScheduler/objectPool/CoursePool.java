package registrationScheduler.objectPool;
import registrationScheduler.objectPool.Course;
import java.lang.ArrayIndexOutOfBoundsException;

public class CoursePool extends ObjectPool{
	private String[] courseNames;
	private int createPointer;
	
	public CoursePool(String[] courses){
		this.courseNames = courses;
		this.createPointer = 0;
	}

	@Override
	protected Course create(){
		try{
			Course c = new Course(courseNames[createPointer]);
			createPointer++;
			return c;
		}catch(ArrayIndexOutOfBoundsException aoob){
			aoob.printStackTrace();
			System.exit(1);
		}finally{

		}
		return null;
	}
}
