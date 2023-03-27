package com.trikorasolutions.mw.eam.maximo.rest.service.common;

import io.smallrye.mutiny.Uni;
import io.vertx.core.json.JsonObject;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

@Path("/mbo")
@RegisterRestClient(configKey = "tpae-api")
public interface MaxRestStatusService {

    /**
     * @param method
     * @param mboName
     * @param id
     * @param uid
     * @param pwd
     * @param workorder
     * @param status
     * @param date      <p>Format: YYYY-MM-DD</p>
     * @param memo
     * @return
     */
    @POST
    @Path("/{mboName}/{id}")
    @Produces("application/json")
    Uni<JsonObject> changeStatus(@HeaderParam("x-http-method-override") String method, @PathParam("mboName") String mboName,
                                 @PathParam("id") String id, @QueryParam("_lid") String uid, @QueryParam("_lpwd") String pwd,
                                 @QueryParam("~wo") String workorder, @QueryParam("~status") String status,
                                 @QueryParam("~date") String date, @QueryParam("~memo") String memo);

}
