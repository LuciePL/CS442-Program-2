package registrationScheduler.threadMgmt;
import registrationScheduler.util.FileProcessor;
import registrationScheduler.util.Logger;
import registrationScheduler.store.Results;
import registrationScheduler.scheduler.Scheduler;

public class CreateWorkers{
	FileProcessor prefFile;
	FileProcessor addDropFile;
	Results results;
	Scheduler scheduler;
	
	public CreateWorkers(FileProcessor prefFile, FileProcessor addDropFile,Results results, Scheduler scheduler){
		this.prefFile = prefFile;
		this.addDropFile = addDropFile;
		this.results = results;
		this.scheduler = scheduler;
	}
	
	public void startWorkers(int numThreads){
		for(int i = 0; i< numThreads; i++){
			WorkerThread temp = new WorkerThread(prefFile,addDropFile,results,scheduler);
			temp.run();
			System.out.println("Worker #"+i);
		}
	}
}