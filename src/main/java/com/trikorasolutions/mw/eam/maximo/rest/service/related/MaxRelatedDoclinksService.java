package com.trikorasolutions.mw.eam.maximo.rest.service.related;

import io.smallrye.mutiny.Uni;
import io.vertx.core.json.JsonObject;
import org.jboss.resteasy.annotations.jaxrs.PathParam;
import org.jboss.resteasy.annotations.jaxrs.QueryParam;

import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

public interface MaxRelatedDoclinksService {

  @PUT
  @Path("/{id}")
  @Produces("application/json")
  Uni<JsonObject> addDocument(@QueryParam("_lid") String uid, @QueryParam("_lpwd") String pwd, @PathParam String id, @QueryParam("doclinks.id1.addinfo") String dlOneAddNfo, @QueryParam("doclinks.id1.show") String dlOneShow, @QueryParam("doclinks.id1.printthrulink") String dlOnePrintthrulink, @QueryParam("doclinks.id1.upload") String dlOneUpload, @QueryParam("doclinks.id1.urltype") String dlOneUrltype, @QueryParam("doclinks.id1.doctype") String dlOneDoctype, @QueryParam("doclinks.id1.description") String dlOneDescription, @QueryParam("doclinks.id1.filename") String dlOneFilename, @QueryParam("doclinks.id1.document") String dlOneDocument, @QueryParam("doclinks.id1.NEWURLNAME") String dlOneNewUrlName, @QueryParam("doclinks.id1.URLNAME") String dlOneUrlName, @QueryParam("doclinks.id1.documentdata") String dlOneDocumentData);
}
