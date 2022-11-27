package beans;

/**
 * 
 * This Model handles transposing the status and message for Rest endpoints
 * @author Tanner Ray
 *
 */
public class RestDataModel {
	private int status;
	private String message;
	
	public RestDataModel() {
		status = 0;
		message = "";
	}
	
	public RestDataModel(int status, String message) {
		this.status = status;
		this.message = message;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	
}
