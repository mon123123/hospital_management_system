package com.hospital;
import java.sql.Connection;
import java.util.Scanner;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.util.Scanner;
public class patient {
    private static Connection connection;
    private static Scanner scanner;
	public patient(Connection connection,Scanner scanner) {
		this.connection = connection;
		this.scanner = scanner;
	}
      public static void addPatient() {
    	  System.out.println("Enter patient name: ");
    	  String name = scanner.next();
    	  System.out.println("Enter Patient Age: ");
    	  int age = scanner.nextInt();
    	  System.out.println("Enter Patient Gender: ");
    	  String gender = scanner.next();
    	  try {
    		  String query = "INSERT INTO patients(name, age,Gender) VALUES(?,?,?)";
    		  PreparedStatement preparedstatement = connection.prepareStatement(query);
    		
    		  preparedstatement.setString(1,name);
    		  preparedstatement.setInt(2,age);
    		  preparedstatement.setString(3,gender);
    		  int affectedrows = preparedstatement.executeUpdate();
    		  if(affectedrows>0) {
    			  System.out.println("patient added successfully!!");
    		  }
    		  else {
    			  System.out.println("faild to add patient !!");
    		  }
    	  }
    	  catch(SQLException e) {
    		  e.printStackTrace();
    		  
    	  }
      }
      public static void viewpatient() {
    	  String query = "select * from patients";
    	  try {
    		  PreparedStatement preparedstatement = connection.prepareStatement(query);
    		  ResultSet resultset = preparedstatement.executeQuery();
    		  System.out.println("patients: ");
    		  System.out.println("+--------------+--------------+---------------+------------------+");
    		 System.out.println("| patient id    | name         |age            | gender           | ");
    		 System.out.println("+----------------+--------------+---------------+-------------------+");
    		 while(resultset.next()) {
    			 int id = resultset.getInt("id");
    			 String name = resultset.getString("name");
    			 int age = resultset.getInt("age");
    			 String gender = resultset.getString("gender");
    			 System.out.printf("|%-12s|%-20s|%-10s|%-12s|\n",id,name,age,gender);
    			 System.out.println("+------------+----------+-----------+-----------+");
    		 }
    	  }
    	  catch(SQLException e) {
    		  e.printStackTrace();
    	  }
      }
      public static boolean getPatientById(int id) {
    	  String query = "SELECT * FROM patients WHERE id = ?";
    	  try {
    		  PreparedStatement preparedstatement = connection.prepareStatement(query);
    		  preparedstatement.setInt(1,id);
    		  ResultSet resultset = preparedstatement.executeQuery();
    		  if(resultset.next()) {
    			  return true;
    		  }
    		  else {
    			  return false;
    		  }
    	  }
    	  catch(SQLException e) {
    		  e.printStackTrace();
    	  }
    	  return false;
      }


	

}
