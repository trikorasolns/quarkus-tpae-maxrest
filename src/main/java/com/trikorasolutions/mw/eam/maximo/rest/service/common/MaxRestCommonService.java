package com.trikorasolutions.mw.eam.maximo.rest.service.common;

import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;
import io.vertx.core.json.JsonObject;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;

import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import java.util.Map;

public interface MaxRestCommonService {

    @PUT
    @Path("/")
    @Produces("application/json")
    Uni<JsonObject> add(@QueryParam("_lid") String uid, @QueryParam("_lpwd") String pwd, Map<String, String> multiValueMap);

    @PUT
    @Path("/{id}")
    @Produces("application/json")
    Uni<JsonObject> modify(@QueryParam("_lid") String uid, @QueryParam("_lpwd") String pwd, @PathParam("id") String id,
                           Map<String, String> multiValueMap);

}
