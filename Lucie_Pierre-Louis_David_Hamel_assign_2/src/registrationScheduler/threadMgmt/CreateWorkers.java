package registrationScheduler.threadMgmt;
import registrationScheduler.util.FileProcessor;
import registrationScheduler.util.Logger;
import registrationScheduler.store.Results;
import registrationScheduler.scheduler.Scheduler;

public class CreateWorkers{
	FileProcessor prefFile;
	FileProcessor addDropFile;
	FileProcessor outFile;
	Results result;
	
	public CreateWorkers(FileProcessor prefFileIn, FileProcessor addDropFileIn, FileProcessor outFileIn, Results resultIn){
		this.prefFile = prefFileIn;
		this.addDropFile = addDropFileIn;
		this.outFile = outFileIn;
		this.result = resultIn;
	}
	
	public void startWorkers(int numThreads){
		for(int i = 0; i< numThreads; i++){
			WorkerThread temp = new WorkerThread(prefFile,addDropFile,outFile,result);
			temp.run();
			System.out.println("Worker #"+i);
		}
	}
}
