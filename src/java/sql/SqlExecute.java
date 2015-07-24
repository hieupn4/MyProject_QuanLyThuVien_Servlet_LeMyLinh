package sql;

import java.sql.Connection;
import java.sql.DriverManager;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;


public class SqlExecute {
	private static String config="config.config";
	private static ResourceBundle resource;
	
	static{
		
	}
	public static Connection getConnection(){
		ResourceBundle.clearCache();
		resource = ResourceBundle.getBundle(config);
		
		Connection connection = null ;
		String driver = resource.getString("driver");
		String user=resource.getString("user.name");
		String pass=resource.getString("user.password");
		String url=resource.getString("host")+ "/" + resource.getString("database");
		System.out.println(url);
		//"jdbc:mysql://localhost/lib_sys";//
		try{
			Class.forName(driver).newInstance();
			connection = DriverManager.getConnection(url,user,pass);
		}catch(Exception e){
			System.out.println("Connection fail");
			System.out.println("Message : " + e.getMessage());
			e.printStackTrace();
			connection = null ;
		}
		return connection;
	}
	public static ResultSet executeCommand(String sqlCommand){
		ResultSet resultSet = null ;
		Connection connection = SqlExecute.getConnection();
		if(connection == null){
			System.out.println("Loi ket noi CSDL");
			return null ;
		}
		try {
			Statement statement = connection.createStatement();
			resultSet = statement.executeQuery(sqlCommand);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Message : " + e.getMessage());
			e.printStackTrace();
			resultSet = null ;
		}
		
		return resultSet;
	}
	public static boolean executeUpdate(String sqlCommand){
		Connection connection = SqlExecute.getConnection();
		if(connection == null){
			System.out.println("Ket noi CSDL loi. ");
			return false ;
		}
		try{
			Statement statement = connection.createStatement();
			statement.executeUpdate(sqlCommand);
		}catch(SQLException e){
			System.out.println("Err ");
			System.out.println("Message : " + e.getMessage());
			e.printStackTrace();
			return false ;
		}
		return true;
	}
}
