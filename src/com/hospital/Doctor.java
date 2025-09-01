package com.hospital;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;

public class Doctor {
	  private static Connection connection;
	   
		public Doctor(Connection connection) {
			this.connection = connection;
			
		}
	    
	      public static void viewDoctors() {
	    	  String query = "select * from doctors";
	    	  try {
	    		  PreparedStatement Preparestatement = connection.prepareStatement(query);
	    		  ResultSet resultset = Preparestatement.executeQuery();
	    		  System.out.println("doctors: ");
	    		  System.out.println("+--------------+--------------+---------------+");
	    		 System.out.println("| doctor id    | name         |spacialization  | ");
	    		 System.out.println("+----------------+--------------+---------------+");
	    		 while(resultset.next()) {
	    			 int id = resultset.getInt("id");
	    			 String name = resultset.getString("name");
	    			 String specialization = resultset.getString("specialization");
	    			 System.out.printf("|%-12s|%-20s|%-18s|\n",id,name,specialization);
	    			 System.out.println("+------------+----------+-----------+-----------+");
	    		 }
	    	  }
	    	  catch(SQLException e) {
	    		  e.printStackTrace();
	    	  }
	      }
	      public static boolean getDoctorById(int id) {
	    	  String query = "SELECT * FROM doctors WHERE id = ?";
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

