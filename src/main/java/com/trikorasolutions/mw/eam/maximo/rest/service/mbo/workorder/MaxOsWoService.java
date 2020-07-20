package com.trikorasolutions.mw.eam.maximo.rest.service.mbo.workorder;

import com.trikorasolutions.mw.eam.maximo.rest.service.related.MaxRelatedDoclinksService;
import com.trikorasolutions.mw.eam.maximo.rest.service.related.MaxRelatedMaterialsService;
import com.trikorasolutions.mw.eam.maximo.rest.service.related.MaxRelatedToolsService;
import com.trikorasolutions.mw.eam.maximo.rest.service.related.MaxRelatedWorklogService;
import io.smallrye.mutiny.Uni;
import io.vertx.core.json.JsonObject;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;
import org.jboss.resteasy.annotations.jaxrs.PathParam;
import org.jboss.resteasy.annotations.jaxrs.QueryParam;

import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import java.util.Map;

@Path("/os/TKR_MW_WO")
@RegisterRestClient(configKey = "mam-api")
public interface MaxOsWoService extends MaxRelatedDoclinksService, MaxRelatedMaterialsService, MaxRelatedToolsService,
    MaxRelatedWorklogService {

  @GET
  @Path("/{id}")
  @Produces("application/json")
  Uni<JsonObject> getById(@QueryParam("_lid") String uid, @QueryParam("_lpwd") String pwd, @PathParam String id, Map<String, String> multiValueMap);


}
