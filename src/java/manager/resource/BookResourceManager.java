package manager.resource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import sql.SqlExecute;

public class BookResourceManager extends AbstractResourceManager {
	//Thuoc tinhs luu tru ten bang va ten cac cot luu tru sach
	private final String BOOK_TABLE_NAME="book";
	private final String BOOK_PUBLISHER="publisher";
	private final String BOOK_PUBLISH_YEAR="publish_year";
	
	public BookResourceManager(){
		
	}
	
	@Override
	public boolean add(Resource resource) {
		// TODO Auto-generated method stub
		if(!(resource instanceof BookResource)){
			System.out.println("Ban insert khong dung kieu");
			return false ;
		}
		boolean insertOK = super.insert(resource);
		if(!insertOK){
			System.out.println("Insert vao bang Resource bi loi");
			return false ;
		}
		String sql = "INSERT INTO "+ BOOK_TABLE_NAME +
					"(" + RESOURCE_ID +","+ BOOK_PUBLISHER +","+ BOOK_PUBLISH_YEAR + ")" +
					"VALUES(?,?,?)";
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
			BookResource book = (BookResource)resource ;
			
			preparedStatement.setString(1, book.getID());
			preparedStatement.setString(2, book.getPublisher());
			preparedStatement.setInt(3, book.getPulishYear());
			
			
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
		return true;
	}


	@Override
	public Resource get(String resourceID) {
		// TODO Auto-generated method stub
		BookResource resource = null ;
		String sql = "SELECT * FROM " + RESOURCE_TABLE_NAME + " , " + BOOK_TABLE_NAME +
						" WHERE " + RESOURCE_TABLE_NAME + "." + RESOURCE_ID + "='"+resourceID + "'"+
								" AND " + BOOK_TABLE_NAME+"." + RESOURCE_ID + "='"+resourceID+"';";
		ResultSet resultSet = SqlExecute.executeCommand(sql);
		if(resultSet == null){
			return null ;
		}
		if(resultSet == null){
			return null ;
		}
		try{
			resource = new BookResource();
			while(resultSet.next()){
				resource.setID(resourceID);
				resource.setName(resultSet.getString(RESOURCE_NAME));
				resource.setAuthors(resultSet.getString(RESOURCE_AUTHORS));
				resource.setDomain(resultSet.getString(RESOURCE_DOMAIN));
				resource.setAmount(resultSet.getInt(RESOURCE_AMOUNT));
				resource.setDescription(resultSet.getString(RESOURCE_DESCRIPTION));
		
				resource.setPublisher(resultSet.getString(BOOK_PUBLISHER));
				resource.setPublishYear(resultSet.getInt(BOOK_PUBLISH_YEAR));
			}
			resultSet.close();
		}catch(SQLException e){
			e.printStackTrace();
		}
		return resource;
	}

	@Override
	public List<Resource> gets() {
		// TODO Auto-generated method stub
		List<Resource> list = null ;
		String sql = "SELECT * FROM " + RESOURCE_TABLE_NAME + " , " + BOOK_TABLE_NAME +
						" WHERE " + RESOURCE_TABLE_NAME + "." + RESOURCE_ID + "="+BOOK_TABLE_NAME+"." + RESOURCE_ID;
		ResultSet resultSet = SqlExecute.executeCommand(sql);
		if(resultSet == null){
			return null ;
		}
		list = new ArrayList<Resource>();
		try{
			
			while(resultSet.next()){
				BookResource resource = new BookResource();
				resource.setID(resultSet.getString(RESOURCE_ID));
				resource.setName(resultSet.getString(RESOURCE_NAME));
				resource.setAuthors(resultSet.getString(RESOURCE_AUTHORS));
				resource.setDomain(resultSet.getString(RESOURCE_DOMAIN));
				resource.setAmount(resultSet.getInt(RESOURCE_AMOUNT));
				resource.setDescription(resultSet.getString(RESOURCE_DESCRIPTION));
		
				resource.setPublisher(resultSet.getString(BOOK_PUBLISHER));
				resource.setPublishYear(resultSet.getInt(BOOK_PUBLISH_YEAR));
				
				list.add(resource);
			}
			resultSet.close();
		}catch(SQLException e){
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public boolean remove(String resourceID) {
		// TODO Auto-generated method stub
		
		//String sql = "DELETE FROM "+BOOK_TABLE_NAME + 
		//		" WHERE " + RESOURCE_ID + " = '"+resourceID + "'"; 
		return super.delete(resourceID);
	}

	@Override
	public List<Resource> search(String command) {
		// TODO Auto-generated method stub
		List<Resource> list = null ;
		
		String sql = "SELECT * FROM " + RESOURCE_TABLE_NAME + " , " + BOOK_TABLE_NAME +
						" WHERE (" + RESOURCE_TABLE_NAME + "." + RESOURCE_ID + "="+BOOK_TABLE_NAME+"." + RESOURCE_ID +
						") AND (" + RESOURCE_TABLE_NAME + "." + RESOURCE_ID + " LIKE '%"+command+"%'" +
						" OR " + RESOURCE_TABLE_NAME + "." + RESOURCE_NAME + " LIKE '%"+command+"%'" +
						" OR " + RESOURCE_TABLE_NAME + "." + RESOURCE_AUTHORS + " LIKE '%"+command+"%' )" +
						" OR " + RESOURCE_TABLE_NAME + "." + RESOURCE_DESCRIPTION + " LIKE '%"+command+"%'" ;
		
		ResultSet resultSet = SqlExecute.executeCommand(sql);
		if(resultSet == null){
			return null ;
		}
		list = new ArrayList<Resource>();
		try{
			
			while(resultSet.next()){
				BookResource resource = new BookResource();
				resource.setID(resultSet.getString(RESOURCE_ID));
				resource.setName(resultSet.getString(RESOURCE_NAME));
				resource.setAuthors(resultSet.getString(RESOURCE_AUTHORS));
				resource.setDomain(resultSet.getString(RESOURCE_DOMAIN));
				resource.setAmount(resultSet.getInt(RESOURCE_AMOUNT));
				resource.setDescription(resultSet.getString(RESOURCE_DESCRIPTION));
		
				resource.setPublisher(resultSet.getString(BOOK_PUBLISHER));
				resource.setPublishYear(resultSet.getInt(BOOK_PUBLISH_YEAR));
				
				list.add(resource);
			}
			resultSet.close();
		}catch(SQLException e){
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public boolean update(Resource resource) {
		// TODO Auto-generated method stub
		if(!(resource instanceof BookResource)){
			System.out.println("Khong dung roi");
			return false ;
		}
		/*
		String sql = "UPDATE " + RESOURCE_TABLE_NAME + " SET " +
						RESOURCE_NAME + "='"+resource.getName()+"' , " +
						RESOURCE_DESCRIPTION + "='"+resource.getDescription()+"' , "+
						RESOURCE_AMOUNT + "="+resource.getAmount() + " ,  " +
						RESOURCE_DOMAIN + "='"+resource.getDomain()+"' " +
						" wHERE "+ RESOURCE_ID + "='"+resource.getID()+"';";
		*/
		BookResource book = (BookResource)resource ;
		
		String sql1 = "UPDATE " + BOOK_TABLE_NAME + " SET " +
		BOOK_PUBLISHER + "='"+book.getPublisher()+"' , " +
		BOOK_PUBLISH_YEAR + "="+book.getPulishYear() + "   " +
		" WHERE "+ RESOURCE_ID + "='"+book.getID()+"';";
		
		
		return super.update(resource) && SqlExecute.executeUpdate(sql1);
	}}