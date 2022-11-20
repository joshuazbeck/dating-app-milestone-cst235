package beans;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * This is the "product" for the Milestone
 * It contains a reference to a User object for further information
 * @author Josh Beck
 *
 */
@ManagedBean(name="datingUser")
@SessionScoped
public class DatingUser implements Serializable {
	
	private static final long serialVersionUID = 2L;
	
	int id;
	
	public String getFirstName() {
		if (this.userRef != null) {
			return this.userRef.getFirstName();
		} else {
			return "";
		}
	}
	
	public String getLastName() {
		if (this.userRef != null) {
			return this.userRef.getLastName();
		} else {
			return "";
		}
	}
	
	public String getEmailAddress() {
		if (this.userRef != null) {
			return this.userRef.getEmailAddress();
		} else {
			return "";
		}
	}
	
	public BigInteger getPhoneNumber() {
		if (this.userRef != null) {
			return this.userRef.getPhoneNumber();
		} else {
			return BigInteger.ONE;
		}
	}
	
	List<String> hobbies;
	
	@NotNull()
	@Size(min=2, max=100) 
	String education;
	
	@NotNull()
	@Size(min=2, max=100) 
	String languagesSpoken;
	
	@NotNull()
	@Size(min=2, max=50) 
	String hairColor;
	
	@NotNull()
	@Size(min=2, max=50) 
	String eyeColor;
	
	int heightInches;
	
	User userRef;
	
	public List<String> getHobbies() {
		return hobbies;
	}
	public void setHobbies(List<String> hobbies) {
		this.hobbies = hobbies;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

	public String getEducation() {
		return education;
	}
	public void setEducation(String education) {
		this.education = education;
	}
	public String getHairColor() {
		return hairColor;
	}
	public void setHairColor(String hairColor) {
		this.hairColor = hairColor;
	}
	public String getEyeColor() {
		return eyeColor;
	}
	public void setEyeColor(String eyeColor) {
		this.eyeColor = eyeColor;
	}
	public int getHeightInches() {
		return heightInches;
	}
	public void setHeightInches(int heightInches) {
		this.heightInches = heightInches;
	}
	public User getUserRef() {
		return userRef;
	}
	public void setUserRef(User userRef) {
		this.userRef = userRef;
	}
	
	
	public DatingUser(String education, String languagesSpoken,
			String hairColor, String eyeColor, int heightInches, User userRef) {
		super();
		this.education = education;
		this.languagesSpoken = languagesSpoken;
		this.hairColor = hairColor;
		this.eyeColor = eyeColor;
		this.heightInches = heightInches;
		this.userRef = userRef;
	}
	public String getLanguagesSpoken() {
		return languagesSpoken;
	}
	public void setLanguagesSpoken(String languagesSpoken) {
		this.languagesSpoken = languagesSpoken;
	}
	public DatingUser() {
	}
	public DatingUser(String education) {
		this.education = education;
	}

	public DatingUser(String education, String hairColor, String eyeColor, int heightInches) {
		this.education = education;
		this.hairColor = hairColor;
		this.eyeColor = eyeColor;
		this.heightInches = heightInches;
	}


	
}
