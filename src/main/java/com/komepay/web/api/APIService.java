package com.komepay.web.api;


import javax.ws.rs.*;
import javax.ws.rs.core.Response;


import com.komepay.web.models.ResponseData;
import com.komepay.web.models.User;

@Path("/")
public class APIService {

    @GET
    @Path("/listuser")
    @Produces("application/json")
    public ResponseData getUsers() {

        ResponseData responseData = new ResponseData();
        responseData.setStatus(true);
        responseData.setMessage("successfully finished request");

        return responseData;

    }

    @POST
    @Path("/auth")
    @Consumes("application/json")
    public Response doAuth(User user) {

        String result = "UserInterface created : " + user;
        return Response.status(201).entity(result).build();

    }


}


