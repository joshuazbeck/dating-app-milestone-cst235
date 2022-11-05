package beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 * This is the "product" for the Milestone
 * It contains a reference to a User object for further information
 * @author Josh Beck
 *
 */
@ManagedBean
@SessionScoped
public class DatingUser implements Serializable {
	
	private static final long serialVersionUID = 2L;
	
	List<String> hobbies;
	
	String education;
	
	List<String> languagesSpoken;
	
	float compatibilityScore;
	
	List<DatingUser> previousRelationships;
	
	String hairColor;
	
	String eyeColor;
	
	int heightInches;
	
	User userRef;
	
	public List<String> getHobbies() {
		return hobbies;
	}
	public void setHobbies(List<String> hobbies) {
		this.hobbies = hobbies;
	}
	public String getEducation() {
		return education;
	}
	public void setEducation(String education) {
		this.education = education;
	}
	public List<String> getLanguagesSpoken() {
		return languagesSpoken;
	}
	public void setLanguagesSpoken(List<String> languagesSpoken) {
		this.languagesSpoken = languagesSpoken;
	}
	public float getCompatibilityScore() {
		return compatibilityScore;
	}
	public void setCompatibilityScore(float compatibilityScore) {
		this.compatibilityScore = compatibilityScore;
	}
	public List<DatingUser> getPreviousRelationships() {
		return previousRelationships;
	}
	public void setPreviousRelationships(List<DatingUser> previousRelationships) {
		this.previousRelationships = previousRelationships;
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
	public DatingUser(List<String> hobbies, String education, List<String> languagesSpoken, float compatibilityScore,
			List<DatingUser> previousRelationships, String hairColor, String eyeColor, int heightInches, User userRef) {
		super();
		this.hobbies = hobbies;
		this.education = education;
		this.languagesSpoken = languagesSpoken;
		this.compatibilityScore = compatibilityScore;
		this.previousRelationships = previousRelationships;
		this.hairColor = hairColor;
		this.eyeColor = eyeColor;
		this.heightInches = heightInches;
		this.userRef = userRef;
	}
	public DatingUser() {
		education = "Student at GCU";
		compatibilityScore = 99.9f;
		hairColor = "brown";
		eyeColor = "brown";
		heightInches = 70;
	}


	
}
