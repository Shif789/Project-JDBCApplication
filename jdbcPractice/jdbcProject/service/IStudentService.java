package jdbcPractice.jdbcProject.service;

import jdbcPractice.jdbcProject.dto.Student;

public interface IStudentService {

	// operations to be implemented
	public String addStudent(String sname, Integer sage, String saddress);

	public Student searchStudent(Integer sid);

	public String updateStudent(Integer sid, String sname, Integer sage, String saddress);

	public String updateStudent2(Student student);

	public String deleteStudent(Integer sid);

}
