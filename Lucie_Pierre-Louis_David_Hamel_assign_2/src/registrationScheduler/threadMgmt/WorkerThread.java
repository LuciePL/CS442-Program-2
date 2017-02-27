package registrationScheduler.threadMgmt;

import registrationScheduler.util.FileProcessor;



public class WorkerThread implements Runnable{
	FileProcessor prefFile;
	FileProcessor addDropFile;
	
	public WorkerThread(FileProcessor prefFile, FileProcessor addDropFile){
		this.prefFile = prefFile;
		this.addDropFile = addDropFile;
	}
	
	public void run(){
		prefFile.createScanner();
		while(prefFile.getScanner().hasNextLine()){
			prefFile.readNextLine();
			System.out.println("pls work");
		}
	}
}