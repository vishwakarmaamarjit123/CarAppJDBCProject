package com.alpha.carapp.controller;

import java.util.List;
import java.util.Scanner;

import com.alpha.carapp.dao.CarDao;
import com.alpha.carapp.dao.UserDao;
import com.alpha.carapp.dto.Car;
import com.alpha.carapp.dto.User;

public class UserController {

	
	public static void main(String[] args) {
		System.out.println("Welcome to User App");
		Driver(new Scanner(System.in), false);
	}
	
	public static User userReference() {
		return new User();
	}
	public static UserDao userDaoReference() {
		return new UserDao();
	}
	

	public static void Driver(Scanner scanner, boolean token) {
		// TODO Auto-generated method stub
		System.out.println("choice option 1-save user data , 2-update user , 3- find by id , 4- dispaly all ,5- delete by id ,6-exit");
		
		int choice = scanner.nextInt();
		if (choice == 6) {
			try {
				if(token)UserDao.userConnectionClose();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("Thank you");
			return ;
		}
		switch (choice) {
		case 1:
			saveUserData(scanner);
			break;
		case 2:
			updateUserData(scanner);
			break;
		case 3:
			findByIdUser(scanner);
			break;
		case 4:
			displayAllUser(scanner);
			break;
		case 5:
			deleteByIdUser(scanner);
			break;

		}
		token = true;
		Driver(scanner, token);
	}

	private static void saveUserData(Scanner scanner) {
		// TODO Auto-generated method stub
		User user = userReference();
		System.out.println("Enter id: ");
		user.setId(scanner.nextInt());
		System.out.println("Enter Name: ");
		scanner.nextLine();
		user.setName(scanner.nextLine());
		System.out.println("Enter Email: ");
		user.setEmail(scanner.nextLine());
		System.out.println("Enter password: ");
		user.setPassword(scanner.nextLine());
		System.out.println("Enter phone number: ");
		user.setPhoneNumber(scanner.nextInt());
		
		try {
			user = userDaoReference().createUser(user);
			if(user!=null) {
				System.out.println(user);
			}
			else {
				System.out.println("data not pushed");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
	}

	private static void deleteByIdUser(Scanner scanner) {
		// TODO Auto-generated method stub

		System.out.println("Enter id: ");
		int id = scanner.nextInt();
		
		try {
			if( userDaoReference().deleteByIdUser(id) ) {
				System.out.println("deleted successfully");
			}
			else{
				System.out.println("no data exist");
			}
			
		} 
		catch(Exception e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
		
	}

	private static void displayAllUser(Scanner scanner) {
		// TODO Auto-generated method stub		
		try {
			 List<User> list = userDaoReference().displayAllUser();
			if(list.size() >0 ) {
				System.out.println(list);
			}
			else {
				System.out.println("Empty table");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
		
	}

	private static void findByIdUser(Scanner scanner) {
		// TODO Auto-generated method stub
		User user = userReference();
		System.out.println("Enter id: ");
		user.setId(scanner.nextInt());
		
		try {
			user = userDaoReference().findIdByUser(user.getId());
			if(user!=null) {
				System.out.println(user);
			}
			else {
				System.out.println("data not exist");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
		
	}

	private static void updateUserData(Scanner scanner) {
		// TODO Auto-generated method stub
		User user = userReference();
		System.out.println("Enter id: ");
		user.setId(scanner.nextInt());
		System.out.println("Enter Name: ");
		scanner.nextLine();
		user.setName(scanner.nextLine());
		System.out.println("Enter Email: ");
		user.setEmail(scanner.nextLine());
		System.out.println("Enter phone number: ");
		user.setPhoneNumber(scanner.nextInt());
		
		try {
			user = userDaoReference().updateUser(user);
			if(user!=null) {
				System.out.println(user);
			}
			else {
				System.out.println("data not updated");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
		
		
	}
	
	
	
	
	
	
}
