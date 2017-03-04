package registrationScheduler.objectPool;
//import registrationScheduler.objectPool.Course;
import java.util.Hashtable;
import java.util.ArrayList;

public abstract class ObjectPool<T>{
	protected ArrayList<T> inUse;
	protected ArrayList<T> free;
	//abstract Object create();
	//abstract boolean validate(Course c);
	//abstract void expire(Course c);

	public ObjectPool(){
		inUse = new ArrayList<T>();
		free = new ArrayList<T>();	
	}

	//this is present in object pool examples but im not sure if	
	// I overrode it correctly in the course 
	protected abstract T create();

	//public void init(T[] objarray){
	//	for(int i = 0; i < objarray.length; i ++){
	//		free.add(objarray[0]);
	//	}
	//}
	
	//get first object in the free list
	public synchronized T checkOut(){
		//long time = System.currentTimeMillis();
		T object;
		if(free.size() > 0){
			object = free.get(0);
			free.remove(0);
			inUse.add(object);
			return object;	
		}
		//need to create a creation method in subclass if there
		//are no objects that are not in use i think
		object = create();
		inUse.add(object);
		return object;
	}

	//possible checkout to be overridden to get specific instances
	//from the object pool
	//public synchronized abstract T checkOutSpecial();

	public synchronized void checkIn(T object){
		if(object == null){
			return;
		}
		inUse.remove(object);
		free.add(object);
	}

	
}
