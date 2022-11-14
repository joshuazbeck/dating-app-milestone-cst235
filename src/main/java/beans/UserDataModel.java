package beans;

import javax.xml.bind.annotation.*;

@XmlRootElement(name="Response")
@XmlAccessorType(XmlAccessType.FIELD)
public class UserDataModel {
	private User data;
	
	
	public UserDataModel() {
		super();
	}
	public UserDataModel(int status, String message, User data) {
		super();
		this.data = data;
	}
	
	public User getData() {
		return data;
	}
	public void setData(User data) {
		this.data = data;
	}
}
