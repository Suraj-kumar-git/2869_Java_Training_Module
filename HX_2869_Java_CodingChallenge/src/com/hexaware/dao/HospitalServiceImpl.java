package com.hexaware.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.hexaware.entity.Appointment;
import com.hexaware.exception.AppointmentNotFoundException;
import com.hexaware.exception.DoctorNotFoundException;
import com.hexaware.exception.PatientNumberNotFoundException;
import com.hexaware.util.DBConnUtil;
import com.hexaware.util.DBPropertyUtil;

public class HospitalServiceImpl implements IHospitalService {

	private Connection connection;
	public static boolean isLoggedIn = false;

	public HospitalServiceImpl() {
		this.connection = DBConnUtil.getConnection();
	}

	@Override
	public Appointment getAppointment(int appointmentId) throws SQLException {
		Appointment appointment = null;
		String query = "SELECT * FROM Appointment WHERE appointmentId = ?";
		try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
			preparedStatement.setInt(1, appointmentId);

			try (ResultSet resultSet = preparedStatement.executeQuery()) {
				if (resultSet.next()) {
					appointment = mapResultSetToAppointment(resultSet);
				} else {
					throw new AppointmentNotFoundException("Appointment Id is invalid");
				}
			}
		} catch (AppointmentNotFoundException e) {
//            e.printStackTrace();
			System.out.println(e.getMessage());
		}
		return appointment;
	}

	@Override
	public List<Appointment> getAppointmentsForPatient(int patientId) throws PatientNumberNotFoundException {
		List<Appointment> appointments = new ArrayList<>();
		String query = "SELECT * FROM Appointment WHERE patientId = ?";
		try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
			preparedStatement.setInt(1, patientId);
			int count = 0;
			try (ResultSet resultSet = preparedStatement.executeQuery()) {
				while (resultSet.next()) {
					count++;
					Appointment appointment = mapResultSetToAppointment(resultSet);
					appointments.add(appointment);
				}
			}
		} catch (SQLException e) {
			throw new PatientNumberNotFoundException("Patient id is not found in the database! Check it once...");
		}
		return appointments;
	}

	@Override
	public List<Appointment> getAppointmentsForDoctor(int doctorId) throws DoctorNotFoundException {
		List<Appointment> appointments = new ArrayList<>();
		String query = "SELECT * FROM Appointment WHERE doctorId = ?";
		try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
			preparedStatement.setInt(1, doctorId);

			try (ResultSet resultSet = preparedStatement.executeQuery()) {
				while (resultSet.next()) {
					Appointment appointment = mapResultSetToAppointment(resultSet);
					appointments.add(appointment);
				}
			}
		} catch (SQLException e) {
//			e.printStackTrace();
			throw new DoctorNotFoundException("No Doctor is there with id " + doctorId);
		}
		return appointments;
	}

	@Override
	public boolean scheduleAppointment(Appointment appointment) {
		String query = "INSERT INTO Appointment (patientId, doctorId, appointmentDate, description) "
				+ "VALUES (?, ?, ?, ?)";
		try (PreparedStatement preparedStatement = connection.prepareStatement(query,
				Statement.RETURN_GENERATED_KEYS)) {
			preparedStatement.setInt(1, appointment.getPatientId());
			preparedStatement.setInt(2, appointment.getDoctorId());
			preparedStatement.setDate(3, java.sql.Date.valueOf(appointment.getAppointmentDate()));
			preparedStatement.setString(4, appointment.getDescription());

			int affectedRows = preparedStatement.executeUpdate();
			if (affectedRows > 0) {
				ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
				if (generatedKeys.next()) {
					appointment.setAppointmentId(generatedKeys.getInt(1));
					return true;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean updateAppointment(Appointment appointment) throws AppointmentNotFoundException {
		String query = "UPDATE Appointment SET patientId = ?, doctorId = ?, "
				+ "appointmentDate = ?, description = ? WHERE appointmentId = ?";
		try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
			preparedStatement.setInt(1, appointment.getPatientId());
			preparedStatement.setInt(2, appointment.getDoctorId());
			preparedStatement.setDate(3, java.sql.Date.valueOf(appointment.getAppointmentDate()));
			preparedStatement.setString(4, appointment.getDescription());
			preparedStatement.setInt(5, appointment.getAppointmentId());

			int affectedRows = preparedStatement.executeUpdate();
			return affectedRows > 0;
		} catch (SQLException e) {
//			e.printStackTrace();
			throw new AppointmentNotFoundException("Appointment Not Found");
		}
//		return false;
	}

	@Override
	public boolean cancelAppointment(int appointmentId) throws AppointmentNotFoundException {
		String query = "DELETE FROM Appointment WHERE appointmentId = ?";
		try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
			preparedStatement.setInt(1, appointmentId);

			int affectedRows = preparedStatement.executeUpdate();
			return affectedRows > 0;
		} catch (SQLException e) {
//			e.printStackTrace();
			throw new AppointmentNotFoundException("Appointment Not Found");
		}
//		return false;
	}

	private Appointment mapResultSetToAppointment(ResultSet resultSet) throws SQLException {
		Appointment appointment = new Appointment();
		appointment.setAppointmentId(resultSet.getInt("appointmentId"));
		appointment.setPatientId(resultSet.getInt("patientId"));
		appointment.setDoctorId(resultSet.getInt("doctorId"));
		appointment.setAppointmentDate(resultSet.getDate("appointmentDate").toLocalDate());
		appointment.setDescription(resultSet.getString("description"));
		return appointment;
	}

	@Override
	public List<Appointment> getAppointmentsForAllDoctor() {
		List<Appointment> appointments = new ArrayList<>();
		String query = "SELECT * FROM Appointment";
		try (Statement stmt = connection.createStatement()) {
			ResultSet resultSet = stmt.executeQuery(query);
			while (resultSet.next()) {
				Appointment appointment = mapResultSetToAppointment(resultSet);
				appointments.add(appointment);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return appointments;
	}

}
