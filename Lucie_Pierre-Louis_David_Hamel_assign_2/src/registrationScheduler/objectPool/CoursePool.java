package registrationScheduler.objectPool;
import registrationScheduler.objectPool.Course;
//import java.lang.ArrayIndexOutOfBoundsException;

public class CoursePool extends ObjectPool{
	private String[] courseNames;
	private int courseCount;
	
	public CoursePool(String[] courses){
		super();
		this.courseNames = courses;
		for(int i =0; i<courseNames.length; i++){
			Course c = new Course(courseNames[i]);
			free.add(c);
			courseCount++;
		}
		//this.createPointer = 0;
	}
	
	
	
	//I couldn't figure out how to use this so I just had the courses made 
	//in the constructor but feel free to fix it
	@Override
	/** @return A new instance of course, created if the object
	pool API needs it */
	protected Course create(){
		//try{
			Course c = new Course(courseNames[courseCount]);
			free.add(c);
			courseCount++;
			return c;
			
		//we don't need to catch this exception	
		/*}catch(ArrayIndexOutOfBoundsException aoob){
			aoob.printStackTrace();
			System.exit(1);
		}finally{

		}
		//return null;
		for(int i =0; i<courseNames.length; i++){
			Course c = new Course(courseNames[i]);
			free.add(c);
		}*/
	}
}
