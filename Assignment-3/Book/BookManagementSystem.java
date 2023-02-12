package JDBc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class BookManagementSystem {
		  static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";  
		  static final String DB_URL = "jdbc:mysql://localhost/userdb";

		  static final String USER = "root";
		  static final String PASS = "root";
		  
		  public static void main(String[] args) {
		    Connection conn = null;
		    Statement stmt = null;
		    try {
		      Class.forName("com.mysql.cj.jdbc.Driver");
		      System.out.println("Connecting to database...");
		      conn = DriverManager.getConnection(DB_URL, USER, PASS);
		      System.out.println("Creating statement...");
		      stmt = conn.createStatement();
		      
		      String sql = "INSERT INTO books (book_id, book_name, author_name, edition) " +
		                   "VALUES (1, 'Introduction to Java', 'Daniel Liang', 9)";
		      stmt.executeUpdate(sql);
		      System.out.println("Book added successfully");
		      
		      sql = "SELECT book_id, book_name, author_name, edition FROM books";
		      ResultSet rs = stmt.executeQuery(sql);
		      while (rs.next()) {
		        int book_id = rs.getInt("book_id");
		        String book_name = rs.getString("book_name");
		        String author_name = rs.getString("author_name");
		        int edition = rs.getInt("edition");
		        
		        System.out.print("Book ID: " + book_id);
		        System.out.print(", Book Name: " + book_name);
		        System.out.print(", Author Name: " + author_name);
		        System.out.println(", Edition: " + edition);
		      }
		      rs.close();
		      stmt.close();
		      conn.close();
		    } catch (SQLException se) {
		      se.printStackTrace();
		    } catch (Exception e) {
		      e.printStackTrace();
		    } finally {
		      try {
		        if (stmt != null)
		          stmt.close();
		      } catch (SQLException se2) {
		      }
		      try {
		        if (conn != null)
		          conn.close();
		      } catch (SQLException se) {
		        se.printStackTrace();
		      }
		    }
		  }
		}

