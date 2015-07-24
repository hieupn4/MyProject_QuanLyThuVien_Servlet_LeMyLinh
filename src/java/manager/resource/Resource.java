package manager.resource;

//import manager.user.Patron;

/*
 */
public abstract class Resource {
	
	public static final String BOOK_TYPE = "RESOURCE_BOOK";
	public static final String  CD_TYPE = "RESOURCE_CD";

  protected String id;

  protected String name;

  protected String description;

  protected int amount;

  protected String domain;
  
  protected String authors ;
  
  public Resource(){
	  id = "";
	  name = "";
	  description = "";
	  amount = 0 ;
	  domain = "";
	  authors = "";
  }
  
  public String getID(){
	  return this.id;
  }
  public void setID(String id){
	  this.id = id ;
  }
  public String getName(){
	  return this.name;
  }
  public void setName(String name){
	  this.name = name ;
  }
  public String getDescription(){
	  return this.description;
  }
  public void setDescription(String description){
	  this.description = description;
  }
  public int getAmount(){
	  return this.amount;
  }
  public void setAmount(int amount){
	  this.amount = amount ;
  }
  public String getDomain(){
	  return this.domain;
  }
  public void setDomain(String domain){
	  this.domain = domain ;
  }
  public String getAuthors(){
	  return this.authors;
  }
  public void setAuthors(String authors){
	  this.authors = authors;
  }

    //public Patron ResourceBorrow;
    //public Patron ResourceRequest;

  abstract public String getType();
}