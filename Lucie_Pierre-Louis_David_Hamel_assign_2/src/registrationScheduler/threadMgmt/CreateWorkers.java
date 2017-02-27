package registrationScheduler.threadMgmt;
import registrationScheduler.util.FileProcessor;
import registrationScheduler.util.Logger;
import registrationScheduler.store.Results;

public class CreateWorkers{
	FileProcessor prefFile;
	FileProcessor addDropFile;
	Results result;
	
	public CreateWorkers(FileProcessor prefFileIn, FileProcessor addDropFileIn,Results resultIn){
		this.prefFile = prefFileIn;
		this.addDropFile = addDropFileIn;
		this.result = resultIn;
	}
	
	public void startWorkers(int numThreads){
		for(int i = 0; i< numThreads; i++){
			WorkerThread temp = new WorkerThread(prefFile,addDropFile,result);
			temp.run();
			System.out.println("Worker #"+i);
		}
	}
}
