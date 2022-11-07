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
 * The service used to emulate the database as of Milestone 2
 * @author Josh Beck
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
	
	private static final String INSERT_USER = "INSERT INTO users () VALUES (?)";
	private static final String FIND_BY_ID = "SELECT * FROM users WHERE id=?";
	private static final String FIND_ALL = "SELCT * FROM users ORDER BY id";
	private static final String UPDATE = "UPDATE users SET first_name=?, last_name=?, email_address=?, phone_number=?, password=?, dating_user=?";
	private static final String DELETE = "DELETE from users WHERE id=?";
	
	/**
	 * Add a user to the database
	 * @param user - User
	 */
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
			stmt.setObject(7, user.getDatingUser());
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			close(stmt);
			close(conn);
		}
	}
	@Override
	public User findAllUsers(User user) {
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			conn = getConnection();
			stmt = conn.prepareStatement(FIND_ALL, Statement.RETURN_GENERATED_KEYS);
			
			ResultSet rs = stmt.getGeneratedKeys();
			
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
			
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			close(stmt);
			close(conn);
		}
	}
	
	@Override
	public void deleteUser(User user) {
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			conn = getConnection();
			stmt = conn.prepareStatement(DELETE, Statement.RETURN_GENERATED_KEYS);
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
