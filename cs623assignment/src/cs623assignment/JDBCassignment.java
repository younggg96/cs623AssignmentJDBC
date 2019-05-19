package cs623assignment;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCassignment {
	// JDBC driver name and database URL
		 static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
		 static final String DB_URL = "jdbc:mysql://localhost:3306/assignment?useLegacyDatetimeCode=false&serverTimezone=UTC";
		 
		 //  Database credentials
		 static final String USER = "root";
		 static final String PASS = "yang960123";
		 
		 public static void main(String[] args) {
		 Connection conn = null;
		 Statement stmt = null;
		 try{
		    //STEP 2: Register JDBC driver
		    Class.forName("com.mysql.cj.jdbc.Driver");

		    //STEP 3: Open a connection
		    System.out.println("Connecting to database...");
		    conn = DriverManager.getConnection(DB_URL,USER,PASS);

		    //STEP 4: Execute a query
		    System.out.println("Creating statement...");
		    
		    //SQL String
		    String sql1, sql2, sql3, sql4, sql5, sql6, sql7;
		    
		    /**
		     * Question1: Get paper and author details by author ID (emailaddr)
		     */
		    
		    sql1 = "SELECT * FROM AUTHOR,AUTHOR_SUBMITS_PAPER,PAPER "
		    		+ "where author.EmailAddr=AUTHOR_SUBMITS_PAPER.EmailAddr_AUTHOR "
		    		+ "and AUTHOR_SUBMITS_PAPER.ID_PAPER=PAPER.ID "
		    		+ "and author.emailaddr = ?";
		    
		    /**
		     * Question 2: Get reviews for a paper by paperId
		     */
		    
		    sql2 = "SELECT * FROM REVIEW, REVIEWER_SUBMITS_REVIEW, REVIEWER_ASSIGNED_PAPER, REVIEWER, PAPER "
		    		+ "where REVIEW.Id=REVIEWER_SUBMITS_REVIEW.ID_REVIEW "
		    		+ "and REVIEWER_SUBMITS_REVIEW.EmailAddr_REVIEWER=REVIEWER.EmailAddr "
		    		+ "and REVIEWER.EmailAddr=REVIEWER_ASSIGNED_PAPER.EmailAddr_REVIEWER "
		    		+ "and PAPER.ID=REVIEWER_ASSIGNED_PAPER.ID_PAPER "
		    		+ "and PAPER.ID = ?";
		    
		    /**
		     * Question 3: Get a count of all paper submitted
		     */
		    
		    sql3 = "SELECT COUNT(*) as numPapers from Paper";
		    
		    /**
		     * Question 4: Submit a new Paper
		     */
		    
		    sql4 = "insert into author values('Gyoung@pace.edu', 'Tom', 'James')";
		    sql5 = "insert into paper values(6, 'Javaweb', 'learning', 'learningJava')";
		    sql6 = "insert into AUTHOR_SUBMITS_PAPER values('Gyoung1@pace.edu', 6)";
		    
		    /**
		     * Question 5: Deleting an author from the table
		     */
		    
		    sql7 = "delete from author where EmailAddr = 'James456@pace.edu'";
		    
		    PreparedStatement ps1 = conn.prepareStatement(sql1);
		    PreparedStatement ps2 = conn.prepareStatement(sql2);
		    Statement statement = conn.createStatement();
		    ResultSet rs3 = statement.executeQuery(sql3);
		    
		    ps1.setString(1, "tom123@pace.edu");
		    ps2.setString(1, "aaa");
		    ResultSet rs1 = ps1.executeQuery();
		    ResultSet rs2 = ps2.executeQuery();
		    
		    int update1, update2 = 0;
		    
		    //STEP 5: Extract data from result set
		    
		    while(rs1.next()) {
		    	
		       String AuthorEmailAddr = rs1.getString("EmailAddr");
		       String AuthorFirstName = rs1.getString("FirstName");
		       String AuthorLastName = rs1.getString("LastName");
		       int PaperId = rs1.getInt("ID");
		       String PaperTitle = rs1.getString("Title");
		       String PaperAbsrract = rs1.getString("Abstract");
		       String PaperFileName = rs1.getString("FileName");

		       System.out.println(" AuthorEmailAddr " + AuthorEmailAddr);
		       System.out.println(" AuthorFirstName " + AuthorFirstName);
		       System.out.println(" AuthorLastName " + AuthorLastName);
		       System.out.println(" PaperId " + PaperId);
		       System.out.println(" PaperTitle " + PaperTitle);
		       System.out.println(" PaperAbsrract " + PaperAbsrract);
		       System.out.println(" PaperFileName " + PaperFileName);
		       
		    }
		    System.out.println("==================================");
		    
		    while(rs2.next()) {
		    	
			    	String string1 = rs2.getString("Recommendation");
			    	int int1 = rs2.getInt("Id");
			    	int int2 = rs2.getInt("MeritScore");
			    	int int3 = rs2.getInt("PaperId");
			    	int int4 = rs2.getInt("ReadabilityScore");
			    	int int5 = rs2.getInt("ReviewerId");
			    	int int6 = rs2.getInt("RelevanceScore");
			    	int int7 = rs2.getInt("OriginalityScore");
			    	
			    	System.out.println(" Reconmmendation " + string1);
			    	System.out.println(" Id " + int1);
			    	System.out.println(" MeritScore " + int2);
			    	System.out.println(" PaperId " + int3);
			    	System.out.println(" ReadabilityScore " + int4);
			    	System.out.println(" RelevanceScore " + int6);
			    	System.out.println(" OriginalityScore " + int7);
			    	System.out.println(" ReviewerId " + int5);
		    	
		    }
		    
		    System.out.println("==================================");
		    
		    while(rs3.next()) {
		    	
			    	int num = rs3.getInt("numPapers");
			    	System.out.println("Paper:" + num);
		    }
		    
		    System.out.println("==================================");
		    
		    update1 = statement.executeUpdate(sql4) + statement.executeUpdate(sql5) + statement.executeUpdate(sql6);
		    System.out.println("Question4: Effect line " + update1);
		    
		    System.out.println("==================================");
		    try {
		    		update2 = statement.executeUpdate(sql7);
		    	
		    } catch(SQLException se) {
		    		System.out.println(se);
		    } finally {
		    		System.out.println("Question5: Effect line " + update2);
		    }
		    
		    
//	      STEP 6: Clean-up environment
		    rs1.close();
		    ps1.close();
		    rs2.close();
		    ps2.close();
		    conn.close();
		   } catch(SQLException se) {
		       //Handle errors for JDBC
		       se.printStackTrace();
		   } catch(Exception e) {
		       //Handle errors for Class.forName
		       e.printStackTrace();
		   } finally {
		       //finally block used to close resources
		       try {
		          if(stmt!=null)
		             stmt.close();
		       } catch(SQLException se2) {
		    	   		se2.printStackTrace();
		       } 
		       
		       try {
		    	   		if(conn!=null)
		    	   		conn.close();
		       } catch (SQLException se) {
		    	   	 	se.printStackTrace();
		       } //end finally try
		   }//end try
		 		System.out.println("Goodbye!");
		}//end main


}
