create database java_cc_2869;
use java_cc_2869;
drop database java_cc_2869;
CREATE TABLE Patient (patientId INT PRIMARY KEY,firstName VARCHAR(50) NOT NULL,lastName VARCHAR(50) NOT NULL,dateOfBirth DATE NOT NULL,gender VARCHAR(10) NOT NULL,contactNumber VARCHAR(15) NOT NULL,address VARCHAR(255) NOT NULL);
CREATE TABLE Doctor (doctorId INT PRIMARY KEY,firstName VARCHAR(50) NOT NULL,lastName VARCHAR(50) NOT NULL,specialization VARCHAR(100) NOT NULL,contactNumber VARCHAR(15) NOT NULL);
CREATE TABLE Appointment (appointmentId INT AUTO_INCREMENT PRIMARY KEY,patientId INT,doctorId INT,appointmentDate DATE NOT NULL,description TEXT,FOREIGN KEY (patientId) REFERENCES Patient(patientId),FOREIGN KEY (doctorId) REFERENCES Doctor(doctorId));

INSERT INTO Patient (patientId, firstName, lastName, dateOfBirth, gender, contactNumber, address)
VALUES
(1, 'John', 'Doe', '1990-05-15', 'Male', '1234567890', '123 Main St'),
(2, 'Alice', 'Smith', '1982-08-20', 'Female', '9876543210', '456 Oak St'),
(3, 'Bob', 'Johnson', '1995-03-10', 'Male', '8765432109', '789 Elm St'),
(4, 'Emma', 'Williams', '1988-11-25', 'Female', '2345678901', '101 Pine St'),
(5, 'Charlie', 'Brown', '1993-07-12', 'Male', '7890123456', '202 Cedar St'),
(6, 'Sophia', 'Miller', '1980-09-03', 'Female', '3456789012', '303 Maple St'),
(7, 'Daniel', 'Davis', '1991-01-28', 'Male', '8901234567', '404 Birch St'),
(8, 'Olivia', 'Taylor', '1987-06-15', 'Female', '4567890123', '505 Spruce St'),
(9, 'Ethan', 'Moore', '1994-04-18', 'Male', '9012345678', '606 Oakwood St'),
(10, 'Ava', 'Harris', '1984-12-07', 'Female', '1230987654', '707 Willow St');
INSERT INTO Doctor (doctorId, firstName, lastName, specialization, contactNumber)
VALUES
(1, 'Dr. Smith', 'Johnson', 'Cardiologist', '1234567890'),
(2, 'Dr. Emily', 'Williams', 'Dermatologist', '9876543210'),
(3, 'Dr. Michael', 'Clark', 'Orthopedic Surgeon', '8765432109'),
(4, 'Dr. Sophia', 'Davis', 'Pediatrician', '2345678901'),
(5, 'Dr. Benjamin', 'Moore', 'Neurologist', '7890123456'),
(6, 'Dr. Olivia', 'Brown', 'Oncologist', '3456789012'),
(7, 'Dr. Ethan', 'Miller', 'Gynecologist', '8901234567'),
(8, 'Dr. Ava', 'Taylor', 'Psychiatrist', '4567890123'),
(9, 'Dr. Noah', 'Jones', 'Urologist', '9012345678'),
(10, 'Dr. Mia', 'Harris', 'Endocrinologist', '1230987654');
INSERT INTO Appointment (appointmentId, patientId, doctorId, appointmentDate, description)
VALUES
(1, 1, 1, '2023-06-01', 'Regular checkup'),
(2, 2, 2, '2023-06-02', 'Skin examination'),
(3, 3, 3, '2023-06-03', 'Orthopedic consultation'),
(4, 4, 4, '2023-06-04', 'Pediatric checkup'),
(5, 5, 5, '2023-06-05', 'Neurology consultation'),
(6, 6, 6, '2023-06-06', 'Oncology follow-up'),
(7, 7, 7, '2023-06-07', 'Gynecology appointment'),
(8, 8, 8, '2023-06-08', 'Psychiatry session'),
(9, 9, 9, '2023-06-09', 'Urology checkup'),
(10, 10, 10, '2023-06-10', 'Endocrinology consultation');

select * from Appointment;
