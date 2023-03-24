package com.trikorasolutions.mw.eam.maximo.rest.service.mbo.inventory;

import com.trikorasolutions.mw.eam.maximo.rest.service.common.MaxRestCommonGetService;
import io.quarkus.arc.DefaultBean;
import io.smallrye.mutiny.Uni;
import io.vertx.core.json.JsonObject;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import java.util.Map;

@DefaultBean
@Path("/mbo/invbalances")
@RegisterRestClient(configKey = "tpae-api")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public interface TpaeMboInvbalancesService extends MaxRestCommonGetService {

  @GET
  @Path("/")
  @Produces(MediaType.APPLICATION_JSON)
  @Consumes(MediaType.APPLICATION_JSON)
  Uni<JsonObject> queryItemset(@QueryParam("_lid") String uid, @QueryParam("_lpwd") String pwd, @QueryParam("itemset") String itemset,
                           Map<String, String> multiValueMap);

  @GET
  @Path("/")
  @Produces(MediaType.APPLICATION_JSON)
  @Consumes(MediaType.APPLICATION_JSON)
  Uni<JsonObject> queryItemsetSite(@QueryParam("_lid") String uid, @QueryParam("_lpwd") String pwd, @QueryParam("itemset") String itemset, @QueryParam("siteid") String site,
                                   Map<String, String> multiValueMap);

}
