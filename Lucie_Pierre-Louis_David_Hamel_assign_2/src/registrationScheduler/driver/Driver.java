package registrationScheduler.driver;

import java.io.File;
import java.io.FileReader;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.io.PrintWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import registrationScheduler.util.FileProcessor;
import registrationScheduler.util.Logger;
import registrationScheduler.threadMgmt.WorkerThread;
import registrationScheduler.threadMgmt.CreateWorkers;
import registrationScheduler.store.Results;
import registrationScheduler.scheduler.Scheduler;



public class Driver{
	public static void main(String args[]) {
		int numThreads =0;
		Logger logger = new Logger();
		//Input validation
		if(args.length != 5){
			System.err.println("Incorrect number of input arguments");
			System.exit(1);
		}
		try{
			if(Integer.parseInt(args[3]) < 1 || Integer.parseInt(args[3])>3){
				System.err.println("Given Value for numThreads is out of bounds");
				System.exit(1);
			}
			else{
				numThreads = Integer.parseInt(args[3]);
			}
			if(Integer.parseInt(args[4])<0 || Integer.parseInt(args[4]) >4){
				System.err.println("Given value for DebugValue is out of bounds");
				System.exit(1);
			}
			else{
				logger.setDebugValue(Integer.parseInt(args[4]));
			}
		}
		catch(NumberFormatException e){
			System.err.println("parse Int failed");
			System.exit(1);
		}
		finally{
			
		}
		FileProcessor preferenceFile = new FileProcessor(args[0],logger);
		FileProcessor addDropFile = new FileProcessor(args[1],logger);
		FileProcessor outFile = new FileProcessor(args[2],logger);
		Results results = new Results(logger);
		CreateWorkers createWorkers = new CreateWorkers(preferenceFile,addDropFile,outFile, results, logger );
		createWorkers.startWorkers(numThreads);
		
		//results.writeSchedulesToFile(outFile);
	
		
	}
	
}

