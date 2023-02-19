package jdbcPractice.jdbcProject.persistence;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import jdbcPractice.JdbcUtil;
import jdbcPractice.jdbcProject.dto.Student;

public class StudentDaoImpl implements IStudentDao {
	// Resources used
	Connection connection;
	PreparedStatement preparedStatement = null;
	ResultSet resultSet = null;

	@Override
	public String addStudent(String sname, Integer sage, String saddress) {
		String sqlInsertQuery = "INSERT INTO students(name,age, address) VALUES(?,?,?)";
		try {
			// setting connection
			connection = JdbcUtil.getJdbcConnection();

			// creating prepared statement
			if (connection != null)
				preparedStatement = connection.prepareStatement(sqlInsertQuery);

			// set values for query parameters
			if (preparedStatement != null) {
				preparedStatement.setString(1, sname);
				preparedStatement.setInt(2, sage);
				preparedStatement.setString(3, saddress);

				// execute the query
				int noRowsAffected = preparedStatement.executeUpdate();

				// Display the result
				if (noRowsAffected == 1) {
					return "success";
				}
			}
		} catch (SQLException | IOException e) {
			e.printStackTrace();
		}
		return "failure";
	}

	@Override
	public Student searchStudent(Integer sid) {
		Student student = null;

		String sqlSelectQuery = "SELECT id,name,age,address FROM students WHERE id=?";
		try {
			// setting connection
			connection = JdbcUtil.getJdbcConnection();

			// creating prepared statement
			if (connection != null)
				preparedStatement = connection.prepareStatement(sqlSelectQuery);

			// set values for query parameters
			if (preparedStatement != null) {
				preparedStatement.setInt(1, sid);

				// execute the query
				resultSet = preparedStatement.executeQuery();

				// Display the result
				if (resultSet != null) {
					if (resultSet.next()) {
						student = new Student();
						int sId = resultSet.getInt(1);
						String sName = resultSet.getString(2);
						int sAge = resultSet.getInt(3);
						String sAddress = resultSet.getString(4);

						student.setSid(sId);
						student.setSname(sName);
						student.setSage(sAge);
						student.setSaddress(sAddress);

					}

					return student;
				}
			}
		} catch (SQLException | IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public String updateStudent(Integer sid, String sname, Integer sage, String saddress) {
		String sqlUpdateQuery = "UPDATE students SET name=?,age=?,address=? WHERE id=?";
		try {
			// setting connection
			connection = JdbcUtil.getJdbcConnection();

			// creating prepared statement
			if (connection != null)
				preparedStatement = connection.prepareStatement(sqlUpdateQuery);

			// set values for query parameters
			if (preparedStatement != null) {
				preparedStatement.setString(1, sname);
				preparedStatement.setInt(2, sage);
				preparedStatement.setString(3, saddress);
				preparedStatement.setInt(4, sid);

				// execute the query
				int noRowsAffected = preparedStatement.executeUpdate();

				// Display the result
				if (noRowsAffected == 1) {
					return "success";
				}
			}
		} catch (SQLException | IOException e) {
			e.printStackTrace();
		}
		return "failure";
	}

	@Override
	public String updateStudent2(Student student) {
		String sqlUpdateQuery = "UPDATE students SET name=?,age=?,address=? WHERE id=?";
		try {
			// setting connection
			connection = JdbcUtil.getJdbcConnection();

			// creating prepared statement
			if (connection != null)
				preparedStatement = connection.prepareStatement(sqlUpdateQuery);

			// set values for query parameters
			if (preparedStatement != null) {
				preparedStatement.setString(1, student.getSname());
				preparedStatement.setInt(2, student.getSage());
				preparedStatement.setString(3, student.getSaddress());
				preparedStatement.setInt(4, student.getSid());

				// execute the query
				int noRowsAffected = preparedStatement.executeUpdate();

				// Display the result
				if (noRowsAffected == 1) {
					return "success";
				}
			}
		} catch (SQLException | IOException e) {
			e.printStackTrace();
		}
		return "failure";
	}

	@Override
	public String deleteStudent(Integer sid) {
		String sqlDeleteQuery = "DELETE FROM students WHERE id=?";
		try {
			// setting connection
			connection = JdbcUtil.getJdbcConnection();

			// creating prepared statement
			if (connection != null)
				preparedStatement = connection.prepareStatement(sqlDeleteQuery);

			// set values for query parameters
			if (preparedStatement != null) {
				preparedStatement.setInt(1, sid);

				// execute the query
				int noRowsAffected = preparedStatement.executeUpdate();

				// Display the result
				if (noRowsAffected == 1) {
					return "success";
				} else {
					return "not found";
				}
			}
		} catch (SQLException | IOException e) {
			e.printStackTrace();
			return "failure";
		}
		return "failure";
	}

}
