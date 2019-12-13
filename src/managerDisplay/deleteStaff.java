package managerDisplay;

import staff.Manager;

public class deleteStaff {
	public static void delete(Manager m,String staffID) {
		DAO.deleteStaff(m.getUser(), m.getPass(), staffID);
	}
}
