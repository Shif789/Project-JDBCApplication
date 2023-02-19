package jdbcPractice.jdbcProject.daofactory;

import jdbcPractice.jdbcProject.persistence.IStudentDao;
import jdbcPractice.jdbcProject.persistence.StudentDaoImpl;

public class StudentDaoFactory {

	// to prevent object creation
	private StudentDaoFactory() {
	}

	// Creates reference of IStudent interface and encapsulates it and made static
	// so that it can be used in static methods
	private static IStudentDao studentDao = null;

	// creates object of StudentDao class and sends as loose coupling
	public static IStudentDao getStudentDao() {
		if (studentDao == null) {
			studentDao = new StudentDaoImpl();
		}
		return studentDao;
	}
}
