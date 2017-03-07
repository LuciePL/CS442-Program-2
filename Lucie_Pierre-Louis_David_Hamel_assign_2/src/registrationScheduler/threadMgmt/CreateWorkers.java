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
	Logger logger;
	
	public CreateWorkers(FileProcessor prefFileIn, FileProcessor addDropFileIn, FileProcessor outFileIn, Results resultIn, Logger loggerIn){
		this.prefFile = prefFileIn;
		this.addDropFile = addDropFileIn;
		this.outFile = outFileIn;
		this.result = resultIn;
		this.logger = loggerIn;
		logger.writeMessage("Create Workers constructor called",4);
	}
	
	/** @return None */
	public void startWorkers(int numThreads){
		for(int i = 0; i< numThreads; i++){
			WorkerThread temp = new WorkerThread(prefFile,addDropFile,outFile,result,logger);
			//logger.writeMessage("Constructor called",4);
			temp.run();
			System.out.println("Worker #"+i);
		}
	}
}
