package com.hexaware.dao;

import java.sql.SQLException;
import java.util.List;

import com.hexaware.entity.Appointment;
import com.hexaware.exception.AppointmentNotFoundException;
import com.hexaware.exception.DoctorNotFoundException;
import com.hexaware.exception.PatientNumberNotFoundException;

public interface IHospitalService {
	public Appointment getAppointment(int appointmentId) throws SQLException;
	public List<Appointment> getAppointmentsForPatient(int patientId) throws PatientNumberNotFoundException;
	public List<Appointment> getAppointmentsForDoctor(int doctorId) throws DoctorNotFoundException;
	public List<Appointment> getAppointmentsForAllDoctor();
	public boolean scheduleAppointment(Appointment appointment);
	public boolean updateAppointment(Appointment appointment) throws AppointmentNotFoundException;
	public boolean cancelAppointment(int appointmentId) throws AppointmentNotFoundException;
	
	
}
