package com.trikorasolutions.mw.eam.maximo.rest.service.related;

import io.smallrye.mutiny.Uni;
import io.vertx.core.json.JsonObject;
import org.jboss.resteasy.annotations.jaxrs.PathParam;
import org.jboss.resteasy.annotations.jaxrs.QueryParam;

import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

public interface MaxRelatedWorklogService {

  @PUT
  @Path("/{id}")
  @Produces("application/json")
  Uni<JsonObject> addWorklog(@QueryParam("_lid") String uid, @QueryParam("_lpwd") String pwd, @PathParam String id,
      @QueryParam("worklog.id1.logtype") String logtype, @QueryParam("worklog.id1.createdate") String createdate,
      @QueryParam("worklog.id1.description") String description,
      @QueryParam("worklog.id1.DESCRIPTION_LONGDESCRIPTION") String longdescription);
}
