package com.trikorasolutions.mw.eam.maximo.rest.service.mbo.asset;

import com.trikorasolutions.mw.eam.maximo.rest.service.common.MaxRestCommonService;
import io.smallrye.mutiny.Multi;
import io.vertx.core.json.JsonObject;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;
import org.jboss.resteasy.annotations.jaxrs.QueryParam;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import java.util.Map;

@Path("/mbo/multiassetlocci")
@RegisterRestClient(configKey = "mam-api")
public interface MaxMboMultiassetService extends MaxRestCommonService {

  @GET
  @Path("/")
  @Produces("application/json")
  Multi<JsonObject> findBySiteRecordey(@QueryParam("_lid") String uid, @QueryParam("_lpwd") String pwd, @QueryParam("siteid") String siteId, @QueryParam("recordkey") String recordKey, Map<String, String> multiValueMap);
}
