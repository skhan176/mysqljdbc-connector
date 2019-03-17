
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class db_login {

	public db_login (){
		
	}

	public void testconnection_mysql (String user, String pass) {        
        Connection connect = null;
        PreparedStatement preparedStatement = null;
        ResultSet rs;

		try {
		      // This will load the MySQL driver, each DB has its own driver
		      Class.forName("com.mysql.jdbc.Driver");
		      // Setup the connection with the DB
		      connect = DriverManager
	    		          .getConnection("address of local host" + "user=" + "username" + "&password=" + "password" + "&verifyServerCertificate=false&requireSSL=false&useSSL=false");

	    	  String qry1a = "SELECT user from <your user table> where user= '" + user + "' and pass= '" + pass + "'"; 

	    	  //System.out.println(qry1a);
	    	  preparedStatement = connect.prepareStatement(qry1a);
	    	  // "id, uid, create_time, token for id_management.id_logtime";
	    	  // Parameters start with 1
	    	  
	    	  ResultSet r1=preparedStatement.executeQuery();

	            if (r1.next())
	            {
	              String nt = r1.getString(1); 
	              System.out.println(" Login success");
	            } else {
	            	System.out.println(" Login fail");	
	            }
	            r1.close();
	            preparedStatement.close();
	    	  
	    	} catch (Exception e) {
	    		try {
					throw e;
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
		  	} finally {
			      if (preparedStatement != null) {
				        try {
							preparedStatement.close();
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
				  }	      

			      if (connect != null) {
			        try {
						connect.close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			      }
		    }
	}
	
 
	
	public static void main(String[] args)
	{
		try
		{
			if (args.length != 2) {
				System.out.println("Usage: java -jar db_login.jar <username> <password>");
				System.out.println("Success returns errorlevel 0. Error return greater than zero.");
				System.exit(1);
			}

	        /* Print a copyright. */
	        System.out.println("Example for mysql DB connection via Java");
	        System.out.println("Copyright: Bon Sy");
	        System.out.println("Free to use this at your own risk!");
	        
	    	db_login DBConnect_instance = new db_login();	       
	    	DBConnect_instance.testconnection_mysql(args[0], args[1]);

		} 
		catch (Exception e){
			// probably error in input
			System.out.println("Hmmm... Looks like input error ....");
		}		
  }
	
	
}


