package registrationScheduler.threadMgmt;
import registrationScheduler.util.FileProcessor;
import registrationScheduler.util.Logger;

public class CreateWorkers{
	FileProcessor prefFile;
	FileProcessor addDropFile;
	
	public CreateWorkers(FileProcessor prefFile, FileProcessor addDropFile){
		this.prefFile = prefFile;
		this.addDropFile = addDropFile;
	}
	
	public void startWorkers(int numThreads){
		for(int i = 0; i< numThreads; i++){
			WorkerThread temp = new WorkerThread(prefFile,addDropFile);
			temp.run();
			System.out.println("Worker #"+i);
		}
	}
}