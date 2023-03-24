package com.trikorasolutions.mw.eam.maximo.rest.service.mbo.inventory;

import com.trikorasolutions.mw.eam.maximo.rest.service.common.MaxRestCommonGetService;
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

@Path("/mbo/invbalances")
@RegisterRestClient(configKey = "tpae-api")
public interface TpaeMboInvbalancesService extends MaxRestCommonGetService {

  @GET
  @Path("/")
  @Produces("application/json")
  Uni<JsonObject> queryItemset(@QueryParam("_lid") String uid, @QueryParam("_lpwd") String pwd, @QueryParam("itemset") String itemset,
                           Map<String, String> multiValueMap);

  @GET
  @Path("/")
  @Produces("application/json")
  Uni<JsonObject> queryItemsetSite(@QueryParam("_lid") String uid, @QueryParam("_lpwd") String pwd, @QueryParam("itemset") String itemset, @QueryParam("siteid") String site,
                                   Map<String, String> multiValueMap);

}
