package com.hexaware.mainmod;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import com.hexaware.dao.HospitalServiceImpl;
import com.hexaware.dao.IHospitalService;
import com.hexaware.entity.Appointment;
import com.hexaware.exception.AppointmentNotFoundException;
import com.hexaware.exception.DoctorNotFoundException;
import com.hexaware.exception.PatientNumberNotFoundException;

public class MainModule {
	static IHospitalService serviceImpl = new HospitalServiceImpl();
	public static void main(String[] args) throws ParseException, AppointmentNotFoundException, PatientNumberNotFoundException, SQLException {
		System.out.println("==============================\n");
		System.out.println("Hospital Management System\n");
		System.out.println("==============================\n\n");
		System.out.println("1. Find your appointment");
		System.out.println("2. Get all your appointments");
		System.out.println("3. Doctor's Appointment");
		System.out.println("4. Schedule Your Appointment");
		System.out.println("5. Update your appointment");
		System.out.println("6. Cancle your appointment");	
		System.out.println("7. See full appointments of Doctor's");
		System.out.println("8. Exit");
		Scanner sc = new Scanner(System.in);
		boolean exit = false;
		while (!exit) {
			System.out.println("\n\nPlease enter your choice: [1-7]\n\n");
			System.out.println("[1] Find your appointment");
			System.out.println("[2] Get all your appointments");
			System.out.println("[3] Doctor's Appointment");
			System.out.println("[4] Schedule Your Appointment");
			System.out.println("[5] Update your appointment");
			System.out.println("[6] Cancle your appointment");
			System.out.println("[7] See full appointments of Doctor's");
			System.out.println("[8] Exit");
			int choice = sc.nextInt();
		    performOperation(choice);
		    System.out.println("==============================================\n\n");
		    System.out.println("Do you want to perform another operation? (y/n)");
		    System.out.println("==============================================");
		    String response = sc.next();
		    if (response.equalsIgnoreCase("n")) {
		    	System.out.println("Exiting System...Goodbye..!");
		    	System.exit(0);
		        exit = true;
		    }
		}
	}
	private static void performOperation(int choice) throws PatientNumberNotFoundException, SQLException {
		Scanner sc= new Scanner(System.in);
	    switch (choice) {
	        case 1:
	        	System.out.println("Enter your appointment Id: ");
                int aid = sc.nextInt();
                Appointment appointment= serviceImpl.getAppointment(aid);
                System.out.println(appointment);
                break;
	        case 2:
	        	System.out.println("Enter your patient Id: ");
                int pid = sc.nextInt();
                List<Appointment> patientAppointments= serviceImpl.getAppointmentsForPatient(pid);
                for(Appointment apnt:patientAppointments) {
                	System.out.println(apnt);
                }
                break;
	        case 3:
	        	System.out.println("Enter your Doctor's Id: ");
                int did = sc.nextInt();
			List<Appointment> doctorAppointments = null;
			try {
				doctorAppointments = serviceImpl.getAppointmentsForDoctor(did);
			} catch (DoctorNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
                for(Appointment dapnt:doctorAppointments) {
                	System.out.println(dapnt);
                }
                break;
	        case 4:
	        	System.out.println("Make new Schedule:");
	        	System.out.println("Press 1 for enter data or press 2 to see the doctor's appointments:");
	        	int opt = sc.nextInt();
	        	switch(opt) {
	        	case 1:
	        		System.out.println("Enter your patientId:");
		        	int npid=sc.nextInt();
		        	System.out.println("Enter your doctorId you want to consult:");
		        	int ndid = sc.nextInt();
		        	System.out.println("Enter the date you want to schedule appointment in format(YYYY-MM-DD):");
		        	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		            LocalDate napd = LocalDate.parse(sc.next(), formatter);
		        	System.out.println("Describe your issue:");
		        	sc.nextLine();
		        	String ndesc = sc.nextLine();
	                Appointment newAppointment = new Appointment(npid,ndid,napd,ndesc);
	                serviceImpl.scheduleAppointment(newAppointment);
	                System.out.println("Your new Appointment is\n"+newAppointment);
	                break;
	        	case 2:
	        		List<Appointment> dAppointments = serviceImpl.getAppointmentsForAllDoctor();
	        		System.out.println("\nHere is the list of doctors appointments:");
	        		for(Appointment daptm : dAppointments) {
	        			System.out.println(daptm);
	        		}
	        		break;
	        	case 3:
	        		System.out.println("You should have pressed 1/2.");
	        		break;
	        	}
	        	
                break;
	        case 5:
	        	System.out.println("Update your Schedule:");
	        	System.out.println("Enter your appointmentId:");
	        	int uaid=sc.nextInt();
	        	System.out.println("Enter your patientId:");
	        	int upid=sc.nextInt();
	        	System.out.println("Enter your doctorId you want to consult:");
	        	int udid = sc.nextInt();
	        	System.out.println("Enter the date you want to schedule appointment in format(YYYY-MM-DD):");
	        	DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	            LocalDate uapd = LocalDate.parse(sc.next(), formatter2);
	        	System.out.println("Describe your issue:");
	        	sc.nextLine();
	        	String udesc = sc.nextLine();
                Appointment updateAppointment = new Appointment(uaid,upid,udid,uapd,udesc);
                try{
                	if(!serviceImpl.updateAppointment(updateAppointment)) {
                		throw new AppointmentNotFoundException("");
                	}else {
                		System.out.println("Your schedule has been updated...\n");
                	}
                }catch(AppointmentNotFoundException e) {
                	System.out.println(e.getMessage());
                }
                System.out.println("\nVerify your schedule>>"+updateAppointment);
                break;
	        case 6:
	        	System.out.println("Cancel your Schedule:");
	        	System.out.println("Enter your appointmentId:");
	        	int caid=sc.nextInt();
	        	try{
	        		if(!serviceImpl.cancelAppointment(caid)) {
	        			throw new AppointmentNotFoundException("No such appointmentId is found,Check your appointmentID");
	        		}else {
	        			System.out.println("Your appointment has been cancelled...");
	        		}
	        	}catch(AppointmentNotFoundException e){
	        		System.out.println(e.getMessage());
	        	}
                break;
	        case 7:
	        	List<Appointment> dAppointments = serviceImpl.getAppointmentsForAllDoctor();
        		System.out.println("Here is the list of doctors appointments:");
        		for(Appointment daptm : dAppointments) {
        			System.out.println(daptm);
        		}
        		break;
	        case 8:
	        	System.out.println("Exited Application...!");
	        	System.exit(0);
	        default:
	            System.out.println("Invalid choice. Please enter a number between 1 and 7.");
	            break;
	    }
	}
}