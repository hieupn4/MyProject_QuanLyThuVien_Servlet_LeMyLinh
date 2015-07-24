package manager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import sql.SqlExecute;

public class ResourceBorrowManagerImpl implements ResourceBorrowManager {
	// Table name and column name for store ResourceBorrow
	private static String TABLE_NAME = "borrow";
	private static String RESOURCE_ID = "book_id";
	private static String PATRON_ID = "user_id";
	private static String BORROW_DATE = "borrow_date";
	private static String RENDER_DATE = "render_date";

	public ResourceBorrowManagerImpl() {
		
	}

	@Override
	public boolean add(ResourceBorrow resourceBorrow) {
		String sql = "INSERT INTO "+TABLE_NAME +
		             "(" + RESOURCE_ID +","+ PATRON_ID + ","+BORROW_DATE+","+RENDER_DATE+")" 
		             + "VALUES(?,?,?,?)";
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
			
			preparedStatement.setString(1, resourceBorrow.getResourceID());
			preparedStatement.setString(2, resourceBorrow.getPatronID());
			java.util.Date dateBorrow = resourceBorrow.getBorrowDate();
			java.util.Date renderDate = resourceBorrow.getRenderDate();
			preparedStatement.setDate(3, new java.sql.Date(dateBorrow.getTime()));
			preparedStatement.setDate(4, new java.sql.Date(renderDate.getTime()));
			
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

	@Override
	public ResourceBorrow get(String resourceID, String patronID) {
		// TODO Auto-generated method stub
		ResourceBorrow resourceBorrow = null ;
		String sql = "SELECT * FROM "+ TABLE_NAME + 
		" WHERE " + RESOURCE_ID +"= ? AND " + PATRON_ID + "= ?";
		Connection connection = SqlExecute.getConnection();
		if(connection == null){
			System.out.println("Loi ket noi CSDL trong ham getResourceBorrow");
			return null ;
		}
		try{
			PreparedStatement statement = connection.prepareStatement(sql);
			if(statement != null){
				statement.setString(1, resourceID);
				statement.setString(2, patronID);
				
				ResultSet resultSet = statement.executeQuery();
				if(resultSet == null){
					return null ;
				}
				if(resultSet.next()){
					resourceBorrow = new ResourceBorrow();
					resourceBorrow.setResourceID(resourceID);
					resourceBorrow.setPatronID(patronID);
					resourceBorrow.setBorrowDate(resultSet.getDate(BORROW_DATE));
					resourceBorrow.setRenderDate(resultSet.getDate(RENDER_DATE));
				}
				
				resultSet.close();
				statement.close();
				connection.close();
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
		return resourceBorrow;
	}

	@Override
	public List<ResourceBorrow> getAllByPatron(String patronID) {
		// TODO Auto-generated method stub
		List<ResourceBorrow> list = null ;
		String sql = "SELECT * FROM "+ TABLE_NAME + 
		" WHERE " + PATRON_ID + "= ?";
		Connection connection = SqlExecute.getConnection();
		if(connection == null){
			System.out.println("Loi ket noi CSDL trong ham getResourceBorrow");
			return null ;
		}
		try{
			PreparedStatement statement = connection.prepareStatement(sql);
			if(statement != null){
				
				statement.setString(1, patronID);
				
				ResultSet resultSet = statement.executeQuery();
				if(resultSet == null){
					return null ;
				}
				list = new ArrayList<ResourceBorrow>();
				while(resultSet.next()){
					
					ResourceBorrow resourceBorrow = new ResourceBorrow();
					resourceBorrow.setResourceID(resultSet.getString(RESOURCE_ID));
					resourceBorrow.setPatronID(patronID);
					resourceBorrow.setBorrowDate(resultSet.getDate(BORROW_DATE));
					resourceBorrow.setRenderDate(resultSet.getDate(RENDER_DATE));
					list.add(resourceBorrow);
				}
				
				resultSet.close();
				statement.close();
				connection.close();
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public List<ResourceBorrow> getAllByResource(String resourceID) {
		// TODO Auto-generated method stub
		List<ResourceBorrow> list = null ;
		String sql = "SELECT * FROM "+ TABLE_NAME + 
		" WHERE " + RESOURCE_ID + "= ?";
		Connection connection = SqlExecute.getConnection();
		if(connection == null){
			System.out.println("Loi ket noi CSDL trong ham getResourceBorrow");
			return null ;
		}
		try{
			PreparedStatement statement = connection.prepareStatement(sql);
			if(statement != null){
				
				statement.setString(1, resourceID);
				
				ResultSet resultSet = statement.executeQuery();
				if(resultSet == null){
					return null ;
				}
				list = new ArrayList<ResourceBorrow>();
				while(resultSet.next()){
					
					ResourceBorrow resourceBorrow = new ResourceBorrow();
					resourceBorrow.setResourceID(resourceID);
					resourceBorrow.setPatronID(resultSet.getString(PATRON_ID));
					resourceBorrow.setBorrowDate(resultSet.getDate(BORROW_DATE));
					resourceBorrow.setRenderDate(resultSet.getDate(RENDER_DATE));
					list.add(resourceBorrow);
				}
				
				resultSet.close();
				statement.close();
				connection.close();
			}
		}catch(SQLException e){
			e.printStackTrace();
			
		}
		return list;
	}

	@Override
	public List<ResourceBorrow> gets() {
		// TODO Auto-generated method stub
		List<ResourceBorrow> list = null ;
		String sql = "SELECT * FROM "+ TABLE_NAME ;
		ResultSet resultSet = SqlExecute.executeCommand(sql);
		if(resultSet == null){
			System.out.println("Truy van CSDL bi loi o ham gets tat ca ResourceBorrow");
			return null; 
		}
		list = new ArrayList<ResourceBorrow>();
		try{
			while(resultSet.next()){
				ResourceBorrow resourceBorrow = new ResourceBorrow();
				
				resourceBorrow.setResourceID(resultSet.getString(RESOURCE_ID));
				resourceBorrow.setPatronID(resultSet.getString(PATRON_ID));
				resourceBorrow.setBorrowDate(resultSet.getDate(BORROW_DATE));
				resourceBorrow.setRenderDate(resultSet.getDate(RENDER_DATE));
				list.add(resourceBorrow);
				
			}
			resultSet.close();
		}catch(SQLException e){
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public boolean remove(String resourceID, String patronID) {
		
		// TODO Auto-generated method stub
		String sql = "DELETE FROM "+ TABLE_NAME + 
		" WHERE " + RESOURCE_ID +"= ? AND " + PATRON_ID + "= ?";
		Connection connection = SqlExecute.getConnection();
		if(connection == null){
			System.out.println("Loi ket noi CSDL trong ham getResourceBorrow");
			return false ;
		}
		try{
			PreparedStatement statement = connection.prepareStatement(sql);
			if(statement != null){
				
				statement.setString(1, resourceID);
				statement.setString(2, patronID);
				
				statement.executeUpdate();
				
				statement.close();
				connection.close();
				
			}
		}catch(SQLException e){
			e.printStackTrace();
			return false;
		}
		return true ;
	}

	@Override
	public boolean update(ResourceBorrow resourceBorrow) {
		// TODO Auto-generated method stub
		String sql = "UPDATE  "+ TABLE_NAME + 
		" SET " + BORROW_DATE +"= ? , " + RENDER_DATE + "= ? " + 
		" WHERE " + RESOURCE_ID +"= ? AND " + PATRON_ID + "= ?";
		Connection connection = SqlExecute.getConnection();
		if(connection == null){
			System.out.println("Loi ket noi CSDL trong ham getResourceBorrow");
			return false ;
		}
		try{
			PreparedStatement statement = connection.prepareStatement(sql);
			if(statement != null){
				
				statement.setDate(1, new java.sql.Date(resourceBorrow.getBorrowDate().getTime()));
				statement.setDate(2, new java.sql.Date(resourceBorrow.getRenderDate().getTime()));
				statement.setString(3, resourceBorrow.getResourceID());
				statement.setString(4, resourceBorrow.getPatronID());
				
				statement.executeUpdate();
				
				statement.close();
				connection.close();
				
			}
		}catch(SQLException e){
			e.printStackTrace();
			return false;
		}
		return true;
	}
}