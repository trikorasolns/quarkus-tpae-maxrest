package com.trikorasolutions.mw.eam.maximo.rest.service.common;

import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;
import io.vertx.core.json.JsonObject;

import javax.ws.rs.Consumes;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;

import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.Map;

@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public interface MaxRestCommonGetService {
    @GET
    @Path("/")
//    @Produces("application/json")
    Multi<JsonObject> find(@QueryParam("_lid") String uid, @QueryParam("_lpwd") String pwd, Map<String, String> multiValueMap);

    @GET
    @Path("/{id}")
//    @Produces("application/json")
    Uni<JsonObject> findById(@QueryParam("_lid") String uid, @QueryParam("_lpwd") String pwd, @PathParam("id") String id,
                             Map<String, String> multiValueMap);
    @GET
    @Path("/")
//    @Produces("application/json")
    Uni<JsonObject> query(@QueryParam("_lid") String uid, @QueryParam("_lpwd") String pwd, @QueryParam("_uw") String userWhere, Map<String, String> multiValueMap);
}
