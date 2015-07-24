package manager.resource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


import sql.SqlExecute;

/*
 */
public abstract class AbstractResourceManager implements ResourceManager {
	protected final String RESOURCE_TABLE_NAME = "resource";
	protected final String RESOURCE_ID="id";
	protected final String RESOURCE_NAME="name";
	protected final String RESOURCE_AUTHORS="authors";
	protected final String RESOURCE_DOMAIN="domain";
	protected final String RESOURCE_AMOUNT = "amount";
	protected final String RESOURCE_DESCRIPTION="description";
	protected final String RESOURCE_TYPE = "resource_type";

  public boolean insert(Resource resource) {
	  String sql = "INSERT INTO " + RESOURCE_TABLE_NAME + 
	  			"("+RESOURCE_ID + "," + RESOURCE_NAME +","+ RESOURCE_AUTHORS +","+ RESOURCE_DOMAIN +","+ RESOURCE_AMOUNT + "," + RESOURCE_DESCRIPTION +"," + RESOURCE_TYPE + ")"
	  			+ " VALUES(?,?,?,?,?,?,?);";
	  Connection connection = SqlExecute.getConnection();
		if(connection == null){
			System.out.println("Loi ket noi CSDL");
			return false ;
		}
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			if(preparedStatement == null){
				System.out.println("Loi tao doi tuong preparedStatement trong ham addResourceBorrow");
				return false ;
			}
			preparedStatement.setString(1, resource.getID());
			preparedStatement.setString(2, resource.getName());
			preparedStatement.setString(3, resource.getAuthors());
			preparedStatement.setString(4, resource.getDomain());
			preparedStatement.setInt(5, resource.getAmount());
			preparedStatement.setString(6, resource.getDescription());
			preparedStatement.setString(7, resource.getType());
			
			System.out.println(preparedStatement.toString());
			
			preparedStatement.executeUpdate();
			//Dong ket noi
			preparedStatement.close();
			connection.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Err: Message : " + e.getMessage());
			e.printStackTrace();
			return false ;
		}
	  return true ;
  }

  public boolean delete(String resourceID) {
	  String sql = "DELETE FROM "+RESOURCE_TABLE_NAME + 
	  				" WHERE " + RESOURCE_ID + " = '"+resourceID + "'"; 
	  return SqlExecute.executeUpdate(sql);
  }
  public boolean update(Resource resource){
	  String sql = "UPDATE " + RESOURCE_TABLE_NAME + " SET " +
		RESOURCE_NAME + "='"+resource.getName()+"' , " +
		RESOURCE_AUTHORS + "='"+resource.getAuthors()+"' , "+
		RESOURCE_DOMAIN + "='"+resource.getDomain() + "' ,  " +
		RESOURCE_AMOUNT + "="+resource.getAmount()+", " +
		RESOURCE_DESCRIPTION + "='" + resource.getDescription()+"' , "+
		RESOURCE_TYPE + "='" + resource.getType()+"' " + 
		" WHERE "+ RESOURCE_ID + "='"+resource.getID()+"';";
	  
	  return SqlExecute.executeUpdate(sql) ;
  }
  
  public String check(String resourceID) {
		// TODO Auto-generated method stub
	  	String result = null ;
		String sql = "SELECT * FROM " + RESOURCE_TABLE_NAME +  
		" WHERE " + RESOURCE_ID + " = '" + resourceID + "';";
		ResultSet resultSet = SqlExecute.executeCommand(sql);
		if(resultSet == null){
			return null ;
		}
		try {
			if(resultSet.next()){
				result = resultSet.getString(RESOURCE_TYPE);
				resultSet.close();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

}