package manager.resource;

/*
 */
public class CDResource extends Resource {

  private int size;

  private int time;

  private String kind;
  
  public CDResource(){
	  super();
	  size = 0;
	  time = 0 ;
	  kind = "";
  }
  
  public int getSize(){
	  return this.size;
  }
  public void setSize(int size){
	  this.size = size ;
  }
  public int getTime(){
	  return this.time;
  }
  public void setTime(int time){
	  this.time = time ;
  }
  public String getKind(){
	  return this.kind;
  }
  public void setKind(String kind){
	  this.kind = kind;
  }

	@Override
	public String getType() {
		// TODO Auto-generated method stub
		return Resource.CD_TYPE;
	}
}