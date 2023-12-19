package com.hexaware.exception;
import java.io.FileNotFoundException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class FileHandlingException extends Exception{
	public FileHandlingException(String message) {
		super(message);
	}
//	public static void read() {
//        try {
//        	Scanner sc=new Scanner(System.in);
//        	System.out.println("Enter file name in format (Filename.txt)");
//        	String File_name=sc.nextLine();
//        	String content = readFromFile(File_name);
//            System.out.println("Read content from file: " + content);
//        } 
//        catch (FileNotFoundException e) {
//            // Handle the FileNotFoundException
//            System.out.println("File not found: " + e.getMessage());
//            //e.printStackTrace();
//        }
//        catch (Exception e) {
//            System.err.println("An unexpected error occurred: " + e.getMessage());
//        }
//    }
//	private static String readFromFile(String fileName) throws IOException {
//        StringBuilder content = new StringBuilder();
//        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
//            String line;
//            while ((line = reader.readLine()) != null) {
//                content.append(line).append("\n");
//            }
//        }
//        return content.toString();
//    }
}