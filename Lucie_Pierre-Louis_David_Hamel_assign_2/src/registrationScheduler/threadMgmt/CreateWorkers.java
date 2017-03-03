package registrationScheduler.threadMgmt;
import registrationScheduler.util.FileProcessor;
import registrationScheduler.util.Logger;
import registrationScheduler.store.Results;
import registrationScheduler.scheduler.Scheduler;

public class CreateWorkers{
	FileProcessor prefFile;
	FileProcessor addDropFile;
	Results result;
	Scheduler scheduler;
	
	public CreateWorkers(FileProcessor prefFileIn, FileProcessor addDropFileIn,Results resultIn, Scheduler schedulerIn){
		this.prefFile = prefFileIn;
		this.addDropFile = addDropFileIn;
		this.result = resultIn;
		this.scheduler = schedulerIn;
	}
	
	public void startWorkers(int numThreads){
		for(int i = 0; i< numThreads; i++){
			WorkerThread temp = new WorkerThread(prefFile,addDropFile,results,scheduler);
			temp.run();
			System.out.println("Worker #"+i);
		}
	}
}
