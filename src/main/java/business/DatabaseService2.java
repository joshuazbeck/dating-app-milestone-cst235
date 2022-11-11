package business;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.ejb.Local;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.enterprise.inject.Alternative;

import beans.DatingUser;
import beans.User;

/**
 * @author Tanner Ray
 *
 */
@Stateless
@Local(DatabaseServiceInterface.class)
@LocalBean
@Alternative
public class DatabaseService2 implements DatabaseServiceInterface2 {
	
	private static final String DB_URL = "jdbc:mysql://localhost:3307/cst-235-dating-app";
	private static final String DB_USER = "root";
	private static final String PASSWORD = "root";
	
	//User table queries
	private static final String INSERT_USER = "INSERT INTO User () VALUES ()";
	private static final String FIND_BY_ID = "SELECT * FROM User WHERE id=?";
	private static final String FIND_ALL = "SELCT * FROM User ORDER BY id";
	private static final String UPDATE = "UPDATE User SET ";
	private static final String DELETE = "DELETE FROM User WHERE id=?";
	
	//DatingUser table queries
	private static final String INSERT_DU = "INSERT INTO DatingUser () VALUES ()";
	private static final String FIND_DU_ID = "";
	private static final String FIND_ALL_DU = "";
	private static final String UPDATE_DU = "UPDATE DatingUser SET ";
	private static final String DELETE_DU = "DELETE FROM DatingUser WHERE user_id";
	
	//Hobby table queries
	private static final String INSERT_HOBBY = "";
	private static final String FIND_HOBBY_ID = "";
	private static final String FIND_ALL_HOBBIES = "";
	private static final String UPDATE_HOBBY = "";
	private static final String DELETE_HOBBY = "";
	
	//Address table queries
	private static final String INSERT_ADDRESS = "";
	private static final String FIND_ADDRESS_ID = "";
	private static final String UPDATE_ADDRESS = "";
	private static final String DELETE_ADDRESS = "";
	
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
			stmt.setString(3, user.getEmailAddress());
			stmt.setObject(4, user.getPhoneNumber());
			stmt.setString(5, user.getUsername());
			stmt.setString(6, user.getPassword());
			
			stmt.executeQuery();
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
	public User findAllUsers(User user) {
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			conn = getConnection();
			stmt = conn.prepareStatement(FIND_ALL, Statement.RETURN_GENERATED_KEYS);
			
			ResultSet rs = stmt.executeQuery();
			
			if (rs.next()) {
				user.getId();
			}
			return this.findUserById(user);
			
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
	public User findUserById(User user) {
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			conn = getConnection();
			stmt = conn.prepareStatement(FIND_BY_ID, Statement.RETURN_GENERATED_KEYS);
			stmt.setInt(1, user.getId());
			
			ResultSet rs = stmt.executeQuery();
			
			if (rs.next()) {
				User found = new User();
				found.setId(rs.getInt("id"));
				
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
			stmt.setString(3, user.getEmailAddress());
			stmt.setObject(4, user.getPhoneNumber());
			stmt.setString(5, user.getPassword());
			stmt.setObject(6, user.getDatingUser());
			
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
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			close(stmt);
			close(conn);
		}
	}
	
	@Override
	public void addDatingUser(User user) {
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			conn = getConnection();
			stmt = conn.prepareStatement(INSERT_DU, Statement.RETURN_GENERATED_KEYS);
			stmt.setInt(0, 0);
					
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		
	}
	@Override
	public DatingUser findAllDatingUsers(User user) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public DatingUser findDatingUserById(User user) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public void updateDatingUser(User user) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void deleteDatingUser(User user) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void addHobby(DatingUser du) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void updateHobby(DatingUser du) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void deleteHobby(DatingUser du) {
		// TODO Auto-generated method stub

	}
	
	@Override
	public void addAddress(User user) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void updateAddress(User user) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void deleteAddress(User user) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void findAddress(User user) {
		// TODO Auto-generated method stub
		
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