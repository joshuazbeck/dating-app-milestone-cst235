package beans;

import java.io.Serializable;
import java.math.BigInteger;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * A model that holds the User object and is used to compare username and password
 * Implements Serializable to make it writable to a file for storage
 * @author Josh Beck
 *
 */
@ManagedBean
@SessionScoped
public class User implements Serializable {
	
	private static final long serialVersionUID = 1L;

	boolean loggedIn;
	
	private int id;

	@NotNull()
	@Size(min=5, max=15) 
	String firstName;
	
	@NotNull()
	@Size(min=5, max=15) 
	String lastName;
	
	@NotNull()
	String emailAddress;
	
	@NotNull()
	BigInteger phoneNumber;
	
	@NotNull()
	@Size(min=5) 
	String username;
	
	@NotNull()
	@Size(min=5) 
	String password;
	
	DatingUser datingUser;

	public boolean isLoggedIn() {
		return loggedIn;
	}

	public void setLoggedIn(boolean loggedIn) {
		this.loggedIn = loggedIn;
	}
	
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public BigInteger getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(BigInteger phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public DatingUser getDatingUser() {
		return datingUser;
	}

	public void setDatingUser(DatingUser datingUser) {
		this.datingUser = datingUser;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public User(String firstName, String lastName, String emailAddress, BigInteger phoneNumber, String username,
			String password, DatingUser datingUser) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.emailAddress = emailAddress;
		this.phoneNumber = phoneNumber;
		this.username = username;
		this.password = password;
		this.datingUser = datingUser;
		this.loggedIn = false;
	}
	public User() {
		super();
	}

}