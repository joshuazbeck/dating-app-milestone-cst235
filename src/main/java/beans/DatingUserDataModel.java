package beans;

import javax.xml.bind.annotation.*;

@XmlRootElement(name="Response")
@XmlAccessorType(XmlAccessType.FIELD)
public class DatingUserDataModel {
	private DatingUser data;
	
	
	public DatingUserDataModel() {
		super();
	}
	public DatingUserDataModel(int status, String message, DatingUser data) {
		super();
		this.data = data;
	}
	
	public DatingUser getData() {
		return data;
	}
	public void setData(DatingUser data) {
		this.data = data;
	}
}