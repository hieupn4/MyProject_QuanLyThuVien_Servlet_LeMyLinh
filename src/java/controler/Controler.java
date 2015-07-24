package controler;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import manager.ResourceBorrow;
import manager.ResourceBorrowManager;
import manager.ResourceBorrowManagerImpl;
import manager.ResourceRequest;
import manager.ResourceRequestManager;
import manager.ResourceRequestManagetImpl;
import manager.resource.BookResource;
import manager.resource.BookResourceManager;
import manager.resource.CDResource;
import manager.resource.CDResourceManager;
import manager.resource.Resource;
import manager.resource.ResourceManager;
import manager.user.Faculty;
import manager.user.FacultyManager;
import manager.user.LibManager;
import manager.user.LibStaffManager;
import manager.user.Patron;
import manager.user.Student;
import manager.user.StudentManager;
import manager.user.UserManager;

/**
 * Servlet implementation class for Servlet: Controler
 * 
 */
public class Controler extends javax.servlet.http.HttpServlet implements
		javax.servlet.Servlet {
	static final long serialVersionUID = 1L;
	private String logedIn = null;
	private String roles = null;
	private String jsp = "index.jsp";
	private String action = null;

	/*
	 * (non-Java-doc)
	 * 
	 * @see javax.servlet.http.HttpServlet#HttpServlet()
	 */
	public Controler() {
		super();
	}

	/*
	 * (non-Java-doc)
	 * 
	 * @see javax.servlet.http.HttpServlet#doGet(HttpServletRequest request,
	 *      HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/*
	 * (non-Java-doc)
	 * 
	 * @see javax.servlet.http.HttpServlet#doPost(HttpServletRequest request,
	 *      HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		action = request.getParameter("action");
		if (action == null) {
			action = "DEFAULT";
		}
		if ("DEFAULT".equals(action)) {

		} else if ("LOGIN".equals(action)) {

			this.login(request, response);

		} else if ("LOGOUT".equals(action)) {
			this.logout(request, response);

		} else if ("VIEW_RESOURCE".equals(action)) {
			viewResource(request, response);

		} else if ("ADD_BOOK".equals(action)) {
			addBook(request, response);

		} else if ("ADD_CD".equals(action)) {
			addCD(request, response);

		} else if ("SEARCH_RESOURCE".equals(action)) {
			searchResource(request, response);

		} else if ("DELETE_RESOURCE".equals(action)) {
			deleteResource(request, response);

		} else if ("UPDATE_BOOK".equals(action)) {
			updateBook(request, response);

		} else if ("UPDATE_CD".equals(action)) {
			updateCD(request, response);

		} else if ("ADD_PATRON".equals(action)) {
			addPatron(request, response);

		} else if ("UPDATE_PATRON".equals(action)) {
			updatePatron(request, response);

		} else if ("DELETE_PATRON".equals(action)) {
			deletePatron(request, response);

		} else if ("VIEW_PATRON".equals(action)) {
			viewPatron(request, response);

		} else if ("REQUEST".equals(action)) {
			request(request, response);
		} else if ("CHECK_OUT".equals(action)) {
			checkOut(request, response);
		} else if ("CHECK_IN".equals(action)) {
			checkIn(request, response);
		}
	}

	public void checkIn(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String userName = request.getParameter("checkIn.userName");
		String resourceID = request.getParameter("checkIn.resourceID");
		String checkInType = request.getParameter("checkIn_type");
		
		System.out.println("check IN : " + userName);
		System.out.println("Check in : " + resourceID );
		
		ResourceBorrowManager resourceBorrowManager = new ResourceBorrowManagerImpl();
		ResourceBorrow resourceBorrow = resourceBorrowManager.get(resourceID, userName);
		if(resourceBorrow == null){
			jsp = "checkIn.jsp";
			String messageErr = "Không tồn tại bạn đọc mượn tài nguyên với thông tin như trên";
			request.setAttribute("messageErr", messageErr);
			dispatch(jsp, request, response);
		}else if("LOAD".equals(checkInType)){
			request.setAttribute("resourceBorrow", resourceBorrow);
			jsp = "loadCheckIn.jsp";
		}else if("DELETE".equals(checkInType)){
			resourceBorrowManager.remove(resourceBorrow.getResourceID(), resourceBorrow.getPatronID());
			jsp = "index.jsp";
		}
		dispatch(jsp, request, response);
	}

	public void checkOut(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String userName = request.getParameter("checkOut.userName");
		String resourceID = request.getParameter("checkOut.resourceID");
		
		ResourceBorrowManager resourceBorrowManager = new ResourceBorrowManagerImpl();
		//ResourceRequestManager resourceRequestManager = new ResourceRequestManagetImpl();
		
		ResourceBorrow resourceBorrow = resourceBorrowManager.get(resourceID, userName);
		//ResourceRequest resourceRequest = resourceRequestManager.get(resourceID, userName);
		
		if(resourceBorrow != null ){
			String messageErr = "Bạn đã mượn sách này rồi";
			jsp = "request.jsp";
			request.setAttribute("messageErr", messageErr);
			dispatch(jsp, request, response);
		}
		
		
		UserManager userManager = new StudentManager();
		ResourceManager resourceManager = new BookResourceManager();
		resourceBorrowManager = new ResourceBorrowManagerImpl();

		String patronType = userManager.checkUserName(userName);
		String resourceType = resourceManager.check(resourceID);

		if (resourceType == null || patronType == null) {
			String messageErr = "Không có bạn đọc hoặc tài nguyên với thông tin đã đưa";
			request.setAttribute("messageErr", messageErr);
			jsp = "checkOut.jsp";
			dispatch(jsp, request, response);
		}

		resourceBorrow = new ResourceBorrow();
		Calendar calendar = Calendar.getInstance();

		java.util.Date borrowDate = calendar.getTime();
		resourceBorrow.setBorrowDate(borrowDate);

		// Don vi tinh va thoi gian duoc phep muon cua ban doc
		int amount = 0;
		int unitTime = -1;
		if (Resource.CD_TYPE.equals(resourceType)) {
			// Kiem tra so luong tai nguyen va so luong da muon
			resourceManager = new CDResourceManager();
			Resource resource = resourceManager.get(resourceID);
			int number = resource.getAmount();
			List<ResourceBorrow> list = resourceBorrowManager
					.getAllByResource(resourceID);
			int numberBorrow = list.size();
			if (number <= numberBorrow ) {
				jsp = "checkOut.jsp";
				String messageErr = "CD này hiện không còn trong thư viện";
				request.setAttribute("messageErr", messageErr);
				dispatch(jsp, request, response);
			}

			// Neu la tai nguyen CD thi chi duoc muon 1 tuan -- 7 ngay
			amount = 7;
			unitTime = Calendar.DATE;
		} else if (Resource.BOOK_TYPE.equals(resourceType)) {

			// Kiem tra xem sach co con trong thu vien hay da duoc muon roi
			resourceManager = new BookResourceManager();
			Resource resource = resourceManager.get(resourceID);
			int number = resource.getAmount();
			List<ResourceBorrow> list = resourceBorrowManager
					.getAllByResource(resourceID);
			if(list == null){
				list = new ArrayList<ResourceBorrow>();
			}
			int numberBorrow = list.size();
			if (number <= numberBorrow) {
				jsp = "checkOut.jsp";
				String messageErr = "Sách này hiện không còn trong thư viện";
				request.setAttribute("messageErr", messageErr);
				dispatch(jsp, request, response);
			}

			if (Patron.LIB_MANAGER_TYPE.equals(patronType)) {
				// Nhan vien thu vien duoc muon sach trong 1 nam
				unitTime = Calendar.YEAR;
				amount = 1;
			} else if (Patron.STUDENT_TYPE.equals(patronType)) {
				// Sinh vien duoc muon sach trong 4 tuan
				unitTime = Calendar.DATE;
				amount = 28;
			} else if (Patron.FACULTY_TYPE.equals(patronType)) {
				// Giang vien duoc muon sach trong 3 thang
				unitTime = Calendar.MONTH;
				amount = 3;
			}
		}

		// Tinh ra ngay fai tra cua ban doc
		calendar.add(unitTime, amount);
		java.util.Date renderDate = calendar.getTime();
		resourceBorrow.setRenderDate(renderDate);
		// Set cac thuoc tinh con lai cua ResourceBorrow
		resourceBorrow.setResourceID(resourceID);
		resourceBorrow.setPatronID(userName);

		// Luu vao CSDL

		resourceBorrowManager.add(resourceBorrow);
		jsp = "index.jsp";
		dispatch(jsp, request, response);

	}

	public void request(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String userName = request.getParameter("request.userName");
		String resourceID = request.getParameter("request.resourceID");
		
		//Check xem da dat sach nay hay muon sach nay chua
		ResourceBorrowManager resourceBorrowManager = new ResourceBorrowManagerImpl();
		ResourceRequestManager resourceRequestManager = new ResourceRequestManagetImpl();
		
		ResourceBorrow resourceBorrow = resourceBorrowManager.get(resourceID, userName);
		ResourceRequest resourceRequest = resourceRequestManager.get(resourceID, userName);
		
		if(resourceBorrow != null || resourceRequest != null){
			String messageErr = "Bạn đã đặt hoặc mượn sách này rồi";
			jsp = "request.jsp";
			request.setAttribute("messageErr", messageErr);
			dispatch(jsp, request, response);
		}
		
		UserManager userManager = new StudentManager();
		String roles = userManager.checkUserName(userName);
		if (roles == null) {
			jsp = "login.jsp";
			dispatch(jsp, request, response);
		}
		ResourceManager resourceManager = new BookResourceManager();
		String resourceType = resourceManager.check(resourceID);
		String date = request.getParameter("request.date");
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		java.util.Date borrowDate;
		try {
			borrowDate = df.parse(date);
		} catch (Exception e) {
			borrowDate = new java.util.Date();
		}
		if (resourceType == null) {
			String messageErr = "Không tồn tại mã tài nguyên như vậy";
			jsp = "request.jsp";
			request.setAttribute("messageErr", messageErr);
			dispatch(jsp, request, response);
		}
		Resource resource = null;
		if (resourceType.equals(Resource.BOOK_TYPE)) {
			resourceManager = new BookResourceManager();
		} else if (resourceType.equals(Resource.CD_TYPE)) {
			resourceManager = new CDResourceManager();
		}
		resourceBorrowManager = new ResourceBorrowManagerImpl();
		resource = resourceManager.get(resourceID);
		int resourceAmount = resource.getAmount();
		int resourceBorrowed = resourceBorrowManager.getAllByResource(resourceID)
				.size();
		
		if (resourceBorrowed < resourceAmount) {
			String messageErr = "Tài nguyên này hiện đang còn trong thư viện, bạn hãy đến mượn";
			jsp = "request.jsp";
			request.setAttribute("messageErr", messageErr);
			dispatch(jsp, request, response);
		}

		resourceRequestManager = new ResourceRequestManagetImpl();
		resourceRequest = new ResourceRequest();
		resourceRequest.setResourceID(resourceID);
		resourceRequest.setPatronID(userName);
		resourceRequest.setBorrowDate(borrowDate);
		resourceRequestManager.add(resourceRequest);
		
		jsp = "index.jsp";
		dispatch(jsp, request, response);
	}

	public void viewResource(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String resourceID = request.getParameter("id");
		HttpSession session = request.getSession();
		String roles = (String) session.getAttribute("patron.roles");
		ResourceManager resourceManager = new BookResourceManager();
		String type = resourceManager.check(resourceID);
		if (type == null) {
			request.setAttribute("messageErr",
					"Mã tài nguyên này không tồn tại");
			jsp = "index.jsp";
			dispatch(jsp, request, response);
		}
		Resource resource = null;
		if (Resource.BOOK_TYPE.equals(type)) {
			jsp = "viewBook.jsp";
			resourceManager = new BookResourceManager();
			resource = resourceManager.get(resourceID);
			if (Patron.LIB_MANAGER_TYPE.equals(roles)) {
				jsp = "editBook.jsp";
			}
			request.setAttribute("book_edit", resource);
		} else if (Resource.CD_TYPE.equals(type)) {
			resourceManager = new CDResourceManager();
			resource = resourceManager.get(resourceID);
			jsp = "viewCD.jsp";
			if (Patron.LIB_MANAGER_TYPE.equals(roles)) {
				jsp = "editCD.jsp";
			}
			request.setAttribute("cd_edit", resource);
		}
		dispatch(jsp, request, response);
	}

	public void searchResource(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String searchKey = request.getParameter("searchKey");
		String searchType = request.getParameter("search.type");
		ResourceManager resourceManager = null;
		if (Resource.CD_TYPE.equals(searchType)) {
			resourceManager = new CDResourceManager();
		} else {
			resourceManager = new BookResourceManager();
		}
		List<Resource> list = resourceManager.search(searchKey);
		HttpSession session = request.getSession();
		session.setAttribute("search_result", list);
		jsp = "index.jsp";
		dispatch(jsp, request, response);
	}

	public void deleteResource(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String resourceID = request.getParameter("delete.resourceID");
		ResourceManager resourceManager = new BookResourceManager();
		String type = resourceManager.check(resourceID);

		if (type == null) {
			jsp = "deleteResource.jsp";
			String messageErr = "Ma dang tai nguyen nay khong co";
			request.setAttribute("messageErr", messageErr);
			dispatch(jsp, request, response);
		}

		if (Resource.BOOK_TYPE.equals(type)) {
			resourceManager = new BookResourceManager();
		} else if (Resource.CD_TYPE.equals(type)) {
			resourceManager = new CDResourceManager();
		}
		resourceManager.remove(resourceID);
		jsp = "index.jsp";
		dispatch(jsp, request, response);
	}

	public void addCD(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String id = request.getParameter("cd.id");
		String name = request.getParameter("cd.name");
		String domain = request.getParameter("cd.domain");
		String authors = request.getParameter("cd.authors");
		int size = 0;
		try {
			size = Integer.parseInt(request.getParameter("cd.size"));
		} catch (Exception e) {
			size = 0;
		}

		int time = 0;
		try {
			time = Integer.parseInt(request.getParameter("cd.time"));
		} catch (Exception e) {
			time = 0;
		}
		String kind = request.getParameter("cd.kind");
		int amount = 0;
		try {
			amount = Integer.parseInt(request.getParameter("cd.amount"));
		} catch (Exception e) {
			amount = 0;
		}

		String description = request.getParameter("cd.description");

		ResourceManager resourceManager = new CDResourceManager();
		CDResource cd = new CDResource();
		cd.setID(id);
		cd.setName(name);
		cd.setDomain(domain);
		cd.setAuthors(authors);
		cd.setSize(size);
		cd.setTime(time);
		cd.setKind(kind);
		cd.setAmount(amount);
		cd.setDescription(description);
		resourceManager.add(cd);
		jsp = "index.jsp";
		dispatch(jsp, request, response);
	}

	public void updateCD(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("cd.id");
		String name = request.getParameter("cd.name");
		String domain = request.getParameter("cd.domain");
		String authors = request.getParameter("cd.authors");
		int size = 0;
		try {
			size = Integer.parseInt(request.getParameter("cd.size"));
		} catch (Exception e) {
			size = 0;
		}

		int time = 0;
		try {
			time = Integer.parseInt(request.getParameter("cd.time"));
		} catch (Exception e) {
			time = 0;
		}
		String kind = request.getParameter("cd.kind");
		int amount = 0;
		try {
			amount = Integer.parseInt(request.getParameter("cd.amount"));
		} catch (Exception e) {
			amount = 0;
		}

		String description = request.getParameter("cd.description");

		ResourceManager resourceManager = new CDResourceManager();
		CDResource cd = new CDResource();
		cd.setID(id);
		cd.setName(name);
		cd.setDomain(domain);
		cd.setAuthors(authors);
		cd.setSize(size);
		cd.setTime(time);
		cd.setKind(kind);
		cd.setAmount(amount);
		cd.setDescription(description);
		resourceManager.update(cd);
		jsp = "index.jsp";
		dispatch(jsp, request, response);
	}

	public void addBook(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String id = request.getParameter("book.id");
		String name = request.getParameter("book.name");
		String domain = request.getParameter("book.domain");
		String authors = request.getParameter("book.authors");
		String publisher = request.getParameter("book.publisher");
		int publishYear = 0;
		try {
			publishYear = Integer.parseInt(request
					.getParameter("book.publishYear"));
		} catch (Exception e) {
			publishYear = 0;
		}
		int amount = 0;
		try {
			amount = Integer.parseInt(request.getParameter("book.amount"));
		} catch (Exception e) {
			amount = 0;
		}

		String description = request.getParameter("book.description");

		ResourceManager resourceManager = new BookResourceManager();
		BookResource book = new BookResource();
		book.setID(id);
		book.setName(name);
		book.setDomain(domain);
		book.setAuthors(authors);
		book.setPublisher(publisher);
		book.setPublishYear(publishYear);
		book.setAmount(amount);
		book.setDescription(description);
		resourceManager.add(book);

		jsp = "index.jsp";
		dispatch(jsp, request, response);
	}

	public void updateBook(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("book.id");
		String name = request.getParameter("book.name");
		String domain = request.getParameter("book.domain");
		String authors = request.getParameter("book.authors");
		String publisher = request.getParameter("book.publisher");
		int publishYear = 0;
		try {
			publishYear = Integer.parseInt(request
					.getParameter("book.publishYear"));
		} catch (Exception e) {
			publishYear = 0;
		}
		int amount = 0;
		try {
			amount = Integer.parseInt(request.getParameter("book.amount"));
		} catch (Exception e) {
			amount = 0;
		}

		String description = request.getParameter("book.description");

		ResourceManager resourceManager = new BookResourceManager();
		BookResource book = new BookResource();
		book.setID(id);
		book.setName(name);
		book.setDomain(domain);
		book.setAuthors(authors);
		book.setPublisher(publisher);
		book.setPublishYear(publishYear);
		book.setAmount(amount);
		book.setDescription(description);
		resourceManager.update(book);

		jsp = "index.jsp";
		dispatch(jsp, request, response);

	}

	public void viewPatron(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		Patron patron = null;
		String userName = request.getParameter("update.userName");
		UserManager userManager = new StudentManager();
		String type = userManager.checkUserName(userName);
		if (type == null) {
			request.setAttribute("messageErr",
					"Tên đăng nhập này không tồn tại");
			jsp = "updatePatron.jsp";
			dispatch(jsp, request, response);
		}
		if (Patron.LIB_MANAGER_TYPE.equals(type)) {
			userManager = new LibStaffManager();
		} else if (Patron.FACULTY_TYPE.equals(type)) {
			userManager = new FacultyManager();
		} else if (Patron.STUDENT_TYPE.equals(type)) {
			userManager = new StudentManager();
		}
		patron = userManager.get(userName);
		request.setAttribute("patron_edit", patron);
		jsp = "editPatron.jsp";
		dispatch(jsp, request, response);
	}

	public void deletePatron(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String userName = request.getParameter("delete.userName");
		UserManager userManager = new StudentManager();
		String type = userManager.checkUserName(userName);
		if (type == null) {
			request.setAttribute("messageErr",
					"Tên đăng nhập này không tồn tại");
			jsp = "deletePatron.jsp";
			dispatch(jsp, request, response);
		}
		if (Patron.LIB_MANAGER_TYPE.equals(type)) {
			userManager = new LibStaffManager();
		} else if (Patron.FACULTY_TYPE.equals(type)) {
			userManager = new FacultyManager();
		} else if (Patron.STUDENT_TYPE.equals(type)) {
			userManager = new StudentManager();
		}
		userManager.remove(userName);
		jsp = "index.jsp";
		dispatch(jsp, request, response);
	}

	public void updatePatron(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		UserManager userManager = new StudentManager();
		String userName = request.getParameter("patron.userName");
		System.out.println(userName);
		String password = request.getParameter("patron.password");
		String fullName = request.getParameter("patron.fullName");

		String birthday = request.getParameter("patron.birthday");
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		java.util.Date date = null;
		try {
			date = df.parse(birthday);
		} catch (Exception e) {
			date = new java.util.Date();
		}

		String email = request.getParameter("patron.email");
		String address = request.getParameter("patron.address");

		String roles = userManager.checkUserName(userName);
		System.out.println(roles);
		if (roles == null) {
			jsp = "index.jsp";
			dispatch(jsp, request, response);
		}
		// String roles = request.getParameter("patron.roles");

		String studentID = request.getParameter("student.id");
		String studentClassroom = request.getParameter("student.classrom");
		String studentDepartment = request.getParameter("student.department");

		String facultyDepartment = request.getParameter("faculty.department");
		String facultySubject = request.getParameter("faculty.subjects");
		int permission = 0;
		try {
			permission = Integer.parseInt(request
					.getParameter("libManager.permission"));
		} catch (Exception e) {
			permission = 1;
		}

		if (Patron.LIB_MANAGER_TYPE.equals(roles)) {
			LibManager libManager = new LibManager();
			libManager.setUserName(userName);
			libManager.setPassword(password);
			libManager.setFullName(fullName);
			libManager.setBirthDay(date);
			libManager.setEmail(email);
			libManager.setAddress(address);
			libManager.setPermission(permission);
			userManager = new LibStaffManager();
			userManager.update(libManager);
			request.setAttribute("patron_edit", libManager);
		} else if (Patron.FACULTY_TYPE.equals(roles)) {
			Faculty patron = new Faculty();
			patron.setUserName(userName);
			patron.setPassword(password);
			patron.setFullName(fullName);
			patron.setBirthDay(date);
			patron.setEmail(email);
			patron.setAddress(address);

			patron.setDepartment(facultyDepartment);
			patron.setSubjects(facultySubject);

			userManager = new FacultyManager();
			userManager.update(patron);
			request.setAttribute("patron_edit", patron);
		} else if (Patron.STUDENT_TYPE.equals(roles)) {
			Student patron = new Student();
			patron.setUserName(userName);
			patron.setPassword(password);
			patron.setFullName(fullName);
			patron.setBirthDay(date);
			patron.setEmail(email);
			patron.setAddress(address);

			patron.setDepartment(studentDepartment);
			patron.setClassrom(studentClassroom);
			patron.setStudentID(studentID);

			System.out.println("Patron name : " + patron.getUserName());

			userManager = new StudentManager();
			userManager.update(patron);
			request.setAttribute("patron_edit", patron);
		}
		jsp = "index.jsp";
		dispatch(jsp, request, response);
	}

	public void addPatron(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String userName = request.getParameter("patron.userName");
		String password = request.getParameter("patron.password");
		String fullName = request.getParameter("patron.fullName");

		String birthday = request.getParameter("patron.birthday");
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		java.util.Date date = null;
		try {
			date = df.parse(birthday);
		} catch (Exception e) {
			date = new java.util.Date();
		}

		String email = request.getParameter("patron.email");
		String address = request.getParameter("patron.address");
		String roles = request.getParameter("patron.roles");

		String studentID = request.getParameter("student.id");
		String studentClassroom = request.getParameter("student.classrom");
		String studentDepartment = request.getParameter("student.department");

		String facultyDepartment = request.getParameter("faculty.department");
		String facultySubject = request.getParameter("faculty.subjects");
		int permission = 0;
		try {
			permission = Integer.parseInt(request
					.getParameter("libManager.permission"));
		} catch (Exception e) {
			permission = 1;
		}

		UserManager userManager = null;
		if (Patron.LIB_MANAGER_TYPE.equals(roles)) {
			LibManager libManager = new LibManager();
			libManager.setUserName(userName);
			libManager.setPassword(password);
			libManager.setFullName(fullName);
			libManager.setBirthDay(date);
			libManager.setEmail(email);
			libManager.setAddress(address);
			libManager.setPermission(permission);
			userManager = new LibStaffManager();
			userManager.add(libManager);
		} else if (Patron.FACULTY_TYPE.equals(roles)) {
			Faculty patron = new Faculty();
			patron.setUserName(userName);
			patron.setPassword(password);
			patron.setFullName(fullName);
			patron.setBirthDay(date);
			patron.setEmail(email);
			patron.setAddress(address);

			patron.setDepartment(facultyDepartment);
			patron.setSubjects(facultySubject);

			userManager = new FacultyManager();
			userManager.add(patron);
		} else if (Patron.STUDENT_TYPE.equals(roles)) {
			Student patron = new Student();
			patron.setUserName(userName);
			patron.setPassword(password);
			patron.setFullName(fullName);
			patron.setBirthDay(date);
			patron.setEmail(email);
			patron.setAddress(address);

			patron.setDepartment(studentDepartment);
			patron.setClassrom(studentClassroom);
			patron.setStudentID(studentID);

			userManager = new StudentManager();
			userManager.add(patron);
		}
		jsp = "index.jsp";
		dispatch(jsp, request, response);
	}

	public void login(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		String userName = request.getParameter("login.userName");
		System.out.println(userName);

		String password = request.getParameter("login.password");
		System.out.println(password);

		UserManager userManager = new StudentManager();
		roles = userManager.checkUser(userName, password);
		if (roles != null) {
			jsp = "index.jsp";
			logedIn = userName;
			session.setAttribute("login.done", logedIn);
			session.setAttribute("patron.roles", roles);
		} else {
			jsp = "login.jsp";
			request.setAttribute("messageErr",
					"Tên đăng nhập hoặc mật khẩu không đúng");
		}
		dispatch(jsp, request, response);
	}

	public void logout(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		jsp = "index.jsp";
		logedIn = null;
		// roles = "PATRON_LIB_MANAGER";
		session.removeAttribute("login.done");
		session.removeAttribute("patron.roles");
		dispatch(jsp, request, response);
	}

	public void dispatch(String jsp, HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		if (jsp != null) {
			RequestDispatcher rd = request.getRequestDispatcher(jsp);
			rd.forward(request, response);
		}
	}
}