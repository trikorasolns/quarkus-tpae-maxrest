package com.trikorasolutions.mw.eam.maximo.rest.service.common;

import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;
import io.vertx.core.json.JsonObject;
import org.jboss.resteasy.annotations.jaxrs.PathParam;
import org.jboss.resteasy.annotations.jaxrs.QueryParam;

import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import java.util.Map;

public interface MaxRestCommonService {
  @GET
  @Path("/")
  @Produces("application/json")
  Multi<JsonObject> find(@QueryParam("_lid") String uid, @QueryParam("_lpwd") String pwd, Map<String, String> multiValueMap);

  @GET
  @Path("/{id}")
  @Produces("application/json")
  Uni<JsonObject> findById(@QueryParam("_lid") String uid, @QueryParam("_lpwd") String pwd, @PathParam String id, Map<String, String> multiValueMap);

  @PUT
  @Path("/")
  @Produces("application/json")
  Uni<JsonObject> add(@QueryParam("_lid") String uid, @QueryParam("_lpwd") String pwd, Map<String, String> multiValueMap);

  @PUT
  @Path("/{id}")
  @Produces("application/json")
  Uni<JsonObject> modify(@QueryParam("_lid") String uid, @QueryParam("_lpwd") String pwd, @PathParam String id, Map<String, String> multiValueMap);
}
