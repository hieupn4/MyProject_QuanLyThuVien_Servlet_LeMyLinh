package manager.resource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import sql.SqlExecute;

public class CDResourceManager extends AbstractResourceManager {
	
	private final String CD_TABLE_NAME="cd";
	private final String CD_SIZE="size";
	private final String CD_TIME="cd_time";
	private final String CD_KIND="kind";

	@Override
	public boolean add(Resource resource) {
		// TODO Auto-generated method stub
		if(!(resource instanceof CDResource)){
			System.out.println("Khong dung kieu du lieu CDResource");
			return false ;
		}
		boolean insertOK = super.insert(resource);
		if(!insertOK){
			System.out.println("Insert vao bang Resource bi loi");
			return false ;
		}
		String sql = "INSERT INTO "+ CD_TABLE_NAME +
					"(" + RESOURCE_ID +","+ CD_SIZE +","+ CD_TIME +","+ CD_KIND + ")" +
					"VALUES(?,?,?,?)";
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
			CDResource cd = (CDResource)resource ;
			
			preparedStatement.setString(1, cd.getID());
			preparedStatement.setInt(2, cd.getSize());
			preparedStatement.setInt(3, cd.getTime());
			preparedStatement.setString(4, cd.getKind());
			
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
		CDResource resource = null ;
		String sql = "SELECT * FROM " + RESOURCE_TABLE_NAME + " , " + CD_TABLE_NAME +
						" WHERE " + RESOURCE_TABLE_NAME + "." + RESOURCE_ID + "='"+resourceID + "'"+
								" AND " + CD_TABLE_NAME+"." + RESOURCE_ID + "='"+resourceID+"';";
		ResultSet resultSet = SqlExecute.executeCommand(sql);
		if(resultSet == null){
			return null ;
		}
		try{
			resource = new CDResource();
			while(resultSet.next()){
				resource.setID(resourceID);
				resource.setName(resultSet.getString(RESOURCE_NAME));
				resource.setAuthors(resultSet.getString(RESOURCE_AUTHORS));
				resource.setDomain(resultSet.getString(RESOURCE_DOMAIN));
				resource.setAmount(resultSet.getInt(RESOURCE_AMOUNT));
				resource.setDescription(resultSet.getString(RESOURCE_DESCRIPTION));
				
				resource.setSize(resultSet.getInt(CD_SIZE));
				resource.setTime(resultSet.getInt(CD_TIME));
				resource.setKind(resultSet.getString(CD_KIND));
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
		String sql = "SELECT * FROM " + RESOURCE_TABLE_NAME + " , " + CD_TABLE_NAME +
						" WHERE " + RESOURCE_TABLE_NAME + "." + RESOURCE_ID + "="+CD_TABLE_NAME+"." + RESOURCE_ID;
		ResultSet resultSet = SqlExecute.executeCommand(sql);
		if(resultSet == null){
			return null ;
		}
		list = new ArrayList<Resource>();
		try{
			CDResource resource = new CDResource();
			while(resultSet.next()){
				resource.setID(resultSet.getString(RESOURCE_ID));
				resource.setName(resultSet.getString(RESOURCE_NAME));
				resource.setAuthors(resultSet.getString(RESOURCE_AUTHORS));
				resource.setDomain(resultSet.getString(RESOURCE_DOMAIN));
				resource.setAmount(resultSet.getInt(RESOURCE_AMOUNT));
				resource.setDescription(resultSet.getString(RESOURCE_DESCRIPTION));
				
				resource.setSize(resultSet.getInt(CD_SIZE));
				resource.setTime(resultSet.getInt(CD_TIME));
				resource.setKind(resultSet.getString(CD_KIND));
				
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
		super.delete(resourceID);
		String sql = "DELETE FROM "+CD_TABLE_NAME + 
				" WHERE " + RESOURCE_ID + " = '"+resourceID + "'"; 
		return SqlExecute.executeUpdate(sql);
	}

	@Override
	public List<Resource> search(String command) {
		// TODO Auto-generated method stub
		List<Resource> list = null ;
		
		String sql = "SELECT * FROM " + RESOURCE_TABLE_NAME + " , " + CD_TABLE_NAME +
						" WHERE (" + RESOURCE_TABLE_NAME + "." + RESOURCE_ID + "="+CD_TABLE_NAME+"." + RESOURCE_ID +
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
				CDResource resource = new CDResource();
				resource.setID(resultSet.getString(RESOURCE_ID));
				resource.setName(resultSet.getString(RESOURCE_NAME));
				resource.setAuthors(resultSet.getString(RESOURCE_AUTHORS));
				resource.setDomain(resultSet.getString(RESOURCE_DOMAIN));
				resource.setAmount(resultSet.getInt(RESOURCE_AMOUNT));
				resource.setDescription(resultSet.getString(RESOURCE_DESCRIPTION));
				
				resource.setSize(resultSet.getInt(CD_SIZE));
				resource.setTime(resultSet.getInt(CD_TIME));
				resource.setKind(resultSet.getString(CD_KIND));
				
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
		if(!(resource instanceof CDResource)){
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
		CDResource cd = (CDResource)resource ;
		
		String sql1 = "UPDATE " + CD_TABLE_NAME + " SET " +
			CD_SIZE + "="+cd.getSize()+" , " +
			CD_TIME + "="+cd.getTime() + " ,  " +
			CD_KIND + "='"+cd.getKind()+"' " +
			" WHERE "+ RESOURCE_ID + "='"+cd.getID()+"';";
		return super.update(resource) && SqlExecute.executeUpdate(sql1);
	}
	
}