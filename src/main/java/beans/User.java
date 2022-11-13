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
	@Size(min=2, max=15) 
	String firstName;
	
	@NotNull()
	@Size(min=2, max=15) 
	String lastName;
	
	@NotNull()
	String emailAddress;
	
	@NotNull()
	BigInteger phoneNumber;
	
	@NotNull()
	@Size(min=2) 
	String address;
	String address2;
	@NotNull()
	@Size(min=2) 
	String city;
	@NotNull()
	@Size(min=2) 
	String state;
	@NotNull()
	@Size(min=2) 
	String country;
	
	@NotNull()
	int zipcode;
	
	@NotNull()
	@Size(min=5) 
	String username;
	
	@NotNull()
	@Size(min=5) 
	String password;
	

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

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getAddress2() {
		return address2;
	}

	public void setAddress2(String address2) {
		this.address2 = address2;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public int getZipcode() {
		return zipcode;
	}

	public void setZipcode(int zipcode) {
		this.zipcode = zipcode;
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
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public User(String firstName, String lastName, String emailAddress, BigInteger phoneNumber, String username,
			String password) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.emailAddress = emailAddress;
		this.phoneNumber = phoneNumber;
		this.username = username;
		this.password = password;
		this.loggedIn = false;
	}
	public User() {
		super();
	}

	public User(int id) {
		this.id = id;
	}

}