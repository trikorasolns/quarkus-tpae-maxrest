package com.trikorasolutions.mw.eam.maximo.rest.service.mbo.inventory;

import com.trikorasolutions.mw.eam.maximo.rest.service.common.MaxRestCommonGetService;
import io.smallrye.mutiny.Uni;
import io.vertx.core.json.JsonObject;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;


import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import java.util.Map;

@Path("/mbo/item")
@RegisterRestClient(configKey = "tpae-api")
public interface TpaeMboItemService extends MaxRestCommonGetService  {

  @GET
  @Path("/")
  @Produces("application/json")
  Uni<JsonObject> queryByItemNum(@QueryParam("_lid") String uid, @QueryParam("_lpwd") String pwd,
                                 @QueryParam("itemset") String itemset, @QueryParam("itemnum") String itemnum,
                                 Map<String, String> multiValueMap);

  @GET
  @Path("/")
  @Produces("application/json")
  Uni<JsonObject> queryByType(@QueryParam("_lid") String uid, @QueryParam("_lpwd") String pwd,
                                 @QueryParam("itemset") String itemset, @QueryParam("itemtype") String itemtype,
                                 Map<String, String> multiValueMap);

  @GET
  @Path("/")
  @Produces("application/json")
  Uni<JsonObject> queryByStatus(@QueryParam("_lid") String uid, @QueryParam("_lpwd") String pwd,
                              @QueryParam("itemset") String itemset, @QueryParam("status") String status, @QueryParam("itemtype") String itemtype,
                              Map<String, String> multiValueMap);

  @GET
  @Path("/")
  @Produces("application/json")
  Uni<JsonObject> queryItemset(@QueryParam("_lid") String uid, @QueryParam("_lpwd") String pwd, @QueryParam("itemset") String itemset,
                            Map<String, String> multiValueMap);

}
