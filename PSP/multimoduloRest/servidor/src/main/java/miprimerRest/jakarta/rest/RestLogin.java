package miprimerRest.jakarta.rest;


import jakarta.servlet.http.HttpServletRequest;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;

@Path("/login")

@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class RestLogin {



    @Context
    HttpServletRequest request;

    @GET
    public Boolean getLogin() {

        request.getSession().setAttribute("LOGIN", true);
        return true;
    }


}
