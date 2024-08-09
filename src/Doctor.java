
import java.util.Scanner;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Doctor {
    private Connection connection;

    private Scanner scanner;

    public Doctor(Connection connection,Scanner scanner){
        this.connection = connection;
        this.scanner=scanner;
    }


    public void addDoctors() {

        System.out.println("Enter Doctor Name: ");
        String name = scanner.next();
        System.out.println("Enter Doctor Id: ");
        int id = scanner.nextInt();
        System.out.println("Enter  Specialization: ");
        String specialization = scanner.next();

        try {
            String query = "INSERT INTO doctors(name, id, specialization) VALUES(?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, name);
            preparedStatement.setInt(2, id);
            preparedStatement.setString(3, specialization);
            int affectedRows = preparedStatement.executeUpdate();
            if (affectedRows > 0) {
                System.out.println("Doctor Added Successfully!!");
            } else {
                System.out.println("Doctor to add Patient!!");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void viewDoctors(){
        String query = "select * from doctors";
        try{
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            System.out.println("Doctors: ");
            System.out.println("+------------+--------------------+------------------+");
            System.out.println("| Doctor Id  | Name               | Specialization   |");
            System.out.println("+------------+--------------------+------------------+");
            while(resultSet.next()){
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String specialization = resultSet.getString("specialization");
                System.out.printf("| %-10s | %-18s | %-16s |\n", id, name, specialization);
                System.out.println("+------------+--------------------+------------------+");
            }

        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public boolean getDoctorById(int id){
        String query = "SELECT * FROM doctors WHERE id = ?";
        try{
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                return true;
            }else{
                return false;
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }
}
