package com.trikorasolutions.mw.eam.maximo.rest.service.mbo.workorder;

import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;
import io.vertx.core.json.JsonObject;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;
import org.jboss.resteasy.annotations.jaxrs.HeaderParam;
import org.jboss.resteasy.annotations.jaxrs.PathParam;
import org.jboss.resteasy.annotations.jaxrs.QueryParam;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import java.util.Map;

@Path("/mbo/workorder")
@RegisterRestClient(configKey = "mam-api")
public interface MaxMboWoService {

  @PUT
  @Path("/")
  @Produces("application/json")
  Uni<JsonObject> add(@QueryParam("_lid") String uid, @QueryParam("_lpwd") String pwd, Map<String, String> multiValueMap);

  @PUT
  @Path("/{id}")
  @Produces("application/json")
  Uni<JsonObject> modify(@QueryParam("_lid") String uid, @QueryParam("_lpwd") String pwd, @PathParam String id, Map<String, String> multiValueMap);

  @GET
  @Path("/{id}")
  @Produces("application/json")
  Uni<JsonObject> getById(@QueryParam("_lid") String uid, @QueryParam("_lpwd") String pwd, @PathParam String id, Map<String, String> multiValueMap);

  @GET
  @Path("/")
  @Produces("application/json")
  Uni<JsonObject> getByNum(@QueryParam("_lid") String uid, @QueryParam("_lpwd") String pwd, @QueryParam("wonum") String workorder, @QueryParam("siteid") String site, Map<String, String> multiValueMap);

  @GET
  @Path("/")
  @Produces("application/json")
  Multi<JsonObject> query(@QueryParam("_lid") String uid, @QueryParam("_lpwd") String pwd, Map<String, String> multiValueMap);

  @POST
  @Path("/{id}")
  @Produces("application/json")
  Uni<JsonObject> changeStatus(@QueryParam("_lid") String uid, @QueryParam("_lpwd") String pwd, @HeaderParam("x-http-method-override") String method, @PathParam String id, @QueryParam("~wo") String workorder, @QueryParam("~status") String status, @QueryParam("~date") String date, @QueryParam("~memo") String memo);
}
