package registrationScheduler.threadMgmt;

import registrationScheduler.util.FileProcessor;
import registrationScheduler.store.Results;


public class WorkerThread implements Runnable{
	FileProcessor prefFile;
	FileProcessor addDropFile;
	Results result;
	
	public WorkerThread(FileProcessor prefFile, FileProcessor addDropFile, Results resultsIn){
		this.prefFile = prefFile;
		this.addDropFile = addDropFile;
		this.result = resultsIn;
	}
	
	public void run(){
		prefFile.createScanner();
		int line=0;
		while(prefFile.getScanner().hasNextLine()){
			prefFile.readNextLine();
			System.out.println("Line : "+line);
			line++;
		}
	}
}
