
package registrationScheduler.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.io.FileNotFoundException;

public class FileProcessor{
	private String fileName;
	private File inFile;
	private Scanner inScanner;
	
	public FileProcessor(String fn){
		fileName = fn;
		inFile = new File(fn);
	}
	
	public void createScanner(){
		try{
			inScanner = new Scanner(inFile);
		}
		catch(FileNotFoundException e2){
			System.err.println("File given does not exist");
			System.exit(1);
		}
		finally{}
	}
	
	public Scanner getScanner(){
		return inScanner;
	}
	/**
	This method exists to read a specific line from the file
	specified when the FileProcessor object was created. 
	@param lineNum The line the user wishes to read from the file
	@return s The line of the file returned as a string
	*/
	public synchronized String readNextLine(){
			return inScanner.nextLine();
	}

	public void clearFile(){
		try{
			File outFile = new File(fileName);
			FileWriter fw = new FileWriter(outFile);
			fw.write("");
			fw.flush();
			fw.close();
		}catch(IOException e){
			e.printStackTrace();
			System.err.println("An error occured handling the file");
			System.exit(1);
		}finally{

		}
	}
	/**
	This method exists to write a string given by the user to the
	file specified when the FileProcessor object was created.
	@param s The String the user wants to write to the file 
	*/
	public void write(String s){
		try{
			File outFile = new File(fileName);
			FileWriter fw = new FileWriter(outFile,true);
			fw.write(s);
			fw.flush();
			fw.close();
		}catch(IOException e){
			e.printStackTrace();
			System.exit(1);
		}finally{

		}
	}

	public String toString(){
		return this.fileName;
	}

	public void setFileName(String newFileName){
		this.fileName = newFileName;
	}

}
