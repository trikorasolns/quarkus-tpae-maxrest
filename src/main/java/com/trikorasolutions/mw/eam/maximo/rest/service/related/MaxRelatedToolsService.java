package com.trikorasolutions.mw.eam.maximo.rest.service.related;

import io.smallrye.mutiny.Uni;
import io.vertx.core.json.JsonObject;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;

import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

public interface MaxRelatedToolsService {

  @PUT
  @Path("/{id}")
  @Produces("application/json")
  Uni<JsonObject> addTools(@QueryParam("_lid") String uid, @QueryParam("_lpwd") String pwd, @PathParam("id") String id, @QueryParam("matusetrans.id1.itemnum") String itemnum, @QueryParam("matusetrans.id1.rotassetnum") String rotassetnum, @QueryParam("matusetrans.id1.ROTASSETSITE") String rotassetsite, @QueryParam("matusetrans.id1.TOOLQTY") String toolqty, @QueryParam("matusetrans.id1.TOOLHRS") String toolhrs, @QueryParam("matusetrans.id1.refwo") String refwo, @QueryParam("matusetrans.id1.enterdate") String enterdate);
}
