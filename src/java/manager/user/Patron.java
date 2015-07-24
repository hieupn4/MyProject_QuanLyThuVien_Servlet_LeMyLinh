package manager.user;

import java.util.Date;
//import manager.resource.Resource;

/**
 * class : Patron
 * file  : manager/user/Patron.java
 * description : class Patron  mieu ta mot doi tuong ban doc 
 * 				truu tuong. Luu tru cac thong tin chung nhat 
 * 				cua tat ca cac ban doc.
 * author : nttuyen
 * Date   : 15/05/2008
 * version : 1.0
 **/
public abstract class Patron {
	public static final String STUDENT_TYPE = "PATRON_STUDENT";
	public static final String FACULTY_TYPE = "PATRON_FACULTY";
	public static final String LIB_MANAGER_TYPE = "PATRON_LIB_MANAGER";
	
  //Ten dang nhap cua ban doc
  //Dung khi dat sach la nhieu
  protected String userName;

  //password de dang nhap
  protected String password;

  //Email cua ban doc
  protected String email;

  //ngay sinh cua ban doc
  protected Date birthDay;

  //Dia chi hien tai cua ban doc
  protected String address;

  //Ho va ten cua ban doc
  protected String fullName;

  /**
   * 
   * Khong hieu cai cho nay cho lam
   * @return
   */
    //public Resource ResourceBorrow;
    //public Resource ResourceRequest;

  /**
   * Ham khoi tao cho doi tuong Patron
   */
  public Patron(){
	  this.userName = "";
	  this.password = "";
	  this.email = "";
	  this.birthDay = new Date();
	  this.address = "";
	  this.fullName = "N/A";
  }
  //userName property
  public String getUserName() {
	  return this.userName;
  }
  public void setUserName(String userName) {
	  this.userName = userName;
  }

  //password property
  public String getPassword() {
	  return this.password;
  }

  public void setPassword(String password) {
	  this.password = password;
  }

  public String getEmail() {
	  return this.email;
  }

  public void setEmail(String email) {
	  this.email = email;
  }

  public Date getBirthDay() {
	  return this.birthDay;
  }

  public void setBirthDay(Date birthDay) {
	  this.birthDay = birthDay;
  }

  public String getAddress() {
	  return this.address;
  }

  public void setAddress(String address) {
	  this.address = address;
  }

  public String getFullName() {
	  return this.fullName;
  }

  public void setFullName(String fullName) {
	  this.fullName = fullName;
  }

  abstract public String getType();
}