package com.trikorasolutions.mw.eam.maximo.rest.service.related;

import io.smallrye.mutiny.Uni;
import io.vertx.core.json.JsonObject;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;

import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

public interface MaxRelatedMaterialsService {

  @PUT
  @Path("/{id}")
  @Produces("application/json")
  Uni<JsonObject> addMaterials(@QueryParam("_lid") String uid, @QueryParam("_lpwd") String pwd, @PathParam("id") String id
      , @QueryParam("matusetrans.id1.itemnum") String itemnum, @QueryParam("matusetrans.id1.description") String description
      , @QueryParam("matusetrans.id1.rotassetnum") String rotassetnum, @QueryParam("matusetrans.id1.quantity") String quantity
      , @QueryParam("matusetrans.id1.issuetype") String issuetype, @QueryParam("matusetrans.id1.actualdate") String actualdate
      , @QueryParam("matusetrans.id1.refwo") String refwo, @QueryParam("matusetrans.id1.storeloc") String storeloc
      , @QueryParam("matusetrans.id1.binnum") String binnum, @QueryParam("matusetrans.id1.itemsetid") String itemsetid
      , @QueryParam("matusetrans.id1.actualtaskid") String actualtaskid);
}
