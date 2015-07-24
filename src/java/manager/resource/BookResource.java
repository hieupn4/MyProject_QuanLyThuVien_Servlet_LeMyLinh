package manager.resource;

/*
 */
public class BookResource extends Resource {

  private String publisher;

  private int publishYear;

  //private String authors;
  
  public BookResource(){
	  super();
	  publisher = "";
	  publishYear = 0;
	  authors = "";
  }
  
  public String getPublisher(){
	  return this.publisher;
  }
  public void setPublisher(String publisher){
	  this.publisher = publisher;
  }
  public int getPulishYear(){
	  return this.publishYear;
  }
  public void setPublishYear(int publishYear){
	  this.publishYear = publishYear;
  }

	@Override
	public String getType() {
		// TODO Auto-generated method stub
		return Resource.BOOK_TYPE;
	}
}