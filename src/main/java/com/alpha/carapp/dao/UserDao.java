package com.alpha.carapp.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.alpha.carapp.dto.Car;
import com.alpha.carapp.dto.User;

public class UserDao {

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
	


	
	public User createUser(User user) throws Exception {
		String sql = "Insert into users Values (?,?,?,?,?)";
		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setInt(1, user.getId());
		preparedStatement.setString(2, user.getName());
		preparedStatement.setString(3, user.getEmail());
		preparedStatement.setString(4, user.getPassword());
		preparedStatement.setInt(5, user.getPhoneNumber());
		if(preparedStatement.executeUpdate()>0) {
			return user;
		}else {
			return null;
		}	
	}
	
	public User updateUser(User user) throws Exception {
		String sql = "update users set name=?, email=?, phonenumber =? where id=?";
		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setInt(4, user.getId());
		preparedStatement.setString(1, user.getName());	
		preparedStatement.setString(2, user.getEmail());
		preparedStatement.setInt(3, user.getPhoneNumber());
		if(preparedStatement.executeUpdate()>0) {
			return user;
		}else {
			return null;
		}	
	}
	
	public User findIdByUser(int id) throws Exception {
		String sql = "select * from users where id=?";
		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setInt(1, id);
		ResultSet resultSet = preparedStatement.executeQuery();
		User user = null;
		if(resultSet.next()) {
			user = new User();
			user.setId(id);
			user.setName(resultSet.getString(2));
			user.setEmail(resultSet.getString(3));
			user.setPassword(resultSet.getString(4));
			user.setPhoneNumber(resultSet.getInt(5));
			return user;
		}
		return user;
	}
	
	public List<User> displayAllUser() throws Exception{
		String sql  ="select * from users";
		Statement statement = connection.createStatement();
		ResultSet resultSet = statement.executeQuery(sql);
		List<User> list = new ArrayList();
		while(resultSet.next()) {
			User user = new User();
			user.setId(resultSet.getInt(1));
			user.setName(resultSet.getString(2));
			user.setEmail(resultSet.getString(3));
			user.setPassword(resultSet.getString(4));
			user.setPhoneNumber(resultSet.getInt(5));
			list.add(user);
		}
		return list;	
	}
	
	public boolean deleteByIdUser(int id) throws Exception{
		String sql = "delete from users where id="+id;
		Statement statement = connection.createStatement();
		if(statement.executeUpdate(sql)>0) {
			return true;
		}
		return false;
	}
	
	
	
	
	public static void userConnectionClose() throws Exception {
		connection.close();
	}
	
	
}
