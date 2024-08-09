import java.sql.*;
import java.util.Scanner;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

    public class Hospital {

        private static final String url = "jdbc:oracle:thin:@localhost:1521:orcl";

        private static final String username = "sys as sysdba";

        private static final String password = "Red@007";

        public static void main(String[] args) {
            try {
                Class.forName("oracle.jdbc.driver.OracleDriver");
            } catch (ClassNotFoundException e) {
                e.printStackTrace();

            }
            Scanner scanner = new Scanner(System.in);

            try {
                Connection connection = DriverManager.getConnection(url, username, password);
                Patient patient = new Patient(connection, scanner);
                Doctor doctor = new Doctor(connection,scanner);
                while (true) {
                    System.out.println("HOSPITAL MANAGEMENT SYSTEM");
                    System.out.println("1.Add Patient");
                    System.out.println("2.Add Doctor");
                    System.out.println("3.View Patients");
                    System.out.println("4.View Doctors");
                    System.out.println("5.Book Appointment");
                    System.out.println("6.Exit");
                    System.out.println("Enter your choice: ");
                    int choice = scanner.nextInt();

                    switch (choice) {
                        case 1:
                            // add patient
                            patient.addPatient();
                        case 2:
                            // add doctor
                            doctor.addDoctors();
                        case 3:
                            // View patient
                            patient.viewPatients();
                        case 4:
                            // add patient
                            doctor.viewDoctors();
                        case 5:
                            // add patient
                        case 6:
                            return;
                        default:
                            System.out.println("Enter a valid choice!!!");

                    }

                }

            } catch (SQLException e) {

                e.printStackTrace();

            }

        }



        public static void bookAppointment(Patient patient, Doctor doctor, Connection connection, Scanner scanner){
            System.out.print("Enter Patient Id: ");
            int patientId = scanner.nextInt();
            System.out.print("Enter Doctor Id: ");
            int doctorId = scanner.nextInt();
            System.out.print("Enter appointment date (YYYY-MM-DD): ");
            String appointmentDate = scanner.next();
            if(patient.getPatientById(patientId) && doctor.getDoctorById(doctorId)){
                if(checkDoctorAvailability(doctorId, appointmentDate, connection)){
                    String appointmentQuery = "INSERT INTO appointments(patient_id, doctor_id, appointment_date) VALUES(?, ?, ?)";
                    try {
                        PreparedStatement preparedStatement = connection.prepareStatement(appointmentQuery);
                        preparedStatement.setInt(1, patientId);
                        preparedStatement.setInt(2, doctorId);
                        preparedStatement.setString(3, appointmentDate);
                        int rowsAffected = preparedStatement.executeUpdate();
                        if(rowsAffected>0){
                            System.out.println("Appointment Booked!");
                        }else{
                            System.out.println("Failed to Book Appointment!");
                        }
                    }catch (SQLException e){
                        e.printStackTrace();
                    }
                }else{
                    System.out.println("Doctor not available on this date!!");
                }
            }else{
                System.out.println("Either doctor or patient doesn't exist!!!");
            }
        }

        public static boolean checkDoctorAvailability(int doctorId, String appointmentDate, Connection connection){
            String query = "SELECT COUNT(*) FROM appointments WHERE doctor_id = ? AND appointment_date = ?";
            try{
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                preparedStatement.setInt(1, doctorId);
                preparedStatement.setString(2, appointmentDate);
                ResultSet resultSet = preparedStatement.executeQuery();
                if(resultSet.next()){
                    int count = resultSet.getInt(1);
                    if(count==0){
                        return true;
                    }else{
                        return false;
                    }
                }
            } catch (SQLException e){
                e.printStackTrace();
            }
            return false;
        }
    }




