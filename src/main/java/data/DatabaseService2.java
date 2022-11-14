package data;

import java.io.ByteArrayInputStream;
import java.io.ObjectInputStream;
import java.math.BigInteger;
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

import org.jboss.resteasy.util.Hex;

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
	
	private static final String DB_URL = "jdbc:mysql://localhost:3306/milestonecst235?autoReconnect=true&useSSL=false";
	private static final String DB_USER = "root";
	private static final String PASSWORD = "password";
	
	//User table queries
	private static final String INSERT_USER = "INSERT INTO user (firstname, lastname, phone_num, email, address_line1, address_line2, city, state, country, zipcode, username, password) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	private static final String GET_BY_ID = "SELECT * FROM user WHERE user_id=?";
	private static final String GET_BY_USER = "SELECT * FROM user WHERE username=?";
	private static final String GET_ALL = "SELECT * FROM user";
	private static final String UPDATE = "UPDATE user SET firstname=?, lastname=?, phone_num=?, address_line1=?, address_line2, city=?, state=?, country=?, zipcode=?, email=?, username=?, password=? WHERE user_id=?";
	private static final String DELETE = "DELETE FROM user WHERE user_id=?";
	
	//DatingUser table queries
	private static final String INSERT_DU = "INSERT INTO dating_user (education, spoken_languages, hair_color, eye_color, height_inches, user_id, hobbies) VALUES (?, ?, ?, ?, ?, ?, ?)";
	private static final String GET_ALL_DU = "SELECT * FROM dating_user";
	private static final String UPDATE_DU = "UPDATE dating_user SET education=?, spoken_languages=?, hair_color=?, eye_color=?, height_inches=?, user_id=?, hobbies=? WHERE dating_user_id=?";
	private static final String DELETE_DU = "DELETE FROM dating_user WHERE dating_user_id=?";
		
	private String delimator = "@";
	/**
	 * Add a user to the database
	 * @param user - User
	 */
	@Override
	public void addUsers(User user) throws RuntimeException, SQLException{
		Connection conn = null;
		PreparedStatement stmt = null;
		
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

			close(stmt);
			close(conn);

	}
	/**
	 * retrieve all users from the database
	 * @param user - User
	 */
	@Override
	public List<User> getAllUsers() throws RuntimeException, SQLException{
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
	public User getUserById(int id) throws RuntimeException, SQLException{
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			conn = getConnection();
			stmt = conn.prepareStatement(GET_BY_ID, Statement.RETURN_GENERATED_KEYS);
			stmt.setInt(1, id);
			
			ResultSet rs = stmt.executeQuery();
			
			if (rs.next()) {
				User found = new User();
				found.setFirstName(rs.getString("firstname"));
				found.setLastName(rs.getString("lastname"));
				found.setPhoneNumber(BigInteger.valueOf(rs.getInt("phone_num")));
				found.setAddress(rs.getString("address_line1"));
				found.setAddress2(rs.getString("address_line2"));
				found.setCity(rs.getString("city"));
				found.setState(rs.getString("city"));
				found.setCountry(rs.getString("country"));
				found.setZipcode(rs.getInt("zipcode"));
				found.setEmailAddress(rs.getString("email"));
				found.setUsername(rs.getString("username"));
				found.setPassword(rs.getString("password"));
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
	public User getUserByUsername(User user) throws RuntimeException, SQLException{
		Connection conn = null;
		PreparedStatement stmt = null;

			conn = getConnection();
			stmt = conn.prepareStatement(GET_BY_USER, Statement.RETURN_GENERATED_KEYS);
			stmt.setString(1, user.getUsername());
			
			ResultSet rs = stmt.executeQuery();
			
			if (rs.next()) {
				User found = new User();
				found.setUsername(rs.getString("username"));
				
				close(stmt);
				close(conn);
				return found;
			} else {
				close(stmt);
				close(conn);
				return null;
			}
		
			
		
	}
	/**
	 * update a user in the database
	 * @param user - User
	 */
	@Override
	public void updateUser(User user) throws RuntimeException, SQLException{
		Connection conn = null;
		PreparedStatement stmt = null;

			conn = getConnection();
			stmt = conn.prepareStatement(UPDATE, Statement.RETURN_GENERATED_KEYS);
			stmt.setString(1, user.getFirstName());
			stmt.setString(2, user.getLastName());
			stmt.setObject(3, user.getPhoneNumber());
			stmt.setString(4, user.getAddress());
			stmt.setString(5, user.getAddress2());
			stmt.setString(6, user.getState());
			stmt.setString(7, user.getCountry());
			stmt.setInt(8, user.getZipcode());
			stmt.setString(9, user.getEmailAddress());
			stmt.setString(10, user.getUsername());
			stmt.setString(11, user.getPassword());
			stmt.setInt(12, user.getId());
								
			stmt.executeUpdate();
			close(stmt);
			close(conn);
		
	}
	/**
	 * delete a user from the database
	 * @param user - User
	 */
	@Override
	public void deleteUser(User user) throws RuntimeException, SQLException{
		Connection conn = null;
		PreparedStatement stmt = null;

			conn = getConnection();
			stmt = conn.prepareStatement(DELETE, Statement.RETURN_GENERATED_KEYS);
			stmt.setInt(1, user.getId());
			
			stmt.executeUpdate();

			close(stmt);
			close(conn);
		
	}
	
	@Override
	public void addDatingUser(DatingUser du) throws RuntimeException, SQLException {
		Connection conn = null;
		PreparedStatement stmt = null;
			
			conn = getConnection();
			stmt = conn.prepareStatement(INSERT_DU, Statement.RETURN_GENERATED_KEYS);
			stmt.setString(1, du.getEducation());
			stmt.setString(2, du.getHairColor());
			stmt.setObject(3, du.getLanguagesSpoken());
			stmt.setString(4, du.getEyeColor());
			stmt.setInt(5, du.getHeightInches());
			
			//MARK: TODO get user from session... hard coding for now
			int user_id = 2;
			//MARK: Set to currently logged in user
			stmt.setInt(6, user_id);
			String hobbyString = "";
			for (String hobby: du.getHobbies()) {
				hobbyString += hobby + this.delimator;
			}
			System.out.println(hobbyString);
			stmt.setObject(7, hobbyString);
			
			stmt.executeUpdate();
	
		
	}
	@Override
	public List<DatingUser> getAllDatingUsers() throws RuntimeException, SQLException{
		Connection conn = null;
		Statement stmt = null;
		
			conn = getConnection();
			stmt = conn.createStatement();
			
			ResultSet rs = stmt.executeQuery(GET_ALL_DU);
			
			List<DatingUser> datingUsers = new ArrayList<DatingUser>();
			while (rs.next()) {
				
				String hobbyString = rs.getString("hobbies");
				// serialize

				List<String> hobbyList = new ArrayList<String>();
			    for (String hobby: hobbyString.split(this.delimator)) {
			    	hobbyList.add(hobby);
			    }
				//need to set more keys
				
			    User uR = this.getUserById(rs.getInt("user_id"));
			    
			    //System.out.println(rs.getInt("user_id"));
			    DatingUser du = new DatingUser();
			    du.setEducation(rs.getString("education"));
			    du.setEyeColor(rs.getString("eye_color"));
			    du.setHairColor(rs.getString("hair_color"));
			    du.setLanguagesSpoken(rs.getString("spoken_languages"));
			    du.setUserRef(uR);
			    du.setHeightInches(rs.getInt("height_inches"));
			    du.setHobbies(hobbyList);
			    du.setId(rs.getInt("dating_user_id"));
			    
				datingUsers.add(du);
			}
			rs.close();
			close(stmt);
			close(conn);
			return datingUsers;
			

			
		
	}
	
	@Override
	public void updateDatingUser(DatingUser du) throws RuntimeException, SQLException {
		
		Connection conn = null;
		PreparedStatement stmt = null;

			conn = getConnection();
			stmt = conn.prepareStatement(UPDATE_DU, Statement.RETURN_GENERATED_KEYS);
			stmt.setString(1, du.getEducation());
			stmt.setObject(2, du.getLanguagesSpoken());
			stmt.setString(3, du.getHairColor());
			stmt.setString(4, du.getEyeColor());
			stmt.setInt(5, du.getHeightInches());
			
			int user_id = 2;
			stmt.setInt(6, user_id);
			String hobbyString = "";
			for (String hobby: du.getHobbies()) {
				hobbyString += hobby + this.delimator;
			}
			System.out.println(hobbyString);
			stmt.setObject(7, hobbyString);
			stmt.setInt(8, du.getId()); 
			stmt.executeUpdate();
	
			close(stmt);
			close(conn);
		
	}
	@Override
	public void deleteDatingUser(DatingUser du) throws RuntimeException, SQLException{
		Connection conn = null;
		PreparedStatement stmt = null;
			
			conn = getConnection();
			stmt = conn.prepareStatement(DELETE_DU, Statement.RETURN_GENERATED_KEYS);
			stmt.setInt(1, du.getId());
			
			stmt.executeUpdate();
		
			close(stmt);
			close(conn);
		
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