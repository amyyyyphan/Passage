//package connectivity;
//
//import java.sql.Connection;
//import java.sql.DriverManager;
//
//public class ConnectionClass {
//	public Connection connection;
//	
//	public Connection getConnection() {
//		String dbName = "Passage";
//		String username = "root";
//		String password = "group8";
//		
//		try {
//			Class.forName("com.mysql.jdbc.Driver");
//			
//			connection = DriverManager.getConnection("jdbc:mysql://localhost/" + dbName + username + password);
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//		return connection;
//	}
//
//}
