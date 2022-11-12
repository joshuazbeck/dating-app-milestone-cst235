package data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.Local;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.enterprise.inject.Alternative;

import beans.DatingUser;
import beans.User;
import business.DatabaseServiceInterface;

/**
 * @author Tanner Ray
 *
 */
@Stateless
@Local(DatabaseServiceInterface2.class)
@LocalBean
@Alternative
public class DatabaseService2 implements DatabaseServiceInterface2 {
	
	private static final String DB_URL = "jdbc:mysql://localhost:3307/milestonecst235";
	private static final String DB_USER = "root";
	private static final String PASSWORD = "root";
	
	//User table queries
	private static final String INSERT_USER = "INSERT INTO user (firstname, lastname, phone_num, email, address_line1, address_line2, city, state, country, zipcode, username, password) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	private static final String GET_BY_ID = "SELECT * FROM user WHERE user_id=?";
	private static final String GET_BY_USER = "SELECT * FROM user WHERE username=?";
	private static final String GET_ALL = "SELCT * FROM user";
	private static final String UPDATE = "UPDATE user SET firstname=?, lastname=?, phone_num=?, email=?, username=?, password=? WHERE user_id=?";
	private static final String DELETE = "DELETE FROM user WHERE user_id=?";
	
	//DatingUser table queries
	private static final String INSERT_DU = "INSERT INTO dating_user (education, spoken_languages, hair_color, eye_color, height_inches, user_id) VALUES (?, ?, ?, ?, ?, ?)";
	private static final String GET_DU_ID = "SELECT * FROM dating_user WHERE dating_user_id=? AND user_id=?";
	private static final String GET_ALL_DU = "SELECT * FROM dating_user";
	private static final String UPDATE_DU = "UPDATE dating_user SET ";
	private static final String DELETE_DU = "DELETE FROM dating_user WHERE dating_user_id=?";
	
	//Hobby table queries
	private static final String INSERT_HOBBY = "";
	private static final String GET_HOBBY_ID = "";
	private static final String GET_ALL_HOBBIES = "";
	private static final String UPDATE_HOBBY = "";
	private static final String DELETE_HOBBY = "";
	
	
	/**
	 * Add a user to the database
	 * @param user - User
	 */
	@Override
	public void addUsers(User user) {
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			conn = getConnection();
			stmt = conn.prepareStatement(INSERT_USER, Statement.RETURN_GENERATED_KEYS);
			stmt.setString(1, user.getFirstName());
			stmt.setString(2, user.getLastName());
			stmt.setObject(3, user.getPhoneNumber());
			stmt.setString(4, user.getEmailAddress());
			stmt.setString(5, user.getAddress());
			stmt.setString(6, user.getAddress2());
			stmt.setString(7, user.getCity());
			stmt.setString(8, user.getState());
			stmt.setString(9, user.getCountry());
			stmt.setInt(10, user.getZipcode());
			stmt.setString(11, user.getUsername());
			stmt.setString(12, user.getPassword());
			
			stmt.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			close(stmt);
			close(conn);
		}
	}
	/**
	 * retrieve all users from the database
	 * @param user - User
	 */
	@Override
	public List<User> getAllUsers() {
		Connection conn = null;
		Statement stmt = null;
		try {
			conn = getConnection();
			stmt = conn.createStatement();
			
			ResultSet rs = stmt.executeQuery(GET_ALL);
			List<User> users = new ArrayList<User>();
			while (rs.next()) {
				int id = rs.getInt("user_id");
				
				users.add(new User(id));
				//might need to add more parameters here later.
				//not needed as of now.
			}
			rs.close();
			return users;
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			close(stmt);
			close(conn);
		}
	}
	/**
	 * retrieve one user from database from given ID
	 * @param user - User
	 */
	@Override
	public User getUserById(User user) {
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			conn = getConnection();
			stmt = conn.prepareStatement(GET_BY_ID, Statement.RETURN_GENERATED_KEYS);
			stmt.setInt(1, user.getId());
			
			ResultSet rs = stmt.executeQuery();
			
			if (rs.next()) {
				User found = new User();
				found.setId(rs.getInt("user_id"));
				
				return found;
			} else {
				return null;
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			close(stmt);
			close(conn);
		}
	}
	@Override
	public User getUserByUsername(User user) {
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			conn = getConnection();
			stmt = conn.prepareStatement(GET_BY_USER, Statement.RETURN_GENERATED_KEYS);
			stmt.setString(1, user.getUsername());
			
			ResultSet rs = stmt.executeQuery();
			
			if (rs.next()) {
				User found = new User();
				found.setUsername(rs.getString("username"));
				
				return found;
			} else {
				return null;
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			close(stmt);
			close(conn);
		}
	}
	/**
	 * update a user in the database
	 * @param user - User
	 */
	@Override
	public void updateUser(User user) {
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			conn = getConnection();
			stmt = conn.prepareStatement(UPDATE, Statement.RETURN_GENERATED_KEYS);
			stmt.setString(1, user.getFirstName());
			stmt.setString(2, user.getLastName());
			stmt.setObject(3, user.getPhoneNumber());
			stmt.setString(4, user.getEmailAddress());
			stmt.setString(5, user.getUsername());
			stmt.setString(6, user.getPassword());
			stmt.setInt(7, user.getId());
			
			stmt.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			close(stmt);
			close(conn);
		}
	}
	/**
	 * delete a user from the database
	 * @param user - User
	 */
	@Override
	public void deleteUser(User user) {
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			conn = getConnection();
			stmt = conn.prepareStatement(DELETE, Statement.RETURN_GENERATED_KEYS);
			stmt.setInt(1, user.getId());
			
			stmt.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			close(stmt);
			close(conn);
		}
	}
	
	@Override
	public void addDatingUser(DatingUser du) {
		Connection conn = null;
		PreparedStatement stmt = null;
		try {			
			conn = getConnection();
			stmt = conn.prepareStatement(INSERT_DU, Statement.RETURN_GENERATED_KEYS);
			stmt.setString(1, du.getEducation());
			stmt.setObject(2, du.getLanguagesSpoken());
			stmt.setString(3, du.getHairColor());
			stmt.setString(4, du.getEyeColor());
			stmt.setInt(5, du.getHeightInches());
			stmt.setInt(6, du.getUserRef().getId());
			
			stmt.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		
	}
	@Override
	public List<DatingUser> getAllDatingUsers() {
		Connection conn = null;
		Statement stmt = null;
		try {
			conn = getConnection();
			stmt = conn.createStatement();
			
			ResultSet rs = stmt.executeQuery(GET_ALL_DU);
			
			List<DatingUser> datingUsers = new ArrayList<DatingUser>();
			while (rs.next()) {
				String edu = rs.getString("education");
				//need to set more keys
				
				datingUsers.add(new DatingUser(edu));
			}
			rs.close();
			return datingUsers;
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			close(stmt);
			close(conn);
		}
	}
	
	private Connection getConnection() {
		try {
			return DriverManager.getConnection(DB_URL, DB_USER, PASSWORD);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	private static void close(Statement stmt) {
		if (stmt != null) {
			try {
				stmt.close();
			} catch (SQLException e) {
				throw new RuntimeException(e);
			}
		}
	}
	
	private static void close(Connection conn) {
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				throw new RuntimeException(e);
			}
		}
	}

}