package com.alpha.carapp.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.alpha.carapp.dto.Car;

public class CarDao {
	
	static Connection connection = null;
	static {
		try {
			Class.forName("org.postgresql.Driver");
			connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/carapp","postgres","pass");
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public Car createCar(Car car) throws Exception {
		String sql = "Insert into car Values (?,?,?,?,?)";
		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setInt(1, car.getId());
		preparedStatement.setString(2, car.getName());
		preparedStatement.setString(3, car.getBrand());
		preparedStatement.setDouble(4, car.getCost());
		preparedStatement.setInt(5, car.getManufactureYear());
		
		if(preparedStatement.executeUpdate()>0) {
			return car;
		}
			return null;
		
	}
	
	public Car updateCar(Car car) throws Exception {
		String sql = "update car set name=?, cost=? where id=?";
		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setInt(3, car.getId());
		preparedStatement.setString(1, car.getName());	
		preparedStatement.setDouble(2, car.getCost());
		if(preparedStatement.executeUpdate()>0) {
			return car;
		}
			return null;
			
	}
	
	public Car findIdByCar(int id) throws Exception {
		String sql = "select * from car where id=?";
		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setInt(1, id);
		ResultSet resultSet = preparedStatement.executeQuery();
		Car car = null;
		if(resultSet.next()) {
			car = new Car();
			car.setId(id);
			car.setName(resultSet.getString(2));
			car.setBrand(resultSet.getString(3));
			car.setCost(resultSet.getDouble(4));
			car.setManufactureYear(resultSet.getInt(5));
			return car;
		}
		return car;
	}
	
	public List<Car> displayAllCar() throws Exception{
		String sql  ="select * from car";
		Statement statement = connection.createStatement();
		ResultSet resultSet = statement.executeQuery(sql);
		List<Car> list = new ArrayList();
		while(resultSet.next()) {
			Car car = new Car();
			car.setId(resultSet.getInt(1));
			car.setName(resultSet.getString(2));
			car.setBrand(resultSet.getString(3));
			car.setCost(resultSet.getDouble(4));
			car.setManufactureYear(resultSet.getInt(5));
			list.add(car);
		}
		return list;	
	}
	
	public boolean deleteByIdCar(int id) throws Exception{
		String sql = "delete from car where id="+id;
		Statement statement = connection.createStatement();
		if(statement.executeUpdate(sql)>0) {
			return true;
		}
		return false;
	}
	
	
	public static void carConnectionClose() throws Exception {
		connection.close();
	}
	
	

}
