package business;

import java.sql.SQLException;

import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import beans.DatingUser;
import beans.DatingUserDataModel;
import beans.User;
import beans.UserDataModel;
import data.DatabaseServiceInterface2;

/**
 * This is the service responsible for managing the REST endpoint
 * @author Josh Beck
 *
 */
@Path("/users")
@Produces({ "application/xml",  "application/json" })
@Consumes({ "application/xml", "application/json" })
public class RestService {
	@EJB
	DatabaseServiceInterface2 service;
	
	@GET
	@Path("/getusersjson/{firstName}/{lastName}")
	@Produces(MediaType.APPLICATION_JSON)
	public UserDataModel getUserAsJson(@PathParam("firstName") String firstName, @PathParam("lastName") String lastName) {
		try {
			User u = service.findUserByFirstOrLast(new User(firstName, lastName));
			return new UserDataModel(0, "", u);
		} catch (Exception e) {
			return null;
		}
	}
	
	@GET
	@Path("/getdatingusersjson/{education}/{hairColor}/{eyeColor}/{heightInches}")
	@Produces(MediaType.APPLICATION_JSON)
	public DatingUserDataModel getDatingUserAsJson(@PathParam("education") String education, @PathParam("hairColor") String hairColor, @PathParam("eyeColor") String eyeColor, @PathParam("heightInches") int heightInches ) {
		try {
			DatingUser du = service.getDatingUser(new DatingUser(education, hairColor, eyeColor, heightInches));
			return new DatingUserDataModel(0, "", du);
		} catch (Exception e) {
			return null;
		}
	}
	
	@GET
	@Path("/logout")
	public String logout() {
//		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
		return "hi";
	}
	@GET
	@Path("/deleteuser/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public String deleteUser(@PathParam("id") int id) {
		try {
			User u = new User();
			u.setId(id);
			service.deleteUser(u);
			return "Successfully deleted user id: " + id;
		} catch (RuntimeException e) {
			System.out.println("There was a runtime exception " + e.getLocalizedMessage());
		} catch (SQLException e) {
			System.out.println("There was a SQL exception " + e.getLocalizedMessage());
		}
		return "Failed deleting the user id: " + id;
	}

}