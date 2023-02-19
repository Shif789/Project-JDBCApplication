package jdbcPractice.jdbcProject.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

import jdbcPractice.jdbcProject.dto.Student;
import jdbcPractice.jdbcProject.service.IStudentService;
import jdbcPractice.jdbcProject.servicefactory.StudentServiceFactory;

public class TestApp {

	public static void main(String[] args) throws Exception {
		Scanner scan = new Scanner(System.in);
		while (true) {
			System.out.println("Press 1 for Insert Operation");
			System.out.println("Press 2 for Search Operation");
			System.out.println("Press 3 for Delete Operation");
			System.out.println("Press 4 for Update Operation");
			System.out.print("Enter any number from 1/2/3/4: ");
			int opSelection = scan.nextInt();

			if (opSelection == 1) {
				insertOperation();// to call insert operation method
			} else if (opSelection == 2) {
				searchOperation();// to call search operation method
			} else if (opSelection == 3) {
				deleteOperation();// to call delete operation method
			} else if (opSelection == 4) {
				updateOperation2();// to call delete operation method
			}

			System.out.println("Do you want to continue? [yes/no]: ");
			String isExit = scan.next();
			if (isExit.equalsIgnoreCase("yes")) {
				break;
			}
		}

		scan.close();
	}

	private static void updateOperation2() throws IOException {
		IStudentService studentService = StudentServiceFactory.getStudentService();
		String newName = null;
		String newAge = null;
		String newAddress = null;

		// get the input from the users
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		// to get the id for the required updating record
		System.out.print("Enter student id that needs to be updated: ");
		String sid = br.readLine();

		// retrieve the data from database using serach operation
		Student student = studentService.searchStudent(Integer.parseInt(sid));

		// displaying current data and taking inputs for update
		if (student != null) {
			Student newStudent = new Student();

			System.out.println("Student id is: " + student.getSid());
			newStudent.setSid(student.getSid());
			System.out.print("Student name is: " + student.getSname() + "\tEnter the updated student name: ");
			newName = br.readLine();
			if (newName.equals("") || newName == "") {
				newStudent.setSname(student.getSname());
			} else {
				newStudent.setSname(newName);
			}

			System.out.print("Student age is: " + student.getSage() + "\tEnter the updated student age: ");
			newAge = br.readLine();
			if (newAge.equals("") || newAge == "") {
				newStudent.setSage(student.getSage());
			} else {
				newStudent.setSage(Integer.parseInt(newAge));
			}

			System.out.print("Student address is: " + student.getSaddress() + "\tEnter the updated student address: ");
			newAddress = br.readLine();
			if (newAddress.equals("") || newAddress == "") {
				newStudent.setSaddress(student.getSaddress());
			} else {
				newStudent.setSaddress(newAddress);
			}

			System.out.println("New student object is " + newStudent);
			System.out.println();
			// Updating the record
			String msg = studentService.updateStudent2(newStudent);
			if (msg.equalsIgnoreCase("success")) {
				System.out.println("record updated succesfully");
			} else {
				System.out.println("record updation failed....");
			}
		} else {
			System.out.println("Record not found for the given id " + sid);
		}
		// closing resource
		// br.close();
	}

	private static void updateOperation() {
		IStudentService studentService = StudentServiceFactory.getStudentService();
		String sname = null;
		Integer sage = null;
		String saddress = null;

		// get the input from the users
		Scanner scanner = new Scanner(System.in);

		// to get the id for the required updating record
		System.out.print("Enter student id that needs to be updated: ");
		int sid = scanner.nextInt();

		// retrieve the data from database using serach operation
		Student student = studentService.searchStudent(sid);

		// displaying current data and taking inputs for update
		if (student != null) {
			System.out.print("Student name is: " + student.getSname() + "\tEnter the updated student name: ");
			sname = scanner.next();
			if (sname == null) {
				sname = student.getSname();
			}

			System.out.print("Student age is: " + student.getSage() + "\tEnter the updated student age: ");
			sage = scanner.nextInt();
			if (sage == null)
				sage = student.getSage();

			System.out.print("Student address is: " + student.getSaddress() + "\tEnter the updated student address: ");
			saddress = scanner.next();
			if (saddress == null)
				saddress = student.getSaddress();

			// Updating the record
			String msg = studentService.updateStudent(sid, sname, sage, saddress);
			if (msg.equalsIgnoreCase("success")) {
				System.out.println("record updated succesfully");
			} else {
				System.out.println("record updation failed....");
			}
		} else {
			System.out.println("Record not found for the given id " + sid);
		}
		// closing resource
		// scanner.close();
	}

	private static void deleteOperation() {
		IStudentService studentService = StudentServiceFactory.getStudentService();

		// get the input from the users
		Scanner scanner = new Scanner(System.in);
		System.out.print("Enter student name: ");
		Integer sid = scanner.nextInt();

		String msg = studentService.deleteStudent(sid);
		if (msg.equalsIgnoreCase("success")) {
			System.out.println("record deleted succesfully");
		} else if (msg.equalsIgnoreCase("not found")) {
			System.out.println("record not found for the given id " + sid);
		} else {
			System.out.println("record deletion failed....");
		}

		// scanner.close();
	}

	private static void searchOperation() {
		Scanner scanner = new Scanner(System.in);
		System.out.print("Enter student id: ");
		int sid = scanner.nextInt();

		IStudentService studentService = StudentServiceFactory.getStudentService();
		Student student = studentService.searchStudent(sid);
		if (student != null) {
			// System.out.println(student);
			System.out.println("Id\tName\tAge\tAddress");
			System.out
					.println(student.getSid() + "\t" + student.getSname() + "\t"
							+ student.getSage() + "\t" + student.getSaddress());
		} else {
			System.out.println("Record not found for the given id " + sid);
		}
		// scanner.close();
	}

	private static void insertOperation() {
		IStudentService studentService = StudentServiceFactory.getStudentService();

		// get the input from the users
		Scanner scanner = new Scanner(System.in);
		System.out.print("Enter student name: ");
		String sname = scanner.next();
		System.out.print("Enter student age: ");
		int sage = scanner.nextInt();
		System.out.print("Enter student address: ");
		String saddress = scanner.next();

		String msg = studentService.addStudent(sname, sage, saddress);
		if (msg.equalsIgnoreCase("success")) {
			System.out.println("record inserted succesfully");
		} else {
			System.out.println("record insertion failed....");
		}

		// scanner.close();
	}

}
