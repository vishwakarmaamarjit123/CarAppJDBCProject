package com.alpha.carapp.controller;

import java.util.List;
import java.util.Scanner;

import com.alpha.carapp.dao.CarDao;
import com.alpha.carapp.dto.Car;

public class CarController {
	
	public static void main(String[] args) {
		System.out.println("Welcome to Car App");
		Driver(new Scanner(System.in), false);
	}
	
	public static Car carReference() {
		return new Car();
	}
	public static CarDao carDaoReference() {
		return new CarDao();
	}
	

	public static void Driver(Scanner scanner, boolean token) {
		// TODO Auto-generated method stub
		System.out.println("choice option 1-save car data , 2-update car , 3- find by id , 4- dispaly all ,5- delete by id ,6-exit");
		
		int choice = scanner.nextInt();
		if (choice == 6) {
			try {
				if(token)CarDao.carConnectionClose();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("Thank you");
			return ;
		}
		switch (choice) {
		case 1:
			saveCarData(scanner);
			break;
		case 2:
			updateCarData(scanner);
			break;
		case 3:
			findByIdCar(scanner);
			break;
		case 4:
			displayAllCar(scanner);
			break;
		case 5:
			deleteByIdCar(scanner);
			break;

		}
		token = true;
		Driver(scanner, token);
	}

	private static void saveCarData(Scanner scanner) {
		// TODO Auto-generated method stub
		Car car = carReference();
		System.out.println("Enter id: ");
		car.setId(scanner.nextInt());
		System.out.println("Enter Name: ");
		scanner.nextLine();
		car.setName(scanner.nextLine());
		System.out.println("Enter Brand: ");
		car.setBrand(scanner.nextLine());
		System.out.println("Enter cost: ");
		car.setCost(scanner.nextDouble());
		System.out.println("Enter manufactureyear: ");
		car.setManufactureYear(scanner.nextInt());
		
		try {
			car = carDaoReference().createCar(car);
			if(car!=null) {
				System.out.println(car);
			}
			else {
				System.out.println("data not pushed");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
	}

	private static void deleteByIdCar(Scanner scanner) {
		// TODO Auto-generated method stub

		System.out.println("Enter id: ");
		int id = scanner.nextInt();
		
		try {
			if( carDaoReference().deleteByIdCar(id) ) {
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

	private static void displayAllCar(Scanner scanner) {
		// TODO Auto-generated method stub		
		try {
			 List<Car> list = carDaoReference().displayAllCar();
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

	private static void findByIdCar(Scanner scanner) {
		// TODO Auto-generated method stub
		Car car = carReference();
		System.out.println("Enter id: ");
		car.setId(scanner.nextInt());
		
		try {
			car = carDaoReference().findIdByCar(car.getId());
			if(car!=null) {
				System.out.println(car);
			}
			else {
				System.out.println("data not Exist");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
		
	}

	private static void updateCarData(Scanner scanner) {
		// TODO Auto-generated method stub
		Car car = carReference();
		System.out.println("Enter id: ");
		car.setId(scanner.nextInt());
		System.out.println("Enter Name: ");
		scanner.nextLine();
		car.setName(scanner.nextLine());
		System.out.println("Enter cost: ");
		car.setCost(scanner.nextDouble());
		
		try {
			car = carDaoReference().updateCar(car);
			if(car!=null) {
				System.out.println(car);
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
