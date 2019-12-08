package staff;

public class Manager extends Staff{
	private static final String USER="manager";
	private static final String PASS="manager";

	public Manager(String staffID) {
		super(staffID);
	}
	
	public String getUser() {return USER;}
	public String getPass() {return PASS;}
}
