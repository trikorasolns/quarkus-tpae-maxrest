package com.trikorasolutions.mw.eam.maximo.rest.service.mbo.resources;

import com.trikorasolutions.mw.eam.maximo.rest.service.common.MaxRestCommonService;
import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;
import io.vertx.core.json.JsonObject;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import java.util.Map;

@Path("/mbo/labtrans")
@RegisterRestClient(configKey = "labtrans-api")
public interface MaxMboLaborTransactionsService extends MaxRestCommonService {

  @PUT
  @Path("/")
  @Produces("application/json")
//  , @QueryParam("siteid") String site, @QueryParam("description") String description
  Uni<JsonObject> addLt(@QueryParam("_lid") String uid, @QueryParam("_lpwd") String pwd, @QueryParam("laborcode") String laborcode
      , @QueryParam("crew") String crew, @QueryParam("refwo") String refwo, @QueryParam("startdate") String startdate
      , @QueryParam("finishdate") String finishdate, @QueryParam("starttime") String starttime, @QueryParam("finishtime") String finishtime
      , @QueryParam("regularhrs") String regularhrs, @QueryParam("premiumpayhours") String premiumpayhours, @QueryParam("premiumpaycode") String premiumpaycode
      , @QueryParam("transtype") String transtype, Map<String, String> multiValueMap);

  @PUT
  @Path("/{id}")
  @Produces("application/json")
  Uni<JsonObject> modifyLt(@QueryParam("_lid") String uid, @QueryParam("_lpwd") String pwd, @PathParam("id") String id, @QueryParam("laborcode") String laborcode
      , @QueryParam("crew") String crew, @QueryParam("refwo") String refwo, @QueryParam("startdate") String startdate
      , @QueryParam("finishdate") String finishdate, @QueryParam("starttime") String starttime, @QueryParam("finishtime") String finishtime
      , @QueryParam("regularhrs") String regularhrs, @QueryParam("premiumpayhours") String premiumpayhours, @QueryParam("premiumpaycode") String premiumpaycode
      , @QueryParam("transtype") String transtype, Map<String, String> multiValueMap);

//  @PUT
//  @Path("/{id}")
//  @Produces("application/json")
//  Uni<JsonObject> modify(@QueryParam("_lid") String uid, @QueryParam("_lpwd") String pwd, @PathParam("id") String id, Map<String, String> multiValueMap);


//  @GET
//  @Path("/{id}")
//  @Produces("application/json")
//  Uni<JsonObject>  getById(@QueryParam("_lid") String uid, @QueryParam("_lpwd") String pwd, @PathParam("id") String id, Map<String, String> multiValueMap);

//  @GET
//  @Path("/")
//  @Produces("application/json")
//  Multi<JsonObject> query(@QueryParam("_lid") String uid, @QueryParam("_lpwd") String pwd, Map<String, String> multiValueMap);

//  @POST
//  @Path("/{id}")
//  @Produces("application/json")
//  Uni<JsonObject> changeStatus(@QueryParam("_lid") String uid, @QueryParam("_lpwd") String pwd, @HeaderParam("x-http-method-override") String method, @PathParam("id") String id, @QueryParam("~wo") String workorder, @QueryParam("~status") String status, @QueryParam("~date") String date, @QueryParam("~memo") String memo);

}
